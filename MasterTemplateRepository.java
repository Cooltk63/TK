package com.crs.iamservice.Repository;

import com.crs.iamservice.Model.MasterTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MasterTemplateRepository extends JpaRepository<MasterTemplate, String> {

    @Query(nativeQuery = true, value = "SELECT MASTER_TEMPLATE FROM IAM_MASTER_TEMPLATE where MASTER_ID=(SELECT MAX(MASTER_ID) FROM IAM_MASTER_TEMPLATE)")
    String gettemplate();

    @Query(nativeQuery = true, value = "SELECT MASTER_TEMPLATE FROM IAM_MASTER_TEMPLATE where TEMPLATE_FLAG='D'")
    String getDefaultTemplate();

}
