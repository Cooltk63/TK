import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

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
     * Includes branch code from the `user` map and processes the last three columns.
     *
     * @param parameters A map containing:
     *                   - `user`: A map with `branch_code` and `quarterEndDate`.
     *                   - `data`: A map with `submissionId` and `valueNameMap` (ordered list of lists).
     */
    @Transactional
    public void saveAllTablesData(Map<String, Map<String, Object>> parameters) {
        // Extract the branch code and quarter end date from the `user` map
        Map<String, Object> userMap = parameters.get("user");
        String branchCode = (String) Optional.ofNullable(userMap.get("branch_code"))
                .orElseThrow(() -> new IllegalArgumentException("Missing `branch_code` in user map"));
        String quarterEndDate = (String) Optional.ofNullable(userMap.get("quarterEndDate"))
                .orElseThrow(() -> new IllegalArgumentException("Missing `quarterEndDate` in user map"));

        // Extract the submission ID and ordered list from the `data` map
        Map<String, Object> dataMap = parameters.get("data");
        String reportMasterIdFk = (String) Optional.ofNullable(dataMap.get("submissionId"))
                .orElseThrow(() -> new IllegalArgumentException("Missing `submissionId` in data map"));

        List<List<List<String>>> valueNameMap = (List<List<List<String>>>) dataMap.get("valueNameMap");
        if (valueNameMap == null || valueNameMap.size() < 4) {
            throw new IllegalArgumentException("Incomplete data. Expecting at least 4 lists.");
        }

        // Map the lists to corresponding entities and save
        saveEntities(valueNameMap.get(0), CRSInduDvlpInc::new, 
                     (entity, row) -> populateInduDvlp(entity, row, branchCode, quarterEndDate, reportMasterIdFk), 
                     induDvlpRepository);
        saveEntities(valueNameMap.get(1), CRSInfraDvlpInc::new, 
                     (entity, row) -> populateInfraDvlp(entity, row, branchCode, quarterEndDate, reportMasterIdFk), 
                     infraDvlpRepository);
        saveEntities(valueNameMap.get(2), CRSAgrDvlpInc::new, 
                     (entity, row) -> populateAgriDvlp(entity, row, branchCode, quarterEndDate, reportMasterIdFk), 
                     agrDvlpRepository);
        saveEntities(valueNameMap.get(3), CRSHousDvlpInc::new, 
                     (entity, row) -> populateHousDvlp(entity, row, branchCode, quarterEndDate, reportMasterIdFk), 
                     housDvlpRepository);
    }

    /**
     * Generic method to save entities in a batch.
     *
     * @param <T>         The entity type.
     * @param rows        The rows of data to map and save.
     * @param constructor A supplier to create a new entity instance.
     * @param mapper      A BiConsumer to populate the entity with row data.
     * @param repository  The repository to save the entities.
     */
    private <T> void saveEntities(List<List<String>> rows, 
                                  Supplier<T> constructor, 
                                  BiConsumer<T, List<String>> mapper, 
                                  JpaRepository<T, ?> repository) {
        if (rows == null || rows.isEmpty()) return;

        var entities = rows.stream()
                .map(row -> {
                    T entity = constructor.get();
                    mapper.accept(entity, row);
                    return entity;
                })
                .toList();

        repository.saveAll(entities);
    }

    /**
     * Populates a CRSInduDvlpInc entity.
     */
    private void populateInduDvlp(CRSInduDvlpInc entity, List<String> row, String branchCode, String quarterEndDate, String reportMasterIdFk) {
        populateCommonFields(entity, row, branchCode, quarterEndDate, reportMasterIdFk);
        entity.setCrsInduDvlpProcfee(getValue(row, row.size() - 5)); // Example
    }

    /**
     * Populates a CRSInfraDvlpInc entity.
     */
    private void populateInfraDvlp(CRSInfraDvlpInc entity, List<String> row, String branchCode, String quarterEndDate, String reportMasterIdFk) {
        populateCommonFields(entity, row, branchCode, quarterEndDate, reportMasterIdFk);
        entity.setCrsInfraDvlpProcfee(getValue(row, row.size() - 5)); // Example
    }

    /**
     * Populates a CRSAgrDvlpInc entity.
     */
    private void populateAgriDvlp(CRSAgrDvlpInc entity, List<String> row, String branchCode, String quarterEndDate, String reportMasterIdFk) {
        populateCommonFields(entity, row, branchCode, quarterEndDate, reportMasterIdFk);
        entity.setCrsAgriDvlpProcfee(getValue(row, row.size() - 5)); // Example
    }

    /**
     * Populates a CRSHousDvlpInc entity.
     */
    private void populateHousDvlp(CRSHousDvlpInc entity, List<String> row, String branchCode, String quarterEndDate, String reportMasterIdFk) {
        populateCommonFields(entity, row, branchCode, quarterEndDate, reportMasterIdFk);
        entity.setCrsHousDvlpProcfee(getValue(row, row.size() - 5)); // Example
    }

    /**
     * Populates common fields for all entities.
     */
    private <T extends CommonEntityFields> void populateCommonFields(T entity, List<String> row, String branchCode, String quarterEndDate, String reportMasterIdFk) {
        entity.setBranchCode(branchCode);
        entity.setDate(LocalDate.parse(quarterEndDate, DATE_FORMATTER));
        entity.setOther(getValue(row, 1));
        entity.setTotal(getValue(row, 3));
        entity.setTotalAdvances(getValue(row, 4));
        entity.setReportMasterListIdFk(reportMasterIdFk);
        entity.setRowId(getValue(row, row.size() - 3));
        entity.setSameRowId(getValue(row, row.size() - 2));
        entity.setHardcodedFlag(getValue(row, row.size() - 1));
    }

    /**
     * Safely gets a value from a list.
     *
     * @param row   The row list.
     * @param index The index to fetch.
     * @return The value at the index or an empty string if out of bounds.
     */
    private String getValue(List<String> row, int index) {
        return (index < row.size() && row.get(index) != null) ? row.get(index) : "";
    }
}