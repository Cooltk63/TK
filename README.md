public Map<String, Object> getSC10Sftp(Map<String, Object> map) {
    log.info("Inside SC10DaoImpl Reading the .TXT File");
    Map<String, Object> updatedTabData = new HashMap<>();

    // Extract input parameters from the map
    String quarterEndDate = (String) map.get("qed");
    String circleCode = (String) map.get("circleCode");
    String reportName = (String) map.get("reportName");

    log.info("Quarter End Date: " + quarterEndDate);

    // Extract year, month, and day from quarterEndDate (format: dd/MM/yyyy)
    String[] dateParts = quarterEndDate.split("/");
    String yyyy = dateParts[2];
    String mm = dateParts[1];
    String dd = dateParts[0];

    // Generate required date formats
    String sessionDate = yyyy + mm + dd;  // Format: YYYYMMDD
    String qDate = dd + mm + yyyy;        // Format: DDMMYYYY

    log.info("Session Date: " + sessionDate);
    log.info("Fetching file...");

    try {
        PropertiesConfiguration config = new PropertiesConfiguration("common.properties");

        String mainPath = config.getProperty("ReportDirIFAMS").toString();
        String filePath = mainPath + qDate + "/IFAMS_SCH10_" + sessionDate + "_" + circleCode + ".txt";

        log.info("File Reading Path : " + mainPath);
        log.info("File Received Path: " + filePath);

        int[] rowNumber = {1, 3, 4, 36, 5, 6, 7, 37, 9, 33, 10, 11, 12, 13, 14, 18, 34,
                38, 19, 20, 21, 39, 22, 40, 24, 25, 26, 27, 28,
                29, 30, 31, 35, 32};
        int rowNumberCount = 0;

        List<String> lines = new ArrayList<>();

        // Read the file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        }

        SC10 sc10 = new SC10();
        Map<Integer, String[]> rowData = new HashMap<>();
        String timeStamp = "";

        for (String line : lines) {
            String[] columns = line.split("\\|");

            for (int i = 0; i < columns.length; i++) {
                if (columns[i] == null || columns[i].trim().isEmpty()) {
                    columns[i] = "0";
                }
            }

            if (columns[0].trim().equalsIgnoreCase("Generated at")) {
                timeStamp = columns[1].trim();
                log.info("Time Stamp Extracted from .txt File ::" + timeStamp);
                updatedTabData.put("FILETIMESTAMP", timeStamp);
                continue;
            }

            int rowNum = rowNumber[rowNumberCount++];
            rowData.put(rowNum, columns);
        }

        // Field names matching SC10.java (without row number)
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

        // ✅ SAFE WHITELIST: Build allowed methods in advance
        Map<String, Method> allowedMethods = new HashMap<>();

        for (int row : rowNumber) {
            for (String field : fieldNames) {
                String methodName = "set" + capitalize(field) + row;
                try {
                    Method method = SC10.class.getMethod(methodName, String.class);
                    allowedMethods.put(methodName, method);  // only whitelisted methods allowed
                } catch (NoSuchMethodException e) {
                    log.warn("No such method found (may be unused): " + methodName);
                }
            }
        }

        // Sort the rows to maintain order
        List<Integer> sortedRows = new ArrayList<>(rowData.keySet());
        Collections.sort(sortedRows);

        // Process each row safely
        for (int row : sortedRows) {
            log.info("Processing row: " + row);
            if (!rowData.containsKey(row)) {
                log.info("Skipping missing row " + row);
                continue;
            }

            String[] data = rowData.get(row);

            for (int index = 1; index <= 30; index++) {
                try {
                    String methodName = "set" + capitalize(fieldNames[index - 1]) + row;
                    Method setterMethod = allowedMethods.get(methodName);

                    if (setterMethod != null) {
                        setterMethod.invoke(sc10, data[index].trim());
                    } else {
                        log.warn("Unsafe or unapproved setter skipped: " + methodName);
                    }
                } catch (Exception e) {
                    log.error("Error setting value using method for row " + row + ": " + e.getMessage());
                }
            }
        }

        // Update timestamp in CCDPFiletime Table
        log.info("Updating CCDPFiletime with timestamp: " + timeStamp);
        int updateTime = ccdpSftpDao.updateCCDPFiletime(timeStamp, circleCode, quarterEndDate, reportName);
        log.info("Timestamp update status: " + updateTime);

        updatedTabData.put("sc10Data", sc10);
        updatedTabData.put("message", "Data received from IFAMS and imported successfully");
        updatedTabData.put("status", true);
        updatedTabData.put("fileAndDataStatus", 1);

    } catch (IOException | DataAccessException | ConfigurationException e) {
        updatedTabData.put("message", "Error reading file");
        updatedTabData.put("fileAndDataStatus", 2);
        updatedTabData.put("status", false);
        log.error("Error while reading the file: " + e.getMessage());
    }

    return updatedTabData;
}