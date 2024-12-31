@Query(
            nativeQuery = true,
            value ="select YSA_CHECK_AMT,YSA_CHECK_CODE from CRS_YSA_CHECK where YSA_CHECK_DATE=to_date(:QED,'dd/mm/yyyy') and YSA_CHECK_BRANCH =:branchCode " +
                                " and YSA_CHECK_CODE in ( '20040' , '20060' , '20092' , '20100')")
    List<List<String>> getScreenData(@Param("QED") Date QED, @Param("branchCode") String branchCode);

    The error i am getting in console

   

Code I had 
package com.crs.commonReportsService.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crs.commonReportsService.models.ResponseVO;
import com.crs.commonReportsService.repositories.ColumnDataRepository;
import com.crs.commonReportsService.repositories.RW30Repository;

@Service("RW30Service")
public class RW30ServiceImpl implements RW30Service {

     @Autowired
    ColumnDataRepository columnDataRepository;

    @Autowired
    RW30Repository rw30Repository;

    static Logger log = Logger.getLogger(RW30ServiceImpl.class.getName());

    public ResponseEntity getScreenData(Map<String, Object> map){
    
        
            //For getting additional Details other than User Details
            Map<String, String> data = (Map<String, String>) map.get("data");
            Map<String, String> loginUserData = (Map<String, String>) map.get("user");

            log.info("Received Data Map"+data);
            log.info("Received Data Map"+loginUserData);
    
            // Response Data Object
            ResponseVO<Map<String, Object>> responseVO = new ResponseVO<>();
    
            String submissionId = String.valueOf(data.get("submissionId"));

            Date parsedDate;
            String branchCode=loginUserData.get("branch_code");

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                parsedDate = dateFormat.parse(loginUserData.get("quarterEndDate"));
    
                Map<String, Object> finalMap = new LinkedHashMap<>();
                List<Map<String, Object>> tabList = new ArrayList<>();

                    log.info("ReportId :"+data.get("reportId"));
                    log.info("ReportId type :"+data.get("reportId").getClass());
                // Get the Particular Report "Tab Data using Report-ID
                List<Map<String, Object>> tabData = columnDataRepository.getTabDataList("4008");

                log.info("Tab Data Received :"+tabData.size());
    
                // Loop for assigning Row Data & Column Data to Tab Data
                for (int i = 0; i < tabData.size(); i++) {
                    log.info("TabData >>>>" + tabData.get(i).get("TAB_NAME"));
                    Map<String, Object> updatedTabData = new HashMap<>();
    
                    // Copying the Tab Data to New Map
                    updatedTabData.putAll(tabData.get(i));
    
                    // Adding COLUMN-DATA
                    updatedTabData.put(
                            "TAB_COLUMN_DATA",
                            columnDataRepository.getTabColumnData(data.get("reportId"), (String) tabData.get(i).get("TAB_VALUE_FK"),loginUserData.get("quarterEndDate"))
                    );

                    log.info("Adding the row data");
                    // Adding the ROW-DATA
                    if (tabData.get(i).get("TAB_VALUE").toString().equalsIgnoreCase("1")) {

                        //Get Data Here
                        List<String> YSAData = new ArrayList<>();
                        List<List<String>> finalList = new ArrayList<>();

                        log.info("Parsed Data for Get parsedDate:"+parsedDate + "branchCode"+branchCode);
                        System.out.println("Is Empty :"+rw30Repository.getScreenData(parsedDate,branchCode) == null);
                        if (rw30Repository.getScreenData(parsedDate,branchCode) == null || rw30Repository.getScreenData(parsedDate,branchCode).isEmpty()) 
                        {
                            log.info("Inside if Condition ...!");
    
                            // If no data Present for Branch & QED Assign the Empty values
                            YSAData.add(0, "");
                            YSAData.add(1, "");
                            YSAData.add(2, "");
                            YSAData.add(3, "");
                            YSAData.add(4, "");
                            YSAData.add(5, "");
                            YSAData.add(6, "false");
    
    
                            finalList.add(0, YSAData);
                            updatedTabData.put("TAB_ROW_DATA", finalList);
                        } 
                        else
                        {
                            log.info("Inside else block");
                            updatedTabData.put("TAB_ROW_DATA", rw30Repository.getScreenData(parsedDate,branchCode));
                        }
                    }
    
                    tabList.add(updatedTabData);
                }
            
    
                // Finally Adding the Whole Tab Data to Map.
                finalMap.put("tabData", tabList);

                log.info("Final Map Data:"+finalMap.get("tabData"));
    
                // Setting Up Success & Response Data
                responseVO.setStatusCode(HttpStatus.OK.value());
                responseVO.setMessage("Data fetched successfully");
                responseVO.setResult(finalMap);
                
            } catch (NullPointerException | ParseException e) {
                log.info("NullPointer Exception Occurred: " + e.getCause());
                responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                responseVO.setMessage(e.getMessage());
            }
            
    
            return new ResponseEntity<>(responseVO, HttpStatus.OK);
        } 

}

