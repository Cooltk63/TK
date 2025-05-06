
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

       
