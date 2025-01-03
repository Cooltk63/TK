import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportsMasterListRepository reportsMasterListRepository;

    @Autowired
    private CRSInduDvlpIncRepository crsInduDvlpIncRepository;

    @Autowired
    private CRSInfraDvlpIncRepository crsInfraDvlpIncRepository;

    @Autowired
    private CRSAgrDvlpIncRepository crsAgrDvlpIncRepository;

    @Autowired
    private CRSHousDvlpIncRepository crsHousDvlpIncRepository;

    public List<List<String>> getMakerReportScreenDetails(Long reportId) {
        List<List<String>> result = new ArrayList<>();

        // Fetch master report data
        reportsMasterListRepository.findByReportId(reportId).ifPresent(report -> {
            result.add(Arrays.asList(
                report.getBranchCode(),
                report.getQuarter(),
                report.getFinancialYear()
            ));
        });

        // Fetch industrial development data
        crsInduDvlpIncRepository.findByReportMasterListId(reportId).forEach(data -> {
            result.add(Arrays.asList(
                data.getProcessingFee(),
                data.getOtherCharges(),
                data.getTotal(),
                data.getTotalAdvances()
            ));
        });

        // Fetch infrastructure development data
        crsInfraDvlpIncRepository.findByReportMasterListId(reportId).forEach(data -> {
            result.add(Arrays.asList(
                data.getProcessingFee(),
                data.getOtherCharges(),
                data.getTotal(),
                data.getTotalAdvances()
            ));
        });

        // Fetch agriculture development data
        crsAgrDvlpIncRepository.findByReportMasterListId(reportId).forEach(data -> {
            result.add(Arrays.asList(
                data.getProcessingFee(),
                data.getOtherCharges(),
                data.getTotal(),
                data.getTotalAdvances()
            ));
        });

        // Fetch housing development data
        crsHousDvlpIncRepository.findByReportMasterListId(reportId).forEach(data -> {
            result.add(Arrays.asList(
                data.getProcessingFee(),
                data.getOtherCharges(),
                data.getTotal(),
                data.getTotalAdvances()
            ));
        });

        return result;
    }
}


xxxxxxx

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ReportsMasterListRepository extends JpaRepository<ReportsMasterList, Long> {
    Optional<ReportsMasterList> findByReportId(Long reportId);
}

xxxxx

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CRS_INDU_DVLP_INC")
@Getter
@Setter
public class CRSInduDvlpInc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated primary key
    private Long id;

    @Column(name = "REPORT_MASTER_LIST_ID_FK") // Foreign key referencing reports_master_list
    private Long reportMasterListId;

    @Column(name = "CRS_INDU_DVLP_PROCFEE") // Processing fee column
    private String processingFee;

    @Column(name = "CRS_INDU_DVLP_OTHER") // Other charges column
    private String otherCharges;

    @Column(name = "CRS_INDU_DVLP_TOTAL") // Total column
    private String total;

    @Column(name = "CRS_INDU_DVLP_TOTAL_ADVANCES") // Total advances column
    private String totalAdvances;
}

xxxxx


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reports_master_list")
@Getter
@Setter
public class ReportsMasterList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated primary key
    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "branch_code") // Branch code column
    private String branchCode;

    @Column(name = "quarter") // Quarter column
    private String quarter;

    @Column(name = "financial_year") // Financial year column
    private String financialYear;
}