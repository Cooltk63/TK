public ResponseEntity downloadZip(Map<String, Object> payload) {
    Map<String, Object> userData = (Map<String, Object>) payload.get("user");
    ResponseVO<String> responseVO = new ResponseVO<>();
    String finYear = userData.get("financial_year").toString();

    // 🔁 Step 1: Platform-safe base path
    String os = System.getProperty("os.name").toLowerCase();
    String basePath = os.contains("win") ? "C:\\MyFiles\\IAM" : "/media/IAM";  // Change Windows path accordingly

    // 🔁 Step 2: Safe path construction
    Path filePath = Paths.get(basePath, finYear, "IAM_Combined.zip");
    File file = filePath.toFile();

    log.info("Looking for file at: " + file.getAbsolutePath());

    byte[] fileContent = null;
    try {
        log.info("File exists: " + file.exists());
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        fileContent = Files.readAllBytes(filePath);

        if (fileContent == null || fileContent.length == 0) {
            log.info("Corrupted File, length: " + (fileContent == null ? 0 : fileContent.length));
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("File Corrupted");
            responseVO.setResult(null);
            return new ResponseEntity<>(responseVO, HttpStatus.OK);
        }

    } catch (IOException e) {
        responseVO.setStatusCode(HttpStatus.OK.value());
        responseVO.setMessage("Failed to download Zip file");
        responseVO.setResult(null);
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    responseVO.setStatusCode(HttpStatus.OK.value());
    responseVO.setMessage("Zip File downloaded successfully.");
    responseVO.setResult(Base64.getEncoder().encodeToString(fileContent)); // Optional: send as Base64 to avoid corruption

    return new ResponseEntity<>(responseVO, HttpStatus.OK);
}