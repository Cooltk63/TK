import React from 'react';
import axios from 'axios';

// REST endpoints (equivalent to your Java backend URLs)
const REST_URIS = {
  getCircleList: './IFRSArchives/GetCircleList',
  downloadXLReport: './IFRSArchives/ArchiveReportsDownloadXL',
  downloadPdfReport: './IFRSArchives/ArchiveReportsDownloadPDF',
  downloadPdf: './IFRSArchives/ArchiveReportsDownloadPDFUser',
  downloadXL: './IFRSArchives/ArchiveReportsDownloadXLUser',
};

// Service object that replicates your AngularJS $http factory methods
const IFRSArchivesService = {
  // Fetches a list of circles
  getCircleList: async (params) => {
    try {
      const response = await axios.post(REST_URIS.getCircleList, params);
      return response.data;
    } catch (error) {
      console.error('Error while getting circle list:', error);
      throw error;
    }
  },

  // Downloads Excel report (Admin)
  downloadXLReport: async (params) => {
    return downloadFile(REST_URIS.downloadXLReport, params, 'Admin_Report.xlsx');
  },

  // Downloads PDF report (Admin)
  downloadPdfReport: async (params) => {
    return downloadFile(REST_URIS.downloadPdfReport, params, 'Admin_Report.pdf');
  },

  // Downloads PDF report (User-specific)
  downloadPdf: async (params) => {
    return downloadFile(REST_URIS.downloadPdf, params, 'User_Report.pdf');
  },

  // Downloads Excel report (User-specific)
  downloadXL: async (params) => {
    return downloadFile(REST_URIS.downloadXL, params, 'User_Report.xlsx');
  },
};

// Shared function to handle file downloads (PDF or Excel)
const downloadFile = async (url, params, filename) => {
  try {
    // Make a POST request and expect binary (blob) data in response
    const response = await axios.post(url, params, {
      responseType: 'blob',
    });

    // Create a downloadable blob object from the response
    const blob = new Blob([response.data], {
      type: response.headers['content-type'],
    });

    // Create a temporary <a> element and simulate a download
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    link.setAttribute('download', filename);
    document.body.appendChild(link);
    link.click();
    link.remove(); // Clean up the DOM
  } catch (error) {
    console.error(`Error while downloading from ${url}:`, error);
    throw error;
  }
};

// React UI component that renders buttons for triggering above service calls
const IFRSArchivesComponent = () => {
  // Sample parameters to send with API requests
  const sampleParams = { reportId: '123', date: '2025-03-31' };

  // Click handler for "Get Circle List"
  const handleGetCircleList = async () => {
    try {
      const data = await IFRSArchivesService.getCircleList(sampleParams);
      console.log('Circle List:', data);
      alert("Circle List fetched. Check console for details.");
    } catch (error) {
      console.error('Failed to fetch circle list');
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>IFRS Archive Reports</h2>

      {/* Button to fetch data (like dropdown values) */}
      <button onClick={handleGetCircleList}>Get Circle List</button><br /><br />

      {/* Download buttons for different report types */}
      <button onClick={() => IFRSArchivesService.downloadXLReport(sampleParams)}>Download Admin Excel</button><br /><br />
      <button onClick={() => IFRSArchivesService.downloadPdfReport(sampleParams)}>Download Admin PDF</button><br /><br />
      <button onClick={() => IFRSArchivesService.downloadPdf(sampleParams)}>Download User PDF</button><br /><br />
      <button onClick={() => IFRSArchivesService.downloadXL(sampleParams)}>Download User Excel</button>
    </div>
  );
};

export default IFRSArchivesComponent;