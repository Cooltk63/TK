package com.crs.iamservice.Service;


import com.crs.iamservice.Model.ResponseVO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class PdfTemplateServiceImpl implements PdfTemplateService {

    @Override
    public ResponseEntity<Map<String, Object>> generateTemplate(Map<String, Object> payload) {
        // Step 1: Extract template string from userData map
        Map<String, Object> userData=(Map<String, Object>) payload.get("user");
        Map<String, Object> dataMap=(Map<String, Object>) payload.get("data");

        ResponseVO<String> responseVO = new ResponseVO<>();

//        String template = (String) userData.get("template"); // key must be "template"
        String template = template();

        if (template == null || template.trim().isEmpty()) {
            throw new IllegalArgumentException("Template string is missing in userData map");
        }

        // Step 2: Convert dataMap <String, Object> to <String, String> safely
        Map<String, String> stringDataMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            if (entry.getValue() != null) {
                log.info("Values Setting ::: KEY :"+entry.getKey() +" ::: VALUE :"+entry.getValue());
                stringDataMap.put(entry.getKey(), entry.getValue().toString());
            }
        }

        // Step 3: Replace placeholders like {{KEY}} with actual values
        String filledText = replacePlaceholders(template, stringDataMap);

        // Step 4: Convert plain text to minimal HTML (for formatting)
        String htmlContent = wrapAsHtml(filledText,stringDataMap);

        // Step 5: Generate PDF using iText + XMLWorker
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            InputStream htmlStream = new ByteArrayInputStream(htmlContent.getBytes(StandardCharsets.UTF_8));
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, htmlStream, StandardCharsets.UTF_8);
            document.close();

            String result=Base64.getEncoder().encodeToString(outputStream.toByteArray()).toString();

            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Failed to generate Template Data");
            responseVO.setResult(result);

        } catch (DocumentException | IOException e) {
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Failed to generate Template Data");
            responseVO.setResult(null);
        }


        // Step 6: Encode PDF bytes to Base64 and return
        return new ResponseEntity(responseVO, HttpStatus.OK) ;
    }

    /**
     * Replaces all placeholders in format {{KEY}} in the template string
     * with corresponding values from the data map.
     *//*
    private String replacePlaceholders(String template, Map<String, String> data) {
        String result = template;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String regex = "\\{\\{\\s*" + Pattern.quote(key) + "\\s*\\}\\}";
            result = result.replace(regex, Matcher.quoteReplacement(value));
        }
        return result;
    }*/

    private String replacePlaceholders(String template, Map<String, String> data) {
        String result = template;

        // Match all {{KEY}} patterns
        Pattern pattern = Pattern.compile("\\{\\{\\s*(\\w+)\\s*\\}\\}");
        Matcher matcher = pattern.matcher(result);

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String key = matcher.group(1); // extract key inside {{ }}
            String value = data.getOrDefault(key, ""); // fallback to empty string if not present
            matcher.appendReplacement(sb, Matcher.quoteReplacement(value));
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * Wraps plain text as basic HTML and replaces line breaks with <br/>
     * to preserve formatting in the final PDF.
     */
  /*  private String wrapAsHtml(String text) {
        String htmlFormatted = text.replaceAll("\n", "<br/>");
        return "<html><body style='font-family:Helvetica; font-size:12px;'>" + htmlFormatted + "</body></html>";
    }*/

    private String wrapAsHtml(String text, Map<String, String> data) {
        String htmlFormatted = text.replaceAll("\n", "<br/>");

        return "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Helvetica; font-size: 12px; }" +
                ".header { display: flex; justify-content: space-between; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='header'>" +
                "<div>Date: " + data.getOrDefault("DATE", "") + "</div>" +
                "<div>Ref. No.: " + data.getOrDefault("REF_NO", "") + "</div>" +
                "</div>" +
                "<br/><br/>" +
                htmlFormatted +
                "</body></html>";
    }


    private String template()
    {
        return "\t\t\t\t\t\t\t\t\t[[Date:]]~{{DATE}}\n" +
                "\t\t\t\t\t\t\t\t\t[[Ref. No.: ]]~{{REF_NO}}\n" +
                "\n" +
                "[[Name of the Firm.]]~{{FIRM_NAME}}\n" +
                "[[FRN No.]]~{{FRN_NO}}\n" +
                "[[GSTIN No.]]~{{GSTN}}\n" +
                "[[Address of the Firm]]~{{FIRM_ADDR}}\n" +
                "\n" +
                "\n" +
                "Madam/ Dear Sir,\n" +
                "\n" +
                "EMPANELMENT OF THE FIRM\n" +
                "INTIMATION\n" +
                "\n" +
                "We are glad to inform you that your firm has been empanelled as [[type of assignment]]~{{ASSIGNMENT_TYPE}} \n" +
                "in our Bank.\n" +
                "\n" +
                "2. This empanelment as [[type of assignment]]~{{ASSIGNMENT_TYPE}} does not mean assignment of \n" +
                "mandate in respect of any specific work. Assignment of specific mandate will be \n" +
                "done and documented by the branch (es) by way of issuing separate letter of \n" +
                "allotment of work.\n" +
                "\n" +
                "3. You are advised to mention the reference no. of this letter in future \n" +
                "correspondence with the branch/ bank. Please also mention this reference no. \n" +
                "while presenting any bill to the branch/ bank in respect of the assignment \n" +
                "entrusted to your firm.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Yours faithfully, \n" +
                "\n" +
                "\n" +
                "(Authorised Signatory)\n" +
                "\n" +
                "\n";
    }
}


This is my service Impl file i wanted to do the emails to the firm on thier provided emails just skip this email part just written the email sent function make it empty 

1- I have received lots of 1-4000 firm data for dynamic pdf generation from FE - Suggest me how did i get the data on my backend List of List or List <Map> Confused right now.
2- After genearting the pdf for each firm store each and every pdf on device mine case server path is /media/IAM/ : Either pdf generate 1 to 4000 
3- after completion of this pdf files generation & storing need to give option to FE user for download all of this PDf files into .Zip file that means I have to write the code for this all pdf files ZIp download aslo 
4- I have alredy written above pdf generation based on data so for large amount data process suggest me faster & smooth and reliable way to do soeverthing with proper comments and explplaination.
