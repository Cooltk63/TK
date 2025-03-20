 private Optional<String> getSC10ReportStatus(String circleCode,String qed){
        log.info("### Getting Report Status From NON CR for Schedule 10");
        log.info("circleCode:-" + circleCode);
        log.info("qed:-" + qed);
        String qry="select NVL(NONCR_FLAG,'10') from BS_NONCR where NONCR_CIRCLE=? and NONCR_QDATE=to_date(?,'dd/mm/yyyy') AND NONCR_NAME=?";
        /*String Flag = */
        return Optional.ofNullable(jdbcTemplate.queryForObject(qry, new Object[]{circleCode, qed, "Schedule 10"}, String.class));
    }
