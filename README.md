const triggerDownload = (fileData, fileName, mimeType) => {
    const blob =
      fileData instanceof Blob
        ? fileData
        : new Blob([fileData], { type: mimeType || 'application/octet-stream' });
  
    const url = window.URL.createObjectURL(blob);
  
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', fileName || `file_${Date.now()}`);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  
    window.URL.revokeObjectURL(url);
  };

  for this function how to i use the below provided excel download

    // Handle Excel Download
  const handleExcelDownload = async (report) => {
    console.log('Inside handleDownload Excel');
    setSelectedRow(report);
    try {
      const payload = {
        report: report,
        user: loginUser,
        type: 'download',
        dash_suppresed: null,
        isSuppresed: false,
      };

      const responseData = await callApi('/Admin/viewReportJrxml', payload, 'POST', 'blob');

      if (responseData) {
        let fileName = circleName + '_' + qed + '_' + report.dash_name + '.excel';
        triggerDownload(responseData, fileName,'excel');
      } else {
        console.error('No response found');
      }
    } catch (error) {
      console.error('Error downloading Excel:', error.message);
    }
  };
