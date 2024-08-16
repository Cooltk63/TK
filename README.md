public ResponseEntity update(Map<String, Object> map) {

        ResponseVO<String> responseVO = new ResponseVO();
        // Data Receive here
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        Map<String, Object> loginuserData = (Map<String, Object>) map.get("user");

        CrsStndAssets entity = new CrsStndAssets();
        List<String> dataList = (List<String>) data.get("value");

        // Condition Check Variable INFRA or NONINFRA
        String infraNonInfraList = dataList.get(1);

        try {

            // Assign data to Bean for Save based on ID is Present or Null
            if (!dataList.get(5).trim().isEmpty()) {
                log.info("Received ID for Update: " + dataList.get(5) + " Is ID Existed :" + crsStndAssetsRepository.existsById(Integer.valueOf(dataList.get(5))));

                // Assigning ID for Update Row
                entity.setStndassetsseq(Integer.parseInt(dataList.get(5)));
            }
            // Insert the Branch while 1st Insert
            entity.setStndastsbranch((String) loginuserData.get("branch_code"));

            // Insert the QED while 1st Insert
            entity.setStndastsdate((String) loginuserData.get("qed"));

            // 1st Element :: nameOfBorrowerList
            entity.setStndastsnameofborrower(dataList.get(0));

            //2nd Element :: infraNonInfraList
            entity.setStndastsinfranoninfra(dataList.get(1));

            //3rd Element Based on Condition
            if (infraNonInfraList.equalsIgnoreCase("INFRA")) {

                //:: infraAccounts2YearsList
                entity.setStndastsinfraaccts2YRS(dataList.get(2));

                // :: infraWithin2YearsList
                entity.setStndastsinfrawithin2YRS(dataList.get(3));

            } else if (infraNonInfraList.equalsIgnoreCase("NONINFRA")) {

                // :: infraAccounts2YearsList
                entity.setStndastsnoninfraaccts1YR(dataList.get(2));

                // :: infraWithin2YearsList
                entity.setStndastsnoninfrawithin1YR(dataList.get(3));
            }

            log.info("Entity Data: " + entity);

            // Method for updating the data in crs_stnd_assets table
            CrsStndAssets result = crsStndAssetsRepository.save(entity);

            log.info("Result Received");

            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Data Inserted/Updated successfully");
            responseVO.setResult("true");
        } catch (RuntimeException e) {
            log.info("exception Occurred");
            responseVO.setResult("false");
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage("Exception Occurred" + e.getMessage());
        }

        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }
	
	This is my update/insert new row data into table method using jpa if i am sending the empty data in dataList.get(5) which is my ROWID for table which i need the condition if user sending the id in dataList.get(5) then it must check 1st is this id exists or not if exists then update values in table and return true with message data updated and if id is not exists then show error failed to update and return false but there is one catch i am using jpa if i am passing the wrong id in dataList.get(5) still inserting the new row in table how do i manage this if dataList.get(5)only empty or null or not anything inside this then and then only insert new row record and return true with message data inserted give me thie code for my aboce requirement
