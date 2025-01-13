public String generateSignature(String uri, String secretKey) throws Exception {
    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
    SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
    sha256_HMAC.init(secret_key);
    return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(uri.getBytes()));
}


@PostMapping("/deleteRow")
public ResponseEntity<String> deleteRow(
    @RequestHeader("X-Signature") String signature,
    HttpServletRequest request,
    @RequestBody Map<String, Object> payload) throws Exception {

    String secretKey = "mySecretKey";  // Keep this secure
    String expectedSignature = generateSignature(request.getRequestURI(), secretKey);

    if (!expectedSignature.equals(signature)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid signature. Potential tampering detected.");
    }

    // Proceed with deletion logic
    String rowId = (String) payload.get("rowId");
    return ResponseEntity.ok("Row deleted successfully.");
}


var uri = '/BS/IFRS/deleteRow';
var signature = btoa(uri + 'mySecretKey');  // Basic encoding for demonstration

$http({
    method: 'POST',
    url: uri,
    headers: {
        'X-Signature': signature
    },
    data: {
        rowId: '456'
    }
});

earl;ier you have provided me this way to resolved this issue using btoa give me the solution using this way and how does it protec the URI manipulation or secure my uri request
