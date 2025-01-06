
public ResponseEntity<ResponseVO<Boolean>> saveStaticDetails(Map<String, Object> map) {
    // Extracting additional details from the input map
    Map<String, Object> data = (Map<String, Object>) map.get("data");

    // Extracting logged user data
    Map<String, String> loginUserData = (Map<String, String>) map.get("user");

    // Getting submission ID from the data map
    int submissionId = (int) data.get("submissionId");

    // Initializing response object
    ResponseVO<Boolean> responseVO = new ResponseVO<>();
    Map<String, Object> resultDataMap = new HashMap<>();

    // List of ROW data (List<List<String>> format)
    List<List<String>> mainList = (List<List<String>>) data.get("value");

    try {
        // Parse the quarterEndDate from user data
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date liabilityDate = dateFormat.parse(loginUserData.get("quarterEndDate"));

        log.info("Liability Date: " + liabilityDate);
        log.info("Main List Data: " + mainList);

        // Ensure the row data corresponds to the proper entities:
        for (int i = 0; i < mainList.size(); i++) {
            List<String> dataList = mainList.get(i);
            String branchCode = loginUserData.get("branch_code");
            String liabilityId = String.valueOf(Integer.parseInt(dataList.get(7)));

            // Process data based on the row index
            if (i == 0) {
                processInduDvl(dataList, liabilityDate, branchCode, submissionId, liabilityId);
            } else if (i == 1) {
                processInfraDvl(dataList, liabilityDate, branchCode, submissionId, liabilityId);
            } else if (i == 2) {
                processAgriDvl(dataList, liabilityDate, branchCode, submissionId, liabilityId);
            } else if (i == 3) {
                processHousDvl(dataList, liabilityDate, branchCode, submissionId, liabilityId);
            }
        }

        // If all records processed successfully
        responseVO.setMessage("All data updated successfully.");
        responseVO.setResult(true);
        responseVO.setStatusCode(HttpStatus.OK.value());

    } catch (ParseException e) {
        log.error("Error parsing date: " + e.getMessage());
        responseVO.setMessage("Invalid date format provided.");
        responseVO.setResult(false);
        responseVO.setStatusCode(HttpStatus.BAD_REQUEST.value());

    } catch (RuntimeException e) {
        log.error("Exception occurred: " + e.getMessage());
        responseVO.setMessage("An unexpected error occurred.");
        responseVO.setResult(false);
        responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    // Return response in the specified format
    return new ResponseEntity<>(responseVO, HttpStatus.valueOf(responseVO.getStatusCode()));
}

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