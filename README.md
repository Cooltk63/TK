This is controller.js file code for JS

app
    .controller('downloadJrxmls', function ($scope, $log, $rootScope, $sce, $http, $window, $sessionStorage, $state, $location, $filter, downloadJrxmlsFactory, circleMakerWorklist, Idle, Keepalive, $modal, ModalService, userFactory, AES256) {
        var downloadJrxmls = this;
        $scope.started = false;
        downloadJrxmls.showDiv = false;
        downloadJrxmls.showAltMesge = false;
        $scope.sessionUser = JSON.parse(AES256.decrypt($rootScope.globals.currentUser));
        $scope.heading = '';

        if ($scope.sessionUser.capacity == '61' || $scope.sessionUser.capacity == '62') {
            $scope.heading = '1';
            console.log("insid 61 or  62");
        } else if ($scope.sessionUser.capacity == '52' || $scope.sessionUser.capacity == '51' || $scope.sessionUser.capacity == '53') {
            console.log("insid 51 or 52");
            $scope.heading = '2';
        } else if ($scope.sessionUser.capacity == '81') {
            console.log("insid 81");
            $scope.heading = '4';
        } else if ($scope.sessionUser.capacity == '91') {
            console.log("insid 91");
            $scope.heading = '4';
        } else if ($scope.sessionUser.capacity == '41') {
            console.log("insid 41");
            $scope.heading = '5';
        } else {
            $scope.heading = '3';
            console.log("insid others");
        }
        // /////////////////////////16052018
        downloadJrxmlsFactory.getBrList($scope.sessionUser).then(function (data) {

            console.log("data" + data + ' length: ' + data.length);

            downloadJrxmls.branchList = data;

        }, function (errResponse) {
            console.error('Error while getting branchList');
        });
        // ////////////end 16052018


        downloadJrxmls.listZ = [];
        downloadJrxmlsFactory
            .getListOfReports($scope.sessionUser)
            .then(function (data) {
                var listWithZ = [];
                console.log(data.list1);

                if (!data.list1.length == '0' || !data.list1.length == null) {

                    downloadJrxmls.showDiv = true;
                    // downloadJrxmls.listOfRows = data;
                    downloadJrxmls.listOfRows = data.list1;


                    downloadJrxmls.listOfCircle = data.list;


                    ////////22102018Start

                    listWithZ = $filter("filter")(downloadJrxmls.listOfRows, {
                        dash_suppresed: 'Z'

                    });
                    if (listWithZ.length > 0) {
                        downloadJrxmls.showAltMesge = true;
                    }


                    /*for (i=0;i<listWithZ.length;i++) {
                                console.log("report with supressed flag  "+JSON.stringify(listWithZ[i].dash_suppresed)+"  * Name * "+JSON.stringify(listWithZ[i].dash_name));
                            }*/

                    console.log("number of reports with dash suppresed as Z * " + listWithZ.length);
                    //////22102018End

                    //21082018
                    downloadJrxmls.FreezedCircles = data.freezedCircles;
                    //	console.log("******************* freezed Circles  "+downloadJrxmls.FreezedCircles.length+"  *  "+downloadJrxmls.FreezedCircles);


                    $scope.selectedBranchCode = 'All Branches';

                    $scope.type = 'PRE';

                } else {
                    downloadJrxmls.message = 'No reports are found to download.';
                }

                // downloadJrxmls.listOfRows = data;
                // $scope.type = 'PRE';
            }, function (errResponse) {

            });


        downloadJrxmls.ind = '';
        downloadJrxmls.act = '';
        downloadJrxmls.prep = '';
        downloadJrxmls.comc = '';
        downloadJrxmls.brc = '';
        downloadJrxmls.dashSupp = '';
        downloadJrxmls.isSupp = '';
        downloadJrxmls.dParam = '';
        downloadJrxmls.checkCsv = function (index, action, prePost, compcircle, branchCode, dash_suppresed, isSuppresed, params) {


            downloadJrxmls.ind = index;
            downloadJrxmls.act = action;
            downloadJrxmls.prep = prePost;
            downloadJrxmls.comc = compcircle;
            downloadJrxmls.brc = branchCode;
            downloadJrxmls.dashSupp = dash_suppresed;
            downloadJrxmls.isSupp = isSuppresed;
            downloadJrxmls.dParam = params;
            $('#myModal999').modal({
                backdrop: 'static', keyboard: false, modal: true
            });
            $('#myModal999').on('shown.bs.modal', function () {
                $('#myModal999').trigger('focus');
            });


        }


        downloadJrxmls.viewReportJrxmlCircle = function (index, action, prePost, compcircle, branchCode, dash_suppresed, isSuppresed, params) {
            // //////////////added 21052018 begin setting
            console.log("**************At Circle Level********* dash_param * " + params);
            var prePostValue = "";
            //eliminating PRE or POST from file name if it do not contain TYPE drop down
            if (params.indexOf('TYPE') != '-1') {
                prePostValue = "_" + prePost;
            }

            console.log("***************** prePostValue * " + prePostValue);

            console.log("**********  At Circle Level **** index " + index + "  *   action " + action + "  *   prePost " + prePost + "  *  compCode " + compcircle + "  *   dash_suppresed " + dash_suppresed + "  * branchCode " + branchCode);

            var isSup = document.getElementById(index).checked;
            console.log(" isSuppresed Circle Level " + isSup);
            var cirCode = $scope.sessionUser.circleCode;
            //alert(dash_suppresed);
            console.log("brc - " + branchCode);
            console.log(compcircle);
            console.log(prePost);

            var saveAsName = "";
            if (!branchCode || branchCode == "All Branches") {
                console.log("save as for circle*");
                saveAsName = $scope.sessionUser.circleName
                    .substring(0, 3);

            } else if (branchCode != "All Branches") {
                console.log("save as for brcode***");
                saveAsName = branchCode;
            }

            console.log("save as Name ******  " + saveAsName);
            // //////////////added 21052018 End
            downloadJrxmls.pdfContent = '';
            console.log(action + "--" + compcircle);
            var report = downloadJrxmls.listOfRows[index];
            console.log(report);
            var params = {
                'report': report,
                'user': $scope.sessionUser,
                'type': action,
                'prePost': prePost,
                'compcircle': compcircle,
                'branchCode': branchCode,
                'dash_suppresed': dash_suppresed,
                'isSuppresed': isSup
            };

            downloadJrxmlsFactory
                .viewReportCircle(params)
                .then(function (data) {
                    if (action == "view") {

                        var file = new Blob([data], {
                            type: 'text/html'
                        });
                        $('#visualizador').show();
                        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
                            window.navigator
                                .msSaveOrOpenBlob(file, saveAsName + "_" + $scope.sessionUser.quarterEndDate + "_" + report.dash_name + prePostValue + ".html");
                        } else {
                            var url = URL
                                .createObjectURL(file);
                            downloadJrxmls.pdfContent = $sce
                                .trustAsResourceUrl(url);
                            // document.getElementById('visualizador').setAttribute('src',url);
                        }
                    } else if (action == "downloadPDF") {

                        var file1 = new Blob([data], {
                            type: 'application/pdf'
                        });

                        saveAs(file1, saveAsName + "_" + $scope.sessionUser.quarterEndDate + "_" + report.dash_name + prePostValue + ".pdf");
                    } else if (action == "download") {

                        var file2 = new Blob([data], {
                            type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
                        });
                        saveAs(file2, saveAsName + "_" + $scope.sessionUser.quarterEndDate + "_" + report.dash_name + prePostValue + ".xlsx");
                    } else if (action.substring(0, 11) == "downloadCSV") {


                        //alert("Please verify the credit and debit amount before uploading the file to FRT!!")
                        var filetype = "";
                        var extension = action.substring(11, 12);
                        if (extension == "C") {

                            extension = ".txt";
                            filetype = 'application/text/plain;charset=utf-8';
                        } else {
                            extension = ".txt";
                            filetype = 'application/text/plain;charset=utf-8';
                        }
                        var file3 = new Blob([data], {
                            type: filetype
                        });
                        saveAs(file3, saveAsName + "_" + $scope.sessionUser.quarterEndDate + "_" + report.dash_name + prePostValue + extension);
                    }

                }, function (errResponse) {

                });
        }

        // //////////////////////////////////////////////////////
        downloadJrxmls.clearcheckcircle = function () {
            downloadJrxmls.checkcircle = false;
            downloadJrxmls.checkcircleMessage = "";
            return false;
        }
        downloadJrxmls.viewReportJrxml = function (index, action, prePost, compcircle, dash_suppresed, isSuppresed, params) {
            console.log("****************At FRT Level******* dash_param * " + params);
            var prePostValue = "";
            //eliminating PRE or POST from file name if it do not contain TYPE drop down
            if (params.indexOf('TYPE') != '-1') {
                prePostValue = "_" + prePost;
            }

            console.log("***************** prePostValue * " + prePostValue);

            console.log("**********  At FRT Level **** index " + index + "  *   action " + action + "  *   prePost " + prePost + "  *  circle " + compcircle + "  *   dash_suppresed " + dash_suppresed);
            downloadJrxmls.checkcircle = false;
            downloadJrxmls.pdfContent = '';
            console.log(action + "--" + compcircle);
            var report = downloadJrxmls.listOfRows[index];
            console.log("***************$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  FreezedCircles length " + downloadJrxmls.FreezedCircles.length);
            // console.log("@@@@@@@@@@@@@@@@@@
            // "+JSON.stringify(report));
            // console.log("-------------
            // "+report.dash_param.indexOf('COMP'));
            // console.log("------------ "+compcircle);
            if (compcircle == undefined && report.dash_param.indexOf('COMP') != '-1') {

                downloadJrxmls.checkcircleMessage = "Please select circle";
                downloadJrxmls.checkcircle = true;
                return false;
            }
            /*if (compcircle == undefined
								&& report.dash_param.indexOf('CIRCLE_LIST') != '-1') {
							downloadJrxmls.checkcircle = true;

							return false;
						}*/
            if (compcircle == undefined && report.dash_param.indexOf('CIRCLE_LIST') != '-1' && downloadJrxmls.FreezedCircles.length > 0) {
                downloadJrxmls.checkcircleMessage = "Please select circle";
                downloadJrxmls.checkcircle = true;

                return false;
            } else if (downloadJrxmls.FreezedCircles.length == 0 && report.dash_param.indexOf('CIRCLE_LIST') != '-1') {

                downloadJrxmls.checkcircleMessage = "No circle has been freezed to generate Consolidation Report";
                downloadJrxmls.checkcircle = true;
                console.log("------------*****************************inside No circle has been freezed");
                return false;
            }


            var isSup = document.getElementById(index).checked;

            console.log(" isSuppresed FRT Level " + isSup);
            var params = {
                'report': report,
                'user': $scope.sessionUser,
                'type': action,
                'prePost': prePost,
                'compcircle': compcircle,
                'dash_suppresed': dash_suppresed,
                'isSuppresed': isSup

            };

            downloadJrxmlsFactory
                .viewReport(params)
                .then(function (data) {
                    if (action == "view") {

                        var file = new Blob([data], {
                            type: 'application/pdf'
                        });
                        $('#visualizador').show();
                        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
                            window.navigator
                                .msSaveOrOpenBlob(file, $scope.sessionUser.circleName
                                    .substring(0, 3) + "_" + $scope.sessionUser.quarterEndDate + "_" + report.dash_name + prePostValue + ".pdf");
                        } else {
                            var url = URL
                                .createObjectURL(file);
                            downloadJrxmls.pdfContent = $sce
                                .trustAsResourceUrl(url);
                            // document.getElementById('visualizador').setAttribute('src',url);
                        }
                    } else if (action == "downloadPDF") {

                        var file1 = new Blob([data], {
                            type: 'application/pdf'
                        });

                        saveAs(file1, $scope.sessionUser.circleName
                            .substring(0, 3) + "_" + $scope.sessionUser.quarterEndDate + "_" + report.dash_name + prePostValue + ".pdf");
                    } else if (action == "download") {

                        var file2 = new Blob([data], {
                            type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
                        });
                        saveAs(file2, $scope.sessionUser.circleName
                            .substring(0, 3) + "_" + $scope.sessionUser.quarterEndDate + "_" + report.dash_name + prePostValue + ".xlsx");
                    }

                }, function (errResponse) {

                });
        }

        // //////////////////////////////////////////////////

    });



This is factory or service file api call to backend used in above code

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
