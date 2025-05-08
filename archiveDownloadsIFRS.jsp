<%--
  Created by IntelliJ IDEA.
  User: V1015698
  Date: 30-10-2024
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrapper">
    <div class="header header-filter" style="background-image: url('assets/img/bg2.jpeg');">
        <div class="container">
            <div class="row tim-row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="brand">
                        <h3 style="color: white;">Archive Reports</h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="main main-raised" style="margin: -160px 30px 0px;" ng-init="IFRSArchives.getCircleData();">
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 align-items-center d-flex justify-content-center">
                        <div class="col-md-5" style="text-align: center;margin: 30px">
                            <div class="input-group" style="width: 80%">
                                <label class="input-group-text" for="selectCategory">Select category type</label>
                                <select class="form-control form-select" id="selectCategory" ng-model="Category" ng-change="IFRSArchives.onChangeCategory()">
                                    <option selected value="reportwise">Reports</option>
                                    <option value="consolidation">Consolidation</option>
                                    <option value="collation">Collation</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12 align-items-center d-flex justify-content-center">
                        <div class="col-md-5" style="text-align: center;margin: 30px">
                            <div class="input-group" style="width: 80%">
                                <label class="input-group-text" for="selectQED">Select Quarter End Date</label>
                                <select class="form-control form-select" id="selectQED"></select>
                            </div>
                        </div>
                        <div class="col-md-5" style="text-align: center;margin: 30px" id="openCircle">
                            <div class="input-group" style="width: 80%">
                                <label class="input-group-text" for="selectCircle">Select Circle</label>
                                <select class="form-control form-select" id="selectCircle">
                                    <option data-ng-repeat="row in IFRSArchives.circleList track by $index" value="{{row.CIRCLECODE}}">{{row.CIRCLENAME}}</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container mt-3">
                <div class="row form-group">
                    <div class="col-md-12" style="margin-left: 30px">
                        <div class="col-md-1">
                            <label class="form-label" for="downloadType">
                                Download As :
                            </label>
                        </div>
                        <div id="downloadType">
                            <div class="form-check mr-3 col-md-5">
                                <input class="form-check-input" name="downloadAs" type="radio" value="PDF"
                                       id="pdf" checked>
                                <label for="pdf" style="margin-right: 15px;color: black">
                                    PDF
                                </label>
                                <input name="downloadAs" type="radio" value="EXCEL"
                                       id="excel">
                                <label class="form-check-label" for="excel" style="color: black">
                                    EXCEL
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="text-align: center;margin: 10px">
                <div class="col-md-12 align-items-center d-flex justify-content-center">
                    <div style="text-align: center" id="fileNotFound" hidden>
                        <strong style="color: red">There is no File/Data available for the selected options</strong>
                    </div>
                    <div style="text-align: center" id="errorDownload" hidden>
                        <strong style="color: red">Something went wrong please try again after sometime....</strong>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row align-items-center d-flex justify-content-center">
                    <div class="col-md-12" style="text-align: center">
                        <button class="btn btn-primary" ng-click="IFRSArchives.handleDownload();">
                            <i class="fa fa-download"></i>  Download</button>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>
