package com.example.loginservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "USER_SESSION")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SESSION_ID")
    private Long sessionId;

    @Column(name="USER_ID")
    private int userid;

    @Column(name="REFRESH_TOKEN")
    private String refreshToken;

    @Column(name="SESSION_STARTED_AT",insertable = false, updatable = false)
    private String sessionStartedAt;

    @Column(name="SESSION_ENDED_AT")
    private String sessionEndedAt;

    @Column(name="DEVICE_ID")
    private String deviceId;


}
