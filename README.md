Vulnerability
Cookie Security: Persistent Session Cookie

Vulnerability Description in Detail
Persistent session cookies can lead to account compromise.

Likely Impact
The method sftpFile() in CriMarrSerivceImpl.java calls sleep()  on line 74. Thread management in a web application is forbidden in some circumstances and is always highly error prone.

Recommendation
Do not use persistent session cookies. You can do this by setting server.servlet.session.cookie.persistent=false in your Spring Boot configuration file.

Code impact ::

// UsedFunction
    @InitBinder("login")
    public void initBinderlogin(WebDataBinder binder) {
        binder.setDisallowedFields(new String[]{});
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
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
