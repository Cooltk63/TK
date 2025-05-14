this is the backend call we do from below provided function 
    let REST_SERVICE_URI_1 = './IFRSArchives/ArchiveReportsDownloadXL';  ::IFRSArchivesFactory.downloadXLReport(params)
    let REST_SERVICE_URI_2 = './IFRSArchives/ArchiveReportsDownloadPDF'; ::IFRSArchivesFactory.downloadPdfReport(params
    let REST_SERVICE_URI_3 = './IFRSArchives/ArchiveReportsDownloadPDFUser'; ::IFRSArchivesFactory.downloadPdf(params)
    let REST_SERVICE_URI_4 = './IFRSArchives/ArchiveReportsDownloadXLUser'; ::IFRSArchivesFactory.downloadXL(params)

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


I need the above provided angular js code into react js code without modifying the business logic and anything I have shared you my existing .jsx file where I have written code this function i wanted to call on Download button hit 

 {/* Download Button */}
        <Grid item xs={12} md={3}>
          <Button fullWidth variant="contained" color="primary" onClick={handleDownload}>
            Download {downloadType}
          </Button>
        </Grid>


So please map the above top provided backend api for the api calls which angular js code alredy written but make sure do not changes the business logic and do not touch any other part of .jsx code which everthing working fine just need this download pdf & excel functionality converted to react js code from angular js code.
