@InitBinder("loginRequest")
public void initBinderLogin(WebDataBinder binder) {
    // Allow only fields the frontend is supposed to send
    binder.setAllowedFields("userId", "password");
}

@RequestMapping(value = "/login", method = RequestMethod.POST)
public @ResponseBody JSONObject doLogin(
        @RequestBody(required = false) @Valid LoginRequest loginRequest,
        HttpServletRequest request) {

    UserLogin user = null;

    String IV = AESGCM256.generateBase64IV();
    String SALT = AESGCM256.generateBase64Salt();

    String userId = (loginRequest != null) ? loginRequest.getUserId() : null;

    // Validate userId before use
    if (!isValidUserId(userId)) {
        log.warn("Invalid user ID attempt: " + userId);
        UserLogin login = new UserLogin();
        login.setIsUserExist("-1");

        ObjectMapper mapper = new ObjectMapper();
        String JsonString = null;
        try {
            JsonString = mapper.writeValueAsString(login);
        } catch (JsonProcessingException e) {
            log.error("Exception Occurred while writing JSON string: " + e.getMessage());
        }

        String encryptedData = AESGCM256.encrypt(JsonString, IV, SALT);

        JSONObject jObj = new JSONObject();
        jObj.put("iv", IV);
        jObj.put("salt", SALT);
        jObj.put("user", encryptedData);
        return jObj;
    }

    HttpSession session = request.getSession();
    session.setAttribute(CommonConstant.USER_ID, userId);
    session.setAttribute(CommonConstant.USER_SESSION_ID, session.getId() + "-" + session.getCreationTime());
    session.setAttribute(CommonConstant.USER_SESSION_ID, session.getId());

    // Your original login logic unchanged
    user = loginService.doLogin(convertToUserLogin(loginRequest)); // Convert DTO to your entity
    String quarterFYearDate = loginService.getQuarterYear();
    String[] QFD = quarterFYearDate.split("~");
    user.setQuarterEndDate(QFD[2]);
    user.setPreviousQuarterEndDate(QFD[4]);
    user.setPreviousYearEndDate(QFD[3]);
    user.setFinancialYear(QFD[1]);
    user.setQuarter(QFD[0]);

    boolean output = ifamsSftpService.getCirclesList(user.getCircleCode());
    user.setIsCircleExist(String.valueOf(output));

    if (!(("-1").equalsIgnoreCase(user.getIsUserExist()) || ("-2").equalsIgnoreCase(user.getIsUserExist()) || ("P").equalsIgnoreCase(user.getStatus()))) {
        user = loginService.getadditionalDetails(user);
        String token = CommonFunctions.getToken(user);
        session.setAttribute("TOKEN", token);
        loginService.saveToken(user, token);
        user.setToken(token);
    }

    // Populate user for encryption (no untrusted fields copied from request except validated userId)
    user.setUserId(userId);

    ObjectMapper mapper = new ObjectMapper();
    String JsonString = null;
    try {
        JsonString = mapper.writeValueAsString(user);
    } catch (JsonProcessingException e) {
        log.error("Exception Occurred while writing JSON string: " + e.getMessage());
    }

    String encryptedData = AESGCM256.encrypt(JsonString, IV, SALT);

    JSONObject jObj = new JSONObject();
    jObj.put("iv", IV);
    jObj.put("salt", SALT);
    jObj.put("user", encryptedData);
    return jObj;
}


public class LoginRequest {
    @NotBlank
    @Size(max = 64)
    private String userId;
    @NotBlank
    private String password;
    // getters and setters
}