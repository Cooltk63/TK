public Map<String, Object> getSC10Sftp(Map<String, Object> map) {
    log.info("Inside SC10DaoImpl getSaveBySftp");

    Map<String, Object> updatedTabData = new HashMap<>();

    // Extract input parameters
    String quarterEndDate = (String) map.get("qed");
    String circleCode = (String) map.get("circleCode");
    String reportName = (String) map.get("reportName");

    log.info("Quarter End Date: " + quarterEndDate);

    // Extract year, month, and day from quarterEndDate (Expected format: dd/MM/yyyy)
    String[] dateParts = quarterEndDate.split("/");
    if (dateParts.length != 3) {
        updatedTabData.put("message", "Invalid date format for quarterEndDate. Expected dd/MM/yyyy.");
        updatedTabData.put("status", false);
        return updatedTabData;
    }

    String yyyy = dateParts[2], mm = dateParts[1], dd = dateParts[0];
    String sessionDate = yyyy + mm + dd;  // Format: YYYYMMDD
    String qDate = dd + mm + yyyy;  // Format: DDMMYYYY

    // Fetch branch code for given circleCode and reportName
    String branchCode = ccdpSftpDao.getBrcodeforsftp(circleCode, reportName);
    log.info("Branch Code: " + branchCode);

    try {
        // Load file path from properties file (CHANGE THIS FOR PRODUCTION)
        PropertiesConfiguration config = new PropertiesConfiguration("common.properties");
        String mainPath = config.getString("ReportDirCCDP");

        // Construct file path dynamically (CHANGE THIS FOR PRODUCTION)
        String filePath = mainPath + qDate + "/IFAMS_SCH10_" + sessionDate + "_" + circleCode + ".txt";
        log.info("File Path: " + filePath);

        // Expected row numbers in the file for mapping
        int[] rowNumber = {1, 3, 4, 36, 5, 6, 7, 37, 9, 33, 10, 11, 12, 13, 14, 18, 34,
                38, 19, 20, 21, 39, 22, 40, 24, 25, 26, 27, 28,
                29, 30, 31, 35, 32};
        int rowNumberCount = 0;

        // Read the file using Java 8 Stream API for efficiency
        List<String> lines;
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            lines = stream.filter(line -> !line.trim().isEmpty())
                          .collect(Collectors.toList());
        }

        if (lines.isEmpty()) {
            updatedTabData.put("message", "File is empty or has only blank lines.");
            updatedTabData.put("status", false);
            return updatedTabData;
        }

        SC10 sc10 = new SC10();
        Map<Integer, String[]> rowData = new HashMap<>();
        String timeStamp = "";

        // Process each line
        for (String line : lines) {
            log.info("Processing line: " + line);
            String[] columns = line.split("\\|");

            // Validate that each line has required number of columns
            if (columns.length < 2) {  // Adjust this based on expected columns per row
                throw new IllegalArgumentException("Invalid data format in file: " + filePath + 
                        " - Each row must have at least 2 columns but found " + columns.length);
            }

            // Extract timestamp if present
            if ("Generated at".equalsIgnoreCase(columns[0].trim())) {
                timeStamp = columns[1].trim();
                updatedTabData.put("FILETIMESTAMP", timeStamp);
                continue;
            }

            // Assign row data to respective row numbers
            int rowNum = rowNumber[rowNumberCount++];
            rowData.put(rowNum, columns);
        }

        // Define field names corresponding to SC10.java variables
        String[] fieldNames = {
                "stcNstaff", "offResidenceA", "otherPremisesA", "electricFitting",
                "totalA", "computers", "compSoftwareInt", "compSoftwareNonint",
                "compSoftwareTotal", "motor", "offResidenceB", "stcLho",
                "otherPremisesB", "otherMachineryPlant", "totalB", "totalFurnFix",
                "landNotRev", "landRev", "landRevEnh", "offBuildNotRev",
                "offBuildRev", "offBuildRevEnh", "residQuartNotRev", "residQuartRev",
                "residQuartRevEnh", "premisTotal", "revtotal", "totalC",
                "premisesUnderCons", "grandTotal"
        };

        // Iterate and set values dynamically using Reflection
        for (int i = 0; i < rowNumber.length; i++) {
            int rowNum = rowNumber[i];

            if (!rowData.containsKey(rowNum)) {
                log.warn("Skipping row " + rowNum + " as it's missing in the file.");
                continue;
            }

            String[] data = rowData.get(rowNum);

            for (int j = 0; j < fieldNames.length; j++) {
                try {
                    String setterName = "set" + capitalize(fieldNames[j]) + rowNum;
                    Method setterMethod = SC10.class.getMethod(setterName, String.class);

                    // Set value only if data is available, else assign an empty string
                    setterMethod.invoke(sc10, data.length > j ? data[j].trim() : "");

                } catch (NoSuchMethodException e) {
                    log.warn("No setter found: " + fieldNames[j] + rowNum);
                } catch (Exception e) {
                    log.error("Error setting value for: " + fieldNames[j] + rowNum, e);
                }
            }
        }

        // Update timestamp in CCDPFiletime Table
        int updateTime = ccdpSftpDao.updateCCDPFiletime(timeStamp, circleCode, quarterEndDate, reportName);
        log.info("Timestamp Updated for CCDP_FILE_TIME: Status: " + updateTime);

        // Return response
        updatedTabData.put("sc10Data", sc10);
        updatedTabData.put("message", "Data successfully extracted and mapped");
        updatedTabData.put("status", true);
        updatedTabData.put("fileAndDataStatus", 1);

    } catch (IOException e) {
        updatedTabData.put("message", "Error reading file: " + e.getMessage());
        updatedTabData.put("fileAndDataStatus", 2);
        updatedTabData.put("status", false);
    } catch (IllegalArgumentException e) {
        updatedTabData.put("message", "Data format error: " + e.getMessage());
        updatedTabData.put("fileAndDataStatus", 2);
        updatedTabData.put("status", false);
    } catch (Exception e) {
        updatedTabData.put("message", "Unexpected error: " + e.getMessage());
        updatedTabData.put("fileAndDataStatus", 2);
        updatedTabData.put("status", false);
    }

    return updatedTabData;
}