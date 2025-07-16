Optional<IAM_Request_MasterList> findByFrnnoAndRequestempanelmenttypeAndRequestempanelmentsubtype(
    String frnno,
    String requestempanelmenttype,
    String requestempanelmentsubtype
);

public void saveOrUpdate(IAM_Request_MasterList entity) {
    Optional<IAM_Request_MasterList> existingOpt =
        requestMasterListRepository.findByFrnnoAndRequestempanelmenttypeAndRequestempanelmentsubtype(
            entity.getFrnno(),
            entity.getRequestempanelmenttype(),
            entity.getRequestempanelmentsubtype()
        );

    if (existingOpt.isPresent()) {
        IAM_Request_MasterList existing = existingOpt.get();
        // 👉 Update logic here (skipped as requested)
        requestMasterListRepository.save(existing);
    } else {
        // 👉 Insert logic
        requestMasterListRepository.save(entity);
    }
}