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

csPrivateKeyPath : /media/CRS/SSO_HRMS_KEYS/PrivateKey.der
21-Mar-2025 16:07:50.450 INFO [http-nio-7002-exec-237] org.apache.catalina.loader.WebappClassLoaderBase.checkStateForResourceLoading Illegal access: this web application instance has been stopped already. Could not load [org.bouncycastle.jcajce.provider.asymmetric.rsa.KeyFactorySpi]. The following stack trace is thrown for debugging purposes as well as to attempt to terminate the thread which caused the illegal access.
        java.lang.IllegalStateException: Illegal access: this web application instance has been stopped already. Could not load [org.bouncycastle.jcajce.provider.asymmetric.rsa.KeyFactorySpi]. The following stack trace is thrown for debugging purposes as well as to attempt to terminate the thread which caused the illegal access.
                at org.apache.catalina.loader.WebappClassLoaderBase.checkStateForResourceLoading(WebappClassLoaderBase.java:1373)
                at org.apache.catalina.loader.WebappClassLoaderBase.checkStateForClassLoading(WebappClassLoaderBase.java:1361)
                at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1198)
                at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1165)
                at java.base/java.security.Provider$Service.getImplClass(Provider.java:1991)
                at java.base/java.security.Provider$Service.getDefaultConstructor(Provider.java:2020)
                at java.base/java.security.Provider$Service.newInstanceOf(Provider.java:1934)
                at java.base/java.security.Provider$Service.newInstanceUtil(Provider.java:1942)
                at java.base/java.security.Provider$Service.newInstance(Provider.java:1917)
                at java.base/java.security.KeyFactory.nextSpi(KeyFactory.java:315)
                at java.base/java.security.KeyFactory.generatePrivate(KeyFactory.java:394)
                at com.crs.externalApiService.util.ClientUtility.getPrivateKey(ClientUtility.java:122)
                at com.crs.externalApiService.services.HrmsServceImpl.generatingDigitalSignature(HrmsServceImpl.java:199)
                at com.crs.externalApiService.services.HrmsServceImpl.getHrmsUserDetails(HrmsServceImpl.java:101)
                at com.crs.externalApiService.controllers.HrmsController.getUser(HrmsController.java:35)
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
                at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)
                at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:165)
                at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
                at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
                at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
                at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:165)
                at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
                at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
                at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
                at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:165)
                at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
                at org.springframework.boot.web.servlet.support.ErrorPageFilter.doFilter(ErrorPageFilter.java:124)
                at org.springframework.boot.web.servlet.support.ErrorPageFilter$1.doFilterInternal(ErrorPageFilter.java:99)
                at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
                at org.springframework.boot.web.servlet.support.ErrorPageFilter.doFilter(ErrorPageFilter.java:117)
                at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:165)
                at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
                at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
                at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
                at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:165)
                at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
                at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
                at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
                at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:482)
                at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115)
                at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
                at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:673)
                at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
                at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344)
                at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:391)
                at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
                at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:896)
                at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1736)
                at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
                at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
                at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
                at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)
                at java.base/java.lang.Thread.run(Thread.java:1583)
