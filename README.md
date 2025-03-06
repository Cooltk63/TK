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
    <tr>
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
            <textarea rows="2" ng-model="sftpstatus.row.message"
                      class="form-control text-center"
                      style="width: 100%; resize: none; word-wrap: break-word; white-space: pre-line;" readonly></textarea>
        </td>
    </tr>
    </tbody>
</table>