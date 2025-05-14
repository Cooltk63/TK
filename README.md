import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {
  Box, Typography, Button, MenuItem, Select, FormControl, InputLabel,
  RadioGroup, FormControlLabel, Radio, Alert, Grid, Paper
} from '@mui/material';
import { styled } from '@mui/material/styles';
import useApi from '../../../common/hooks/useApi'

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
  const { callApi } = useApi();

  useEffect(() => {
    fetchCircleData();
  }, []);

  

  const fetchCircleData = async () => {
    try {
        const payload ={
            "salt": "7a47fbbab8c7b37a412441c3427bfad7",
            "iv": "e54a9f072307cea44c42de6191dd8cad",
            "data": "uY+KyAu6j6ImOcemBRrujSfKIWVk21DyO6SCmyBoXyuw+LEv91wnUqCmgLgrtvrr"
        }

      const response = await callApi('/IFRSArchives/GetCircleList',payload ,'POST')
        
       
      console.log("Response Data Received :::"+response)
      setCircleList(response || []);
    } catch (err) {
      console.error('Error fetching circle list:', err);
    }
  };

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

  return (
    <StyledPaper elevation={2}>
      <Typography variant="h5" gutterBottom sx={{marginY:2}}>IFRS Archive Download</Typography>

      <Grid container spacing={5} alignItems="center">
        {/* Category */}
        <Grid size={{ xs: 2, sm: 4, md: 4 }}>
          <FormControl fullWidth>
            
            {/* <InputLabel>Category</InputLabel> */}
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
        <Grid size={{ xs: 2, sm: 2, md: 4 }}>
          <FormControl fullWidth>
            <InputLabel>Quarter End Date</InputLabel>
            <Select
              value={selectedQed}
              onChange={(e) => setSelectedQed(e.target.value)}
              label="Quarter End Date"
            >
              {generateQedOptions().map((qed)=>(<MenuItem key={qed.value} value={qed.value}>
                {qed.label}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Grid>

        {/* Circle (conditional) */}
        {selectedCategory === 'reportwise' && (
          <Grid size={{ xs: 2, sm: 2, md: 4 }}>
            <FormControl fullWidth>
              <InputLabel>Circle</InputLabel>
              <Select
                value={selectedCircle}
                onChange={(e) => setSelectedCircle(e.target.value)}
                label="Circle"
              >
                {circleList.map(circle => (
                  <MenuItem key={circle.CIRCLENAME} value={circle.CIRCLECODE}>
                    {circle.CIRCLENAME}
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
   const currentDate = new Date();
  const currentYear = currentDate.getFullYear();
  const currentMonth = currentDate.getMonth();
  const currentQuarter = currentMonth < 3 ? 3 : currentMonth < 6 ? 0 : currentMonth < 9 ? 1 : 2;
  const maxCurrentQuarter = currentQuarter - 1;
  const isQ1 = currentMonth < 3;
  const startYear = 2024;
  const endYear = isQ1 ? currentYear : currentYear + 1;

  const quarters = [
    { label: "Q1 (April-June)", month: 5, day: 30 },
    { label: "Q2 (July-September)", month: 8, day: 30 },
    { label: "Q3 (October-December)", month: 11, day: 31 },
    { label: "Q4 (January-March)", month: 2, day: 31 },
  ];

  const options = [];

  for (let year = startYear; year < endYear; year++) {
    quarters.forEach((quarter, index) => {
      if (year === endYear - 1 && index >= maxCurrentQuarter) return;

      const fyStartYear = year;
      const fyEndYear = year + 1;
      const endDate = new Date(
        quarter.month === 2 ? fyEndYear : fyStartYear,
        quarter.month,
        quarter.day
      );

      const formattedDate = `${endDate.getDate().toString().padStart(2, '0')}/${(endDate.getMonth() + 1).toString().padStart(2, '0')}/${endDate.getFullYear()}`;
      options.push({ value: formattedDate, label: `${formattedDate} ${quarter.label}` });
    });
  }

  return options;
}

export default IFRSDownloadArchives;
