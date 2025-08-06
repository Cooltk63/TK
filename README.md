lprivate static byte[] computeHashFromFile(String filePath, MessageDigest md) throws IOException {
    try (InputStream inputStream = new FileInputStream(filePath)) {
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            md.update(buffer, 0, bytesRead);
        }
    }
    return md.digest();
}

private static String getFileHash(String filePath) throws IOException, NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hash = computeHashFromFile(filePath, md);
    return Base64.getEncoder().encodeToString(hash);
}