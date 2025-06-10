public String getBrcodeforsftp(String circleCode, String rpname) {
    log.info("Entering method getBrcodeforsftp with parameters circleCode: " + circleCode + ", rpname: " + rpname);

    String result = "";
    try {
        String baseQuery = "SELECT branchCode from BS_CCDP_FILES where CIRCLECODE=? and REPORTNAME=?";
        log.debug("Constructed SQL query: " + baseQuery);

        Object[] queryParams = new Object[2];
        queryParams[0] = circleCode;
        queryParams[1] = rpname;

        result = jdbcTemplate.queryForObject(baseQuery, queryParams, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                String branchCode = rs.getString("branchCode");
                return (branchCode != null) ? branchCode : "";
            }
        });

        log.info("Query executed successfully, Result is: " + result);
    } catch (DataAccessException ex) {
        log.error("Data Access Exception occurred in getBrcodeforsftp. Message: " + ex.getMessage(), ex);
    } catch (Exception e) {
        log.error("Unexpected exception in getBrcodeforsftp: " + e.getMessage(), e);
    }

    log.info("Exiting getBrcodeforsftp with result: " + result);
    return result;
}

public int checkCCDPTimeStamp(String circleCode, String qed, String fileType) {
    log.info("Method checkCCDPTimeStamp called with parameters - circleCode: " + circleCode + ", qed: " + qed + ", fileType: " + fileType);

    String baseQuery = "select count(*) from BS_CCDPFILES_TIME where circlecode=? and QUARTER_DATE=to_date(?,'dd/mm/yyyy') AND FILE_TYPE=?";
    Object[] parameters = new Object[3];
    parameters[0] = circleCode;
    parameters[1] = qed;
    parameters[2] = fileType;

    log.debug("Executing SQL: " + baseQuery);

    int count = 0;
    try {
        count = jdbcTemplate.queryForObject(baseQuery, parameters, Integer.class);
        log.info("Retrieved count in checkCCDPTimeStamp: " + count);
    } catch (DataAccessException e) {
        log.error("Error in checkCCDPTimeStamp: " + e.getMessage(), e);
    }

    log.info("Exiting checkCCDPTimeStamp with count: " + count);
    return count;
}

public String getCCDPTimeStamp(String circleCode, String qed, String fileType) {
    log.info("Method getCCDPTimeStamp invoked with input - circleCode: " + circleCode + ", qed: " + qed + ", fileType: " + fileType);

    String query = "select nvl(CCDP_timestamp,0) as CCDP_timestamp from BS_CCDPFILES_TIME where circlecode=? and QUARTER_DATE=to_date(?,'dd/mm/yyyy') AND FILE_TYPE=?";
    String timeSt = "";

    try {
        Object[] params = new Object[]{circleCode, qed, fileType};
        timeSt = jdbcTemplate.queryForObject(query, params, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                String result = rs.getString("CCDP_timestamp");
                if (result == null || result.isEmpty()) {
                    return "0";
                } else {
                    return result;
                }
            }
        });

        log.info("Timestamp fetched successfully: " + timeSt);
    } catch (Exception ex) {
        log.error("Exception in getCCDPTimeStamp: " + ex.getMessage(), ex);
        timeSt = "0";
    }

    log.info("Exiting getCCDPTimeStamp with value: " + timeSt);
    return timeSt;
}

public int updateCCDPFiletime(String timeStamp, String circleCode, String qed, String type) {
    log.info("updateCCDPFiletime invoked with params: " + timeStamp + ", " + circleCode + ", " + qed + ", " + type);

    int countForCCDPTime = checkCCDPTimeStamp(circleCode, qed, type);
    String qry = "";
    JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
    Object[] updateParams = new Object[]{timeStamp, circleCode, qed, type};

    if (countForCCDPTime >= 1) {
        qry = "update BS_CCDPFILES_TIME set CCDP_timestamp=? where circlecode=? and quarter_date=to_date(?,'dd/mm/yyyy') and file_Type=?";
        log.info("Record exists. Preparing to update timestamp.");
    } else {
        qry = "insert into BS_CCDPFILES_TIME (CCDP_timestamp, circlecode, quarter_date, file_type) VALUES (?, ?, to_date(?,'dd/mm/yyyy'), ?)";
        log.info("Record does not exist. Preparing to insert new timestamp.");
    }

    int count = 0;
    try {
        count = jdbcTemplateObject.update(qry, updateParams);
        log.info("Query executed. Rows affected: " + count);
    } catch (DataAccessException ex) {
        log.error("Exception while executing update/insert in updateCCDPFiletime: " + ex.getMessage(), ex);
    }

    log.info("Exiting updateCCDPFiletime with count: " + count);
    return count;
}

public int getCountdata(String circleCode, String qed, String type) {
    log.info("Entering getCountdata with inputs - circleCode: " + circleCode + ", qed: " + qed + ", type: " + type);

    String qry = "";
    String rpName = "";
    int count = 0;

    JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
    int countForCCDPTime = checkCCDPTimeStamp(circleCode, qed, type);

    Object[] paramList = new Object[]{circleCode, qed};

    if (type != null && type.equalsIgnoreCase("SC9")) {
        log.debug("File type is SC9");
        qry = "select count(*) from BS_SC9 where SC9_CIRCLE =? and  SC9_DATE =to_date(?,'dd/mm/yyyy')";
    } else {
        log.debug("File type is not SC9; using ANX2C query");
        qry = "select count(*) from BS_ANX2C where ANNX2C_CIRCLE=? and  ANNX2C_QED=to_date(?,'dd/mm/yyyy')";
    }

    try {
        count = jdbcTemplateObject.queryForObject(qry, paramList, Integer.class);
        log.info("Count retrieved from DB: " + count);
    } catch (DataAccessException ex) {
        log.error("Data access error in getCountdata: " + ex.getMessage(), ex);
    } catch (Exception ex) {
        log.error("Unexpected error in getCountdata: " + ex.getMessage(), ex);
    }

    log.info("Exiting getCountdata with count: " + count);
    return count;
}