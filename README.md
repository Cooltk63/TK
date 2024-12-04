if (currentStatus.equalsIgnoreCase("X") ||currentStatus.equalsIgnoreCase("12") || currentStatus.equalsIgnoreCase("10") ||currentStatus.equalsIgnoreCase("11")) 
        {

                // Inserting Empty Data 1st time Screen Loading
                List<String> ParticularsEmptyData = new ArrayList<>();
                ParticularsEmptyData.add(0, "CHRG-OFFICE RENT");
                ParticularsEmptyData.add(1, "CHRG-HOUSE RENT");
                ParticularsEmptyData.add(2, "Charges -TELEPHONE & FAX");
                ParticularsEmptyData.add(3, "CHARGES - ELECTRICITY & GAS");
                ParticularsEmptyData.add(4, "CHARGES-REPAIRS- BANK PROPERTY");

                // For inserting Empty Data while Screen Loading
                List<String> CompList = new ArrayList<>();
                CompList.add(0, "10151");
                CompList.add(1, "10152");
                CompList.add(2, "10212");
                CompList.add(3, "10154");
                CompList.add(4, "10225");

                // Getting ADJ Data List
                log.info("Getting Previous Quarter Data from ADJDATA :"+quarterEndDate +" branch_code :"+branch_code);
                List<String> ADJData=crsAdjmocRepository.getAdjData(quarterEndDate,branch_code);

                log.info("ADJData List Size :"+ADJData.size());
                log.info("ADJ DATA WE HAVE :"+ADJData);


                // Insert 6 more rows with 0 values for particular fields
                for (int i = 0; i < 5; i++) {
                    log.info("submissionId for Empty Insert :" + submissionId);

                    CRS_Adjmoc zeroLiability = new CRS_Adjmoc();

//                    zeroLiability.setAdjmocid(i + 1);
                    zeroLiability.setAdjmocdescriptions(ParticularsEmptyData.get(i));
                    zeroLiability.setAdjmocpnlcompcode(CompList.get(i));
                    zeroLiability.setAdjmoccglno("0");
                    zeroLiability.setEstimatedmonthlyexpense(new BigDecimal(0));
                    zeroLiability.setLikelyexpense6months(new BigDecimal(0));

                    log.info("ADJData Empty :"+ADJData.isEmpty());
                    if(ADJData.isEmpty())
                    {
                        log.info("inside if block insert ADJData Values");
                        zeroLiability.setActualexpensPL(new BigDecimal(0));
                    }
                    else {
                        log.info("inside else block set to 0 Values while Insert");
                        zeroLiability.setActualexpensPL(new BigDecimal(ADJData.get(i)));
                    }


                    zeroLiability.setAdjmocamount(new BigDecimal(0));
                    zeroLiability.setRemarks(" ");

                    zeroLiability.setAdjmocdate(liabilityDate); // SAME
                    zeroLiability.setAdjmochead("EXPENSES A/C");  // SAME
                    zeroLiability.setAdjmocbranch((String) loginUserData.get("branch_code"));  //SAME
                    zeroLiability.setReportmasterFK(submissionId); //SAME
                    zeroLiability.setAdjmocsubhead("EXPENSES"); //SAME

                    log.info("Inserting zero-value row: " + zeroLiability);

                    // Inserting Empty Row Data in DB
                    crsAdjmocRepository.save(zeroLiability);

                }
            }


            In this method logic on List<String> ADJData=crsAdjmocRepository.getAdjData(quarterEndDate,branch_code); returns ths 5 values which fetched from CRS_ADJData tables  and inserted into the current CRS_ADJMOC table i wanted the whenever values inside that adjdata is changed or modified same values inside my CRS_ADJMOC table which previously inserted or maybe not insetred while 1st time get updated as per the adj_Data table values. do not change anything other than my requirement
