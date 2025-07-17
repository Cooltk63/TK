This is Source Entity::
package com.crs.iamservice.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "IAM_FIRM_EMPANELMENT")
public class FirmEmpanelment {

    @Id
    @Column(name = "FRN")
    String frnNumber;

    @Column(name = "FIRM_NAME")
    String firmName;

    @Column(name = "EMPANELMENT_TYPE")
    String empanelmentType;

    @Column(name = "EMPANELMENT_SUB_TYPE")
    String empanelmentSubType;

    @Column(name = "EMPANELED_BY")
    int empaneledBy;

    @Column(name = "FINANCIAL_YEAR")
    String financialyear;

    @Column(name = "REQUEST_STATUS")
    String requeststatus;

    @Column(name = "EMPANELED_STATUS")
    String empaneledStatus;

    @Column(name = "MOB_NO")
    private String mobno;

    @Column(name = "CONTACT_PERSON")
    private String contactperson;

    @Column(name = "POC_EMAIL")
    private String pocEmail;

    @Column(name = "POC_DESIGNATION")
    private String pocDesignation;
}


This is destination Entity

@Entity
@Getter
@Setter
@Table(name = "IAM_FIRM_ARCHIVE")
public class IAM_FIRM_ARCHIVE {

    @Column(name = "FRN")
    String frnNumber;
    @Column(name = "FIRM_NAME")
    String firmName;
    @Column(name = "EMPANELMENT_TYPE")
    String empanelmentType;
    @Column(name = "EMPANELMENT_SUB_TYPE")
    String empanelmentSubType;
    @Column(name = "EMPANELED_BY")
    int empaneledBy;
    @Column(name = "FINANCIAL_YEAR")
    String financialyear;
    @Column(name = "REQUEST_STATUS")
    String requeststatus;
    @Column(name = "EMPANELED_STATUS")
    String empaneledStatus;
    @Id
    @Column(name = "ARCHIVE_ID")
    private int archiveid;
    @Column(name = "ARCHIVED_BY")
    private String archivedby;
    @Column(name = "ARCHIVED_TIMESTAMP")
    private Date archivedtimestamp;
    @Column(name = "MOB_NO")
    private String mobno;

    @Column(name = "CONTACT_PERSON")
    private String contactperson;

    @Column(name = "POC_EMAIL")
    private String pocEmail;

    @Column(name = "POC_DESIGNATION")
    private String pocDesignation;
}


I wanted to move all data from Source table to destination use the financial year as parameter for findall for source table.
