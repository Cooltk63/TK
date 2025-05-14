
This is old angular.js code which i have given 
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


// For Downloading Excel & PDF (New react js code)
  const handleDownload = async () => {
    setFileNotFound(false);
    setErrorDownload(false);
    if (!selectedQed) return;

        //OG Payload
    /* let payload = {
                    'circleCode': circle,
                    'qed': selectedQed,
                };
                let params ={
                    'salt': salt,
                    'iv': iv,
                    'data' : aesUtil.encrypt(salt, iv, passphrase, JSON.stringify(payload)),
                } */

        // Sample Payload
    const payload = {
        "salt": "7a47fbbab8c7b37a412441c3427bfad7",
        "iv": "e54a9f072307cea44c42de6191dd8cad",
        "data": "/JFl/bV59121bMnZxZUr72PN32OcLIDpsihlG6tLrJ0ZJWrnCxZBorntJy8tktLy"
    };


    try {
      const response = await callApi(
        selectedCategory==='Reports' && downloadType === 'PDF' ? '/IFRSArchives/ArchiveReportsDownloadPDFUser' : '/IFRSArchives/ArchiveReportsDownloadXL',
        payload,'POST'
      );

      if (response.flag) {
        const fileName = `${circleCode}_IFRS_Liabilities_${selectedQed}`;
        const base64Data = response.pdfContent;

        if (downloadType === 'PDF') {
          const link = document.createElement('a');
          link.href = `data:application/pdf;base64,${base64Data}`;
          link.download = `${fileName}.pdf`;
          link.click();
        } else {
          const byteCharacters = atob(base64Data);
          const byteArray = new Uint8Array([...byteCharacters].map(c => c.charCodeAt(0)));
          const blob = new Blob([byteArray], {
            type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
          });
          const url = URL.createObjectURL(blob);
          const link = document.createElement('a');
          link.href = url;
          link.download = `${fileName}.xlsx`;
          link.click();
          URL.revokeObjectURL(url);
        }
      } else {
        if (response.displayMessage === 'fileNotFound') setFileNotFound(true);
        else if (response.displayMessage === 'error') setErrorDownload(true);
      }
    } catch (err) {
      alert(`Failed to download ${downloadType}: ${err.message}`);
    }
  };

Is this new react code given to me is similar to angular js code business logic I felt excel download missing or wont understanding anything give me proper code and recheck with angular js code without affetcing the business logic explain the logic in comment so better understand
