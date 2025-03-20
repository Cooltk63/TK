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
        String qDate = dd + mm + yyyy;  // Format: DDMMYYYY

        log.info("Session Date: " + sessionDate);
        log.info("Fetching file...");

        try {
            // Retrieve file path from properties
            PropertiesConfiguration config = new PropertiesConfiguration("common.properties");

            //Path where file get Read
            String mainPath = config.getProperty("ReportDirIFAMSS").toString();

            log.info("File Reading Path : " + mainPath);

            String filePath = mainPath + qDate + "/IFAMS_SCH10_" + sessionDate + "_" + circleCode + ".txt";

            log.info("File Received Path: " + filePath);

            int[] rowNumber = { 1,3,4,36,5,6,7,37,9,33,10,11,12,13,14,18,34,
                    38,19,20,21,39,22,40,24,25,26,27,28,
                    29,30,31,35,32 };
            log.info("Array Length : " + rowNumber.length);
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

            // Initialize SC10 object and storage for row data
            SC10 sc10 = new SC10();
            Map<Integer, String[]> rowData = new HashMap<>();
            String timeStamp = "";

            // Process each line from the file
            for (String line : lines) {
                log.info("Processing line: " + line);
                String[] columns = line.split("\\|");

                // Extract timestamp if present
                if (columns[0].trim().equalsIgnoreCase("Generated at")) {
                    timeStamp = columns[1].trim();
//                    sc10.setGeneratedTimeStamp(timeStamp);
                    log.info("Time Stamp Extracted from .txt File ::"+timeStamp);
                    updatedTabData.put("FILETIMESTAMP", timeStamp);
                    continue;
                }

                // Parse row number and store data
                log.info("rowNumberCount BEFORE : " + rowNumberCount);
                int rowNum = rowNumber[rowNumberCount++];
                log.info("rowNumberCount Afer : " + rowNumberCount);
                rowData.put(rowNum, columns);
            }


            // Define field names as per SC10.java (without row numbers)
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

            // Step 1: Sort row numbers to maintain correct order
            List<Integer> sortedRows = new ArrayList<>(rowData.keySet());
            Collections.sort(sortedRows);

            // Step 2: Iterate over sorted rows and set values dynamically
            for (int row : sortedRows) {
                if (!rowData.containsKey(row)) {
                    log.info("Skipping row " + row + " as it's not present in the file.");
                    continue;  //  Skip missing row without setting any data
                }

                String[] data = rowData.get(row);  //  Retrieve existing row data (guaranteed to be non-null)

                for (int index = 1; index <= 30; index++) {  //  Ensure all 30 values are processed
                    try {
                        String setterName = "set" + capitalize(fieldNames[index - 1]) + row;  //  Adjust index correctly

                        log.info("Field Data names");
                        Method setterMethod = SC10.class.getMethod(setterName, String.class);
                        log.info("Data WE GET WHILE READING :::"+data[index].trim());
                        setterMethod.invoke(sc10, data[index].trim());  //  Only set values for present rows

                    } catch (NoSuchMethodException e) {
                        log.warn("No setter found: " + fieldNames[index - 1] + row);
                    } catch (Exception e) {
                        log.error("Error setting value for: " + fieldNames[index - 1] + row, e);
                    }
                }
            }

            // Update timestamp in CCDPFiletime Table database
            log.info("Updating / Inserting Data into CCDPFiletime "+"FILE Extracted timeStamp ::"+timeStamp + "circleCode ::" +circleCode + "quarterEndDate ::"+quarterEndDate + "reportName ::"+reportName );
            int updateTime = ccdpSftpDao.updateCCDPFiletime(timeStamp, circleCode, quarterEndDate, reportName);

            log.info("TImeStamp Updated for CCDP_FILE_TIME : Status :" +updateTime + "reportName: " + reportName);



            // Return response
            updatedTabData.put("sc10Data", sc10);
            updatedTabData.put("message", "Data successfully extracted and mapped ");
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
