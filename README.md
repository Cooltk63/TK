// Method to handle InduDvl
private void processInduDvl(List<String> dataList, Date liabilityDate, String branchCode, int submissionId, String liabilityId) {
    CRSInduDvlpInc existingEntity = crsInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(liabilityDate, branchCode, liabilityId);
    if (existingEntity == null) {
        CRSInduDvlpInc newEntity = new CRSInduDvlpInc();
        newEntity.setDate(liabilityDate);
        newEntity.setBranchCode(branchCode);
        newEntity.setCrsInduDvlpId(liabilityId);
        // Assuming dataList contains specific values for entity fields
        newEntity.setSomeField(dataList.get(0)); // Set fields from dataList accordingly
        newEntity.setOtherField(dataList.get(1)); // Set additional fields
        crsInduDvIncRepository.save(newEntity);
    } else {
        existingEntity.setDate(liabilityDate);
        existingEntity.setBranchCode(branchCode);
        existingEntity.setCrsInduDvlpId(liabilityId);
        // Update fields from dataList accordingly
        existingEntity.setSomeField(dataList.get(0));
        existingEntity.setOtherField(dataList.get(1));
        crsInduDvIncRepository.save(existingEntity);
    }
}

// Method to handle InfraDvl
private void processInfraDvl(List<String> dataList, Date liabilityDate, String branchCode, int submissionId, String liabilityId) {
    CRSInfraDvlpInc existingEntity = crsInfraDvIncRepository.findByDateAndBranchNumberAndCrsInfraDvlpId(liabilityDate, branchCode, liabilityId);
    if (existingEntity == null) {
        CRSInfraDvlpInc newEntity = new CRSInfraDvlpInc();
        newEntity.setDate(liabilityDate);
        newEntity.setBranchCode(branchCode);
        newEntity.setCrsInfraDvlpId(liabilityId);
        // Set fields based on dataList
        newEntity.setSomeField(dataList.get(0));
        newEntity.setOtherField(dataList.get(1));
        crsInfraDvIncRepository.save(newEntity);
    } else {
        existingEntity.setDate(liabilityDate);
        existingEntity.setBranchCode(branchCode);
        existingEntity.setCrsInfraDvlpId(liabilityId);
        existingEntity.setSomeField(dataList.get(0));
        existingEntity.setOtherField(dataList.get(1));
        crsInfraDvIncRepository.save(existingEntity);
    }
}

// Method to handle AgriDvl
private void processAgriDvl(List<String> dataList, Date liabilityDate, String branchCode, int submissionId, String liabilityId) {
    CRSAgrDvlpInc existingEntity = crsAgriDvIncRepository.findByDateAndBranchNumberAndCrsAgrDvlpId(liabilityDate, branchCode, liabilityId);
    if (existingEntity == null) {
        CRSAgrDvlpInc newEntity = new CRSAgrDvlpInc();
        newEntity.setDate(liabilityDate);
        newEntity.setBranchCode(branchCode);
        newEntity.setCrsAgrDvlpId(liabilityId);
        newEntity.setSomeField(dataList.get(0));  // Set fields based on dataList
        newEntity.setOtherField(dataList.get(1));
        crsAgriDvIncRepository.save(newEntity);
    } else {
        existingEntity.setDate(liabilityDate);
        existingEntity.setBranchCode(branchCode);
        existingEntity.setCrsAgrDvlpId(liabilityId);
        existingEntity.setSomeField(dataList.get(0));
        existingEntity.setOtherField(dataList.get(1));
        crsAgriDvIncRepository.save(existingEntity);
    }
}

// Method to handle HousDvl
private void processHousDvl(List<String> dataList, Date liabilityDate, String branchCode, int submissionId, String liabilityId) {
    CRSHousDvlpInc existingEntity = crsHousDvIncRepository.findByDateAndBranchNumberAndCrsHousDvlpId(liabilityDate, branchCode, liabilityId);
    if (existingEntity == null) {
        CRSHousDvlpInc newEntity = new CRSHousDvlpInc();
        newEntity.setDate(liabilityDate);
        newEntity.setBranchCode(branchCode);
        newEntity.setCrsHousDvlpId(liabilityId);
        newEntity.setSomeField(dataList.get(0)); // Set fields based on dataList
        newEntity.setOtherField(dataList.get(1));
        crsHousDvIncRepository.save(newEntity);
    } else {
        existingEntity.setDate(liabilityDate);
        existingEntity.setBranchCode(branchCode);
        existingEntity.setCrsHousDvlpId(liabilityId);
        existingEntity.setSomeField(dataList.get(0));
        existingEntity.setOtherField(dataList.get(1));
        crsHousDvIncRepository.save(existingEntity);
    }
}