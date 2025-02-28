import java.io.*;
import java.util.*;
import org.apache.commons.configuration.PropertiesConfiguration;

public class SC10DaoImpl {

    public Map<String, Object> getSaveBySftp(Map<String, Object> map) {
        log.info("Inside SC10DaoImpl getSaveBySftp");
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

        // Get branch code based on circleCode and reportName
        String branchCode = ccdpSftpDao.getBrcodeforsftp(circleCode, reportName);

        log.info("Session Date: " + sessionDate);
        log.info("Branch Code: " + branchCode);
        log.info("Fetching file...");

        try {
            // Retrieve file path from properties
            PropertiesConfiguration config = new PropertiesConfiguration("common.properties");
            String mainPath = config.getProperty("ReportDirCCDP").toString();
            String filePath = mainPath + qDate + "/SCH_10_" + sessionDate + "_" + branchCode + ".txt";
            log.info("File Path: " + filePath);

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
                    sc10.setGeneratedTimeStamp(timeStamp);
                    continue;
                }

                // Parse row number and store data
                int rowNumber = Integer.parseInt(columns[0].trim());
                rowData.put(rowNumber, columns);
            }

            // Ensure all row numbers from 1 to 39 exist with exactly 30 values
            for (int i = 1; i <= 39; i++) {
                if (!rowData.containsKey(i)) {
                    rowData.put(i, new String[30]); // Fill missing rows with 30 zeros
                    Arrays.fill(rowData.get(i), "0");
                } else if (rowData.get(i).length < 30) {
                    rowData.put(i, Arrays.copyOf(rowData.get(i), 30)); // Ensure all rows have 30 values
                }
            }

            // Sort rows to maintain correct order
            List<Integer> sortedRows = new ArrayList<>(rowData.keySet());
            Collections.sort(sortedRows);

            // Set values to SC10 bean setters in correct order
            for (int row : sortedRows) {
                String[] data = rowData.get(row);
                int index = 1; // Skip row number

                sc10.setStcNstaff1(getValue(data, index++));
                sc10.setOffResidenceA1(getValue(data, index++));
                sc10.setOtherPremisesA1(getValue(data, index++));
                sc10.setElectricFitting1(getValue(data, index++));
                sc10.setTotalA1(getValue(data, index++));
                sc10.setComputers1(getValue(data, index++));
                sc10.setCompSoftwareInt1(getValue(data, index++));
                sc10.setCompSoftwareNonint1(getValue(data, index++));
                sc10.setCompSoftwareTotal1(getValue(data, index++));
                sc10.setMotor1(getValue(data, index++));
                sc10.setOffResidenceB1(getValue(data, index++));
                sc10.setStcLho1(getValue(data, index++));
                sc10.setOtherPremisesB1(getValue(data, index++));
                sc10.setOtherMachineryPlant1(getValue(data, index++));
                sc10.setTotalB1(getValue(data, index++));
                sc10.setTotalFurnFix1(getValue(data, index++));
                sc10.setLandNotRev1(getValue(data, index++));
                sc10.setLandRev1(getValue(data, index++));
                sc10.setLandRevEnh1(getValue(data, index++));
                sc10.setOffBuildNotRev1(getValue(data, index++));
                sc10.setOffBuildRev1(getValue(data, index++));
                sc10.setOffBuildRevEnh1(getValue(data, index++));
                sc10.setResidQuartNotRev1(getValue(data, index++));
                sc10.setResidQuartRev1(getValue(data, index++));
                sc10.setResidQuartRevEnh1(getValue(data, index++));
                sc10.setPremisTotal1(getValue(data, index++));
                sc10.setRevtotal1(getValue(data, index++));
                sc10.setTotalC1(getValue(data, index++));
                sc10.setPremisesUnderCons1(getValue(data, index++));
                sc10.setGrandTotal1(getValue(data, index++));
            }

            // Update timestamp in database
            int updateTime = ccdpSftpDao.updateCCDPFiletime(timeStamp, circleCode, quarterEndDate, reportName);

            // Return response
            updatedTabData.put("sc10Data", sc10);
            updatedTabData.put("message", "Data successfully extracted and mapped: " + timeStamp);
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

    // Helper method to safely retrieve values from the array
    private String getValue(String[] data, int index) {
        return index < data.length ? data[index].trim() : "0";
    }
}