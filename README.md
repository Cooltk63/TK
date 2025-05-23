Vulnerability
Weak Encryption: Inadequate RSA Padding

Vulnerability Description in Detail
The method decryptKeyFile() in DecryptAscii.java performs public key RSA encryption without OAEP padding, which makes the encryption weak.

Likely Impact
The method doEncrypt() in RSA.java performs public key RSA encryption without OAEP padding, which makes the encryption weak.

Recommendation
To use RSA securely, you must use OAEP (Optimal Asymmetric Encryption Padding), using SHA-2 hash, when performing encryption.

Code Imapcted :
package com.tcs.scheduler;


import com.tcs.services.BranchManagementService;
import com.tcs.services.UploadAsciiService;
import com.tcs.services.UploadService2;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
@Component
public class DecryptAscii {

    @Autowired
    UploadAsciiService UploadAsciiService;
    
    // Encryption Algorithm
    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    static Logger log = Logger.getLogger(DecryptAscii.class.getName());
    public void decryptAsciiFile(String  qDate,String fileType,String sessionDate) throws Exception {
//        log.info("into decryptAsciiFile- "+qDate+" "+fileType+" "+sessionDate);
        String fileDate2 =sessionDate;  ////////20230930
        String fileName="";
        String fileKeyName="";
        String decFileName="";
        String folder = "";
        String unZipType = "";
        PropertiesConfiguration config = new PropertiesConfiguration("common.properties");
        if (fileType.equalsIgnoreCase("BS_BRANCH")) {
            fileName = "/FIN_ENTYLIST_" + fileDate2 + "_txt.enc";
            fileKeyName = "/FIN_ENTYLIST_" + fileDate2 + ".key";
            decFileName = "/FIN_ENTYLIST_" + fileDate2 + ".txt";
        }
        if (fileType.equalsIgnoreCase("BS_PNL")) {
            folder = "/PNL/";
            unZipType="PL";
            fileName = "/PL_" + fileDate2 + "_zip.enc";
            fileKeyName = "/PL_" + fileDate2 + ".key";
            decFileName= "/PL_" + fileDate2 + ".zip";
        } else if (fileType.equalsIgnoreCase("BS_YSA")) {
            folder = "/YSA/";
            unZipType="YSA";
            fileName = "/YSA_" + fileDate2 + "_zip.enc";
            fileKeyName = "/YSA_" + fileDate2 + ".key";
            decFileName= "/YSA_" + fileDate2 + ".zip";
        } else if (fileType.equalsIgnoreCase("BS_TBP")) {
            folder = "/TBP/";
            unZipType="TBP";
            fileName = "/TBP_" + fileDate2 + "_zip.enc";
            fileKeyName = "/TBP_" + fileDate2 + ".key";
            decFileName= "/TBP_" + fileDate2 + ".zip";
        } else if (fileType.equalsIgnoreCase("BS_TBY")) {
            unZipType="TBY";
            folder = "/TBY/";
            fileName = "/TBY_" + fileDate2 + "_zip.enc";
            fileKeyName = "/TBY_" + fileDate2 + ".key";
            decFileName= "/TBY_" + fileDate2 + ".zip";
        }else if (fileType.equalsIgnoreCase("BS_PROFIT")) {
            unZipType="PCF";
            folder = "/PCF/";
            fileName = "/PCF_" + fileDate2 + "_zip.enc";
            fileKeyName = "/PCF_" + fileDate2 + ".key";
            decFileName= "/PCF_" + fileDate2 + ".zip";
        }else if (fileType.equalsIgnoreCase("BS_FILECOUNT")) {
            fileName = "/FILE_LINE_COUNT_" + fileDate2 + "_txt.enc";
            fileKeyName = "/FILE_LINE_COUNT_" + fileDate2 + ".key";
            decFileName = "/FILE_LINE_COUNT_" + fileDate2 + ".txt";
        }
        //Path path = Paths.get("D:\\media\\BS\\ASCII\\30092023\\FIN_ENTYLIST_20230930.key");
        Path path = Paths.get(config.getProperty("ReportDirASCII").toString()+qDate+fileKeyName);
//        log.info("path-"+ path);
        byte[] data = Files.readAllBytes(path);

        //PrivateKey tcsPrivateKey = getPrivateKey(config.getProperty("ReportDirASCII").toString()+"privateKey/PrivateKeyNew.der");
        PrivateKey tcsPrivateKey = getPrivateKey(config.getProperty("ReportDirASCII").toString()+"privateKey/PrivateKeyNew.der");
        //PrivateKey tcsPrivateKey = getPrivateKey("D:\\media\\BS\\ASCII\\30092023\\PrivateKeyNew.der");
        byte[] dec = decryptKeyFile(tcsPrivateKey, data);


        String str = new String(dec, StandardCharsets.UTF_8); // for UTF-8 encoding
        String EncryptFileHash = str.split("\\|\\|")[1];

        log.info("myField   : " + str);
        log.info("EncryptFileHash : " + EncryptFileHash);


        byte[] cipher = new byte[16];
        ByteBuffer bb = ByteBuffer.wrap(dec);
        bb.get(cipher, 0, cipher.length);

            // Extracting IV
        byte[] byteArrCopyNew  = Arrays.copyOfRange(dec, 64, dec.length);

        decryptFile(config.getProperty("ReportDirASCII").toString()+qDate+fileName,
                config.getProperty("ReportDirASCII").toString()+qDate+decFileName, cipher, byteArrCopyNew);
        log.info("after decryptFilev fn ");
        String decryptFileHash = getFileHash(config.getProperty("ReportDirASCII").toString()+qDate+decFileName);
        log.info("Input (Hex) - decryptFileHash - : " + decryptFileHash);

        if(EncryptFileHash.equals(decryptFileHash)) {
           log.info("Hash Match");
        } else {
            log.info("Hash Does Not Match");
        }
        if((!(fileType.equalsIgnoreCase("BS_BRANCH" ))) && (!(fileType.equalsIgnoreCase("BS_FILECOUNT"))) ) {
            log.info("intooooooo iffffffffffffffffff****************************");
            UploadAsciiService.unzipAsciiFile(folder, fileDate2, qDate, decFileName, unZipType, fileName, fileKeyName);
        }
        log.info("after unzip ascii files");
    }

    public static PrivateKey getPrivateKey(String priFilename) throws InvalidKeySpecException, InvalidKeySpecException {

        byte[] keyBytes = new byte[0];
        try {
            keyBytes = Files.readAllBytes(Paths.get(priFilename));
        } catch (IOException e) {
            log.info("Error");
        }

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = null;
        try {
            kf = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            log.info("No Such Algorithm Exception Occurred");
        }
        return kf.generatePrivate(spec);
    }

    private static void decryptFile(
            String encryptedFile,
            String decryptedFile,
            byte[] key,
            byte[] iv
    ) throws Exception {
//        log.info("encryptedFile--"+encryptedFile);
//        log.info("decryptedFile--"+decryptedFile);

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        log.info("after secret key spec");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, parameterSpec);
//        log.info("after cipher init--"+ encryptedFile );
        byte[] cText = Files.readAllBytes(Paths.get(encryptedFile));
        byte[] plainText = cipher.doFinal(cText);
        log.info("after plaintext");
        try (OutputStream outputStream = new FileOutputStream(decryptedFile)) {
            outputStream.write(plainText);
        }
    }

    public static byte[] decryptKeyFile(PrivateKey key, byte[] ciphertext)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(ciphertext);
    }

    private static String getFileHash(String filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
            log.info("into get file hash");
        try (InputStream inputStream = new FileInputStream(filePath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        }

        byte[] hash = md.digest();
        log.info("last line of  get file hash");
        return Base64.getEncoder().encodeToString(hash);
    }

}


Solve this issue without affecting existing working functionality
