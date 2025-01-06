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

        // Map and save entities for each table
        saveEntities(valueNameMap.get(0), CRSInduDvlpInc.class, induDvlpRepository);
        saveEntities(valueNameMap.get(1), CRSInfraDvlpInc.class, infraDvlpRepository);
        saveEntities(valueNameMap.get(2), CRSAgrDvlpInc.class, agrDvlpRepository);
        saveEntities(valueNameMap.get(3), CRSHousDvlpInc.class, housDvlpRepository);
    }

    /**
     * Maps rows of data to entities and saves them using the provided repository.
     * 
     * @param <T>       The entity type.
     * @param rows      The rows of data to be mapped.
     * @param clazz     The class type of the entity.
     * @param repository The repository to save the entities.
     */
    private <T> void saveEntities(List<List<String>> rows, Class<T> clazz, JpaRepository<T, ?> repository) {
        List<T> entities = new ArrayList<>();
        for (List<String> row : rows) {
            T entity = mapRowToEntity(row, clazz);
            entities.add(entity);
        }
        if (!entities.isEmpty()) {
            repository.saveAll(entities);
        }
    }

    /**
     * Maps a single row of data to an entity of the specified type.
     * 
     * @param <T>   The entity type.
     * @param row   The row of data to be mapped.
     * @param clazz The class type of the entity.
     * @return An entity populated with data from the row.
     */
    private <T> T mapRowToEntity(List<String> row, Class<T> clazz) {
        try {
            T entity = clazz.newInstance();

            if (clazz.equals(CRSInduDvlpInc.class)) {
                populateInduDvlpEntity((CRSInduDvlpInc) entity, row);
            } else if (clazz.equals(CRSInfraDvlpInc.class)) {
                populateInfraDvlpEntity((CRSInfraDvlpInc) entity, row);
            } else if (clazz.equals(CRSAgrDvlpInc.class)) {
                populateAgriDvlpEntity((CRSAgrDvlpInc) entity, row);
            } else if (clazz.equals(CRSHousDvlpInc.class)) {
                populateHousDvlpEntity((CRSHousDvlpInc) entity, row);
            }

            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Error mapping row to entity: " + e.getMessage());
        }
    }

    /**
     * Populates a CRSInduDvlpInc entity with data from a row.
     */
    private void populateInduDvlpEntity(CRSInduDvlpInc entity, List<String> row) {
        entity.setCrsInduDvlpBrno(row.get(0));
        entity.setCrsInduDvlpDate(parseDate(row.get(1)));
        entity.setCrsInduDvlpOther(row.get(2));
        entity.setCrsInduDvlpProcfee(row.get(3));
        entity.setCrsInduDvlpTotal(row.get(4));
        entity.setCrsInduDvlpTotalAdvances(row.get(5));
        entity.setReportMasterListIdFk(row.get(6));
    }

    /**
     * Populates a CRSInfraDvlpInc entity with data from a row.
     */
    private void populateInfraDvlpEntity(CRSInfraDvlpInc entity, List<String> row) {
        entity.setCrsInfraDvlpBrno(row.get(0));
        entity.setCrsInfraDvlpDate(parseDate(row.get(1)));
        entity.setCrsInfraDvlpOther(row.get(2));
        entity.setCrsInfraDvlpProcfee(row.get(3));
        entity.setCrsInfraDvlpTotal(row.get(4));
        entity.setCrsInfraDvlpTotalAdvances(row.get(5));
        entity.setReportMasterListIdFk(row.get(6));
    }

    /**
     * Populates a CRSAgrDvlpInc entity with data from a row.
     */
    private void populateAgriDvlpEntity(CRSAgrDvlpInc entity, List<String> row) {
        entity.setCrsAgriDvlpBrno(row.get(0));
        entity.setCrsAgriDvlpDate(parseDate(row.get(1)));
        entity.setCrsAgriDvlpOther(row.get(2));
        entity.setCrsAgriDvlpProcfee(row.get(3));
        entity.setCrsAgriDvlpTotal(row.get(4));
        entity.setCrsAgriDvlpTotalAdvances(row.get(5));
        entity.setReportMasterListIdFk(row.get(6));
    }

    /**
     * Populates a CRSHousDvlpInc entity with data from a row.
     */
    private void populateHousDvlpEntity(CRSHousDvlpInc entity, List<String> row) {
        entity.setCrsHousDvlpBrno(row.get(0));
        entity.setCrsHousDvlpDate(parseDate(row.get(1)));
        entity.setCrsHousDvlpOther(row.get(2));
        entity.setCrsHousDvlpProcfee(row.get(3));
        entity.setCrsHousDvlpTotal(row.get(4));
        entity.setCrsHousDvlpTotalAdvances(row.get(5));
        entity.setReportMasterListIdFk(row.get(6));
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