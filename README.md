 public ResponseEntity saveData(Map<String, Object> map) {

        // Data Receive here
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        Map<String, Object> loginuserData = (Map<String, Object>) map.get("user");

try{
        //List of ROW Data
        List<String> dataList = (List<String>) data.get("value");
        com.crs.commonReportsService.models.CRS_Othassests entity = new com.crs.commonReportsService.models.CRS_Othassests();


        //List Data
        entity.setParticulars(dataList.get(0));
        entity.setProvisionableamount(dataList.get(1));
        entity.setWriteoffduring3months(dataList.get(2));
        entity.setAddinprovamount3months(dataList.get(3));
        entity.setReductioninprovamount3months(dataList.get(4));
        entity.setProvamtresult(dataList.get(5));
        entity.setRateofprovision(dataList.get(6));
        entity.setProvisionrequirement(dataList.get(7));
        entity.setOthassestssq(dataList.get(8));

        //User Data
        entity.setAssestsbranch((String) loginuserData.get("branch_code"));
        entity.setAssestsdate((String) loginuserData.get("quarterEndDate"));
        entity.setReportmasteridfk((String) data.get("submissionId"));

        // Check if ROW -ID is empty or null for insert scenario
        if (dataList.get(9).trim().isEmpty() || dataList.get(9) == null) {

            log.info("Inside IFFFF....");
            log.info("Entity Data for Insert: " + entity);

            // Save the new entity
            CRS_Othassests savedEntity = crsOthassestsRepository.save(entity);
            log.info("New row inserted successfully: " + savedEntity);

            // Sending data to response MAP
            Map<String,Object>resultDataMap=new HashMap<>();
            resultDataMap.put("status",true);
            resultDataMap.put("newRowNum",savedEntity.getSerialassestsid());

            ResponseVO<Map<String,Object>> responseVO = new ResponseVO();
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Data Inserted successfully");
            responseVO.setResult(resultDataMap);
            return new ResponseEntity<>(responseVO, HttpStatus.OK);
        }

        else {
            log.info("Inside ELSE....");
            // ID is provided, check for existence and update
            int id = Integer.parseInt(dataList.get(5));
            CRS_Othassests crsOthassests=crsOthassestsRepository.findByserialassestsid(id);
            ResponseVO<Map<String, Object>> responseVO = new ResponseVO();
            Map<String, Object> resultDataMap = new HashMap<>();
                log.info("Received Find BY :"+crsOthassests);
              crsOthassestsRepository.save(entity);
            log.info("Data Updated successfully: " + crsOthassests);

            resultDataMap.put("status",true);
            resultDataMap.put("newRowNum",entity.getSerialassestsid());

            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Data Updated successfully");
            responseVO.setResult(resultDataMap);
            return new ResponseEntity<>(responseVO, HttpStatus.OK);
        }


} catch (RuntimeException e) {
ResponseVO<String> responseVO = new ResponseVO();
            log.info("Exception Occurred :"+e.getCause());
        responseVO.setResult("false");
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseVO.setMessage("Exception Occurred: " + e.getMessage());
        return new ResponseEntity<>(responseVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
return null;
        }


        I have this save method for saving data but having issue my table not had any unique key it only had repeated data in "Othassestssq" column data like 1 1a. 1b. 2 seriallay like and another column is RequestId which had range 1 to 15 maybe for static rows I had unique id which is  submissionId which applicable for every row but my table data had fix 15 rows as static data and remaing dynamic data to be added by user while 1 st time insert how do i insert the data i had branch_code qed for selecting unique row or particular row do do i use the branch code to update the data into particular row and set the Othassestssq and  RequestId not excedding 1 to 15 serial using spring data jpa i want the update method for the same in this method 
