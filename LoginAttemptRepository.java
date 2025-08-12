package com.example.loginservice.repository;

import com.example.loginservice.entity.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {

    public LoginAttempt findByUserId(int userId);


    @Query(value = "select count(1) from USER_LOGIN_ATTEMPTS where USER_ID =:userId ",nativeQuery = true)
    public int getIfUserPresent(@Param("userId") int userId);

    @Query(value="select count(*) from user_login_attempts " +
            "where USER_ID=:userId and success='N' and LOGIN_METHOD='PASS' " +
            "and attempt_time >= systimestamp - NUMTODSINTERVAL(:attemptInterval,'HOUR')",nativeQuery = true)
    public int getFailedAttemptCount(@Param("userId") int userId,@Param("attemptInterval") int attemptInterval);
}
