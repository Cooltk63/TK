 let params = {
            'salt': salt,
            'iv': iv,
            'data': aesUtil.encrypt(salt, iv, passphrase, JSON.stringify(payload)),
        }
        
        liabilitiesMasterFactory.deleteRowData(params).then(function (data) {
            if (data) {
            // Some Processing
            }
            }


This is my service.js file code to call the backend URI
             function deleteRowData(params) {
             let REST_SERVICE_URI_3 = './IFRS/liabiltiyMaster/delete';
        let deferred = $q.defer();
        $http.post(REST_SERVICE_URI_3, params).then(function (response) {
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while deleteRowData call respponse :' + errResponse);
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }
