import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

public class AESDecryption {
    private static final String AES_ALGO = "AES";
    private static final String AES_CIPHER = "AES/CBC/PKCS5Padding";

    public static void main(String[] args) {
        try {
            // 1️⃣ Read Encrypted File (Base64 Encoded)
            String encryptedBase64 = new String(Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Encrypted")), StandardCharsets.UTF_8);
            
            // 2️⃣ Read the Key File (Plain Text Key)
            String keyString = new String(Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Dynamic_Key.key")), StandardCharsets.UTF_8).trim();

            // 3️⃣ Decrypt Using Client’s Methodology
            String decryptedText = decryptPayload(encryptedBase64, keyString);

            // 4️⃣ Print Decrypted Data
            System.out.println("Decrypted Data:\n" + decryptedText);

        } catch (Exception e) {
            System.err.println("Decryption Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String decryptPayload(String encryptedText, String secretKey) throws Exception {
        try {
            // Decode Base64 Encrypted Text
            byte[] encryptedTextByte = Base64.getDecoder().decode(encryptedText);

            // Convert Key to Byte Array (UTF-8)
            byte[] keybyte = secretKey.getBytes(StandardCharsets.UTF_8);

            // Derive IV from First 16 Bytes of the Key
            byte[] ivKey = Arrays.copyOf(keybyte, 16);
            IvParameterSpec iv = new IvParameterSpec(ivKey);

            // Create Secret Key Object
            SecretKeySpec secretKeyObject = new SecretKeySpec(Arrays.copyOf(keybyte, 16), AES_ALGO);

            // Initialize Cipher
            Cipher cipher = Cipher.getInstance(AES_CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, secretKeyObject, iv);

            // Perform Decryption
            byte[] decryptedByte = cipher.doFinal(encryptedTextByte);

            // Convert Decrypted Bytes to String
            return new String(decryptedByte, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new Exception("Decryption failed: " + e.getMessage(), e);
        }
    }
}