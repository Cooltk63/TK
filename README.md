import java.lang.reflect.Method;
import java.util.*;

public class SC10Processor {

    public void setSC10Data(SC10 sc10, Map<Integer, String[]> rowData) {
        // List of field names as per SC10.java (excluding row numbers)
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

        // Process each row from 1 to 39
        for (int row = 1; row <= 39; row++) {
            String[] data = rowData.getOrDefault(row, new String[30]); // Get row data or set empty values

            for (int index = 0; index < fieldNames.length; index++) {
                try {
                    // Construct the setter name dynamically (e.g., "setComputers5")
                    String setterName = "set" + capitalize(fieldNames[index]) + row;

                    // Get the setter method from SC10 class
                    Method setterMethod = SC10.class.getMethod(setterName, String.class);

                    // Invoke the setter method with value or empty string if missing
                    setterMethod.invoke(sc10, getValue(data, index + 1));

                } catch (NoSuchMethodException e) {
                    log.warn("No setter found: " + fieldNames[index] + row);
                } catch (Exception e) {
                    log.error("Error setting value for: " + fieldNames[index] + row, e);
                }
            }
        }
    }

    // Capitalizes first letter of field name to match setter method format
    private String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    // Helper method to safely get value from array
    private String getValue(String[] data, int index) {
        return index < data.length ? data[index].trim() : "";
    }
}