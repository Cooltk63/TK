// Step 1: Sort row numbers to maintain correct order
List<Integer> sortedRows = new ArrayList<>(rowData.keySet());
Collections.sort(sortedRows);

// Step 2: Iterate over sorted rows and set values dynamically
for (int row : sortedRows) {
    if (!rowData.containsKey(row)) {  
        log.info("Skipping row " + row + " as it's not present in the file.");  // ✅ Log skipped row
        continue;  // ✅ Skip missing row without setting any data
    }

    String[] data = rowData.get(row);  // ✅ Retrieve existing row data (guaranteed to be non-null)

    for (int index = 0; index < fieldNames.length; index++) {
        try {
            String setterName = "set" + capitalize(fieldNames[index]) + row;
            Method setterMethod = SC10.class.getMethod(setterName, String.class);
            setterMethod.invoke(sc10, data[index].trim());  // ✅ Only set values for present rows

        } catch (NoSuchMethodException e) {
            log.warn("No setter found: " + fieldNames[index] + row);
        } catch (Exception e) {
            log.error("Error setting value for: " + fieldNames[index] + row, e);
        }
    }
}