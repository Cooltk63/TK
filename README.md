import React from 'react';
import axios from 'axios';
import {
  Container,
  Typography,
  Grid,
  Button,
  Paper,
  Box,
} from '@mui/material';

// REST endpoints
const REST_URIS = {
  getCircleList: './IFRSArchives/GetCircleList',
  downloadXLReport: './IFRSArchives/ArchiveReportsDownloadXL',
  downloadPdfReport: './IFRSArchives/ArchiveReportsDownloadPDF',
  downloadPdf: './IFRSArchives/ArchiveReportsDownloadPDFUser',
  downloadXL: './IFRSArchives/ArchiveReportsDownloadXLUser',
};

// Service object
const IFRSArchivesService = {
  getCircleList: async (params) => {
    try {
      const response = await axios.post(REST_URIS.getCircleList, params);
      return response.data;
    } catch (error) {
      console.error('Error while getting circle list:', error);
      throw error;
    }
  },

  downloadXLReport: async (params) =>
    downloadFile(REST_URIS.downloadXLReport, params, 'Admin_Report.xlsx'),

  downloadPdfReport: async (params) =>
    downloadFile(REST_URIS.downloadPdfReport, params, 'Admin_Report.pdf'),

  downloadPdf: async (params) =>
    downloadFile(REST_URIS.downloadPdf, params, 'User_Report.pdf'),

  downloadXL: async (params) =>
    downloadFile(REST_URIS.downloadXL, params, 'User_Report.xlsx'),
};

// File download handler
const downloadFile = async (url, params, filename) => {
  try {
    const response = await axios.post(url, params, {
      responseType: 'blob',
    });

    const blob = new Blob([response.data], {
      type: response.headers['content-type'],
    });

    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    link.setAttribute('download', filename);
    document.body.appendChild(link);
    link.click();
    link.remove();
  } catch (error) {
    console.error(`Error downloading from ${url}:`, error);
    throw error;
  }
};

const IFRSArchivesComponent = () => {
  const sampleParams = { reportId: '123', date: '2025-03-31' };

  const handleGetCircleList = async () => {
    try {
      const data = await IFRSArchivesService.getCircleList(sampleParams);
      console.log('Circle List:', data);
      alert('Circle List fetched. Check console.');
    } catch (error) {
      alert('Failed to fetch circle list.');
    }
  };

  return (
    <Container maxWidth="sm">
      <Paper elevation={3} sx={{ padding: 4, borderRadius: 3, mt: 5 }}>
        <Typography variant="h5" gutterBottom>
          IFRS Archive Reports
        </Typography>

        <Grid container spacing={2}>
          <Grid item xs={12}>
            <Button fullWidth variant="contained" onClick={handleGetCircleList}>
              Get Circle List
            </Button>
          </Grid>

          <Grid item xs={12}>
            <Typography variant="subtitle1" fontWeight="bold">
              Admin Reports
            </Typography>
          </Grid>

          <Grid item xs={6}>
            <Button fullWidth variant="outlined" onClick={() => IFRSArchivesService.downloadXLReport(sampleParams)}>
              Download Excel
            </Button>
          </Grid>
          <Grid item xs={6}>
            <Button fullWidth variant="outlined" onClick={() => IFRSArchivesService.downloadPdfReport(sampleParams)}>
              Download PDF
            </Button>
          </Grid>

          <Grid item xs={12} mt={2}>
            <Typography variant="subtitle1" fontWeight="bold">
              User Reports
            </Typography>
          </Grid>

          <Grid item xs={6}>
            <Button fullWidth variant="outlined" onClick={() => IFRSArchivesService.downloadXL(sampleParams)}>
              Download Excel
            </Button>
          </Grid>
          <Grid item xs={6}>
            <Button fullWidth variant="outlined" onClick={() => IFRSArchivesService.downloadPdf(sampleParams)}>
              Download PDF
            </Button>
          </Grid>
        </Grid>
      </Paper>
    </Container>
  );
};

export default IFRSArchivesComponent;