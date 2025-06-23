@GetMapping("/download-zip")
public ResponseEntity<Resource> downloadZip() throws IOException {
    String zipFilePath = "/media/IAM_Combined.zip";
    File file = new File(zipFilePath);

    if (!file.exists()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .contentLength(file.length())
            .body(resource);
}