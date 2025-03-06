// Step 2: Iterate over sorted rows and set values dynamically
for (int row : sortedRows) {
    if (!rowData.containsKey(row)) {
        log.info("Skipping row " + row + " as it's not present in the file.");
        continue;  // ✅ Skip missing row without setting any data
    }

    String[] data = rowData.get(row);  // ✅ Retrieve existing row data (guaranteed to be non-null)

    for (int index = 1; index <= 30; index++) {  // ✅ Ensure all 30 values are processed
        try {
            String setterName = "set" + capitalize(fieldNames[index - 1]) + row;  // ✅ Adjust index correctly
            Method setterMethod = SC10.class.getMethod(setterName, String.class);
            setterMethod.invoke(sc10, data[index].trim());  // ✅ Only set values for present rows

        } catch (NoSuchMethodException e) {
            log.warn("No setter found: " + fieldNames[index - 1] + row);
        } catch (Exception e) {
            log.error("Error setting value for: " + fieldNames[index - 1] + row, e);
        }
    }
}