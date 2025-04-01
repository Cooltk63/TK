import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESDecryptor {
    public static void main(String[] args) {
        try {
            // 🔹 Load encrypted data as raw bytes
            byte[] encryptedData = Files.readAllBytes(Paths.get("path/to/encrypted/file"));

            // 🔹 Extract IV (first 16 bytes)
            byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);
            byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);

            System.out.println("IV: " + Base64.getEncoder().encodeToString(iv));

            // 🔹 Load key file (check if it's Base64 encoded or raw bytes)
            byte[] keyBytes = Files.readAllBytes(Paths.get("path/to/key/file"));
            String keyContent = new String(keyBytes).trim();

            // Detect if the key is Base64 encoded or raw
            byte[] decodedKey;
            try {
                decodedKey = Base64.getDecoder().decode(keyContent);
                System.out.println("Key is Base64 encoded.");
            } catch (IllegalArgumentException e) {
                decodedKey = keyContent.getBytes(); // Assume raw bytes
                System.out.println("Key is raw bytes.");
            }

            // 🔹 Ensure AES key is valid (16, 24, or 32 bytes)
            byte[] finalKey;
            if (decodedKey.length == 16 || decodedKey.length == 24 || decodedKey.length == 32) {
                finalKey = decodedKey;
            } else {
                System.err.println("Invalid AES key length: " + decodedKey.length);
                return;
            }

            System.out.println("Key Length: " + finalKey.length);
            System.out.println("Key (Base64): " + Base64.getEncoder().encodeToString(finalKey));

            // 🔹 Initialize AES decryption
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(finalKey, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            // 🔹 Decrypt data
            byte[] decryptedData = cipher.doFinal(cipherText);
            System.out.println("Decryption successful:\n" + new String(decryptedData, "UTF-8"));

        } catch (Exception e) {
            System.err.println("Decryption Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}