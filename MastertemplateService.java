package com.crs.iamservice.Service;

import com.crs.iamservice.Model.MasterTemplate;
import com.crs.iamservice.Model.ResponseVO;
import com.crs.iamservice.Repository.MasterTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/*
 Author :V1012297
 Date:30/06/2025
 Desc: Master Template Service
*/

@Slf4j
@Service("MastertemplateService")
public class MastertemplateServiceImpl implements MastertemplateService {

    @Autowired
    MasterTemplateRepository masterTemplateRepository;
    // Fetch most Recently Saved Template
    public ResponseEntity<Map<String, Object>> getMasterTemplate() {
        log.info("inside getMasterTemplate");
        ResponseVO<String> responseVO = new ResponseVO<>();

        try {
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Success fetched Template Data");
            responseVO.setResult(masterTemplateRepository.gettemplate());
//            log.info("Result get :" + masterTemplateRepository.gettemplate());


        } catch (Exception e) {
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Failed to fetched Template Data");
            responseVO.setResult(null);

        }
        return new ResponseEntity(responseVO, HttpStatus.OK);
    }

    // Save Edited Master Template
    public ResponseEntity<Map<String, Object>> saveMasterTemplate(@RequestBody Map<String, Object> payload) {

        log.info("inside saveMasterTemplate");
        ResponseVO<Object> responseVO = new ResponseVO<>();
        // Extracting additional details from the input map
        Map<String, Object> data = (Map<String, Object>) payload.get("data");
        int updateResult = 0;
        log.info("Data get :" + data);
        try {
            updateResult = updateMasterList(payload);
            log.info("Data updated :{}", updateResult);
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Template Saved Successfully");
            responseVO.setResult(updateResult);
        } catch (Exception e) {
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Failed to save Template Data");
            responseVO.setResult(updateResult);
        }

        return new ResponseEntity(responseVO, HttpStatus.OK);
    }

    // Updates the master Template Table here
    private int updateMasterList(Map<String, Object> payload) {
        log.info("inside updateMasterList");
        Map<String, Object> data = (Map<String, Object>) payload.get("data");
        Map<String, Object> loginuserData = (Map<String, Object>) payload.get("user");
        MasterTemplate entity = new MasterTemplate();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int result = 0;


        try {

            int userId = Integer.parseInt(loginuserData.get("pf_number").toString());
//            int getSequence = masterTemplateRepository.getsequence();
//            log.info("getSequence :" + getSequence);

//            entity.setMastertemplateid(getSequence);
            entity.setMastertemplatedata(data.get("MASTER_TEMPLATE").toString());
            entity.setUpdatedby(userId);
            entity.setUpdatedtime(formatter.format(currentDateTime));
            entity.setTemplateflag("F");

            MasterTemplate saved = masterTemplateRepository.save(entity);
            result = saved.getMastertemplateid();
            log.info("Returned Saved Result ::" + saved.getMastertemplateid());
        } catch (Exception e) {
            log.error("Exception occurred while saving MasterTemplate" +e.getMessage());
        }

        return result;
    }

    // Fetch Default Template
    public ResponseEntity<Map<String, Object>> getDefaultTemplate() {
        log.info("Inside getDefaultTemplate");
        ResponseVO<String> responseVO = new ResponseVO<>();

        try {
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Success fetched Template Data");
            responseVO.setResult(masterTemplateRepository.getDefaultTemplate());


        } catch (Exception e) {
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Failed to fetched Template Data");
            responseVO.setResult(null);

        }
        return new ResponseEntity(responseVO, HttpStatus.OK);
    }

}
