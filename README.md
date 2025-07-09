import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FirmMasterService {

    @Autowired
    private FirmMasterRepository firmMasterRepository;

    public void saveFirmMasterDetails(Map<String, Object> payload) {
        // 1️⃣ Extract the List<Map<String, Object>> from payload safely
        Object firmsObject = payload.get("firms");

        if (!(firmsObject instanceof List<?> firmsList) || firmsList.isEmpty()) {
            // No valid data to process, exit early
            return;
        }

        // 2️⃣ Convert each Map to Firm_Master entity
        List<Firm_Master> firmEntities = firmsList.stream()
            .filter(item -> item instanceof Map)  // Ensure each item is a Map
            .map(item -> mapToFirmEntity((Map<String, Object>) item))
            .collect(Collectors.toList());

        // 3️⃣ Save all entities in batch
        firmMasterRepository.saveAll(firmEntities);
    }

    /**
     * Converts a Map<String, Object> into a Firm_Master entity safely.
     */
    private Firm_Master mapToFirmEntity(Map<String, Object> firmMap) {
        Firm_Master firm = new Firm_Master();

        firm.setBranchcode(parseInt(firmMap.get("BRANCH_CODE")));
        firm.setBranchname(getString(firmMap.get("BRANCH_NAME")));
        firm.setUcnno(parseInt(firmMap.get("UCN_NO")));
        firm.setFrnno(getString(firmMap.get("FRN_NO")));
        firm.setFirmname(getString(firmMap.get("FIRM_NAME")));
        firm.setPanno(getString(firmMap.get("PAN_NO")));
        firm.setGstnno(getString(firmMap.get("GSTN")));  // Key matches your incoming JSON
        firm.setAddress(getString(firmMap.get("FIRM_ADDR")));
        firm.setCity(getString(firmMap.get("CITY")));
        firm.setState(getString(firmMap.get("STATE")));
        firm.setDistrict(getString(firmMap.get("DISTRICT")));
        firm.setPincode(parseInt(firmMap.get("PIN_CODE")));
        firm.setMobno(parseInt(firmMap.get("MOB_NO")));
        firm.setContactperson(getString(firmMap.get("CONTACT_PERSON")));
        firm.setPocEmail(getString(firmMap.get("EMAIL")));
        firm.setFirmtype(getString(firmMap.get("ASSIGNMENT_TYPE")));
        firm.setPocDesignation(getString(firmMap.get("POC_DESIGNATION")));

        return firm;
    }

    /**
     * Safely parses an Object to Integer. Returns 0 if null or invalid.
     */
    private int parseInt(Object obj) {
        if (obj == null) return 0;
        try {
            return Integer.parseInt(obj.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Safely retrieves String value from Object. Returns empty string if null.
     */
    private String getString(Object obj) {
        return obj != null ? obj.toString() : "";
    }
}