dicgcReport.downloadGranularFiles = function () {
        console.log("Inside the download gran controller function");
        dicgcReportFactory.downloadGranularFile().then(function (response) {
            let file = new Blob([response.data], {
                type: 'text/csv'
            });
            saveAs(file, "Granular" + ".csv");
        }, function (errResponse) {
            alert("Failed to Download CSV " ,errResponse);
        });
    };
