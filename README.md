@Service
public class FirmMasterService {

    @Autowired
    private FirmMasterRepository firmMasterRepository;

    @Autowired
    private EmpanelmentRepository empanelmentRepository;

    // Path where FRN validation errors will be written
    private static final String ERROR_FILE_PATH = "/media/IAM/FRN_Error_Log.txt";  // Update to your path

    /**
     * Process the uploaded Excel file, validate each row, check for existing FRN, 
     * save valid firms to DB, and log errors for duplicates or invalid data.
     *
     * @param file          The uploaded Excel file (.xlsx)
     * @param loggedUser    The currently logged-in user
     * @param financialYear The financial year in context
     * @return List of validation errors to be sent back to frontend
     * @throws IOException If reading the Excel fails
     */
    public List<String> processExcelAndSave(MultipartFile file, String loggedUser, String financialYear) throws IOException {
        List<String> validationErrors = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = 0;

            // Process each row one by one (skip header row)
            for (Row row : sheet) {
                if (rowNum++ == 0) continue;  // Skip header row

                // Read data from current row into a Map
                Map<String, String> rowData = readRowData(row);

                // Validate row and collect any errors
                List<String> rowValidationErrors = validateRow(rowData, row.getRowNum());
                if (!rowValidationErrors.isEmpty()) {
                    validationErrors.addAll(rowValidationErrors);
                    continue;  // Skip to next row if validation fails
                }

                String frnNo = rowData.get("FRN");

                // Check if this FRN already exists in Empanelment
                int frnCount = empanelmentRepository.checkFrnCount(frnNo, loggedUser, financialYear);
                log.info("Row {} FRN: {} | FRN Count: {}", row.getRowNum(), frnNo, frnCount);

                if (frnCount == 0) {
                    // FRN not present → save firm to master table
                    Firm_Master firm = mapToFirmEntity(rowData);
                    firmMasterRepository.save(firm);  // Saving each firm individually
                } else {
                    // FRN already empanelled → log this in text file
                    String errorLine = String.format("FRN: %s, Firm Name: %s, Error: FRN Already Empanelled",
                                                     frnNo, rowData.get("FIRM_NAME"));
                    writeErrorToFile(errorLine);
                }
            }
        }

        return validationErrors;
    }

    /**
     * Reads cell values from a row and maps them to field names.
     */
    private Map<String, String> readRowData(Row row) {
        Map<String, String> data = new HashMap<>();
        data.put("UCN", getCellValue(row.getCell(0)));
        data.put("FRN", getCellValue(row.getCell(1)));
        data.put("FIRM_NAME", getCellValue(row.getCell(2)));
        data.put("PAN", getCellValue(row.getCell(3)));
        data.put("GSTN", getCellValue(row.getCell(4)));
        data.put("ADDRESS", getCellValue(row.getCell(5)));
        data.put("CITY", getCellValue(row.getCell(6)));
        data.put("STATE", getCellValue(row.getCell(7)));
        data.put("DISTRICT", getCellValue(row.getCell(8)));
        data.put("PINCODE", getCellValue(row.getCell(9)));
        data.put("MOBILE", getCellValue(row.getCell(10)));
        data.put("POC", getCellValue(row.getCell(11)));
        data.put("POC_DESIG", getCellValue(row.getCell(12)));
        data.put("POC_EMAIL", getCellValue(row.getCell(13)));
        data.put("FIRM_TYPE", getCellValue(row.getCell(14)));
        return data;
    }

    /**
     * Validates the required fields of a row.
     */
    private List<String> validateRow(Map<String, String> data, int rowNum) {
        List<String> errors = new ArrayList<>();

        if (isEmpty(data.get("FRN"))) errors.add("Row " + rowNum + ": Firm Registration No. is mandatory");
        if (isEmpty(data.get("FIRM_NAME"))) errors.add("Row " + rowNum + ": Firm Name is mandatory");
        if (isEmpty(data.get("PAN"))) errors.add("Row " + rowNum + ": PAN No. is mandatory");
        if (isEmpty(data.get("PINCODE"))) errors.add("Row " + rowNum + ": Pin Code is mandatory");
        if (isEmpty(data.get("MOBILE"))) errors.add("Row " + rowNum + ": Primary Mobile No. is mandatory");

        try {
            Integer.parseInt(data.get("UCN"));
        } catch (NumberFormatException e) {
            errors.add("Row " + rowNum + ": Invalid UCN (must be numeric)");
        }

        return errors;
    }

    /**
     * Maps validated row data to the Firm_Master entity.
     */
    private Firm_Master mapToFirmEntity(Map<String, String> data) {
        Firm_Master firm = new Firm_Master();

        firm.setUcnno(Integer.parseInt(data.get("UCN")));
        firm.setFrnno(data.get("FRN"));
        firm.setFirmname(data.get("FIRM_NAME"));
        firm.setPanno(data.get("PAN"));
        firm.setGstnno(data.get("GSTN"));
        firm.setAddress(data.get("ADDRESS"));
        firm.setCity(data.get("CITY"));
        firm.setState(data.get("STATE"));
        firm.setDistrict(data.get("DISTRICT"));
        firm.setPincode(data.get("PINCODE"));
        firm.setMobno(data.get("MOBILE"));
        firm.setContactperson(data.get("POC"));
        firm.setPocDesignation(data.get("POC_DESIG"));
        firm.setPocEmail(data.get("POC_EMAIL"));
        firm.setFirmtype(data.get("FIRM_TYPE"));

        firm.setBranchcode(0);              // Default branch code
        firm.setBranchname("DEFAULT");      // Default branch name

        return firm;
    }

    /**
     * Reads cell value safely as String.
     */
    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }

    /**
     * Checks if a String is null or empty.
     */
    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Writes error message to the error log text file.
     */
    private void writeErrorToFile(String errorLine) {
        try {
            Files.write(Paths.get(ERROR_FILE_PATH),
                        (errorLine + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.error("Failed to write error to log file", e);
        }
    }
}