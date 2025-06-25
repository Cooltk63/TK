// Updated Email Payload Construction with Explanation
// This follows your original structure, with no logic change.

public JSONObject buildEmailRequest(String toEmail, String toName, String subject, String htmlContent, byte[] pdfBytes, String fileName, CRSSettings crsSetting) {

    // 1. FROM object: sender details
    JSONObject fromJsonObj = new JSONObject();
    fromJsonObj.put("email", crsSetting.getEmailSenderId()); // dynamic sender from DB
    fromJsonObj.put("name", "CRS"); // sender name shown in inbox

    // 2. CONTENT array: email body in HTML format
    JSONObject contentJsonObj = new JSONObject();
    contentJsonObj.put("type", "HTML"); // fixed type
    contentJsonObj.put("value", htmlContent); // body passed dynamically

    JSONArray contentJsonArrayObj = new JSONArray();
    contentJsonArrayObj.put(contentJsonObj);

    // 3. ATTRIBUTES: custom metadata (optional)
    JSONObject attributesJsonObj = new JSONObject();
    attributesJsonObj.put("LEAD", "Andy Dwyer");
    attributesJsonObj.put("BAND", "Mouse Rat");

    // 4. TO object: recipient email and name
    JSONObject toJsonObj = new JSONObject();
    toJsonObj.put("email", toEmail);
    toJsonObj.put("name", toName);

    JSONArray toJsonArrayObj = new JSONArray();
    toJsonArrayObj.put(toJsonObj);

    // 5. PERSONALIZATION: recipient + attributes + tokens
    JSONObject personalizationsJsonObj = new JSONObject();
    personalizationsJsonObj.put("attributes", attributesJsonObj);
    personalizationsJsonObj.put("to", toJsonArrayObj);
    personalizationsJsonObj.put("token_to", "8693839845");
    personalizationsJsonObj.put("token_cc", "MSGID657243");
    personalizationsJsonObj.put("token_bcc", "MSGID657244");

    JSONArray personalizationsJsonArrayObj = new JSONArray();
    personalizationsJsonArrayObj.put(personalizationsJsonObj);

    // 6. SETTINGS (optional analytics)
    JSONObject settingsJsonObj = new JSONObject();
    settingsJsonObj.put("open_track", true);
    settingsJsonObj.put("click_track", true);
    settingsJsonObj.put("unsubscribe_track", true);

    // 7. ATTACHMENT: if any PDF is present
    JSONArray attachments = new JSONArray();
    if (pdfBytes != null && pdfBytes.length > 0) {
        JSONObject attachment = new JSONObject();
        attachment.put("content", Base64.getEncoder().encodeToString(pdfBytes));
        attachment.put("filename", fileName);
        attachment.put("type", "application/pdf");
        attachment.put("disposition", "attachment");
        attachments.put(attachment);
    }

    // 8. FINAL JSON OBJECT
    JSONObject obj = new JSONObject();
    obj.put("from", fromJsonObj);
    obj.put("subject", subject);
    obj.put("content", contentJsonArrayObj);
    obj.put("personalizations", personalizationsJsonArrayObj);
    obj.put("settings", settingsJsonObj);
    if (!attachments.isEmpty()) {
        obj.put("attachments", attachments);
    }

    return obj; // use .toString() where required
}
