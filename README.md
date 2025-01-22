@Transactional
@Override
public Map<String, Object> saveRowData(Map<String, Object> map) {
    Map<String, Object> result = new HashMap<>();
    try {
        // Extract data and user details from the input map
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        Map<String, String> loginUserData = (Map<String, String>) map.get("user");

        log.info("Data Map: {}", data);
        log.info("User Map: {}", loginUserData);

        // Extract submission ID
        String submissionId = data.get("submissionId").toString();

        // Extract the list of row data (List<List<String>> format)
        List<String> dataList = (List<String>) data.get("value");

        // Parse quarter end date and branch code from user data
        Date quarterEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(loginUserData.get("quarterEndDate"));
        String branchCode = loginUserData.get("branch_code");

        log.info("Processing DataList: {}", dataList);

        // Extract row ID from 7th position
        String rowIdStr = dataList.get(7).trim();

        if (rowIdStr.isEmpty()) {
            // If row ID is empty or null, insert new data
            log.info("Inserting new data as rowId is empty or null");
            TAR_MODE newEntity = setEntity(null, dataList, quarterEndDate, branchCode, submissionId);
            tarModeRepository.save(newEntity);

            result.put("RowId", newEntity.getModeid());
            result.put("status", true);
            log.info("New data inserted with RowId: {}", newEntity.getModeid());
        } else {
            // Parse row ID and check if the record exists
            int rowId = Integer.parseInt(rowIdStr);

            if (tarModeRepository.existsByModeid(rowId)) {
                // If record exists, update the data
                log.info("Updating existing data for RowId: {}", rowId);
                TAR_MODE existingEntity = tarModeRepository.findById(rowId).orElseThrow(() -> 
                    new RuntimeException("Record not found with RowId: " + rowId)
                );
                TAR_MODE updatedEntity = setEntity(existingEntity, dataList, quarterEndDate, branchCode, submissionId);
                tarModeRepository.save(updatedEntity);

                result.put("RowId", updatedEntity.getModeid());
                result.put("status", true);
                log.info("Data updated for RowId: {}", rowId);
            } else {
                // If record does not exist, insert new data with provided rowId
                log.info("No existing data for RowId: {}. Inserting new data.", rowId);
                TAR_MODE newEntity = setEntity(null, dataList, quarterEndDate, branchCode, submissionId);
                newEntity.setModeid(rowId); // Set provided RowId as modeid
                tarModeRepository.save(newEntity);

                result.put("RowId", newEntity.getModeid());
                result.put("status", true);
                log.info("New data inserted with provided RowId: {}", rowId);
            }
        }
    } catch (ParseException e) {
        log.error("Error parsing date: {}", e.getMessage());
        result.put("status", false);
    } catch (RuntimeException e) {
        log.error("Unexpected error: {}", e.getMessage());
        result.put("status", false);
    }

    return result;
}


xxxx

private TAR_MODE setEntity(TAR_MODE entity, List<String> dataList, Date quarterEndDate, String branchCode, String submissionId) {
    // If no existing entity, create a new one
    if (entity == null) {
        entity = new TAR_MODE();
    }

    try {
        // Parse and set the payment date
        entity.setMode_payment_dt(new SimpleDateFormat("dd/MM/yyyy").parse(dataList.get(0)));
    } catch (ParseException e) {
        log.info("Invalid payment date '{}'. Setting as null.", dataList.get(0));
        entity.setMode_payment_dt(null);
    }

    // Map data fields directly
    entity.setMode_nature(dataList.get(1));           // Nature of the payment
    entity.setMode_payee(dataList.get(2));            // Payee name
    entity.setMode_pan(dataList.get(3));              // PAN number
    entity.setMode_typebgl(dataList.get(4).equalsIgnoreCase("Debited / Credited to P&L BGL") ? "1" : "2");
    entity.setMode_amt(parseAmount(dataList.get(5))); // Safely parse and set the amount
    entity.setMode_reason(dataList.get(6));           // Reason for payment
    entity.setMode_branch(branchCode);                // Branch code
    entity.setMode_date(quarterEndDate);              // Quarter end date
    entity.setReportSubmissionId(submissionId);       // Submission ID

    return entity;
}