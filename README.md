public interface TarModeRepository extends JpaRepository<TAR_MODE, Integer> {

    // Method to find a TAR_MODE entity by its modeID
    Optional<TAR_MODE> findById(Integer modeId);

    // Custom delete method (if needed, but JpaRepository already provides delete functionality)
    void deleteById(Integer modeId);

}