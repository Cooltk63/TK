import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl {

    @Autowired
    private CRSInduDvlpRepository induDvlpRepository;

    @Autowired
    private CRSInfraDvlpRepository infraDvlpRepository;

    @Autowired
    private CRSAgrDvlpRepository agrDvlpRepository;

    @Autowired
    private CRSHousDvlpRepository housDvlpRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Saves data for all four tables based on the ordered list provided by the frontend.
     *
     * @param parameters A map containing:
     *                   - 'user': A map with 'branch_code' and 'quarterEndDate'.
     *                   - 'data': A map with 'submissionId' and 'valueNameMap' (ordered list of lists).
     */
    @Transactional
    public void saveAllTablesData(Map<String, Map<String, Object>> parameters) {
        // Extract and validate user and data information
        String branchCode = getRequiredParameter(parameters, "user", "branch_code");
        String quarterEndDate = getRequiredParameter(parameters, "user", "quarterEndDate");
        String reportMasterIdFk = getRequiredParameter(parameters, "data", "submissionId");

        // Retrieve the ordered list of data
        List<List<List<String>>> valueNameMap = (List<List<List<String>>>) parameters.get("data").get("valueNameMap");
        if (valueNameMap == null || valueNameMap.size() < 4) {
            throw new IllegalArgumentException("Incomplete data. Expecting at least 4 lists.");
        }

        // Process and save each category of data using method references
        saveData(valueNameMap.get(0), branchCode, quarterEndDate, reportMasterIdFk, induDvlpRepository, CRSInduDvlpInc::new, "CrsInduDvlpProcfee");
        saveData(valueNameMap.get(1), branchCode, quarterEndDate, reportMasterIdFk, infraDvlpRepository, CRSInfraDvlpInc::new, "CrsInfraDvlpProcfee");
        saveData(valueNameMap.get(2), branchCode, quarterEndDate, reportMasterIdFk, agrDvlpRepository, CRSAgrDvlpInc::new, "CrsAgriDvlpProcfee");
        saveData(valueNameMap.get(3), branchCode, quarterEndDate, reportMasterIdFk, housDvlpRepository, CRSHousDvlpInc::new, "CrsHouseDvlpProcfee");
    }

    /**
     * Retrieves a required parameter from the provided map.
     *
     * @param parameters The main parameters map.
     * @param mapKey     The key to access the inner map ('user' or 'data').
     * @param key        The key of the required parameter.
     * @return The value associated with the key.
     * @throws IllegalArgumentException if the key is missing or the value is null.
     */
    private String getRequiredParameter(Map<String, Map<String, Object>> parameters, String mapKey, String key) {
        return Optional.ofNullable((String) parameters.get(mapKey).get(key))
                .orElseThrow(() -> new IllegalArgumentException("Missing '" + key + "' in parameters"));
    }

    /**
     * Saves data for a specific category (InduDvlp, InfraDvlp, AgriDvlp, HousDvlp).
     *
     * @param dataRows          The list of data rows to process.
     * @param branchCode        The branch code.
     * @param quarterEndDate    The quarter end date.
     * @param reportMasterIdFk  The report master ID.
     * @param repository        The repository to save the entities.
     * @param entityConstructor A supplier to create a new entity instance.
     * @param procFeeField      The field to be set as Procfee (varies by table).
     * @param <T>               The type of the entity.
     */
    private <T> void saveData(
            List<List<String>> dataRows,
            String branchCode,
            String quarterEndDate,
            String reportMasterIdFk,
            JpaRepository<T, ?> repository,
            Supplier<T> entityConstructor,
            String procFeeField) {

        if (dataRows == null || dataRows.isEmpty()) return;

        // Map each row to an entity and save it
        List<T> entities = dataRows.stream()
                .map(row -> {
                    T entity = entityConstructor.get();
                    populateCommonFields(entity, row, branchCode, quarterEndDate, reportMasterIdFk, procFeeField);
                    return entity;
                })
                .collect(Collectors.toList());

        // Save all entities in a batch
        repository.saveAll(entities);
    }

    /**
     * Populates common fields for all entities (branch code, date, etc.).
     *
     * @param entity            The entity to populate.
     * @param row               The data row.
     * @param branchCode        The branch code.
     * @param quarterEndDate    The quarter end date.
     * @param reportMasterIdFk  The report master ID.
     * @param procFeeField      The field name for 'procfee'.
     */
    private void populateCommonFields(
            Object entity,
            List<String> row,
            String branchCode,
            String quarterEndDate,
            String reportMasterIdFk,
            String procFeeField) {

        // Set common fields for all entities
        LocalDate date = LocalDate.parse(quarterEndDate, DATE_FORMATTER);

        if (entity instanceof CRSInduDvlpInc) {
            CRSInduDvlpInc induEntity = (CRSInduDvlpInc) entity;
            induEntity.setBranchCode(branchCode);
            induEntity.setDate(date);
            induEntity.setOther(getValue(row, 1));  // Example: Column index for 'other'
            induEntity.setTotal(getValue(row, 3));  // Example: Column index for 'total'
            induEntity.setTotalAdvances(getValue(row, 4));  // Example: Column index for 'total advances'
            induEntity.setReportMasterListIdFk(reportMasterIdFk);
            induEntity.setRowId(getValue(row, row.size() - 3));  // Last 3rd column
            induEntity.setSameRowId(getValue(row, row.size() - 2));  // 2nd last column
            induEntity.setHardcodedFlag(getValue(row, row.size() - 1));  // Last column
            setProcFee(induEntity, procFeeField, getValue(row, 5));  // Set procfee dynamically
        } else if (entity instanceof CRSInfraDvlpInc) {
            CRSInfraDvlpInc infraEntity = (CRSInfraDvlpInc) entity;
            infraEntity.setBranchCode(branchCode);
            infraEntity.setDate(date);
            infraEntity.setOther(getValue(row, 1));  // Example: Column index for 'other'
            infraEntity.setTotal(getValue(row, 3));  // Example: Column index for 'total'
            infraEntity.setTotalAdvances(getValue(row, 4));  // Example: Column index for 'total advances'
            infraEntity.setReportMasterListIdFk(reportMasterIdFk);
            infraEntity.setRowId(getValue(row, row.size() - 3));  // Last 3rd column
            infraEntity.setSameRowId(getValue(row, row.size() - 2));  // 2nd last column
            infraEntity.setHardcodedFlag(getValue(row, row.size() - 1));  // Last column
            setProcFee(infraEntity, procFeeField, getValue(row, 5));  // Set procfee dynamically
        } else if (entity instanceof CRSAgrDvlpInc) {
            CRSAgrDvlpInc agrEntity = (CRSAgrDvlpInc) entity;
            agrEntity.setBranchCode(branchCode);
            agrEntity.setDate(date);
            agrEntity.setOther(getValue(row, 1));  // Example: Column index for 'other'
            agrEntity.setTotal(getValue(row, 3));  // Example: Column index for 'total'
            agrEntity.setTotalAdvances(getValue(row, 4));  // Example: Column index for 'total advances'
            agrEntity.setReportMasterListIdFk(reportMasterIdFk);
            agrEntity.setRowId(getValue(row, row.size() - 3));  // Last 3rd column
            agrEntity.setSameRowId(getValue(row, row.size() - 2));  // 2nd last column
            agrEntity.setHardcodedFlag(getValue(row, row.size() - 1));  // Last column
            setProcFee(agrEntity, procFeeField, getValue(row, 5));  // Set procfee dynamically
        } else if (entity instanceof CRSHousDvlpInc) {
            CRSHousDvlpInc housEntity = (CRSHousDvlpInc) entity;
            housEntity.setBranchCode(branchCode);
            housEntity.setDate(date);
            housEntity.setOther(getValue(row, 1));  // Example: Column index for 'other'
            housEntity.setTotal(getValue(row, 3));  // Example: Column index for 'total'
            housEntity.setTotalAdvances(getValue(row, 4));  // Example: Column index for 'total advances'
            housEntity.setReportMasterListIdFk(reportMasterIdFk);
            housEntity.setRowId(getValue(row, row.size() - 3));  // Last 3rd column
            housEntity.setSameRowId(getValue(row, row.size() - 2));  // 2nd last column
            housEntity.setHardcodedFlag(getValue(row, row.size() - 1));  // Last column
            setProcFee(housEntity, procFeeField, getValue(row, 5));  // Set procfee dynamically
        }
    }

    /**
     * Helper function to retrieve values from row.
     * @param row The row of data.
     * @param index The column index.
     * @return The value at the specified column index.
     */
    private String getValue(List<String> row, int index) {
        if (row != null && row.size() > index) {
            return row.get(index);
        }
        return "";
    }

    /**
     * Dynamically sets the Procfee field in the entity.
     */
    private void setProcFee(Object entity, String procFeeField, String procFeeValue) {
        if ("CrsInduDvlpProcfee".equals(procFeeField)) {
            ((CRSInduDvlpInc) entity).setCrsInduDvlpProcfee(procFeeValue);
        } else if ("CrsInfraDvlpProcfee".equals(procFeeField)) {
            ((CRSInfraDvlpInc) entity).setCrsInfraDvlpProcfee(procFeeValue);
        } else if ("CrsAgriDvlpProcfee".equals(procFeeField)) {
            ((CRSAgrDvlpInc) entity).setCrsAgriDvlpProcfee(procFeeValue);
        } else if ("CrsHouseDvlpProcfee".equals(procFeeField)) {
            ((CRSHousDvlpInc) entity).setCrsHouseDvlpProcfee(procFeeValue);
        }
    }
}