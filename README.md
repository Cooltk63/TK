I need the filter used to intercept the request from frontend other than login url all others url's which used for intercept request & extract the JSON data from then and using this json key to decrypt (decrypt function provided by me as per below) data & then transfer that decrypted data same as per previously sent by frontend but only difference data is decrypted fe can sent map inside map or anything list or single data anything whatever fe is sending just descrypt the data & sent same to backend using the Java 8 filter with spring mvc 


//below code for Java AES/GCM Encryption 7 Decryption in my case only used the decryption without changing anything inside the code as per below
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AESGCMFrontendCompatible {

    // Constants
    private static final String ALGORITHM = "AES";
    private static final String CIPHER_TRANSFORMATION = "AES/GCM/PKCS5Padding";
    private static final int GCM_TAG_LENGTH = 16; // in bytes (128 bits)
    private static final int ITERATIONS = 1000;
    private static final int KEY_LENGTH = 256;

    // Hardcoded values (same as frontend)
    private static final String BASE64_IV = "HHpJGrIv+FIx7uGu";
    private static final String BASE64_SALT = "d6PI1Fz7kVbn7Xw+cz1NwQ==";
    private static final String BASE64_PASSWORD = "juVI+XqX90tQSqYPAmtVxg==";

    public static void main(String[] args) throws Exception {
        // Input plain text
        String plainText = "This text will be encrypted and decrypted!";

        System.out.println("========== ENCRYPTION ==========");
        String encryptedBase64 = encrypt(plainText, BASE64_PASSWORD);
        System.out.println("Plain Text: " + plainText);
        System.out.println("Encrypted (Base64): " + encryptedBase64);
        System.out.println("IV (Base64): " + BASE64_IV);
        System.out.println("Salt (Base64): " + BASE64_SALT);

        System.out.println("\n========== DECRYPTION ==========");
        String decryptedText = decrypt("s5jrQ+AJgnJPT6eYChOmUYNokSD8NY/1wzJXTy+D28DReA==", BASE64_PASSWORD);
        System.out.println("Decrypted: " + decryptedText);
    }

    public static String encrypt(String plainText, String base64Password) throws Exception {
        byte[] iv = Base64.getDecoder().decode(BASE64_IV);
        byte[] salt = Base64.getDecoder().decode(BASE64_SALT);
        byte[] passwordBytes =  base64Password.getBytes(StandardCharsets.UTF_8);//Base64.getDecoder().decode(base64Password);

        SecretKeySpec key = deriveKey(passwordBytes, salt);

        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);

        byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String base64CipherText, String base64Password) throws Exception {
        byte[] iv = Base64.getDecoder().decode(BASE64_IV);
        byte[] salt = Base64.getDecoder().decode(BASE64_SALT);
        byte[] encryptedBytes = Base64.getDecoder().decode(base64CipherText);
        byte[] passwordBytes = base64Password.getBytes(StandardCharsets.UTF_8);//Base64.getDecoder().decode(base64Password);

        SecretKeySpec key = deriveKey(passwordBytes, salt);

        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, gcmSpec);

        byte[] decrypted = cipher.doFinal(encryptedBytes);
        return new String(decrypted, "UTF-8");
    }

    private static SecretKeySpec deriveKey(byte[] passwordBytes, byte[] salt) throws Exception {
        KeySpec spec = new PBEKeySpec(
                toCharArray(passwordBytes),
                salt,
                ITERATIONS,
                KEY_LENGTH
        );
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    // Helper: Convert byte[] to char[] for PBEKeySpec
    private static char[] toCharArray(byte[] bytes) {
        char[] chars = new char[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            chars[i] = (char) (bytes[i] & 0xff);
        }
        return chars;
    }
}
