
This is my .jsp file for render
<!-- <script>
$(".select2").select2();
</script> -->

<div class="wrapper">

    <div class="header header-filter" style="background-image: url('assets/img/bg2.jpeg');">
        <div class="container">
            <div class="row tim-row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="brand">
                        <h3 style="color: white;">Reports</h3>
                    </div>
                </div>
            </div>

        </div>

    </div>


    <div class="main main-raised">
        <div style=" position:  absolute;    width: 100%;" ng-if="downloadJrxmls.checkcircle == true"
             class="alert alert-warning">
            <div class="container-fluid">
                <div class="alert-icon">
                    <i class="fa fa-warning"></i>
                </div>
                <button type="button" class="close" ng-click="downloadJrxmls.clearcheckcircle();" data-dismiss="alert"
                        aria-label="Close">
                    <span aria-hidden="true"><i class="fa fa-close"></i></span>
                </button>
                <b>{{downloadJrxmls.checkcircleMessage}}</b>
            </div>
        </div>


        <div class="section">

            <div class="container">


                <div class="row">
                    <p>
                    <h3><b>{{downloadJrxmls.message}}</b></h3></p>
                    <div data-ng-if="downloadJrxmls.showAltMesge == true">
                        <div class="checkbox"><span class="text-danger"
                                                    style="font-size: large">Please select Checkbox, <%--</span>
                            <label>
                                <input type="checkbox" name="optionsCheckboxes" disabled="" checked=""><span
                                    class="checkbox-material"><span class="check"></span></span>

                            </label><span class="text-danger" style="font-size: large">--%>provided against the report for getting zero suppressed report.</span>
                        </div>
                    </div>
                    <br>


                    <div class="col-md-12" ng-if="downloadJrxmls.showDiv == true">


                        <form data-ng-submit="" ng-init="">
                            <input type="hidden" id="csrfPreventionSalt" value="${csrfPreventionSalt}"/>

                            <table id="example1" class="table table-hover">
                                <thead>


                                <tr ng-if="heading!='3' && heading!='5'">
                                    <th>Report Name</th>
                                    <th>View</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th ng-if="heading=='2'"></th>
                                    <th ng-if="heading=='2'">Circle/Branch</th>
                                    <th>PRE/POST</th>
                                    <th ng-if="heading=='2'">CompCode</th>
                                    <th ng-if="heading=='1'">Circles</th>

                                </tr>


                                <tr data-ng-if="heading=='3'">
                                    <th>Report Name</th>
                                    <th>View</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th>PRE/POST</th>

                                </tr>

                                <tr data-ng-if="heading=='5'">
                                    <th>Report Name</th>
                                    <th>View</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>

                                </tr>


                                </thead>
                                <tbody style="font-weight: 400;">


                                <tr data-ng-repeat="row in downloadJrxmls.listOfRows">


                                    <td style="text-align: inherit;">{{row.dash_name}}</td>

                                    <td width="4%">
                                        <input type="hidden"
                                               name="dash_suppresed"
                                               value="{{row.dash_suppresed}}"
                                        />
                                        <div class="checkbox" ng-show="row.dash_suppresed == 'Z'">
                                            <label> <input type="checkbox"
                                                           name="isSelected{{key}}" id="{{$index}}"
                                                           ng-model="isSuppresed" value="{{key}}"
                                            /><span
                                                    class="checkbox-material"><span class="check"></span></span>
                                                <br>
                                            </label>
                                        </div>


                                    </td>

                                    <td ng-if="heading=='2'" width="4%">
                                        <button class="btn-default"
                                                ng-show="row.dash_dwnload.contains('H')"  ng-click="downloadJrxmls.viewReportJrxmlCircle($index,'view', row.type,row.circle,row.branchCode,row.dash_suppresed,row.isSuppresed,row.dash_param)">
                                            <i class="fa fa-binoculars"></i>

                                        </button>
                                    </td>
                                    <td ng-if="heading=='2'" width="4%">
                                        <button class="btn-danger"
                                                ng-show="row.dash_dwnload.contains('P')"
                                                ng-click="downloadJrxmls.viewReportJrxmlCircle($index,'downloadPDF',row.type,row.circle,row.branchCode,row.dash_suppresed,row.isSuppresed,row.dash_param)">
                                            <i class="fa fa-file-pdf-o"></i></button>
                                    </td>

                                    <td ng-if="heading=='2'" width="4%">
                                        <button class="btn-success" ng-show="row.dash_dwnload.contains('E')"
                                                ng-click="downloadJrxmls.viewReportJrxmlCircle($index,'download',row.type,row.circle,row.branchCode,row.dash_suppresed,row.isSuppresed,row.dash_param)">
                                            <i class="fa fa-file-excel-o"></i>
                                        </button>
                                    </td>

                                    <td ng-if="heading=='4'" width="4%">
                                        <button class="btn-default"
                                                ng-show="row.dash_dwnload.contains('H')"  ng-click="downloadJrxmls.viewReportJrxmlCircle($index,'view', row.type,row.circle,row.branchCode,row.dash_suppresed,row.isSuppresed,row.dash_param)">
                                            <i class="fa fa-binoculars"></i>

                                        </button>
                                    </td>
                                    <td ng-if="heading=='4'" width="4%">
                                        <button class="btn-danger"
                                                ng-show="row.dash_dwnload.contains('P')"
                                                ng-click="downloadJrxmls.viewReportJrxmlCircle($index,'downloadPDF',row.type,row.circle,row.branchCode,row.dash_suppresed,row.isSuppresed,row.dash_param)">
                                            <i class="fa fa-file-pdf-o"></i></button>
                                    </td>

                                    <td ng-if="heading=='4'" width="4%">
                                        <button class="btn-success" ng-show="row.dash_dwnload.contains('E')"
                                                ng-click="downloadJrxmls.viewReportJrxmlCircle($index,'download',row.type,row.circle,row.branchCode,row.dash_suppresed,row.isSuppresed,row.dash_param)">
                                            <i class="fa fa-file-excel-o"></i>
                                        </button>
                                    </td>



                                    <td ng-if="heading=='2'" width="4%">
                                        <button class="btn-success"
                                                ng-show="row.dash_dwnload.contains('T') || row.dash_dwnload.contains('C')"
                                                ng-click="downloadJrxmls.checkCsv($index,'downloadCSV'+row.dash_dwnload,row.type,row.circle,row.branchCode,row.dash_suppresed,row.isSuppresed,row.dash_param)">
                                            <i class="fa fa-file-text-o"></i>
                                        </button>
                                    </td>


                                    <td ng-if="heading=='2'">
											<span ng-show="row.dash_param.indexOf('BRANCH_CODE')!= '-1'">
											
											<select
                                                    name="branchCode" ng-model="row.branchCode"
                                                    class="form-control" ng-required="true"
                                                    ng-options="branch.branchCode as branch.branchCode for branch in downloadJrxmls.branchList"
                                                    ng-init="row.branchCode=selectedBranchCode"
                                                    required>
											</select>

											</span>
                                    </td>


                                    <td ng-if="heading=='2'">
											<span ng-show="row.dash_param.indexOf('TYPE')!= '-1'">
											<select class="form-control"ng-init="row.type='PRE'" name="type" ng-model="row.type">
                                                <option value="PRE">PRE</option>
                                                <option value="POST">POST</option>
                                               <option ng-show="row.dash_param.indexOf('TYPESE') != '-1' " value="INTERSE">INTERSE</option>
											</select>
											</span>
                                    </td>


                                    <td ng-if="heading=='2'">
											
											<span ng-show="row.dash_param.indexOf('COMP')!= '-1'">
											
											<select ng-model="row.circle" class="" style="width: 100%"
                                                    multiple="multiple"
                                                    ng-options="circle.circleCode as circle.circleName for circle in downloadJrxmls.listOfCircle "></select>
											</span>
                                        <!-- <script type="text/javascript">
                                        $(".select2").select2();
                                        </script>  -->
                                    </td>
                                    <td ng-if="heading=='2'">

                                    </td>


                                   <!--for heading 2 -->

                                    <td ng-if="heading=='1'" width="4%">
                                        <button class="btn-default"
                                                ng-show="row.dash_dwnload.contains('H')"
                                                ng-click="downloadJrxmls.viewReportJrxml($index,'view',row.type,row.circle,row.dash_suppresed,row.isSuppresed,row.dash_param)">
                                            <i class="fa fa-binoculars"></i>

                                        </button>
                                    </td>
                                    <td ng-if="heading=='1'" width="4%">
                                        <button class="btn-danger"
                                                ng-show="row.dash_dwnload.contains('P')"
                                                ng-click="downloadJrxmls.viewReportJrxml($index,'downloadPDF',row.type,row.circle,row.dash_suppresed,row.isSuppresed,row.dash_param)">
                                            <i class="fa fa-file-pdf-o"></i></button>
                                    </td>
                                    <td ng-if="heading=='1'" width="4%">
                                        <button class="btn-success" ng-show=" row.dash_dwnload.contains('E')"
                                                ng-click="downloadJrxmls.viewReportJrxml($index,'download',row.type,row.circle,row.dash_suppresed,row.isSuppresed,row.dash_param)">
                                            <i class="fa fa-file-excel-o"></i>
                                        </button>
                                    </td>

                                    <!--for heading 5 -->
                                    <td ng-if="heading=='5'" width="4%">
                                        <button class="btn-default"
                                                ng-show="row.dash_dwnload.contains('H')"
                                                ng-click="downloadJrxmls.viewReportJrxml($index,'view',row.type,row.circle,row.dash_suppresed,row.isSuppresed,row.dash_param)">
                                            <i class="fa fa-binoculars"></i>

                                        </button>
                                    </td>
                                    <td ng-if="heading=='5'" width="4%">
                                        <button class="btn-danger"
                                                ng-show="row.dash_dwnload.contains('P')"
                                                ng-click="downloadJrxmls.viewReportJrxml($index,'downloadPDF',row.type,row.circle,row.dash_suppresed,row.isSuppresed,row.dash_param)">
                                            <i class="fa fa-file-pdf-o"></i></button>
                                    </td>
                                    <td ng-if="heading=='5'" width="4%">
                                        <button class="btn-success" ng-show=" row.dash_dwnload.contains('E')"
                                                ng-click="downloadJrxmls.viewReportJrxml($index,'download',row.type,row.circle,row.dash_suppresed,row.isSuppresed,row.dash_param)">
                                            <i class="fa fa-file-excel-o"></i>
                                        </button>
                                    </td>




                                    <td ng-if="heading=='1'" width="20%">
											<span ng-show="row.dash_param.indexOf('TYPE')!= '-1'">
                                                <select class="form-control" name="type" ng-init="row.type='PRE'"ng-model="row.type">
                                                    <option value="PRE">PRE</option><option value="POST">POST</option>
                                                   <option ng-show="row.dash_param.indexOf('TYPESE') != '-1' " value="INTERSE">INTERSE</option>
                                                </select>
											</span>
                                    </td>

                                    <td ng-if="heading=='1'">
											
											<span ng-if="row.dash_param.indexOf('CIRCLE_LIST')!= '-1'">
											
											<select requried="requried" ng-model="row.circle" class=""
                                                    style="width: 100%" multiple="multiple"
                                                    ng-options="circle.circleCode as circle.circleName for circle in downloadJrxmls.FreezedCircles ">
											<!-- <option value="">--Select Circle--</option> -->
											</select>	
											</span>


                                        <span ng-if="row.dash_param.indexOf('COMP')!= '-1'">
											
											<select requried="requried" ng-model="row.circle" class=""
                                                    style="width: 100%"
                                                    ng-options="circle.circleCode as circle.circleName for circle in downloadJrxmls.listOfCircle ">
										 <option value="">--Select Circle--</option> 
											</select>	
											</span>
                                        <!-- <script type="text/javascript">
                                        $(".select2").select2();
                                        </script>  -->
                                    </td>
                                    <td ng-if="heading=='1'">

                                    </td>


                                </tr>

                                </tbody>
                            </table>

                            <div ng-show="downloadJrxmls.pdfContent">

                                <embed id="visualizador" width="100%" height="500px" type="text/html"
                                       style="overflow: auto;" ng-src="{{downloadJrxmls.pdfContent}}"></embed>
                            </div>
                        </form>


                    </div>
                </div>
            </div>
        </div>
    </div>


</div>


<div class="modal fade" id="myModal999" role="dialog" style="z-index : 1400">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #E74C3C" ;>
                <!--<button type="button" class="close" data-dismiss="modal">�</button> -->
                <h4 class="modal-title" style="color: white;">Attention!</h4>
            </div>
            <div class="modal-body" id="popup">
                This file will contain only MOC posted and accepted by circle/Unit for current quarter in BSA. Please
                verify branch wise CR and DR is matching as per Finance Two (F2) before uploading csv into Finance Two
                (F2).
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-success" data-dismiss="modal"
                        ng-click="downloadJrxmls.viewReportJrxmlCircle(downloadJrxmls.ind,downloadJrxmls.act,downloadJrxmls.prep,downloadJrxmls.comc,downloadJrxmls.brc,downloadJrxmls.dashSupp,downloadJrxmls.isSupp,downloadJrxmls.dParam)">
                    Continue
                </button>
            </div>


        </div>
    </div>


</div>


This is my angularjs controller

app
    .controller('downloadJrxmls2Controller', function ($scope, $log, $rootScope, $sce, $http, $window, $sessionStorage, $state, $location, $filter, downloadJrxmls2Factory, circleMakerWorklist, Idle, Keepalive, $modal, ModalService, userFactory, AES256) {
        var downloadJrxmls2 = this;
        $scope.started = false;
        downloadJrxmls2.showDiv = false;
        downloadJrxmls2.showAltMesge = false;
        $scope.sessionUser = JSON.parse(AES256.decrypt($rootScope.globals.currentUser));
        $scope.heading = '';
        console.log("capacity---" + $scope.sessionUser.capacity);

        /* if ($scope.sessionUser.capacity == '61'
                 || $scope.sessionUser.capacity == '62') {
                 $scope.heading = '1';
                 console.log("insid 61 or  62");
             } else if ($scope.sessionUser.capacity == '52'
                 || $scope.sessionUser.capacity == '51'
                 || $scope.sessionUser.capacity == '53') {
                 console.log("insid 51 or 52");
                 $scope.heading = '2';
             }else*/
        if ($scope.sessionUser.capacity == '61') {
            console.log("insid 81");
            $scope.heading = '4';
        } else {
            $scope.heading = '3';
            console.log("insid others");
        }
        // /////////////////////////16052018
        downloadJrxmls2Factory.getBrList($scope.sessionUser).then(function (data) {

            console.log("data" + data + ' length: ' + data.length);

            downloadJrxmls2.branchList = data;

        }, function (errResponse) {
            console.error('Error while getting branchList');
        });
        // ////////////end 16052018


        downloadJrxmls2.listZ = [];
        downloadJrxmls2Factory
            .getListOfReports2($scope.sessionUser)
            .then(function (data) {
                var listWithZ = [];
                console.log(data.list1);

                if (!data.list1.length == '0' || !data.list1.length == null) {

                    downloadJrxmls2.showDiv = true;
                    // downloadJrxmls2.listOfRows = data;
                    downloadJrxmls2.listOfRows = data.list1;


                    downloadJrxmls2.listOfCircle = data.list;


                    ////////22102018Start

                    listWithZ = $filter("filter")(downloadJrxmls2.listOfRows, {
                        dash_suppresed: 'Z'

                    });
                    if (listWithZ.length > 0) {
                        downloadJrxmls2.showAltMesge = true;
                    }


                    /*for (i=0;i<listWithZ.length;i++) {
                                console.log("report with supressed flag  "+JSON.stringify(listWithZ[i].dash_suppresed)+"  * Name * "+JSON.stringify(listWithZ[i].dash_name));
                            }*/

                    console.log("number of reports with dash suppresed as Z * " + listWithZ.length);
                    //////22102018End

                    //21082018
                    downloadJrxmls2.FreezedCircles = data.freezedCircles;
                    //	console.log("******************* freezed Circles  "+downloadJrxmls2.FreezedCircles.length+"  *  "+downloadJrxmls2.FreezedCircles);


                    $scope.selectedBranchCode = 'All Branches';

                    $scope.type = 'PRE';

                } else {
                    downloadJrxmls2.message = 'No reports are found to download.';
                }

                // downloadJrxmls2.listOfRows = data;
                // $scope.type = 'PRE';
            }, function (errResponse) {

            });


        downloadJrxmls2.ind = '';
        downloadJrxmls2.act = '';
        downloadJrxmls2.prep = '';
        downloadJrxmls2.comc = '';
        downloadJrxmls2.brc = '';
        downloadJrxmls2.dashSupp = '';
        downloadJrxmls2.isSupp = '';
        downloadJrxmls2.dParam = '';
        downloadJrxmls2.checkCsv = function (index, action, prePost, compcircle, branchCode, dash_suppresed, isSuppresed, params) {


            downloadJrxmls2.ind = index;
            downloadJrxmls2.act = action;
            downloadJrxmls2.prep = prePost;
            downloadJrxmls2.comc = compcircle;
            downloadJrxmls2.brc = branchCode;
            downloadJrxmls2.dashSupp = dash_suppresed;
            downloadJrxmls2.isSupp = isSuppresed;
            downloadJrxmls2.dParam = params;
            $('#myModal999').modal({
                backdrop: 'static', keyboard: false, modal: true
            });
            $('#myModal999').on('shown.bs.modal', function () {
                $('#myModal999').trigger('focus');
            });


        }


        downloadJrxmls2.viewReportJrxmlCircle = function (index, action, prePost, compcircle, branchCode, dash_suppresed, isSuppresed, params) {
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
            downloadJrxmls2.pdfContent = '';
            console.log(action + "--" + compcircle);
            var report = downloadJrxmls2.listOfRows[index];
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

            downloadJrxmls2Factory
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
                            downloadJrxmls2.pdfContent = $sce
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
        downloadJrxmls2.clearcheckcircle = function () {
            downloadJrxmls2.checkcircle = false;
            downloadJrxmls2.checkcircleMessage = "";
            return false;
        }
        downloadJrxmls2.viewReportJrxml = function (index, action, prePost, compcircle, dash_suppresed, isSuppresed, params) {
            console.log("****************At FRT Level******* dash_param * " + params);
            var prePostValue = "";
            //eliminating PRE or POST from file name if it do not contain TYPE drop down
            if (params.indexOf('TYPE') != '-1') {
                prePostValue = "_" + prePost;
            }

            console.log("***************** prePostValue * " + prePostValue);

            console.log("**********  At FRT Level **** index " + index + "  *   action " + action + "  *   prePost " + prePost + "  *  circle " + compcircle + "  *   dash_suppresed " + dash_suppresed);
            downloadJrxmls2.checkcircle = false;
            downloadJrxmls2.pdfContent = '';
            console.log(action + "--" + compcircle);
            var report = downloadJrxmls2.listOfRows[index];
            console.log("***************$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  FreezedCircles length " + downloadJrxmls2.FreezedCircles.length);
            // console.log("@@@@@@@@@@@@@@@@@@
            // "+JSON.stringify(report));
            // console.log("-------------
            // "+report.dash_param.indexOf('COMP'));
            // console.log("------------ "+compcircle);
            if (compcircle == undefined && report.dash_param.indexOf('COMP') != '-1') {

                downloadJrxmls2.checkcircleMessage = "Please select circle";
                downloadJrxmls2.checkcircle = true;
                return false;
            }
            /*if (compcircle == undefined
								&& report.dash_param.indexOf('CIRCLE_LIST') != '-1') {
							downloadJrxmls2.checkcircle = true;

							return false;
						}*/
            if (compcircle == undefined && report.dash_param.indexOf('CIRCLE_LIST') != '-1' && downloadJrxmls2.FreezedCircles.length > 0) {
                downloadJrxmls2.checkcircleMessage = "Please select circle";
                downloadJrxmls2.checkcircle = true;

                return false;
            } else if (downloadJrxmls2.FreezedCircles.length == 0 && report.dash_param.indexOf('CIRCLE_LIST') != '-1') {

                downloadJrxmls2.checkcircleMessage = "No circle has been freezed to generate Consolidation Report";
                downloadJrxmls2.checkcircle = true;
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

            downloadJrxmls2Factory
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
                            downloadJrxmls2.pdfContent = $sce
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

