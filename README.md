package com.crs.iamservice.Service;

import com.crs.iamservice.Model.ResponseVO; import com.itextpdf.text.Document; import com.itextpdf.text.DocumentException; import com.itextpdf.text.PageSize; import com.itextpdf.text.pdf.PdfWriter; import com.itextpdf.tool.xml.XMLWorkerHelper; import lombok.extern.slf4j.Slf4j; import org.springframework.beans.factory.annotation.Value; import org.springframework.http.HttpStatus; import org.springframework.http.ResponseEntity; import org.springframework.stereotype.Service;

import java.io.; import java.nio.charset.StandardCharsets; import java.time.LocalDate; import java.util.; import java.util.concurrent.*; import java.util.regex.Matcher; import java.util.regex.Pattern; import java.util.zip.ZipEntry; import java.util.zip.ZipOutputStream;

@Slf4j @Service public class DownloadPDFServiceImpl implements DownloadPDFService {

@Value("${IAM.base.path:/media/IAM/}")
private String IAM_BASE_PATH;

private static final String ZIP_FILENAME = "IAM_Combined.zip";

@Override
public ResponseEntity<Map<String, Object>> downloadAllPdf(Map<String, Object> payload) {
    Map<String, Object> userData = (Map<String, Object>) payload.get("user");
    List<Map<String, Object>> firmList = (List<Map<String, Object>>) payload.get("firms");

    ResponseVO<String> responseVO = new ResponseVO<>();
    String template = template();

    if (template == null || template.trim().isEmpty()) {
        throw new IllegalArgumentException("Template string is missing in userData map");
    }

    String yearFolder = getFinancialYearFolder();
    String yearPath = IAM_BASE_PATH + yearFolder;
    File yearDir = new File(yearPath);
    if (!yearDir.exists()) yearDir.mkdirs();

    ExecutorService executor = Executors.newFixedThreadPool(10);
    List<Future<?>> futures = new ArrayList<>();

    for (Map<String, Object> dataMap : firmList) {
        futures.add(executor.submit(() -> {
            try {
                Map<String, String> stringDataMap = new HashMap<>();
                dataMap.forEach((k, v) -> {
                    if (v != null) stringDataMap.put(k, v.toString());
                });

                String filledText = replacePlaceholders(template, stringDataMap);
                String htmlContent = wrapAsHtml(filledText, stringDataMap);
                byte[] pdfBytes = generatePdfBytes(htmlContent);

                String fileName = (stringDataMap.getOrDefault("FIRM_NAME", "UnknownFirm")
                        .replaceAll("\\s+", "_") + "_"
                        + stringDataMap.getOrDefault("FRN_NO", "null") + ".pdf");

                savePdfToDisk(yearPath, fileName, pdfBytes);
                sendEmailStub(stringDataMap.get("EMAIL"), pdfBytes, fileName);

            } catch (Exception e) {
                log.error("Error generating PDF for firm: " + dataMap, e);
            }
        }));
    }

    executor.shutdown();
    try {
        executor.awaitTermination(20, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
        log.error("Executor interrupted.", e);
    }

    try {
        zipAllGeneratedPdfs(yearPath, yearPath + "/" + ZIP_FILENAME);
    } catch (IOException e) {
        log.error("Error while creating ZIP file", e);
    }

    responseVO.setStatusCode(HttpStatus.OK.value());
    responseVO.setMessage("PDFs generated and ZIP created successfully.");
    responseVO.setResult(Base64.getEncoder().encodeToString((yearPath + "/" + ZIP_FILENAME).getBytes()));
    return new ResponseEntity<>(Map.of("response", responseVO), HttpStatus.OK);
}

private byte[] generatePdfBytes(String htmlContent) throws IOException, DocumentException {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        InputStream htmlStream = new ByteArrayInputStream(htmlContent.getBytes(StandardCharsets.UTF_8));
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, htmlStream, StandardCharsets.UTF_8);
        document.close();
        return outputStream.toByteArray();
    }
}

private void savePdfToDisk(String directoryPath, String filename, byte[] pdfBytes) throws IOException {
    File file = new File(directoryPath, filename);
    try (FileOutputStream fos = new FileOutputStream(file)) {
        fos.write(pdfBytes);
    }
}

private void zipAllGeneratedPdfs(String sourceFolder, String outputZipFile) throws IOException {
    try (FileOutputStream fos = new FileOutputStream(outputZipFile);
         ZipOutputStream zipOut = new ZipOutputStream(fos)) {
        File dir = new File(sourceFolder);
        for (File file : Objects.requireNonNull(dir.listFiles((d, name) -> name.endsWith(".pdf")))) {
            try (FileInputStream fis = new FileInputStream(file)) {
                zipOut.putNextEntry(new ZipEntry(file.getName()));
                fis.transferTo(zipOut);
                zipOut.closeEntry();
            }
        }
    }
}

private void sendEmailStub(String email, byte[] pdfBytes, String filename) {
    log.info("[EMAIL STUB] Would send email to: {} with PDF: {} ({} bytes)", email, filename, pdfBytes.length);
}

private String replacePlaceholders(String template, Map<String, String> data) {
    Pattern pattern = Pattern.compile("\\{\\{\\s*(\\w+)\\s*}}\\");
    Matcher matcher = pattern.matcher(template);
    StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
        String key = matcher.group(1);
        String value = data.getOrDefault(key, "");
        matcher.appendReplacement(sb, Matcher.quoteReplacement(value));
    }
    matcher.appendTail(sb);
    return sb.toString();
}

private String wrapAsHtml(String text, Map<String, String> data) {
    String htmlFormatted = text.replaceAll("\n", "<br/>");
    return "<html><head><style>" +
            "body { font-family: Helvetica; font-size: 12px; }" +
            ".header { display: flex; justify-content: space-between; margin-bottom: 20px; }" +
            ".pdf-container { border: 2px solid black; padding: 40px; margin: 30px; height: 95%; width: 90%; box-sizing: border-box; }" +
            "</style></head><body><div class='pdf-container'>" +
            "<div class='header'><div>Date: " + data.getOrDefault("DATE", "") + "</div>" +
            "<div>Ref. No.: " + data.getOrDefault("REF_NO", "") + "</div></div><br/><br/>" +
            htmlFormatted +
            "</div></body></html>";
}

private String template() {
    return "\t\t\t\t\t\t\t\t\t\t[[Date:]]~{{DATE}}\n" +
            "\t\t\t\t\t\t\t\t\t\t[[Ref. No.: ]]~{{REF_NO}}\n" +
            "\n[[Name of the Firm.]]~{{FIRM_NAME}}\n" +
            "[[FRN No.]]~{{FRN_NO}}\n" +
            "[[GSTIN No.]]~{{GSTN}}\n" +
            "[[Address of the Firm]]~{{FIRM_ADDR}}\n" +
            "\nMadam/ Dear Sir,\nEMPANELMENT OF THE FIRM\nINTIMATION\n" +
            "We are glad to inform you that your firm has been empanelled as [[type of assignment]]~{{ASSIGNMENT_TYPE}} in our Bank.\n" +
            "2. This empanelment as [[type of assignment]]~{{ASSIGNMENT_TYPE}} does not mean assignment of mandate in respect of any specific work. Assignment of specific mandate will be done and documented by the branch (es) by way of issuing separate letter of allotment of work.\n" +
            "3. You are advised to mention the reference no. of this letter in future correspondence with the branch/ bank. Please also mention this reference no. while presenting any bill to the branch/ bank in respect of the assignment entrusted to your firm.\n" +
            "\nYours faithfully,\n\n(Authorised Signatory)\n";
}

private String getFinancialYearFolder() {
    LocalDate today = LocalDate.now();
    int startYear = today.getMonthValue() >= 4 ? today.getYear() : today.getYear() - 1;
    return startYear + "-" + (startYear + 1);
}

}

