import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.IntStream;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SC10DaoImpl {

    private static final Logger log = LoggerFactory.getLogger(SC10DaoImpl.class);

    public Map<String, Object> getSC10Sftp(Map<String, Object> map) {
        log.info("Inside SC10DaoImpl getSaveBySftp");
        Map<String, Object> updatedTabData = new HashMap<>();

        try {
            // Extract input parameters
            String quarterEndDate = (String) map.get("qed");
            String circleCode = (String) map.get("circleCode");
            String reportName = (String) map.get("reportName");

            // Convert quarter date to required formats
            String[] dateParts = quarterEndDate.split("/");
            String sessionDate = dateParts[2] + dateParts[1] + dateParts[0]; // YYYYMMDD
            String qDate = dateParts[0] + dateParts[1] + dateParts[2]; // DDMMYYYY

            // Fetch branch code from the database
            String branchCode = ccdpSftpDao.getBrcodeforsftp(circleCode, reportName);
            log.info("Branch Code: {}", branchCode);

            // Retrieve file path from properties
            PropertiesConfiguration config = new PropertiesConfiguration("common.properties");
            String filePath = config.getProperty("ReportDirCCDP") + qDate +
                    "/IFAMS_SCH10_" + sessionDate + "_" + circleCode + ".txt";
            log.info("File Path: {}", filePath);

            // Row numbers and corresponding field names
            int[] rowNumber = {1, 3, 4, 36, 5, 6, 7, 37, 9, 33, 10, 11, 12, 13, 14, 18, 34, 38, 19, 20, 21, 39, 22, 40, 24, 25, 26, 27, 28, 29, 30, 31, 35, 32};
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

            // Cache setter methods using MethodHandles for better performance
            Map<Integer, MethodHandles.Lookup> methodCache = new HashMap<>();
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            SC10 sc10 = new SC10();

            IntStream.range(0, rowNumber.length).forEach(i -> {
                try {
                    String setterName = "set" + capitalize(fieldNames[i]) + rowNumber[i];
                    MethodType methodType = MethodType.methodType(void.class, String.class);
                    methodCache.put(rowNumber[i], lookup.findVirtual(SC10.class, setterName, methodType));
                } catch (NoSuchMethodException | IllegalAccessException e) {
                    log.warn("No setter found for: {}", fieldNames[i] + rowNumber[i]);
                }
            });

            // Read file and map data
            String timeStamp = "";
            Map<Integer, String> rowData = new HashMap<>();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                String line;
                int rowIndex = 0;

                while ((line = bufferedReader.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;

                    log.info("Processing line: {}", line);
                    String[] columns = line.split("\\|");

                    // Extract timestamp if present
                    if (columns[0].trim().equalsIgnoreCase("Generated at")) {
                        timeStamp = columns[1].trim();
                        updatedTabData.put("FILETIMESTAMP", timeStamp);
                        continue;
                    }

                    // Map the second column data to respective row numbers
                    if (rowIndex < rowNumber.length) {
                        rowData.put(rowNumber[rowIndex], columns[1].trim());
                        rowIndex++;
                    }
                }
            }

            // Invoke setters using cached method handles
            rowData.forEach((row, value) -> {
                Optional.ofNullable(methodCache.get(row)).ifPresent(methodHandle -> {
                    try {
                        methodHandle.invoke(sc10, value);
                    } catch (Throwable e) {
                        log.error("Error setting value for row {}: {}", row, e.getMessage());
                    }
                });
            });

            // Update timestamp in the database
            int updateTime = ccdpSftpDao.updateCCDPFiletime(timeStamp, circleCode, quarterEndDate, reportName);
            log.info("TimeStamp Updated: {}", updateTime);

            // Return response
            updatedTabData.put("sc10Data", sc10);
            updatedTabData.put("message", "Data successfully extracted and mapped");
            updatedTabData.put("status", true);
            updatedTabData.put("fileAndDataStatus", 1);

        } catch (IOException e) {
            log.error("File reading error: {}", e.getMessage());
            updatedTabData.put("message", "Error reading file: " + e.getMessage());
            updatedTabData.put("fileAndDataStatus", 2);
            updatedTabData.put("status", false);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            updatedTabData.put("message", "Unexpected error: " + e.getMessage());
            updatedTabData.put("fileAndDataStatus", 2);
            updatedTabData.put("status", false);
        }

        return updatedTabData;
    }

    /**
     * Capitalizes the first letter of the given string.
     */
    private String capitalize(String str) {
        return str == null || str.isEmpty() ? str : str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}