List<CrsReports> hhh = ggg.getData();

for (CrsReports report : hhh) {
    try {
        byte[] reportBytes = report.getReportsDataAsBytes(); // Convert Blob to byte[]
        log.info("Report Data: " + Arrays.toString(reportBytes)); // Print as byte array
    } catch (SQLException e) {
        log.error("Error reading BLOB data", e);
    }
}