package com.crs.iamservice.Service;

import com.crs.iamservice.Model.CRSSettings;
import com.crs.iamservice.Model.IAM_Email;
import com.crs.iamservice.Repository.CRSSettingsRepository;
import com.crs.iamservice.Repository.IAMEmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    CRSSettingsRepository crsSettingsRepository;

    @Autowired
    IAMEmailRepository iAMEmailRepository;

    @Value("${app.preIpString}")
    private String preIpString;

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

    // Helper method to build the JSON payload
    private JSONObject buildEmailRequest(CRSSettings crsSetting, String toEmail, String FirmName, String subject, String htmlContent, byte[] pdfBytes, String pdfFilename) {
        log.info("Building Email Request");

        log.info("CRSSetting : {}"+ crsSetting.getUrl());
        log.info("CRSSetting : {}"+ crsSetting.getEmailSenderId());

        JSONObject from = new JSONObject();
        from.put("email", crsSetting.getEmailSenderId());
        from.put("name", "CRS");
        log.info("><><><><><>< FROM Success ><><><><><><");

        JSONObject content = new JSONObject();
        content.put("type", "HTML");
        // need to add the content here for Email Body
        content.put("value", htmlContent);
        JSONArray contentArray = new JSONArray().put(content);
        log.info("><><><><><>< CONTENT Success ><><><><><><");

        JSONObject to = new JSONObject();
        to.put("email", toEmail);
        to.put("name", FirmName);
        JSONArray toArray = new JSONArray().put(to);
        log.info("><><><><><>< TO Success ><><><><><><");

        JSONObject personalization = new JSONObject();
        log.info("><><><><><>< Personalization Initialized ><><><><><><");
        personalization.put("to", toArray);
        personalization.put("attributes", new JSONObject(Map.of("FIRM", FirmName)));
        personalization.put("token_to", "8693839845");
        personalization.put("token_cc", "MSGID657243");
        personalization.put("token_bcc", "MSGID657244");
        JSONArray personalizations = new JSONArray().put(personalization);
        log.info("><><><><><>< Personalization Success ><><><><><><");

        JSONObject attachment = new JSONObject();
        // Added the pdf byte[] here for Email Attachment
        attachment.put("content", pdfBytes);
        attachment.put("filename", pdfFilename);
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

    private void logEmailStatus(int frnNo, int userId, String toEmail, String payload, String status, String remark) {
        try {
            IAM_Email entity = new IAM_Email();
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

    //Generate Email Body for Sending mail
    private String generateHtmlBody(String FIRM_NAME, int FRN_NO) {

        return "<html>" +
                "<body style='font-family:Arial,sans-serif; line-height:1.6;'>" +
                "<h3>भारतीय स्टेट बैंक की ओर से शुभकामनाएं !</h3>" +
                "<h4>Greetings from SBI !</h4>" +
                "<div style='font-family: Arial, sans-serif; font-size: 14px; color: #222; line-height: 1.6;'>" +
                "<p>Dear " + FIRM_NAME + ",</p>" +
                "<p>We are pleased to inform you that your firm has been successfully <strong>empanelled</strong> with our bank for the role of <strong>{{ASSIGNMENT_TYPE}}</strong>.</p>" +
                "<p>Your empanelment reference details are as follows:</p>" +
                "<ul>" +
                "<li><strong>FRN No.:</strong> " + FRN_NO + "</li>" +
                "</ul>" +
                "<p>Please keep this information for your records. Further communication regarding specific assignments or mandates will be sent by the respective branches.</p>" +
                "<p style='color:gray;font-size:12px;'>" +
                "कृपया इस स्वतः उत्पन्न ईमेल का उत्तर न दें।<br/>" +
                "Please do not reply to this auto generated email." +
                "<p>Thank you,<br/>" +
                "<strong>Team CRS</strong></p>" +
                "</div>" +
                "</body>" +
                "</html>";
    }

}


Model:: 

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
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "IAM_EMAIL_SEQ")
    @SequenceGenerator(name = "IAM_EMAIL_SEQ" ,sequenceName ="IAM_EMAIL_SEQ",  allocationSize = 1 )
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


Console Output:: 
org.springframework.dao.DataIntegrityViolationException: could not execute statement [ORA-00001: unique constraint (FNSONLI.IAM_EMAIL_LOGS_PK) violated
] [insert into iam_email_logs (frn_email_data,email_date,email_remark,email_status,frn_email,frn_no,user_id,rml_id) values (?,?,?,?,?,?,?,?)]; SQL [insert into iam_email_logs (frn_email_data,email_date,email_remark,email_status,frn_email,frn_no,user_id,rml_id) values (?,?,?,?,?,?,?,?)]; constraint [FNSONLI.IAM_EMAIL_LOGS_PK]
	at org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException(HibernateJpaDialect.java:290)
	at org.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:241)
	at org.springframework.orm.jpa.JpaTransactionManager.doCommit(JpaTransactionManager.java:566)
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:795)
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:758)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:676)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:426)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:165)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:223)
	at jdk.proxy4/jdk.proxy4.$Proxy128.save(Unknown Source)
	at com.crs.iamservice.Service.EmailServiceImpl.logEmailStatus(EmailServiceImpl.java:183)
	at com.crs.iamservice.Service.EmailServiceImpl.sendEmailWithAttachment(EmailServiceImpl.java:107)
	at com.crs.iamservice.Service.PdfTemplateServiceImpl.lambda$generateAllPdf$0(PdfTemplateServiceImpl.java:195)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:572)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:317)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
	at java.base/java.lang.Thread.run(Thread.java:1570)
Caused by: org.hibernate.exception.ConstraintViolationException: could not execute statement [ORA-00001: unique constraint (FNSONLI.IAM_EMAIL_LOGS_PK) violated
] [insert into iam_email_logs (frn_email_data,email_date,email_remark,email_status,frn_email,frn_no,user_id,rml_id) values (?,?,?,?,?,?,?,?)]
	at org.hibernate.dialect.OracleDialect.lambda$buildSQLExceptionConversionDelegate$1(OracleDialect.java:1104)
	at org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:58)
	at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:108)
	at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:197)
	at org.hibernate.engine.jdbc.mutation.internal.AbstractMutationExecutor.performNonBatchedMutation(AbstractMutationExecutor.java:134)
	at org.hibernate.engine.jdbc.mutation.internal.MutationExecutorSingleNonBatched.performNonBatchedOperations(MutationExecutorSingleNonBatched.java:55)
	at org.hibernate.engine.jdbc.mutation.internal.AbstractMutationExecutor.execute(AbstractMutationExecutor.java:55)
	at org.hibernate.persister.entity.mutation.InsertCoordinatorStandard.doStaticInserts(InsertCoordinatorStandard.java:194)
	at org.hibernate.persister.entity.mutation.InsertCoordinatorStandard.coordinateInsert(InsertCoordinatorStandard.java:132)
	at org.hibernate.persister.entity.mutation.InsertCoordinatorStandard.insert(InsertCoordinatorStandard.java:104)
	at org.hibernate.action.internal.EntityInsertAction.execute(EntityInsertAction.java:110)
	at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:632)
	at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:499)
	at org.hibernate.event.internal.AbstractFlushingEventListener.performExecutions(AbstractFlushingEventListener.java:371)
	at org.hibernate.event.internal.DefaultFlushEventListener.onFlush(DefaultFlushEventListener.java:41)
	at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:127)
	at org.hibernate.internal.SessionImpl.doFlush(SessionImpl.java:1425)
	at org.hibernate.internal.SessionImpl.managedFlush(SessionImpl.java:487)
	at org.hibernate.internal.SessionImpl.flushBeforeTransactionCompletion(SessionImpl.java:2324)
	at org.hibernate.internal.SessionImpl.beforeTransactionCompletion(SessionImpl.java:1981)
	at org.hibernate.engine.jdbc.internal.JdbcCoordinatorImpl.beforeTransactionCompletion(JdbcCoordinatorImpl.java:439)
	at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcResourceLocalTransactionCoordinatorImpl.beforeCompletionCallback(JdbcResourceLocalTransactionCoordinatorImpl.java:169)
	at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcResourceLocalTransactionCoordinatorImpl$TransactionDriverControlImpl.commit(JdbcResourceLocalTransactionCoordinatorImpl.java:267)
	at org.hibernate.engine.transaction.internal.TransactionImpl.commit(TransactionImpl.java:101)
	at org.springframework.orm.jpa.JpaTransactionManager.doCommit(JpaTransactionManager.java:562)
	... 22 common frames omitted
Caused by: java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique constraint (FNSONLI.IAM_EMAIL_LOGS_PK) violated

	at oracle.jdbc.driver.T4CTTIoer11.processError(T4CTTIoer11.java:629)
	at oracle.jdbc.driver.T4CTTIoer11.processError(T4CTTIoer11.java:563)
	at oracle.jdbc.driver.T4C8Oall.processError(T4C8Oall.java:1230)
	at oracle.jdbc.driver.T4CTTIfun.receive(T4CTTIfun.java:771)
	at oracle.jdbc.driver.T4CTTIfun.doRPC(T4CTTIfun.java:298)
	at oracle.jdbc.driver.T4C8Oall.doOALL(T4C8Oall.java:511)
	at oracle.jdbc.driver.T4CPreparedStatement.doOall8(T4CPreparedStatement.java:162)
	at oracle.jdbc.driver.T4CPreparedStatement.executeForRows(T4CPreparedStatement.java:1240)
	at oracle.jdbc.driver.OracleStatement.executeSQLStatement(OracleStatement.java:1819)
	at oracle.jdbc.driver.OracleStatement.doExecuteWithTimeout(OracleStatement.java:1471)
	at oracle.jdbc.driver.OraclePreparedStatement.executeInternal(OraclePreparedStatement.java:3760)
	at oracle.jdbc.driver.OraclePreparedStatement.executeLargeUpdate(OraclePreparedStatement.java:4061)
	at oracle.jdbc.driver.OraclePreparedStatement.executeUpdate(OraclePreparedStatement.java:4036)
	at oracle.jdbc.driver.OraclePreparedStatementWrapper.executeUpdate(OraclePreparedStatementWrapper.java:995)
	at com.zaxxer.hikari.pool.ProxyPreparedStatement.executeUpdate(ProxyPreparedStatement.java:61)
	at com.zaxxer.hikari.pool.HikariProxyPreparedStatement.executeUpdate(HikariProxyPreparedStatement.java)
	at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:194)
	... 43 common frames omitted
Caused by: oracle.jdbc.OracleDatabaseException: ORA-00001: unique constraint (FNSONLI.IAM_EMAIL_LOGS_PK) violated

	at oracle.jdbc.driver.T4CTTIoer11.processError(T4CTTIoer11.java:636)
	... 59 common frames omitted
