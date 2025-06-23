@Service
public class PdfTemplateServiceImpl implements PdfTemplateService {

    /**
     * Generates PDF from plain-text template and data maps received from frontend,
     * and returns it as Base64-encoded string.
     *
     * @param userData Contains the template string (with key like "template")
     * @param dataMap Contains key-value pairs for placeholders
     * @return Base64-encoded PDF string
     * @throws Exception if any step fails
     */
    @Override
    public String generatePdfBase64(Map<String, Object> userData, Map<String, Object> dataMap) throws Exception {
        // Step 1: Extract template string from userData map
        String template = (String) userData.get("template"); // key must be "template"

        if (template == null || template.trim().isEmpty()) {
            throw new IllegalArgumentException("Template string is missing in userData map");
        }

        // Step 2: Convert dataMap <String, Object> to <String, String> safely
        Map<String, String> stringDataMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            if (entry.getValue() != null) {
                stringDataMap.put(entry.getKey(), entry.getValue().toString());
            }
        }

        // Step 3: Replace placeholders like {{KEY}} with actual values
        String filledText = replacePlaceholders(template, stringDataMap);

        // Step 4: Convert plain text to minimal HTML (for formatting)
        String htmlContent = wrapAsHtml(filledText);

        // Step 5: Generate PDF using iText + XMLWorker
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();

        InputStream htmlStream = new ByteArrayInputStream(htmlContent.getBytes(StandardCharsets.UTF_8));
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, htmlStream, StandardCharsets.UTF_8);
        document.close();

        // Step 6: Encode PDF bytes to Base64 and return
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    /**
     * Replaces all placeholders in format {{KEY}} in the template string
     * with corresponding values from the data map.
     */
    private String replacePlaceholders(String template, Map<String, String> data) {
        String result = template;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            result = result.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return result;
    }

    /**
     * Wraps plain text as basic HTML and replaces line breaks with <br/>
     * to preserve formatting in the final PDF.
     */
    private String wrapAsHtml(String text) {
        String htmlFormatted = text.replaceAll("\n", "<br/>");
        return "<html><body style='font-family:Helvetica; font-size:12px;'>" + htmlFormatted + "</body></html>";
    }
}