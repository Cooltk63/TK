

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

public class AESDecryption {

    public static void main(String[] args) {
        try {
            // 🔹 Load Encrypted File (Read as raw bytes, NOT Base64)
            byte[] encryptedData = Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Encrypted"));
            System.out.println("Encrypted data length: " + encryptedData.length);

            // 🔹 Extract IV (First 16 bytes)
            byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);
            System.out.println("IV Length: " + iv.length);

            // 🔹 Extract Actual Ciphertext (After IV)
            byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);
            System.out.println("CipherText Length: " + cipherText.length);

            // 🔹 Load Key File
            byte[] keyBytes = Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Dynamic_Key.key"));
            String keyBase64 = new String(keyBytes).trim();
            System.out.println("Raw Key File (Base64): " + keyBase64);

            byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
            System.out.println("Decoded Key Length: " + decodedKey.length);

            // 🔹 Ensure Valid AES Key Length (16, 24, or 32 bytes)
            byte[] finalKey;
            if (decodedKey.length == 16 || decodedKey.length == 24 || decodedKey.length == 32) {
                finalKey = decodedKey; // Key is already valid
                System.out.println("finalKey Length: " + finalKey.length);
            } else {
                // If key length is invalid, adjust it
                finalKey = new byte[16]; // Default to AES-256 (32 bytes)
                System.arraycopy(decodedKey, 0, finalKey, 0, Math.min(decodedKey.length, finalKey.length));
                System.out.println("finalKey Length: " + finalKey.length);
            }
            System.out.println("Final Key Length: " + finalKey.length);

            // 🔹 Setup AES Decryption
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(finalKey, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
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
}





Console Outout As per below ::

Encrypted data length: 7024
IV Length: 16
CipherText Length: 7008
Raw Key File (Base64): tfc54T2/ZsUj1uIubosxdqglCrMjTMQVuPYs4c/2WQYxY4zBEOeFt8wGMMt5JlXVHdnRqR2GaCVo5Yjbag6rEMR7cFvDdk5oXvs41/B6KE96rHhgKul1CNYGtpptl8qHAeXM1kcn6qEO9C9vpukmu54BmgO7vo2tJOFJLHi9l/CxEUSVhgaFG2e+LhxlC+qOcvnZIJk6uBbNYV1tdBPO1FyTNfFRN2YNwCiuWM0Sr4KKssm3ugP+GJ08p5ZdiW2RmdEy4htGaHyTv/GLfmEWh0RP5IkVM2dMXnx/6GKOdB5oZWGOw0yAGBtyJfZvNwFqd0srWAIaDJYHn9b7i0TKWQ==
Decoded Key Length: 256
finalKey Length: 16
Final Key Length: 16
Decryption Error: Given final block not properly padded. Such issues can arise if a bad key is used during decryption.
javax.crypto.BadPaddingException: Given final block not properly padded. Such issues can arise if a bad key is used during decryption.
	at com.sun.crypto.provider.CipherCore.unpad(CipherCore.java:975)
	at com.sun.crypto.provider.CipherCore.fillOutputBuffer(CipherCore.java:1056)
	at com.sun.crypto.provider.CipherCore.doFinal(CipherCore.java:853)
	at com.sun.crypto.provider.AESCipher.engineDoFinal(AESCipher.java:446)
	at javax.crypto.Cipher.doFinal(Cipher.java:2172)
	at AESDecryption.main(AESDecryption.java:54)
