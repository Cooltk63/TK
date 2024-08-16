package com.crs.commonReportsService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="CRS_STND_ASSETS")
public class CrsStndAssets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "CRS_STND_SEQ")
    @SequenceGenerator(name = "CRS_STND_SEQ",sequenceName = "CRS_STND_SEQ",allocationSize = 1)
    @Column(name = "STND_ASSETS_SEQ")
    private int stndassetsseq;

    @Column(name = "STND_ASTS_NAME_OF_BORROWER")
    private String stndastsnameofborrower;

    @Column(name = "STND_ASTS_INFRA_NON_INFRA")
    private String stndastsinfranoninfra;

    @Column(name = "STND_ASTS_INFRA_WITHIN2YRS")
    private String stndastsinfrawithin2YRS;

    @Column(name = "STND_ASTS_INFRA_ACCTS2YRS")
    private String  stndastsinfraaccts2YRS;

    @Column(name = "STND_ASTS_NONINFRA_WITHIN1YR")
    private String stndastsnoninfrawithin1YR;

    @Column(name = "STND_ASTS_NONINFRA_ACCTS1YR")
    private String stndastsnoninfraaccts1YR;

    @Column(name = "STND_ASSETS_BRANCH")
    private String stndastsbranch;

    @Column(name = "STND_ASSETS_DATE")
    private String stndastsdate;
}

This is my entity class suggest the changes accrodingly and 
