limport java.util.ArrayList;
import java.util.List;

public class ReplaceNullInList {
    public static void main(String[] args) {
        // Simulating a scenario where no data is received from the query (empty list)
        List<List<String>> data = new ArrayList<>(); // This is the empty result set

        // Define the number of rows and columns expected
        int numRows = 3;    // Specify the number of rows you want when no data is received
        int numColumns = 2; // Specify the number of columns expected in each row

        // Call the method to handle no data and replace null or empty strings with "0"
        List<List<String>> modifiedData = handleEmptyAndReplaceNull(data, numRows, numColumns);

        // Print the modified list
        modifiedData.forEach(System.out::println);
    }

    private static List<List<String>> handleEmptyAndReplaceNull(List<List<String>> data, int numRows, int numColumns) {
        // Check if the data list is empty
        if (data.isEmpty()) {
            // Create a default list with the specified number of rows and columns
            for (int i = 0; i < numRows; i++) {
                List<String> defaultRow = new ArrayList<>();
                for (int j = 0; j < numColumns; j++) {
                    defaultRow.add("0");
                }
                // Add the default row to the data
                data.add(defaultRow);
            }
        } else {
            // If data is not empty, replace null or empty values with "0"
            data.forEach(row -> 
                row.replaceAll(value -> (value == null || value.isEmpty()) ? "0" : value)
            );
        }

        // Return the modified list
        return data;
    }
}