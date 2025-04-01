import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class AESDecryption {
    public static void main(String[] args) throws Exception {
        // Read the encrypted file
        byte[] encryptedData = Files.readAllBytes(Paths.get("path/to/encrypted/file"));

        // Read the key file (assuming it's stored as raw bytes)
        byte[] keyBytes = Files.readAllBytes(Paths.get("path/to/key/file"));
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

        // IV (Assuming it is provided separately or stored in the encrypted file)
        byte[] ivBytes = new byte[16]; // Replace with actual IV
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // Initialize Cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // Decode Base64 (if applicable)
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

        // Decrypt
        byte[] decryptedData = cipher.doFinal(encryptedBytes);

        // Save the decrypted file
        Files.write(Paths.get("path/to/decrypted/file"), decryptedData);

        System.out.println("Decryption completed successfully!");
    }
}