 $scope.downloadMocFiles = function () {
            console.log("Inside the download MOC controller function");
            mocMakerFactory.downloadMocFile().then(function (response) {
                let file = new Blob([response], {
                    type: 'text/csv'
                });
                saveAs(file, "Moc"+".csv");

            }, function (errResponse) {
                alert("Failed to Download CSV " ,errResponse);
            });
        };
