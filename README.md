@Transactional
@Override
public Map<String, Object> deleteRowData(Map<String, Object> map) {
    Map<String, Object> result = new HashMap<>();
    
    try {
        // Get the modeID from the data map
        String modeIdStr = (String) map.get("data").get("deleteId");
        int modeId = Integer.parseInt(modeIdStr); // Converting string to integer

        // Check if the entity exists by modeID
        Optional<TAR_MODE> existingEntity = tarModeRepository.findById(modeId);

        if (existingEntity.isPresent()) {
            // If it exists, delete the entity
            tarModeRepository.delete(existingEntity.get());
            result.put("Message", "Data deleted successfully.");
            result.put("status", true);
        } else {
            // If entity doesn't exist
            result.put("Message", "Data not found.");
            result.put("status", false);
        }
    } catch (Exception e) {
        result.put("Message", "Error occurred: " + e.getMessage());
        result.put("status", false);
    }
    
    return result;
}