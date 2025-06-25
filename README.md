// Create JSON payload (reolace with your actual payload)
            JSONObject fromJsonObj = new JSONObject();
            //fromJsonObj.put("email", "ebms_uat_sender@ebmsgits.sbi.co.in"); //"ebms_uat_sender@ebmsgits.sbi.co.in"
            fromJsonObj.put("email", crsSetting.getEmailSenderId()); //"ebms_uat_sender@ebmsgits.sbi.co.in"
            fromJsonObj.put("name", CRS); // "CRS" : sender name.

            JSONObject contentJsonObj = new JSONObject();
            contentJsonObj.put("type", HTML);
            contentJsonObj.put("value", stringBuilder(name, otpForEmail));// constructs email templet

            JSONArray contentJsonArrayObj = new JSONArray();
            contentJsonArrayObj.put(contentJsonObj);

            JSONObject attributesJsonObj = new JSONObject();
            attributesJsonObj.put("LEAD", "Andy Dwyer");
            attributesJsonObj.put("BAND", "Mouse Rat");

            JSONObject toJsonObj = new JSONObject();
            toJsonObj.put("email", email); //"ebms_uat_receiver@ebmsgits.sbi.co.in" for UAT testing only.
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
            obj.put("subject", "CRS : OTP for password");
            obj.put("content", contentJsonArrayObj);
            obj.put("personalizations", personalizationsJsonArrayObj);
            obj.put("settings", settingsJsonObj);
            String finalRequest = obj.toString();
