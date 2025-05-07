import React, { useState, useEffect } from 'react';
import { styled } from '@mui/material/styles';
import {
  Table, TableBody, TableCell, TableContainer,
  TableHead, TableRow, Paper, Button, Modal, Box
} from '@mui/material';
import useApi from "../../../common/hooks/useApi";
import {
  Close as CloseIcon,
  Visibility as VisibilityIcon,
  PictureAsPdf as PictureAsPdfIcon,
  Description as DescriptionIcon,
} from '@mui/icons-material';

const loginUser = JSON.parse(localStorage.getItem('user'));

// Table styles
const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${TableCell.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${TableCell.body}`]: {
    fontSize: 14,
  },
}));
const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': { backgroundColor: theme.palette.action.hover },
  '&:last-child td, &:last-child th': { border: 0 },
}));

// Modal styling
const modalStyle = {
  position: 'absolute',
  top: '5%',
  left: '5%',
  width: '90%',
  height: '90%',
  bgcolor: 'background.paper',
  boxShadow: 24,
  p: 2,
  overflow: 'auto'
};

const IFRSDownloadReport = () => {
  const { data, error, isLoading, callApi } = useApi();
  const [rows, setRows] = useState([]);
  const [receivedData, setReceivedData] = useState({});
  const [openModal, setOpenModal] = useState(false);
  const [pdfUrl, setPdfUrl] = useState(null);

  const loadData = async () => {
    try {
      const responseData = await callApi('/Admin/getListOfReports', loginUser, 'POST');
      setReceivedData(responseData);
      setRows(responseData?.list1 || []);
    } catch (error) {
      console.error('Error loading data:', error);
    }
  };

  useEffect(() => { loadData(); }, []);

  // Main Action Handler
  const actionHandler = async (type, data) => {
    const payload = {
      dash_suppresed: null,
      isSuppresed: false,
      report: data,
      user: loginUser
    };

    try {
      const response = await fetch('/Admin/viewReportJrxmlCircle', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      if (!response.ok) throw new Error('Failed to fetch blob');

      const blob = await response.blob();

      if (type === 'view') {
        const url = URL.createObjectURL(blob);
        setPdfUrl(url);        // set blob URL
        setOpenModal(true);    // open modal
      } else {
        // download logic for PDF or Excel
        const contentType = type === 'downloadPDF' ? 'application/pdf' : 'application/vnd.ms-excel';
        const filename = type === 'downloadPDF' ? 'report.pdf' : 'report.xlsx';

        const downloadUrl = window.URL.createObjectURL(new Blob([blob], { type: contentType }));
        const link = document.createElement('a');
        link.href = downloadUrl;
        link.setAttribute('download', filename);
        document.body.appendChild(link);
        link.click();
        link.remove();
      }

    } catch (err) {
      console.error(`${type} action failed:`, err);
    }
  };

  return (
    <>
      <TableContainer component={Paper}>
        <Table aria-label="customized table">
          <TableHead>
            <TableRow>
              <StyledTableCell>Report Name</StyledTableCell>
              <StyledTableCell colSpan={3} sx={{ textAlign: 'center' }}>Actions</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <StyledTableRow key={row.dash_name}>
                <StyledTableCell>{row.dash_name || "HELLO"}</StyledTableCell>
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

      {/* Modal for PDF preview */}
      <Modal open={openModal} onClose={() => setOpenModal(false)}>
        <Box sx={modalStyle}>
          <Button onClick={() => setOpenModal(false)} variant="contained" color="error" size="small" sx={{ float: 'right' }}>
            <CloseIcon fontSize="small" />
          </Button>
          {pdfUrl && (
            <iframe src={pdfUrl} title="PDF Preview" width="100%" height="100%" frameBorder="0" />
          )}
        </Box>
      </Modal>
    </>
  );
};

export default IFRSDownloadReport;