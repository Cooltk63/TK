const handleDownload = async () => {
  setFileNotFound(false);
  setErrorDownload(false);

  if (!selectedQed) return;

  try {
    let endpoint = '';
    let fileName = '';
    let payloadData = {};

    // ========================
    // Business Logic - Payload Setup
    // ========================

    if (selectedCategory === 'reportwise') {
      if (!selectedCircle) return;

      // --- HARDCODED payload for testing ---
      payloadData = {
        circleCode: "C001",
        qed: "31/03/2024"
      };

      // --- DYNAMIC way for production (commented for now) ---
      // payloadData = {
      //   circleCode: selectedCircle,
      //   qed: selectedQed
      // };

      fileName = `${selectedCircle}_IFRS_Liabilities_${selectedQed}`;
      endpoint = downloadType === 'PDF'
        ? '/IFRSArchives/ArchiveReportsDownloadPDFUser'
        : '/IFRSArchives/ArchiveReportsDownloadXL';
    } 
    else if (selectedCategory === 'consolidation' || selectedCategory === 'collation') {

      // --- HARDCODED payload for testing ---
      payloadData = {
        circleCode: "HO",
        qed: "31/03/2024",
        type: selectedCategory
      };

      // --- DYNAMIC way for production (commented for now) ---
      // payloadData = {
      //   circleCode: circleCode,
      //   qed: selectedQed,
      //   type: selectedCategory
      // };

      fileName = `IFRS_Liabilities_${selectedCategory}_${selectedQed}`;
      endpoint = downloadType === 'PDF'
        ? '/IFRSArchives/ArchiveDownloadPDFUser'
        : '/IFRSArchives/ArchiveDownloadXL';
    } 
    else {
      return; // Unknown category
    }

    // ========================
    // Payload Submission
    // ========================

    // --- HARDCODED request directly without encryption ---
    const response = await callApi(endpoint, payloadData, 'POST');

    // --- ENCRYPTED submission for production (commented) ---
    // const encryptedPayload = {
    //   salt: salt,
    //   iv: iv,
    //   data: aesUtil.encrypt(salt, iv, passphrase, JSON.stringify(payloadData)),
    // };
    // const response = await callApi(endpoint, encryptedPayload, 'POST');

    // ========================
    // File Handling
    // ========================
    if (response.flag) {
      const base64Data = response.pdfContent;

      if (downloadType === 'PDF') {
        const link = document.createElement('a');
        link.href = `data:application/pdf;base64,${base64Data}`;
        link.download = `${fileName}.pdf`;
        link.click();
      } else {
        const byteCharacters = atob(base64Data);
        const byteArray = new Uint8Array(
          Array.from(byteCharacters).map(char => char.charCodeAt(0))
        );
        const blob = new Blob([byteArray], {
          type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
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