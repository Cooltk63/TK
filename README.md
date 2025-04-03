import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class decryptPayload {

    private static final int TAG_LENGTH_BIT = 128;
    private static final String AES_ALGO = "AES";
    private static final String AES_CIPHER = "AES/CBC/PKCS5Padding";

    public static void main(String[] args) {
        try {
            // 1️⃣ Read Encrypted File (Base64 Encoded)
            String encryptedBase64 = new String(Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Encrypted")));

            // 2️⃣ Read the Key File (Plain Text Key)
            String keyString = new String(Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Dynamic_Key.key")));

           String DecodedKey= Arrays.toString(Base64.getDecoder().decode(keyString));

            // 3️⃣ Decrypt using EncryptedData & DecodedKey
            String decryptedText = decryptPayload(encryptedBase64, DecodedKey);

            // 4️⃣ Print Decrypted Data
            System.out.println("Decrypted Data:\n" + decryptedText);

        } catch (Exception e) {
            System.err.println("Decryption Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String decryptPayload(String encryptedText, String secretKey) throws Exception {

        String decryptedText;
        //IvParameterSpec iv;
        try {
            byte[] encryptedTextByte = Base64.getDecoder().decode(encryptedText);
            byte[] keybyte = secretKey.getBytes(StandardCharsets.UTF_8);
            byte[] ivKey = Arrays.copyOf(keybyte, 16);
            //iv = new IvParameterSpec(ivKey);
            SecretKey secretKeyObject = new SecretKeySpec(keybyte, AES_ALGO);
            System.out.println("ivKey==>" + ivKey);
            Cipher cipher = Cipher.getInstance(AES_CIPHER);
            //cipher.init(Cipher.DECRYPT_MODE, secretKeyObject, iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKeyObject, new GCMParameterSpec(TAG_LENGTH_BIT, ivKey));//R
            byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
            decryptedText = new String(decryptedByte);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                 | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new Exception(e.getMessage());
        }
        return decryptedText;
    }


}
