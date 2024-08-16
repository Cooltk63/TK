import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class YourService {

    private final CrsStndAssetsRepository crsStndAssetsRepository;
    // ... other dependencies

    public ResponseEntity update(Map<String, Object> map) {

        ResponseVO<String> responseVO = new ResponseVO();
        // Data Receive here
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        Map<String, Object> loginuserData = (Map<String, Object>) map.get("user");

        CrsStndAssets entity = new CrsStndAssets();
        List<String> dataList = (List<String>) data.get("value");

        // Condition Check Variable INFRA or NONINFRA
        String infraNonInfraList = dataList.get(1);

        try {

            // Assign data to Bean for Save based on ID is Present or Null
            Integer id = dataList.get(5) != null ? Integer.parseInt(dataList.get(5)) : null;

            if (id != null) {
                Optional<CrsStndAssets> existingEntity = crsStndAssetsRepository.findById(id);
                if (existingEntity.isPresent()) {
                    entity = existingEntity.get();
                } else {
                    log.info("ID {} not found for update", id);
                    responseVO.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    responseVO.setMessage("Invalid ID provided. Record not found.");
                    responseVO.setResult("false");
                    return new ResponseEntity<>(responseVO, HttpStatus.BAD_REQUEST);
                }
            }

            // Insert the Branch while 1st Insert
            entity.setStndastsbranch((String) loginuserData.get("branch_code"));

            // Insert the QED while 1st Insert
            entity.setStndastsdate((String) loginuserData.get("qed"));

            // 1st Element :: nameOfBorrowerList
            entity.setStndastsnameofborrower(dataList.get(0));

            //2nd Element :: infraNonInfraList
            entity.setStndastsinfranoninfra(dataList.get(1));

            //3rd Element Based on Condition
            if (infraNonInfraList.equalsIgnoreCase("INFRA")) {
                //:: infraAccounts2YearsList
                entity.setStndastsinfraaccts2YRS(dataList.get(2));
                // :: infraWithin2YearsList
                entity.setStndastsinfrawithin2YRS(dataList.get(3));
            } else if (infraNonInfraList.equalsIgnoreCase("NONINFRA")) {
                // :: infraAccounts2YearsList
                entity.setStndastsnoninfraaccts1YR(dataList.get(2));
                // :: infraWithin2YearsList
                entity.setStndastsnoninfrawithin1YR(dataList.get(3));
            }

            log.info("Entity Data: " + entity);

            // Method for updating the data in crs_stnd_assets table
            CrsStndAssets result = crsStndAssetsRepository.save(entity);

            log.info("Result Received");

            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Data Inserted/Updated successfully");
            responseVO.setResult("true");
        } catch (RuntimeException e) {
            log.info("exception Occurred");
            responseVO.setResult("false");
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage("Exception Occurred" + e.getMessage());
        }

        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }
}
