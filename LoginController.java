package com.example.loginservice.controller;

import com.example.loginservice.ResponseVO;
import com.example.loginservice.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    public static final String REFRESH_TOKEN = "refreshToken";

    @Autowired
    private LoginService loginService;



    @PostMapping("/check-user")
    public ResponseEntity<Map<String, Object>> checkUser(@RequestBody Map<String, String> user) {
        int userId = Integer.parseInt(user.get("userId"));

        Map<String, Object> result = loginService.checkUser(userId);
        ResponseVO<Map<String, Object>> responseVO = new ResponseVO<Map<String, Object>>();
        responseVO.setStatusCode(200);
        responseVO.setResult(result);
        responseVO.setMessage("");
        return new ResponseEntity(responseVO, HttpStatus.OK);
    }

    @PostMapping("/update-password")
    public ResponseEntity<Map<String, Object>> updatePassword(@RequestBody Map<String, String> user) {
        int userId = Integer.parseInt(user.get("userId"));
        String password = user.get("password");
        ResponseVO<Map<String, Object>> responseVO = new ResponseVO<>();
        Map<String, Object> result = new HashMap<>();
        boolean updateResult = loginService.updatePassword(userId, password);
        result.put("update", updateResult);
        if (updateResult) {
            responseVO.setStatusCode(200);
            responseVO.setMessage("Success");
        } else {
            responseVO.setStatusCode(500);
            responseVO.setMessage("Fail");
        }
        responseVO.setResult(result);
        return new ResponseEntity(responseVO, HttpStatus.OK);

    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> userCredentials, HttpServletResponse response) {

        Map<String, Object> logInInfo = loginService.verifyUserCredentials(userCredentials);
        if (Boolean.TRUE.equals(logInInfo.get("validCredentials"))) {
            ResponseCookie refreshCookie = ResponseCookie.from(REFRESH_TOKEN,
                            logInInfo.get(REFRESH_TOKEN).toString())
                    .secure(true)
                    .maxAge(3600)
                    .httpOnly(true)
                    .sameSite("None")
                    .path("/")
                    .build();
            response.setHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
            logInInfo.remove(REFRESH_TOKEN);

        }
        ResponseVO<Map<String, Object>> responseVO = new ResponseVO<>();
        responseVO.setStatusCode(200);
        responseVO.setResult(logInInfo);
        responseVO.setMessage("");
        responseVO.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity(responseVO, HttpStatus.OK);

    }

    @PostMapping("/refresh-token")
    public void  refreshToken(@CookieValue(value = "refreshToken",required = false) String refreshToken) {
        if(refreshToken != null) {
            System.out.println("Refresh token is " + refreshToken);
        }
        else{
            System.out.println("Refresh token is null");
        }

    }

    @PostMapping("/test-jwt")
    public void testJwt(@RequestBody Map<String, String> temp) {
        System.out.println(temp);

    }

}
