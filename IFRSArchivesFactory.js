import React, { useState } from 'react';
import axios from 'axios';

const DownloadComponent = () => {
  const [selectedCategory, setSelectedCategory] = useState("reportwise");
  const [downloadType, setDownloadType] = useState("PDF");
  const [selectedQed, setSelectedQed] = useState("31/03/2024"); // Example QED
  const [fileNotFound, setFileNotFound] = useState(false);
  const [errorDownload, setErrorDownload] = useState(false);
  const [circleList, setCircleList] = useState([]);
  const sessionUser = { userId: "user1", circleCode: "CIRC01" }; // Example session data

  // API Call Function (Your API call)
  const callApi = async (url, payload, method) => {
    try {
      const response = await axios({
        method: method,
        url: url,
        data: payload
      });
      return response.data;
    } catch (error) {
      console.error('API Call Error: ', error);
    }
  };

  // Handle Download PDF or Excel based on Category and Download Type
  const handleDownload = async () => {
    setFileNotFound(false);
    setErrorDownload(false);

    if (!selectedQed) return;

    const payload = {
      "salt": "7a47fbbab8c7b37a412441c3427bfad7",
      "iv": "e54a9f072307cea44c42de6191dd8cad",
      "data": "/JFl/bV59121bMnZxZUr72PN32OcLIDpsihlG6tLrJ0ZJWrnCxZBorntJy8tktLy"
    };

    try {
      const response = await callApi(
        selectedCategory === 'reportwise' && downloadType === 'PDF' 
          ? '/IFRSArchives/ArchiveReportsDownloadPDFUser' 
          : '/IFRSArchives/ArchiveReportsDownloadXL', 
        payload, 'POST'
      );

      if (response.flag) {
        const fileName = `${sessionUser.circleCode}_IFRS_Liabilities_${selectedQed}`;
        const base64Data = response.pdfContent;

        if (downloadType === 'PDF') {
          // PDF Download logic
          const link = document.createElement('a');
          link.href = `data:application/pdf;base64,${base64Data}`;
          link.download = `${fileName}.pdf`;
          link.click();
        } else {
          // Excel Download logic
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

  // Render the Component UI
  return (
    <div>
      <h3>Download IFRS Reports</h3>

      {/* Dropdowns for Category, QED, Circle */}
      <div>
        <select value={selectedCategory} onChange={(e) => setSelectedCategory(e.target.value)}>
          <option value="reportwise">Report Wise</option>
          <option value="consolidation">Consolidation</option>
          <option value="collation">Collation</option>
        </select>

        <select value={selectedQed} onChange={(e) => setSelectedQed(e.target.value)}>
          <option value="31/03/2024">31/03/2024</option>
          {/* More options can be added here */}
        </select>

        <select onChange={(e) => setSelectedCircle(e.target.value)}>
          {circleList.map(circle => (
            <option key={circle.CIRCLECODE} value={circle.CIRCLECODE}>
              {circle.CIRCLENAME}
            </option>
          ))}
        </select>

        {/* Radio buttons for PDF/Excel selection */}
        <div>
          <input 
            type="radio" 
            value="PDF" 
            checked={downloadType === 'PDF'}
            onChange={() => setDownloadType('PDF')} 
          />
          <label>PDF</label>
          <input 
            type="radio" 
            value="EXCEL" 
            checked={downloadType === 'EXCEL'}
            onChange={() => setDownloadType('EXCEL')} 
          />
          <label>Excel</label>
        </div>

        {/* Download Button */}
        <button onClick={handleDownload}>Download</button>
      </div>

      {/* Alerts for error handling */}
      {fileNotFound && <p>File not found!</p>}
      {errorDownload && <p>Error during download!</p>}
    </div>
  );
};

export default DownloadComponent;