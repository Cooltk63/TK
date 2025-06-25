@Autowired
IAMEmailRepository iamEmailRepository;

private void logEmailStatus(int frnNo, int userId, String toEmail, String payload, String status, String remark) {
    try {
        IAM_Email log = new IAM_Email();
        log.setFrnno(frnNo);
        log.setUserid(userId);
        log.setFrnemailid(toEmail);
        log.setEmaildata(payload);
        log.setEmaildate(new Date());
        log.setEmailstatus(status); // "SUCCESS" or "FAIL"
        log.setEmailremark(remark);
        iamEmailRepository.save(log);
    } catch (Exception e) {
        log.error("Failed to log email status to DB", e);
    }
}


xxxx
public boolean sendEmailWithAttachment(String toEmail, String toName, String subject, String htmlBody, byte[] pdfBytes, String fileName, int frnNo, int userId) {
    try {
        CRSSettings crsSetting = crsSettingsRepository.getUrl("EMAIL", preIpString);
        String apiKey = crsSetting.getApiKey();
        String apiUrl = crsSetting.getUrl();

        log.info("Fetched API Key & URL from DB: APIKEY :: {} APIURL :: {}", apiKey, apiUrl);

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("api_key", apiKey);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        JSONObject emailRequest = buildEmailRequest(toEmail, toName, subject, htmlBody, pdfBytes, fileName);
        emailRequest.put("email", crsSetting.getEmailSenderId());
        emailRequest.put("name", CRS);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = emailRequest.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            responseBuilder.append(inputLine);
        }
        in.close();
        connection.disconnect();

        if (responseCode == HttpURLConnection.HTTP_ACCEPTED || responseCode == HttpURLConnection.HTTP_OK) {
            log.info("Email sent successfully to {} with file {}", toEmail, fileName);

            // Log success
            logEmailStatus(frnNo, userId, toEmail, emailRequest.toString(), "SUCCESS", "Delivered");

            return true;
        } else {
            log.error("Email failed. Code: {}, Message: {}", responseCode, connection.getResponseMessage());

            // Log failure
            logEmailStatus(frnNo, userId, toEmail, emailRequest.toString(), "FAIL", connection.getResponseMessage());

            return false;
        }

    } catch (Exception e) {
        log.error("Exception during email send", e);
        logEmailStatus(frnNo, userId, toEmail, htmlBody, "FAIL", e.getMessage());
        return false;
    }
}