import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;


public class AESDecryption {
    public static void main(String[] args) {
        try { // 1️⃣
            byte[] encryptedData = Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Encrypted"));
            // Load the encrypted file (Binary, NOT Base64) byte[] encryptedData = Files.readAllBytes(Paths.get("C:\Users\v1012297\Downloads\keys\IFAMS_SCH10_20240331_002_Encrypted")); System.out.println("Encrypted Data length: " + encryptedData.length);


// 2️⃣ Extract IV (First 16 bytes)
            byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);
            System.out.println("IV length: " + iv.length);

            // 3️⃣ Extract Ciphertext (Rest after IV)
            byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);
            System.out.println("CipherText Length: " + cipherText.length);

            // 4️⃣ Load the key file (Base64 encoded key)
            byte[] keyBytes = Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Dynamic_Key.key"));
            String keyBase64 = new String(keyBytes).trim(); // Remove any newline issues
            System.out.println("Raw Key File (Base64): " + keyBase64);

            // 5️⃣ Decode the Base64 key
            byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
            System.out.println("Decoded Key Length: " + decodedKey.length);

            // 6️⃣ Validate key length (AES supports 16, 24, 32 bytes)
            byte[] finalKey=new byte[16];
            if (decodedKey.length == 16 || decodedKey.length == 24 || decodedKey.length == 32) {
                finalKey = decodedKey;
            } else {
                finalKey = new byte[16]; // Default to AES-128 (16 bytes)
                System.arraycopy(decodedKey, 0, finalKey, 0, Math.min(decodedKey.length, finalKey.length));
                System.out.println("finalKey Length: " + finalKey.length);
            }
            System.out.println("Final Key Length: " + finalKey.length);

            // 7️⃣ Perform AES decryption
            byte[] decryptedData = decryptAES(cipherText, finalKey, iv);

            // 8️⃣ Print Decrypted Output
            System.out.println("Decrypted Data: \n" + new String(decryptedData, "UTF-8"));

        } catch (Exception e) {
            System.err.println("Decryption Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static byte[] decryptAES(byte[] cipherText, byte[] secretKey, byte[] iv) throws Exception {
        if (iv.length != 16) {
            throw new IllegalArgumentException("Invalid IV length: " + iv.length + ". Must be 16 bytes.");
        }

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(cipherText);
    }

}


Still giving me this error

