// Loop for processing each tab data in the `tabData` list
for (int i = 0; i < tabData.size(); i++) {
    log.info("Processing TabData >>>> " + tabData.get(i).get("TAB_NAME"));

    // Create a new map to hold updated tab data
    Map<String, Object> updatedTabData = new HashMap<>();

    // Copy all existing values from the current tab data into the updated map
    updatedTabData.putAll(tabData.get(i));

    // Fetch column data for the current tab based on report ID and tab value foreign key
    List<Map<String, Object>> getColumnData = columnDataRepository.getTabColumnData(
            (String) data.get("reportId"),                  // Report ID for filtering
            (String) tabData.get(i).get("TAB_VALUE_FK")     // Tab Value foreign key
    );

    // Fetch the dynamic date provided in the input data
    String dynamicDate = (String) data.get("quarterEndDate");

    // Use Stream API to process and modify the column data
    List<Map<String, Object>> modifiedColumnData = getColumnData.stream()
            .map(column -> {
                // Check if the column name matches "Provisional as on"
                if ("Provisional as on".equalsIgnoreCase((String) column.get("COLUMN_NAME"))) {
                    // Create a new map with the updated column name by appending the dynamic date
                    return Map.copyOf(new HashMap<>(column) {{
                        put("COLUMN_NAME", "Provisional as on " + dynamicDate); // Append date
                    }});
                }
                // Return an immutable copy of the original column if no modification is needed
                return Map.copyOf(column);
            })
            .toList(); // Convert the stream back into an immutable list (Java 16+ feature)

    // Add the modified column data to the updated tab data map
    updatedTabData.put("TAB_COLUMN_DATA", modifiedColumnData);

    // Handle row data processing for specific tabs
    if (tabData.get(i).get("TAB_VALUE").toString().equalsIgnoreCase("1")) {
        // Tab 1: Fetch row data based on the submission ID
        log.info("Fetching ROW Data for SubmissionId: " + submissionId);

        // Retrieve the raw row data from the repository
        List<List<String>> actualRowData = crsOthassestsRepository.getReportDetails(submissionId);

        // Initialize a list to hold modified row data
        List<List<String>> totalModified = new ArrayList<>();

        // Process each row in the retrieved data
        for (List<String> row : actualRowData) {
            List<String> modifiedRow = new ArrayList<>();

            // Add an empty string as the first column (required modification)
            modifiedRow.add("");

            // Get the serial index from the 8th column of the row
            int serialIndex = Integer.parseInt(row.get(8));

            // Modify rows for specific serial indices (2, 3, 6, 7)
            if (serialIndex == 2 || serialIndex == 3 || serialIndex == 6 || serialIndex == 7) {
                // Append "_D" to the first five columns
                for (int k = 0; k <= 4; k++) {
                    modifiedRow.add(row.get(k) + "_D");
                }
                // Copy the remaining columns as they are
                for (int k = 5; k < row.size(); k++) {
                    modifiedRow.add(row.get(k));
                }
            } else {
                // If no modification is needed, copy all columns as they are
                modifiedRow.addAll(row);
            }

            // Set a specific value ("100") in the 7th column for most rows
            if (serialIndex != 0 && serialIndex != 5) {
                modifiedRow.set(7, "100");
            }

            // For serial indices 1 and 5, set the 7th column to an empty string
            if (serialIndex == 1 || serialIndex == 5) {
                modifiedRow.set(7, " ");
            }

            // Add the modified row to the list
            totalModified.add(modifiedRow);
        }

        // Sort the modified rows by the serial index (adjusted for added empty string at position 0)
        totalModified.sort(Comparator.comparingInt(o -> Integer.parseInt(o.get(9))));

        // Add the modified and sorted row data to the updated tab data map
        updatedTabData.put("TAB_ROW_DATA", totalModified);

    } else if (tabData.get(i).get("TAB_VALUE").toString().equalsIgnoreCase("2")) {
        // Tab 2: Fetch and add row data for the second tab
        log.info("Fetching ROW Data for Tab 2");
        updatedTabData.put("TAB_ROW_DATA", crsOthassestsRepository.getAddRowData(submissionId));
    }

    // Add the updated tab data to the final list of tab data
    tabList.add(updatedTabData);
}