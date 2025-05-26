public class InputValidator {
    private static final Pattern USER_ID_PATTERN = Pattern.compile("^[a-zA-Z0-9]{3,50}$");

    public static boolean isValidUserId(String userId) {
        return userId != null && USER_ID_PATTERN.matcher(userId).matches();
    }
}


String userId = CommonFunctions.getDcrypted(userLogin.getUserId());

if (!InputValidator.isValidUserId(userId)) {
    log.warn("Invalid user ID attempt: " + userId);
    throw new IllegalArgumentException("Invalid user ID format. Only alphabets and numbers are allowed.");
}

userLogin.setUserId(userId);