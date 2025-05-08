app.controller('IFRSDownloadArchives' , function($scope, $rootScope, $filter, $timeout, $http, $window,
                                                 $sessionStorage, $state, $location, loginFactory, Idle,
                                                 Keepalive, $modal, ModalService, mocReversalFactory,$sce,
                                                 userFactory, mocMakerFactory, AES256, IFRSArchivesFactory) {
    let IFRSArchives = this;
    $scope.started = false;
    $scope.sessionUser = JSON.parse(AES256.decrypt($rootScope.globals.currentUser));
    let qed = $scope.sessionUser.quarterEndDate;
    let userId = $scope.sessionUser.userId;
    let circleCode = $scope.sessionUser.circleCode;
    let {passphrase, iv, salt, aesUtil} = encryptValues($scope, AES256, $rootScope);
    $('.modal-backdrop').remove();
    $('body').removeClass("modal-open");
    let report;
    IFRSArchives.selectedQed = '';
    $scope.Category = 'reportwise';
    IFRSArchives.circleList =[];

    /*function to handle Circles Data end date
     @ Author V1015698*/
    IFRSArchives.getCircleData = function () {
        let payload = {
            'qed' : qed,
            'userId' : userId
        };
        let params ={
            'salt': salt,
            'iv': iv,
            'data' : aesUtil.encrypt(salt, iv, passphrase, JSON.stringify(payload)),
        }
        IFRSArchivesFactory.getCircleList(params).then(function (data) {
            IFRSArchives.circleList = data;
            console.log(IFRSArchives.circleList);
            IFRSArchives.handleQedDropdown();
        }, function (errResponse) {
            console.error('Error while login'+errResponse);
        })
    };

    /*function to handle Quarter end date
     @ Author V1015698*/
    IFRSArchives.handleQedDropdown = function () {
        const dropdown = document.getElementById('selectQED');
        const currentDate = new Date();
        const currentYear = currentDate.getFullYear();
        const currentMonth = currentDate.getMonth();
        const currentQuarter = currentMonth < 3 ? 3 : currentMonth < 6 ? 0 : currentMonth < 9 ? 1 : 2;
        const maxCurrentQuarter = currentQuarter - 1;
        const isQ1 = currentMonth < 3; // Q1 of a financial year ends in March
        let startYear = 2024;

        // End year is the financial year that is currently active
        const endYear = isQ1 ? currentYear : currentYear + 1;

        const quarters = [
            { label: "Q1 (April-June)", month: 5, day: 30 },
            { label: "Q2 (July-September)", month: 8, day: 30 },
            { label: "Q3 (October-December)", month: 11, day: 31 },
            { label: "Q4 (January-March)", month: 2, day: 31 },
        ];

        for (let year = startYear; year < endYear; year++) {
            quarters.forEach((quarter, index) => {
                // Stop adding if we've passed the previous quarter in the current financial year
                if(year === endYear - 1 && index >= maxCurrentQuarter) return;
                const fyStartYear = year;
                const fyEndYear = year + 1;
                const endDate = new Date(
                    quarter.month === 2 ? fyEndYear : fyStartYear,
                    quarter.month,
                    quarter.day
                );

                const formattedDate = `${endDate.getDate().toString().padStart(2, '0')}/${(endDate.getMonth() + 1).toString().padStart(2, '0')}/${endDate.getFullYear()}`;
                console.log(formattedDate);
                const option = document.createElement('option');
                option.value = formattedDate;
                option.textContent = `${formattedDate} ${quarter.label}`;
                dropdown.appendChild(option);
            });
        }
    };

    IFRSArchives.onChangeCategory = function () {
        let selectedcategory = $('#selectCategory').val();
        $('#fileNotFound').hide();
        $('#errorDownload').hide();
        if(selectedcategory === 'reportwise'){
            $('#openCircle').show();
        }else{
            $('#openCircle').hide();
        }
    };

    IFRSArchives.handleDownload = function () {
        $('#fileNotFound').hide();
        $('#errorDownload').hide();
        let selectedcategory = $('#selectCategory').val();
        let selectedQed = $('#selectQED').val();
        let selectedDownloadType = $('input[name="downloadAs"]:checked').val();
        console.log(selectedcategory);
        console.log(selectedQed);
        console.log(selectedDownloadType);
        console.log(qed);
        console.log(circleCode);
        if(selectedcategory === 'reportwise'){
            let circle = $('#selectCircle').val();
            console.log(circle);
            if(circle !== '' && selectedQed !==''){
                let payload = {
                    'circleCode': circle,
                    'qed': selectedQed,
                };
                let params ={
                    'salt': salt,
                    'iv': iv,
                    'data' : aesUtil.encrypt(salt, iv, passphrase, JSON.stringify(payload)),
                }
                if(selectedDownloadType === 'PDF'){
                    IFRSArchivesFactory.downloadPdfReport(params).then(function (data) {
                        console.log(data);
                            if(data.flag){
                                $('#fileNotFound').hide();
                                $('#errorDownload').hide();
                                const link = document.createElement('a');
                                link.href = `data:application/pdf;base64,${data.pdfContent}`;
                                link.download = circle + "_IFRS_Liabilities_" + selectedQed + ".pdf";
                                link.click();
                            }else{
                                console.log('inside else case');
                                if(data.displayMessage ==='fileNotFound'){
                                    $('#fileNotFound').show();
                                    $('#errorDownload').hide();
                                }else if(data.displayMessage === 'error'){
                                    $('#errorDownload').show();
                                    $('#fileNotFound').hide();
                                }
                            }
                        },
                        function (errResponse) {
                            alert("Failed to Download PDF "+errResponse);
                        });

                }else{
                    IFRSArchivesFactory.downloadXLReport(params).then(function (data) {
                        console.log(data);
                            if(data.flag){
                                $('#fileNotFound').hide();
                                $('#errorDownload').hide();
                                const byteCharecters = atob(data.pdfContent);
                                const byteNumbers = new Array(byteCharecters.length);
                                for (let i = 0; i < byteCharecters.length; i++) {
                                    byteNumbers[i] = byteCharecters.charCodeAt(i);
                                }
                                const byteArray = new Uint8Array(byteNumbers);
                                const blob = new Blob([byteArray], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
                                const link = document.createElement('a');
                                link.href = URL.createObjectURL(blob);
                                link.download = circle + "_IFRS_Liabilities_" + selectedQed + ".xlsx";
                                link.click();
                            }else {
                                if(data.displayMessage ==='fileNotFound'){
                                    $('#fileNotFound').show();
                                    $('#errorDownload').hide();
                                }else if(data.displayMessage === 'error'){
                                    $('#errorDownload').show();
                                    $('#fileNotFound').hide();
                                }
                            }
                        },
                        function (errResponse) {
                            alert("Failed to Download Excel "+errResponse);
                        });
                }

            }
        }else if(selectedcategory === 'consolidation' || selectedcategory === 'collation'){
            if(selectedQed !== ''){
                let payload = {
                    'qed': selectedQed,
                    'type': selectedcategory,
                    'circleCode': circleCode,
                };
                let params ={
                    'salt': salt,
                    'iv': iv,
                    'data' : aesUtil.encrypt(salt, iv, passphrase, JSON.stringify(payload)),
                }
                if(selectedDownloadType === 'PDF'){
                    IFRSArchivesFactory.downloadPdf(params).then(function (data) {
                            if(data.flag){
                                $('#fileNotFound').hide();
                                $('#errorDownload').hide();
                                const link = document.createElement('a');
                                link.href = `data:application/pdf;base64,${data.pdfContent}`;
                                link.download = "IFRS_Liabilities_" + selectedcategory +"_" + selectedQed + ".pdf";
                                link.click();
                            }else{
                                if(data.displayMessage ==='fileNotFound'){
                                    $('#fileNotFound').show();
                                    $('#errorDownload').hide();
                                }else if(data.displayMessage ==='error'){
                                    $('#fileNotFound').hide();
                                    $('#errorDownload').show();
                                }
                            }
                        },
                        function (errResponse) {
                            alert("Failed to Download PDF "+errResponse);
                        });

                }else{
                    IFRSArchivesFactory.downloadXL(params).then(function (data) {
                            if(data.flag){
                                const byteCharecters = atob(data.pdfContent);
                                const byteNumbers = new Array(byteCharecters.length);
                                for (let i = 0; i < byteCharecters.length; i++) {
                                    byteNumbers[i] = byteCharecters.charCodeAt(i);
                                }
                                const byteArray = new Uint8Array(byteNumbers);
                                const blob = new Blob([byteArray], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
                                const link = document.createElement('a');
                                link.href = URL.createObjectURL(blob);
                                link.download = "IFRS_Liabilities_"+ selectedcategory +"_" + selectedQed + ".xlsx";
                                link.click();
                            }else{
                                if(data.displayMessage ==='fileNotFound'){
                                    $('#errorDownload').hide();
                                    $('#fileNotFound').show();
                                }else if(data.displayMessage ==='error'){
                                    $('#fileNotFound').hide();
                                    $('#errorDownload').show();
                                }
                            }
                        },
                        function (errResponse) {
                            alert("Failed to Download Excel "+errResponse);
                        });
                }
            }
        }
    };

// Excel Download Fn
    IFRSArchives.downloadReportXL = function () {
        $('#fileNotFound').hide();
        $('#errorDownload').hide();
        let selectedQed = $('#selectQED').val();
        console.log(selectedQed);
        let payload = {
            'circleCode': circleCode,
            'qed': selectedQed,
        };
        let params ={
            'salt': salt,
            'iv': iv,
            'data' : aesUtil.encrypt(salt, iv, passphrase, JSON.stringify(payload)),
        }
        IFRSArchivesFactory.downloadXLReport(params).then(function (data) {
                if(data.flag){
                    const byteCharecters = atob(data.pdfContent);
                    const byteNumbers = new Array(byteCharecters.length);
                    for (let i = 0; i < byteCharecters.length; i++) {
                        byteNumbers[i] = byteCharecters.charCodeAt(i);
                    }
                    const byteArray = new Uint8Array(byteNumbers);
                    const blob = new Blob([byteArray], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
                    const link = document.createElement('a');
                    link.href = URL.createObjectURL(blob);
                    link.download = circleCode + "_IFRS_Liabilities_" + selectedQed + ".xlsx";
                    link.click();
                }else{
                    if(data.displayMessage ==='fileNotFound'){
                        $('#errorDownload').hide();
                        $('#fileNotFound').show();
                    }else if(data.displayMessage ==='error'){
                        $('#fileNotFound').hide();
                        $('#errorDownload').show();
                    }
                }
            },
            function (errResponse) {
                alert("Failed to Download Excel "+errResponse);
            });
    };

// PDF Download Fn
    IFRSArchives.downloadReportPdf = function () {
        $('#fileNotFound').hide();
        $('#errorDownload').hide();
        let selectedQed = $('#selectQED').val();
        console.log(selectedQed);
        let payload = {
            'circleCode': circleCode,
            'qed': selectedQed,
        };
        let params ={
            'salt': salt,
            'iv': iv,
            'data' : aesUtil.encrypt(salt, iv, passphrase, JSON.stringify(payload)),
        }
        IFRSArchivesFactory.downloadPdfReport(params).then(function (data) {
                if(data.flag){
                    const link = document.createElement('a');
                    link.href = `data:application/pdf;base64,${data.pdfContent}`;
                    link.download = circleCode + "_IFRS_Liabilities_" + selectedQed + ".pdf";
                    link.click();
                }else{
                    if(data.displayMessage ==='fileNotFound'){
                        $('#errorDownload').hide();
                        $('#fileNotFound').show();
                    }else if(data.displayMessage ==='error'){
                        $('#fileNotFound').hide();
                        $('#errorDownload').show();
                    }
                }
            },
            function (errResponse) {
                alert("Failed to Download PDF "+errResponse);
            });
    };
});
