// Method to handle InduDvl
private void processInduDvl(List<String> dataList, Date liabilityDate, String branchCode, int submissionId, String liabilityId) {
    CRSInduDvlpInc existingEntity = crsInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(liabilityDate, branchCode, liabilityId);
    if (existingEntity == null) {
        CRSInduDvlpInc newEntity = setInduDvlpIncEntity(dataList, liabilityDate, branchCode, submissionId);
        crsInduDvIncRepository.save(newEntity);
    } else {
        existingEntity = setInduDvlpIncEntity(dataList, liabilityDate, branchCode, submissionId);
        crsInduDvIncRepository.save(existingEntity);
    }
}

// Method to handle InfraDvl
private void processInfraDvl(List<String> dataList, Date liabilityDate, String branchCode, int submissionId, String liabilityId) {
    CRSInfraDvlpInc existingEntity = crsInfraDvIncRepository.findByDateAndBranchNumberAndCrsInfraDvlpId(liabilityDate, branchCode, liabilityId);
    if (existingEntity == null) {
        CRSInfraDvlpInc newEntity = setInfraDvlpIncEntity(dataList, liabilityDate, branchCode, submissionId);
        crsInfraDvIncRepository.save(newEntity);
    } else {
        existingEntity = setInfraDvlpIncEntity(dataList, liabilityDate, branchCode, submissionId);
        crsInfraDvIncRepository.save(existingEntity);
    }
}

// Method to handle AgriDvl
private void processAgriDvl(List<String> dataList, Date liabilityDate, String branchCode, int submissionId, String liabilityId) {
    CRSAgrDvlpInc existingEntity = crsAgriDvIncRepository.findByDateAndBranchNumberAndCrsAgrDvlpId(liabilityDate, branchCode, liabilityId);
    if (existingEntity == null) {
        CRSAgrDvlpInc newEntity = setAgriDvlpIncEntity(dataList, liabilityDate, branchCode, submissionId);
        crsAgriDvIncRepository.save(newEntity);
    } else {
        existingEntity = setAgriDvlpIncEntity(dataList, liabilityDate, branchCode, submissionId);
        crsAgriDvIncRepository.save(existingEntity);
    }
}

// Method to handle HousDvl
private void processHousDvl(List<String> dataList, Date liabilityDate, String branchCode, int submissionId, String liabilityId) {
    CRSHousDvlpInc existingEntity = crsHousDvIncRepository.findByDateAndBranchNumberAndCrsHousDvlpId(liabilityDate, branchCode, liabilityId);
    if (existingEntity == null) {
        CRSHousDvlpInc newEntity = setHousDvlpIncEntity(dataList, liabilityDate, branchCode, submissionId);
        crsHousDvIncRepository.save(newEntity);
    } else {
        existingEntity = setHousDvlpIncEntity(dataList, liabilityDate, branchCode, submissionId);
        crsHousDvIncRepository.save(existingEntity);
    }
}

I am getting error on this methods 

setInduDvlpIncEntity
setInfraDvlpIncEntity
setInfraDvlpIncEntity
setAgriDvlpIncEntity

The method setAgriDvlpIncEntity(List<String>, Date, String, int) is undefined for the type RW10ServiceImplJava(67108964)
what is this make the simple use of this dont make things to complicated and read the code again and cross check again if any methods missing or anything.

