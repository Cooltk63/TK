// Step 1: Process only existing rows, skipping missing ones
for (int i : rowData.keySet()) {  // ✅ Iterate only through available rows
    if (rowData.get(i).length < 30) {
        rowData.put(i, Arrays.copyOf(rowData.get(i), 30));  // ✅ Ensure existing rows have 30 values
    }
}