import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {
  Box, Typography, Button, MenuItem, Select, FormControl, InputLabel,
  RadioGroup, FormControlLabel, Radio, Alert, Grid, Paper
} from '@mui/material';
import { styled } from '@mui/material/styles';

const StyledPaper = styled(Paper)(({ theme }) => ({
  padding: theme.spacing(4),
  maxWidth: '100%',
  margin: 'auto',
  backgroundColor: '#f5f5f5',
}));

const IFRSDownloadArchives = () => {
  const sessionUser = {
    userId: 'testUser',
    circleCode: 'CIRC01',
    quarterEndDate: '31/03/2024',
  };

  const [circleList, setCircleList] = useState([]);
  const [selectedQed, setSelectedQed] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('reportwise');
  const [selectedCircle, setSelectedCircle] = useState('');
  const [downloadType, setDownloadType] = useState('PDF');
  const [fileNotFound, setFileNotFound] = useState(false);
  const [errorDownload, setErrorDownload] = useState(false);

  const qed = sessionUser.quarterEndDate;
  const userId = sessionUser.userId;
  const circleCode = sessionUser.circleCode;

  useEffect(() => {
    fetchCircleData();
  }, []);

  const fetchCircleData = async () => {
    try {
      const response = await axios.post('/ifrs/getCircleList', {
        qed: qed,
        userId: userId,
      });
      setCircleList(response.data || []);
    } catch (err) {
      console.error('Error fetching circle list:', err);
    }
  };

  const handleDownload = async () => {
    setFileNotFound(false);
    setErrorDownload(false);
    if (!selectedQed) return;

    const payload = {
      qed: selectedQed,
      circleCode: selectedCategory === 'reportwise' ? selectedCircle : circleCode,
      type: ['consolidation', 'collation'].includes(selectedCategory) ? selectedCategory : undefined,
    };

    try {
      const response = await axios.post(
        downloadType === 'PDF' ? '/ifrs/downloadPdf' : '/ifrs/downloadXL',
        payload
      );

      if (response.data.flag) {
        const fileName = `${circleCode}_IFRS_Liabilities_${selectedQed}`;
        const base64Data = response.data.pdfContent;

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
        if (response.data.displayMessage === 'fileNotFound') setFileNotFound(true);
        else if (response.data.displayMessage === 'error') setErrorDownload(true);
      }
    } catch (err) {
      alert(`Failed to download ${downloadType}: ${err.message}`);
    }
  };

  return (
    <StyledPaper elevation={3}>
      <Typography variant="h5" gutterBottom>IFRS Archive Download</Typography>

      <Grid container spacing={3} alignItems="center">
        {/* Category */}
        <Grid item xs={12} md={2}>
          <FormControl fullWidth>
            <InputLabel>Category</InputLabel>
            <Select
              value={selectedCategory}
              onChange={(e) => setSelectedCategory(e.target.value)}
              label="Category"
            >
              <MenuItem value="reportwise">Report Wise</MenuItem>
              <MenuItem value="consolidation">Consolidation</MenuItem>
              <MenuItem value="collation">Collation</MenuItem>
            </Select>
          </FormControl>
        </Grid>

        {/* QED */}
        <Grid item xs={12} md={3}>
          <FormControl fullWidth>
            <InputLabel>Quarter End Date</InputLabel>
            <Select
              value={selectedQed}
              onChange={(e) => setSelectedQed(e.target.value)}
              label="Quarter End Date"
            >
              {generateQedOptions()}
            </Select>
          </FormControl>
        </Grid>

        {/* Circle (conditional) */}
        {selectedCategory === 'reportwise' && (
          <Grid item xs={12} md={2}>
            <FormControl fullWidth>
              <InputLabel>Circle</InputLabel>
              <Select
                value={selectedCircle}
                onChange={(e) => setSelectedCircle(e.target.value)}
                label="Circle"
              >
                {circleList.map(circle => (
                  <MenuItem key={circle.circleCode} value={circle.circleCode}>
                    {circle.circleName}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          </Grid>
        )}

        {/* Radio Buttons */}
        <Grid item xs={12} md={2}>
          <RadioGroup row value={downloadType} onChange={(e) => setDownloadType(e.target.value)}>
            <FormControlLabel value="PDF" control={<Radio />} label="PDF" />
            <FormControlLabel value="EXCEL" control={<Radio />} label="Excel" />
          </RadioGroup>
        </Grid>

        {/* Download Button */}
        <Grid item xs={12} md={3}>
          <Button fullWidth variant="contained" color="primary" onClick={handleDownload}>
            Download {downloadType}
          </Button>
        </Grid>

        {/* Alerts */}
        {fileNotFound && (
          <Grid item xs={12}>
            <Alert severity="warning">File not found</Alert>
          </Grid>
        )}
        {errorDownload && (
          <Grid item xs={12}>
            <Alert severity="error">Error during download</Alert>
          </Grid>
        )}
      </Grid>
    </StyledPaper>
  );
};

function generateQedOptions() {
  const options = [];
  const currentDate = new Date();
  const currentYear = currentDate.getFullYear();
  const currentMonth = currentDate.getMonth();
  const currentQuarter = currentMonth < 3 ? 3 : currentMonth < 6 ? 0 : currentMonth < 9 ? 1 : 2;
  const isQ1 = currentMonth < 3;
  const endYear = isQ1 ? currentYear : currentYear + 1;

  const quarters = [
    { label: "Q1 (Apr-Jun)", month: 5, day: 30 },
    { label: "Q2 (Jul-Sep)", month: 8, day: 30 },
    { label: "Q3 (Oct-Dec)", month: 11, day: 31 },
    { label: "Q4 (Jan-Mar)", month: 2, day: 31 },
  ];

  for (let year = 2024; year <= endYear; year++) {
    quarters.forEach((q, index) => {
      if (year === endYear && index >= currentQuarter) return;
      const endDate = new Date(q.month === 2 ? year + 1 : year, q.month, q.day);
      const dateStr = `${String(endDate.getDate()).padStart(2, '0')}/${String(endDate.getMonth() + 1).padStart(2, '0')}/${endDate.getFullYear()}`;
      options.push(<MenuItem key={dateStr} value={dateStr}>{`${dateStr} ${q.label}`}</MenuItem>);
    });
  }

  return options;
}

export default IFRSDownloadArchives;