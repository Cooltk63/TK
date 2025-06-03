@GetMapping("/download/moc")
public ResponseEntity<Resource> downloadMocFile(HttpSession session) throws IOException {
    // Check authentication and authorization
    if (session.getAttribute("user") == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // Adjust path to wherever your protected file is stored
    Path filePath = Paths.get("/opt/app/protected/moc.csv");
    Resource resource = new UrlResource(filePath.toUri());

    if (!resource.exists()) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType("text/csv"))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"moc.csv\"")
        .body(resource);
}




xxx
<a href="${pageContext.request.contextPath}/download/moc" class="btn btn-warning">
    <i class="fa fa-download"></i> Download Sample File
</a>