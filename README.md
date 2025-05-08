import React, { useState, useEffect } from 'react';
import { styled } from '@mui/material/styles';
import {
  Table,
  TableBody,
  TableCell,
  tableCellClasses,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
} from '@mui/material';

import {
  Visibility as VisibilityIcon,
  PictureAsPdf as PictureAsPdfIcon,
  Description as DescriptionIcon,
} from '@mui/icons-material';

import useApi from '../../../common/hooks/useApi';
import ReportViewer from '../../../common/components/viewer/ReportViewer';
import downloadFile from '../../../common/components/viewer/DownloadFile';

// Styling for table cells
const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

// Styling for table rows
const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    backgroundColor: theme.palette.action.hover,
  },
  '&:last-child td, &:last-child th': {
    border: 0,
  },
}));

const IFRSDownloadReport = () => {
  const loginUser = JSON.parse(localStorage.getItem('user'));
  const { callApi } = useApi();

  const [rows, setRows] = useState([]);
  const [pdfUrl, setPdfUrl] = useState(null);
  const [dialogOpen, setDialogOpen] = useState(false);
  const [selectedRow, setSelectedRow] = useState(null);
  const qed = loginUser.quarterEndDate;
  const circleName = loginUser.circleName.substring(0, 3);

  // Load report data on component mount
  useEffect(() => {
    const fetchReports = async () => {
      try {
        const data = await callApi('/Admin/getListOfReports', loginUser, 'POST');
        setRows(data?.list1 || []);
      } catch (error) {
        console.error('Error fetching report list:', error);
      }
    };
    fetchReports();
  }, []);

  // For Pdf view
  const handleView = async (report) => {
    console.log('Inside View Report');
    setSelectedRow(report);
    try {
      const payload = {
        report: report,
        user: loginUser,
        type: 'view',
        dash_suppresed: null,
        isSuppresed: false,
      };

      const responseData = await callApi('/Admin/viewReportJrxml', payload, 'POST', 'application/json');
      console.log('Getting view Data ', responseData);
      if (responseData) {
        const blob = new Blob([responseData], { type: 'application/json' });
        const url = URL.createObjectURL(blob); // Create a URL for the Blob
        console.log('URl created :::', url);
        setPdfUrl(blob); // ArrayBuffer
        setDialogOpen(true);
      } else {
        console.error('No response found');
      }
    } catch (error) {
      console.error('Error fetching Circle Maker MiscellaneousWorklist:', error.message);
    }
  };

  // For Pdf Download
  const handlePDFDownload = async (report) => {
    console.log('Inside handleDownload PDF');
    setSelectedRow(report);
    try {
      const payload = {
        report: report,
        user: loginUser,
        type: 'downloadPDF',
        dash_suppresed: null,
        isSuppresed: false,
      };

      const responseData = await callApi('/Admin/viewReportJrxml', payload, 'POST', 'application/pdf');

      if (responseData) {
        let fileName = circleName + '_' + qed + '_' + report.dash_name + '.pdf';
        downloadFile(responseData, 'pdf', fileName);
      } else {
        console.error('No response found');
      }
    } catch (error) {
      console.error('Error while downloading PDF:', error.message);
    }
  };

  // For Excel Download
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

      const responseData = await callApi('/Admin/viewReportJrxml', payload, 'POST', 'application/pdf');

      if (responseData) {
        let fileName = circleName + '_' + qed + '_' + report.dash_name + '.pdf';
        downloadFile(responseData, 'excel', fileName);
      } else {
        console.error('No response found');
      }
    } catch (error) {
      console.error('Error while downloading PDF:', error.message);
    }
  };

  return (
    <>
      <TableContainer component={Paper}>
        <Table aria-label="report table">
          <TableHead>
            <TableRow>
              <StyledTableCell>Report Name</StyledTableCell>
              <StyledTableCell colSpan={3} align="center">
                Actions
              </StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <StyledTableRow key={row.dash_name}>
                <StyledTableCell>{row.dash_name || 'Unnamed Report'}</StyledTableCell>
                <StyledTableCell>
                  <Button size="small" onClick={() => handleView(row)}>
                    <VisibilityIcon />
                  </Button>
                </StyledTableCell>
                <StyledTableCell>
                  <Button size="small" color="error" onClick={() => handlePDFDownload(row)}>
                    <PictureAsPdfIcon />
                  </Button>
                </StyledTableCell>
                <StyledTableCell>
                  <Button size="small" color="success" onClick={() => handleExcelDownload(row)}>
                    <DescriptionIcon />
                  </Button>
                </StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Dialog to show PDF preview */}
      <Paper sx={{ width: '100%' }}>
        {pdfUrl && (
          <ReportViewer
            open={dialogOpen}
            onClose={() => setDialogOpen(false)}
            title={selectedRow.dash_name}
            bufferData={pdfUrl}
            type="html"
            onDownloadPDF={handlePDFDownload}
          />
        )}
      </Paper>
    </>
  );
};

export default IFRSDownloadReport;
