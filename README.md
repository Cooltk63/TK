package com.crs.externalApiService.services;

import com.crs.externalApiService.models.CrsSetting;
import com.crs.externalApiService.repositories.HrmsDao;
import com.crs.externalApiService.util.AesGcm128;
//import com.crs.externalApiService.services.HrmsService;
import com.crs.externalApiService.util.ClientUtility;
import com.crs.externalApiService.util.CommonFunction;
import com.crs.externalApiService.util.SSOCommonConstants;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service("HrmsService")
public class HrmsServceImpl implements HrmsService {

    private static final Logger log = Logger.getLogger(HrmsServceImpl.class.getName());

    private static final String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    
    final HrmsDao hrmsDao;

    public HrmsServceImpl(  HrmsDao hrmsDao) { 
        this.hrmsDao = hrmsDao;
    }

    /*
     * Method to get user details from HRMS
     * @Author: V1012981
     * @LastEditor: V101939
     */
    @Override
    public Map<String, Object> getHrmsUserDetails(String pfId) {


        testBounceCastle();

        try {
            // STEP 1
            // Generate AES (Advanced Encryption Standard) Secret Key for Encryption & Decryption
            AesGcm128 aes = new AesGcm128(SSOCommonConstants.AES_KEY_SIZE, SSOCommonConstants.AES_256_GCM_ALGORITHM);
            String aesKey = aes.generateKey();
            log.info("AES KEY : " + aesKey);

            // STEP 2
            // Generating REQUEST_REFERENCE_NUMBER
            String requestRefNum = requestRefNum();
            log.info("RequestRefNum : " + requestRefNum);

            // STEP 3
            // Generating REQUEST Payload
            JSONObject payloadJSON = new JSONObject();
            payloadJSON.put("PF_NUMBER", pfId);
            payloadJSON.put("SOURCE_ID", SSOCommonConstants.HRMS_SOURCE_ID);

            String payloadData = payloadJSON.toString();
            log.info("payloadData : " + payloadData);

            // STEP 4
            // Encrypt the plain JSON request with AES GCM 128 Key and IV
            String encryptedPayloadData = aes.encrypt(aesKey, payloadData);
            log.info("encryptedPayloadData : " + encryptedPayloadData);

            // STEP 5
            // Generating Digital Signature (DIGI_SIGN)
            String signedSenderTokenHash = generatingDigitalSignature(payloadData);
            log.info("signedSenderTokenHash : " + signedSenderTokenHash);

            // STEP 6
            // Generating final request
            JSONObject obj = new JSONObject();
            obj.put("REQUEST_REFERENCE_NUMBER", requestRefNum);
            obj.put("DIGI_SIGN", signedSenderTokenHash);
            obj.put("REQUEST", encryptedPayloadData);

            String finalRequest = obj.toString();
            log.info("finalRequest : " + finalRequest);

            // STEP 6
            // Generating ACCESS TOKEN
            String accessToken = generateAccessToken(aesKey);
            log.info("AccessToken : " + accessToken);

            // STEP 7
            // HRMS API Calling
            return hrmsApiCall(finalRequest, accessToken, aes);

        } catch (NoSuchAlgorithmException e) {
            log.info("Exception NoSuchAlgorithmException : " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            log.info("Exception NoSuchPaddingException : " + e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            log.info("Exception InvalidAlgorithmParameterException : " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            log.info("Exception IllegalBlockSizeException : " + e.getMessage());
        } catch (BadPaddingException e) {
            log.info("Exception BadPaddingException : " + e.getMessage());
        } catch (InvalidKeyException e) {
            log.info("Exception InvalidKeyException : " + e.getMessage());
        }

        return null;
    }


    public void testBounceCastle() {
        // Add Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
        System.out.println("Bouncy Castle Provider added successfully.");

        try {
            // Create a KeyPairGenerator for RSA
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
            keyPairGenerator.initialize(2048, new SecureRandom());

            // Generate the key pair
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Print the keys
            System.out.println("Public Key: " + publicKey);
            System.out.println("Private Key: " + privateKey);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algorithm not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    
    public static String requestRefNum() {
        /*
        REQUEST_REFERENCE_NUMBER * string
        maxLength: 25
        Unique Reference Number format SBIXXYYDDDhhMMssSSSnnnnnn.
        First 3 alphabets will always be SBI,
        XX will signify Channel Identifier (eg: LT for YONO channel),
        YYDDD will signify the Julian date (eg: 26-02-2020 will be represented as 20057),
        hhMMssSSS will signify the current time in hours, minutes, second and milisecond,
        nnnnnn will signify running sequence number.
        */
        // SBI AJ 23254 05584087d cf8ebf
        return "SBI" + SSOCommonConstants.HRMS_SOURCE_ID + calculateJulianDateTime() + generateAlphaNumericSequence();
    }

    public static String generatingDigitalSignature(String payloadData) {
        try {


            for (Provider provider : Security.getProviders()) {
                System.out.println("===========================================================" + provider.getName());
                provider.getServices().forEach(service -> System.out.println("  " + service.getAlgorithm()));
            }


            log.info("generatingDigitalSignature Begin: "); 
            // Exception Message : java.security.InvalidKeyException: IOException : algid parse error, not a sequence
            java.security.Security.addProvider(
                    new org.bouncycastle.jce.provider.BouncyCastleProvider()
            );
            log.info("generatingDigitalSignature End: ");


            for (Provider provider : Security.getProviders()) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + provider.getName());
                provider.getServices().forEach(service -> System.out.println("  " + service.getAlgorithm()));
            }


            //String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
            //log.info("rootPath : " + rootPath);

            // SHA256-RSA algorithm has to be used to sign the plain request payload with the help of sender’s Private Key.
            //String tcsPrivateKeyPath = SSOCommonConstants.TCS_PRIVATE_KEY;   // "/media/CRS/SSO_HRMS_KEYS/PrivateKey.der";
            String tcsPrivateKeyPath = rootPath + "SSO_HRMS_KEYS/PrivateKey.der"; 
            log.info("tcsPrivateKeyPath : " + tcsPrivateKeyPath); 

            //String m3 = Thread.currentThread().getContextClassLoader().getResource("SSO_HRMS_KEYS/PrivateKey.der").getPath();
            //log.info("m3 : " + m3);
           // File file = new ClassPathResource("SSO_HRMS_KEYS/PrivateKey.der").getFile();
            //log.info("m2 : " + file.getAbsolutePath());
            

            PrivateKey tcsPrivateKey = ClientUtility.getPrivateKey(tcsPrivateKeyPath);

            return ClientUtility.signSenderTokenHash(tcsPrivateKey, payloadData);
        } catch (InvalidKeySpecException e) {
            log.info("GeneratingDigitalSignature Exception Message : " + e.getMessage());
            log.info("GeneratingDigitalSignature Exception Cause : " + e.getCause());
        } catch (Exception ex) {
            log.info("Exception >>>>> " + ex.toString());
        }
        return null;
    }

    public static String generateAccessToken(String aesKey) {

        // Getting HRMS public key server path
        //String ccdpPublicKeyPath = SSOCommonConstants.HRMS_PUBLIC_KEY;
        String ccdpPublicKeyPath = rootPath + "SSO_KEYS/CLIENT_KEYS/ENC_EIS.cer";

        // Getting HRMS public key to encrypt data using their public key
        PublicKey ccdpServerPublicKey = ClientUtility.getPublicKey(ccdpPublicKeyPath);

        // RSA algorithm must be used to encrypt the above AES 256 encryption key and IV with the help of receiver's Public Key.
        return ClientUtility.encryptAesKey(SSOCommonConstants.RSA_ALGORITHM, aesKey, ccdpServerPublicKey);
    }

    public Map<String, Object> hrmsApiCall(String finalRequest, String accessToken, AesGcm128 aes) {
        URL urlObj = null;
        HttpURLConnection connection = null;
        int responseCode = 0;


        String env = CommonFunction.getPreIpString();
        log.info("env ::::::"+env);

        if (env.equalsIgnoreCase("LR") || env.equalsIgnoreCase("DEV")) {

            // {"RESPONSE_STATUS":"0","ERROR_CODE":"","ERROR_DESCRIPTION":"","PF_NUMBER":"01015402","FIRST_NAME":"MAYANK","MIDDLE_NAME":"","LAST_NAME":"MISHRA","BRANCH_CODE":"04430","MOBILE_NUMBER":"9407869300","EMPLOYEE_STATUS":"Active","EMAIL":""}
            // {EMPLOYEE_STATUS=Active, MOBILE_NUMBER=9407869300, MIDDLE_NAME=, BRANCH_CODE=04430, LAST_NAME=MISHRA, EMAIL=, FIRST_NAME=MAYANK}
            Map<String, Object> defualtUserObj = new HashMap<>();
            defualtUserObj.put("FIRST_NAME", "CRS User");
            defualtUserObj.put("MIDDLE_NAME", "MN");
            defualtUserObj.put("LAST_NAME", "LN");
            defualtUserObj.put("BRANCH_CODE", "04430");
            defualtUserObj.put("MOBILE_NUMBER", "9407869300");
            defualtUserObj.put("EMPLOYEE_STATUS", "Active");
            defualtUserObj.put("EMAIL", "");
            defualtUserObj.put("STATUS", "A");
            return defualtUserObj;
        }

        try {
            // Getting HRMS API
            CrsSetting crsSetting = hrmsDao.getUrl("HRMS","DEV");
            log.info("111 " + crsSetting.getUsername());
            log.info("222 " + crsSetting.getPassword());
            log.info("333 " + crsSetting.getEnv());
            log.info("444 " + crsSetting.getUrl());

            urlObj = new URL(crsSetting.getUrl());
            
            // Open a connection(?) on the URL(??) and cast the response(???)
            connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("POST");

            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Add a parameter to the HTTP header
            connection.setRequestProperty("AccessToken", accessToken);

            // For POST only - START
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = finalRequest.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            // For POST only - END

            log.info("Connection get content length  : " + connection.getContentLength());
            responseCode = connection.getResponseCode();
            log.info("ResponseCode : " + responseCode);
            log.info("ResponseMessage : " + connection.getResponseMessage());

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                // This line makes the request
                /*try {*/
                    InputStream responseStream = connection.getInputStream();

                    BufferedReader in = new BufferedReader(new InputStreamReader(responseStream), 100);
                    String inputLine;
                    StringBuffer response = new StringBuffer(100);

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    log.info("ResponseMessage : " + response.toString());

                    JSONObject json = new JSONObject(response.toString());
                    String REQUEST_REFERENCE_NUMBER = json.getString("REQUEST_REFERENCE_NUMBER");
                    String RESPONSE = json.getString("RESPONSE");
                    String RESPONSE_DATE = json.getString("RESPONSE_DATE");
                    String DIGI_SIGN = json.getString("DIGI_SIGN");

                    log.info("REQUEST_REFERENCE_NUMBER : " + REQUEST_REFERENCE_NUMBER);
                    log.info("RESPONSE : " + RESPONSE);
                    log.info("RESPONSE_DATE : " + RESPONSE_DATE);
                    log.info("DIGI_SIGN : " + DIGI_SIGN);

                    String responseDecrypted = aes.decrypt(aes.generateKey(), RESPONSE);
                    log.info("responseDecrypted :" + responseDecrypted);

                JSONObject jsonResponseDecrypted = new JSONObject(responseDecrypted.toString());
                String RESPONSE_STATUS = jsonResponseDecrypted.getString("RESPONSE_STATUS");
                String ERROR_CODE = jsonResponseDecrypted.getString("ERROR_CODE");
                String ERROR_DESCRIPTION = jsonResponseDecrypted.getString("ERROR_DESCRIPTION");
                Map<String, Object> hrmsUserObj = new HashMap<>();
                /**
                 * The below line is just to send 9 as the error code as we didn't get any response from the HRMS.
                 * @author v1009204
                 */
                hrmsUserObj.put("STATUS", "9");

                if (
                        RESPONSE_STATUS.equalsIgnoreCase("0")
                                && ERROR_CODE.equalsIgnoreCase("")
                ) {

                    hrmsUserObj.put("FIRST_NAME", jsonResponseDecrypted.getString("FIRST_NAME"));
                    hrmsUserObj.put("MIDDLE_NAME", jsonResponseDecrypted.getString("MIDDLE_NAME"));
                    hrmsUserObj.put("LAST_NAME", jsonResponseDecrypted.getString("LAST_NAME"));
                    hrmsUserObj.put("BRANCH_CODE", jsonResponseDecrypted.getString("BRANCH_CODE"));
                    hrmsUserObj.put("MOBILE_NUMBER", jsonResponseDecrypted.getString("MOBILE_NUMBER"));
                    hrmsUserObj.put("EMPLOYEE_STATUS", jsonResponseDecrypted.getString("EMPLOYEE_STATUS"));
                    hrmsUserObj.put("EMAIL", jsonResponseDecrypted.getString("EMAIL"));
                    hrmsUserObj.put("STATUS", RESPONSE_STATUS);

                    log.info("User json object >... " + hrmsUserObj);


                } else if (RESPONSE_STATUS.equalsIgnoreCase("1") && ERROR_CODE.equalsIgnoreCase("SI531")) {
                    hrmsUserObj.put("ERROR_CODE", ERROR_DESCRIPTION.split("|")[1]);
                    hrmsUserObj.put("STATUS", RESPONSE_STATUS);

                }
                return hrmsUserObj;
             /*   } catch (Exception e) {
                    log.info("Exception : " + e.getMessage());
                }*/
            }
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException e) {
            log.info("Exception :" +e.getMessage());
        }
        return Collections.emptyMap();
    }
    public static String calculateJulianDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyDDDhhmmssSSS");
        return df.format(new Date());
    }
    public static String generateAlphaNumericSequence() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(6);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
    }
}

    

   

    
