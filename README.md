<%--
  Created by IntelliJ IDEA.
  User: V1012297
  Date: 04-03-2025
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<head>
    <style>
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        /* Firefox */
        input[type=number] {
            -moz-appearance: textfield;
        }

    </style>

</head>
<body>
<div class="wrapper">
    <div class="header header-filter" style="background-image: url('assets/img/bg2.jpeg');">
        <div class="container">
            <div class="row tim-row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="brand">
                        <h3 style="color: white;">SFTP STATUS</h3>
                    </div>
                </div>
            </div>

        </div>

    </div>


    <div class="main main-raised"
         style="outline: #1c2529; outline-width: inherit; outline-style: auto; height: inherit">
        <div class="section">


            <div class="container table-responsive" style="border-radius: 20px;" >
                <table class="table table-bordered table-responsive"
                       style="width:100%; border-radius: 20px; overflow: auto">
                    <thead style="background-color:#34495E; font-weight:600; color:white; border-radius: 10px;">
                    <tr>
                        <th style="text-align: center; vertical-align: middle; width: 15%;">Circle Name</th>
                        <th style="text-align: center; vertical-align: middle; width: 20%;">Sch-10 Report Status</th>
                        <th style="text-align: center; vertical-align: middle; width: 10%;">File Received Status</th>
                        <th style="text-align: center; vertical-align: middle; width: 20%;">File Recd. Date-Time</th>
                        <th style="text-align: center; vertical-align: middle; width: 15%;">Whether Revised File Received</th>
                        <th style="text-align: center; vertical-align: middle; width: 20%;">Error, if any</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="row in sftpDataList">
                        <td>
                            <input type="text" ng-model="sftpstatus.row.circleName"
                                   class="form-control text-center"
                                   style="width: 100%; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;" readonly />
                        </td>
                        <td>
                            <input type="text" ng-model="sftpstatus.row.reportStatus"
                                   class="form-control text-center"
                                   style="width: 100%;" readonly />
                        </td>
                        <td>
                            <input type="text" ng-model="sftpstatus.row.fileReceivedStatus"
                                   class="form-control text-center"
                                   style="width: 100%;" readonly />
                        </td>
                        <td>
                            <input type="text" ng-model="sftpstatus.row.timeStamp"
                                   class="form-control text-center"
                                   style="width: 100%;" readonly />
                        </td>
                        <td>
                            <input type="text" ng-model="sftpstatus.row.revisedFileStatus"
                                   class="form-control text-center"
                                   style="width: 100%;" readonly />
                        </td>
                        <td>

                            <input type="text" ng-model="sftpstatus.row.message"
                                   class="form-control text-center"
                                   style="width: 100%;" readonly />

                        </td>
                    </tr>
                    </tbody>
                </table>

                <!--Container-->
            </div>

            <!--Section-->
        </div>

        <!--Main -->
    </div>
    <!--Wrapper-->
</div>
</body>


This is Angularjs Controller 

app.controller('SFTPStatusController', function ($scope, $rootScope, $http, $window, $sessionStorage,
                                                          $state, $timeout, $location, Idle, Keepalive, $modal,
                                                          ModalService, userFactory, sftpstatusFactory, refreshFactory, AES256) {

var sftpstatus = this;
$scope.started = false;
$scope.sessionUser = JSON.parse(AES256.decrypt($rootScope.globals.currentUser));

let circleCode =$scope.sessionUser.circleCode;
let QED =$scope.sessionUser.quarterEndDate;
sftpstatus.sftpDataList={};
console.log("SFTP StatusController CircleCode IS:" +circleCode);
console.log("SFTP StatusController QED IS:" +QED);



    // Function for fetching the List of Circles from DB
    // sftpstatus.getSummaryData = function () {
        let param={
            'ReportId':'310010',
            'circleCode':circleCode,
            'QED':QED
        }

        sftpstatusFactory
            .getSFTPData(param)
            .then(function (data) {
                    console.log(" List Received :::" + JSON.stringify(data));
                    sftpstatus.sftpDataList=data;

                    console.log("After assigning the data ::"+sftpstatus.sftpDataList)

                },
                function (errResponse) {
                    console
                        .error('Error getting saved data in sftpstatus');
                });
    // }


});



below is the console log I am getting but there is no data on screen displayed yet what is the problem how did i resolve this
