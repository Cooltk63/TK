import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * Saves data for all four tables based on the input provided by the frontend.
     * The data is received in a List<List<String>> format, where each row represents 
     * a specific table type and its associated data.
     *
     * @param data A list of rows where:
     *             - The first column indicates the table type ("INDU", "INFRA", "AGRI", "HOUS").
     *             - Remaining columns hold the corresponding table data.
     */
    @Transactional
    public void saveAllTablesData(List<List<String>> data) {
        // Prepare lists to hold entities for each table
        List<CRSInduDvlpInc> induList = new ArrayList<>();
        List<CRSInfraDvlpInc> infraList = new ArrayList<>();
        List<CRSAgrDvlpInc> agrList = new ArrayList<>();
        List<CRSHousDvlpInc> housList = new ArrayList<>();

        // Process each row of data
        for (List<String> row : data) {
            // Determine the table type from the first column
            String tableType = row.get(0);

            // Map the row data to the appropriate entity based on the table type
            switch (tableType) {
                case "INDU":
                    induList.add(mapToInduEntity(row));
                    break;
                case "INFRA":
                    infraList.add(mapToInfraEntity(row));
                    break;
                case "AGRI":
                    agrList.add(mapToAgriEntity(row));
                    break;
                case "HOUS":
                    housList.add(mapToHousEntity(row));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid table type: " + tableType);
            }
        }

        // Save all entities to their respective repositories in a batch
        if (!induList.isEmpty()) induDvlpRepository.saveAll(induList);
        if (!infraList.isEmpty()) infraDvlpRepository.saveAll(infraList);
        if (!agrList.isEmpty()) agrDvlpRepository.saveAll(agrList);
        if (!housList.isEmpty()) housDvlpRepository.saveAll(housList);
    }

    /**
     * Maps a row to a CRSInduDvlpInc entity.
     *
     * @param row The data row as a List<String>.
     * @return A CRSInduDvlpInc entity populated with the data.
     */
    private CRSInduDvlpInc mapToInduEntity(List<String> row) {
        CRSInduDvlpInc entity = new CRSInduDvlpInc();
        entity.setCrsInduDvlpBrno(row.get(1));
        entity.setCrsInduDvlpDate(parseDate(row.get(2)));
        entity.setCrsInduDvlpOther(row.get(3));
        entity.setCrsInduDvlpProcfee(row.get(4));
        entity.setCrsInduDvlpTotal(row.get(5));
        entity.setCrsInduDvlpTotalAdvances(row.get(6));
        entity.setReportMasterListIdFk(row.get(7));
        return entity;
    }

    /**
     * Maps a row to a CRSInfraDvlpInc entity.
     *
     * @param row The data row as a List<String>.
     * @return A CRSInfraDvlpInc entity populated with the data.
     */
    private CRSInfraDvlpInc mapToInfraEntity(List<String> row) {
        CRSInfraDvlpInc entity = new CRSInfraDvlpInc();
        entity.setCrsInfraDvlpBrno(row.get(1));
        entity.setCrsInfraDvlpDate(parseDate(row.get(2)));
        entity.setCrsInfraDvlpOther(row.get(3));
        entity.setCrsInfraDvlpProcfee(row.get(4));
        entity.setCrsInfraDvlpTotal(row.get(5));
        entity.setCrsInfraDvlpTotalAdvances(row.get(6));
        entity.setReportMasterListIdFk(row.get(7));
        return entity;
    }

    /**
     * Maps a row to a CRSAgrDvlpInc entity.
     *
     * @param row The data row as a List<String>.
     * @return A CRSAgrDvlpInc entity populated with the data.
     */
    private CRSAgrDvlpInc mapToAgriEntity(List<String> row) {
        CRSAgrDvlpInc entity = new CRSAgrDvlpInc();
        entity.setCrsAgriDvlpBrno(row.get(1));
        entity.setCrsAgriDvlpDate(parseDate(row.get(2)));
        entity.setCrsAgriDvlpOther(row.get(3));
        entity.setCrsAgriDvlpProcfee(row.get(4));
        entity.setCrsAgriDvlpTotal(row.get(5));
        entity.setCrsAgriDvlpTotalAdvances(row.get(6));
        entity.setReportMasterListIdFk(row.get(7));
        return entity;
    }

    /**
     * Maps a row to a CRSHousDvlpInc entity.
     *
     * @param row The data row as a List<String>.
     * @return A CRSHousDvlpInc entity populated with the data.
     */
    private CRSHousDvlpInc mapToHousEntity(List<String> row) {
        CRSHousDvlpInc entity = new CRSHousDvlpInc();
        entity.setCrsHousDvlpBrno(row.get(1));
        entity.setCrsHousDvlpDate(parseDate(row.get(2)));
        entity.setCrsHousDvlpOther(row.get(3));
        entity.setCrsHousDvlpProcfee(row.get(4));
        entity.setCrsHousDvlpTotal(row.get(5));
        entity.setCrsHousDvlpTotalAdvances(row.get(6));
        entity.setReportMasterListIdFk(row.get(7));
        return entity;
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