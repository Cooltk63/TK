public class AESDecryption {
    public static void main(String[] args) {
        try {
            // Load Encrypted File (Read as raw bytes, NOT Base64)
            byte[] encryptedData = Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Encrypted"));
            System.out.println("Encrypted data length: " + encryptedData.length);

            // Extract IV (First 16 bytes)
            byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);  // Extract first 16 bytes as IV
            System.out.println("IV Length: " + iv.length);

            // Extract Actual Ciphertext (After IV)
            byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);  // Remaining part as ciphertext
            System.out.println("CipherText Length: " + cipherText.length);

            // Load Key File
            byte[] keyBytes = Files.readAllBytes(Paths.get("C:\\Users\\v1012297\\Downloads\\keys\\IFAMS_SCH10_20240331_002_Dynamic_Key.key"));
            String keyBase64 = new String(keyBytes).trim();
            System.out.println("Raw Key File (Base64): " + keyBase64);

            byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
            System.out.println("Decoded Key Length: " + decodedKey.length);

            // Ensure Valid AES Key Length (16 bytes for AES-128)
            byte[] finalKey = new byte[16]; // Default to AES-128 (16 bytes)
            System.arraycopy(decodedKey, 0, finalKey, 0, Math.min(decodedKey.length, finalKey.length));
            System.out.println("Final Key Length: " + finalKey.length);

            // Setup AES Decryption
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(finalKey, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);  // Correct IV usage
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            // Decrypt Data
            byte[] decryptedData = cipher.doFinal(cipherText);

            // Print or Save Decrypted Output
            System.out.println("Decrypted Data:\n" + new String(decryptedData, "UTF-8"));

        } catch (Exception e) {
            System.err.println("Decryption Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}