app.factory('IFRSArchivesFactory', ['$http', '$q', function ($http, $q) {
    let REST_SERVICE_URI_0 = './IFRSArchives/GetCircleList';
    let REST_SERVICE_URI_1 = './IFRSArchives/ArchiveReportsDownloadXL';
    let REST_SERVICE_URI_2 = './IFRSArchives/ArchiveReportsDownloadPDF';
    let REST_SERVICE_URI_3 = './IFRSArchives/ArchiveReportsDownloadPDFUser';
    let REST_SERVICE_URI_4 = './IFRSArchives/ArchiveReportsDownloadXLUser';

    return {
        getCircleList: getCircleList,
        downloadXLReport: downloadXLReport,
        downloadPdfReport: downloadPdfReport,
        downloadPdf: downloadPdf,
        downloadXL: downloadXL,
    };

    function getCircleList(params) {
        let deferred = $q.defer();
        $http.post(REST_SERVICE_URI_0, params).then(function (response) {
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while getList call respponse :' + errResponse);
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }
    function downloadXLReport(params) {
        let deferred = $q.defer();
        $http.post(REST_SERVICE_URI_1, params).then(function (response) {
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while Downloading');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }
    function downloadPdfReport(params) {
        let deferred = $q.defer();
        $http.post(REST_SERVICE_URI_2, params).then(function (response) {
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while Downloading');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }
    function downloadPdf(params) {
        let deferred = $q.defer();
        $http.post(REST_SERVICE_URI_3, params).then(function (response) {
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while Downloading');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }
    function downloadXL(params) {
        let deferred = $q.defer();
        $http.post(REST_SERVICE_URI_4, params).then(function (response) {
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while Downloading');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }



}]);
