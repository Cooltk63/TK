public String sanitizeFilename(String filename) {
    if (filename == null) return "download";
    // Allow only safe characters: a-zA-Z0-9_-.
    return filename.replaceAll("[^a-zA-Z0-9._-]", "_");
}