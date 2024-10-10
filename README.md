app
    .controller(
        'sc09Controller',
        function ($scope, $rootScope, $http, $window, $sessionStorage,
                  $timeout, $state, $location, sc09Factory, Idle,
                  Keepalive, $modal, ModalService, userFactory, refreshFactory, AES256) {
                  console.log("inside sc09Controller");

            if ($rootScope.reportObject == undefined) {
                refreshFactory.backToState();
                return;
            }

            console.log("Report Status is " + $rootScope.reportObject.status);

            $scope.sessionUser = JSON.parse(AES256.decrypt($rootScope.globals.currentUser));
            var sc09 = this;
            $scope.started = false;
            sc09.row = {};
            var arr = [];
            sc09.adjColmn = false;
            let circleCode =$scope.sessionUser.circleCode;
            let reportID="310009";

            // List for SFTP Fn.
            sc09.CirclesList = ["001", "002", "003", "004", "005","006","007","008","009","011","015","014","016","017","010","012","013","023","024","027"];

            if ($scope.sessionUser.capacity == '61') {
                sc09.adjColmn = true;
            }
            if ($scope.sessionUser.capacity == '51') {
                sc09.row.facility_Adj_1 = 0;
                sc09.row.facility_Adj_2 = 0;
                sc09.row.facility_Adj_3 = 0;
                sc09.row.facility_Adj_Total = 0;
                sc09.row.security_Adj_1 = 0;
                sc09.row.security_Adj_2 = 0;
                sc09.row.security_Adj_3 = 0;
                sc09.row.security_Adj_Total = 0;
                sc09.row.sector_Adj_a1 = 0;
                sc09.row.sector_Adj_a2 = 0;
                sc09.row.sector_Adj_a3 = 0;
                sc09.row.sector_Adj_a4 = 0;
                sc09.row.sector_Adj_a_Total = 0;
                sc09.row.sector_Adj_b1 = 0;

                sc09.row.sector_Adj_1 = 0;
                sc09.row.sector_Adj_2 = 0;
                sc09.row.sector_Adj_3 = 0;
                sc09.row.sector_Adj_b_Total = 0;
                sc09.row.sector_Adj_ab_Total = 0;


            }

            //Disbaling all the tables cells
            $('#example1 :input').prop("disabled",true);

            // New Change Here
            //*****************************//

            // Function to check if a 3-digit circleCode (as a string) exists in the list
            sc09.isCircleInList = function(circleCode) {
                return sc09.CirclesList.includes(circleCode);
            };

            // SFTP Error Disables Buttons
            sc09.btnDisable=function()
            {
                $('#savebtn').prop("disabled",true);
                $('#submitbtn').prop("disabled",true);
            }

            // Required for Getting Screen Data
            var userInfo = {
                'circleCode': $scope.sessionUser.circleCode,
                'quarterEndDate': $scope.sessionUser.quarterEndDate,
                'qed': $scope.sessionUser.quarterEndDate,
                'role': $scope.sessionUser.capacity,
                'reportName': 'SC9'
            };

            // Get Screen Data Main Fn.
            sc09.getInitialScreenData=function() {

                // Parameters Required for SFTP
                const sftpParams = {
                    'circleCode': $scope.sessionUser.circleCode,
                    'qed': $scope.sessionUser.quarterEndDate,
                    'reportID': '310009',
                    'reportName': 'SC9'
                };

                // Checked if Circle Authorized to Fetch Files/Data
                if (sc09.isCircleInList(circleCode)) {
                    console.log(circleCode + " exists in the list.");

                    // Call SFTP Here
                    sc09Factory.getSFTPSC9Data(sftpParams).then(function (data) {
                        console.log("Received data get SFTP SC9Data Load :" + JSON.stringify(data));

                        console.log("status:-"+data.fileAndDataStatus);

                        // File Existed & SFTP Successfully Inserted Data
                        if (data.fileAndDataStatus===1) {
                            console.log("SFTP Success Loading Data on Screen ::Status:1::");

                            // Show the Success Model
                            //sc09.displayModel(data);

                            console.log("Calling SFTPDataSet Fn.")
                            // Setting Data for Bean -- FOR GETTING DATA FROM TEXT
                            sc09.SFTPDataSet(data);

                             // Disable Tables Cells
                            $('#example1 :input').prop("disabled",true);
                            //sc09.btnDisable();

                        }

                            // Error Or File Mismatch IN Files SFTP
                        else if(data.fileAndDataStatus===2){
                                // Show the Error Model & Redirected back to worklist
                                console.log("Error While SFTP Data- Inside Else Part ! with Error Message Model ::Status:2::");
                            sc09.displayModel(data);
                            $('#example1 :input').prop("disabled",true);
                            sc09.btnDisable();
                            }


                        // No Files Existed  or Error Files Received but data already inserted in DB
                        else {
                            console.log("No Files Existed  or Error Files Received but data already inserted in DB ::Status:3::");
                            sc09.displayModel(data);
                            sc09.getSC9Dataalt(userInfo);

                            // DISABLE ALL THE FIELDS OR CELLS HERE
                            $('#example1 :input').prop("disabled",true);
                            console.log(circleCode + " does not exist in the list.");

                            }
                        },

                        function (errResponse) {
                            console.error('Error while getting Files from SFTP ');
                            $('#example1 :input').prop("disabled",true);
                            sc09.btnDisable();
                        });
                } else {

                    // Calling getData For Manually Inserting Values
                    console.log("Circle Not Exists in List Manually Feed the data ");
                    sc09.getSC9Dataalt(userInfo);

                    // DISABLE ALL THE FIELDS OR CELLS HERE
                    $('#example1 :input').prop("disabled",false);
                    console.log(circleCode + " does not exist in the list.");
                }
            }
            // Get Screen Data Fn. END Here
            //*********************************//



            var row = {
                'circleCode': $scope.sessionUser.circleCode,
                'quarterEndDate': $scope.sessionUser.quarterEndDate,
                'role': $scope.sessionUser.capacity
            };

            // Regular Flow Function for Getting Data
            sc09.getSC9Dataalt=function(userInfo) {

                sc09Factory.getSC09ReportData(userInfo).then(
                    function (data) {
                        console.log(data);
                        sc09.row = data;
                        sc09Factory
                            .getSC09ValiadationData(row)
                            .then(
                                function (data) {

                                    console.log(data);
                                    var a, b, c;

                                    if ($scope.sessionUser.capacity == '51' || $scope.sessionUser.capacity == '52') {
                                        console.log("get validation data for sc09 with User Role " + $scope.sessionUser.capacity);
                                        for (var keyName in data) {
                                            var key = keyName;
                                            var value = data[keyName];
                                            if (key == "[i] Bills Purchased and Discounted less bills rediscounted $") {
                                                sc09.row.facility_Total_Display1 = (sc09
                                                    .parseFloat(value))
                                                    .toFixed(2);
                                                a = value;
                                            }
                                            if (key == "[ii]Cash Credits, Overdrafts, Loans repayable on Demand and Recalled Assets $$") {
                                                sc09.row.facility_Total_Display2 = (sc09
                                                    .parseFloat(value))
                                                    .toFixed(2);
                                                b = value;
                                            }
                                            if (key == "[iii]  Term Loans , Agricultural Term Loans, FCNRB Term Loan") {
                                                // sc09.row.facility_Total_3=value;
                                                // sc09.row.facility_Total_Display3=value;
                                                sc09.row.facility_Total_Display3 = (sc09
                                                    .parseFloat(value))
                                                    .toFixed(2);
                                                c = value;

                                            }
                                        }
                                    } else if ($scope.sessionUser.capacity == '61' || $scope.sessionUser.capacity == '62') {
                                        console.log("get validation data for sc09 with User Role  " + $scope.sessionUser.capacity);
                                        for (var keyName in data) {
                                            var key = keyName;
                                            var value = data[keyName];


                                            if (key == "090101001051") {
                                                var v = value.split("~");
                                                console.log("Adjustmnt column data row 1 is " + v[0] + " Total column data row 1 is " + v[1]);
                                                sc09.row.facility_Adj_1 = v[0];
                                                console.log("entered 090101001051 * " + (sc09
                                                    .parseFloat(v[1]))
                                                    .toFixed(2));


                                                sc09.row.facility_Total_Display1 = (sc09
                                                    .parseFloat(v[1]))
                                                    .toFixed(2);

                                                a = v[1];
                                            }
                                            if (key == "090101001052") {
                                                var v = value.split("~");
                                                console.log("Adjustmnt column data row 2 is " + v[0] + " Total column data row 2 is " + v[1]);
                                                sc09.row.facility_Adj_2 = v[0];
                                                console.log("entered 090101001052 * " + (sc09
                                                    .parseFloat(v[1]))
                                                    .toFixed(2));

                                                sc09.row.facility_Total_Display2 = (sc09
                                                    .parseFloat(v[1]))
                                                    .toFixed(2);
                                                b = v[1];
                                            }
                                            if (key == "090101001053") {
                                                var v = value.split("~");
                                                console.log("Adjustmnt column data row 3 is " + v[0] + " Total column data row 3 is " + v[1]);
                                                sc09.row.facility_Adj_3 = v[0];
                                                console.log("entered 090101001053 * " + (sc09
                                                    .parseFloat(v[1]))
                                                    .toFixed(2));

                                                sc09.row.facility_Total_Display3 = (sc09
                                                    .parseFloat(v[1]))
                                                    .toFixed(2);
                                                c = v[1];

                                            }
                                        }
                                    }


                                    sc09.row.facility_Total_TotalDisplay = (sc09
                                            .parseFloat(a)
                                        + sc09.parseFloat(b) + sc09
                                            .parseFloat(c)).toFixed(2);
                                    var row1 = ({
                                        'FirstTotal': a,
                                        'SecondTotal': b,
                                        'ThirdTotal': c
                                    });
                                    sc09.row = angular.extend({}, sc09.row,
                                        row1);

                                    console.log("VV " + sc09.row.facility_Total_Display1 + " # " + sc09.row.facility_Total_Display2 + " # " + sc09.row.facility_Total_Display3);
                                    //  alert("  # "+sc09.row.facility_Total_Display1+" # "+sc09.row.facility_Total_Display2+" # "+sc09.row.facility_Total_Display3);
                                    console.log("get val data for sc09 total " + sc09.row.facility_Total_TotalDisplay + " Total 1 " + sc09.row.FirstTotal + " Total 2 " + sc09.row.SecondTotal + " Total 3 " + sc09.row.ThirdTotal);
                                }, function (errResponse) {
                                    console.error('Error while login');
                                });
                    }, function (errResponse) {
                        console.error('Error while login');
                    });
            }

            // SFTP Data Function for Setting Values to Screen
            sc09.SFTPDataSet=function(data) {
                console.log("Inside SFTPDataSet Data Received :"+JSON.stringify(data));
                        if(data.status)
                        {
                            sc09.displayModel(data);
                            sc09.row = data.sc09Data;
                            sc09Factory
                                .getSC09ValiadationData(row)
                                .then(
                                    function (data) {

                                        console.log(data);
                                        var a, b, c;

                                        if ($scope.sessionUser.capacity == '51' || $scope.sessionUser.capacity == '52') {
                                            console.log("get validation data for sc09 with User Role " + $scope.sessionUser.capacity);
                                            for (var keyName in data) {
                                                var key = keyName;
                                                var value = data[keyName];
                                                if (key == "[i] Bills Purchased and Discounted less bills rediscounted $") {
                                                    sc09.row.facility_Total_Display1 = (sc09
                                                        .parseFloat(value))
                                                        .toFixed(2);
                                                    a = value;
                                                }
                                                if (key == "[ii]Cash Credits, Overdrafts, Loans repayable on Demand and Recalled Assets $$") {
                                                    sc09.row.facility_Total_Display2 = (sc09
                                                        .parseFloat(value))
                                                        .toFixed(2);
                                                    b = value;
                                                }
                                                if (key == "[iii]  Term Loans , Agricultural Term Loans, FCNRB Term Loan") {
                                                    // sc09.row.facility_Total_3=value;
                                                    // sc09.row.facility_Total_Display3=value;
                                                    sc09.row.facility_Total_Display3 = (sc09
                                                        .parseFloat(value))
                                                        .toFixed(2);
                                                    c = value;

                                                }
                                            }
                                        } else if ($scope.sessionUser.capacity == '61' || $scope.sessionUser.capacity == '62') {
                                            console.log("get validation data for sc09 with User Role  " + $scope.sessionUser.capacity);
                                            for (var keyName in data) {
                                                var key = keyName;
                                                var value = data[keyName];


                                                if (key == "090101001051") {
                                                    var v = value.split("~");
                                                    console.log("Adjustmnt column data row 1 is " + v[0] + " Total column data row 1 is " + v[1]);
                                                    sc09.row.facility_Adj_1 = v[0];
                                                    console.log("entered 090101001051 * " + (sc09
                                                        .parseFloat(v[1]))
                                                        .toFixed(2));


                                                    sc09.row.facility_Total_Display1 = (sc09
                                                        .parseFloat(v[1]))
                                                        .toFixed(2);

                                                    a = v[1];
                                                }
                                                if (key == "090101001052") {
                                                    var v = value.split("~");
                                                    console.log("Adjustmnt column data row 2 is " + v[0] + " Total column data row 2 is " + v[1]);
                                                    sc09.row.facility_Adj_2 = v[0];
                                                    console.log("entered 090101001052 * " + (sc09
                                                        .parseFloat(v[1]))
                                                        .toFixed(2));

                                                    sc09.row.facility_Total_Display2 = (sc09
                                                        .parseFloat(v[1]))
                                                        .toFixed(2);
                                                    b = v[1];
                                                }
                                                if (key == "090101001053") {
                                                    var v = value.split("~");
                                                    console.log("Adjustmnt column data row 3 is " + v[0] + " Total column data row 3 is " + v[1]);
                                                    sc09.row.facility_Adj_3 = v[0];
                                                    console.log("entered 090101001053 * " + (sc09
                                                        .parseFloat(v[1]))
                                                        .toFixed(2));

                                                    sc09.row.facility_Total_Display3 = (sc09
                                                        .parseFloat(v[1]))
                                                        .toFixed(2);
                                                    c = v[1];

                                                }
                                            }
                                        }


                                        sc09.row.facility_Total_TotalDisplay = (sc09
                                                .parseFloat(a)
                                            + sc09.parseFloat(b) + sc09
                                                .parseFloat(c)).toFixed(2);
                                        var row1 = ({
                                            'FirstTotal': a,
                                            'SecondTotal': b,
                                            'ThirdTotal': c
                                        });
                                        sc09.row = angular.extend({}, sc09.row,
                                            row1);

                                        console.log("VV " + sc09.row.facility_Total_Display1 + " # " + sc09.row.facility_Total_Display2 + " # " + sc09.row.facility_Total_Display3);
                                        //  alert("  # "+sc09.row.facility_Total_Display1+" # "+sc09.row.facility_Total_Display2+" # "+sc09.row.facility_Total_Display3);
                                        console.log("get val data for sc09 total " + sc09.row.facility_Total_TotalDisplay + " Total 1 " + sc09.row.FirstTotal + " Total 2 " + sc09.row.SecondTotal + " Total 3 " + sc09.row.ThirdTotal);
                                    }, function (errResponse) {
                                        console.error('Error while login');
                                    });
                        }
                        else
                        {
                            // Show the Error Model & Redirected back to worklist
                            console.log("Error Invalid Data Received");
                            sc09.displayModel(data);
                            $('#example1 :input').prop("disabled",true);
                            sc09.btnDisable();
                        }

                    }, function (errResponse) {
                        console.error('Error while login');

            }

            ////

            // var circleCode = $scope.sessionUser.circleCode;
            /* var row = {
                 'circleCode': $scope.sessionUser.circleCode,
                 'quarterEndDate': $scope.sessionUser.quarterEndDate,
                 'role': $scope.sessionUser.capacity
             };

             sc09Factory
                 .getSC09ValiadationData(row)
                 .then(
                     function (data) {

                         console.log(data);
                         // alert('data'+data);
                         var a, b, c;

                         if ($scope.sessionUser.capacity == '51' || $scope.sessionUser.capacity == '52') {
                             console.log("get validation data for sc09 with User Role " + $scope.sessionUser.capacity);
                             for (var keyName in data) {
                                 var key = keyName;
                                 var value = data[keyName];
                                 if (key == "[i] Bills Purchased and Discounted less bills rediscounted $") {
                                     // sc09.row.facility_Total_1=value;
                                     // sc09.row.facility_Total_Display1=value;
                                     sc09.row.facility_Total_Display1 = (sc09
                                         .parseFloat(value))
                                         .toFixed(2);
                                     a = value;
                                 }
                                 if (key == "[ii]Cash Credits, Overdrafts, Loans repayable on Demand and Recalled Assets $$") {
                                     // sc09.row.facility_Total_2=value;
                                     // sc09.row.facility_Total_Display2=value;
                                     sc09.row.facility_Total_Display2 = (sc09
                                         .parseFloat(value))
                                         .toFixed(2);
                                     b = value;
                                 }
                                 if (key == "[iii]  Term Loans , Agricultural Term Loans, FCNRB Term Loan") {
                                     // sc09.row.facility_Total_3=value;
                                     // sc09.row.facility_Total_Display3=value;
                                     sc09.row.facility_Total_Display3 = (sc09
                                         .parseFloat(value))
                                         .toFixed(2);
                                     c = value;

                                 }
                             }
                         }
                         else if($scope.sessionUser.capacity == '61' || $scope.sessionUser.capacity == '62') {
                             console.log("get validation data for sc09 with User Role  " + $scope.sessionUser.capacity);
                             for (var keyName in data) {
                                 var key = keyName;
                                 var value = data[keyName];
                                 if (key == "090101001051") {
                                     console.log("entered 090101001051 * "+(sc09
                                         .parseFloat(value))
                                         .toFixed(2));


                                     sc09.row.facility_Total_Display1 = (sc09
                                         .parseFloat(value))
                                         .toFixed(2);

                                     a = value;
                                 }
                                 if (key == "090101001052") {
                                     console.log("entered 090101001052 * "+(sc09
                                         .parseFloat(value))
                                         .toFixed(2));

                                     sc09.row.facility_Total_Display2 = (sc09
                                         .parseFloat(value))
                                         .toFixed(2);
                                     b = value;
                                 }
                                 if (key == "090101001053") {
                                     console.log("entered 090101001053 * "+(sc09
                                         .parseFloat(value))
                                         .toFixed(2));

                                     sc09.row.facility_Total_Display3 = (sc09
                                         .parseFloat(value))
                                         .toFixed(2);
                                     c = value;

                                 }
                             }
                         }


                         sc09.row.facility_Total_TotalDisplay = (sc09
                                 .parseFloat(a)
                             + sc09.parseFloat(b) + sc09
                                 .parseFloat(c)).toFixed(2);
                         // alert("a "+ a+"b "+ b+"c "+ c);
                         var row1 = ({
                             'FirstTotal': a,
                             'SecondTotal': b,
                             'ThirdTotal': c
                         });
                         sc09.row = angular.extend({}, sc09.row,
                             row1);

                         console.log("VV "+sc09.row.facility_Total_Display1+" # "+sc09.row.facility_Total_Display2+" # "+sc09.row.facility_Total_Display3);
                       //  alert("  # "+sc09.row.facility_Total_Display1+" # "+sc09.row.facility_Total_Display2+" # "+sc09.row.facility_Total_Display3);
                         console.log("get val data for sc09 total " + sc09.row.facility_Total_TotalDisplay + " Total 1 " + sc09.row.FirstTotal + " Total 2 " + sc09.row.SecondTotal + " Total 3 " + sc09.row.ThirdTotal);
                     }, function (errResponse) {
                         console.error('Error while login');
                     });*/

            //////////Start for providing View button to display submitted data at FRT level
            /* sc09.isData =false;
            sc09.viewSubmitData=function(v){

                console.log("################### "+sc09.checkView);
                if (sc09.checkView==false){
                    console.log("WE unchecked View****************************************");
                  //  $state.go('frt_maker.SC09');
                    $state.reload($state.current.name);
                }
                else{
                    var userInfo = {
                        'circleCode': $scope.sessionUser.circleCode,
                        'quarterEndDate': $scope.sessionUser.quarterEndDate,
                        'role': $scope.sessionUser.capacity,
                        'viewFlag':v
                    };
                    var row = {
                        'circleCode': $scope.sessionUser.circleCode,
                        'quarterEndDate': $scope.sessionUser.quarterEndDate,
                        'role': $scope.sessionUser.capacity
                    };
                    sc09Factory.getSC09ReportData(userInfo).then(
                        function (data) {
                            console.log(data);
                            // alert('data'+data);

                            // console.log("************************ check ** submitted data length "+data);
                            ///  if (data.length>0){
                            sc09.row = data;
                            sc09.isData =true;
                            sc09Factory
                                .getSC09ValiadationData(row)
                                .then(
                                    function (data) {
                                        console.log(data);
                                        var a, b, c;
                                        for (var keyName in data) {
                                            var key = keyName;
                                            var value = data[keyName];
                                            if (key == "090101001051") {
                                                sc09.row.facility_Total_Display1 = (sc09
                                                    .parseFloat(value))
                                                    .toFixed(2);
                                                a = value;
                                            }
                                            if (key == "090101001052") {
                                                sc09.row.facility_Total_Display2 = (sc09
                                                    .parseFloat(value))
                                                    .toFixed(2);
                                                b = value;
                                            }
                                            if (key == "090101001053") {
                                                sc09.row.facility_Total_Display3 = (sc09
                                                    .parseFloat(value))
                                                    .toFixed(2);
                                                c = value;
                                            }
                                        }
                                        sc09.row.facility_Total_TotalDisplay = (sc09
                                                .parseFloat(a)
                                            + sc09.parseFloat(b) + sc09
                                                .parseFloat(c)).toFixed(2);


                                    }, function (errResponse) {
                                        console.error('Error while login');
                                    });
                            ///  }

                        }, function (errResponse) {
                            console.error('Error while login');
                        });
                }


            }*/
            //////////END for providing View button to display submitted data at FRT level
            $scope
                .$watchCollection(
                    'sc09.row',
                    function (newValue, oldValue) {

                        if (sc09.row != undefined) {
                            sc09.row.facility_Standard_Total = (sc09
                                    .parseFloat(sc09.row.facility_Standard_1)
                                + sc09
                                    .parseFloat(sc09.row.facility_Standard_2) + sc09
                                    .parseFloat(sc09.row.facility_Standard_3))
                                .toFixed(2);
                            sc09.row.facility_SubStandard_Total = (sc09
                                    .parseFloat(sc09.row.facility_SubStandard_1)
                                + sc09
                                    .parseFloat(sc09.row.facility_SubStandard_2) + sc09
                                    .parseFloat(sc09.row.facility_SubStandard_3))
                                .toFixed(2);
                            sc09.row.facility_Doubtful_Total = (sc09
                                    .parseFloat(sc09.row.facility_Doubtful_1)
                                + sc09
                                    .parseFloat(sc09.row.facility_Doubtful_2) + sc09
                                    .parseFloat(sc09.row.facility_Doubtful_3))
                                .toFixed(2);
                            sc09.row.facility_Loss_Total = (sc09
                                    .parseFloat(sc09.row.facility_Loss_1)
                                + sc09
                                    .parseFloat(sc09.row.facility_Loss_2) + sc09
                                    .parseFloat(sc09.row.facility_Loss_3))
                                .toFixed(2);

                            sc09.row.facility_Adj_Total = (sc09
                                    .parseFloat(sc09.row.facility_Adj_1)
                                + sc09
                                    .parseFloat(sc09.row.facility_Adj_2) + sc09
                                    .parseFloat(sc09.row.facility_Adj_3))
                                .toFixed(2);


                            sc09.row.facility_Total_1 = (sc09
                                    .parseFloat(sc09.row.facility_Standard_1)
                                + sc09
                                    .parseFloat(sc09.row.facility_SubStandard_1)
                                + sc09
                                    .parseFloat(sc09.row.facility_Doubtful_1) + sc09
                                    .parseFloat(sc09.row.facility_Loss_1) + sc09.parseFloat(sc09.row.facility_Adj_1))
                                .toFixed(2);
                            sc09.row.facility_Total_2 = (sc09
                                    .parseFloat(sc09.row.facility_Standard_2)
                                + sc09
                                    .parseFloat(sc09.row.facility_SubStandard_2)
                                + sc09
                                    .parseFloat(sc09.row.facility_Doubtful_2) + sc09
                                    .parseFloat(sc09.row.facility_Loss_2) + sc09.parseFloat(sc09.row.facility_Adj_2))
                                .toFixed(2);
                            sc09.row.facility_Total_3 = (sc09
                                    .parseFloat(sc09.row.facility_Standard_3)
                                + sc09
                                    .parseFloat(sc09.row.facility_SubStandard_3)
                                + sc09
                                    .parseFloat(sc09.row.facility_Doubtful_3) + sc09
                                    .parseFloat(sc09.row.facility_Loss_3) + sc09.parseFloat(sc09.row.facility_Adj_3))
                                .toFixed(2);

                            sc09.row.facility_Total_Total = (sc09
                                    .parseFloat(sc09.row.facility_Total_1)
                                + sc09
                                    .parseFloat(sc09.row.facility_Total_2) + sc09
                                    .parseFloat(sc09.row.facility_Total_3))
                                .toFixed(2);


                            // ***** Additional Calculation FOR COLUMN
                            sc09.row.facility_Total_Display1_SUM=(
                                sc09.parseFloat(sc09.row.facility_Standard_1) +
                                sc09.parseFloat(sc09.row.facility_SubStandard_1) +
                                sc09.parseFloat(sc09.row.facility_Doubtful_1) +
                                sc09.parseFloat(sc09.row.facility_Loss_1));

                            sc09.row.facility_Total_Display2_SUM=(
                                sc09.parseFloat(sc09.row.facility_Standard_2) +
                                sc09.parseFloat(sc09.row.facility_SubStandard_2) +
                                sc09.parseFloat(sc09.row.facility_Doubtful_2) +
                                sc09.parseFloat(sc09.row.facility_Loss_2));

                            sc09.row.facility_Total_Display3_SUM=(
                                sc09.parseFloat(sc09.row.facility_Standard_3) +
                                sc09.parseFloat(sc09.row.facility_SubStandard_3) +
                                sc09.parseFloat(sc09.row.facility_Doubtful_3) +
                                sc09.parseFloat(sc09.row.facility_Loss_3));

                            sc09.row.facility_Total_TotalDisplay_SUM=(
                           sc09.parseFloat(sc09.row.facility_Total_Display1_SUM)+
                           sc09.parseFloat(sc09.row.facility_Total_Display2_SUM)+
                           sc09.parseFloat(sc09.row.facility_Total_Display3_SUM));



                            // ***** Additional Calculation FOR COLUMN

                            var abcd = document
                                .getElementById("facility_Standard_Total");
                            abcd.style.border = "0px solid white";
                            var def = document
                                .getElementById("security_Standard_Total");
                            def.style.border = "0px solid white";
                            var def = document
                                .getElementById("sector_Standard_ab_Total");
                            def.style.border = "0px solid white";

                            var abc = document
                                .getElementById("facility_SubStandard_Total");
                            abc.style.border = "0px solid white";
                            var def = document
                                .getElementById("security_SubStandard_Total");
                            def.style.border = "0px solid white";
                            var def = document
                                .getElementById("sector_SubStandard_ab_Total");
                            def.style.border = "0px solid white";

                            var abc = document
                                .getElementById("facility_Doubtful_Total");
                            abc.style.border = "0px solid white";
                            var def = document
                                .getElementById("security_Doubtful_Total");
                            def.style.border = "0px solid white";
                            var def = document
                                .getElementById("sector_Doubtful_ab_Total");
                            def.style.border = "0px solid white";

                            var abc = document
                                .getElementById("facility_Loss_Total");
                            abc.style.border = "0px solid white";
                            var def = document
                                .getElementById("security_Loss_Total");
                            def.style.border = "0px solid white";
                            var def = document
                                .getElementById("sector_Loss_ab_Total");
                            def.style.border = "0px solid white";


                            if (document.getElementById("facility_Adj_Total") != null) {
                                var abc = document
                                    .getElementById("facility_Adj_Total");
                                abc.style.border = "0px solid white";
                            }
                            if (document.getElementById("security_Adj_Total") != null) {
                                var def = document
                                    .getElementById("security_Adj_Total");
                                def.style.border = "0px solid white";
                            }
                            if (document.getElementById("sector_Adj_ab_Total") != null) {
                                var ghi = document
                                    .getElementById("sector_Adj_ab_Total");
                                ghi.style.border = "0px solid white";
                            }


                            var abc = document
                                .getElementById("facility_Total_TotalDisplay");
                            abc.style.border = "0px solid white";
                            var def = document
                                .getElementById("security_Total_Total");
                            def.style.border = "0px solid white";
                            var def = document
                                .getElementById("sector_ab_Total_ab_Total");
                            def.style.border = "0px solid white";

                            var abc = document
                                .getElementById("facility_Total_Display1");
                            abc.style.border = "0px solid white";
                            var abc = document
                                .getElementById("facility_Total_Display2");
                            abc.style.border = "0px solid white";
                            var abc = document
                                .getElementById("facility_Total_Display3");
                            abc.style.border = "0px solid white";
                            if (parseFloat(sc09.row.facility_Total_1) != parseFloat(sc09.row.facility_Total_Display1)) {
                                // var abc=
                                // document.getElementsByName(sc09.row.facility_Total_Display1);
                                var abc = document
                                    .getElementById("facility_Total_Display1");
                                abc.style.border = "1px solid red";

                            }

                            if (parseFloat(sc09.row.facility_Total_2) != parseFloat(sc09.row.facility_Total_Display2)) {
                                // var abc=
                                // document.getElementsByName(sc09.row.facility_Total_Display1);
                                var abc = document
                                    .getElementById("facility_Total_Display2");
                                abc.style.border = "1px solid red";

                            }

                            if (parseFloat(sc09.row.facility_Total_3) != parseFloat(sc09.row.facility_Total_Display3)) {
                                // var abc=
                                // document.getElementsByName(sc09.row.facility_Total_Display1);
                                var abc = document
                                    .getElementById("facility_Total_Display3");
                                abc.style.border = "1px solid red";

                            }

                            // ///security wise

                            sc09.row.security_Standard_Total = (sc09
                                    .parseFloat(sc09.row.security_Standard_1)
                                + sc09
                                    .parseFloat(sc09.row.security_Standard_2) + sc09
                                    .parseFloat(sc09.row.security_Standard_3))
                                .toFixed(2);
                            sc09.row.security_SubStandard_Total = (sc09
                                    .parseFloat(sc09.row.security_SubStandard_1)
                                + sc09
                                    .parseFloat(sc09.row.security_SubStandard_2) + sc09
                                    .parseFloat(sc09.row.security_SubStandard_3))
                                .toFixed(2);
                            sc09.row.security_Doubtful_Total = (sc09
                                    .parseFloat(sc09.row.security_Doubtful_1)
                                + sc09
                                    .parseFloat(sc09.row.security_Doubtful_2) + sc09
                                    .parseFloat(sc09.row.security_Doubtful_3))
                                .toFixed(2);
                            sc09.row.security_Loss_Total = (sc09
                                    .parseFloat(sc09.row.security_Loss_1)
                                + sc09
                                    .parseFloat(sc09.row.security_Loss_2) + sc09
                                    .parseFloat(sc09.row.security_Loss_3))
                                .toFixed(2);

                            sc09.row.security_Adj_Total = (sc09
                                    .parseFloat(sc09.row.security_Adj_1)
                                + sc09
                                    .parseFloat(sc09.row.security_Adj_2) + sc09
                                    .parseFloat(sc09.row.security_Adj_3))
                                .toFixed(2);
                            sc09.row.security_Total_1 = (sc09
                                    .parseFloat(sc09.row.security_Standard_1)
                                + sc09
                                    .parseFloat(sc09.row.security_SubStandard_1)
                                + sc09
                                    .parseFloat(sc09.row.security_Doubtful_1) + sc09
                                    .parseFloat(sc09.row.security_Loss_1) + sc09.parseFloat(sc09.row.security_Adj_1))
                                .toFixed(2);
                            sc09.row.security_Total_2 = (sc09
                                    .parseFloat(sc09.row.security_Standard_2)
                                + sc09
                                    .parseFloat(sc09.row.security_SubStandard_2)
                                + sc09
                                    .parseFloat(sc09.row.security_Doubtful_2) + sc09
                                    .parseFloat(sc09.row.security_Loss_2) + sc09.parseFloat(sc09.row.security_Adj_2))
                                .toFixed(2);
                            sc09.row.security_Total_3 = (sc09
                                    .parseFloat(sc09.row.security_Standard_3)
                                + sc09
                                    .parseFloat(sc09.row.security_SubStandard_3)
                                + sc09
                                    .parseFloat(sc09.row.security_Doubtful_3) + sc09
                                    .parseFloat(sc09.row.security_Loss_3) + sc09.parseFloat(sc09.row.security_Adj_3))
                                .toFixed(2);

                            sc09.row.security_Total_Total = (sc09
                                    .parseFloat(sc09.row.security_Total_1)
                                + sc09
                                    .parseFloat(sc09.row.security_Total_2) + sc09
                                    .parseFloat(sc09.row.security_Total_3))
                                .toFixed(2);

                            // //////////sector wise

                            // a

                            sc09.row.sector_Standard_a_Total = (sc09
                                    .parseFloat(sc09.row.sector_Standard_a1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Standard_a2)
                                + sc09
                                    .parseFloat(sc09.row.sector_Standard_a3) + sc09
                                    .parseFloat(sc09.row.sector_Standard_a4))
                                .toFixed(2);
                            sc09.row.sector_SubStandard_a_Total = (sc09
                                    .parseFloat(sc09.row.sector_SubStandard_a1)
                                + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_a2)
                                + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_a3) + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_a4))
                                .toFixed(2);
                            sc09.row.sector_Doubtful_a_Total = (sc09
                                    .parseFloat(sc09.row.sector_Doubtful_a1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_a2)
                                + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_a3) + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_a4))
                                .toFixed(2);
                            sc09.row.sector_Loss_a_Total = (sc09
                                    .parseFloat(sc09.row.sector_Loss_a1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Loss_a2)
                                + sc09
                                    .parseFloat(sc09.row.sector_Loss_a3) + sc09
                                    .parseFloat(sc09.row.sector_Loss_a4))
                                .toFixed(2);

                            sc09.row.sector_Adj_a_Total = (sc09
                                    .parseFloat(sc09.row.sector_Adj_a1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Adj_a2)
                                + sc09
                                    .parseFloat(sc09.row.sector_Adj_a3) + sc09
                                    .parseFloat(sc09.row.sector_Adj_a4))
                                .toFixed(2);

                            sc09.row.sector_Total_a1 = (sc09
                                    .parseFloat(sc09.row.sector_Standard_a1)
                                + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_a1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_a1) + sc09
                                    .parseFloat(sc09.row.sector_Loss_a1) + sc09.parseFloat(sc09.row.sector_Adj_a1))
                                .toFixed(2);
                            sc09.row.sector_Total_a2 = (sc09
                                    .parseFloat(sc09.row.sector_Standard_a2)
                                + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_a2)
                                + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_a2) + sc09
                                    .parseFloat(sc09.row.sector_Loss_a2) + sc09.parseFloat(sc09.row.sector_Adj_a2))
                                .toFixed(2);
                            sc09.row.sector_Total_a3 = (sc09
                                    .parseFloat(sc09.row.sector_Standard_a3)
                                + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_a3)
                                + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_a3) + sc09
                                    .parseFloat(sc09.row.sector_Loss_a3) + sc09.parseFloat(sc09.row.sector_Adj_a3))
                                .toFixed(2);
                            sc09.row.sector_Total_a4 = (sc09
                                    .parseFloat(sc09.row.sector_Standard_a4)
                                + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_a4)
                                + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_a4) + sc09
                                    .parseFloat(sc09.row.sector_Loss_a4) + sc09.parseFloat(sc09.row.sector_Adj_a4))
                                .toFixed(2);

                            sc09.row.sector_a_Total_a_Total = (sc09
                                    .parseFloat(sc09.row.sector_Total_a1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Total_a2)
                                + sc09
                                    .parseFloat(sc09.row.sector_Total_a3) + sc09
                                    .parseFloat(sc09.row.sector_Total_a4))
                                .toFixed(2);

                            // /b

                            sc09.row.sector_Standard_b_Total = (sc09
                                    .parseFloat(sc09.row.sector_Standard_b1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Standard_1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Standard_2) + sc09
                                    .parseFloat(sc09.row.sector_Standard_3))
                                .toFixed(2);
                            sc09.row.sector_SubStandard_b_Total = (sc09
                                    .parseFloat(sc09.row.sector_SubStandard_b1)
                                + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_1)
                                + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_2) + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_3))
                                .toFixed(2);
                            sc09.row.sector_Doubtful_b_Total = (sc09
                                    .parseFloat(sc09.row.sector_Doubtful_b1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_2) + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_3))
                                .toFixed(2);
                            sc09.row.sector_Loss_b_Total = (sc09
                                    .parseFloat(sc09.row.sector_Loss_b1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Loss_1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Loss_2) + sc09
                                    .parseFloat(sc09.row.sector_Loss_3))
                                .toFixed(2);

                            sc09.row.sector_Adj_b_Total = (sc09
                                    .parseFloat(sc09.row.sector_Adj_b1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Adj_1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Adj_2) + sc09
                                    .parseFloat(sc09.row.sector_Adj_3))
                                .toFixed(2);

                            sc09.row.sector_Total_b1 = (sc09
                                    .parseFloat(sc09.row.sector_Standard_b1)
                                + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_b1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_b1) + sc09
                                    .parseFloat(sc09.row.sector_Loss_b1) + sc09.parseFloat(sc09.row.sector_Adj_b1))
                                .toFixed(2);


                            sc09.row.sector_Total_1 = (sc09
                                    .parseFloat(sc09.row.sector_Standard_1)
                                + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_1) + sc09
                                    .parseFloat(sc09.row.sector_Loss_1) + sc09
                                    .parseFloat(sc09.row.sector_Adj_1))
                                .toFixed(2);
                            sc09.row.sector_Total_2 = (sc09
                                    .parseFloat(sc09.row.sector_Standard_2)
                                + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_2)
                                + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_2) + sc09
                                    .parseFloat(sc09.row.sector_Loss_2) + sc09
                                    .parseFloat(sc09.row.sector_Adj_2))
                                .toFixed(2);
                            sc09.row.sector_Total_3 = (sc09
                                    .parseFloat(sc09.row.sector_Standard_3)
                                + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_3)
                                + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_3) + sc09
                                    .parseFloat(sc09.row.sector_Loss_3) + sc09
                                    .parseFloat(sc09.row.sector_Adj_3))
                                .toFixed(2);

                            sc09.row.sector_b_Total_b_Total = (sc09
                                    .parseFloat(sc09.row.sector_Total_b1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Total_1)
                                + sc09
                                    .parseFloat(sc09.row.sector_Total_2) + sc09
                                    .parseFloat(sc09.row.sector_Total_3))
                                .toFixed(2);

                            sc09.row.sector_Standard_ab_Total = (sc09
                                .parseFloat(sc09.row.sector_Standard_a_Total) + sc09
                                .parseFloat(sc09.row.sector_Standard_b_Total))
                                .toFixed(2);
                            sc09.row.sector_SubStandard_ab_Total = (sc09
                                .parseFloat(sc09.row.sector_SubStandard_a_Total) + sc09
                                .parseFloat(sc09.row.sector_SubStandard_b_Total))
                                .toFixed(2);
                            sc09.row.sector_Doubtful_ab_Total = (sc09
                                .parseFloat(sc09.row.sector_Doubtful_a_Total) + sc09
                                .parseFloat(sc09.row.sector_Doubtful_b_Total))
                                .toFixed(2);
                            sc09.row.sector_Loss_ab_Total = (sc09
                                .parseFloat(sc09.row.sector_Loss_a_Total) + sc09
                                .parseFloat(sc09.row.sector_Loss_b_Total))
                                .toFixed(2);
                            sc09.row.sector_Adj_ab_Total = (sc09
                                .parseFloat(sc09.row.sector_Adj_a_Total) + sc09
                                .parseFloat(sc09.row.sector_Adj_b_Total))
                                .toFixed(2);

                            sc09.row.sector_ab_Total_ab_Total = (sc09
                                    .parseFloat(sc09.row.sector_Standard_ab_Total)
                                + sc09
                                    .parseFloat(sc09.row.sector_SubStandard_ab_Total)
                                + sc09
                                    .parseFloat(sc09.row.sector_Doubtful_ab_Total) + sc09
                                    .parseFloat(sc09.row.sector_Loss_ab_Total) + sc09
                                    .parseFloat(sc09.row.sector_Adj_ab_Total))
                                .toFixed(2);

                            function areEqual(arg1, arg2, arg3) {
                                return (parseFloat(arg1) == parseFloat(arg2)
                                    && parseFloat(arg2) == parseFloat(arg3) && parseFloat(arg3) == parseFloat(arg1));
                            }

                            var ap1 = areEqual(
                                sc09.row.facility_Standard_Total,
                                sc09.row.security_Standard_Total,
                                sc09.row.sector_Standard_ab_Total);
                            if (ap1 == false) {
                                var abc = document
                                    .getElementById("facility_Standard_Total");
                                abc.style.border = "1px solid red";
                                var def = document
                                    .getElementById("security_Standard_Total");
                                def.style.border = "1px solid red";
                                var def = document
                                    .getElementById("sector_Standard_ab_Total");
                                def.style.border = "1px solid red";
                            }
                            var ap2 = areEqual(
                                sc09.row.facility_SubStandard_Total,
                                sc09.row.security_SubStandard_Total,
                                sc09.row.sector_SubStandard_ab_Total);

                            if (ap2 == false) {
                                var abc = document
                                    .getElementById("facility_SubStandard_Total");
                                abc.style.border = "1px solid red";
                                var def = document
                                    .getElementById("security_SubStandard_Total");
                                def.style.border = "1px solid red";
                                var def = document
                                    .getElementById("sector_SubStandard_ab_Total");
                                def.style.border = "1px solid red";
                            }

                            var ap3 = areEqual(
                                sc09.row.facility_Doubtful_Total,
                                sc09.row.security_Doubtful_Total,
                                sc09.row.sector_Doubtful_ab_Total);

                            if (ap3 == false) {
                                var abc = document
                                    .getElementById("facility_Doubtful_Total");
                                abc.style.border = "1px solid red";
                                var def = document
                                    .getElementById("security_Doubtful_Total");
                                def.style.border = "1px solid red";
                                var def = document
                                    .getElementById("sector_Doubtful_ab_Total");
                                def.style.border = "1px solid red";
                            }

                            var ap4 = areEqual(
                                sc09.row.facility_Loss_Total,
                                sc09.row.security_Loss_Total,
                                sc09.row.sector_Loss_ab_Total);

                            if (ap4 == false) {
                                var abc = document
                                    .getElementById("facility_Loss_Total");
                                abc.style.border = "1px solid red";
                                var def = document
                                    .getElementById("security_Loss_Total");
                                def.style.border = "1px solid red";
                                var def = document
                                    .getElementById("sector_Loss_ab_Total");
                                def.style.border = "1px solid red";
                            }
                            var ap5 = areEqual(
                                sc09.row.facility_Total_TotalDisplay,
                                sc09.row.security_Total_Total,
                                sc09.row.sector_ab_Total_ab_Total);

                            if (ap5 == false) {
                                var abc = document
                                    .getElementById("facility_Total_TotalDisplay");
                                abc.style.border = "1px solid red";
                                var def = document
                                    .getElementById("security_Total_Total");
                                def.style.border = "1px solid red";
                                var def = document
                                    .getElementById("sector_ab_Total_ab_Total");
                                def.style.border = "1px solid red";
                            }

                            var ap6 = areEqual(
                                sc09.row.facility_Adj_Total,
                                sc09.row.security_Adj_Total,
                                sc09.row.sector_Adj_ab_Total);

                            if (ap6 == false) {
                                var abc = document
                                    .getElementById("facility_Adj_Total");
                                abc.style.border = "1px solid red";
                                var def = document
                                    .getElementById("security_Adj_Total");
                                def.style.border = "1px solid red";
                                var ghi = document
                                    .getElementById("sector_Adj_ab_Total");
                                ghi.style.border = "1px solid red";
                            }


                            /*
											 * ///total// var abc=
											 * document.getElementById("facility_Standard_Total");
											 * abc.style.border="0px solid
											 * white"; var abc=
											 * document.getElementById("security_Standard_Total");
											 * abc.style.border="0px solid
											 * white";
											 * if(parseFloat(sc09.row.facility_Standard_Total)!=parseFloat(sc09.row.security_Standard_Total)) {
											 * //var abc=
											 * document.getElementsByName(sc09.row.facility_Total_Display1);
											 * var abc=
											 * document.getElementById("facility_Standard_Total");
											 * abc.style.border="0px solid
											 * white"; var def=
											 * document.getElementById("security_Standard_Total");
											 * def.style.border="0px solid
											 * white"; }
											 */

                        }
                    });


            sc09.SubmitSC09Report = function (row) {

                var validateArray = [];

                console.log("inside sc09 submit ...");
                console.log("*****  For Saving data  ? " + sc09.row.save);   // true
                console.log("********** M O C pending ?" + $rootScope.reportObject.areMocPending);   //false
                //while saving ignoring validations
                if (sc09.row.save == false) {
                console.log("####### Inside if row.save == false")

                    if (parseFloat(sc09.row.FirstTotal) != parseFloat(sc09.row.facility_Total_1)) {
                        validateArray
                            .push('Total value Not Matching for [i] Bills Purchased and Discounted less bills rediscounted $');
                    }
                    if (parseFloat(sc09.row.SecondTotal) != parseFloat(sc09.row.facility_Total_2)) {
                        validateArray
                            .push('Total value Not Matching for [ii] Cash Credits, Overdrafts, Loans repayable on Demand and Recalled Assets $$');
                    }
                    if (parseFloat(sc09.row.ThirdTotal) != parseFloat(sc09.row.facility_Total_3)) {
                        validateArray
                            .push('Total value Not Matching for [iii] Term Loans , Agricultural Term Loans, FCNRB Term Loan');
                    }

                    function areEqual(arg1, arg2, arg3) {
                        return (parseFloat(arg1) == parseFloat(arg2)
                            && parseFloat(arg2) == parseFloat(arg3) && parseFloat(arg3) == parseFloat(arg1));
                    }

                    var ap1 = areEqual(sc09.row.facility_Standard_Total,
                        sc09.row.security_Standard_Total,
                        sc09.row.sector_Standard_ab_Total);
                    if (ap1 == false) {
                        validateArray
                            .push('Total values of A-1, A-2 and A-3 Differ for Standatd');
                    }

                    var ap2 = areEqual(sc09.row.facility_SubStandard_Total,
                        sc09.row.security_SubStandard_Total,
                        sc09.row.sector_SubStandard_ab_Total);

                    if (ap2 == false) {
                        validateArray
                            .push('Total values of A-1, A-2 and A-3 Differ for Sub-Standatd');
                    }

                    var ap3 = areEqual(sc09.row.facility_Doubtful_Total,
                        sc09.row.security_Doubtful_Total,
                        sc09.row.sector_Doubtful_ab_Total);

                    if (ap3 == false) {
                        validateArray
                            .push('Total values of A-1, A-2 and A-3 Differ for Doubtful');
                    }

                    var ap4 = areEqual(sc09.row.facility_Loss_Total,
                        sc09.row.security_Loss_Total,
                        sc09.row.sector_Loss_ab_Total);

                    if (ap4 == false) {
                        validateArray
                            .push('Total values of A-1, A-2 and A-3 Differ for Loss');
                    }

                    console.log("user role in SUBMIT sc09 is " + $scope.sessionUser.capacity);
                    if ($scope.sessionUser.capacity == '61') {
                        var ap6 = areEqual(sc09.row.facility_Adj_Total,
                            sc09.row.security_Adj_Total,
                            sc09.row.sector_Adj_ab_Total);
                        if (ap6 == false) {
                            validateArray
                                .push('Total values of A-1, A-2 and A-3 Differ for Adjustment');
                        }
                    }

                    console.log("******* SC 9 ******* number of validation errors " + validateArray.length);

                }

                $scope.validateArray = validateArray;

                if (validateArray.length == 0) {
                    console.log(row);
                    var i = {};
                    i = row;
                    console.log(i);
                    var circleCode = $scope.sessionUser.circleCode;
                    var quarterEndDate = $scope.sessionUser.quarterEndDate;
                    if ($scope.sessionUser.capacity == "61") {
                        var row1 = {
                            'circleCode': circleCode,
                            'quarterEndDate': quarterEndDate,
                            'userId': $scope.sessionUser.userId,
                            'role': $scope.sessionUser.capacity,
                            'reportName': $rootScope.reportObject.name,
                            'reportId': $rootScope.reportObject.reportId,
                            'reportMasterId': $rootScope.reportObject.reportMasterId,
                            'status': $rootScope.reportObject.status,
                            'areMocPending': $rootScope.reportObject.areMocPending
                        };
                    } else {

                        var row1 = {
                            'circleCode': circleCode,
                            'quarterEndDate': quarterEndDate,
                            'userId': $scope.sessionUser.userId,
                            'reportName': $rootScope.reportObject.name,
                            'reportId': $rootScope.reportObject.reportId,
                            'reportMasterId': $rootScope.reportObject.reportMasterId,
                            'status': $rootScope.reportObject.status,
                            'role': $scope.sessionUser.capacity,
                            'areMocPending': $rootScope.reportObject.areMocPending
                        };
                    }
                    var copy = angular.extend({}, i, row1);

                    if ($scope.sc09Form.$dirty == true && sc09.isCircleInList(circleCode))
                    {
                        alert("You cant Make changes here");
                        console.log("Cant Make Chnage HERERERERERERERERER");
                        sc09.redirect();
                    }

                    if ($scope.sc09Form.$dirty == true) {
                        sc09Factory
                            .SubmitSC09Report(copy)
                            .then(
                                function (data) {

                                    console.log("************** submitStatus ~ Sequence (reportId) ~ reportStatus : " + data);
                                    var result = [];
                                    result = data.split('~');


                                    if (result[0] == 0) {

                                        $rootScope.reportObject.reportId = result[1];
                                        $rootScope.reportObject.status = result[2];
                                        console.log("# reportId " + $rootScope.reportObject.reportId);
                                        console.log("# status " + $rootScope.reportObject.status);

                                        if (sc09.row.save == false) {
                                            sc09.displayMessage = "Report Submitted Successfully";
                                            if (sc09.displayMessage) {

                                                $('#myModal1')
                                                    .modal(
                                                        {
                                                            backdrop: 'static',
                                                            keyboard: false,
                                                            modal: true
                                                        });
                                                $('#myModal1')
                                                    .on(
                                                        'shown.bs.modal',
                                                        function () {
                                                            $(
                                                                '#myModal1')
                                                                .trigger(
                                                                    'focus');
                                                        });
                                            }
                                        }

                                        if (sc09.row.save == true) {
                                            if($scope.sc09Form.$dirty == true && sc09.isCircleInList(circleCode))
                                            {
                                                alert("Exising the form");
                                                sc09.redirect();
                                            }
                                            sc09.displayMessage = "Report Saved Successfully";

                                            if (sc09.displayMessage) {

                                                $('#myModal9')
                                                    .modal(
                                                        {
                                                            backdrop: 'static',
                                                            keyboard: false,
                                                            modal: true
                                                        });
                                                $('#myModal9')
                                                    .on(
                                                        'shown.bs.modal',
                                                        function () {
                                                            $(
                                                                '#myModal1')
                                                                .trigger(
                                                                    'focus');
                                                        });
                                            }

                                        }


                                    }

                                },
                                function (errResponse) {
                                    console
                                        .error('Error while submitting schedule 09');
                                });

                    }


                    if ($scope.sc09Form.$dirty == false) {

                        if (sc09.row.save == false) {
                            sc09.displayMessage111 = "Would you like to submit the report ?";
                        }
                        if (sc09.row.save == true) {
                            sc09.displayMessage111 = "Would you like to save the report ?";
                        }
                        if (sc09.displayMessage111) {

                            $('#myModal3').modal({
                                backdrop: 'static',
                                keyboard: false,
                                modal: true
                            });
                            $('#myModal3')
                                .on(
                                    'shown.bs.modal',
                                    function () {
                                        $('#myModal3').trigger(
                                            'focus');
                                    });
                        }

                    }

                    sc09.YesSubmitSc09 = function (row) {

                        var i = {};
                        i = row;
                        console.log(i);
                        var circleCode = $scope.sessionUser.circleCode;
                        var quarterEndDate = $scope.sessionUser.quarterEndDate;
                        if ($scope.sessionUser.capacity == "61") {
                            var row1 = {
                                'circleCode': circleCode,
                                'quarterEndDate': quarterEndDate,
                                'role': $scope.sessionUser.capacity,
                                'userId': $scope.sessionUser.userId,
                                'reportName': $rootScope.reportObject.name,
                                'reportId': $rootScope.reportObject.reportId,
                                'reportMasterId': $rootScope.reportObject.reportMasterId,
                                'status': $rootScope.reportObject.status,
                                'areMocPending': $rootScope.reportObject.areMocPending

                            };
                        } else {
                            var row1 = {
                                'circleCode': circleCode,
                                'role': $scope.sessionUser.capacity,
                                'quarterEndDate': quarterEndDate,
                                'userId': $scope.sessionUser.userId,
                                'reportName': $rootScope.reportObject.name,
                                'reportId': $rootScope.reportObject.reportId,
                                'reportMasterId': $rootScope.reportObject.reportMasterId,
                                'status': $rootScope.reportObject.status,
                                'role': $scope.sessionUser.capacity,
                                'areMocPending': $rootScope.reportObject.areMocPending

                            };
                        }
                        var copy = angular.extend({}, i, row1);

                        sc09Factory
                            .SubmitSC09Report(copy)
                            .then(
                                function (data) {

                                    console.log("************** submitStatus ~ Sequence (reportId) ~ reportStatus : " + data);
                                    var result = [];
                                    result = data.split('~');

                                    if (result[0] == 0) {
                                        $rootScope.reportObject.reportId = result[1];
                                        $rootScope.reportObject.status = result[2];

                                        console.log("reportId " + $rootScope.reportObject.reportId);
                                        console.log("status " + $rootScope.reportObject.status);

                                        if (sc09.row.save == false) {
                                            sc09.displayMessage = "Report Submitted Successfully";
                                            if (sc09.displayMessage) {

                                                $('#myModal1')
                                                    .modal(
                                                        {
                                                            backdrop: 'static',
                                                            keyboard: false,
                                                            modal: true
                                                        });
                                                $('#myModal1')
                                                    .on(
                                                        'shown.bs.modal',
                                                        function () {
                                                            $(
                                                                '#myModal1')
                                                                .trigger(
                                                                    'focus');
                                                        });
                                            }
                                        }
                                        if (sc09.row.save == true) {
                                            alert("calling here");
                                            sc09.displayMessage = "Report Saved Successfully";

                                            console.log("1111111111111111111111111111111");
                                            if (sc09.displayMessage) {
                                                console.log("22222222222222222222222222222222222");
                                                $('#myModal9')
                                                    .modal(
                                                        {
                                                            backdrop: 'static',
                                                            keyboard: false,
                                                            modal: true
                                                        });
                                                $('#myModal9')
                                                    .on(
                                                        'shown.bs.modal',
                                                        function () {
                                                            $(
                                                                '#myModal1')
                                                                .trigger(
                                                                    'focus');
                                                        });
                                            }

                                        }

                                    }
                                    console.log("3333333333333333333333");
                                    /*if (data == 0) {
                                        sc09.displayMessage = "Report Submitted Successfully";
                                        if (sc09.displayMessage) {

                                            $('#myModal1')
                                                .modal(
                                                    {
                                                        backdrop: 'static',
                                                        keyboard: false,
                                                        modal: true
                                                    });
                                            $('#myModal1')
                                                .on(
                                                    'shown.bs.modal',
                                                    function () {
                                                        $(
                                                            '#myModal1')
                                                            .trigger(
                                                                'focus');
                                                    });
                                        }
                                    }*/

                                },
                                function (errResponse) {
                                    console
                                        .error('Error while login');
                                });

                    }

                } else {
                    $('#myModal2').modal({
                        backdrop: 'static',
                        keyboard: false,
                        modal: true
                    });
                    $('#myModal2').on('shown.bs.modal', function () {
                        $('#myModal2').trigger('focus');
                    });
                }

            }

            sc09.parseFloat = function (value) {
                if (isNaN(value)) {
                    value = 0;
                }
                return parseFloat(value * 1);
            }

            // Re-direct user to Worklist
            sc09.redirect = function () {
                $timeout(function () {

                    if ($scope.sessionUser.capacity == "61") {
                        $state.go('frt_maker.worklist');
                    } else {
                        $state.go('circle_maker.worklist');
                    }
                }, 500);
            }



            // Display Error & Success Message
            sc09.displayModel=function(data)
            {
                if (data.status) {
                    console.log("Value displayModel Data is: "+data);
                    sc09.displayMessage = data.message;

                    $('#successSFTP')
                        .modal(
                            {
                                backdrop: 'static',
                                keyboard: false,
                                modal: true
                            });
                    $('#successSFTP')
                        .on('shown.bs.modal',
                            function () {
                                $('#successSFTP')
                                    .trigger('focus');
                            });
                }
                else {
                    sc09.displayMessage = data.message;
                    $('#failedSFTP')
                        .modal(
                            {
                                backdrop: 'static',
                                keyboard: false,
                                modal: true
                            });
                    $('#failedSFTP')
                        .on('shown.bs.modal',
                            function () {
                                $('#failedSaveSubmit')
                                    .trigger('focus');
                            });
                }
            }

        });
