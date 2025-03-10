public Map<String, Object> getSC10Sftp(Map<String, Object> map) {
    log.info("Inside SC10DaoImpl getSaveBySftp");

    Map<String, Object> updatedTabData = new HashMap<>();
    
    // Extract input parameters
    String quarterEndDate = (String) map.get("qed");
    String circleCode = (String) map.get("circleCode");
    String reportName = (String) map.get("reportName");

    log.info("Quarter End Date: " + quarterEndDate);

    // Extract year, month, and day from quarterEndDate (format: dd/MM/yyyy)
    String[] dateParts = quarterEndDate.split("/");
    String sessionDate = dateParts[2] + dateParts[1] + dateParts[0];  // YYYYMMDD
    String qDate = dateParts[0] + dateParts[1] + dateParts[2];  // DDMMYYYY

    // Get branch code
    String branchCode = ccdpSftpDao.getBrcodeforsftp(circleCode, reportName);
    log.info("Session Date: " + sessionDate);
    log.info("Branch Code: " + branchCode);
    
    try {
        // Retrieve file path from properties
        PropertiesConfiguration config = new PropertiesConfiguration("common.properties");
        String filePath = config.getProperty("ReportDirCCDP") + qDate + "/IFAMS_SCH10_" + sessionDate + "_" + circleCode + ".txt";
        log.info("Received File Path: " + filePath);

        int[] rowNumber = {1, 3, 4, 36, 5, 6, 7, 37, 9, 33, 10, 11, 12, 13, 14, 18, 34,
                38, 19, 20, 21, 39, 22, 40, 24, 25, 26, 27, 28, 29, 30, 31, 35, 32};

        log.info("Array Length: " + rowNumber.length);

        // Read file efficiently in a single loop
        SC10 sc10 = new SC10();
        Map<Integer, String[]> rowData = new HashMap<>();
        AtomicInteger rowNumberCount = new AtomicInteger(0);
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

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.filter(line -> !line.trim().isEmpty()).forEach(line -> {
                log.info("Processing line: " + line);
                String[] columns = line.split("\\|");

                if (columns[0].trim().equalsIgnoreCase("Generated at")) {
                    updatedTabData.put("FILETIMESTAMP", columns[1].trim());
                    return;
                }

                int rowNum = rowNumber[rowNumberCount.getAndIncrement()];
                rowData.put(rowNum, columns);
            });
        }

        // Sorting row numbers for consistent processing
        rowData.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    int row = entry.getKey();
                    String[] data = entry.getValue();

                    for (int i = 1; i <= 30; i++) {
                        try {
                            String setterName = "set" + capitalize(fieldNames[i - 1]) + row;
                            Method setterMethod = SC10.class.getMethod(setterName, String.class);
                            setterMethod.invoke(sc10, data.length > i ? data[i].trim() : "");
                        } catch (NoSuchMethodException e) {
                            log.warn("No setter found: " + fieldNames[i - 1] + row);
                        } catch (Exception e) {
                            log.error("Error setting value for: " + fieldNames[i - 1] + row, e);
                        }
                    }
                });

        // Update timestamp in CCDPFiletime table
        int updateTime = ccdpSftpDao.updateCCDPFiletime((String) updatedTabData.get("FILETIMESTAMP"), circleCode, quarterEndDate, reportName);
        log.info("Timestamp Updated for CCDP_FILE_TIME: Status: " + updateTime + ", reportName: " + reportName);

        // Return response
        updatedTabData.put("sc10Data", sc10);
        updatedTabData.put("message", "Data successfully extracted and mapped");
        updatedTabData.put("status", true);
        updatedTabData.put("fileAndDataStatus", 1);

    } catch (IOException e) {
        updatedTabData.put("message", "Error reading file: " + e.getMessage());
        updatedTabData.put("fileAndDataStatus", 2);
        updatedTabData.put("status", false);
    } catch (Exception e) {
        updatedTabData.put("message", "Unexpected error: " + e.getMessage());
        updatedTabData.put("fileAndDataStatus", 2);
        updatedTabData.put("status", false);
    }

    return updatedTabData;
}