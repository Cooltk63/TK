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
            System.out.println("🔹 Key Length: " + keyBase64.length());

            // 5️⃣ Decode Base64 Key
            byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
            System.out.println("🔹 Decoded Key Length: " + decodedKey.length);

            // 6️⃣ Ensure Key is 16, 24, or 32 bytes
            byte[] finalKey = new byte[16];


            if (decodedKey.length == 16) {
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
            System.out.println("Decrypted Data:\n" + new String(decryptedData, "UTF-8"));

        } catch (Exception e) {
            System.err.println("Decryption Error: " + e.getMessage());
            e.printStackTrace();
            System.out.println("MSG : " + e.getMessage());
            System.out.println("Cause : " + e.getCause());
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


for this code ai am getting the error of 

D:\jdk1.8.0_351\bin\java.exe "-javaagent:E:\Tushar Khade\Softwares\2025-IDE\IntelliSD\lib\idea_rt.jar=62541" -Dfile.encoding=UTF-8 -classpath "D:\jdk1.8.0_351\jre\lib\charsets.jar;D:\jdk1.8.0_351\jre\lib\deploy.jar;D:\jdk1.8.0_351\jre\lib\ext\access-bridge-64.jar;D:\jdk1.8.0_351\jre\lib\ext\cldrdata.jar;D:\jdk1.8.0_351\jre\lib\ext\dnsns.jar;D:\jdk1.8.0_351\jre\lib\ext\jaccess.jar;D:\jdk1.8.0_351\jre\lib\ext\jfxrt.jar;D:\jdk1.8.0_351\jre\lib\ext\localedata.jar;D:\jdk1.8.0_351\jre\lib\ext\nashorn.jar;D:\jdk1.8.0_351\jre\lib\ext\sunec.jar;D:\jdk1.8.0_351\jre\lib\ext\sunjce_provider.jar;D:\jdk1.8.0_351\jre\lib\ext\sunmscapi.jar;D:\jdk1.8.0_351\jre\lib\ext\sunpkcs11.jar;D:\jdk1.8.0_351\jre\lib\ext\zipfs.jar;D:\jdk1.8.0_351\jre\lib\javaws.jar;D:\jdk1.8.0_351\jre\lib\jce.jar;D:\jdk1.8.0_351\jre\lib\jfr.jar;D:\jdk1.8.0_351\jre\lib\jfxswt.jar;D:\jdk1.8.0_351\jre\lib\jsse.jar;D:\jdk1.8.0_351\jre\lib\management-agent.jar;D:\jdk1.8.0_351\jre\lib\plugin.jar;D:\jdk1.8.0_351\jre\lib\resources.jar;D:\jdk1.8.0_351\jre\lib\rt.jar;F:\Projects\CRS Projects\CRS_Revamp\Backend\decryption_IFAMS\out\production\decryption_IFAMS" AESDecryption
🔹 Encrypted Data Length: 7024
IV Length: 16
🔹 CipherText Length: 7008
🔹 Key Length: 344
🔹 Decoded Key Length: 256
🔹 Final Key Length: 16
Decryption Error: Given final block not properly padded. Such issues can arise if a bad key is used during decryption.
javax.crypto.BadPaddingException: Given final block not properly padded. Such issues can arise if a bad key is used during decryption.
	at com.sun.crypto.provider.CipherCore.unpad(CipherCore.java:975)
	at com.sun.crypto.provider.CipherCore.fillOutputBuffer(CipherCore.java:1056)
	at com.sun.crypto.provider.CipherCore.doFinal(CipherCore.java:853)
	at com.sun.crypto.provider.AESCipher.engineDoFinal(AESCipher.java:446)
	at javax.crypto.Cipher.doFinal(Cipher.java:2172)
	at AESDecryption.decryptAES(AESDecryption.java:73)
	at AESDecryption.main(AESDecryption.java:52)
MSG : Given final block not properly padded. Such issues can arise if a bad key is used during decryption.
Cause : null

Process finished with exit code 0



My requirement for decryption as per below 

1-The methodology/steps to be used at our end for decryption

ALGO = AES
CIPHER = AES/CBC/PKCS5Padding
with Base64 encoder and 16-byte iv parameter 


Suggest me is there any issues or code is not as per my requirements let me how did i resove this issue.
