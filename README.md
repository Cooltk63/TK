// For Saving SBLC ::: HEDGE_INV
public ResponseEntity saveSblc(Map<String, Object> map) {
    try {
        // Extracting user and data details from input JSON
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        Map<String, String> loginUserData = (Map<String, String>) map.get("user");

        // Extract submissionId and data list values
        String submissionId = String.valueOf(data.get("submissionId"));
        List<String> dataList = (List<String>) data.get("value");
        log.info("Data List Received: " + dataList);

        // Parse dates from user data and data list
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date quarterEndDate = dateFormat.parse(loginUserData.get("quarterEndDate"));
        Date sblcDate = dateFormat.parse(dataList.get(0));

        // Create entity and set common values
        CRS_HedgeInv entity = new CRS_HedgeInv();
        entity.setHedgeinv_branch(loginUserData.get("branch_code"));
        entity.setHedgeinv_date(quarterEndDate);
        entity.setHedgeinv_invdate(sblcDate);
        entity.setHedgeinv_pan(dataList.get(1));       // PAN Number
        entity.setHedgeinv_customer(dataList.get(2));  // Customer ID
        entity.setHedgeinv_amount(dataList.get(3));    // Amount
        entity.setHedgeInvReporMastertFK(Integer.parseInt(submissionId)); // Submission ID

        // Check if a record with the same branch_code and quarterEndDate exists
        CRS_HedgeInv existingEntity = crsHedgeInvRepository
                .findByHedgeinv_branchAndHedgeinv_date(loginUserData.get("branch_code"), quarterEndDate);

        Map<String, Object> resultDataMap = new HashMap<>();
        ResponseVO<Map<String, Object>> responseVO = new ResponseVO<>();

        if (existingEntity != null) {
            // Record exists, update the existing record
            entity.setHedgeinvId(existingEntity.getHedgeinvId());
            CRS_HedgeInv updatedEntity = crsHedgeInvRepository.save(entity);

            // Prepare response for successful update
            resultDataMap.put("status", true);
            resultDataMap.put("newRowNum", updatedEntity.getHedgeinvId());
            resultDataMap.put("newId", updatedEntity.getHedgeinvId());

            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Data Updated successfully");
            responseVO.setResult(resultDataMap);
            log.info("Data Updated successfully: " + updatedEntity);
        } else {
            // No existing record found, perform a new insert
            CRS_HedgeInv newEntity = crsHedgeInvRepository.save(entity);

            // Prepare response for successful insert
            resultDataMap.put("status", true);
            resultDataMap.put("newRowNum", newEntity.getHedgeinvId());
            resultDataMap.put("newId", newEntity.getHedgeinvId());

            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Data Inserted successfully");
            responseVO.setResult(resultDataMap);
            log.info("New record inserted successfully: " + newEntity);
        }
        return new ResponseEntity<>(responseVO, HttpStatus.OK);

    } catch (ParseException e) {
        log.error("Date Parsing Exception: ", e);
        throw new RuntimeException("Date parsing failed", e);
    } catch (RuntimeException e) {
        log.error("Unexpected Exception: ", e);
        
        ResponseVO<String> errorResponse = new ResponseVO<>();
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage("Exception Occurred: " + e.getMessage());
        errorResponse.setResult("false");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}