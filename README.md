import java.util.ArrayList;
import java.util.List;

public class ReplaceNullInList {
    public static void main(String[] args) {
        // Initialize the List<List<String>> with some sample data
        List<List<String>> data = new ArrayList<>();
        data.add(new ArrayList<>(List.of("100", null)));
        data.add(new ArrayList<>(List.of("", "200")));
        data.add(new ArrayList<>(List.of("300", "")));
        data.add(new ArrayList<>(List.of(null, null))); // Example with all null values

        // Call the method to replace null or empty strings with "0"
        List<List<String>> modifiedData = replaceNullOrEmptyWithZero(data);

        // Print the modified list
        modifiedData.forEach(System.out::println);
    }

    private static List<List<String>> replaceNullOrEmptyWithZero(List<List<String>> data) {
        // Iterate over each row in the list
        data.forEach(row -> 
            // Replace null or empty values with "0"
            row.replaceAll(value -> (value == null || value.isEmpty()) ? "0" : value)
        );

        // Return the modified list
        return data;
    }
}