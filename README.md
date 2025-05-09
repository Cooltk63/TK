import React, { useState, useEffect } from 'react';
import {
  Box, Paper, Typography, Button, MenuItem, Select, FormControl, InputLabel,
  RadioGroup, FormControlLabel, Radio, Alert, Stack
} from '@mui/material';
import { styled } from '@mui/material/styles';
// import { AES256, encryptValues } from '../../utils/encryption';
import IFRSArchivesService from './IFRSArchivesService'; // Axios service layer

const StyledBox = styled(Box)(({ theme }) => ({
  padding: theme.spacing(4),
  backgroundColor: '#f9f9f9',
}));

const IFRSDownloadArchives = () => {
  // const sessionUser = JSON.parse(AES256.decrypt(localStorage.getItem('user')));
  const sessionUser = {
    userId: 'testUser',
    circleCode: 'CIRC01',
    quarterEndDate: '31/03/2024'
  };

  const [circleList, setCircleList] = useState([]);
  const [selectedQed, setSelectedQed] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('reportwise');
  const [selectedCircle, setSelectedCircle] = useState('');
  const [downloadType, setDownloadType] = useState('PDF');
  const [fileNotFound, setFileNotFound] = useState(false);
  const [errorDownload, setErrorDownload] = useState(false);

  // const { passphrase, iv, salt, aesUtil } = encryptValues();

  const qed = sessionUser.quarterEndDate;
  const userId = sessionUser.userId;
  const circleCode = sessionUser.circleCode;

  useEffect(() => {
    fetchCircleData();
  }, []);

  const fetchCircleData = async () => {
    const payload = { qed, userId };
    // const params = {
    //   salt,
    //   iv,
    //   data: aesUtil.encrypt(salt, iv, passphrase, JSON.stringify(payload)),
    // };
    const params = { payload }; // Temporary fallback
    try {
      const data = await IFRSArchivesService.getCircleList(params);
      setCircleList(data || []);
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

    // const params = {
    //   salt,
    //   iv,
    //   data: aesUtil.encrypt(salt, iv, passphrase, JSON.stringify(payload)),
    // };
    const params = { payload }; // Temporary fallback

    try {
      const response = downloadType === 'PDF'
        ? await IFRSArchivesService.downloadPdf(params)
        : await IFRSArchivesService.downloadXL(params);

      if (response.flag) {
        if (downloadType === 'PDF') {
          const link = document.createElement('a');
          link.href = `data:application/pdf;base64,${response.pdfContent}`;
          link.download = `${circleCode}_IFRS_Liabilities_${selectedQed}.pdf`;
          link.click();
        } else {
          const byteCharacters = atob(response.pdfContent);
          const byteArray = new Uint8Array([...byteCharacters].map(char => char.charCodeAt(0)));
          const blob = new Blob([byteArray], {
            type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
          });
          const url = URL.createObjectURL(blob);
          const link = document.createElement('a');
          link.href = url;
          link.download = `${circleCode}_IFRS_Liabilities_${selectedQed}.xlsx`;
          link.click();
          URL.revokeObjectURL(url);
        }
      } else {
        if (response.displayMessage === 'fileNotFound') setFileNotFound(true);
        else if (response.displayMessage === 'error') setErrorDownload(true);
      }
    } catch (err) {
      alert(`Failed to Download ${downloadType}: ${err}`);
    }
  };

  return (
    <StyledBox>
      <Typography variant="h5" gutterBottom>IFRS Archive Download</Typography>

      <Stack spacing={3}>
        {/* Category Select */}
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

        {/* QED Dropdown */}
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

        {/* Circle Dropdown */}
        {selectedCategory === 'reportwise' && (
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
        )}

        {/* Download Type */}
        <FormControl>
          <RadioGroup
            row
            value={downloadType}
            onChange={(e) => setDownloadType(e.target.value)}
          >
            <FormControlLabel value="PDF" control={<Radio />} label="PDF" />
            <FormControlLabel value="EXCEL" control={<Radio />} label="Excel" />
          </RadioGroup>
        </FormControl>

        {/* Download Button */}
        <Button variant="contained" color="primary" onClick={handleDownload}>
          Download {downloadType}
        </Button>

        {/* Alerts */}
        {fileNotFound && <Alert severity="warning">File not found</Alert>}
        {errorDownload && <Alert severity="error">Error during download</Alert>}
      </Stack>
    </StyledBox>
  );
};

// Helper to generate QED options
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

  for (let year = 2024; year < endYear; year++) {
    quarters.forEach((q, index) => {
      if (year === endYear - 1 && index >= currentQuarter) return;
      const endDate = new Date(q.month === 2 ? year + 1 : year, q.month, q.day);
      const dateStr = `${String(endDate.getDate()).padStart(2, '0')}/${String(endDate.getMonth() + 1).padStart(2, '0')}/${endDate.getFullYear()}`;
      options.push(<MenuItem key={dateStr} value={dateStr}>{`${dateStr} ${q.label}`}</MenuItem>);
    });
  }

  return options;
}

export default IFRSDownloadArchives;