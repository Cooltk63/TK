// Restrict file upload to CSV only
String originalFilename = file.getOriginalFilename();
String contentType = file.getContentType();

if (originalFilename == null || !originalFilename.toLowerCase().endsWith(".csv")) {
    return "Invalid file type. Only .csv files are allowed.";
}

if (contentType == null || 
    !(contentType.equals("text/csv") || contentType.equals("application/vnd.ms-excel") || contentType.equals("application/csv"))) {
    return "Invalid content type. Only CSV files are allowed.";
}


if (file.getSize() > 5 * 1024 * 1024) { // 5 MB limit
    return "File too large. Maximum allowed size is 5MB.";
}


String sanitizedFilename = FilenameUtils.getName(originalFilename); // From Apache Commons IO