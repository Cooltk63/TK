// Function to generate HMAC-SHA256 signature (CryptoJS must be included)
function generateSignature(data, secretKey) {
    return CryptoJS.HmacSHA256(data, secretKey).toString(CryptoJS.enc.Base64);
}

// Prepare the params as usual
let params = {
    'salt': salt,
    'iv': iv,
    'data': aesUtil.encrypt(salt, iv, passphrase, JSON.stringify(payload)),
};

// Generate the signature using encrypted 'data'
let secretKey = 'mySecretKey';  // Should be kept secure and not hardcoded
params['signature'] = generateSignature(params.data, secretKey);  // Add signature

// Call the existing service method without changing it
liabilitiesMasterFactory.deleteRowData(params).then(function (data) {
    if (data) {
        // Existing data processing remains unchanged
    }
});



import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@PostMapping("/BS/IFRS/deleteRow")
@ResponseBody
public String deleteRow(@RequestBody Map<String, String> requestData, HttpServletRequest request) throws Exception {
    String encryptedData = requestData.get("data");
    String providedSignature = requestData.get("signature");
    String secretKey = "mySecretKey";  // Secure storage required

    // Regenerate the expected signature
    String expectedSignature = generateSignature(encryptedData, secretKey);

    // Validate the signature
    if (providedSignature == null || !providedSignature.equals(expectedSignature)) {
        request.getSession().invalidate();  // Force logout on failure
        return "Security validation failed. Please log in again.";
    }

    // Proceed with deletion if signature is valid
    return "Row deleted successfully.";
}

// Signature generation logic
public String generateSignature(String data, String secretKey) throws Exception {
    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
    SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
    sha256_HMAC.init(secret_key);
    return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(data.getBytes()));
}