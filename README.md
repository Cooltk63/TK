@Transactional
@Override
public Map<String, Object> saveRowData(Map<String, Object> map) {
    Map<String, Object> result = new HashMap<>();

    try {
        // Extracting data and user details from the input map
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        Map<String, String> loginUserData = (Map<String, String>) map.get("user");

        log.info("Data Map: {}", data);
        log.info("User Map: {}", loginUserData);

        // Extract submission ID from data
        String submissionId = data.get("submissionId").toString();

        // Extract the list of row data (List<List<String>> format)
        List<List<String>> mainList = (List<List<String>>) data.get("value");

        // Parse quarter end date and branch code from user data
        Date quarterEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(loginUserData.get("quarterEndDate"));
        String branchCode = loginUserData.get("branch_code");

        log.info("Quarter End Date: {}", quarterEndDate);
        log.info("Main List Data: {}", mainList);

        // Process each row in the main list
        for (List<String> dataList : mainList) {
            if (dataList.size() < 9) {
                log.warn("Skipping incomplete data: {}", dataList);
                continue; // Skip incomplete data
            }

            log.info("Processing DataList: {}", dataList);

            // Extract MODE_ID to check for existing records
            int tarModId = Integer.parseInt(dataList.get(8));

            // Check if data exists for the given submission ID
            TAR_MODE existingEntity = tarModeRepository.findByReportSubmissionId(submissionId);

            // Insert new data if no existing record, else update
            if (existingEntity == null) {
                log.info("No existing record found. Inserting new record.");
                existingEntity = setEntity(null, dataList, quarterEndDate, branchCode, submissionId);
            } else {
                log.info("Existing record found. Updating record.");
                existingEntity = setEntity(existingEntity, dataList, quarterEndDate, branchCode, submissionId);
            }

            // Save the entity (insert or update)
            tarModeRepository.save(existingEntity);
            log.info("Record saved successfully for ID: {}", tarModId);
        }

        // Successful processing
        result.put("Message", "All data processed successfully.");
        result.put("status", true);

    } catch (ParseException e) {
        log.error("Error parsing date: {}", e.getMessage());
        result.put("Message", "Invalid date format.");
        result.put("status", false);
    } catch (RuntimeException e) {
        log.error("Unexpected error: {}", e.getMessage());
        result.put("Message", "An error occurred while processing data.");
        result.put("status", false);
    }

    return result;
}


/**
 * Maps the data from the frontend to the TAR_MODE entity.
 * Used for both insert and update operations.
 *
 * @param entity        Existing entity for update; null for insert
 * @param dataList      List of string data received from the frontend
 * @param quarterEndDate Quarter end date from user data
 * @param branchCode    Branch code from user data
 * @param submissionId  Submission ID for the report
 * @return Fully populated TAR_MODE entity
 */
private TAR_MODE setEntity(TAR_MODE entity, List<String> dataList, Date quarterEndDate, String branchCode, String submissionId) {
    // If no existing entity, create a new one
    if (entity == null) {
        entity = new TAR_MODE();
    }

    try {
        // Parse and set the payment date
        entity.setMode_payment_dt(new SimpleDateFormat("dd/MM/yyyy").parse(dataList.get(0)));
    } catch (ParseException e) {
        log.warn("Invalid payment date '{}'. Setting as null.", dataList.get(0));
        entity.setMode_payment_dt(null);
    }

    // Map data fields directly
    entity.setMode_nature(dataList.get(1));           // Nature of the payment
    entity.setMode_payee(dataList.get(2));            // Payee name
    entity.setMode_pan(dataList.get(3));              // PAN number
    entity.setMode_typebgl(dataList.get(4));          // Type BGL

    // Safely parse and set the amount
    entity.setMode_amt(parseAmount(dataList.get(5)));

    entity.setMode_reason(dataList.get(6));           // Reason for payment
    entity.setMode_branch(branchCode);                // Branch code
    entity.setMode_date(quarterEndDate);              // Quarter end date
    entity.setReportSubmissionId(submissionId);       // Submission ID

    return entity;
}

/**
 * Helper method to safely parse amount values.
 * Defaults to 0 if the value is invalid.
 *
 * @param amountStr String value of the amount
 * @return Parsed integer amount or 0 if invalid
 */
private int parseAmount(String amountStr) {
    try {
        return Integer.parseInt(amountStr);
    } catch (NumberFormatException e) {
        log.warn("Invalid amount '{}'. Defaulting to 0.", amountStr);
        return 0;
    }
}