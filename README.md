package com.tar.reportService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tar.reportService.models.TAR_MODE;

import java.util.Optional;

public interface TarModeRepository extends JpaRepository<TAR_MODE, Integer> {

    /**
     * Find TAR_MODE by modeId.
     *
     * @param modeId The modeId to search for.
     * @return An Optional containing the TAR_MODE entity if it exists.
     */
    Optional<TAR_MODE> findByModeid(@Param("modeId") int modeId);

    /**
     * Check if a TAR_MODE record exists by modeId.
     *
     * @param modeId The modeId to check.
     * @return True if a record exists, false otherwise.
     */
    boolean existsByModeid(@Param("modeId") int modeId);
}

xx

public Map<String, Object> saveRowData(List<String> dataList) {
    Map<String, Object> result = new HashMap<>();

    try {
        // Fetch modeId from the 8th position of the dataList
        int modeId = Integer.parseInt(dataList.get(7));

        // Check if a record exists with the given modeId
        if (tarModeRepository.existsByModeid(modeId)) {
            // Fetch the existing entity
            TAR_MODE existingEntity = tarModeRepository.findByModeid(modeId).orElseThrow(
                () -> new IllegalStateException("Data not found for the given modeId: " + modeId)
            );

            // Update the entity with new data
            setEntity(existingEntity, dataList);
            tarModeRepository.save(existingEntity);

            result.put("modeId", modeId);
            result.put("status", true);
            result.put("message", "Record updated successfully");
        } else {
            // Create a new entity and save it
            TAR_MODE newEntity = new TAR_MODE();
            setEntity(newEntity, dataList);
            TAR_MODE savedEntity = tarModeRepository.save(newEntity);

            result.put("modeId", savedEntity.getModeid());
            result.put("status", true);
            result.put("message", "Record inserted successfully");
        }
    } catch (Exception e) {
        result.put("status", false);
        result.put("message", "Failed to save or update data: " + e.getMessage());
    }

    return result;
}