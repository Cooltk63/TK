public boolean sendMailTemp(String email, String name, String subject, String bodyHtml) {

    Map<String, String> result = new HashMap<>();

    log.info("########## Send Mail Temp #########");

    boolean flag = false;

    try {

        log.info("111111111");
        log.info("preIpString >> " + preIpString);


        CRSSettings crsSetting = CRSSettingsRepository.getUrl("EMAIL", preIpString);
        String apiKey = crsSetting.getApiKey(); 
        String apiUrl = crsSetting.getUrl();

        log.info("DB Fetched Params from CRS Setting: " + crsSetting.getUrl());
        log.info("DB Fetched Params from CRS Setting: " + crsSetting.getApiKey());


        // Create URL object
        URL url = new URL(apiUrl);

        // Open Connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method
        connection.setRequestMethod("POST");

        // Set API key in the request header
        // Add a parameter to the HTTP header
        connection.setRequestProperty("api_key", apiKey);

        connection.setRequestProperty("Content-Type", "application/json ");

        // Enable input/output streams
        connection.setDoOutput(true);

        // Create JSON payload (reolace with your actual payload)
        JSONObject fromJsonObj = new JSONObject();
        
        fromJsonObj.put("email", "alerts@mail.co.in"); 
        fromJsonObj.put("name", "BRS ALERT"); // 

        JSONObject contentJsonObj = new JSONObject();
        contentJsonObj.put("type", HTML);
        contentJsonObj.put("value", bodyHtml);

        JSONArray contentJsonArrayObj = new JSONArray();
        contentJsonArrayObj.put(contentJsonObj);

        JSONObject attributesJsonObj = new JSONObject();
        attributesJsonObj.put("LEAD", "Andy Dwyer");
        attributesJsonObj.put("BAND", "Mouse Rat");

        JSONObject toJsonObj = new JSONObject();
        toJsonObj.put("email", email); 
        toJsonObj.put("name", name);

        JSONArray toJsonArrayObj = new JSONArray();
        toJsonArrayObj.put(toJsonObj);

        JSONObject personalizationsJsonObj = new JSONObject();
        personalizationsJsonObj.put("attributes", attributesJsonObj);
        personalizationsJsonObj.put("to", toJsonArrayObj);
        personalizationsJsonObj.put("token_to", "8693839845");
        personalizationsJsonObj.put("token_cc", "MSGID657243");
        personalizationsJsonObj.put("token_bcc", "MSGID657244");

        JSONArray personalizationsJsonArrayObj = new JSONArray();
        personalizationsJsonArrayObj.put(personalizationsJsonObj);

        JSONObject settingsJsonObj = new JSONObject();
        settingsJsonObj.put("open_track", true);
        settingsJsonObj.put("click_track", true);
        settingsJsonObj.put("unsubscribe_track", true);

        JSONObject obj = new JSONObject();
        obj.put("from", fromJsonObj);
        obj.put("subject", subject);
        obj.put("content", contentJsonArrayObj);
        obj.put("personalizations", personalizationsJsonArrayObj);
        obj.put("settings", settingsJsonObj);
        String finalRequest = obj.toString();


        // Write JSON payload to the request
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = finalRequest.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        // Get response code
        int responseCode = connection.getResponseCode();
        InputStream responseStream = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(responseStream), 100);
        String inputLine;
        log.info("connection.getContentLength() >>>>> " + connection.getContentLength());
        StringBuffer response = new StringBuffer(100);

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        log.info("Email Response received >>> " + response);

        // Read response
        if (responseCode == HttpURLConnection.HTTP_ACCEPTED) {
            log.info("Email Response received >>> " + response);
            flag = true;

            result.put("response", String.valueOf(response));
        } else {
            log.error("Error : " + responseCode);
            log.error("Error Response message : " + connection.getResponseMessage());
            flag = false;
            result.put("response", responseCode + " : " + connection.getResponseMessage());
        }
        // Close connection
        connection.disconnect();
    } catch (IOException e) {
        log.error("Mail IOException : " + e.getMessage());
        log.error("Mail Exception : " + e.getCause());
        flag = false;
        result.put("response", e.getMessage());
    }

    result.put("flag", String.valueOf(flag));
    return flag;
}
