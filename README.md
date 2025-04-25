import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.*;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AESGCMEncryptorFixed {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/PKCS5Padding";
    private static final int GCM_TAG_LENGTH = 16; // in bytes
    private static final int ITERATIONS = 1000;
    private static final int KEY_LENGTH = 256;

    // Hardcoded Base64-encoded IV and Salt
    private static final String BASE64_IV = "muWlNQ9H3DjYTsVI";
    private static final String BASE64_SALT = "4Q0V9q2/XWsN4JjWFPGstw==";

    public static void main(String[] args) throws Exception {
        runTest();
    }

    public static void runTest() throws Exception {
        String password = "juVI+XqX90tQSqYPAmtVxg=="; // Must match frontend
        String plainText = "Hello from Java backend!";

        System.out.println("=== ENCRYPTION PHASE ===");
        String encryptedData = encrypt(plainText, password);
        System.out.println("Original Text : " + plainText);
        System.out.println("Encrypted Data (Base64): " + encryptedData);
        System.out.println("IV (Base64): " + BASE64_IV);
        System.out.println("Salt (Base64): " + BASE64_SALT);
        System.out.println("Password (Base64): " + password);

        System.out.println("\n=== DECRYPTION PHASE ===");
        String decryptedData = decrypt(encryptedData, password);
        System.out.println("Decrypted Data: " + decryptedData);
    }

    public static String encrypt(String data, String password) throws Exception {
        byte[] ivBytes = Base64.getDecoder().decode(BASE64_IV);
        byte[] saltBytes = Base64.getDecoder().decode(BASE64_SALT);

        SecretKeySpec key = deriveKey(password, saltBytes);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, ivBytes);
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedData, String password) throws Exception {
        byte[] ivBytes = Base64.getDecoder().decode(BASE64_IV);
        byte[] saltBytes = Base64.getDecoder().decode(BASE64_SALT);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

        SecretKeySpec key = deriveKey(password, saltBytes);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, key, gcmSpec);

        byte[] decrypted = cipher.doFinal(encryptedBytes);
        return new String(decrypted);
    }

    private static SecretKeySpec deriveKey(String password, byte[] salt) throws Exception {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }
}