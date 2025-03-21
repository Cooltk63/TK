package com.crs.externalApiService.util;

//import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
//import org.json.JSONObject;
import javax.crypto.*;
//import javax.crypto.spec.GCMParameterSpec;
//import javax.crypto.spec.OAEPParameterSpec;
//import javax.crypto.spec.PSource;
import java.io.*;
//import java.nio.ByteBuffer;
import java.nio.file.Files;
//import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
//import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
//import org.apache.commons.codec.binary.Base64;
import java.util.logging.Logger;
//import java.security.interfaces.RSAPrivateKey;

@Slf4j
public class ClientUtility {


    //static { Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); }


    public static PublicKey getPublicKey(String pubFilename) {
        log.info("Inside Getting getPublicKey for Path: " + pubFilename);
        ///media/CRS/SSO_KEYS/CLIENT_KEYS/ENC_EIS.cer
        CertificateFactory cf = null;
        PublicKey publicKey = null;
        try {
            log.info("Extracting the Public Key from Certificate File");
            cf = CertificateFactory.getInstance("X.509");
            //***********TK CHANGE Here Added the parameter passed HRMS Key Path Instead of hardCode
//            FileInputStream fis = new FileInputStream("D:\\media\\CRS\\SSO_KEYS\\CLIENT_KEYS\\ENC_EIS_UAT.cer");
            FileInputStream fis = new FileInputStream(pubFilename);
            X509Certificate cert = (X509Certificate) cf.generateCertificate(fis);
            publicKey = cert.getPublicKey();
        } catch (FileNotFoundException e) {
            log.info("File not Found Exception Occurred " + e.getMessage());
        } catch (NullPointerException e) {
            log.info(" Null Pointer Exception Occurred ");
        } catch (RuntimeException e) {
            log.info(" Run Time Exception Occurred ");
        } catch (CertificateException e) {
            log.info(" CertificateException Exception Occurred " + e.getMessage());
        } catch (Exception e) {
            log.info("Exception Occurred " + e.getMessage());
        }

        return publicKey;
    }

    @SuppressWarnings("deprecation")
    public static String signSenderTokenHash(PrivateKey privateKey, String senderTokenHash) {
        byte[] signature = new byte[0];

        try {
            Signature privateSignature = Signature.getInstance("SHA256withRSA");

            privateSignature.initSign(privateKey);

            privateSignature.update(senderTokenHash.getBytes("UTF-8"));

            signature = privateSignature.sign();
        }
        catch (NoSuchAlgorithmException e) {
            log.info(" Invalid Key Exception Occurred ");
        } catch (InvalidKeyException e) {
            log.info(" Invalid Key Exception Occurred ");
        } catch (SignatureException e) {
            log.info(" Signature Exception Occurred ");
        } catch (UnsupportedEncodingException e) {
            log.info(" Unsupported Encoding Exception Occurred ");
        }
        return Base64.encodeBase64String(signature);

    }

 


    public static PrivateKey getPrivateKey(String filePath)  {
        try {
            byte[] keyBytes = Files.readAllBytes(Paths.get(filePath));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA"); // No Bouncy Castle needed
            return keyFactory.generatePrivate(keySpec);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }



    public static SecretKey generateAESkey() {
        // --- generate a new AES secret key ---
        KeyGenerator aesKeyGenerator = null;
        try {
            aesKeyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            log.info("NoSuch Algorithm Exception Occurred");
        } catch (RuntimeException e) {
            log.info("Runtime doesn't have support for AES key generator (mandatory algorithm for runtimes)");
        }
        aesKeyGenerator.init(256);
        SecretKey aesKey = aesKeyGenerator.generateKey();
        return aesKey;
    }

    /*
    @Author V1012981
    Encrytpting aes key
     */
    public static String encryptAesKey(String algorithm, String input, PublicKey key) {
        byte[] mestobyte = input.getBytes();
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedText = cipher.doFinal(mestobyte);
            return java.util.Base64.getEncoder().encodeToString(encryptedText);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

}



This is the class i had 


below is the same error I am getting even after your provided soluiton

at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:896)
        at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1736)
        at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
        at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
        at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)
        at java.base/java.lang.Thread.run(Thread.java:1583)
Caused by: java.io.FileNotFoundException: /media/CRS/created/ANX_IX_COL.pdf (Permission denied)
        at java.base/java.io.FileOutputStream.open0(Native Method)
        at java.base/java.io.FileOutputStream.open(FileOutputStream.java:289)
        at java.base/java.io.FileOutputStream.<init>(FileOutputStream.java:230)
        at java.base/java.io.FileOutputStream.<init>(FileOutputStream.java:179)
        at net.sf.jasperreports.export.SimpleOutputStreamExporterOutput.<init>(SimpleOutputStreamExporterOutput.java:66)
        ... 64 common frames omitted
2025-03-21 :: 14:07:36.817 || INFO :: CommonServiceImpl.java: | 1239 | ::  circleCode :: 001 qed :: 31/03/2025 jrxmlName :: ANX_IX_COL
2025-03-21 :: 14:07:36.818 || INFO :: CommonServiceImpl.java: | 1243 | ::  jrxmlName: ANX_IX_COL
2025-03-21 :: 14:07:36.819 || INFO :: CommonServiceImpl.java: | 1251 | ::  jasperFilePath: /media/tomcat/apache-tomcat-10.1.23/webapps/commonService/WEB-INF/classes/concoljasper/ANX_IX_COL.jasper
2025-03-21 :: 14:07:36.866 || INFO :: CommonServiceImpl.java: | 1268 | ::  Connection closed
2025-03-21 :: 14:07:36.867 || ERROR:: ErrorPageFilter.java: | 182 | ::  Forwarding to error page from request [/CommonService/downloadCircleOrColReports] due to exception [java.io.FileNotFoundException: /media/CRS/created/ANX_IX_COL.pdf (Permission denied)]
net.sf.jasperreports.engine.JRRuntimeException: java.io.FileNotFoundException: /media/CRS/created/ANX_IX_COL.pdf (Permission denied)
        at net.sf.jasperreports.export.SimpleOutputStreamExporterOutput.<init>(SimpleOutputStreamExporterOutput.java:70)
        at net.sf.jasperreports.export.SimpleOutputStreamExporterOutput.<init>(SimpleOutputStreamExporterOutput.java:83)
        at net.sf.jasperreports.engine.JasperExportManager.exportToPdfFile(JasperExportManager.java:153)
        at net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfFile(JasperExportManager.java:503)
        at com.crs.commonService.services.CommonServiceImpl.downloadCircleOrColReports(CommonServiceImpl.java:1259)
        at com.crs.commonService.controllers.CommonServiceController.downloadCircleOrColReports(CommonServiceController.java:207)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
        at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:255)
        at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:188)
        at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118)
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:926)
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:831)
        at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
        at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089)
        at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)
        at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)
        at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:914)
        at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)
        at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:196)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
