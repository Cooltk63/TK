Vulnerability
Key Management: Hardcoded Encryption Key

Vulnerability Description in Detail
Hardcoded encryption keys can compromise security in a way that is not easy to remedy.

Likely Impact
Hardcoded encryption keys can compromise security in a way that is not easy to remedy.

Recommendation
Encryption keys should never be hardcoded and should be obfuscated and managed in an external source. Storing encryption keys in plain text anywhere on the system allows anyone with sufficient permissions to read and potentially misuse the encryption key.

Code Impacted ::
 public static final String secretKey = "XTY@784";

    private String generateSignature(String uri, String secretKey) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(uri.getBytes()));
    }
