@PostMapping("/IFRS/liabiltiyMaster/delete")
public ResponseEntity<String> deleteRowData(
        @RequestHeader("X-Signature") String receivedSignature,
        HttpServletRequest request,
        @RequestBody Map<String, Object> params) throws Exception {

    String secretKey = "mySecretKey";
    String requestURI = request.getRequestURI();

    String expectedSignature = generateSignature(requestURI, secretKey);

    if (!expectedSignature.equals(receivedSignature)) {
        request.getSession().invalidate();  // Logout on invalid signature
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid request. Signature mismatch.");
    }

    // Proceed with deletion logic
    String encryptedData = (String) params.get("data");
    return ResponseEntity.ok("Row deleted successfully.");
}




function deleteRowData(params) {
    let REST_SERVICE_URI_3 = './IFRS/liabiltiyMaster/delete';
    let deferred = $q.defer();

    // Generate Signature
    let secretKey = 'mySecretKey';  // Keep this secure
    let signature = CryptoJS.HmacSHA256(REST_SERVICE_URI_3, secretKey).toString(CryptoJS.enc.Base64);

    $http({
        method: 'POST',
        url: REST_SERVICE_URI_3,
        headers: {
            'X-Signature': signature  // Signature sent in headers
        },
        data: params  // Existing params remain unchanged
    }).then(function (response) {
        deferred.resolve(response.data);
    }, function (errResponse) {
        console.error('Error while deleteRowData call response :' + errResponse);
        deferred.reject(errResponse);
    });

    return deferred.promise;
}


<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>