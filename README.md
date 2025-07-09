import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FirmMasterService {

    @Autowired
    private FirmMasterRepository firmMasterRepository;

    /**
     * Saves firm details to the database after validating the input.
     * Returns true if successful, false otherwise.
     */
    public boolean saveFirmMasterDetails(Map<String, Object> payload) {
        try {
            // 1️⃣ Validate: Check if "firms" key exists and is a non-empty list
            Object firmsObject = payload.get("firms");

            if (!(firmsObject instanceof List<?> firmsList) || firmsList.isEmpty()) {
                // Invalid or empty list — no data to save
                return false;
            }

            // 2️⃣ Convert each Map to Firm_Master, skip invalid maps safely
            List<Firm_Master> firmEntities = firmsList.stream()
                .filter(item -> item instanceof Map)  // Ensure it's a Map
                .map(item -> mapToFirmEntity((Map<String, Object>) item))
                .filter(Objects::nonNull)  // Skip invalid maps that failed validation
                .collect(Collectors.toList());

            if (firmEntities.isEmpty()) {
                // No valid entities to save
                return false;
            }

            // 3️⃣ Save all valid entities in bulk
            firmMasterRepository.saveAll(firmEntities);

            return true; // ✅ Successfully saved
        } catch (Exception e) {
            e.printStackTrace();
            return false; // ❌ Any unexpected error caught here
        }
    }

    /**
     * Maps input map to Firm_Master entity. Returns null if required fields are missing.
     */
    private Firm_Master mapToFirmEntity(Map<String, Object> firmMap) {
        // Basic validation: FRN_NO and FIRM_NAME are required (you can add more if needed)
        String frnNo = getString(firmMap.get("FRN_NO"));
        String firmName = getString(firmMap.get("FIRM_NAME"));

        if (frnNo.isEmpty() || firmName.isEmpty()) {
            // Required fields missing — skip this entry
            return null;
        }

        Firm_Master firm = new Firm_Master();
        firm.setBranchcode(parseInt(firmMap.get("BRANCH_CODE")));
        firm.setBranchname(getString(firmMap.get("BRANCH_NAME")));
        firm.setUcnno(parseInt(firmMap.get("UCN_NO")));
        firm.setFrnno(frnNo);
        firm.setFirmname(firmName);
        firm.setPanno(getString(firmMap.get("PAN_NO")));
        firm.setGstnno(getString(firmMap.get("GSTN")));
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

    private int parseInt(Object obj) {
        if (obj == null) return 0;
        try {
            return Integer.parseInt(obj.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String getString(Object obj) {
        return obj != null ? obj.toString().trim() : "";
    }
}