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
                       style="width:100%; border-radius: 20px;overflow: auto">
                    <thead style="background-color:#34495E; font-weight:600 ; color:white;border-radius: 10px;">

                    <tr>
                        <td style="text-align: center; vertical-align: middle; width: auto">Circle Name</td>
                        <td style="text-align: center; vertical-align: middle; width: auto">Sch-10 report Status</td>
                        <td style="text-align: center; vertical-align: middle; width: auto">File received status</td>
                        <td style="text-align: center; vertical-align: middle; width: auto">File recd. date-time</td>
                        <td style="text-align: center; vertical-align: middle; width: auto">Whether revised file received</td>
                        <td style="text-align: center; vertical-align: middle; width: auto">Error, if any</td>

                    </tr>

                    </thead>
                    <tbody>


                    <tr>

                        <td>
                            <input type="text" ng-model="sftpstatus.row.circleName"
                                   style="text-align:right; width: auto" maxlength="5"
                                   class="form-control"/>
                        </td>

                        <td>
                            <input type="text" ng-model="sftpstatus.row.reportStatus"
                                   style="text-align:right; width: auto" maxlength="5"
                                   class="form-control"/>
                        </td>
                        <td>
                            <input type="text" ng-model="sftpstatus.row.fileReceivedStatus"
                                   style="text-align:right; width: auto" maxlength="5"
                                   class="form-control"/>
                        </td>

                        <td>
                            <input type="text" ng-model="sftpstatus.row.timeStamp"
                                   style="text-align:right; width: auto" maxlength="5"
                                   class="form-control"/>
                        </td>


                        <td>
                            <input type="text" ng-model="sftpstatus.row.revisedFileStatus"
                                   style="text-align:right; width: auto" maxlength="5"
                                   class="form-control"/>
                        </td>
                        <td>
                            <textarea  rows="4" cols="50" wrap="soft" ng-model="sftpstatus.row.message"
                                   style="text-align:right; width: auto" maxlength="5"
                                   class="form-control"/>
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
