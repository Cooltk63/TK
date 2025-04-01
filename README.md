import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class AESDecryptor {
    public static void main(String[] args) throws Exception {
        // Load encrypted file as raw bytes (DO NOT Base64 decode)
        byte[] encryptedData = Files.readAllBytes(Paths.get("path/to/encrypted/file"));

        // Extract the IV (first 16 bytes)
        byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);

        // Extract actual ciphertext (after IV)
        byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);

        // Load and decode the AES key
        byte[] keyBytes = Files.readAllBytes(Paths.get("path/to/key/file"));
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
}