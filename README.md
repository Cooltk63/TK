import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

public class AESDecryption {

    public static void main(String[] args) throws Exception {
        // Load encrypted file as raw bytes (DO NOT Base64 decode)
        byte[] encryptedData = Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Encrypted"));

        // Extract the IV (first 16 bytes)
        byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);

        // Extract actual ciphertext (after IV)
        byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);

        // Load and decode the AES key
        byte[] keyBytes = Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Dynamic_Key.key"));
        byte[] decodedKey = Base64.getDecoder().decode(new String(keyBytes).trim());

        // Ensure valid AES key length
        byte[] finalKey = new byte[32]; // AES-256
        System.arraycopy(decodedKey, 0, finalKey, 0, Math.min(decodedKey.length, finalKey.length));

        // Set up AES decryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(finalKey, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // Decrypt data
        byte[] decryptedData = cipher.doFinal(cipherText);

        // Print or save decrypted output
        System.out.println("Decrypted Data:\n" + new String(decryptedData, "UTF-8"));
    }



Exception in thread "main" javax.crypto.BadPaddingException: Given final block not properly padded. Such issues can arise if a bad key is used during decryption.
	at java.base/com.sun.crypto.provider.CipherCore.unpad(CipherCore.java:861)
	at java.base/com.sun.crypto.provider.CipherCore.fillOutputBuffer(CipherCore.java:941)
	at java.base/com.sun.crypto.provider.CipherCore.doFinal(CipherCore.java:734)
	at java.base/com.sun.crypto.provider.AESCipher.engineDoFinal(AESCipher.java:446)
	at java.base/javax.crypto.Cipher.doFinal(Cipher.java:2252)
	at AESDecryption.main(AESDecryption.java:37)
