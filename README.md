public Map<String, Object> saveRowData(List<String> dataList) {
    Map<String, Object> result = new HashMap<>();

    // Validate that dataList has at least 8 elements
    if (dataList.size() < 8) {
        throw new IllegalArgumentException("Invalid data list. At least 8 elements are required.");
    }

    // Extract the rowId from the 8th position
    String rowIdString = dataList.get(7).trim();

    // Create a new entity object
    TAR_MODE entity = new TAR_MODE();

    // Set common entity fields using setEntity method
    setEntity(entity, dataList);

    if (rowIdString.isEmpty()) {
        // Case 1: Insert new row when rowId is empty
        log.info("Inserting a new row as rowId is empty.");
        TAR_MODE savedEntity = tarModeRepository.save(entity); // Save entity and get generated modeId
        result.put("rowId", savedEntity.getModeid());
        result.put("status", true);
    } else {
        // Case 2: Update existing row when rowId is provided
        try {
            int rowId = Integer.parseInt(rowIdString); // Convert rowId to integer
            TAR_MODE existingEntity = tarModeRepository.findByModeid(rowId);

            if (existingEntity != null) {
                log.info("Updating existing row with modeId: {}", rowId);
                entity.setModeid(rowId); // Set the existing modeId for update
                TAR_MODE updatedEntity = tarModeRepository.save(entity); // Save updated entity
                result.put("rowId", updatedEntity.getModeid());
                result.put("status", true);
            } else {
                log.warn("No record found with modeId: {}. Update failed.", rowId);
                result.put("rowId", rowId);
                result.put("status", false);
            }
        } catch (NumberFormatException e) {
            log.error("Invalid rowId format: {}. Expected a number.", rowIdString);
            throw new IllegalArgumentException("Invalid rowId format. Must be an integer.", e);
        }
    }

    return result;
}
xxxx

private void setEntity(TAR_MODE entity, List<String> dataList) {
    // Populate entity fields from dataList
    entity.setBranchCode(dataList.get(0).trim());
    entity.setModeName(dataList.get(1).trim());
    entity.setDescription(dataList.get(2).trim());
    entity.setQuarterEndDate(dataList.get(3).trim());
    entity.setYear(dataList.get(4).trim());
    entity.setStatus(dataList.get(5).trim());
    entity.setRemarks(dataList.get(6).trim());
    // dataList.get(7) is rowId and handled separately in saveRowData
}


xxx

@Repository
public interface TarModeRepository extends JpaRepository<TAR_MODE, Integer> {
    TAR_MODE findByModeid(int modeId); // Method to fetch an entity by modeId
}