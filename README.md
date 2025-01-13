function deleteRowData(params) {

        let deferred = $q.defer();

        // Function to generate HMAC-SHA256 signature (CryptoJS must be included)
        function generateSignature(data, secretKey) {
            return CryptoJS.HmacSHA256(data, secretKey).toString(CryptoJS.enc.Base64);
        }

        let secretKey = 'mySecretKey';

        let signature=generateSignature(REST_SERVICE_URI_3,secretKey);
        console.log("Value of signature"+signature);
        
        $http({
            method:'POST',
            url:REST_SERVICE_URI_3,
            data:params,
         headers:{
            'X-Signature': signature
        }

        }).then(function (response) {
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while deleteRowData call respponse :' + errResponse);
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }

    Getting this error 

    angular.js:15697 TypeError: CryptoJS.HmacSHA256 is not a function
    at generateSignature (user_service.js:9535:29)
    at Object.deleteRowData (user_service.js:9540:23)
    at configLiabilities.confirmDeleteRow (ifrsconfigLiabilities.js:301:34)
    at fn (eval at compile (angular.js:16548:15), <anonymous>:4:610)
    at callback (angular.js:29123:13)
    at Scope.$eval (angular.js:19523:28)
    at Scope.$apply (angular.js:19622:25)
    at HTMLButtonElement.<anonymous> (angular.js:29127:19)
    at HTMLButtonElement.dispatch (jquery-3.7.1.js:5145:27)
    at elemData.handle (jquery-3.7.1.js:4949:28) undefined

    This is the .js file added for crypto-js.min.js
﻿


