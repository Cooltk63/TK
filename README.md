import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

public class AESFileDecryption {
    public static void main(String[] args) {
        try {
            // Paths to files (UPDATE THESE AS PER YOUR NEED)
            String keyFilePath = "C:/path/to/keyfile.key";  // Path to your AES key file
            String encryptedFilePath = "C:/path/to/encrypted/file";  // Path to your encrypted file
            String decryptedFilePath = "C:/path/to/decrypted/output";  // Output decrypted file

            // Step 1: Read and decode Base64-encoded AES key
            byte[] keyBase64 = Files.readAllBytes(Paths.get(keyFilePath));
            byte[] keyBytes = Base64.getDecoder().decode(new String(keyBase64).trim());

            // Validate AES key length (16, 24, or 32 bytes)
            if (keyBytes.length != 16 && keyBytes.length != 24 && keyBytes.length != 32) {
                throw new IllegalArgumentException("Invalid AES key length: " + keyBytes.length);
            }
            SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

            // Step 2: Read the encrypted file
            byte[] encryptedData = Files.readAllBytes(Paths.get(encryptedFilePath));

            // Extract IV (first 16 bytes)
            byte[] ivBytes = Arrays.copyOfRange(encryptedData, 0, 16);
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

            // Extract actual encrypted content
            byte[] encryptedBytes = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);

            // Step 3: Initialize Cipher for AES Decryption (CBC Mode)
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            // Step 4: Perform decryption
            byte[] decryptedData = cipher.doFinal(encryptedBytes);

            // Step 5: Write the decrypted output file
            Files.write(Paths.get(decryptedFilePath), decryptedData);

            System.out.println("Decryption successful! Decrypted file saved at: " + decryptedFilePath);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Decryption failed: " + e.getMessage());
        }
    }
}