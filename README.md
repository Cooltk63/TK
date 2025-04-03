import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

public class AESDecryption {
    public static void main(String[] args) {
        try {
            // 1️⃣ Read Encrypted File
            byte[] encryptedData = Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Encrypted"));
            System.out.println("🔹 Encrypted Data Length: " + encryptedData.length);

            // 2️⃣ Extract IV (First 16 bytes)
            if (encryptedData.length < 16) throw new IllegalArgumentException("Invalid encrypted file! Size too small.");
            byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);
            System.out.println("IV Length: " + iv.length);

            // 3️⃣ Extract CipherText (After IV)
            byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);
            System.out.println("🔹 CipherText Length: " + cipherText.length);

            // 4️⃣ Read and Decode the Key File
            byte[] keyBytes = Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Dynamic_Key.key"));
            String keyBase64 = new String(keyBytes).trim();
            System.out.println("🔹 Base64 Encoded Key Length: " + keyBase64.length());

            // 5️⃣ Decode Base64 Key
            byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
            System.out.println("🔹 Decoded Key Length: " + decodedKey.length);

            // 6️⃣ Validate Key Size (Must Be 16, 24, or 32 Bytes)
            if (decodedKey.length != 16 && decodedKey.length != 24 && decodedKey.length != 32) {
                throw new IllegalArgumentException("Invalid AES Key Length: " + decodedKey.length);
            }

            // 7️⃣ Perform AES Decryption
            byte[] decryptedData = decryptAES(cipherText, decodedKey, iv);
            System.out.println("✅ Decrypted Data:\n" + new String(decryptedData, "UTF-8"));

        } catch (Exception e) {
            System.err.println("❌ Decryption Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static byte[] decryptAES(byte[] cipherText, byte[] secretKey, byte[] iv) throws Exception {
        if (iv.length != 16) {
            throw new IllegalArgumentException("❌ Invalid IV length: " + iv.length + ". Must be 16 bytes.");
        }

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(cipherText);
    }
}