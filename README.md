@GetMapping("/download.zip")
public ResponseEntity<Resource> downloadZip(@RequestParam("path") String path) {
    File file = new File(path);
    if (!file.exists() || !file.getName().endsWith(".zip")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    try {
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(resource);

    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}