// Fetch column data for the specific report and tab
List<Map<String, Object>> getColumnData = columnDataRepository.getTabColumnData(
        (String) data.get("reportId"),   // Report ID to filter column data
        (String) tabData.get(i).get("TAB_VALUE_FK") // Tab value to filter column data
);

// Fetch dynamic date from input data
String dynamicDate = (String) data.get("quarterEndDate");

// Use Stream API to process the column data
List<Map<String, Object>> modifiedColumnData = getColumnData.stream()
        .map(column -> {
            // Check if "COLUMN_NAME" matches the condition
            if ("Provisional as on".equalsIgnoreCase((String) column.get("COLUMN_NAME"))) {
                // Return a new immutable map with updated COLUMN_NAME
                return Map.copyOf(new HashMap<>(column) {{
                    put("COLUMN_NAME", "Provisional as on " + dynamicDate); // Append dynamic date
                }});
            }
            // Return an immutable copy of the original map for other columns
            return Map.copyOf(column);
        })
        .toList(); // Java 16+; creates an immutable list

// Add the modified column data to the updated tab data map
updatedTabData.put("TAB_COLUMN_DATA", modifiedColumnData);