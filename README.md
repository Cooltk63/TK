import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "CRS_INDU_DVLP_INC")
@Getter
@Setter
public class CRSInduDvlpInc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SR_NO")
    private String srNo;

    @Column(name = "CRS_INDU_DVLP_BRNO", length = 5)
    private String branchNumber;

    @Column(name = "CRS_INDU_DVLP_DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "CRS_INDU_DVLP_OTHER", length = 30)
    private String other;

    @Column(name = "CRS_INDU_DVLP_PROCFEE", length = 30)
    private String processingFee;

    @Column(name = "CRS_INDU_DVLP_TOTAL", length = 30)
    private String total;

    @Column(name = "CRS_INDU_DVLP_TOTAL_ADVANCES", length = 30)
    private String totalAdvances;

    @Column(name = "REPORT_MASTER_LIST_ID_FK", length = 10)
    private String reportMasterListIdFk;
}


xxx

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "CRS_INFRA_DVLP_INC")
@Getter
@Setter
public class CRSInfraDvlpInc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SR_NO")
    private String srNo;

    @Column(name = "CRS_INFRA_DVLP_BRNO", length = 5)
    private String branchNumber;

    @Column(name = "CRS_INFRA_DVLP_DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "CRS_INFRA_DVLP_OTHER", length = 30)
    private String other;

    @Column(name = "CRS_INFRA_DVLP_PROCFEE", length = 30)
    private String processingFee;

    @Column(name = "CRS_INFRA_DVLP_TOTAL", length = 30)
    private String total;

    @Column(name = "CRS_INFRA_DVLP_TOTAL_ADVANCES", length = 30)
    private String totalAdvances;

    @Column(name = "REPORT_MASTER_LIST_ID_FK", length = 10)
    private String reportMasterListIdFk;
}


xxx


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "CRS_AGRI_DVLP_INC")
@Getter
@Setter
public class CRSAgrDvlpInc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SR_NO")
    private String srNo;

    @Column(name = "CRS_AGRI_DVLP_BRNO", length = 5)
    private String branchNumber;

    @Column(name = "CRS_AGRI_DVLP_DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "CRS_AGRI_DVLP_OTHER", length = 30)
    private String other;

    @Column(name = "CRS_AGRI_DVLP_PROCFEE", length = 30)
    private String processingFee;

    @Column(name = "CRS_AGRI_DVLP_TOTAL", length = 30)
    private String total;

    @Column(name = "CRS_AGRI_DVLP_TOTAL_ADVANCES", length = 30)
    private String totalAdvances;

    @Column(name = "REPORT_MASTER_LIST_ID_FK", length = 10)
    private String reportMasterListIdFk;
}


xxxx

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "CRS_HOUS_DVLP_INC")
@Getter
@Setter
public class CRSHousDvlpInc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SR_NO")
    private String srNo;

    @Column(name = "CRS_HOUS_DVLP_BRNO", length = 5)
    private String branchNumber;

    @Column(name = "CRS_HOUS_DVLP_DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "CRS_HOUS_DVLP_OTHER", length = 30)
    private String other;

    @Column(name = "CRS_HOUS_DVLP_PROCFEE", length = 30)
    private String processingFee;

    @Column(name = "CRS_HOUS_DVLP_TOTAL", length = 30)
    private String total;

    @Column(name = "CRS_HOUS_DVLP_TOTAL_ADVANCES", length = 30)
    private String totalAdvances;

    @Column(name = "REPORT_MASTER_LIST_ID_FK", length = 10)
    private String reportMasterListIdFk;
}

xxxx