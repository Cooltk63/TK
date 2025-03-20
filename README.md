l// Process each line from the file
for (String line : lines) {
    log.info("Processing line: " + line);
    
    if (line == null || line.trim().isEmpty()) {  // ✅ Skip empty lines
        log.warn("Skipping empty or null line.");
        continue;
    }
    
    String[] columns = line.split("\\|");  // ✅ Split using pipe "|"

    // ✅ Replace null or empty values with "0"
    for (int i = 0; i < columns.length; i++) {
        if (columns[i] == null || columns[i].trim().isEmpty()) {
            log.warn("Empty value found at index " + i + ". Replacing with 0.");
            columns[i] = "0";  // ✅ Set empty values to "0"
        }
    }

    int rowNum = rowNumber[rowNumberCount++];  // ✅ Get correct row number
    rowData.put(rowNum, columns);  // ✅ Store updated row data
}