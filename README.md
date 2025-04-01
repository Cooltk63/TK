import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Arrays;

public class AESKeyDecoder {
    public static void main(String[] args) throws Exception {
        // Read the key file
        byte[] keyFileContent = Files.readAllBytes(Paths.get("path/to/key/file"));

        // Convert file content to String
        String base64Key = new String(keyFileContent).trim(); // Remove extra spaces/newlines

        // Decode Base64
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);

        // Ensure key is valid AES size (16, 24, or 32 bytes)
        if (decodedKey.length >= 32) {
            decodedKey = Arrays.copyOf(decodedKey, 32); // Use 32 bytes for AES-256
        } else if (decodedKey.length >= 24) {
            decodedKey = Arrays.copyOf(decodedKey, 24); // Use 24 bytes for AES-192
        } else if (decodedKey.length >= 16) {
            decodedKey = Arrays.copyOf(decodedKey, 16); // Use 16 bytes for AES-128
        } else {
            throw new IllegalArgumentException("Invalid AES key length: " + decodedKey.length + " bytes");
        }

        System.out.println("Decoded AES key length: " + decodedKey.length + " bytes");
    }
}