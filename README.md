import React from 'react';
import axios from 'axios';

const REST_URIS = {
  getCircleList: './IFRSArchives/GetCircleList',
  downloadXLReport: './IFRSArchives/ArchiveReportsDownloadXL',
  downloadPdfReport: './IFRSArchives/ArchiveReportsDownloadPDF',
  downloadPdf: './IFRSArchives/ArchiveReportsDownloadPDFUser',
  downloadXL: './IFRSArchives/ArchiveReportsDownloadXLUser',
};

const downloadFile = async (url, params, filename) => {
  try {
    const response = await axios.post(url, params, { responseType: 'blob' });
    const blob = new Blob([response.data], { type: response.headers['content-type'] });
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    link.setAttribute('download', filename);
    document.body.appendChild(link);
    link.click();
    link.remove();
  } catch (error) {
    console.error(`Download failed from ${url}`, error);
  }
};

// MAIN COMPONENT
const IFRSDownloadReport = ({ isAdmin }) => {
  const reports = [
    { name: 'User List' },
    { name: 'IFRS Collation' },
    { name: 'IFRS Consolidation' },
  ];

  const handleDownload = (type, reportName) => {
    const params = { reportName };

    if (isAdmin) {
      if (type === 'pdf') {
        downloadFile(REST_URIS.downloadPdfReport, params, 'Admin_Report.pdf');
      } else {
        downloadFile(REST_URIS.downloadXLReport, params, 'Admin_Report.xlsx');
      }
    } else {
      if (type === 'pdf') {
        downloadFile(REST_URIS.downloadPdf, params, 'User_Report.pdf');
      } else {
        downloadFile(REST_URIS.downloadXL, params, 'User_Report.xlsx');
      }
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>{isAdmin ? 'Archived Report Download' : 'User Report Download'}</h2>
      <table border="1" cellPadding="10">
        <thead>
          <tr>
            <th>Report Name</th>
            <th>View</th>
            <th>PRE/POST</th>
          </tr>
        </thead>
        <tbody>
          {reports.map((r, idx) => (
            <tr key={idx}>
              <td>{r.name}</td>
              <td><button>View</button></td>
              <td>
                <button
                  onClick={() => handleDownload('pdf', r.name)}
                  style={{ backgroundColor: 'red', color: 'white', marginRight: '10px' }}
                >
                  PDF
                </button>
                <button
                  onClick={() => handleDownload('xl', r.name)}
                  style={{ backgroundColor: 'green', color: 'white' }}
                >
                  XL
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default IFRSDownloadReport;