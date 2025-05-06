app.factory('downloadJrxmlsFactory', ['$http', '$q', function ($http, $q) {

    var factory = {
        getListOfReports: getListOfReports,
        viewReport: viewReport,
        viewReportCircle: viewReportCircle,
        downloadReport: downloadReport,
        getBrList: getBrList
    };

    return factory;

    function getBrList(user) {

        var REST_SERVICE_URI = './Admin/getBrList';

        var deferred = $q.defer();
        //console.log('Returned Id getBrList : ' + user.userId);

        $http.post(REST_SERVICE_URI, user).then(function (response) {
            console.log(response.data);
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while gettng branchList');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }

    function getListOfReports(user) {
        var REST_SERVICE_URI = './Admin/getListOfReports';
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, user).then(function (response) {
            console.log(response.data);
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while updating User');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }

    function viewReport(formData) {
        // alert(formData.report.status);
        var REST_SERVICE_URI = './Admin/viewReportJrxml';
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, formData, {
            responseType: 'arraybuffer'
        }).then(function (response) {
            console.log(response.data);
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while updating User');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }

    function viewReportCircle(formData) {
        // alert(formData.report.status);
        var REST_SERVICE_URI = './Admin/viewReportJrxmlCircle';
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, formData, {
            responseType: 'arraybuffer'
        }).then(function (response) {
            console.log(response.data);
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while updating User');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }

    function downloadReport(formData) {
        // alert(formData.report.status);
        var REST_SERVICE_URI = './Admin/downloadReport';
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, formData, {
            responseType: 'arraybuffer'
        }).then(function (response) {
            console.log(response.data);
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while updating User');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }

}]);
