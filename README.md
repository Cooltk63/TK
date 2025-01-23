package com.tar.reportService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tar.reportService.models.TAR_MODE;
import com.tar.reportService.repository.TarModeRepository;
import com.tar.reportService.repository.TarParamRepository;

import jakarta.transaction.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@Service("ST08Service")
public class ST08ServiceImpl implements ST08Service {
    
    static Logger log = Logger.getLogger(ST08ServiceImpl.class.getName());

    @Autowired
    TarParamRepository tarParamRepository;

    @Autowired
    TarModeRepository tarModeRepository;

    private List<String> getbgltype(){
		return tarParamRepository.getBgltype();
	}

@Override
    public Map<String, Object> getTabRowData(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>();

        log.info("Inside getTabData RW-08ST");

        Map<String, String> data = (Map<String, String>) map.get("data");
        Map<String, String> loginUserData = (Map<String, String>) map.get("user");

        String submissionId=data.get("submissionId").toString();

        log.info(" Data Map :"+data);
      log.info("User Map "+loginUserData);

        try {
           List<List<String>> getST08Data=tarParamRepository.getData(submissionId);

//           log.info("ROW Data We Get :"+getST08Data);

            result.put("TAB_ROW_DATA", getST08Data);

        } catch (Exception e) {
            log.info("Exception Occurred :"+ e.getCause());
            log.info("Exception Occurred :"+ e.getMessage());
            
        }
//        log.info("TAB ROW Data :"+result.get("TAB_ROW_DATA"));
        return result;
    }


    @Transactional
    @Override
    public Map<String, Object> saveRowData(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>();

        try {
            // Extracting data and user details from the input map
            Map<String, Object> data = (Map<String, Object>) map.get("data");
            Map<String, String> loginUserData = (Map<String, String>) map.get("user");

            log.info("Data Map ::"+ data);
            log.info("User Map ::"+ loginUserData);

            // Extract submission ID from data
            String submissionId = data.get("submissionId").toString();

            // Parse quarter end date and branch code from user data
            Date quarterEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(loginUserData.get("quarterEndDate"));
            String branchCode = loginUserData.get("branch_code");


            // Extract the list of row data (List<List<String>> format)
            List<String> dataList = (List<String>) data.get("value");

            log.info("Processing DataList for Save ::"+ dataList);



            TAR_MODE insertingEntity = setEntity(dataList, quarterEndDate, branchCode, submissionId);


            // Insert new data if no existing record, else update
            if (dataList.get(dataList.size()-2).trim().isEmpty() || dataList.get(7) == null) {
                log.info("No existing record found. Inserting new record.");
                TAR_MODE insertEntity=tarModeRepository.save(insertingEntity);
                result.put("newId", insertEntity.getModeid());
                result.put("newRowNum", insertEntity.getModeid());
            } else {
                // Extract MODE_ID to check for existing records
                int tarModId = Integer.parseInt(dataList.get(7));

                // Check if data exists for the given submission ID
                TAR_MODE existingEntity = tarModeRepository.findByModeid(tarModId);

                log.info("Existing record found. Updating record.");
                insertingEntity.setModeid(tarModId);
                existingEntity=tarModeRepository.save(insertingEntity);

               log.info("Updated record ::"+ existingEntity.getModeid());
                result.put("newId", existingEntity.getModeid());
                result.put("newRowNum", existingEntity.getModeid());
            }

            result.put("status", true);

        } catch (ParseException e) {
            log.info("Error parsing date: {}"+ e.getMessage());
            log.info("Error parsing date: {}"+ e.getCause());
            result.put("status", false);
        } catch (RuntimeException e) {
            log.info("Unexpected error: {}"+ e.getMessage());
            result.put("status", false);
        }
        return result;
    }




        @Transactional
        @Override
        public Map<String, Object> deleteTabRowData(Map<String, Object> map) {
                Map<String, Object> result = new HashMap<>();
                Map<String, Object> data = (Map<String, Object>) map.get("data");
                try {
                    log.info("Data Received :"+data);
                    // Get the modeID from the data map
                    String modeIdStr = data.get("value").toString();

                    int modeId = Integer.parseInt(modeIdStr); // Converting string to integer

                    // Check if the entity exists by modeID
                    Optional<TAR_MODE> existingEntity = Optional.ofNullable(tarModeRepository.findByModeid(modeId));

                    log.info("Existing record found. Deleting record.");

                    if (existingEntity.isPresent()) {
                        // If it exists, delete the entity
                        tarModeRepository.delete(existingEntity.get());
                        log.info("Entity Deleted Successfully");
                        result.put("Message", "Data deleted successfully.");
                        result.put("status", 1);
                    } else {
                        // If entity doesn't exist
                        result.put("Message", "Data not found.");
                        result.put("status", 0);
                    }
                } catch (Exception e) {
                    result.put("Message", "Error occurred: " + e.getMessage());
                    result.put("status", 0);
                }

                return result;
            }


    private TAR_MODE setEntity(List<String> dataList, Date quarterEndDate, String branchCode, String submissionId) {
       TAR_MODE entity = new TAR_MODE();
        try {
            // Parse and set the payment date
            entity.setMode_payment_dt(new SimpleDateFormat("dd/MM/yyyy").parse(dataList.get(0)));
        } catch (ParseException e) {
            log.info("Invalid payment date '{}'. Setting as null."+ dataList.get(0));
            entity.setMode_payment_dt(null);
        }

        // Map data fields directly
        entity.setMode_nature(dataList.get(1));           // Nature of the payment
        entity.setMode_payee(dataList.get(2));            // Payee name
        entity.setMode_pan(dataList.get(3));              // PAN number
        entity.setMode_typebgl(dataList.get(4).equalsIgnoreCase("Debited / Credited to P&L BGL") ? "1" : "2");
        entity.setMode_amt(parseAmount(dataList.get(5))); // Safely parse and set the amount
        entity.setMode_reason(dataList.get(6));           // Reason for payment
        entity.setMode_branch(branchCode);                // Branch code
        entity.setMode_date(quarterEndDate);              // Quarter end date
        entity.setReportSubmissionId(submissionId);       // Submission ID

        return entity;
    }


    private int parseAmount(String amountStr) {
        try {
            return Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            log.info("Invalid amount '{}'. Defaulting to 0."+ amountStr);
            return 0;
        }
    }





    }


// This is tar mode Repo File Here

package com.tar.reportService.repository;

import com.tar.reportService.models.TAR_MODE_Extended;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tar.reportService.models.TAR_MODE;

import java.util.Optional;

public interface TarModeRepository extends JpaRepository<TAR_MODE,Integer> {


     TAR_MODE findByReportSubmissionId(@Param("submissionId")String submissionId);


     // Method to find a TAR_MODE entity by its modeID
     TAR_MODE findByModeid(int modeId);



}


// This is my modal Class 
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
    @Id
    @Column(name = "MODE_ID")
    private int modeid;
    
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

// This is my Extended Class
package com.tar.reportService.models;

import java.io.Serializable;
import java.util.Date;

public class TAR_MODE_Extended implements  Serializable{
    
private String mode_branch;
private Date mode_date;
private int modeid;
}



