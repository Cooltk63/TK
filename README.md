import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AESFileTool {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    public static byte[] readBytes(String path) throws Exception {
        return Files.readAllBytes(Paths.get(path));
    }

    public static void encrypt(String inputPath, String outputPath, String keyPath, String ivPath) throws Exception {
        byte[] keyBytes = readBytes(keyPath);
        byte[] ivBytes = readBytes(ivPath);
        byte[] inputBytes = readBytes(inputPath);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,
                new SecretKeySpec(keyBytes, "AES"),
                new IvParameterSpec(ivBytes));

        byte[] encrypted = cipher.doFinal(inputBytes);
        Files.write(Paths.get(outputPath), encrypted);
        System.out.println("Encrypted file written to: " + outputPath);
    }

    public static void decrypt(String inputPath, String outputPath, String keyPath, String ivPath) throws Exception {
        byte[] keyBytes = readBytes(keyPath);
        byte[] ivBytes = readBytes(ivPath);
        byte[] inputBytes = readBytes(inputPath);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,
                new SecretKeySpec(keyBytes, "AES"),
                new IvParameterSpec(ivBytes));

        byte[] decrypted = cipher.doFinal(inputBytes);
        Files.write(Paths.get(outputPath), decrypted);
        System.out.println("Decrypted file written to: " + outputPath);
    }

    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Usage:");
            System.out.println("  java -jar AESFileTool.jar encrypt <inputFilePath> <outputFilePath> <keyFilePath> <ivFilePath>");
            System.out.println("  java -jar AESFileTool.jar decrypt <inputFilePath> <outputFilePath> <keyFilePath> <ivFilePath>");
            return;
        }

        String operation = args[0];
        String inputPath = args[1];
        String outputPath = args[2];
        String keyPath = args[3];
        String ivPath = args[4];

        try {
            if ("encrypt".equalsIgnoreCase(operation)) {
                encrypt(inputPath, outputPath, keyPath, ivPath);
            } else if ("decrypt".equalsIgnoreCase(operation)) {
                decrypt(inputPath, outputPath, keyPath, ivPath);
            } else {
                System.out.println("Invalid operation. Use 'encrypt' or 'decrypt'.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



xxxxxx
import your.package.name.AESFileTool;

public class YourApp {
    public static void main(String[] args) {
        try {
            String inputFile = "data/input.txt";
            String encryptedFile = "data/output.aes";
            String decryptedFile = "data/output_decrypted.txt";
            String keyFile = "data/aes.key";
            String ivFile = "data/aes.iv";

            // Encrypt the file
            AESFileTool.encrypt(inputFile, encryptedFile, keyFile, ivFile);

            // Decrypt the file
            AESFileTool.decrypt(encryptedFile, decryptedFile, keyFile, ivFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

xxxxx

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.security.SecureRandom;

public class KeyAndIVGenerator {

    public static void main(String[] args) throws Exception {
        // Generate AES 256-bit Key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // AES-256
        SecretKey secretKey = keyGen.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        // Generate 16-byte IV
        byte[] ivBytes = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(ivBytes);

        // Save Key to file
        try (FileOutputStream keyOut = new FileOutputStream("aes.key")) {
            keyOut.write(keyBytes);
        }

        // Save IV to file
        try (FileOutputStream ivOut = new FileOutputStream("aes.iv")) {
            ivOut.write(ivBytes);
        }

        System.out.println("Key and IV generated successfully.");
    }
}