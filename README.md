List<List<String>> finalList = new ArrayList<>();

for (int j = 1; j <= 7; j++) {
    List<String> rowData = new ArrayList<>();
    rowData.add(""); // index 0
    rowData.add(""); // index 1
    rowData.add(""); // index 2
    rowData.add(""); // index 3
    rowData.add(""); // index 4
    rowData.add(""); // index 5
    rowData.add(""); // index 6
    rowData.add(String.valueOf(j)); // index 7 (set to j)
    rowData.add(String.valueOf(j)); // index 8 (set to j)
    rowData.add("false"); // index 9

    finalList.add(rowData);
}

System.out.println("Value of finalList :" + finalList);