// Simple path sanitization (safe for Java 8)
if (filename.contains("..") || filename.contains("/") || filename.contains("\\") || filename.contains("%")) {
    throw new IllegalArgumentException("Invalid filename: path traversal attempt.");
}
outFilePath = config.getProperty("REPORT_HOME_DIR") + "reports" + File.separator + filename + ".xlsx";