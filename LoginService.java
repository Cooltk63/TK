package com.example.loginservice.service;

import com.example.loginservice.entity.LoginAttempt;
import com.example.loginservice.entity.LoginParam;
import com.example.loginservice.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface LoginService {

    public Map<String, Object> checkUser(int userId);
    public boolean updatePassword(int userId, String password);
    public Map<String, Object> verifyUserCredentials(Map<String, Object> userCredentials);
    public void passwordBasedLogin(Map<String, Object> userCredentials, int userFailedPasswordAttemptCount, int permittedAttemptCount, LoginAttempt loginAttempt, Map<String, Object> userInfo, User user, LoginParam loginparam) ;
}
