// IF Data Present IN RW-03
if (rw03ListData.size() > 0) {
    for (List<String> dataList : rw03ListData) {

        // Insert the first row with the actual data from dataList
        CrsLiability crsLiability = new CrsLiability();
        crsLiability.setLiabilityParticulars("Contiongent liability As per As-29");
        crsLiability.setLiabilityLstYs(dataList.get(0));
        crsLiability.setLiabilityAddition(dataList.get(1));
        crsLiability.setLiabilityReversal(dataList.get(2));
        crsLiability.setLiabilityBranch((String) loginuserData.get("branch_code"));
        crsLiability.setLiabilityDate(liabilityDate);
        crsLiability.setReportMasterListIdfk(submissionId);
        crsLiability.setCrsLiabilityId("1");

        log.info("Entity Data for Insert: " + crsLiability);
        CrsLiability entityData = crsLiabilityRepository.save(crsLiability);
        log.info("CrsLiability saved to database: " + entityData.getCrsLiabilityId());

        // Insert 6 more rows with 0 values for particular fields
        for (int i = 1; i <= 6; i++) {
            CrsLiability zeroLiability = new CrsLiability();
            zeroLiability.setLiabilityParticulars("Contiongent liability As per As-29");
            zeroLiability.setLiabilityLstYs("0");
            zeroLiability.setLiabilityAddition("0");
            zeroLiability.setLiabilityReversal("0");
            zeroLiability.setLiabilityBranch((String) loginuserData.get("branch_code"));
            zeroLiability.setLiabilityDate(liabilityDate);
            zeroLiability.setReportMasterListIdfk(submissionId);
            zeroLiability.setCrsLiabilityId(String.valueOf(i + 1));  // Set unique ID for each row

            log.info("Inserting zero-value row: " + zeroLiability);
            CrsLiability zeroEntity = crsLiabilityRepository.save(zeroLiability);
            log.info("Zero-value CrsLiability saved to database: " + zeroEntity.getCrsLiabilityId());
        }
    }
}