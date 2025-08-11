@RequestMapping(value = "/login", method = RequestMethod.POST)
public @ResponseBody
JSONObject doLogin(@Validated @RequestBody(required = false) UserLogin userLogin, HttpServletRequest request) {
    //        log.info("LoginController > doLogin  >>>>> " + request.getAttribute("data"));

    UserLogin user = null;

    String IV = AESGCM256.generateBase64IV();
    String SALT = AESGCM256.generateBase64Salt();

    UserLogin safeUserData = new UserLogin();

    // TRUST BOUNDARY FIX: Create separate structure for untrusted input validation
    // This ensures we don't mix trusted and untrusted data in the same object
    UntrustedInput untrustedData = new UntrustedInput();
    
    // TRUST BOUNDARY FIX: Null check for request body to prevent NPE
    if (userLogin == null) {
        log.warn("Login attempt with null request body");
        return createSecureErrorResponse(IV, SALT, "-1");
    }

    // FOR NEW FE Changes - TRUST BOUNDARY FIX: Extract to untrusted container first
    untrustedData.rawUserId = userLogin.getUserId();

    // TRUST BOUNDARY FIX: Validate and sanitize the untrusted input
    String validatedUserId = validateAndSanitizeInput(untrustedData.rawUserId);
    
    safeUserData.setUserId(validatedUserId);
    
    // OLD FE Handling
    //        String userId = CommonFunctions.getDcrypted(userLogin.getUserId());

    // SCR 2024-25 :Trust Boundary Violation (Enhanced BE Validation for UserId)
    // TRUST BOUNDARY FIX: Using the validated userId instead of raw input
    if (!isValidUserId(validatedUserId)) {
        log.warn("Invalid user ID attempt: " + maskSensitiveData(untrustedData.rawUserId));
        return createSecureErrorResponse(IV, SALT, "-1");
    }

    // TRUST BOUNDARY FIX: Use only validated data for further processing
    userLogin.setUserId(validatedUserId);
    HttpSession session = request.getSession();

    session.setAttribute(CommonConstant.USER_ID, validatedUserId);
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

    // Adding the Parameter to token for checking isCircle Authorized to SFTP Data
    boolean output = ifamsSftpService.getCirclesList(user.getCircleCode());
    //        log.info("Is Circle Exits for SFTP ::"+output);

    user.setIsCircleExist(String.valueOf(output));
    //        log.info("User getCircleExits :"+user.getIsCircleExist());

    //        if (!(("-1").equalsIgnoreCase(user.getIsUserExist()) || ("P").equalsIgnoreCase(user.getStatus()))) {
    if (!(("-1").equalsIgnoreCase(user.getIsUserExist()) || ("-2").equalsIgnoreCase(user.getIsUserExist()) || ("P").equalsIgnoreCase(user.getStatus()))) {
        user = loginService.getadditionalDetails(user);
        String token = CommonFunctions.getToken(user);
        session.setAttribute("TOKEN", token);
        int updated = loginService.saveToken(user, token);
        user.setToken(token);
    }

    //starting encryption
    // TRUST BOUNDARY FIX: Use validated userId instead of potentially tainted data
    user.setUserId(validatedUserId);
    user.setUserName(user.getUserName());
    user.setCircleCode(user.getCircleCode());
    user.setCircleName(user.getCircleName());
    user.setRole(user.getRole());
    user.setCapacity(user.getCapacity());
    if (!("P").equalsIgnoreCase(user.getStatus())) {
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
    String JsonString = null;
    try {
        JsonString = mapper.writeValueAsString(user);
    } catch (JsonProcessingException e) {
        log.error("Exception Occurred  while writing JSON string: " + e.getMessage());
    }

    String decryptedData = AESGCM256.encrypt(
            JsonString,
            IV,
            SALT
    );

    JSONObject jObj = new JSONObject();
    jObj.put("iv", IV);
    jObj.put("salt", SALT);
    jObj.put("user", decryptedData);
    return jObj;
}

// TRUST BOUNDARY FIX: Helper class to contain untrusted input separately
// This prevents mixing of trusted and untrusted data in the same structure
private static class UntrustedInput {
    String rawUserId;
}

// TRUST BOUNDARY FIX: Enhanced input validation and sanitization method
// This method validates and cleans the input before it enters trusted boundaries
private String validateAndSanitizeInput(String rawInput) {
    // Handle null input
    if (rawInput == null) {
        return null;
    }
    
    // Trim whitespace
    String sanitized = rawInput.trim();
    
    // Basic length validation
    if (sanitized.isEmpty() || sanitized.length() > 100) {
        return null;
    }
    
    // Remove potentially dangerous characters while preserving valid ones
    // This maintains your existing business logic while securing the input
    sanitized = sanitized.replaceAll("[<>\"'&;]", "");
    
    return sanitized;
}

// TRUST BOUNDARY FIX: Enhanced isValidUserId method with comprehensive validation
// This method ensures only properly validated data crosses the trust boundary
private boolean isValidUserId(String userId) {
    // Null or empty check
    if (userId == null || userId.trim().isEmpty()) {
        return false;
    }
    
    String trimmed = userId.trim();
    
    // Length validation
    if (trimmed.length() < 1 || trimmed.length() > 100) {
        return false;
    }
    
    // Character validation - allow alphanumeric, dots, hyphens, underscores
    // This pattern can be adjusted based on your existing valid userId formats
    if (!trimmed.matches("^[a-zA-Z0-9._@-]+$")) {
        return false;
    }
    
    // Prevent common injection patterns
    String lowerCase = trimmed.toLowerCase();
    if (lowerCase.contains("script") || lowerCase.contains("javascript") || 
        lowerCase.contains("--") || lowerCase.contains("/*") || 
        lowerCase.contains("*/") || lowerCase.contains("union") || 
        lowerCase.contains("select")) {
        return false;
    }
    
    return true;
}

// TRUST BOUNDARY FIX: Secure error response creation method
// This ensures error responses don't leak sensitive information
private JSONObject createSecureErrorResponse(String IV, String SALT, String errorCode) {
    UserLogin login = new UserLogin();
    login.setIsUserExist(errorCode);

    // Sending the Empty Data
    ObjectMapper mapper = new ObjectMapper();
    String JsonString = null;

    try {
        JsonString = mapper.writeValueAsString(login);
    } catch (JsonProcessingException e) {
        log.error("Exception Occurred while writing JSON string: " + e.getMessage());
    }

    String decryptedData = AESGCM256.encrypt(
            JsonString,
            IV,
            SALT
    );

    JSONObject jObj = new JSONObject();
    jObj.put("iv", IV);
    jObj.put("salt", SALT);
    jObj.put("user", decryptedData);
    return jObj;
}

// TRUST BOUNDARY FIX: Helper method to mask sensitive data in logs
// This prevents sensitive information from appearing in log files
private String maskSensitiveData(String data) {
    if (data == null || data.length() <= 4) {
        return "***";
    }
    return data.substring(0, 2) + "***" + data.substring(data.length() - 2);
}