Public ResponseEntity update(Map<String, Object> map) {

    ResponseVO<String> responseVO = new ResponseVO();

    // Data Receive here
    Map<String, Object> data = (Map<String, Object>) map.get("data");
    Map<String, Object> loginuserData = (Map<String, Object>) map.get("user");

    CrsStndAssets entity = new CrsStndAssets();
    List<String> dataList = (List<String>) data.get("value");

    // Condition Check Variable INFRA or NONINFRA
    String infraNonInfraList = dataList.get(1);

    try {

        // Check if ID is empty or null for insert scenario
        if (dataList.get(5).trim().isEmpty() || dataList.get(5) == null) {
            log.info("Inserting new row");

            // Insert the Branch while 1st Insert
            entity.setStndastsbranch((String) loginuserData.get("branch_code"));

            // Insert the QED while 1st Insert
            entity.setStndastsdate((String) loginuserData.get("qed"));

            // Assign data based on dataList
            entity.setStndastsnameofborrower(dataList.get(0));
            entity.setStndastsinfranoninfra(dataList.get(1));

            if (infraNonInfraList.equalsIgnoreCase("INFRA")) {
                entity.setStndastsinfraaccts2YRS(dataList.get(2));
                entity.setStndastsinfrawithin2YRS(dataList.get(3));
            } else if (infraNonInfraList.equalsIgnoreCase("NONINFRA")) {
                entity.setStndastsnoninfraaccts1YR(dataList.get(2));
                entity.setStndastsnoninfrawithin1YR(dataList.get(3));
            }

            log.info("Entity Data for Insert: " + entity);

            // Save the new entity
            CrsStndAssets savedEntity = crsStndAssetsRepository.save(entity);

            log.info("New row inserted successfully: " + savedEntity);

            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Data Inserted successfully");
            responseVO.setResult("true");
            return new ResponseEntity<>(responseVO, HttpStatus.OK);
        } else {
            // ID is provided, check for existence and update
            int id = Integer.parseInt(dataList.get(5));
            log.info("Received ID for Update: " + id + " Is ID Existed :" + crsStndAssetsRepository.existsById(id));

            if (crsStndAssetsRepository.existsById(id)) {
                // Update existing row
                entity.setStndassetsseq(id);  // Set the ID for update

                // ... (rest of the update logic for existing row)

                log.info("Entity Data for Update: " + entity);

                CrsStndAssets updatedEntity = crsStndAssetsRepository.save(entity);

                log.info("Data Updated successfully: " + updatedEntity);

                responseVO.setStatusCode(HttpStatus.OK.value());
                responseVO.setMessage("Data Updated successfully");
                responseVO.setResult("true");
                return new ResponseEntity<>(responseVO, HttpStatus.OK);
            } else {
                // ID not found, handle error
                log.info("ID " + id + " not found for update");

                responseVO.setStatusCode(HttpStatus.BAD_REQUEST.value());
                responseVO.setMessage("Invalid ID provided. Record not found.");
                responseVO.setResult("false");
                return new ResponseEntity<>(responseVO, HttpStatus.BAD_REQUEST);
            }
        }

    } catch (RuntimeException e) {
        log.error("Exception Occurred", e);
        responseVO.setResult("false");
        responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseVO.setMessage("Exception Occurred: " + e.getMessage());
        return new ResponseEntity<>(responseVO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
