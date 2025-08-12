package com.example.loginservice.entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "USER_LOGIN_ATTEMPTS")
public class LoginAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ATTEMPT_ID")
    private Long attemptId;

    @Column(name="USER_ID")
    private int userId;

    @Column(name="LOGIN_METHOD")
    private String loginMethod;

    @Column(name="IP_ADDRESS")
    private String ipAddress;

    @Column(name="ATTEMPT_TIME", nullable = false,updatable = false,insertable = false)
    private Timestamp attemptTime;

    @Column(name="SUCCESS")
    private String success;

    @Column(name = "FAILURE_REASON")
    private String failureReason;
}
