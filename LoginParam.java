package com.example.loginservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
@Entity
@Getter
@Setter
@ToString
@Table(name="LOGIN_PARAM")
public class LoginParam {

    @Id
    @Column(name="ACTIVE_LOGIN_MODE")
    private String activeLoginMode;
    @Column(name="OTP_VALIDITY")
    private int otpValidity;
    @Column(name="PASSWORD_VALIDITY")
    private int passwordValidity;
    @Column(name="WRONG_PASSWORD_ATTEMPTS")
    private int wrongPasswordAttempts;
    @Column(name="ATTEMPT_INTERVAL")
    private int attemptInterval;
}
