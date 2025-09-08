public Map<String, Object> verifyUserCredentials(Map<String, Object> userCredentials) {

        log.info("verifyUser Credentials req={}", userCredentials);

        String userId = userCredentials.get("username").toString();
        String pass = userCredentials.get("password").toString();
        log.info("UserId Passing " + userId);
        log.info("Pass Passing " + pass);
        User user = userRepository.findByUserId(userId);

//        log.info("user DAT From DB ={}", user.getFirstName());

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
            log.info("User Status is ::" + user.getAccountStatus());
            userInfo.put("userStatus", "ACTIVE");
            if (loginparam.getActiveLoginMode().equalsIgnoreCase("P")) {
                log.info("Login Param Active Login Mode ::" + loginparam.getActiveLoginMode());
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
            log.info("User Status is not active inside else block ::", user.getAccountStatus());
            loginAttempt.setSuccess("N");
            loginAttempt.setFailureReason("Trying to login with a " + user.getAccountStatus() + " User");
            userInfo.put("userStatus", user.getAccountStatus());
            userInfo.put(VALID_CREDENTIALS, false);

        }
        loginAttemptRepository.save(loginAttempt);
        if (Boolean.TRUE.equals(userInfo.get(VALID_CREDENTIALS))) {

            log.info("User Credentials Valid ::" + userInfo.get(VALID_CREDENTIALS));

            Map<String, Object> userRole = userRepository.getUserRole(userId);
            //log.info(userRole.get("role").toString());

//            String accessToken = jwtService.generateAccessToken(user, userRole);
//            String refreshToken = jwtService.generateRefreshToken(user, userRole);

            // Generate a HS256 JWT (sub + jti + exp)
            log.info("Before Generate JWT User Data ::{}", user.getUserId());

            String accessToken = HmacJwtUtil.generate(hmacSecret, user.getUserId(), ttlSeconds);
            String refreshToken = HmacJwtUtil.generate(hmacSecret, user.getUserId(), ttlSeconds);


            RefreshToken previousRefreshToken = refreshTokenRepository.getLatestRefreshTokensByUserid(userId);
            if (previousRefreshToken != null) {
                previousRefreshToken.setSessionEndedAt(Timestamp.valueOf(LocalDateTime.now()));
                refreshTokenRepository.save(previousRefreshToken);
            }
            RefreshToken refreshTokenEntity = new RefreshToken();
            refreshTokenEntity.setUserid(userId);
            refreshTokenEntity.setRefreshToken(refreshToken);

            refreshTokenRepository.save(refreshTokenEntity);

            userInfo.put("accessToken", accessToken);
            userInfo.put("refreshToken", refreshToken);
        }

        return userInfo;

    }

    Calling above method from below ::

    // Methods Validate the User Existed in DB & Return the user object
        Map<String, Object> userData = loginService.verifyUserCredentials(userMap);

        User user = (User) userData.get("user");

        log.info("User Data Received after verifyUserCredentials:: " + (user ==null ? null : user.toString()));

        log.info("User Data ID Received: " + user.getUserId());
//        log.info("User Data Name Received: " + user.getFirstName());


I am getting this User Object null even if I do the casting & verifyUserCredentials() has the user Object data

User is a Entity class which had the Setter & getter methods guide me how idi i get the extract data from Map<String, Object> of User entity as we setting in userInfo.put("user", user); inside verifyUserCredentials()
