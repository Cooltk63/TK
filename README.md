// IF Data Present IN RW-03
            if(rw03ListData.size()>0)
            {
        for(List<String> dataList : rw03ListData){

             // Setting the List Data to Entity
            // CrsLiability crsLiability = setEntity( dataList, (String) loginuserData.get("quarterEndDate"), (String) loginuserData.get("branch_code"), (String) data.get("submissionId"));
            CrsLiability crsLiability=new CrsLiability();

            for(String i: dataList)
            {
                log.info("DataList Data :"+i);
            }
            // Set Particulars for 1st Index Row When RW-03 Data Received
            crsLiability.setLiabilityParticulars("Contiongent liability As per As-29");
            crsLiability.setLiabilityLstYs(dataList.get(0));
            crsLiability.setLiabilityAddition(dataList.get(1));
            crsLiability.setLiabilityReversal(dataList.get(2));


            // Query Had 4 Param to Select Getting Only 2 Param
            //crsLiability.setLiabilityCurYr(dataList.get(2));

            crsLiability.setLiabilityBranch((String) loginuserData.get("branch_code"));
            crsLiability.setLiabilityDate(liabilityDate);
            crsLiability.setReportMasterListIdfk(submissionId);
            crsLiability.setCrsLiabilityId("1");


                log.info("Entity Data for Insert: " + crsLiability);
                CrsLiability entityData = crsLiabilityRepository.save(crsLiability);
                log.info("CrsLiability saved to database" + entityData.getCrsLiabilityId());

            }
        }
