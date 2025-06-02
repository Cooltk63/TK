@Autowired
private TableValidationService tableValidationService;

public void someMethod() {
    String tableName = "FR_T03";
    Set<String> requiredColumns = Set.of("T03_PARAMID_FK", "T03_VAL_CY", "T03_QED", "T03_VAL_PY");

    boolean isTableValid = tableValidationService.isTableValid(tableName);
    boolean areColumnsValid = tableValidationService.areColumnsValid(tableName, requiredColumns);

    if (isTableValid && areColumnsValid) {
        // Safe to proceed with dynamic query
    } else {
        throw new RuntimeException("Invalid table or missing columns");
    }
}