import React, { useState, useEffect } from 'react';
import { styled } from '@mui/material/styles';
import {
  Table, TableBody, TableCell, tableCellClasses,
  TableContainer, TableHead, TableRow, Paper,
  Button, Dialog, DialogTitle, DialogContent, IconButton
} from '@mui/material';

import {
  Visibility as VisibilityIcon,
  PictureAsPdf as PictureAsPdfIcon,
  Description as DescriptionIcon,
  Close as CloseIcon
} from '@mui/icons-material';

import useApi from "../../../common/hooks/useApi";
import axios from 'axios';


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

  // Load report data on component mount
  useEffect(() => {
    const fetchReports = async () => {
      try {
        const data = await callApi('/Admin/getListOfReports', loginUser, 'POST');
        setRows(data?.list1 || []);
      } catch (error) {
        console.error("Error fetching report list:", error);
      }
    };
    fetchReports();
  }, []);

  // Handles button actions for view/download
  const actionHandler = async (type, report) => {
    const payload = {
      dash_suppresed: null,
      isSuppresed: false,
      report: report,
      user: loginUser
    };

    try {

      const response = await callApi('/Admin/viewReportJrxmlCircle', payload, 'POST', 'arraybuffer');
 

      const blob = new Blob([response], {
        type: type === 'downloadExcel' ? 'application/vnd.ms-excel' : 'application/pdf'
      });

      const blobUrl = window.URL.createObjectURL(blob);

      if (type === 'view') {
        setPdfUrl(blobUrl); // Set PDF blob URL for preview
        setDialogOpen(true); // Open modal
      } else {
        // For download actions
        const link = document.createElement('a');
        link.href = blobUrl;
        link.download = type === 'downloadPDF' ? 'report.pdf' : 'report.xls';
        document.body.appendChild(link);
        link.click();
        link.remove();
      }

    } catch (error) {
      console.error(`Error on ${type}:`, error);
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
                <StyledTableCell>{row.dash_name || "Unnamed Report"}</StyledTableCell>
                <StyledTableCell>
                  <Button size="small" onClick={() => actionHandler('view', row)}>
                    <VisibilityIcon />
                  </Button>
                </StyledTableCell>
                <StyledTableCell>
                  <Button size="small" color="error" onClick={() => actionHandler('downloadPDF', row)}>
                    <PictureAsPdfIcon />
                  </Button>
                </StyledTableCell>
                <StyledTableCell>
                  <Button size="small" color="success" onClick={() => actionHandler('downloadExcel', row)}>
                    <DescriptionIcon />
                  </Button>
                </StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Dialog to show PDF preview */}
      <Dialog open={dialogOpen} onClose={() => setDialogOpen(false)} maxWidth="md" fullWidth>
        <DialogTitle>
          Preview Report
          <IconButton
            aria-label="close"
            onClick={() => setDialogOpen(false)}
            sx={{ position: 'absolute', right: 8, top: 8 }}
          >
            <CloseIcon />
          </IconButton>
        </DialogTitle>
        <DialogContent dividers>
          {pdfUrl && (
            <iframe
              src={pdfUrl}
              title="PDF Viewer"
              width="100%"
              height="600px"
              style={{ border: 'none' }}
            />
          )}
        </DialogContent>
      </Dialog>
    </>
  );
};

export default IFRSDownloadReport;


Still getting the failed ot load pdf error insted of single call use 3 different calls for each time to view, downloadPdf ,downloadExcel click & used below sample provided code just for reference to how view is handled here and displayed use the below code reference for everthing & do not changes my code working functionality 
