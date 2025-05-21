List<String> lines = new ArrayList<String>();

            FileReader reader;
            BufferedReader bufferedReader;

            reader = new FileReader(path);
            bufferedReader = new BufferedReader(reader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            // CSR-2024-25 Vulnerability : Denial of Service
            // Line length limit (e.g., 400 characters)
            int maxLineLength = 400;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > maxLineLength) {
                    log.error("File is too large to read : Possible DoS attack");
                    //throw new IOException("File is too large to read : Possible DoS attack");
                    return null;
                }
                stringBuffer.append(line);
                if (!line.equalsIgnoreCase("")) {
                    lines.add(line);
                    stringBuffer.append("\n");
                }


            }
