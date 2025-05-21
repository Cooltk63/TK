int maxLineLength = 400;
int maxTotalLength = 100000; // total 100,000 characters (~100 KB)
int currentLength = 0;

while ((line = bufferedReader.readLine()) != null) {
    if (line.length() > maxLineLength) {
        log.error("File line too long: Possible DoS attack");
        return null;
    }

    currentLength += line.length();
    if (currentLength > maxTotalLength) {
        log.error("File too large in total: Possible DoS attack");
        return null;
    }

    stringBuffer.append(line);
    if (!line.trim().isEmpty()) {
        lines.add(line);
        stringBuffer.append("\n");
    }
}