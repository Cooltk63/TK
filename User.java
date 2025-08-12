package com.example.loginservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {

    @Id
    @Column(name="USER_ID")
    private int userId;

    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="MIDDLE_NAME")
    private String middleName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="PHONE_NUMBER")
    private String phoneNumber;
    @Column(name="EMAIL")
    private String email;
    @Column(name="PASSWORD_HASH")
    private String passwordHash;
    @Column(name="ACCOUNT_STATUS")
    private String accountStatus;
    @Column(name="CREATED_AT")
    private Timestamp createdAt;
    @Column(name="UPDATED_AT")
    private Timestamp updatedAt;
    @Column(name="LAST_LOGIN_AT")
    private Timestamp lastLoginAt;
    @Column(name="IS_DELETED")
    private String isDeleted;
    @Column(name="DELETED_AT")
    private Timestamp deletedAt;

    @Column(name="TEMP_PASSWORD_SET_AT")
    private Timestamp tempPasswordSetAt;

    @Column(name="USER_WRONG_PASSWORD_COUNT")
    private int userWrongPasswordCount;
}
