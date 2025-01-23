@Service
public class TarModeService {

    @Autowired
    private TarModeRepository tarModeRepository;

    /**
     * Saves or updates TAR_MODE data based on the provided rowDataList.
     * Returns a map with row number and status for each operation.
     *
     * @param rowDataList List of rows where each row contains TAR_MODE data as strings.
     * @return Map with "rowNum" as the row index and "status" as true/false for success or failure.
     */
    public Map<String, Object> saveRowData(List<List<String>> rowDataList) {
        Map<String, Object> resultMap = new HashMap<>();
        int rowNum = 0;

        for (List<String> rowData : rowDataList) {
            rowNum++; // Increment row index
            try {
                String rowId = rowData.get(7); // Extract row ID from the 8th position

                TAR_MODE tarMode;
                if (rowId == null || rowId.isEmpty()) {
                    // If row ID is not provided, create a new entity for insertion
                    tarMode = new TAR_MODE();
                } else {
                    int modeId = Integer.parseInt(rowId); // Parse the mode ID
                    // Check if the modeId already exists in the database
                    tarMode = tarModeRepository.findByModeid(modeId).orElse(new TAR_MODE());
                    tarMode.setModeid(modeId); // Set mode ID in the entity
                }

                // Populate the TAR_MODE entity using the setEntity method
                setEntity(rowData, tarMode);

                // Save the entity (insert or update)
                TAR_MODE savedEntity = tarModeRepository.save(tarMode);

                // Add success status and mode ID to the result map
                resultMap.put("rowNum", rowNum);
                resultMap.put("status", true);
                resultMap.put("modeId", savedEntity.getModeid());
            } catch (Exception e) {
                // Handle any errors and add failure status to the result map
                resultMap.put("rowNum", rowNum);
                resultMap.put("status", false);
                resultMap.put("error", e.getMessage());
            }
        }

        return resultMap;
    }

    /**
     * Maps data from the rowData list to the TAR_MODE entity based on the predefined index positions.
     *
     * @param rowData List of string values representing the row data.
     * @param tarMode The TAR_MODE entity to populate.
     */
    private void setEntity(List<String> rowData, TAR_MODE tarMode) {
        tarMode.setMode_branch(rowData.get(0)); // mode_branch at index 0
        tarMode.setMode_date(java.sql.Date.valueOf(rowData.get(1))); // mode_date at index 1, in 'yyyy-MM-dd' format
        tarMode.setMode_account(Integer.parseInt(rowData.get(2))); // mode_account at index 2
        tarMode.setMode_amt(Integer.parseInt(rowData.get(3))); // mode_amt at index 3
        tarMode.setMode_nature(rowData.get(4)); // mode_nature at index 4
        tarMode.setMode_pan(rowData.get(5)); // mode_pan at index 5
        tarMode.setModeid(Integer.parseInt(rowData.get(6))); // mode_id at index 6
        tarMode.setMode_payee(rowData.get(7)); // mode_payee at index 7
        tarMode.setMode_payment_dt(java.sql.Date.valueOf(rowData.get(8))); // mode_payment_dt at index 8
        tarMode.setMode_reason(rowData.get(9)); // mode_reason at index 9
        tarMode.setMode_typebgl(rowData.get(10)); // mode_typebgl at index 10
        tarMode.setReportSubmissionId(rowData.get(11)); // reportSubmissionId at index 11
    }
}