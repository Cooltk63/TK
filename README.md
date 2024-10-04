import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListModification {

    public static void main(String[] args) {
        List<List<Object>> originalList = new ArrayList<>();
        originalList.add(List.of("FRAUDS - DEBITED TO RECALLED ASSETS A/c (Prod Cd 6998-9981)**", 0, 0, 0, 0, 0, 0, 0, 1, 1, false));
        originalList.add(List.of("PROVISION ON ACCOUNT OF ENTRIES OUTSTANDING IN ADJUSTING ACCOUNT FOR PREVIOUS QUARTER(S) (i.e. PRIOR TO CURRENT QUARTER)", 0, 0, 0, 0, 0, 100, 0, 10, 10, false));
        originalList.add(List.of("PROVISION ON N.P.A. INTEREST FREE STAFF LOANS", 0, 0, 0, 0, 0, 100, 0, 11, 11, false));
        originalList.add(List.of("Frauds reported within time up to null provision @ 100% ##", 0, 0, 0, 0, 0, 100, 0, 2, 2, false));
        originalList.add(List.of("Delayed Reported frauds Provision @ 100% ##", 0, 0, 0, 0, 0, 100, 0, 3, 3, false));
        originalList.add(List.of("OTHERS LOSSES IN RECALLED ASSETS (Prod cd 6998 - 9982)#", 0, 0, 0, 0, 0, 100, 0, 4, 4, false));
        originalList.add(List.of("FRAUDS - OTHER (NOT DEBITED TO RA A/c)$", 0, 0, 0, 0, 0, 0, 0, 5, 5, false));
        originalList.add(List.of("Frauds reported within time up tonullprovision @ 100% ##", 0, 0, 0, 0, 0, 100, 0, 6, 6, false));
        originalList.add(List.of("Delayed Reported frauds Provision @ 100% ##", 0, 0, 0, 0, 0, 100, 0, 7, 7, false));
        originalList.add(List.of("REVENUE ITEM IN SYSTEM SUSPENSE", 0, 0, 0, 0, 0, 100, 0, 8, 8, false));
        originalList.add(List.of("PROVISION ON ACCOUNT OF FSLO", 0, 0, 0, 0, 0, 100, 0, 9, 9, false));

        List<List<String>> totalModified = new ArrayList<>();

        for (List<Object> row : originalList) {
            List<String> modifiedRow = new ArrayList<>();
            modifiedRow.add("");  // Add an empty string at the 0th position

            int serialIndex = Integer.parseInt(row.get(8).toString());  // 8th position contains serial number

            // Modify for Index no 1, 2, 5, 6 (positions 1, 2, 3, 4 get "_D" added)
            if (serialIndex == 1 || serialIndex == 2 || serialIndex == 5 || serialIndex == 6) {
                for (int k = 0; k <= 4; k++) {
                    modifiedRow.add(row.get(k).toString() + "_D");
                }
                for (int k = 5; k < row.size(); k++) {
                    modifiedRow.add(row.get(k).toString());
                }
            } else {
                for (Object element : row) {
                    modifiedRow.add(element.toString());
                }
            }

            // Set the 6th position value to 100, except when serialIndex is 0 or 4
            if (serialIndex != 0 && serialIndex != 4) {
                modifiedRow.set(7, "100");  // 6th position becomes 7th due to the empty string at index 0
            }

            // Add empty string at the 7th position for lists with index 1 and 4
            if (serialIndex == 1 || serialIndex == 4) {
                modifiedRow.set(7, "");  // Replacing 7th position with an empty string
            }

            totalModified.add(modifiedRow);
        }

        // Sort the list based on the serial index at position 9 (previously 8 due to the added empty string)
        totalModified.sort(Comparator.comparingInt(o -> Integer.parseInt(o.get(9))));

        // Printing the final modified and sorted list for verification
        for (List<String> row : totalModified) {
            System.out.println(row);
        }
    }
}