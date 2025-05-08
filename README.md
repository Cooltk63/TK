import React, { useState } from 'react';
import {
  Button, Grid, TextField, MenuItem, Dialog, DialogTitle, DialogContent, DialogActions,
  Typography, Paper, FormControl, InputLabel, Select
} from '@mui/material';
import axios from 'axios';

// Axios configuration
const api = axios.create({
  baseURL: '/IFRSWeb',
  headers: {
    'Content-Type': 'application/json'
  }
});

const IFRSArchiveDownloads = () => {
  const [branchCode, setBranchCode] = useState('');
  const [quarterEndDate, setQuarterEndDate] = useState('');
  const [reportType, setReportType] = useState('');
  const [showDialog, setShowDialog] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  // Sample report options
  const reportTypes = [
    { value: 'IFRS_Summary', label: 'IFRS Summary' },
    { value: 'IFRS_Branch', label: 'IFRS Branch' },
    { value: 'IFRS_Circle', label: 'IFRS Circle' }
  ];

  const validateFields = () => {
    if (!branchCode || !quarterEndDate || !reportType) {
      setErrorMessage('All fields are required.');
      return false;
    }
    setErrorMessage('');
    return true;
  };

  const downloadReport = async () => {
    if (!validateFields()) return;

    const payload = {
      branch_code: branchCode,
      quarterEndDate,
      reportType
    };

    try {
      const response = await api.post('/IFRSDownloadArchivesController/downloadIFRSArchiveReport', payload, {
        responseType: 'blob'
      });

      const fileName = `${reportType}_${quarterEndDate}_${branchCode}.pdf`;
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', fileName);
      document.body.appendChild(link);
      link.click();
      link.remove();
    } catch (error) {
      setErrorMessage('Download failed. Please try again.');
      console.error('Download error:', error);
    }
  };

  return (
    <Paper sx={{ padding: 4, maxWidth: 700, margin: 'auto', mt: 5 }}>
      <Typography variant="h6" gutterBottom>
        IFRS Archive Report Download
      </Typography>

      {errorMessage && (
        <Typography color="error" gutterBottom>
          {errorMessage}
        </Typography>
      )}

      <Grid container spacing={3}>
        <Grid item xs={12}>
          <TextField
            fullWidth
            label="Branch Code"
            value={branchCode}
            onChange={(e) => setBranchCode(e.target.value)}
          />
        </Grid>

        <Grid item xs={12}>
          <TextField
            fullWidth
            type="date"
            label="Quarter End Date"
            InputLabelProps={{ shrink: true }}
            value={quarterEndDate}
            onChange={(e) => setQuarterEndDate(e.target.value)}
          />
        </Grid>

        <Grid item xs={12}>
          <FormControl fullWidth>
            <InputLabel>Report Type</InputLabel>
            <Select
              value={reportType}
              onChange={(e) => setReportType(e.target.value)}
              label="Report Type"
            >
              {reportTypes.map((report) => (
                <MenuItem key={report.value} value={report.value}>
                  {report.label}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Grid>

        <Grid item xs={12} textAlign="center">
          <Button variant="contained" onClick={downloadReport}>
            Download Report
          </Button>
        </Grid>
      </Grid>

      {/* Optional Dialog for feedback or preview */}
      <Dialog open={showDialog} onClose={() => setShowDialog(false)}>
        <DialogTitle>Report Preview</DialogTitle>
        <DialogContent>
          {/* Could insert iframe preview if needed */}
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowDialog(false)}>Close</Button>
        </DialogActions>
      </Dialog>
    </Paper>
  );
};

export default IFRSArchiveDownloads;