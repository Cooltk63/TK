// Put this at the class level or as a static final field
private static final Set<String> ALLOWED_COLUMNS = Set.of(
    "T40_COL1", "T40_COL2", "T40_COL3", "T40_COL4",
    "T40_COL5", "T40_COL6", "T40_COL7", "T40_COL8",
    "T40_COL9", "T40_COL10", "T40_COL11"
);


public int save(Map<String, Object> map) {
    log.info("in save function in AssetQualityDaoImpl");

    String columnName = (String) map.get("columnName");

    // Validate the column name
    if (!ALLOWED_COLUMNS.contains(columnName)) {
        log.error("Attempt to use invalid column name: {}", columnName);
        throw new IllegalArgumentException("Invalid column name: " + columnName);
    }

    // Construct query safely with validated column name
    String query = "UPDATE FR_T40 SET " + columnName + " = ? WHERE T40_ID = ? AND T40_RPTID_FK = ? AND T40_QED = TO_DATE(?,'dd/mm/yyyy')";

    return jdbcTemplate.update(
        query,
        map.get(FIELD_VALUE),
        map.get(ROW_ID),
        map.get(REPORT_ID),
        map.get(QUARTER_END_DATE)
    );
}