let params = {
            'salt': salt,
            'iv': iv,
            'data': aesUtil.encrypt(salt, iv, passphrase, JSON.stringify(payload)),
        }
        liabilitiesMasterFactory.deleteRowData(params).then(function (data) {
            if (data) {
			some data processing 
			}
			
			
			// This is the service.js file method which is called from above contoller 
			function deleteRowData(params) {
        let deferred = $q.defer();
        $http.post(REST_SERVICE_URI_3, params).then(function (response) {
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while deleteRowData call respponse :' + errResponse);
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }
	
