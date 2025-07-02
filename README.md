Console Output::
2025-07-02 :: 10:40:54.098 || ERROR:: EmailServiceImpl.java: | 189 | ::  Failed to log email status to DB
org.springframework.dao.DataIntegrityViolationException: could not execute statement [ORA-00001: unique constraint (FNSONLI.IAM_EMAIL_LOGS_PK) violated
] [insert into iam_email_logs (frn_email_data,email_date,email_remark,email_status,frn_email,frn_no,user_id,rml_id) values (?,?,?,?,?,?,?,?)]; SQL [insert into iam_email_logs (frn_email_data,email_date,email_remark,email_status,frn_email,frn_no,user_id,rml_id) values (?,?,?,?,?,?,?,?)]; constraint [FNSONLI.IAM_EMAIL_LOGS_PK]
	
Caused by: org.hibernate.exception.ConstraintViolationException: could not execute statement [ORA-00001: unique constraint (FNSONLI.IAM_EMAIL_LOGS_PK) violated
] [insert into iam_email_logs (frn_email_data,email_date,email_remark,email_status,frn_email,frn_no,user_id,rml_id) values (?,?,?,?,?,?,?,?)]
	
	... 22 common frames omitted
Caused by: java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique constraint (FNSONLI.IAM_EMAIL_LOGS_PK) violated

Caused by: oracle.jdbc.OracleDatabaseException: ORA-00001: unique constraint (FNSONLI.IAM_EMAIL_LOGS_PK) violated

Code::

 @Override
    public boolean sendEmailWithAttachment(Map<String, Object> emailParams) {

        log.info("Email Params Received :{}", emailParams);

        int FRN = Integer.parseInt(emailParams.get("FRN_NO").toString());
        String toEmail = emailParams.get("FIRM_EMAIL").toString();
        String FirmName = emailParams.get("FIRM_NAME").toString();
        String subject = emailParams.get("SUBJECT").toString();
        byte[] pdfBytes = (byte[]) emailParams.get("PDFDATA");
        String fileName = emailParams.get("PDFNAME").toString();

        String htmlBody = generateHtmlBody(emailParams.get("FIRM_NAME").toString(), FRN);
        int userid = Integer.parseInt(emailParams.get("UserId").toString());
        try {

            CRSSettings crsSetting = crsSettingsRepository.getUrl("EMAIL", preIpString);
            // Getting the Email API Key & URL from DB
            String apiKey = crsSetting.getApiKey();
            String apiUrl = crsSetting.getUrl();

            log.info("Fetched API Key & URl from DB :APIKEY ::"+apiKey +"APIURL ::"+ apiUrl);

            // Create request object with headers
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            log.info("Setting-up the connection & Parameters");
            connection.setRequestMethod("POST");
            connection.setRequestProperty("api_key", apiKey);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            log.info("Connection & Parameters set Successfully");
            // Create JSON body with all fields
            JSONObject emailRequest = buildEmailRequest(crsSetting, toEmail, FirmName, subject, htmlBody, pdfBytes, fileName);

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
                // Log success
                logEmailStatus(FRN, userid, toEmail, emailRequest.toString(), "SUCCESS", "Delivered");

                return true;
            } else {
                log.error("Failed to send email. Response code: {}, message: {}", responseCode, connection.getResponseMessage());

                // Log failure
                logEmailStatus(FRN, userid, toEmail, emailRequest.toString(), "FAIL", connection.getResponseMessage());

                return false;
            }

        } catch (Exception e) {
            logEmailStatus(FRN, userid, toEmail, htmlBody, "FAIL", e.getMessage());
            log.error("Exception while sending email with attachment", e);
            return false;
        }
    }

private void logEmailStatus(int frnNo, int userId, String toEmail, String payload, String status, String remark) {
        try {
            int Sequence= iAMEmailRepository.getsequence();
            IAM_Email entity = new IAM_Email();

            entity.setRmlid(Sequence);
            entity.setFrnno(frnNo);
            entity.setUserid(userId);
            entity.setFrnemailid(toEmail);
            entity.setEmaildata(payload);
            entity.setEmaildate(new Date());
            entity.setEmailstatus(status); // "SUCCESS" or "FAIL"
            entity.setEmailremark(remark);
            log.info("logging status set: {}", entity);
            IAM_Email saveEntity=iAMEmailRepository.save(entity);
            log.info("Email Logged ::"+saveEntity.getRmlid());
        } catch (Exception e) {
            log.error("Failed to log email status to DB", e);
        }
    }

    Model Class::
    package com.crs.iamservice.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="IAM_EMAIL_LOGS")
@Getter
@Setter
public class IAM_Email {

    @Id
    @Column(name = "RML_ID")
    private int rmlid;

    @Column(name = "FRN_NO")
    private int frnno;

    @Column(name = "USER_ID")
    private int userid;

    @Column(name = "FRN_EMAIL")
    private String frnemailid;

    @Column(name = "FRN_EMAIL_DATA")
    private String emaildata;

    @Column(name = "EMAIL_DATE")
    private Date emaildate;

    @Column(name="EMAIL_STATUS")
    private String emailstatus;

    @Column(name="EMAIL_REMARK")
    private String emailremark;

}


    I wanted to log the entry even if its fail to send email or success every time need to insert new entry not any update why i am getting the above console output error tell and how to resolve
