package com.crs.commonReportsService.services;

import com.crs.commonReportsService.models.CRS_Othassests;
import com.crs.commonReportsService.models.CrsLiability;
import com.crs.commonReportsService.models.ResponseVO;
import com.crs.commonReportsService.repositories.CRS_OthassestsRepository;
import com.crs.commonReportsService.repositories.ColumnDataRepository;
import com.crs.commonReportsService.repositories.RW04CustomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@Service("RW04Service")
public class RW04ServiceImpl implements RW04Service {

    static Logger log = Logger.getLogger(RW04ServiceImpl.class.getName());
    @Autowired
    RW04CustomRepository rw04CustomRepository;
    @Autowired
    CRS_OthassestsRepository crsOthassestsRepository;

    @Autowired
    ColumnDataRepository columnDataRepository;

    @Override
    public ResponseEntity getReportDetails(Map<String, Object> map) {

        // Data Receive here
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        Map<String, Object> loginuserData = (Map<String, Object>) map.get("user");
        log.info("Data Map Values ::" + data);
        log.info("Login user Map Values ::" + loginuserData);

        ResponseVO<Map<String, Object>> responseVO = new ResponseVO<>();


        // SubmissionID Here
        int submissionId = Integer.parseInt(String.valueOf(data.get("submissionId")));

        Date liabilityDate = null;

        // Date Conversion
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Current QED
            liabilityDate = dateFormat.parse((String) loginuserData.get("quarterEndDate"));

        } catch (ParseException e) {
            log.info("Error parsing date");
        }


        try {

            Map<String, Object> finalMap = new LinkedHashMap<>();
            List<Map<String, Object>> tabList = new ArrayList<>();

            /// First Time Insert
            String currentStatus=data.get("currentStatus").toString();
            if(currentStatus.equalsIgnoreCase("X"))
            {

            // Inserting Empty Data 1st time Screen Loading
                List<String> ParticularsEmptyData=new ArrayList<>();
//                ParticularsEmptyData.add(,"");
                ParticularsEmptyData.add(0,"FRAUDS - DEBITED TO RECALLED ASSETS A/c (Prod Cd 6998-9981)**");
                ParticularsEmptyData.add(1,"Frauds reported within time up to " +data.get("quarterEndDate") +" provision @ 100% ##");
                ParticularsEmptyData.add(2,"Delayed Reported frauds Provision @ 100% ##");
                ParticularsEmptyData.add(3,"OTHERS LOSSES IN RECALLED ASSETS (Prod cd 6998 - 9982)#");
                ParticularsEmptyData.add(4,"FRAUDS - OTHER (NOT DEBITED TO RA A/c)$");
                ParticularsEmptyData.add(5,"Frauds reported within time up to" +data.get("quarterEndDate") +"provision @ 100% ##");
                ParticularsEmptyData.add(6,"Delayed Reported frauds Provision @ 100% ##");
                ParticularsEmptyData.add(7,"REVENUE ITEM IN SYSTEM SUSPENSE");
                ParticularsEmptyData.add(8,"PROVISION ON ACCOUNT OF FSLO");
                ParticularsEmptyData.add(9,"PROVISION ON ACCOUNT OF ENTRIES OUTSTANDING IN ADJUSTING ACCOUNT FOR PREVIOUS QUARTER(S) (i.e. PRIOR TO CURRENT QUARTER)");
                ParticularsEmptyData.add(10,"PROVISION ON N.P.A. INTEREST FREE STAFF LOANS");

                // Insert 6 more rows with 0 values for particular fields
                for (int i = 0; i <11; i++) {
                    log.info("submissionId for Empty Insert :"+submissionId);

                    CRS_Othassests zeroLiability = new CRS_Othassests();
                    zeroLiability.setParticulars(ParticularsEmptyData.get(i));
                    zeroLiability.setProvisionableamount("0");
                    zeroLiability.setWriteoffduring6months("0");
                    zeroLiability.setAddinprovamount6months("0");
                    zeroLiability.setReductioninprovamount6months("0");
                    zeroLiability.setProvamtresult("0");

                    // If Condition here
                    if(i == 0||i == 4)
                    {
                        log.info("inside IF Condition TRUE SETTING 00000" +i);
                        zeroLiability.setRateofprovision("0");
                    }
                    else {
                        log.info("inside IF Condition TRUE SETTING 100");
                        zeroLiability.setRateofprovision("100");
                    }

                    log.info("Value of Rate provision on " +i +" Value :"+zeroLiability.getRateofprovision());


                    zeroLiability.setProvisionrequirement("0");
                    zeroLiability.setOthassestssq(String.valueOf(i + 1)); // Set unique ID for each row
                    zeroLiability.setAssestsbranch((String) loginuserData.get("branch_code"));
                    zeroLiability.setAssestsdate(liabilityDate);
                    zeroLiability.setReportmasteridfk(submissionId);
                    zeroLiability.setSerialassestsid(String.valueOf(i + 1));

                    log.info("Inserting zero-value row: " + zeroLiability);

                    // Inserting RW-03 Empty Row Data in DB
                    CRS_Othassests zeroEntity = crsOthassestsRepository.save(zeroLiability);
                    log.info("Zero-value CrsLiability saved to database: " + zeroEntity.getSerialassestsid());
                }
            }



            ///

            // Get the Particular Report "Tab Data using Report-ID
            List<Map<String, Object>> tabData = columnDataRepository.getTabDataList((String) data.get("reportId"));


            // Loop for assigning Row Data & Column Data to Tab Data
            for (int i = 0; i < tabData.size(); i++) {
                log.info("TabData >>>>" + tabData.get(i).get("TAB_NAME"));
                Map<String, Object> updatedTabData = new HashMap<>();

                // Copying the Tab Data to New Map
                updatedTabData.putAll(tabData.get(i));

                List<Map<String,Object>> getColumnData=columnDataRepository.getTabColumnData((String) data.get("reportId"), (String) tabData.get(i).get("TAB_VALUE_FK"));
                // Adding COLUMN-DATA
                updatedTabData.put(
                        "TAB_COLUMN_DATA", getColumnData
                );

                // Adding the ROW-DATA
                if (tabData.get(i).get("TAB_VALUE").toString().equalsIgnoreCase("1")) {
                    //Get OthAssets Static Data Here
                    log.info("SubmissionId for getting ROW Data" +submissionId);
                    List<List<String>> actualRowData=  crsOthassestsRepository.getReportDetails(submissionId);
                    log.info("getting Row Data Here Size"+actualRowData.size());
                    
                    ////><><><><><><><><><><><><><><>><
                    List<List<String>> totalModified = new ArrayList<>();

                    for (List<String> row : actualRowData) {
                        List<String> modifiedRow = new ArrayList<>();
                        modifiedRow.add("");  // Add an empty string at the 0th position

                        int serialIndex = Integer.parseInt(row.get(8).toString());  // 8th position contains serial number

                        // Modify for Index no 1, 2, 5, 6 (positions 1, 2, 3, 4 get "_D" added)
                        if (serialIndex == 1 || serialIndex == 2 || serialIndex == 5 || serialIndex == 6) {
                            for (int k = 0; k <= 4; k++) {
                                modifiedRow.add(row.get(k).toString() + "_D");
                            }
                            for (int k = 5; k < row.size(); k++) {
                                modifiedRow.add(row.get(k).toString());
                            }
                        } else {
                            for (Object element : row) {
                                modifiedRow.add(element.toString());
                            }
                        }

                        // Set the 6th position value to 100, except when serialIndex is 0 or 4
                        if (serialIndex != 0 && serialIndex != 4) {
                            modifiedRow.set(7, "100");  // 6th position becomes 7th due to the empty string at index 0
                        }

                        // Add empty string at the 7th position for lists with index 1 and 4
                        if (serialIndex == 1 || serialIndex == 4) {
                            modifiedRow.set(7, "");  // Replacing 7th position with an empty string
                        }

                        totalModified.add(modifiedRow);
                    }

                    // Sort the list based on the serial index at position 9 (previously 8 due to the added empty string)
                    totalModified.sort(Comparator.comparingInt(o -> Integer.parseInt(o.get(9))));

                    // Printing the final modified and sorted list for verification
                    for (List<String> row : totalModified) {
                       log.info("FINAL ROW DATA AT POSITION "+row);
                    }

                    ////><><><><><><><><><><><><><><>><
                    
                        log.info("List Data Send to TAB ROW :"+totalModified);

                    updatedTabData.put("TAB_ROW_DATA", totalModified);
                }
                // Get Add-Row Data for 2nd Tab
             else if (tabData.get(i).get("TAB_VALUE").toString().equalsIgnoreCase("2")) {
                log.info("Inside 2nd tab data");
                updatedTabData.put("TAB_ROW_DATA", crsOthassestsRepository.getAddRowData(submissionId));
            }
                tabList.add(updatedTabData);
            }

            // Finally Adding the Whole Tab Data to Map.
            finalMap.put("tabData", tabList);

            // Setting Up Success & Response Data
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Data fetched successfully");
            responseVO.setResult(finalMap);


        } catch (NullPointerException e) {
            log.info("NullPointer Exception Occurred: " + e.getCause());
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage("Getting Error Message " + e.getMessage());
        }

        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }


    // For Saving the Add Row Data
    @Override
    public ResponseEntity saveAddRowData(Map<String, Object> map) {

        // Data Receive here
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        Map<String, Object> loginUserData = (Map<String, Object>) map.get("user");

        // Data Required for Setting Data into Entity
        String quarterEndDate= (String) loginUserData.get("quarterEndDate");
        String branch_code= (String) loginUserData.get("branch_code");
        int submissionId= Integer.parseInt(String.valueOf(data.get("submissionId")));


        try {
            //List of ROW Data
            List<String> dataList = (List<String>) data.get("value");
            CRS_Othassests entity = setEntity(dataList,quarterEndDate, branch_code, submissionId);

            // Getting Sequence ID for New Row Insert
            String CrsOthAssetsSeq=crsOthassestsRepository.getSequenceId();


            // Check if ROW -ID is empty or null for insert scenario
            if (dataList.get(9).trim().isEmpty() || dataList.get(9) == null) {
                log.info("Entity Data for Insert: " + entity);

                // Setting SequenceId Before Inserting the New Record
                entity.setSerialassestsid(CrsOthAssetsSeq);

                // Saving Screen Data to DB
                CRS_Othassests insertData = crsOthassestsRepository.save(entity);

                log.info("CRS_Othassests New Inserted ID ::" + insertData.getSerialassestsid());

                // Sending data to response MAP
                Map<String, Object> resultDataMap = new HashMap<>();
                resultDataMap.put("status", true);
                resultDataMap.put("newId", insertData.getSerialassestsid());
                resultDataMap.put("newRowNum", insertData.getSerialassestsid());

                ResponseVO<Map<String, Object>> responseVO = new ResponseVO();
                responseVO.setStatusCode(HttpStatus.OK.value());
                responseVO.setMessage("Data Inserted successfully");
                responseVO.setResult(resultDataMap);
                return new ResponseEntity<>(responseVO, HttpStatus.OK);
            }
            else {
                // ID is provided, check for existence and update
                String id = dataList.get(9);
                log.info("Received ID for Update: " + id + " Is ID Existed :" + crsOthassestsRepository.existsByserialassestsid(String.valueOf(id)));

                ResponseVO<Map<String, Object>> responseVO = new ResponseVO();
                Map<String, Object> resultDataMap = new HashMap<>();
                if (crsOthassestsRepository.existsByserialassestsid(id)) {
                    // Update existing row
                    entity.setSerialassestsid(String.valueOf(id));  // Set the ID for update

                    CRS_Othassests updatedEntity = crsOthassestsRepository.save(entity);
                    log.info("Data Updated successfully: " + updatedEntity);

                    resultDataMap.put("status", true);
                    resultDataMap.put("newId", updatedEntity.getSerialassestsid());
                    resultDataMap.put("newRowNum", updatedEntity.getSerialassestsid());

                    responseVO.setStatusCode(HttpStatus.OK.value());
                    responseVO.setMessage("Data Updated successfully");
                    responseVO.setResult(resultDataMap);
                    return new ResponseEntity<>(responseVO, HttpStatus.OK);
                }
                else {
                    // ID not found, handle error
                    log.info("ID " + id + " not found for update");
                    responseVO.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    responseVO.setMessage("Invalid ID provided. Record not found.");
                    resultDataMap.put("status", false);
                    responseVO.setResult(resultDataMap);
                    return new ResponseEntity<>(responseVO, HttpStatus.BAD_REQUEST);
                }
            }

        } catch (RuntimeException e) {
            ResponseVO<String> responseVO = new ResponseVO();
            log.info("Exception Occurred :" + e.getCause());
            log.info("Exception Occurred :" + e.getMessage());
            responseVO.setResult("false");
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage("Exception Occurred: " + e.getMessage());
            return new ResponseEntity<>(responseVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<ResponseVO<Boolean>> saveStaticData(Map<String, Object> map) {
        // Extract data from the incoming request map
        Map<String, Object> data = (Map<String, Object>) map.get("data");

        Map<String, Object> loginUserData = (Map<String, Object>) map.get("user");

        log.info("Static Data received 'MAP:: data': "+data.toString());
        log.info("Static Data received 'MAP:: user': "+loginUserData.toString());

        // Retrieve submission ID from data map
        int submissionId = Integer.parseInt(String.valueOf(data.get("submissionId")));

        String quarterEndDate= (String) loginUserData.get("quarterEndDate");

        String branch_code= (String) loginUserData.get("branch_code");

        // Initialize the response object
        ResponseVO<Boolean> responseVO = new ResponseVO<>();
      

        try {
            // List of ROW Data from the incoming request
            List<List<String>> mainList = (List<List<String>>) data.get("value");

            // Parse the quarterEndDate from the user data
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date parsedDate = dateFormat.parse((String) loginUserData.get("quarterEndDate"));

            log.info("Data List Size for Static Save :"+mainList.size());

            // Process each list (row) inside the main list
            for (List<String> dataList : mainList) {
                if (dataList.size() < 10) {
                    log.info("Insufficient data in list: " + dataList);
                    continue; // Skip to the next list
                }

                String serialAssetsId = dataList.get(9); // 9th index (10th element)

                // Fetch existing entity based on composite key: quarterEndDate, branchCode, serialAssetsId
                CRS_Othassests existingEntity = crsOthassestsRepository.findByAssestsdateAndAssestsbranchAndSerialassestsid( parsedDate, branch_code, serialAssetsId);



                // If no existing entity, create a new one
                if (existingEntity != null) {
                    CRS_Othassests updatingEntity=setEntity(dataList,quarterEndDate,branch_code, submissionId);
                        crsOthassestsRepository.save(updatingEntity);
                        log.info("Data updated successfully for ID: " + serialAssetsId);

                    // If all records processed successfully
                    responseVO.setMessage("All data updated successfully.");
                    responseVO.setResult(true);
                    responseVO.setStatusCode(HttpStatus.OK.value());


                }else {
                    // Update existing entity if it exists
                    log.info("Existing entity found. Updating record.");

                    // Update fields
                    log.info("Updating existing entity data");
                    existingEntity=setEntity(dataList, quarterEndDate,branch_code, submissionId);

                    // Save the updated entity
                    crsOthassestsRepository.save(existingEntity);
                }
            }

            // If all records processed successfully
            responseVO.setMessage("All data updated successfully.");
            responseVO.setResult(true);
            responseVO.setStatusCode(HttpStatus.OK.value());

        } catch (ParseException e) {
            // Handle date parsing errors
            log.info("Error parsing date: " + e.getMessage());
            responseVO.setMessage("Invalid date format provided.");
            responseVO.setResult(false);
            responseVO.setStatusCode(HttpStatus.BAD_REQUEST.value());
        } catch (RuntimeException e) {
            // Handle unexpected runtime exceptions
            log.info("Exception occurred: " + e.getMessage());
            responseVO.setResult(false);
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage("An unexpected error occurred.");
        }

        // Common return statement for all cases
        return new ResponseEntity<>(responseVO, HttpStatus.valueOf(responseVO.getStatusCode()));
    }


    // Delete Add-row
    @Transactional
    @Override
    public ResponseEntity delete(Map<String, Object> map) {
        //For getting additional Details other than User Details
        Map<String, String> data = (Map<String, String>) map.get("data");
        ResponseVO<Integer> responseVO = new ResponseVO<>();

        int isDeleted = 0;

        try {

            int deleteId = Integer.parseInt(data.get("uniqueId"));

            // Check before ID is Existed or Not
            int exits = crsOthassestsRepository.countByserialassestsid(String.valueOf(deleteId));
            log.info("Is ID Exits :" + exits);

            if (exits > 0) {
                //If ID exists deleting Data
                isDeleted = crsOthassestsRepository.deleteByserialassestsid(String.valueOf(deleteId));
                log.info("Result for isDeleted :" + isDeleted);
            }

            // Setting Up Success & Response Data
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Data Deleted successfully");
            responseVO.setResult(isDeleted);

        } catch (NullPointerException e) {
            log.info("NullPointer Exception Occurred: " + e.getCause());
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage("Failed to delete data");
            responseVO.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }




    // Setting List Data to Entity for SAv/ Update
    private CRS_Othassests setEntity(List<String> dataList, String quarterEndDate,String branch_code,int submissionId )
    {
        log.info("Data Original Size :"+dataList.size());

        if(dataList.size() == 12) {
            dataList.remove(0);

            log.info("Data List AFTER Removing 0th Index :"+dataList);
            log.info("Data List Deletion Size :"+dataList.size());

            log.info("dataList.get(0) :"+dataList.get(0));
            log.info("dataList.get(1) :"+dataList.get(1));
            log.info("dataList.get(2) :"+dataList.get(2));
            log.info("dataList.get(3) :"+dataList.get(3));
            log.info("dataList.get(4) :"+dataList.get(4));
            log.info("dataList.get(5) :"+dataList.get(5));
            log.info("dataList.get(7) :"+dataList.get(7));
        }


        CRS_Othassests entity = new CRS_Othassests();


        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date parsedDate = dateFormat.parse(quarterEndDate);
            //List Data
            entity.setParticulars(dataList.get(0));
            entity.setProvisionableamount(dataList.get(1));
            entity.setWriteoffduring6months(dataList.get(2));
            entity.setAddinprovamount6months(dataList.get(3));
            entity.setReductioninprovamount6months(dataList.get(4));
            entity.setProvamtresult(dataList.get(5));
            entity.setRateofprovision(dataList.get(6));
            entity.setProvisionrequirement(dataList.get(7));
            entity.setOthassestssq(dataList.get(8)); // This is AplhaNumeric Sequence
            entity.setSerialassestsid(dataList.get(9));


            //User Data
            entity.setAssestsbranch(branch_code);
            entity.setAssestsdate(parsedDate);
            entity.setReportmasteridfk(submissionId);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

         // Returning the Column Data here
        private String[] SplitListData(List<Map<String,Object>> getColumnData)
        {
            List<String> ParticularsList=new ArrayList<>();

            //Iterate Columns Data Map
            for(Map<String,Object> Data:getColumnData)
            {
                ParticularsList.add((String) Data.get("HEADING_VALUES"));
            }

            log.info("ParticularsList Extracted from getColumnData:"+ParticularsList);


            log.info("Splitting List Data of Particular List :"+ParticularsList.get(1));
            // Get List of Particulars Required for Screen
            String ParticularsDataStr=ParticularsList.get(1);

            log.info("ParticularsDataStr Single String:"+ParticularsDataStr);

            String regex = "[|]";
            String[] ParticularsData = ParticularsDataStr.split(regex);

            log.info("ParticularsData Length:"+ParticularsData.length);
            log.info("ParticularsData [0] :"+ParticularsData[0]);


            return ParticularsData;
        }



}


