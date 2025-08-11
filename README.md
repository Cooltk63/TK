@Component
public class InputValidator {
    
    private static final Pattern VALID_USERID_PATTERN = Pattern.compile("^[a-zA-Z0-9._-]+$");
    private static final int MAX_USERID_LENGTH = 50;
    private static final int MIN_USERID_LENGTH = 3;
    
    public boolean isValidUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            return false;
        }
        
        String trimmed = userId.trim();
        return trimmed.length() >= MIN_USERID_LENGTH && 
               trimmed.length() <= MAX_USERID_LENGTH && 
               VALID_USERID_PATTERN.matcher(trimmed).matches() &&
               !containsSqlInjectionPatterns(trimmed) &&
               !containsXssPatterns(trimmed);
    }
    
    private boolean containsSqlInjectionPatterns(String input) {
        String[] patterns = {"'", "\"", ";", "--", "/*", "*/", "xp_", "exec", "union", "select"};
        String lowerInput = input.toLowerCase();
        return Arrays.stream(patterns).anyMatch(lowerInput::contains);
    }
    
    private boolean containsXssPatterns(String input) {
        String[] patterns = {"<script", "javascript:", "onload=", "onerror=", "<iframe"};
        String lowerInput = input.toLowerCase();
        return Arrays.stream(patterns).anyMatch(lowerInput::contains);
    }
}



@InitBinder
public void initBinder(WebDataBinder binder) {
    binder.setDisallowedFields("class.*", "Class.*", "*.class.*", "*.Class.*");
}



@RequestMapping(value = "/login", method = RequestMethod.POST)
public @ResponseBody
JSONObject doLogin(@Validated @RequestBody(required = false) UserLogin userLogin, 
                   HttpServletRequest request, BindingResult bindingResult) {

    // Step 1: Input Validation - Check if request body exists
    if (userLogin == null) {
        log.warn("Login attempt with null request body from IP: " + getClientIpAddress(request));
        return createErrorResponse("Invalid request data");
    }

    // Step 2: Check validation errors from @Validated annotation
    if (bindingResult != null && bindingResult.hasErrors()) {
        log.warn("Login validation errors: " + bindingResult.getAllErrors());
        return createErrorResponse("Invalid request parameters");
    }

    // Step 3: Create separate trusted and untrusted data structures
    UntrustedUserInput untrustedInput = new UntrustedUserInput();
    TrustedUserData trustedData = new TrustedUserData();
    
    try {
        // Step 4: Extract and validate untrusted input
        String rawUserId = userLogin.getUserId();
        untrustedInput.setRawUserId(rawUserId);
        
        // Step 5: Comprehensive validation and sanitization
        String sanitizedUserId = validateAndSanitizeUserId(rawUserId);
        if (sanitizedUserId == null) {
            log.warn("Invalid user ID attempt: " + maskSensitiveData(rawUserId) + 
                    " from IP: " + getClientIpAddress(request));
            return createErrorResponse("-1");
        }
        
        // Step 6: Move validated data to trusted structure
        trustedData.setUserId(sanitizedUserId);
        
        // Step 7: Generate encryption parameters
        String IV = AESGCM256.generateBase64IV();
        String SALT = AESGCM256.generateBase64Salt();
        
        // Step 8: Create session with validated data only
        HttpSession session = request.getSession();
        session.setAttribute(CommonConstant.USER_ID, trustedData.getUserId());
        session.setAttribute(CommonConstant.USER_SESSION_ID, session.getId());
        
        // Step 9: Create clean user object for service layer
        UserLogin validatedUserLogin = new UserLogin();
        validatedUserLogin.setUserId(trustedData.getUserId());
        // Copy other validated fields as needed
        
        // Step 10: Service layer call with validated data
        UserLogin user = loginService.doLogin(validatedUserLogin);
        
        // Step 11: Additional validations
        if (!isValidUserResponse(user)) {
            log.warn("Invalid user response for userId: " + maskSensitiveData(trustedData.getUserId()));
            return createErrorResponse("-1");
        }
        
        // Step 12: Process business logic with validated data
        String quarterFYearDate = loginService.getQuarterYear();
        if (quarterFYearDate != null && quarterFYearDate.contains("~")) {
            String[] QFD = quarterFYearDate.split("~");
            if (QFD.length >= 5) {
                user.setQuarter(sanitizeString(QFD[0]));
                user.setFinancialYear(sanitizeString(QFD[1]));
                user.setQuarterEndDate(sanitizeString(QFD[2]));
                user.setPreviousYearEndDate(sanitizeString(QFD[3]));
                user.setPreviousQuarterEndDate(sanitizeString(QFD[4]));
            }
        }
        
        // Step 13: Circle authorization check
        boolean output = ifamsSftpService.getCirclesList(user.getCircleCode());
        user.setIsCircleExist(String.valueOf(output));
        
        // Step 14: Token generation for valid users
        if (isUserValidForToken(user)) {
            user = loginService.getadditionalDetails(user);
            String token = CommonFunctions.getToken(user);
            session.setAttribute("TOKEN", token);
            int updated = loginService.saveToken(user, token);
            user.setToken(token);
        }
        
        // Step 15: Create response with sanitized data
        UserLogin responseUser = createSafeResponseUser(user, trustedData.getUserId());
        
        // Step 16: Serialize and encrypt response
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(responseUser);
        String encryptedData = AESGCM256.encrypt(jsonString, IV, SALT);
        
        JSONObject response = new JSONObject();
        response.put("iv", IV);
        response.put("salt", SALT);
        response.put("user", encryptedData);
        
        log.info("Successful login for user: " + maskSensitiveData(trustedData.getUserId()));
        return response;
        
    } catch (Exception e) {
        log.error("Login error: " + e.getMessage(), e);
        return createErrorResponse("-1");
    }
}

// Helper method: Enhanced UserId validation
private String validateAndSanitizeUserId(String rawUserId) {
    if (rawUserId == null || rawUserId.trim().isEmpty()) {
        return null;
    }
    
    // Remove potential harmful characters
    String sanitized = rawUserId.trim();
    
    // Check length constraints
    if (sanitized.length() > 50 || sanitized.length() < 3) {
        return null;
    }
    
    // Allow only alphanumeric characters, dots, and hyphens
    if (!sanitized.matches("^[a-zA-Z0-9._-]+$")) {
        return null;
    }
    
    // Additional business logic validation
    if (!isValidUserIdFormat(sanitized)) {
        return null;
    }
    
    return sanitized;
}

// Helper method: Enhanced UserId format validation
private boolean isValidUserId(String userId) {
    return validateAndSanitizeUserId(userId) != null;
}

private boolean isValidUserIdFormat(String userId) {
    // Add your specific business rules here
    // Example: check against known patterns, database existence, etc.
    return userId != null && 
           !userId.contains("..") && 
           !userId.contains("--") && 
           !userId.startsWith(".") && 
           !userId.startsWith("-");
}

// Helper method: Validate user response from service
private boolean isValidUserResponse(UserLogin user) {
    return user != null && 
           user.getUserId() != null && 
           !"-1".equalsIgnoreCase(user.getIsUserExist()) && 
           !"-2".equalsIgnoreCase(user.getIsUserExist());
}

// Helper method: Check if user is valid for token generation
private boolean isUserValidForToken(UserLogin user) {
    return user != null && 
           !"-1".equalsIgnoreCase(user.getIsUserExist()) && 
           !"-2".equalsIgnoreCase(user.getIsUserExist()) && 
           !"P".equalsIgnoreCase(user.getStatus());
}

// Helper method: Create safe response user object
private UserLogin createSafeResponseUser(UserLogin user, String validatedUserId) {
    UserLogin safeUser = new UserLogin();
    
    // Only set validated and sanitized data
    safeUser.setUserId(validatedUserId);
    safeUser.setUserName(sanitizeString(user.getUserName()));
    safeUser.setCircleCode(sanitizeString(user.getCircleCode()));
    safeUser.setCircleName(sanitizeString(user.getCircleName()));
    safeUser.setRole(sanitizeString(user.getRole()));
    safeUser.setCapacity(sanitizeString(user.getCapacity()));
    
    if (!"P".equalsIgnoreCase(user.getStatus())) {
        safeUser.setStatus(sanitizeString(user.getStatus()));
    }
    
    safeUser.setIsBranchFinal(user.getIsBranchFinal());
    safeUser.setIsCircleFreeze(user.getIsCircleFreeze());
    safeUser.setIsAuditorDig(user.getIsAuditorDig());
    safeUser.setIsCheckerDig(user.getIsCheckerDig());
    safeUser.setMocFlag(user.getMocFlag());
    safeUser.setIsCircleExist(user.getIsCircleExist());
    safeUser.setToken(user.getToken());
    safeUser.setQuarter(user.getQuarter());
    safeUser.setFinancialYear(user.getFinancialYear());
    safeUser.setQuarterEndDate(user.getQuarterEndDate());
    safeUser.setPreviousQuarterEndDate(user.getPreviousQuarterEndDate());
    safeUser.setPreviousYearEndDate(user.getPreviousYearEndDate());
    
    // Set fixed values for sensitive fields
    safeUser.setFrRMId("444444");
    safeUser.setFrReportId("444444");
    
    return safeUser;
}

// Helper method: Sanitize string inputs
private String sanitizeString(String input) {
    if (input == null) return null;
    
    // Remove potential XSS and injection characters
    return input.trim()
                .replaceAll("[<>\"'&]", "")
                .replaceAll("(?i)(script|javascript|vbscript)", "");
}

// Helper method: Create error response
private JSONObject createErrorResponse(String errorCode) {
    try {
        UserLogin errorUser = new UserLogin();
        errorUser.setIsUserExist(errorCode);
        
        String IV = AESGCM256.generateBase64IV();
        String SALT = AESGCM256.generateBase64Salt();
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(errorUser);
        String encryptedData = AESGCM256.encrypt(jsonString, IV, SALT);
        
        JSONObject response = new JSONObject();
        response.put("iv", IV);
        response.put("salt", SALT);
        response.put("user", encryptedData);
        
        return response;
    } catch (Exception e) {
        log.error("Error creating error response: " + e.getMessage());
        return new JSONObject();
    }
}

// Helper method: Get client IP address
private String getClientIpAddress(HttpServletRequest request) {
    String xForwardedFor = request.getHeader("X-Forwarded-For");
    if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
        return xForwardedFor.split(",")[0].trim();
    }
    return request.getRemoteAddr();
}

// Helper method: Mask sensitive data for logging
private String maskSensitiveData(String data) {
    if (data == null || data.length() <= 4) return "***";
    return data.substring(0, 2) + "***" + data.substring(data.length() - 2);
}

// Helper classes for Trust Boundary separation
private static class UntrustedUserInput {
    private String rawUserId;
    // Add other untrusted fields as needed
    
    public String getRawUserId() { return rawUserId; }
    public void setRawUserId(String rawUserId) { this.rawUserId = rawUserId; }
}

private static class TrustedUserData {
    private String userId;
    // Add other trusted fields as needed
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}
