import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.Base64;

public class DecryptPayload {

    private static final int TAG_LENGTH_BIT = 128;
    private static final String AES_ALGO = "AES";
    private static final String AES_CIPHER = "AES/GCM/NoPadding";  // Fix: Use correct GCM mode

    public static void main(String[] args) {
        try {
            // Read Encrypted File (Base64 Encoded)
            String encryptedBase64 = new String(Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Encrypted"))).trim();

            // Read the Key File (Base64 Encoded Key)
            String keyBase64 = new String(Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Dynamic_Key.key"))).trim();

            // Decode Base64 Key
            byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
            if (decodedKey.length != 16 && decodedKey.length != 24 && decodedKey.length != 32) {
                throw new IllegalArgumentException("Invalid AES key length. Expected 16, 24, or 32 bytes.");
            }

            // Decrypt
            String decryptedText = decryptPayload(encryptedBase64, decodedKey);

            // Print Decrypted Data
            System.out.println("Decrypted Data:\n" + decryptedText);

        } catch (Exception e) {
            System.err.println("Decryption Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String decryptPayload(String encryptedText, byte[] secretKey) throws Exception {
        try {
            // Decode Encrypted Data from Base64
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

            // Extract IV from the beginning of the encrypted data (First 16 bytes)
            byte[] iv = new byte[16];
            System.arraycopy(encryptedBytes, 0, iv, 0, 16);

            // Extract actual encrypted data (excluding IV)
            byte[] cipherText = new byte[encryptedBytes.length - 16];
            System.arraycopy(encryptedBytes, 16, cipherText, 0, cipherText.length);

            // Create Secret Key Object
            SecretKey secretKeyObject = new SecretKeySpec(secretKey, AES_ALGO);

            // Initialize Cipher for Decryption
            Cipher cipher = Cipher.getInstance(AES_CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, secretKeyObject, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

            // Perform Decryption
            byte[] decryptedBytes = cipher.doFinal(cipherText);
            return new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new Exception("Decryption failed: " + e.getMessage(), e);
        }
    }
}