Vulnerability :Trust Boundary Violation	

Vulnerability Description in Detail :The method doLogin() in LoginController.java commingles trusted and untrusted data in the same data structure, which encourages programmers to mistakenly trust unvalidated data.

Likely Impact :The method doLogin() in LoginController.java commingles trusted and untrusted data in the same data structure, which encourages programmers to mistakenly trust unvalidated data.
Recommendation :Define clear trust boundaries in the application. Do not use the same data structure to hold trusted data in some contexts and untrusted data in other contexts. Minimize the number of ways that data can move across a trust boundary. Trust boundary violations sometimes occur when input needs to be built up over a series of user interactions before being processed. It may not be possible to do complete input validation until all of the data has arrived. In these situations, it is still important to maintain a trust boundary. The untrusted data should be built up in a single untrusted data structure, validated, and then moved into a trusted location.

Code impacted ::
UserLogin doLogin(@RequestBody(required =false)UserLogin userLogin, HttpServletRequest request) {
//        log.info("LoginController > doLogin  >>>>> " + request.getAttribute("data"));

        UserLogin user = null;

//        Map<String, Object> jsonMap = (Map<String, Object>) request.getAttribute("data");
//        log.info("jsonMap " + jsonMap);
//        log.info("jsonMap " + jsonMap.get("userId"));
//
//        ////log.info("id " + userLogin.getUserId());
//        String userId = (String) jsonMap.get("userId");
        // FOR NEW FE Changes
//        String userId = userLogin.getUserId();
        String userId = CommonFunctions.getDcrypted(userLogin.getUserId());
//        log.info("userrid"+userId);
        userLogin.setUserId(userId);
        HttpSession session=request.getSession();

        session.setAttribute(CommonConstant.USER_ID, userId);
        session.setAttribute(CommonConstant.USER_SESSION_ID, session.getId() + "-" + session.getCreationTime());
        session.setAttribute(CommonConstant.USER_SESSION_ID, session.getId());




        user = loginService.doLogin(userLogin);
        String quarterFYearDate = loginService.getQuarterYear();
        String QFD[] = quarterFYearDate.split("~");
        String quarter = QFD[0];
        String financial_year = QFD[1];
        String quarter_end_date = QFD[2];
        String previousYearEndDate = QFD[3];
        String previousQuarterEndDate = QFD[4];
        user.setQuarterEndDate(quarter_end_date);
        user.setPreviousQuarterEndDate(previousQuarterEndDate);
        user.setPreviousYearEndDate(previousYearEndDate);
        user.setFinancialYear(financial_year);
        user.setQuarter(quarter);
//        log.info("user.getCircleCode()::"+user.getCircleCode());
        // Adding the Parameter to token for checking isCircle Authorized to SFTP Data

        boolean output=ifamsSftpService.getCirclesList(user.getCircleCode());
        log.info("Is Circle Exits for SFTP ::"+output);

        user.setIsCircleExist(String.valueOf(output));
        log.info("User getCircleExits :"+user.getIsCircleExist());




        if (!(("-1").equalsIgnoreCase(user.getIsUserExist()) || ("P").equalsIgnoreCase(user.getStatus()))) {
            user = loginService.getadditionalDetails(user);
            String token = CommonFunctions.getToken(user);
            session.setAttribute("TOKEN", token);
            int updated = loginService.saveToken(user, token);
            user.setToken(token);
            log.info("User save token :"+user.getToken());
        }
        //starting encryption
        user.setUserId(CommonFunctions.getEncrypted(userId));
        user.setUserName(CommonFunctions.getEncrypted(user.getUserName()));
        user.setCircleCode(CommonFunctions.getEncrypted(user.getCircleCode()));
        user.setCircleName(CommonFunctions.getEncrypted(user.getCircleName()));
        user.setRole(CommonFunctions.getEncrypted(user.getRole()));
        user.setCapacity(CommonFunctions.getEncrypted(user.getCapacity()));
        if(!("P").equalsIgnoreCase(user.getStatus())){
        user.setStatus(CommonFunctions.getEncrypted(user.getStatus()));
        }
        user.setIsBranchFinal(CommonFunctions.getEncrypted(user.getIsBranchFinal()));
        user.setIsCircleFreeze(CommonFunctions.getEncrypted(user.getIsCircleFreeze()));
        user.setIsAuditorDig(CommonFunctions.getEncrypted(user.getIsAuditorDig()));
        user.setIsCheckerDig(CommonFunctions.getEncrypted(user.getIsCheckerDig()));
        user.setFrRMId(CommonFunctions.getEncrypted("444444"));
        user.setFrReportId(CommonFunctions.getEncrypted("444444"));
        user.setMocFlag(CommonFunctions.getEncrypted(user.getMocFlag()));
        user.setIsCircleExist(CommonFunctions.getEncrypted(user.getIsCircleExist()));

        return user;
    }
