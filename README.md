import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Arrays;

public class AESDecryption {

    public static void main(String[] args) {
        try {
            // 🔹 Load Encrypted File (Read as raw bytes, NOT Base64)
            byte[] encryptedData = Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Encrypted"));
            System.out.println("Encrypted Data length: " + encryptedData.length);

            // 🔹 Extract IV (First 16 bytes)
            byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);
            System.out.println("IV length: " + iv.length);

            // 🔹 Extract Actual Ciphertext (After IV)
            byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);
            System.out.println("CipherText Length: " + cipherText.length);

            // 🔹 Load Key File (Base64 encoded key)
            byte[] keyBytes = Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Dynamic_Key.key"));
            String keyBase64 = new String(keyBytes);
            System.out.println("Raw Key File (Base64): " + keyBase64);

            // 🔹 Decode Base64 key
            byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
            System.out.println("Decoded Key Length: " + decodedKey.length);

            // 🔹 Ensure Valid AES Key Length (16, 24, or 32 bytes)
            byte[] finalKey;
            if (decodedKey.length == 16 || decodedKey.length == 24 || decodedKey.length == 32) {
                finalKey = decodedKey; // Key is already valid
                System.out.println("finalKey Length: " + finalKey.length);
            } else {
                // If key length is invalid, adjust it
                finalKey = new byte[16]; // Default to AES-128 (16 bytes)
                System.arraycopy(decodedKey, 0, finalKey, 0, Math.min(decodedKey.length, finalKey.length));
                System.out.println("finalKey Length: " + finalKey.length);
            }


            decrypt(encryptedData, iv);
            // 🔹 Setup AES Decryption (AES/CBC/PKCS5Padding)
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(finalKey, "AES");


            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            decrypt(encryptedData,iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            // 🔹 Decrypt Data
            byte[] decryptedData = cipher.doFinal(cipherText);

            // 🔹 Print or Save Decrypted Output
            System.out.println("Decrypted Data:\n" + new String(decryptedData, "UTF-8"));

        } catch (Exception e) {
            System.err.println("Decryption Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static byte[] decrypt(byte[] cipherTextData, byte[] secretKey) throws Exception {
        try {
            String iv = new String(secretKey).substring(0, 16);

            byte[] encrypted = cipherTextData;

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec keyspec = new SecretKeySpec(secretKey, "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original);
            return originalString.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


This is the error we getting

D:\jdk1.8.0_351\bin\java.exe "-javaagent:E:\Tushar Khade\Softwares\2025-IDE\IntelliSD\lib\idea_rt.jar=62588" -Dfile.encoding=UTF-8 -classpath "D:\jdk1.8.0_351\jre\lib\charsets.jar;D:\jdk1.8.0_351\jre\lib\deploy.jar;D:\jdk1.8.0_351\jre\lib\ext\access-bridge-64.jar;D:\jdk1.8.0_351\jre\lib\ext\cldrdata.jar;D:\jdk1.8.0_351\jre\lib\ext\dnsns.jar;D:\jdk1.8.0_351\jre\lib\ext\jaccess.jar;D:\jdk1.8.0_351\jre\lib\ext\jfxrt.jar;D:\jdk1.8.0_351\jre\lib\ext\localedata.jar;D:\jdk1.8.0_351\jre\lib\ext\nashorn.jar;D:\jdk1.8.0_351\jre\lib\ext\sunec.jar;D:\jdk1.8.0_351\jre\lib\ext\sunjce_provider.jar;D:\jdk1.8.0_351\jre\lib\ext\sunmscapi.jar;D:\jdk1.8.0_351\jre\lib\ext\sunpkcs11.jar;D:\jdk1.8.0_351\jre\lib\ext\zipfs.jar;D:\jdk1.8.0_351\jre\lib\javaws.jar;D:\jdk1.8.0_351\jre\lib\jce.jar;D:\jdk1.8.0_351\jre\lib\jfr.jar;D:\jdk1.8.0_351\jre\lib\jfxswt.jar;D:\jdk1.8.0_351\jre\lib\jsse.jar;D:\jdk1.8.0_351\jre\lib\management-agent.jar;D:\jdk1.8.0_351\jre\lib\plugin.jar;D:\jdk1.8.0_351\jre\lib\resources.jar;D:\jdk1.8.0_351\jre\lib\rt.jar;F:\Projects\CRS Projects\CRS_Revamp\Backend\decryption_IFAMS\out\production\decryption_IFAMS" AESDecryption
Encrypted Data length: 7024
IV length: 16
CipherText Length: 7008
Raw Key File (Base64): tfc54T2/ZsUj1uIubosxdqglCrMjTMQVuPYs4c/2WQYxY4zBEOeFt8wGMMt5JlXVHdnRqR2GaCVo5Yjbag6rEMR7cFvDdk5oXvs41/B6KE96rHhgKul1CNYGtpptl8qHAeXM1kcn6qEO9C9vpukmu54BmgO7vo2tJOFJLHi9l/CxEUSVhgaFG2e+LhxlC+qOcvnZIJk6uBbNYV1tdBPO1FyTNfFRN2YNwCiuWM0Sr4KKssm3ugP+GJ08p5ZdiW2RmdEy4htGaHyTv/GLfmEWh0RP5IkVM2dMXnx/6GKOdB5oZWGOw0yAGBtyJfZvNwFqd0srWAIaDJYHn9b7i0TKWQ==
Decoded Key Length: 256
finalKey Length: 16
java.security.InvalidAlgorithmParameterException: Wrong IV length: must be 16 bytes long
	at com.sun.crypto.provider.CipherCore.init(CipherCore.java:525)
	at com.sun.crypto.provider.AESCipher.engineInit(AESCipher.java:346)
	at javax.crypto.Cipher.implInit(Cipher.java:813)
	at javax.crypto.Cipher.chooseProvider(Cipher.java:871)
	at javax.crypto.Cipher.init(Cipher.java:1403)
	at javax.crypto.Cipher.init(Cipher.java:1334)
	at AESDecryption.decrypt(AESDecryption.java:81)
	at AESDecryption.main(AESDecryption.java:48)
java.security.InvalidAlgorithmParameterException: Wrong IV length: must be 16 bytes long
	at com.sun.crypto.provider.CipherCore.init(CipherCore.java:525)
	at com.sun.crypto.provider.AESCipher.engineInit(AESCipher.java:346)
	at javax.crypto.Cipher.implInit(Cipher.java:813)
	at javax.crypto.Cipher.chooseProvider(Cipher.java:871)
	at javax.crypto.Cipher.init(Cipher.java:1403)
	at javax.crypto.Cipher.init(Cipher.java:1334)
	at AESDecryption.decrypt(AESDecryption.java:81)
	at AESDecryption.main(AESDecryption.java:55)
Decryption Error: Given final block not properly padded. Such issues can arise if a bad key is used during decryption.
javax.crypto.BadPaddingException: Given final block not properly padded. Such issues can arise if a bad key is used during decryption.
	at com.sun.crypto.provider.CipherCore.unpad(CipherCore.java:975)
	at com.sun.crypto.provider.CipherCore.fillOutputBuffer(CipherCore.java:1056)
	at com.sun.crypto.provider.CipherCore.doFinal(CipherCore.java:853)
	at com.sun.crypto.provider.AESCipher.engineDoFinal(AESCipher.java:446)
	at javax.crypto.Cipher.doFinal(Cipher.java:2172)
	at AESDecryption.main(AESDecryption.java:59)

Process finished with exit code 0


