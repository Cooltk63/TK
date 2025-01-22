package com.tar.reportService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tar.reportService.models.TAR_MODE;

import java.util.Optional;

public interface TarModeRepository extends JpaRepository<TAR_MODE,Integer> {


     TAR_MODE findByReportSubmissionId(@Param("submissionId")String submissionId);


     // Method to find a TAR_MODE entity by its modeID
     Optional<TAR_MODE>  findByModeid(int modeId);

     boolean existsByModeid(int modeId);
}

This is my Model class

package com.tar.reportService.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TAR_MODE")
@IdClass(TAR_MODE_Extended.class)
public class TAR_MODE {

    @Column(name = "MODE_ACCOUNT")
    private int mode_account;
    
    @Column(name = "MODE_AMT")
    private int mode_amt;
    
    @Id
    @Column(name = "MODE_BRANCH")
    private String mode_branch;
    
    @Id
    @Column(name = "MODE_DATE")
    private Date mode_date;
    
//    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "TAR_SEQ")
//    @SequenceGenerator(name = "TAR_SEQ",sequenceName = "TAR_SEQ",allocationSize = 1)
    @Column(name = "MODE_ID")
    private int modeid;
    
    @Column(name = "MODE_NATURE")
    private String mode_nature;
    
    @Column(name = "MODE_PAN")
    private String mode_pan;
    
    @Column(name = "MODE_PAYEE")
    private String mode_payee;
    
    @Column(name = "MODE_PAYMENT_DT")
    private Date mode_payment_dt; 
    
    @Column(name = "MODE_REASON")
    private String mode_reason;
    
    @Column(name = "MODE_TYPEBGL")
    private String mode_typebgl;
    
    @Column(name = "TAR_RML_FK")
    private String reportSubmissionId;

}

This is my extended Class

package com.tar.reportService.models;

import java.io.Serializable;
import java.util.Date;

public class TAR_MODE_Extended implements  Serializable{
    
private String mode_branch;
private Date mode_date;
private int modeid;
}






