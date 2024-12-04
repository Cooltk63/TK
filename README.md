if (currentStatus.equalsIgnoreCase("X") || currentStatus.equalsIgnoreCase("12") || 
    currentStatus.equalsIgnoreCase("10") || currentStatus.equalsIgnoreCase("11")) {

    // Initialize particulars and component list
    List<String> ParticularsEmptyData = Arrays.asList(
            "CHRG-OFFICE RENT",
            "CHRG-HOUSE RENT",
            "Charges -TELEPHONE & FAX",
            "CHARGES - ELECTRICITY & GAS",
            "CHARGES-REPAIRS- BANK PROPERTY"
    );

    List<String> CompList = Arrays.asList("10151", "10152", "10212", "10154", "10225");

    // Fetch existing data from ADJData table
    log.info("Getting Previous Quarter Data from ADJDATA: " + quarterEndDate + " branch_code: " + branch_code);
    List<String> ADJData = crsAdjmocRepository.getAdjData(quarterEndDate, branch_code);

    log.info("ADJData List Size: " + ADJData.size());
    log.info("ADJ DATA WE HAVE: " + ADJData);

    for (int i = 0; i < 5; i++) {
        log.info("Processing Particular: " + ParticularsEmptyData.get(i) + ", Component: " + CompList.get(i));

        // Check if a row already exists for the particular component
        CRS_Adjmoc existingRow = crsAdjmocRepository.findByAdjmocdescriptionsAndAdjmocpnlcompcode(
                ParticularsEmptyData.get(i), CompList.get(i));

        if (existingRow != null) {
            // Update existing row
            log.info("Row exists, updating values...");
            existingRow.setActualexpensPL(new BigDecimal(ADJData.isEmpty() ? "0" : ADJData.get(i)));
            existingRow.setAdjmocamount(new BigDecimal(0));
            existingRow.setRemarks("Updated based on ADJData");
            crsAdjmocRepository.save(existingRow);
        } else {
            // Insert new row
            log.info("Row does not exist, inserting new row...");
            CRS_Adjmoc zeroLiability = new CRS_Adjmoc();
            zeroLiability.setAdjmocdescriptions(ParticularsEmptyData.get(i));
            zeroLiability.setAdjmocpnlcompcode(CompList.get(i));
            zeroLiability.setAdjmoccglno("0");
            zeroLiability.setEstimatedmonthlyexpense(new BigDecimal(0));
            zeroLiability.setLikelyexpense6months(new BigDecimal(0));
            zeroLiability.setActualexpensPL(new BigDecimal(ADJData.isEmpty() ? "0" : ADJData.get(i)));
            zeroLiability.setAdjmocamount(new BigDecimal(0));
            zeroLiability.setRemarks("Inserted during screen load");
            zeroLiability.setAdjmocdate(liabilityDate);
            zeroLiability.setAdjmochead("EXPENSES A/C");
            zeroLiability.setAdjmocbranch((String) loginUserData.get("branch_code"));
            zeroLiability.setReportmasterFK(submissionId);
            zeroLiability.setAdjmocsubhead("EXPENSES");
            crsAdjmocRepository.save(zeroLiability);
        }
    }
}




@Query("SELECT a FROM CRS_Adjmoc a WHERE a.adjmocdescriptions = :description AND a.adjmocpnlcompcode = :compCode")
CRS_Adjmoc findByAdjmocdescriptionsAndAdjmocpnlcompcode(@Param("description") String description, @Param("compCode") String compCode);

