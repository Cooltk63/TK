import java.lang.reflect.Method;
import java.util.*;

public class SC10Processor {

    public void setSC10Data(SC10 sc10, Map<Integer, String[]> rowData) {
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

        // Step 1: Ensure all row numbers from 1 to 39 exist in rowData
        for (int i = 1; i <= 39; i++) {
            if (!rowData.containsKey(i)) {
                rowData.put(i, new String[30]);  // Create an empty array of 30 values
                Arrays.fill(rowData.get(i), ""); // Fill missing rows with empty strings (important for frontend calculations)
            } else if (rowData.get(i).length < 30) {
                rowData.put(i, Arrays.copyOf(rowData.get(i), 30)); // Ensure all rows have exactly 30 values
            }
        }

        // Step 2: Sort row numbers to maintain correct order
        List<Integer> sortedRows = new ArrayList<>(rowData.keySet());
        Collections.sort(sortedRows);

        // Step 3: Iterate over sorted rows and set values dynamically
        for (int row : sortedRows) {
            String[] data = rowData.get(row); // Retrieve data for the current row

            for (int index = 0; index < fieldNames.length; index++) {
                try {
                    // Construct the correct setter method name, e.g., setComputers5
                    String setterName = "set" + capitalize(fieldNames[index]) + row;

                    // Get the setter method from SC10 class using Reflection
                    Method setterMethod = SC10.class.getMethod(setterName, String.class);

                    // Invoke the setter method with extracted value or empty string if missing
                    setterMethod.invoke(sc10, getValue(data, index + 1));

                } catch (NoSuchMethodException e) {
                    log.warn("No setter found: " + fieldNames[index] + row);
                } catch (Exception e) {
                    log.error("Error setting value for: " + fieldNames[index] + row, e);
                }
            }
        }
    }

    /**
     * Capitalizes the first letter of a field name to match setter method naming conventions.
     * Example: "computers" -> "Computers"
     */
    private String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * Safely retrieves a value from the given data array.
     * If the index is out of bounds, returns an empty string ("").
     */
    private String getValue(String[] data, int index) {
        return index < data.length ? data[index].trim() : "";
    }
}