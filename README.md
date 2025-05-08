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
  Dialog,
  DialogTitle,
  DialogContent,
  IconButton,
} from '@mui/material';

import {
  Visibility as VisibilityIcon,
  PictureAsPdf as PictureAsPdfIcon,
  Description as DescriptionIcon,
  Close as CloseIcon,
} from '@mui/icons-material';

import useApi from '../../../common/hooks/useApi';
import downloadFile from '../../../common/components/viewer/DownloadFile';

// Styled components for table cells and rows
const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

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

  // Fetch report list on component mount
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

  // Handle View Report (opens Dialog with PDF view)
  const handleView = async (report) => {
    console.log('Inside View Report');
    setSelectedRow(report);
    try {
      const payload = {
        report,
        user: loginUser,
        type: 'view',
        dash_suppresed: null,
        isSuppresed: false,
      };

      const responseData = await callApi('/Admin/viewReportJrxml', payload, 'POST', 'blob');

      if (responseData) {
        const blob = new Blob([responseData], { type: 'application/pdf' }); // Change to PDF blob
        setPdfUrl(blob);
        setDialogOpen(true);
      } else {
        console.error('No response found');
      }
    } catch (error) {
      console.error('Error viewing report:', error.message);
    }
  };

  // Handle PDF Download
  const handlePDFDownload = async (report) => {
    console.log('Inside handleDownload PDF');
    setSelectedRow(report);
    try {
      const payload = {
        report,
        user: loginUser,
        type: 'downloadPDF',
        dash_suppresed: null,
        isSuppresed: false,
      };

      const responseData = await callApi('/Admin/viewReportJrxml', payload, 'POST', 'blob');

      if (responseData) {
        let fileName = circleName + '_' + qed + '_' + report.dash_name + '.pdf';
        downloadFile(responseData, 'pdf', fileName);
      } else {
        console.error('No response found');
      }
    } catch (error) {
      console.error('Error downloading PDF:', error.message);
    }
  };

  // Handle Excel Download
  const handleExcelDownload = async (report) => {
    console.log('Inside handleDownload Excel');
    setSelectedRow(report);
    try {
      const payload = {
        report,
        user: loginUser,
        type: 'download',
        dash_suppresed: null,
        isSuppresed: false,
      };

      const responseData = await callApi('/Admin/viewReportJrxml', payload, 'POST', 'blob');

      if (responseData) {
        let fileName = circleName + '_' + qed + '_' + report.dash_name + '.pdf';
        downloadFile(responseData, 'excel', fileName);
      } else {
        console.error('No response found');
      }
    } catch (error) {
      console.error('Error downloading Excel:', error.message);
    }
  };

  return (
    <>
      <TableContainer component={Paper}>
        <Table aria-label="report table">
          <TableHead>
            <TableRow>
              <StyledTableCell>Report Name</StyledTableCell>
              <StyledTableCell colSpan={3} align="center">Actions</StyledTableCell>
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
      <Dialog
        open={dialogOpen}
        onClose={() => setDialogOpen(false)}
        fullWidth
        maxWidth="lg"
      >
        <DialogTitle>
          {selectedRow?.dash_name}
          <IconButton
            aria-label="close"
            onClick={() => setDialogOpen(false)}
            sx={{
              position: 'absolute',
              right: 8,
              top: 8,
              color: (theme) => theme.palette.grey[500],
            }}
          >
            <CloseIcon />
          </IconButton>
        </DialogTitle>
        <DialogContent dividers>
          {pdfUrl ? (
            <object
              data={URL.createObjectURL(pdfUrl)}
              type="application/pdf"
              width="100%"
              height="600px"
            >
              <p>
                PDF preview not supported.{' '}
                <a href={URL.createObjectURL(pdfUrl)} target="_blank" rel="noopener noreferrer">
                  Download PDF
                </a>
              </p>
            </object>
          ) : (
            <p>Loading PDF...</p>
          )}
        </DialogContent>
      </Dialog>
    </>
  );
};

export default IFRSDownloadReport;

This is working for pdf view perfectly but downloading the empty or 0 byte size files for PDF & EXCEl
