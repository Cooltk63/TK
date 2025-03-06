 // Process each line from the file
            for (String line : lines) {
                log.info("Processing line: " + line);
                String[] columns = line.split("\\|");

                // Extract timestamp if present
                if (columns[0].trim().equalsIgnoreCase("Generated at")) {
                    timeStamp = columns[1].trim();
//                    sc10.setGeneratedTimeStamp(timeStamp);
                    continue;
                }

                // Parse row number and store data
                int rowNumber = Integer.parseInt(columns[0].trim());
                rowData.put(rowNumber, columns);
            }


            // Define field names as per SC10.java (without row numbers)
            String[] fieldNames = {
                    "stcNstaff", "offResidenceA", "otherPremisesA", "electricFitting",
                    "totalA", "computers", "compSoftwareInt", "compSoftwareNonint",
                    "compSoftwareTotal", "motor", "offResidenceB", "stcLho",
                    "otherPremisesB", "otherMachineryPlant", "totalB", "totalFurnFix",
                    "landNotRev", "landRev", "landRevEnh", "offBuildNotRev",
                    "offBuildRev", "offBuildRevEnh", "residQuartNotRev", "residQuartRev",
                    "residQuartRevEnh", "premisTotal", "revtotal", "totalC",
                    "premisesUnderCons", "grandTotal"
            };

            // Step 1: Sort row numbers to maintain correct order
            List<Integer> sortedRows = new ArrayList<>(rowData.keySet());
            Collections.sort(sortedRows);

            // Step 2: Iterate over sorted rows and set values dynamically
            for (int row : sortedRows) {
                if (!rowData.containsKey(row)) {
                    log.info("Skipping row " + row + " as it's not present in the file.");  //  Log skipped row
                    continue;  //  Skip missing row without setting any data
                }

                String[] data = rowData.get(row);  //  Retrieve existing row data (guaranteed to be non-null)

                for (int index = 1; index < fieldNames.length; index++) {
                    try {
                        String setterName = "set" + capitalize(fieldNames[index]) + row;
                        Method setterMethod = SC10.class.getMethod(setterName, String.class);
                        setterMethod.invoke(sc10, data[index].trim());  //  Only set values for present rows

                    } catch (NoSuchMethodException e) {
                        log.warn("No setter found: " + fieldNames[index] + row);
                    } catch (Exception e) {
                        log.error("Error setting value for: " + fieldNames[index] + row, e);
                    }
                }
            }

            // Update timestamp in database
            int updateTime = ccdpSftpDao.updateCCDPFiletime(timeStamp, circleCode, quarterEndDate, reportName);



            // Return response
            updatedTabData.put("sc10Data", sc10);
            updatedTabData.put("message", "Data successfully extracted and mapped: " + timeStamp);
            updatedTabData.put("status", true);
            updatedTabData.put("fileAndDataStatus", 1);

        } catch (IOException e) {
            updatedTabData.put("message", "Error reading file: " + e.getMessage());
            updatedTabData.put("fileAndDataStatus", 2);
            updatedTabData.put("status", false);
        } catch (Exception e) {
            updatedTabData.put("message", "Unexpected error: " + e.getMessage());
            updatedTabData.put("fileAndDataStatus", 2);
            updatedTabData.put("status", false);
        }
