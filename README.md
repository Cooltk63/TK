package com.crs.renderService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;



@Getter
@Setter
@Entity
@IdClass(CRSInduDvlpIncExtended.class)
@Table(name = "CRS_INDU_DVLP_INC")
public class CRSInduDvlpInc {

    
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SR_NO")
    public String srNo;

    @Id
    @Column(name = "CRS_INDU_DVLP_BRNO", length = 5)
    public String branchcode;

    @Id
    @Column(name = "CRS_INDU_DVLP_DATE")
    // @Temporal(TemporalType.DATE)
    public Date date;

    @Column(name = "CRS_INDU_DVLP_OTHER", length = 30)
    public String other;

    @Column(name = "CRS_INDU_DVLP_PROCFEE", length = 30)
    public String procfee;

    @Column(name = "CRS_INDU_DVLP_TOTAL", length = 30)
    public String total;

    @Column(name = "CRS_INDU_DVLP_TOTAL_ADVANCES", length = 30)
    public String totaladvances;

    @Column(name = "REPORT_MASTER_LIST_ID_FK", length = 10)
    public String submissionid;
}


This above provided model class and  below is ist ID Class

package com.crs.renderService.models;

import java.io.Serializable;
import java.util.Date;


public class CRSInduDvlpIncExtended implements Serializable {
    
    public String branchcode;

    public Date date;
}

This is below repository interface as per below

package com.crs.renderService.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crs.renderService.models.CRSInduDvlpInc;
import com.crs.renderService.models.CRSInduDvlpIncExtended;

@Repository
public interface CRSInduDvIncRepository extends JpaRepository<CRSInduDvlpInc,CRSInduDvlpIncExtended> {

   
    @Query(nativeQuery = true,value = "select rm.branch_code ,rm.REPORT_DATE,ax.CRS_INDU_DVLP_PROCFEE,ax.CRS_INDU_DVLP_OTHER,ax.CRS_INDU_DVLP_TOTAL,ax.CRS_INDU_DVLP_TOTAL_ADVANCES " + //
                " from report_submission rm , CRS_INDU_DVLP_INC ax " +
                " where rm.SUBMISSION_ID=ax.REPORT_MASTER_LIST_ID_FK and rm.SUBMISSION_ID=:submission_id")
    public List<String>getInduaData(@Param("submission_id")String submission_id);
    
    CRSInduDvlpInc findBydateAndbranchcode(Date date, String branchNumber /*, String crsInduDvlpId */);
    
    
}

