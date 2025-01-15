
// ST08ServiceImpl Method

@Transactional
    @Override
    public Map<String, Object> saveRowData(Map<String, Object> map) {

      Map<String, Object> result = new HashMap<>();

      // Extracting additional details from the input map
      Map<String, Object> data = (Map<String, Object>) map.get("data");

      // Extracting logged user data
      Map<String, String> loginUserData = (Map<String, String>) map.get("user");

      log.info(" Data Map :"+data);
      log.info("User Map "+loginUserData);

      // Getting submission ID from the data map
      String submissionId = data.get("submissionId").toString();

      // List of ROW data (List<List<String>> format)
      List<List<String>> mainList = (List<List<String>>) data.get("value");


      // Before Insert Deleting Data From Table :: Need to Discusss 

try {
            // Parse the quarterEndDate from user data
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date quarterEndDate = dateFormat.parse(loginUserData.get("quarterEndDate"));
            String branchCode = loginUserData.get("branch_code");

            log.info("Liability Date: " + quarterEndDate);
            log.info("Main List Data: " + mainList);

            // Ensure the row data corresponds to the proper entities:
            for (int i = 0; i < mainList.size(); i++) {
                List<String> dataList = mainList.get(i);

                if (dataList.size() < 9) {
                  log.info("Insufficient data in list: " + dataList);
                  continue; // Skip to the next list
              }
                
                log.info("DataList Data Received :"+dataList);
                int TarMod_ID = Integer.parseInt(dataList.get(8).toString());


                // // Process save data for new Data received from FE
                // TAR_MODE newDataEntity = setEntity(dataList, quarterEndDate, branchCode, submissionId);

                TAR_MODE existingEntity=tarModeRepository.findByReportSubmissionId(submissionId);

                log.info("Existing Entity "+existingEntity.getMode_id());
                
                // If no existing entity data fount Inserting new one
                if (existingEntity.getMode_id()==0) {
                    log.info("Inside existingEntity.getMode_id()==0 Condition");
                  //Inserting data
                   existingEntity = setEntity(dataList, quarterEndDate, branchCode, submissionId);

                  log.info("getting the Update Entity Data:"+existingEntity);

                    tarModeRepository.save(existingEntity);
                    log.info("Data updated successfully for ID: " + TarMod_ID);

                    // If all records processed successfully
                    result.put("Message", "All data Inserted successfully.");
                    result.put("status", true);


                } else {
                    // Update existing entity if it exists
                    log.info("Existing entity found. Updating record.");
                    existingEntity = setEntity(dataList, quarterEndDate, branchCode, submissionId);
                    // If all records processed successfully
                    result.put("Message", "All data Updated successfully.");
                    result.put("status", true);

                    // Save the updated entity
                    tarModeRepository.save(existingEntity);
                }
            }
            

//            // If all records processed successfully
//            result.put("Message", "All data updated successfully.");
//            result.put("status", true);
//            // result.put("result", true);

        } catch (ParseException e) {
            log.info("Error parsing date: " + e.getMessage());
            result.put("Message", "ParseException");
            result.put("status", false);
        }
          catch (RuntimeException e) {
            log.info("Error parsing date: " + e.getMessage());
            result.put("Message", "RuntimeException");
            result.put("status", false);
        }
        // Return response in the specified format
        return result;
    }
	
	
	***// TarModeRepository Method 
	TAR_MODE findByReportSubmissionId(@Param("submissionId")String submissionId);
	
	***// Set Entity Method from ST08ServiceImpl
	private TAR_MODE setEntity(List<String> dataList, Date quarterEndDate, String branch_code, String submissionId) {

      TAR_MODE entity=new TAR_MODE();

      try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date PaymentDate = dateFormat.parse(dataList.get(0));
        //List Data
        entity.setMode_payment_dt(PaymentDate);
        entity.setMode_nature(dataList.get(1));
        entity.setMode_payee(dataList.get(2));
        entity.setMode_pan(dataList.get(3));
        entity.setMode_typebgl(dataList.get(4));
        entity.setMode_amt(Integer.parseInt(dataList.get(5)));
        entity.setMode_reason(dataList.get(6));
       
    
        //User Data
        entity.setMode_branch(branch_code);
        entity.setMode_date(quarterEndDate);
        entity.setReportSubmissionId(submissionId);
    } catch (ParseException e) {
        throw new RuntimeException(e);
    }
    return entity;
}
	
	
	***//Tar Mode Entity Class
	
package com.tar.reportService.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TAR_MODE")
@IdClass(TAR_MODE_Extended.class)
public class TAR_MODE {

    @Column(name = "MODE_ACCOUNT")
    private int mode_account;
    
    @Column(name = "MODE_AMT")
    private int mode_amt;
    
    @Id
    @Column(name = "MODE_BRANCH")
    private String mode_branch;
    
    @Id
    @Column(name = "MODE_DATE")
    private Date mode_date;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "TAR_SEQ")
    @SequenceGenerator(name = "TAR_SEQ",sequenceName = "TAR_SEQ",allocationSize = 1)
    @Column(name = "MODE_ID")
    private int mode_id;
    
    @Column(name = "MODE_NATURE")
    private String mode_nature;
    
    @Column(name = "MODE_PAN")
    private String mode_pan;
    
    @Column(name = "MODE_PAYEE")
    private String mode_payee;
    
    @Column(name = "MODE_PAYMENT_DT")
    private Date mode_payment_dt; 
    
    @Column(name = "MODE_REASON")
    private String mode_reason;
    
    @Column(name = "MODE_TYPEBGL")
    private String mode_typebgl;
    
    @Column(name = "TAR_RML_FK")
    private String reportSubmissionId;
    
    
    
}


	***// Sample List of Data I have received in data Map Inside Value Key as per below
	
	The below data may have multiple list inside value
	 "user": {
        "branch_code": "00016",
        "quarterEndDate": "30/09/2024"
    },
    "data": {
        "submissionId": "2041",
         "reportId":"9019",
        "tabValue":"1",
        "value": [
            [
                "28/04/2017",
                "NUCLEAR BILLS",
                "MSEDCL",
                "0",
                "0",
                "14220",
                "NUCLEAR PAID",
                " ",
                "241350",
                "241350",
                "false"
            ]
        ]
    }
}

I need the correction in above ST08ServiceImpl saveRowData method where i am trying to insert the List of data received from FE and before inserting the data i am 1st wanted to check if data existed in db for provided submission or not if present then update the data with existing record founded by existing entity if no record found then insert the new record which recieved from FE 

I wanted this method to be very efficient and effective but without repeating code i am using jav 17 version but i have no ideaa about java 17 features so give me all the code with comments on each functionality or method 

I have provided you the TAR_mode entity class and repository class just fro reference dont give me back any code chnages unless there is changes in that file
