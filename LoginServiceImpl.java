package com.example.loginservice.service;

import com.example.loginservice.entity.LoginAttempt;
import com.example.loginservice.entity.LoginParam;
import com.example.loginservice.entity.RefreshToken;
import com.example.loginservice.entity.User;
import com.example.loginservice.filter.JwtService;
import com.example.loginservice.repository.LoginAttemptRepository;
import com.example.loginservice.repository.LoginParamRepository;
import com.example.loginservice.repository.RefreshTokenRepository;
import com.example.loginservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    public static final String VALID_CREDENTIALS = "validCredentials";
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  LoginAttemptRepository loginAttemptRepository;

    @Autowired
    private  LoginParamRepository loginParamRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


//    public LoginService(JwtService jwtService, UserRepository userRepository, LoginAttemptRepository loginAttemptRepository, LoginParamRepository loginParamRepository) {
//        this.jwtService = jwtService;
//        this.userRepository = userRepository;
//        this.loginAttemptRepository = loginAttemptRepository;
//        this.loginParamRepository = loginParamRepository;
//    }


    public Map<String, Object> checkUser(int userId) {

        int userStatus = userRepository.checkUser(userId);
        Map<String, Object> userInfo = new HashMap<>();

        User user = userRepository.findByUserId(userId);
        if (user != null) {
            // Existing User is trying to login

            if (user.getAccountStatus().equalsIgnoreCase("active")) {
                int userLoginAttemptsCount = loginAttemptRepository.getIfUserPresent(userId);
                LoginParam loginParam= loginParamRepository.getLoginParam();
                LocalDateTime lastUpdateTime = user.getTempPasswordSetAt().toLocalDateTime();
                boolean isPasswordValid = LocalDateTime.now().isBefore(lastUpdateTime.plusHours(loginParam.getPasswordValidity()));
                System.out.println(isPasswordValid);
                System.out.println(user.getPasswordHash()==null);
                if(user.getPasswordHash()==null || !isPasswordValid)
                {
                    // New User
                    userInfo.put("updatePassword", true);
                }
                else{
                    userInfo.put("updatePassword", false);
                }
                userInfo.put("userStatus", "ACTIVE");
                userInfo.put("message", "Active User");

            } else {
                // Inactive , Locked or Pending_Verification users
                String accountStatus = user.getAccountStatus();
                userInfo.put("userCheck", true);
                userInfo.put("userStatus", accountStatus);
                userInfo.put("message", accountStatus.equalsIgnoreCase("locked") ? "Locked User" : (accountStatus.equalsIgnoreCase("inactive") ? "Inactive User" : "Verification Pending"));

            }
        } else {
            // Invalid user is trying to login
            userInfo.put("userCheck", false);
            userInfo.put("userStatus", "INVALID");
            userInfo.put("message", "Invalid User");
        }





        return userInfo;

    }


    public boolean updatePassword(int userId, String password) {
        try {

            User user = userRepository.findByUserId(userId);
            String passwordHash = encoder.encode(password);
            user.setPasswordHash(passwordHash);
            user.setTempPasswordSetAt(Timestamp.valueOf(LocalDateTime.now()));
            User updatedUser = userRepository.save(user);

        } catch (DataIntegrityViolationException e) {
            return false;
        }
        return true;

    }

    public Map<String, Object> verifyUserCredentials(Map<String, Object> userCredentials) {

        int userId = Integer.parseInt((String) userCredentials.get("userId"));
        User user = userRepository.findByUserId(userId);
        LoginParam loginparam = loginParamRepository.getLoginParam();

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("loginMethod", loginparam.getActiveLoginMode());
        if (user == null) {
            userInfo.put("message", "Invalid User");
            userInfo.put(VALID_CREDENTIALS, false);
            userInfo.put("userStatus", "INVALID");
            return userInfo;
        }


        int userFailedPasswordAttemptCount = user.getUserWrongPasswordCount();
        int permittedAttemptCount = loginparam.getWrongPasswordAttempts();

        LoginAttempt loginAttempt = new LoginAttempt();
        loginAttempt.setUserId(userId);
        loginAttempt.setLoginMethod(loginparam.getActiveLoginMode().equalsIgnoreCase("P") ? "PASS" : "SSO");

        if (user.getAccountStatus().equalsIgnoreCase("active")) {
            userInfo.put("userStatus", "ACTIVE");
            if (loginparam.getActiveLoginMode().equalsIgnoreCase("P")) {
                passwordBasedLogin(userCredentials, userFailedPasswordAttemptCount, permittedAttemptCount, loginAttempt, userInfo, user, loginparam);
            } else {
                // SSO based login
                loginAttempt.setSuccess("Y");
                loginAttempt.setLoginMethod("SSO");
                userInfo.put("user", user);
                userInfo.put(VALID_CREDENTIALS, true);

            }
        } else {
            /*
            Request tampered and trying to login with  inactive / locked / verfication pending user
            */
            loginAttempt.setSuccess("N");
            loginAttempt.setFailureReason("Trying to login with a " + user.getAccountStatus() + " User");
            userInfo.put("userStatus", user.getAccountStatus());
            userInfo.put(VALID_CREDENTIALS, false);

        }
        loginAttemptRepository.save(loginAttempt);
        if (Boolean.TRUE.equals(userInfo.get(VALID_CREDENTIALS))) {

            Map<String, Object> userRole = userRepository.getUserRole(userId);
            //log.info(userRole.get("role").toString());

            String accessToken = jwtService.generateAccessToken(user, userRole);
            String refreshToken = jwtService.generateRefreshToken(user, userRole);
            RefreshToken refreshTokenEntity = new RefreshToken();
            refreshTokenEntity.setUserid(userId);
            refreshTokenEntity.setRefreshToken(refreshToken);
            refreshTokenRepository.save(refreshTokenEntity);
            userInfo.put("accessToken", accessToken);
            userInfo.put("refreshToken", refreshToken);
        }

        return userInfo;

    }

    public void passwordBasedLogin(Map<String, Object> userCredentials, int userFailedPasswordAttemptCount, int permittedAttemptCount, LoginAttempt loginAttempt, Map<String, Object> userInfo, User user, LoginParam loginparam) {
        // Password based login
        String password = (String) userCredentials.get("password");
        if (userFailedPasswordAttemptCount >= permittedAttemptCount) {
            loginAttempt.setSuccess("N");
            loginAttempt.setFailureReason("Exceeded Attempt Limit");
            userInfo.put("message", "Exceeded Attempt Limit");
            userInfo.put(VALID_CREDENTIALS, false);
        } else {

            if (encoder.matches(password, user.getPasswordHash())) {
                // Correct Password
                loginAttempt.setSuccess("Y");
                loginAttempt.setFailureReason("Success");
                // userInfo.put("passwordValid",true);
                // userInfo.put("user",user);
                userInfo.put(VALID_CREDENTIALS, true);


            } else {
                // Invalid Password
                if (userFailedPasswordAttemptCount == permittedAttemptCount - 1) {
                    // The user has reached the permitted wrong password limit - The user has to be locked
                    user.setAccountStatus("LOCKED");

                    user.setUserWrongPasswordCount(0);
                    userInfo.put("userStatus", "ACTIVE");
                }
                else{
                    user.setUserWrongPasswordCount(userFailedPasswordAttemptCount + 1);
                }
                userRepository.save(user);
                loginAttempt.setSuccess("N");
                loginAttempt.setFailureReason("Wrong Password");
                userInfo.put(VALID_CREDENTIALS, false);
                userInfo.put("attemptsLeft", permittedAttemptCount - userFailedPasswordAttemptCount - 1);

            }
        }


    }
}
