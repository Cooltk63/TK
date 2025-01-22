@Transactional
    @Override
    public Map<String, Object> saveRowData(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>();

        try {
            // Extracting data and user details from the input map
            Map<String, Object> data = (Map<String, Object>) map.get("data");
            Map<String, String> loginUserData = (Map<String, String>) map.get("user");

            log.info("Data Map: {}"+ data);
            log.info("User Map: {}"+ loginUserData);

            // Extract submission ID from data
            String submissionId = data.get("submissionId").toString();

            // Extract the list of row data (List<List<String>> format)
            List<String> dataList = (List<String>) data.get("value");

            // Parse quarter end date and branch code from user data
            Date quarterEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(loginUserData.get("quarterEndDate"));
            String branchCode = loginUserData.get("branch_code");

                log.info("Processing DataList: {}"+ dataList);

            TAR_MODE insertEntity = setEntity(null, dataList, quarterEndDate, branchCode, submissionId);

                // Extract MODE_ID to check for existing records
//

            // IF ID NOT Provided or Empty at 7th Location of Data List
            if (dataList.get(7).trim().isEmpty() || dataList.get(6) == null) {

                log.info("Inside insertEntity RW-08ST");
                tarModeRepository.save(insertEntity);
                result.put("newId", insertEntity.getModeid());
                result.put("newRowNum", insertEntity.getModeid());
                result.put("status", true);
                return result;
            }
            else {
                int tarModId = Integer.parseInt(dataList.get(7));

                // Check if data exists for the given Mode ID
                if(tarModeRepository.existsByModeid(tarModId))
                {
                    log.info("Inside existed Data Entity");
                    TAR_MODE updateEntity = setEntity(null, dataList, quarterEndDate, branchCode, submissionId);
                    tarModeRepository.save(updateEntity);
                    result.put("newId", updateEntity.getModeid());
                    result.put("newRowNum", updateEntity.getModeid());
                    result.put("status", true);
                }
                else {
                    // ID not found, handle error
                    log.info("ID " + tarModId + " not found for update");
                    result.put("status", false);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
            log.info("Error parsing date: {}"+ e.getMessage());
            log.info("Error parsing date: {}"+ e.getCause());
            result.put("status", false);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.info("Unexpected error: {}"+ e.getMessage());
            result.put("status", false);
        }

        return result;
    }


 private TAR_MODE setEntity(TAR_MODE entity, List<String> dataList, Date quarterEndDate, String branchCode, String submissionId) {
        // If no existing entity, create a new one
        if (entity == null) {
            entity = new TAR_MODE();
        }

        try {
            // Parse and set the payment date
            entity.setMode_payment_dt(new SimpleDateFormat("dd/MM/yyyy").parse(dataList.get(0)));
        } catch (ParseException e) {
            log.info("Invalid payment date '{}'. Setting as null."+ dataList.get(0));
            entity.setMode_payment_dt(null);
        }

        // Map data fields directly
        entity.setMode_nature(dataList.get(1));           // Nature of the payment
        entity.setMode_payee(dataList.get(2));            // Payee name
        entity.setMode_pan(dataList.get(3));              // PAN number
        entity.setMode_typebgl(String.valueOf(dataList.get(4).equalsIgnoreCase("Debited / Credited to P&L BGL") ? 1:2));          // Type BGL
        // Safely parse and set the amount
        entity.setMode_amt(parseAmount(dataList.get(5)));

        entity.setMode_reason(dataList.get(6));           // Reason for payment
        entity.setMode_branch(branchCode);                // Branch code
        entity.setMode_date(quarterEndDate);              // Quarter end date
        entity.setReportSubmissionId(submissionId);       // Submission ID

//        entity.setModeid(Integer.parseInt(dataList.get(7)));  // For FE RowId

        return entity;
    }


    private int parseAmount(String amountStr) {
        try {
            return Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            log.info("Invalid amount '{}'. Defaulting to 0."+ amountStr);
            return 0;
        }
    }


I need the save method 
