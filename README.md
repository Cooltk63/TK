import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl {

    @Autowired
    private CRSInduDvlpRepository induDvlpRepository;

    @Autowired
    private CRSInfraDvlpRepository infraDvlpRepository;

    @Autowired
    private CRSAgrDvlpRepository agrDvlpRepository;

    @Autowired
    private CRSHousDvlpRepository housDvlpRepository;

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Saves data for all four tables based on the ordered list provided by the frontend.
     * 
     * @param dataMap A map containing a key `valueNameMap` holding the ordered list of data:
     *                - 1st list: Data for `induDvlp`.
     *                - 2nd list: Data for `infraDvlp`.
     *                - 3rd list: Data for `agriDvlp`.
     *                - 4th list: Data for `housDvlp`.
     */
    @Transactional
    public void saveAllTablesData(Map<String, List<List<String>>> dataMap) {
        // Extract the ordered list from the map
        List<List<String>> valueNameMap = dataMap.get("valueNameMap");

        // Validate the data structure
        if (valueNameMap == null || valueNameMap.size() < 4) {
            throw new IllegalArgumentException("Invalid data structure. Expecting at least 4 lists.");
        }

        // Map each list to its corresponding entity and save them
        List<CRSInduDvlpInc> induList = mapToEntities(valueNameMap.get(0), CRSInduDvlpInc.class);
        List<CRSInfraDvlpInc> infraList = mapToEntities(valueNameMap.get(1), CRSInfraDvlpInc.class);
        List<CRSAgrDvlpInc> agrList = mapToEntities(valueNameMap.get(2), CRSAgrDvlpInc.class);
        List<CRSHousDvlpInc> housList = mapToEntities(valueNameMap.get(3), CRSHousDvlpInc.class);

        // Save all entities in batches
        if (!induList.isEmpty()) induDvlpRepository.saveAll(induList);
        if (!infraList.isEmpty()) infraDvlpRepository.saveAll(infraList);
        if (!agrList.isEmpty()) agrDvlpRepository.saveAll(agrList);
        if (!housList.isEmpty()) housDvlpRepository.saveAll(housList);
    }

    /**
     * Maps a list of rows to entities of a specified type.
     *
     * @param rows The list of rows containing table data.
     * @param clazz The entity class type.
     * @return A list of entities populated with the data.
     * @param <T> Generic type representing the entity class.
     */
    private <T> List<T> mapToEntities(List<List<String>> rows, Class<T> clazz) {
        List<T> entities = new ArrayList<>();

        for (List<String> row : rows) {
            try {
                T entity = clazz.newInstance();

                if (clazz.equals(CRSInduDvlpInc.class)) {
                    CRSInduDvlpInc induEntity = (CRSInduDvlpInc) entity;
                    induEntity.setCrsInduDvlpBrno(row.get(0));
                    induEntity.setCrsInduDvlpDate(parseDate(row.get(1)));
                    induEntity.setCrsInduDvlpOther(row.get(2));
                    induEntity.setCrsInduDvlpProcfee(row.get(3));
                    induEntity.setCrsInduDvlpTotal(row.get(4));
                    induEntity.setCrsInduDvlpTotalAdvances(row.get(5));
                    induEntity.setReportMasterListIdFk(row.get(6));
                } else if (clazz.equals(CRSInfraDvlpInc.class)) {
                    CRSInfraDvlpInc infraEntity = (CRSInfraDvlpInc) entity;
                    infraEntity.setCrsInfraDvlpBrno(row.get(0));
                    infraEntity.setCrsInfraDvlpDate(parseDate(row.get(1)));
                    infraEntity.setCrsInfraDvlpOther(row.get(2));
                    infraEntity.setCrsInfraDvlpProcfee(row.get(3));
                    infraEntity.setCrsInfraDvlpTotal(row.get(4));
                    infraEntity.setCrsInfraDvlpTotalAdvances(row.get(5));
                    infraEntity.setReportMasterListIdFk(row.get(6));
                } else if (clazz.equals(CRSAgrDvlpInc.class)) {
                    CRSAgrDvlpInc agrEntity = (CRSAgrDvlpInc) entity;
                    agrEntity.setCrsAgriDvlpBrno(row.get(0));
                    agrEntity.setCrsAgriDvlpDate(parseDate(row.get(1)));
                    agrEntity.setCrsAgriDvlpOther(row.get(2));
                    agrEntity.setCrsAgriDvlpProcfee(row.get(3));
                    agrEntity.setCrsAgriDvlpTotal(row.get(4));
                    agrEntity.setCrsAgriDvlpTotalAdvances(row.get(5));
                    agrEntity.setReportMasterListIdFk(row.get(6));
                } else if (clazz.equals(CRSHousDvlpInc.class)) {
                    CRSHousDvlpInc housEntity = (CRSHousDvlpInc) entity;
                    housEntity.setCrsHousDvlpBrno(row.get(0));
                    housEntity.setCrsHousDvlpDate(parseDate(row.get(1)));
                    housEntity.setCrsHousDvlpOther(row.get(2));
                    housEntity.setCrsHousDvlpProcfee(row.get(3));
                    housEntity.setCrsHousDvlpTotal(row.get(4));
                    housEntity.setCrsHousDvlpTotalAdvances(row.get(5));
                    housEntity.setReportMasterListIdFk(row.get(6));
                }

                entities.add(entity);
            } catch (Exception e) {
                throw new RuntimeException("Error mapping row to entity: " + e.getMessage());
            }
        }

        return entities;
    }

    /**
     * Converts a date string into a Date object. Returns null if the input is empty.
     *
     * @param dateString The date string in "yyyy-MM-dd" format.
     * @return A Date object or null if the input is empty.
     */
    private Date parseDate(String dateString) {
        try {
            return dateString == null || dateString.isEmpty()
                    ? null
                    : new SimpleDateFormat(DATE_FORMAT).parse(dateString);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString);
        }
    }
}