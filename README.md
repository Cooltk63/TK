@GetMapping("/download")
public ResponseEntity<byte[]> downloadZipFile(@RequestParam String year) throws IOException {
    Path filePath = Paths.get("/media", "IAM", year, "Combined.zip");
    File file = filePath.toFile();

    System.out.println("Looking for file at: " + file.getAbsolutePath());

    if (!file.exists()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    byte[] fileContent = Files.readAllBytes(filePath);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentLength(fileContent.length);
    headers.setContentDisposition(ContentDisposition.attachment().filename(file.getName()).build());

    return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
}