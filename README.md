    public ResponseEntity<ResponseVO<Boolean>> saveStaticDetails(Map<String, Object> map) {

        // Extracting additional details from the input map
        Map<String, Object> data = (Map<String, Object>) map.get("data");

        // Extracting logged user data
        Map<String, String> loginUserData = (Map<String, String>) map.get("user");

        // Getting submission ID from the data map
        int submissionId = (int) data.get("submissionId");

        // Initializing response object
        ResponseVO<Boolean> responseVO = new ResponseVO<>();
        Map<String, Object> resultDataMap = new HashMap<>();

        // List of ROW data (List<List<String>> format)
        List<List<String>> mainList = (List<List<String>>) data.get("value");

        try {
            // Parse the quarterEndDate from user data
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date liabilityDate = dateFormat.parse(loginUserData.get("quarterEndDate"));

            log.info("Liability Date: " + liabilityDate);
            log.info("Main List Data: " + mainList);

            // Process each list (row) inside the main list
            for (List<String> dataList : mainList) {

                // Set the ROW-ID (Liability ID)
                String crsLiabilityId = String.valueOf(Integer.parseInt(dataList.get(7)));
                log.info("crsLiabilityId: " + crsLiabilityId);

                // Check if an entity with the given params exists in the database
                CrsLiability existingEntity = crsLiabilityRepository.findByLiabilityDateAndLiabilityBranchAndCrsLiabilityId(
                        liabilityDate, loginUserData.get("branch_code"), crsLiabilityId);

                // If no existing entity, create a new one
                if (existingEntity == null) {
                    log.info("No existing entity found. Inserting new record.");
                    CrsLiability newEntity = setEntity(dataList, loginUserData.get("quarterEndDate"), loginUserData.get("branch_code"), submissionId);
                    crsLiabilityRepository.save(newEntity);

                } else {
                    // Update existing entity if it exists
                    log.info("Existing entity found. Updating record.");

                    // Update fields
                    log.info("Updating existing entity data");
                    existingEntity = setEntity(dataList, loginUserData.get("quarterEndDate"), loginUserData.get("branch_code"), submissionId);

                    // Save the updated entity
                    crsLiabilityRepository.save(existingEntity);
                }
            }

            // If all records processed successfully
            responseVO.setMessage("All data updated successfully.");
//            resultDataMap.put("status", true);
//            responseVO.setResult(resultDataMap);
            responseVO.setResult(true);
            responseVO.setStatusCode(HttpStatus.OK.value());

        } catch (ParseException e) {
            // Handle date parsing errors
            log.info("Error parsing date: " + e.getMessage());
            responseVO.setMessage("Invalid date format provided.");
//            resultDataMap.put("status", false);
//            responseVO.setResult(resultDataMap);
            responseVO.setResult(false);
            responseVO.setStatusCode(HttpStatus.BAD_REQUEST.value());

        } catch (RuntimeException e) {
            // Handle unexpected runtime exceptions
            log.info("Exception occurred: " + e.getMessage());
            responseVO.setMessage("An unexpected error occurred.");
//            resultDataMap.put("status", false);
//            responseVO.setResult(resultDataMap);
            responseVO.setResult(false);
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        // Return response in the specified format
        return new ResponseEntity<>(responseVO, HttpStatus.valueOf(responseVO.getStatusCode()));
    }


This is above sample method i have alredy written for your reference.use above referenced method for generating my required code and use the map as per the above provided in code see how FE sending map and values.

also sharing my repository autowired objects name as per below
crsInduDvIncRepository
crsInfraDvIncRepository
crsAgriDvIncRepository
crsHousDvIncRepository

also sharing the models data
package com.crs.renderService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "CRS_AGRI_DVLP_INC")
@Getter
@Setter
@IdClass(CRSAgrDvlpIncExtended.class)
public class CRSAgrDvlpInc {

    
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SR_NO")
    private String srNo;
    @Id
    @Column(name = "CRS_AGRI_DVLP_BRNO", length = 5)
    private String branchNumber;

    @Id
    @Column(name = "CRS_AGRI_DVLP_DATE")
    // @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "CRS_AGRI_DVLP_OTHER", length = 30)
    private String other;

    @Column(name = "CRS_AGRI_DVLP_PROCFEE", length = 30)
    private String processingFee;

    @Column(name = "CRS_AGRI_DVLP_TOTAL", length = 30)
    private String total;

    @Column(name = "CRS_AGRI_DVLP_TOTAL_ADVANCES", length = 30)
    private String totalAdvances;

    @Column(name = "REPORT_MASTER_LIST_ID_FK", length = 10)
    private String reportMasterListIdFk;
}
xxx


package com.crs.renderService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "CRS_INDU_DVLP_INC")
@Getter
@Setter
@IdClass(CRSInduDvlpIncExtended.class)
public class CRSInduDvlpInc {

    
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SR_NO")
    private String srNo;

    @Id
    @Column(name = "CRS_INDU_DVLP_BRNO", length = 5)
    private String branchNumber;

    @Id
    @Column(name = "CRS_INDU_DVLP_DATE")
    // @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "CRS_INDU_DVLP_OTHER", length = 30)
    private String other;

    @Column(name = "CRS_INDU_DVLP_PROCFEE", length = 30)
    private String processingFee;

    @Column(name = "CRS_INDU_DVLP_TOTAL", length = 30)
    private String total;

    @Column(name = "CRS_INDU_DVLP_TOTAL_ADVANCES", length = 30)
    private String totalAdvances;

    @Column(name = "REPORT_MASTER_LIST_ID_FK", length = 10)
    private String reportMasterListIdFk;
}


xxxx

package com.crs.renderService.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "CRS_INFRA_DVLP_INC")
@Getter
@Setter
@IdClass(CRSInfraDvlpIncExtended.class)
public class CRSInfraDvlpInc {

    
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SR_NO")
    private String srNo;

    @Id
    @Column(name = "CRS_INFRA_DVLP_BRNO", length = 5)
    private String branchNumber;

    @Id
    @Column(name = "CRS_INFRA_DVLP_DATE")
    // @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "CRS_INFRA_DVLP_OTHER", length = 30)
    private String other;

    @Column(name = "CRS_INFRA_DVLP_PROCFEE", length = 30)
    private String processingFee;

    @Column(name = "CRS_INFRA_DVLP_TOTAL", length = 30)
    private String total;

    @Column(name = "CRS_INFRA_DVLP_TOTAL_ADVANCES", length = 30)
    private String totalAdvances;

    @Column(name = "REPORT_MASTER_LIST_ID_FK", length = 10)
    private String reportMasterListIdFk;
}



XXX

package com.crs.renderService.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "CRS_HOUS_DVLP_INC")
@Getter
@Setter
@IdClass(CRSHousDvlpIncExtended.class)
public class CRSHousDvlpInc {

   
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SR_NO")
    private String srNo;

    @Id
    @Column(name = "CRS_HOUS_DVLP_BRNO", length = 5)
    private String branchNumber;

    @Id
    @Column(name = "CRS_HOUS_DVLP_DATE")
    // @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "CRS_HOUS_DVLP_OTHER", length = 30)
    private String other;

    @Column(name = "CRS_HOUS_DVLP_PROCFEE", length = 30)
    private String processingFee;

    @Column(name = "CRS_HOUS_DVLP_TOTAL", length = 30)
    private String total;

    @Column(name = "CRS_HOUS_DVLP_TOTAL_ADVANCES", length = 30)
    private String totalAdvances;

    @Column(name = "REPORT_MASTER_LIST_ID_FK", length = 10)
    private String reportMasterListIdFk;
}


