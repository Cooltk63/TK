If you have a single report ID and need to handle multiple `TabData` within the response, where each `TabData` contains its own `ColumnData` and `RowData`, you can structure your code to add multiple `TabData` entries to `tabdataList`. Below is an updated version of your code to handle multiple `TabData`:

```java
try {
    // Method for getting the data stored in crs_stnd_assets table
    List<List<String>> result = crsStndAssetsRepository.getScreenDetails(mapData.get("submissionId"));
    log.info("Before Result: " + result);

    // Create a list to hold all TabData entries
    List<Object> tabdataList = new ArrayList<>();

    // Assuming you have a way to get multiple TabData for the given report ID
    List<Map<String, Object>> multipleTabData = columnDataRepository.getMultipleTabData(mapData.get("reportId")); // Custom method to get multiple TabData

    for (Map<String, Object> TabData : multipleTabData) {
        // Get the corresponding ColumnData for each TabData (if it's unique per Tab)
        List<Map<String, Object>> ColumnData = columnDataRepository.getColumnData(mapData.get("reportId"));
        log.info("ColumnData >>>> " + ColumnData);

        // Create a new Map for each TabData
        Map<String, Object> NewTabData = new HashMap<>(TabData);

        // Create a new list for adding Columns data to the result
        List<Object> tabColumnData = new ArrayList<>();
        tabColumnData.add(ColumnData);

        // Add all data to the new Map
        NewTabData.put("TAB_COLUMN_DATA", tabColumnData);
        NewTabData.put("TAB_ROW_DATA", result);

        // Add the NewTabData to the list
        tabdataList.add(NewTabData);
    }

    // Final data map to be returned in the response
    Map<String, Object> finalDataMap = new HashMap<>();
    finalDataMap.put("tabData", tabdataList);

    // Setting up success & response data
    responseVO.setStatusCode(HttpStatus.OK.value());
    responseVO.setMessage("Data retrieved successfully");
    responseVO.setResult(finalDataMap);

} catch (RuntimeException e) {
    log.error("Runtime exception occurred", e);
    responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    responseVO.setMessage("Exception occurred: " + e.getMessage());
}

return new ResponseEntity<>(responseVO, HttpStatus.OK);
```

### Key Changes:
1. **Handling Multiple `TabData`**: The code assumes you have a method (`getMultipleTabData`) to retrieve multiple `TabData` entries for the single report ID.

2. **Loop through `multipleTabData`**: Each `TabData` is processed in the loop. A new map (`NewTabData`) is created and populated with `ColumnData` and `RowData`.

3. **Adding to `tabdataList`**: Each `NewTabData` is added to `tabdataList`.

4. **Returning the `tabdataList`**: This list, containing multiple `TabData` entries, is added to the final response.

This approach allows you to include multiple sets of `ColumnData` and `RowData` within different `TabData` in the final response, all