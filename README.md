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
        saveData(valueNameMap.get(0), branchCode, quarterEndDate, reportMasterIdFk, induDvlpRepository, CRSInduDvlpInc::new);
        saveData(valueNameMap.get(1), branchCode, quarterEndDate, reportMasterIdFk, infraDvlpRepository, CRSInfraDvlpInc::new);
        saveData(valueNameMap.get(2), branchCode, quarterEndDate, reportMasterIdFk, agrDvlpRepository, CRSAgrDvlpInc::new);
        saveData(valueNameMap.get(3), branchCode, quarterEndDate, reportMasterIdFk, housDvlpRepository, CRSHousDvlpInc::new);
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
     * @param <T>               The type of the entity.
     */
    private <T extends CommonEntityFields> void saveData(
            List<List<String>> dataRows,
            String branchCode,
            String quarterEndDate,
            String reportMasterIdFk,
            JpaRepository<T, ?> repository,
            Supplier<T> entityConstructor) {

        if (dataRows == null || dataRows.isEmpty()) return;

        // Map each row to an entity and save it
        List<T> entities = dataRows.stream()
                .map(row -> {
                    T entity = entityConstructor.get();
                    populateCommonFields(entity, row, branchCode, quarterEndDate, reportMasterIdFk);
                    populateSpecificFields(entity, row);
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
     */
    private void populateCommonFields(
            CommonEntityFields entity,
            List<String> row,
            String branchCode,
            String quarterEndDate,
            String reportMasterIdFk) {

        entity.setBranchCode(branchCode);
        entity.setDate(LocalDate.parse(quarterEndDate, DATE_FORMATTER));
        entity.setOther(getValue(row, 1));  // Example: Column index for 'other'
        entity.setTotal(getValue(row, 3));  // Example: Column index for 'total'
        entity.setTotalAdvances(getValue(row, 4));  // Example: Column index for 'total advances'
        entity.setReportMasterListIdFk(reportMasterIdFk);
        entity.setRowId(getValue(row, row.size() - 3));  // Last 3rd column
        entity.setSameRowId(getValue(row, row.size() - 2));  // 2nd last column
        entity.setHardcodedFlag(getValue(row, row.size() - 1));  // Last column
    }

    /**
     * Populates specific fields for each entity type.
     *
     * @param entity The entity to populate.
     * @param row    The data row.
     */
    private void populateSpecificFields(CommonEntityFields entity, List<String> row) {
        if (entity instanceof CRSInduDvlpInc) {
            ((CRSInduDvlpInc) entity).setCrsInduDvlpProcfee(getValue(row, 5));  // Example index for 'procfee'
        } else if (entity instanceof CRSInfraDvlpInc) {
            ((CRSInfraDvlpInc) entity).setCrsInfraDvlpProcfee(getValue(row, 5));  // Example index for 'procfee'
        } else if (entity instanceof CRSAgrDvlpInc) {
            ((CRSAgrDvlpInc) entity).setCrsAgriDvlpProcfee(getValue(row, 5));  // Example index for 'procfee'
        } else if (entity instanceof CRSHousDvlpInc) {
            ((CRSHousDvlpInc) entity).setCrsHousDvlpProcfee(getValue(row, 5));  // Example index for 'procfee'
        }
    }

    /**
     * Safely retrieves the value from a row or returns an empty string if out of bounds.
     *
     * @param row The data row.
     * @param index The index of the column to retrieve.
     * @return The value at the given index or an empty string if out of bounds.
     */
    private String getValue(List<String> row, int index) {
        return index < row.size() ? row.get(index) : "";
    }
}