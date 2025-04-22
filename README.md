<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>SFTP Status</title>
    <!-- Link your local Bootstrap 3.3.6 CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
    
    <style>
        body {
            background-color: #f5f5f5;
            padding: 30px;
        }

        .panel-custom {
            border-radius: 10px;
            border: none;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }

        .panel-heading {
            background-color: #337ab7 !important;
            color: #fff !important;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            padding: 15px;
            font-size: 18px;
        }

        .table th {
            background-color: #eee;
        }

        .table td input {
            width: 100%;
            border: none;
            background: #f9f9f9;
            padding: 5px 10px;
            border-radius: 4px;
        }

        .table td input:focus {
            outline: none;
            background: #fff;
            box-shadow: 0 0 5px rgba(51, 122, 183, 0.5);
        }
    </style>
</head>
<body ng-app="myApp" ng-controller="SFTPStatusController as sftpstatus">

    <div class="container">
        <div class="panel panel-default panel-custom">
            <div class="panel-heading text-center">SFTP File Status</div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped text-center">
                        <thead>
                            <tr>
                                <th>Circle Name</th>
                                <th>File Name</th>
                                <th>Status</th>
                                <th>Remarks</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="row in sftpstatus.sftpDataList">
                                <td><input type="text" ng-model="row.circleName" readonly /></td>
                                <td><input type="text" ng-model="row.fileName" readonly /></td>
                                <td><input type="text" ng-model="row.status" readonly /></td>
                                <td><input type="text" ng-model="row.remarks" readonly /></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- AngularJS from local if available -->
    <script src="${pageContext.request.contextPath}/resources/js/angular.min.js"></script>
    <script>
        var app = angular.module('myApp', []);
        app.controller('SFTPStatusController', function () {
            var sftpstatus = this;

            // Sample data for demo
            sftpstatus.sftpDataList = [
                { circleName: 'North', fileName: 'north_file.csv', status: 'Success', remarks: 'Uploaded successfully' },
                { circleName: 'South', fileName: 'south_file.csv', status: 'Failed', remarks: 'Permission error' },
                { circleName: 'East', fileName: 'east_file.csv', status: 'Pending', remarks: 'Waiting on server' }
            ];
        });
    </script>
</body>
</html>