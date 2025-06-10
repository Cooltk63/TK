@Override
public List<SFTPStatus> getListData(String QED) {
    log.info("Entering method getListData in DaoImpl with input parameter QED: {}", QED);

    // Initialize result list
    List<SFTPStatus> statusList = new ArrayList<>();

    // Validate input
    if (QED == null || QED.trim().isEmpty()) {
        log.warn("Input QED is null or empty. Returning empty list.");
        return statusList;
    }

    // Build SQL query
    StringBuilder queryBuilder = new StringBuilder();
    queryBuilder.append("SELECT ");
    queryBuilder.append("CIRCLE_NAME, ");
    queryBuilder.append("REPORT_STATUS, ");
    queryBuilder.append("FILES_RECEIVED_STATUS, ");
    queryBuilder.append("FILES_TIMESTAMP, ");
    queryBuilder.append("REVISED_COUNT, ");
    queryBuilder.append("ERROR_MESSAGE ");
    queryBuilder.append("FROM BS_SFTPFILES_STATUS ");
    queryBuilder.append("WHERE QUARTER_DATE = TO_DATE(?, 'dd/mm/yyyy')");

    final String finalQuery = queryBuilder.toString();

    log.debug("Constructed SQL query: {}", finalQuery);

    try {
        statusList = jdbcTemplate.query(finalQuery, new Object[]{QED}, new RowMapper<SFTPStatus>() {
            @Override
            public SFTPStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
                SFTPStatus sftpStatus = createEmptyStatusObject();

                String circleName = rs.getString("CIRCLE_NAME");
                if (circleName != null) {
                    sftpStatus.setCircleName(circleName.trim());
                }

                String reportStatus = rs.getString("REPORT_STATUS");
                sftpStatus.setReportStatus(reportStatus != null ? reportStatus.trim() : null);

                String filesReceivedStatus = rs.getString("FILES_RECEIVED_STATUS");
                sftpStatus.setFileReceivedStatus(filesReceivedStatus != null ? filesReceivedStatus.trim() : null);

                String fileTimestamp = rs.getString("FILES_TIMESTAMP");
                sftpStatus.setTimeStamp(fileTimestamp != null ? fileTimestamp : "N/A");

                String revisedCount = rs.getString("REVISED_COUNT");
                if (revisedCount != null && revisedCount.equalsIgnoreCase("1")) {
                    sftpStatus.setRevisedFileStatus("NO");
                } else {
                    sftpStatus.setRevisedFileStatus("YES");
                }

                String errorMessage = rs.getString("ERROR_MESSAGE");
                sftpStatus.setMessage(errorMessage != null ? errorMessage.trim() : "");

                return sftpStatus;
            }
        });

        if (statusList.isEmpty()) {
            log.info("Query executed successfully but returned an empty list.");
        } else {
            log.info("Query executed successfully, total records fetched: {}", statusList.size());
        }

    } catch (Exception e) {
        log.error("Exception occurred while fetching data in getListData: {}", e.getMessage(), e);
        throw new RuntimeException("Failed to fetch SFTP Status list due to: " + e.getMessage(), e);
    }

    log.info("Exiting method getListData with result list size: {}", statusList.size());
    return statusList;
}

private SFTPStatus createEmptyStatusObject() {
    SFTPStatus status = new SFTPStatus();
    status.setCircleName("");
    status.setReportStatus("");
    status.setFileReceivedStatus("");
    status.setTimeStamp("");
    status.setRevisedFileStatus("");
    status.setMessage("");
    return status;
}