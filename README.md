@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${email.api.key}")
    private String apiKey;

    @Value("${email.api.url}")
    private String apiUrl;

    @Override
    public boolean sendHtmlEmailWithAttachment(String toEmail, String toName, String subject, String htmlBody, byte[] pdfBytes, String fileName) {
        try {
            // Create request object with headers
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("api_key", apiKey);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Create JSON body with all fields
            JSONObject emailRequest = buildEmailRequest(toEmail, toName, subject, htmlBody, pdfBytes, fileName);

            // Write JSON to output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = emailRequest.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read response
            int responseCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder responseBuilder = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                responseBuilder.append(inputLine);
            }
            in.close();
            connection.disconnect();

            if (responseCode == HttpURLConnection.HTTP_ACCEPTED || responseCode == HttpURLConnection.HTTP_OK) {
                log.info("Email sent successfully to {} with file {}", toEmail, fileName);
                return true;
            } else {
                log.error("Failed to send email. Response code: {}, message: {}", responseCode, connection.getResponseMessage());
                return false;
            }

        } catch (Exception e) {
            log.error("Exception while sending email with attachment", e);
            return false;
        }
    }

    // Helper method to build the JSON payload
    private JSONObject buildEmailRequest(String toEmail, String toName, String subject, String htmlContent, byte[] pdfBytes, String fileName) {
        JSONObject from = new JSONObject();
        from.put("email", "alerts@mail.co.in");
        from.put("name", "BRS ALERT");

        JSONObject content = new JSONObject();
        content.put("type", "HTML");
        content.put("value", htmlContent);
        JSONArray contentArray = new JSONArray().put(content);

        JSONObject to = new JSONObject();
        to.put("email", toEmail);
        to.put("name", toName);
        JSONArray toArray = new JSONArray().put(to);

        JSONObject personalization = new JSONObject();
        personalization.put("to", toArray);
        personalization.put("attributes", new JSONObject(Map.of("FIRM", toName)));
        personalization.put("token_to", "8693839845");
        personalization.put("token_cc", "MSGID657243");
        personalization.put("token_bcc", "MSGID657244");
        JSONArray personalizations = new JSONArray().put(personalization);

        JSONObject attachment = new JSONObject();
        attachment.put("content", Base64.getEncoder().encodeToString(pdfBytes));
        attachment.put("filename", fileName);
        attachment.put("type", "application/pdf");
        attachment.put("disposition", "attachment");
        JSONArray attachments = new JSONArray().put(attachment);

        JSONObject settings = new JSONObject();
        settings.put("open_track", true);
        settings.put("click_track", true);
        settings.put("unsubscribe_track", true);

        JSONObject emailRequest = new JSONObject();
        emailRequest.put("from", from);
        emailRequest.put("subject", subject);
        emailRequest.put("content", contentArray);
        emailRequest.put("personalizations", personalizations);
        emailRequest.put("attachments", attachments);
        emailRequest.put("settings", settings);

        return emailRequest;
    }
}