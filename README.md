// List to hold the sanitized lines of the CSV file
List<String> safeLines = new ArrayList<>();

// Reading the original CSV file line-by-line
try (BufferedReader reader = new BufferedReader(new FileReader(outFilePath))) {
    String line;
    // Loop through each line of the CSV
    while ((line = reader.readLine()) != null) {

        // Split each line into individual cells by comma (CSV format)
        // The -1 ensures trailing empty cells are included
        String[] cells = line.split(",", -1);

        // Loop through each cell in the line
        for (int i = 0; i < cells.length; i++) {
            String cell = cells[i].trim(); // Remove leading/trailing spaces

            // Check if cell starts with a dangerous formula-triggering character
            if (!cell.isEmpty() && (cell.startsWith("=") || cell.startsWith("+") || cell.startsWith("-") || cell.startsWith("@"))) {
                // Add a single quote before the cell content to neutralize it
                cells[i] = "'" + cell;
            }
        }

        // Join the cells back into a sanitized CSV line
        safeLines.add(String.join(",", cells));
    }
}

// Now write the sanitized content back to the same CSV file
try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFilePath))) {
    for (String safeLine : safeLines) {
        writer.write(safeLine);  // Write the cleaned line
        writer.newLine();        // Move to the next line
    }
}