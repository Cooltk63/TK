@Override
public List<SFTPStatus> getListData(String QED) {
    log.info("inside getListData DaoImpl ::");
    String query = "select CIRCLE_NAME, REPORT_STATUS, FILES_RECEIVED_STATUS, FILES_TIMESTAMP, REVISED_COUNT, ERROR_MESSAGE FROM BS_SFTPFILES_STATUS where QUARTER_DATE = to_date(?,'dd/mm/yyyy')";

    List<SFTPStatus> statusList = jdbcTemplate.query(query, new Object[]{QED}, (rs, rowNum) -> {
        SFTPStatus sftpStatus = new SFTPStatus();
        sftpStatus.setCircleName(rs.getString("CIRCLE_NAME"));
        sftpStatus.setReportStatus(rs.getString("REPORT_STATUS"));
        sftpStatus.setFileReceivedStatus(rs.getString("FILES_RECEIVED_STATUS"));
        sftpStatus.setTimeStamp(rs.getString("FILES_TIMESTAMP"));
        sftpStatus.setRevisedFileStatus(rs.getString("REVISED_COUNT").equalsIgnoreCase("1") ? "NO" : "YES");
        sftpStatus.setMessage(rs.getString("ERROR_MESSAGE"));
        return sftpStatus;
    });

    log.info("Returning Data list size :: " + statusList.size());
    return statusList;
}