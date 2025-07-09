import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FirmMasterService {

    @Autowired
    private FirmMasterRepository firmMasterRepository;

    /**
     * This method takes the payload containing firm details in List<Map<String, Object>> form,
     * maps each map to the Firm_Master entity, and saves them to the database.
     *
     * If a firm with the same FRN_NO (primary key) exists → it will be updated.
     * If it doesn't exist → it will be inserted as new.
     *
     * @param payload The incoming data containing "firms" key with List<Map<String, Object>>.
     * @return true if data is saved (inserted/updated), false if invalid or error.
     */
    public boolean saveOrUpdateFirmMasterDetails(Map<String, Object> payload) {
        try {
            // 1️⃣ Extract the "firms" list safely from the incoming payload
            Object firmsObject = payload.get("firms");

            // 2️⃣ Validate that the object is indeed a List and not null/empty
            if (!(firmsObject instanceof List<?> firmsList) || firmsList.isEmpty()) {
                return false;  // No valid data → return false immediately
            }

            List<Firm_Master> firmsToSave = new ArrayList<>();

            // 3️⃣ Iterate over each Map<String, Object> from the list
            for (Object item : firmsList) {
                if (item instanceof Map) {
                    Map<String, Object> firmMap = (Map<String, Object>) item;

                    // 4️⃣ Map the incoming map to a Firm_Master entity object
                    Firm_Master firmEntity = mapToFirmEntity(firmMap);

                    if (firmEntity != null) {
                        // 5️⃣ Add to the list of entities to save
                        firmsToSave.add(firmEntity);
                    }
                }
            }

            // 6️⃣ Check if there is any valid entity to save
            if (!firmsToSave.isEmpty()) {
                // 👉 This saveAll() automatically handles:
                //    ➔ Insert if FRN_NO doesn't exist
                //    ➔ Update if FRN_NO already exists
                firmMasterRepository.saveAll(firmsToSave);
                return true;  // Successfully saved/updated
            }

            return false;  // Nothing valid to save
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // Unexpected error during processing
        }
    }

    /**
     * Converts a single Map<String, Object> to a Firm_Master entity.
     * Performs minimal validation by ensuring FRN_NO and FIRM_NAME are present.
     *
     * @param firmMap The incoming map with firm data.
     * @return Firm_Master entity or null if required fields missing.
     */
    private Firm_Master mapToFirmEntity(Map<String, Object> firmMap) {
        String frnNo = getString(firmMap.get("FRN_NO"));
        String firmName = getString(firmMap.get("FIRM_NAME"));

        // ❗ Basic validation: Both FRN_NO and FIRM_NAME must be present
        if (frnNo.isEmpty() || firmName.isEmpty()) {
            return null;  // Skip this map — cannot save/update without key fields
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

    /**
     * Safely converts an Object to an int, returns 0 if conversion fails or is null.
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
     * Safely converts an Object to a trimmed String, returns empty string if null.
     */
    private String getString(Object obj) {
        return obj != null ? obj.toString().trim() : "";
    }
}