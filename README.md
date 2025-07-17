package com.crs.iamservice.Repository;

import com.crs.iamservice.Model.FirmEmpanelment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirmEmpanelmentRepository extends JpaRepository<FirmEmpanelment, String> {

    // Fetch all records by financial year
    List<FirmEmpanelment> findByFinancialyear(String financialyear);

    // Delete all records by financial year
    void deleteByFinancialyear(String financialyear);
}

xxxx


package com.crs.iamservice.Repository;

import com.crs.iamservice.Model.IAM_FIRM_ARCHIVE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IamFirmArchiveRepository extends JpaRepository<IAM_FIRM_ARCHIVE, Integer> {
}


xxxx

package com.crs.iamservice.Service;

import com.crs.iamservice.Model.FirmEmpanelment;
import com.crs.iamservice.Model.IAM_FIRM_ARCHIVE;
import com.crs.iamservice.Repository.FirmEmpanelmentRepository;
import com.crs.iamservice.Repository.IamFirmArchiveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class DataMoveService {

    private final FirmEmpanelmentRepository firmEmpanelmentRepository;
    private final IamFirmArchiveRepository iamFirmArchiveRepository;

    public DataMoveService(FirmEmpanelmentRepository firmEmpanelmentRepository,
                            IamFirmArchiveRepository iamFirmArchiveRepository) {
        this.firmEmpanelmentRepository = firmEmpanelmentRepository;
        this.iamFirmArchiveRepository = iamFirmArchiveRepository;
    }

    /**
     * Move all records for a given financial year from IAM_FIRM_EMPANELMENT
     * to IAM_FIRM_ARCHIVE with extra fields archivedby and archivedtimestamp.
     */
    @Transactional
    public String moveDataByFinancialYear(String financialYear, String archivedBy) {
        // 1. Fetch all records for given financial year
        List<FirmEmpanelment> sourceData = firmEmpanelmentRepository.findByFinancialyear(financialYear);

        if (sourceData.isEmpty()) {
            return "No records found for financial year: " + financialYear;
        }

        // 2. Prepare list for IAM_FIRM_ARCHIVE
        AtomicInteger counter = new AtomicInteger(1);
        List<IAM_FIRM_ARCHIVE> archiveList = sourceData.stream()
                .map(src -> {
                    IAM_FIRM_ARCHIVE archive = new IAM_FIRM_ARCHIVE();
                    archive.setArchiveid(counter.getAndIncrement()); // or use sequence if DB handles
                    archive.setFrnNumber(src.getFrnNumber());
                    archive.setFirmName(src.getFirmName());
                    archive.setEmpanelmentType(src.getEmpanelmentType());
                    archive.setEmpanelmentSubType(src.getEmpanelmentSubType());
                    archive.setEmpaneledBy(src.getEmpaneledBy());
                    archive.setFinancialyear(src.getFinancialyear());
                    archive.setRequeststatus(src.getRequeststatus());
                    archive.setEmpaneledStatus(src.getEmpaneledStatus());
                    archive.setMobno(src.getMobno());
                    archive.setContactperson(src.getContactperson());
                    archive.setPocEmail(src.getPocEmail());
                    archive.setPocDesignation(src.getPocDesignation());

                    // Additional Columns
                    archive.setArchivedby(archivedBy);
                    archive.setArchivedtimestamp(Date.from(Instant.now()));

                    return archive;
                }).collect(Collectors.toList());

        // 3. Save to archive table
        iamFirmArchiveRepository.saveAll(archiveList);

        // 4. Delete from source table
        firmEmpanelmentRepository.deleteByFinancialyear(financialYear);

        return "Successfully moved " + archiveList.size() + " records for Financial Year: " + financialYear;
    }
}


xxxx

package com.crs.iamservice.Controller;

import com.crs.iamservice.Service.DataMoveService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/iam")
public class DataMoveController {

    private final DataMoveService dataMoveService;

    public DataMoveController(DataMoveService dataMoveService) {
        this.dataMoveService = dataMoveService;
    }

    /**
     * API: Move all records from EMPANELMENT to ARCHIVE for given financial year.
     * Example: POST /api/iam/move?financialYear=2024-25&archivedBy=Admin
     */
    @PostMapping("/move")
    public String moveData(@RequestParam String financialYear, @RequestParam String archivedBy) {
        return dataMoveService.moveDataByFinancialYear(financialYear, archivedBy);
    }
}