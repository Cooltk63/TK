public static void decryptFile(String encryptedFilePath, String decryptedFilePath) throws Exception {
    // Read encrypted file as bytes
    byte[] encryptedBytes = Files.readAllBytes(Paths.get(encryptedFilePath));

    // Perform decryption (Replace this with your actual decryption logic)
    byte[] decryptedBytes = decrypt(encryptedBytes);  // You must implement this method

    // Save decrypted content as a new .txt file
    Files.write(Paths.get(decryptedFilePath), decryptedBytes);
}



// Path of the encrypted file (without any extension)
String encryptedFilePath = mainPath + qDate + "/IFAMS_SCH10_" + sessionDate + "_" + circleCode;

// Define the decrypted file path (explicitly adding .txt extension)
String decryptedFilePath = encryptedFilePath + "_decrypted.txt";

// Call decryption method
decryptFile(encryptedFilePath, decryptedFilePath);

// Now use the decrypted file for reading
filePath = decryptedFilePath;