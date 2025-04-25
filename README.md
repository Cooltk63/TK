import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Main {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/PKCS5Padding";
    private static final int GCM_TAG_LENGTH = 16; // 128 bits
    private static final int ITERATIONS = 1000;
    private static final int KEY_LENGTH = 256;

    public static void main(String[] args) throws Exception {
        String password = "juVI+XqX90tQSqYPAmtVxg=="; // Use your password here
        String iv = "muWlNQ9H3DjYTsVI";
        String salt = "4Q0V9q2/XWsN4JjWFPGstw==";
        String encryptedData = "JW6eYVUn81ZowUmviQTYmzLy8GwIqugYkf1SM8cXELfarg==";

        byte[] ivBytes = Base64.getDecoder().decode(iv);
        byte[] saltBytes = Base64.getDecoder().decode(salt);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

        SecretKey secretKey = generateSecretKey(password, saltBytes);
        byte[] decryptedData = decrypt(encryptedBytes, secretKey, ivBytes);

        System.out.println("Decrypted Data: " + new String(decryptedData));
    }

    private static SecretKey generateSecretKey(String password, byte[] salt) throws Exception {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] key = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(key, ALGORITHM);
    }


    private static byte[] decrypt(byte[] encryptedData, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        return cipher.doFinal(encryptedData);
    }
}
