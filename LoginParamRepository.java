package com.example.loginservice.repository;

import com.example.loginservice.entity.LoginParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginParamRepository extends JpaRepository<LoginParam, Long> {

    @Query(value = "Select * from LOGIN_PARAM",nativeQuery = true)
    public LoginParam getLoginParam();
}
