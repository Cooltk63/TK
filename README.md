Vulnerability
Trust Boundary Violation

Vulnerability Description in Detail
The method doLogin() in LoginController.java commingles trusted and untrusted data in the same data structure, which encourages programmers to mistakenly trust unvalidated data.


I need to resolve this issue like sanitize the input which received from FE like validations of symbols special chaaracter allow alpha numeric character

// UsedFunction
    @InitBinder("login")
    public void initBinderlogin(WebDataBinder binder) {
        binder.setDisallowedFields(new String[]{});
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject doLogin(@RequestBody(required =false)UserLogin userLogin, HttpServletRequest request) throws Exception {
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

        String IV = AESGCM256.generateBase64IV();
        String SALT = AESGCM256.generateBase64Salt();

        //starting encryption
        user.setUserId(userId);
        user.setUserName(user.getUserName());
        user.setCircleCode(user.getCircleCode());
        user.setCircleName(user.getCircleName());
        user.setRole(user.getRole());
        user.setCapacity(user.getCapacity());
        if(!("P").equalsIgnoreCase(user.getStatus())){
        user.setStatus(user.getStatus());
        }
        user.setIsBranchFinal(user.getIsBranchFinal());
        user.setIsCircleFreeze(user.getIsCircleFreeze());
        user.setIsAuditorDig(user.getIsAuditorDig());
        user.setIsCheckerDig(user.getIsCheckerDig());
        user.setFrRMId("444444");
        user.setFrReportId("444444");
        user.setMocFlag(user.getMocFlag());
        user.setIsCircleExist(user.getIsCircleExist());

        ObjectMapper mapper = new ObjectMapper();
        String JsonString = mapper.writeValueAsString(user);

        String decryptedData = AESGCM256.encrypt(
                JsonString,
                IV,
                SALT
        );


        log.info("decryptedData::"+decryptedData);

        JSONObject jObj = new JSONObject();
        jObj.put("iv", IV);
        jObj.put("salt", SALT);
        jObj.put("user", decryptedData);
        return jObj;
    }

