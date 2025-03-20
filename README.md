private Optional<String> getSC10ReportStatus(String circleCode, String qed) {
    log.info("### Getting Report Status From NON CR for Schedule 10");
    log.info("circleCode:-" + circleCode);
    log.info("qed:-" + qed);

    String qry = "SELECT NVL(NONCR_FLAG, '10') FROM BS_NONCR WHERE NONCR_CIRCLE=? AND NONCR_QDATE=TO_DATE(?, 'dd/mm/yyyy') AND NONCR_NAME=?";

    List<String> results = jdbcTemplate.query(qry, new Object[]{circleCode, qed, "Schedule 10"}, 
        (rs, rowNum) -> rs.getString(1));

    return results.isEmpty() ? Optional.of("10") : Optional.of(results.get(0));
}