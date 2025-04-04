public static void decryptFile(String encryptedFilePath, String decryptedFilePath) throws Exception {
    // Read encrypted file as bytes
    byte[] encryptedBytes = Files.readAllBytes(Paths.get(encryptedFilePath));

    // Perform decryption (Replace this with your actual decryption logic)
    byte[] decryptedBytes = decrypt(encryptedBytes);  // You must implement this method

    // Save decrypted content as a new .txt file
    Files.write(Paths.get(decryptedFilePath), decryptedBytes);
}