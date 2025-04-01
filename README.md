import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESDecryption {
    
    public static void main(String[] args) {
        try {
            // 🔹 Base64 Encoded Encrypted Data (Ciphertext)
            String base64CipherText = "YOUR_BASE64_ENCRYPTED_CIPHERTEXT"; // Replace with your actual Base64 encrypted string
            System.out.println("Ciphertext (Base64): " + base64CipherText);

            // 🔹 Base64 Encoded Key
            String base64Key = "YOUR_BASE64_KEY"; // Replace with your actual Base64 key
            System.out.println("Key (Base64): " + base64Key);

            // 🔹 Extract the key (assuming 256-bit AES, so it's 32 bytes)
            byte[] decodedKey = Base64.getDecoder().decode(base64Key);
            SecretKeySpec secretKey = new SecretKeySpec(decodedKey, "AES");

            // 🔹 Extract the IV (first 16 bytes from the ciphertext)
            byte[] decodedCipherText = Base64.getDecoder().decode(base64CipherText);
            byte[] iv = new byte[16];
            System.arraycopy(decodedCipherText, 0, iv, 0, iv.length);

            // 🔹 Extract the actual ciphertext (after the IV)
            byte[] cipherText = new byte[decodedCipherText.length - iv.length];
            System.arraycopy(decodedCipherText, iv.length, cipherText, 0, cipherText.length);
            
            // 🔹 Initialize Cipher for AES/CBC/PKCS5Padding Decryption
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            // 🔹 Decrypt the ciphertext
            byte[] decryptedData = cipher.doFinal(cipherText);
            
            // 🔹 Print the Decrypted Data (assuming UTF-8 encoding)
            System.out.println("Decrypted Data: " + new String(decryptedData, "UTF-8"));
        } catch (Exception e) {
            System.err.println("Decryption Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}