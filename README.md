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
            // 🔹 Load Encrypted File (Read as raw bytes, NOT Base64)
            byte[] encryptedData = Files.readAllBytes(Paths.get("path/to/encrypted/file"));

            // 🔹 Extract IV (First 16 bytes)
            byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);

            // 🔹 Extract Actual Ciphertext (After IV)
            byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);

            // 🔹 Load Key File
            byte[] keyBytes = Files.readAllBytes(Paths.get("path/to/key/file"));
            byte[] decodedKey = Base64.getDecoder().decode(new String(keyBytes).trim());

            // 🔹 Ensure Valid AES Key Length (16, 24, or 32 bytes)
            byte[] finalKey;
            if (decodedKey.length == 16 || decodedKey.length == 24 || decodedKey.length == 32) {
                finalKey = decodedKey; // Key is already valid
            } else {
                // If key length is invalid, adjust it
                finalKey = new byte[32]; // Default to AES-256 (32 bytes)
                System.arraycopy(decodedKey, 0, finalKey, 0, Math.min(decodedKey.length, finalKey.length));
            }

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