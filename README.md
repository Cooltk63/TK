// Estimate capacity: each branchCode is roughly 8 characters, plus quotes and commas (~10 chars)
int estimatedCapacity = listOfBranches.size() * 10;
StringBuffer buffer = new StringBuffer(estimatedCapacity);

Map<String, String> listEmptyBranch = new HashMap<>();
for (int i = 0; i < listOfBranches.size(); i++) {
    String branchCode = (String) listOfBranches.get(i);
    listEmptyBranch.put(branchCode, "N");

    buffer.append("'").append(branchCode).append("'");
    if (i < listOfBranches.size() - 1) {
        buffer.append(",");
    }
}