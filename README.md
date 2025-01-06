private CRSInduDvlpInc setInduDvlpIncEntity(List<String> dataList, Date liabilityDate, String branchCode, int submissionId) {
    CRSInduDvlpInc entity = new CRSInduDvlpInc();
    entity.setBranchNumber(branchCode);
    entity.setDate(liabilityDate);
    entity.setOther(dataList.get(0)); // Example for other fields, adjust as needed
    entity.setProcessingFee(dataList.get(1));
    entity.setTotal(dataList.get(2));
    entity.setTotalAdvances(dataList.get(3));
    entity.setReportMasterListIdFk(String.valueOf(submissionId)); // Example of setting ID
    return entity;
}


xxx


private CRSInfraDvlpInc setInfraDvlpIncEntity(List<String> dataList, Date liabilityDate, String branchCode, int submissionId) {
    CRSInfraDvlpInc entity = new CRSInfraDvlpInc();
    entity.setBranchNumber(branchCode);
    entity.setDate(liabilityDate);
    entity.setOther(dataList.get(0));
    entity.setProcessingFee(dataList.get(1));
    entity.setTotal(dataList.get(2));
    entity.setTotalAdvances(dataList.get(3));
    entity.setReportMasterListIdFk(String.valueOf(submissionId));
    return entity;
}

xxx

private CRSAgrDvlpInc setAgriDvlpIncEntity(List<String> dataList, Date liabilityDate, String branchCode, int submissionId) {
    CRSAgrDvlpInc entity = new CRSAgrDvlpInc();
    entity.setBranchNumber(branchCode);
    entity.setDate(liabilityDate);
    entity.setOther(dataList.get(0));
    entity.setProcessingFee(dataList.get(1));
    entity.setTotal(dataList.get(2));
    entity.setTotalAdvances(dataList.get(3));
    entity.setReportMasterListIdFk(String.valueOf(submissionId));
    return entity;
}

xxxx

private CRSHousDvlpInc setHousDvlpIncEntity(List<String> dataList, Date liabilityDate, String branchCode, int submissionId) {
    CRSHousDvlpInc entity = new CRSHousDvlpInc();
    entity.setBranchNumber(branchCode);
    entity.setDate(liabilityDate);
    entity.setOther(dataList.get(0));
    entity.setProcessingFee(dataList.get(1));
    entity.setTotal(dataList.get(2));
    entity.setTotalAdvances(dataList.get(3));
    entity.setReportMasterListIdFk(String.valueOf(submissionId));
    return entity;
}


