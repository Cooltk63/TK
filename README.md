// Method for getting the data stored in crs_stnd_assets table
            List<List<String>> result = crsStndAssetsRepository.getScreenDetails(mapData.get("submissionId"));
            log.info("BEfore Result :" + result);
            //result=processResult(result);
            //log.info("result >>>> " + result);


            // Get the Particular Report "Column" Data using Report-ID
            List<Map<String, Object>> ColumnData = columnDataRepository.getColumnData(mapData.get("reportId"));
            log.info("ColumnData >>>> " + ColumnData);

            // Get the Particular Report "Tab Data using Report-ID
            Map<String, Object> TabData = columnDataRepository.getTabData(mapData.get("reportId"));
            log.info("TabData >>>>" + TabData);


            //Created Map for ("Avoiding TupleBackedMap cannot be modified Error")
            Map<String, Object> NewTabData = new HashMap<>();

            //Copy all the Tab Value Returned data to new Map
            NewTabData.putAll(TabData);

            // Created the New List for Adding Columns data to Result.
            List<Object> tabColumnData = new ArrayList<>();
            tabColumnData.add(ColumnData);

            // Adding the All Data to Map
            NewTabData.put("TAB_COLUMN_DATA", tabColumnData);
            NewTabData.put("TAB_ROW_DATA", result);

            List<Object> tabdataList = new ArrayList<>();
            tabdataList.add(NewTabData);

            Map<String, Object> finalDataMap = new HashMap<>();

            // Final Assign Data to MAP
            finalDataMap.put("tabData", tabdataList);

            // Setting Up Success & Response Data
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("data saved successfully");
            responseVO.setResult(finalDataMap);

        } catch (RuntimeException e) {
            log.info("Runtime exception Occurred");
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage("Exception Occurred" + e.getMessage());
            log.info("Get Cause :" + e.getCause().toString());
        }
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
