import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import org.apache.commons.configuration.PropertiesConfiguration;

public class SC10DaoImpl {

    public Map<String, Object> getSC10Sftp(Map<String, Object> map) {
        log.info("Inside SC10DaoImpl getSaveBySftp");

        Map<String, Object> updatedTabData = new HashMap<>();

        // Extract required input parameters
        String quarterEndDate = (String) map.get("qed");
        String circleCode = (String) map.get("circleCode");
        String reportName = (String) map.get("reportName");

        log.info("Quarter End Date: " + quarterEndDate);

        // Extract year, month, and day from the quarterEndDate (Expected format: dd/MM/yyyy)
        String[] dateParts = quarterEndDate.split("/");
        String sessionDate = dateParts[2] + dateParts[1] + dateParts[0];  // Format: YYYYMMDD
        String qDate = dateParts[0] + dateParts[1] + dateParts[2];  // Format: DDMMYYYY

        // Get branch code based on circleCode and reportName
        String branchCode = ccdpSftpDao.getBrcodeforsftp(circleCode, reportName);
        log.info("Session Date: " + sessionDate);
        log.info("Branch Code: " + branchCode);

        try {
            // Load file path from configuration properties
            PropertiesConfiguration config = new PropertiesConfiguration("common.properties");

            // Modify this section for **PRODUCTION PATH CHANGES**
            String filePath = config.getProperty("ReportDirCCDP") + qDate + "/IFAMS_SCH10_" + sessionDate + "_" + circleCode + ".txt";
            log.info("File Path Received: " + filePath);

            // Row numbers corresponding to required fields
            int[] rowNumber = {1, 3, 4, 36, 5, 6, 7, 37, 9, 33, 10, 11, 12, 13, 14, 18, 34,
                    38, 19, 20, 21, 39, 22, 40, 24, 25, 26, 27, 28, 29, 30, 31, 35, 32};

            log.info("Row Number Array Length: " + rowNumber.length);

            // SC10 object to store extracted values
            SC10 sc10 = new SC10();
            Map<Integer, String[]> rowData = new HashMap<>();
            AtomicInteger rowNumberCount = new AtomicInteger(0);

            // Field names mapping for reflection-based setter calls
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

            // Efficiently read the file using Java Streams (Avoids multiple loops)
            try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
                stream.filter(line -> !line.trim().isEmpty()).forEach(line -> {
                    log.info("Processing Line: " + line);
                    String[] columns = line.split("\\|");

                    // Capture file timestamp when encountered
                    if (columns[0].trim().equalsIgnoreCase("Generated at")) {
                        updatedTabData.put("FILETIMESTAMP", columns[1].trim());
                        return;
                    }

                    // Assign row number and store data
                    int rowNum = rowNumber[rowNumberCount.getAndIncrement()];
                    rowData.put(rowNum, columns);
                });
            }

            // Process the extracted data and populate the SC10 object dynamically
            rowData.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())  // Ensure correct order
                    .forEach(entry -> {
                        int row = entry.getKey();
                        String[] data = entry.getValue();

                        for (int i = 1; i <= 30; i++) {
                            try {
                                // Create dynamic setter method name
                                String setterName = "set" + capitalize(fieldNames[i - 1]) + row;
                                Method setterMethod = SC10.class.getMethod(setterName, String.class);

                                // Set value, handle missing columns safely
                                setterMethod.invoke(sc10, data.length > i ? data[i].trim() : "");
                            } catch (NoSuchMethodException e) {
                                log.warn("No setter method found: " + fieldNames[i - 1] + row);
                            } catch (Exception e) {
                                log.error("Error setting value for: " + fieldNames[i - 1] + row, e);
                            }
                        }
                    });

            // Update the CCDP_FILE_TIME table with the extracted timestamp
            int updateTime = ccdpSftpDao.updateCCDPFiletime((String) updatedTabData.get("FILETIMESTAMP"), circleCode, quarterEndDate, reportName);
            log.info("Timestamp Updated for CCDP_FILE_TIME. Status: " + updateTime + ", Report: " + reportName);

            // Prepare response data
            updatedTabData.put("sc10Data", sc10);
            updatedTabData.put("message", "Data successfully extracted and mapped");
            updatedTabData.put("status", true);
            updatedTabData.put("fileAndDataStatus", 1);

        } catch (IOException e) {
            // Handle file read errors
            updatedTabData.put("message", "Error reading file: " + e.getMessage());
            updatedTabData.put("fileAndDataStatus", 2);
            updatedTabData.put("status", false);
        } catch (Exception e) {
            // Handle unexpected exceptions
            updatedTabData.put("message", "Unexpected error: " + e.getMessage());
            updatedTabData.put("fileAndDataStatus", 2);
            updatedTabData.put("status", false);
        }

        return updatedTabData;
    }

    /**
     * Utility method to capitalize the first letter of a string
     */
    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}