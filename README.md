Vulnerability :: Vulnerability Description in Detail	Likely Impact	
Recommendation :: Weak Cryptographic Hash: Missing Required Step	On line 191 of DecryptAscii.java, the code invokes the method digest() prior to invoking a required step after the call to getInstance() on line 181.	On line 191 of DecryptAscii.java, the code invokes the method digest() prior to invoking a required step after the call to getInstance() on line 181.	Implement all steps required in the generation of a cryptographic hash. Where possible, explicitly specify the parameters used to ensure that the strength of hash is not compromised.

Source Code ::

  private static String getFileHash(String filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
            log.info("into get file hash");
        try (InputStream inputStream = new FileInputStream(filePath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        }

        byte[] hash = md.digest();
        log.info("last line of  get file hash");
        return Base64.getEncoder().encodeToString(hash);
    }

    Tell me how to resolved this issue
