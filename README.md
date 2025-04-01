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
            System.out.println("🔹 IV (Hex): " + bytesToHex(iv));

            // 3️⃣ Extract CipherText (After IV)
            byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);
            System.out.println("🔹 CipherText Length: " + cipherText.length);

            // 4️⃣ Read and Decode the Key File
            byte[] keyBytes = Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Dynamic_Key.key"));
            String keyBase64 = new String(keyBytes).trim();
            System.out.println("🔹 Key (Base64 Encoded): " + keyBase64);

            // 5️⃣ Decode Base64 Key
            byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
            System.out.println("🔹 Decoded Key (Hex): " + bytesToHex(decodedKey));
            System.out.println("🔹 Decoded Key Length: " + decodedKey.length);

            // 6️⃣ Ensure Key is 16, 24, or 32 bytes
            byte[] finalKey = new byte[16];
            if (decodedKey.length == 16 || decodedKey.length == 24 || decodedKey.length == 32) {
                finalKey = decodedKey;
            } else {
                System.arraycopy(decodedKey, 0, finalKey, 0, Math.min(decodedKey.length, finalKey.length));
            }
            System.out.println("🔹 Final Key Length: " + finalKey.length);

            // 7️⃣ Check CipherText Length
            if (cipherText.length == 0 || cipherText.length % 16 != 0) {
                throw new IllegalArgumentException("Invalid CipherText! Length must be multiple of 16 bytes.");
            }

            // 8️⃣ Perform AES Decryption
            byte[] decryptedData = decryptAES(cipherText, finalKey, iv);
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

    // Helper method to print byte array as Hex
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }
}