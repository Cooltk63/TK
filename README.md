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


import React, { useState, useEffect } from 'react';
import { Box, Paper, useTheme } from '@mui/material';
import useApi from '../../common/hooks/useApi';
import CustomizedDataGrid from '../../common/components/ui/CustomizedDataGrid';
import { ViewButton, CreateButton, EditButton } from '../../common/components/ui/Buttons';
import Chip from '@mui/material/Chip';
import _ from 'lodash';
import { useNavigate } from 'react-router-dom';
import { CIRCLE_MAKER_REPORT_ROUTES } from '../../common/constants/RoutesConstants';
import ReportViewer from '../../common/components/viewer/ReportViewer';
import downloadFile from '../../common/components/viewer/DownloadFile';

// Main MiscellaneousWorklist Component
const MiscellaneousWorklist = () => {
  const { callApi } = useApi();
  const navigate = useNavigate();
  const [reports, setReports] = useState([]);
  const theme = useTheme();
  const user = JSON.parse(localStorage.getItem('user'));
  const [selectedReport, setSelectdedReport] = useState(null);
  const [viewData, setViewData] = useState(null);
  const [openViewDialog, setOpenViewDialog] = useState(false);
  const circleCode = user.circleCode;
  const qed = user.quarterEndDate;

  useEffect(() => {
    console.log(user);
    const fetchData = async () => {
      try {
        const payload = {
          userId: 'Ot8DorIJK6+qObfKwkxJMQ==',
          token:
            '0b6KGqWJGylD+5vKS9zFjxiOpIn5+VQGJMWKxPAdl8qZUDmEb4jrF23SHc29Mwmz3viAJRmB881nKmhEXM9d2fCw4WlKO8GzzQtA+cwilEMfT2SoAebJgo4sevLJfOZXfl7g0g9/Jabb4cq5DrV1hxppOB/GPhw+s+VdeyVMOL5xd2sjvXwBno+93vdfldfAMB1vWSXKsI9RjbaGmHLshExBgaACzlaBj23vvGdWLBA6qNMIjso7xIg1rZ3FGUduLxvV6EqCjqikV0GuRGmrLtIFjiUjHCF2Nh13h74oVkrCXr9sSBzxTjcTsgSFfb+WYZZP4LrfCpcXiXukpmHIgljZyliMoVY7saERGmfQpQARCuACsrXtottPoKNPpgCuCixtrEc0/sbU5lvQOLyjgVjT8udPXYWh0xMwTsXOfPZ387u9AiMGGbhGvLBbntXuETdEarN9PWp9BG1q7InETl4KMCYeanTtrDoWoUFu917gGPgqJgP5j02Slk/fE1q8SsLqAu9XAh61Dn6DA4O6PADA+RZQAhCh0PuLR55cFt924YjNqJHt8L0ZQpKUcinHwrg4F8T5IDNCFLY3ZlJW2uivM3HhpssXtxbHECXuPmmA2QuEk2nrBy8Mx0114tUFES3YA/aNj81GpKcpU4bBjK6w5XMTkL2pAJxj+WMbGMyc0+1imJdoP96nGH3DZZRDzSp+tMzAmVsDCX3B77o+PT9YFhclAEf2i4bSCojGxJKfJ6lv7JUv1DJpHtbXEP/1+VsL/9u7Lhmf3JqnEgtlIi5FnPQwtVUZPf94KjgMThThHf/73g2BGHKdNxzvpbGJ',
          userName: 'sm0XGrx5vORvI5VfrOCuQQ==',
          circleCode: 'g67NlTGq/d69f9B13WAYgw==',
          circleName: 'Z8+tsGJTuPXPCGnx8OlsVTaj7n22HNIpEMvUwk9HBYE=',
          role: 'IQMW/o3omv+iKZEyHaUnVA==',
          capacity: 'UbJLInb+u/gNUf1afJjMxQ==',
          status: '79hx7ep46vyiygd8wTYTIg==',
          quarterEndDate: 'bYCfZKKhIWkOC6hdCucpGQ==',
          previousQuarterEndDate: 'I7RUPiw1mWI4ZTorGvDrYQ==',
          previousYearEndDate: 'DnnDac/ckErvYzkYhjxAkA==',
          financialYear: 'iTB2kqR+RIDSOZKIWPHqNA==',
          quarter: '6BfdfWe6m61gZvT94N+WQQ==',
          isBranchFinal: '/benKZlAFyUD3QY0MMMozw==',
          isCheckerDig: 'gYSBy8u0wUc9jhNHPw6+mg==',
          isAuditorDig: 'gYSBy8u0wUc9jhNHPw6+mg==',
          isCircleExist: '4nqgE0lTJM1BIqmxFphfIQ==',
        };
        const responseData = await callApi('/MiscWorklist/getMiscMakerWorklist', payload, 'POST');
        if (responseData) {
          console.log(responseData);
          setReports(responseData);
        } else {
          console.error('No response found');
        }
      } catch (error) {
        console.error('Error fetching Circle Maker MiscellaneousWorklist:', error.message);
      }
    };

    fetchData();
  }, []);

  function renderStatus(status) {
    const colors = {
      savedbymaker: 'success',
      reportnotcreated: 'error',
      reportinitiated: 'default',
    };

    return <Chip label={status} color={colors[_.lowerCase(status).replace(/\s+/g, '')]} size="small" />;
  }

  // Column definitions
  const columns = [
    {
      field: 'name',
      headerName: 'Report Name',
      hideable: false,
      flex: 1.5,
    },
    {
      field: 'checkerRemarks',
      headerName: 'Checker Remarks',
      headerAlign: 'center',
      align: 'center',
      flex: 1.5,
      sortable: false,
      renderCell: (params) => (
        <span style={{ color: !params.value ? theme.palette.grey[500] : 'inherit' }}>
          {!params.value ? 'checker remarks will appear here' : params.value}
        </span>
      ),
    },
    {
      field: 'pendingStatus',
      headerName: 'Status',
      headerAlign: 'center',
      align: 'center',
      flex: 1.5,
      hideable: false,
      renderCell: (params) => renderStatus(params.value),
    },
    {
      field: 'actions',
      headerName: 'Action',
      headerAlign: 'center',
      align: 'center',
      flex: 2,
      sortable: false,
      filterable: false,
      renderCell: (params) => (
        <Box
          sx={{
            '& .MuiDataGrid-cell:focus': {
              outline: 'none', // Remove focus outline for cells
            },
            gap: 2,
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            m: 1,
          }}
        >
          {(params.row.status === '10' || params.row.status === '11' || params.row.status === '12') && (
            <EditButton
              onClickHandler={() => {
                console.log(params.row, 'clicked on Edit');
                handleEdit(params.row);
              }}
            />
          )}
          {params.row.status && (
            <ViewButton
              onClickHandler={() => {
                console.log(params.row, 'clicked on view');
                handleView(params.row);
              }}
            />
          )}

          {(params.row.status === null || params.row.status === '' || params.row.status == undefined) && (
            <CreateButton
              onClickHandler={() => {
                handleCreate(params.row);
              }}
            />
          )}
        </Box>
      ),
    },
  ];

  const handleCreate = async (report) => {
    console.log('Inside Create Report');
    console.log(report);
    try {
      const payload = {
        report: report,
        user: user,
        type: 'I',
      };
      const responseData = await callApi('/MiscWorklist/miscReportEntryInMasterTable', payload, 'POST');
      if (responseData == 1) {
        console.log(responseData);
        CIRCLE_MAKER_REPORT_ROUTES[report.reportMasterId]
          ? navigate('./' + CIRCLE_MAKER_REPORT_ROUTES[report.reportMasterId], { state: { report } })
          : console.log('Invalid ReportMasterID');
      } else {
        console.error('No response found');
      }
    } catch (error) {
      console.error('Error fetching Circle Maker MiscellaneousWorklist:', error.message);
    }
  };

  const handleView = async (report) => {
    console.log('Inside View Report');
    setSelectdedReport(report);
    try {
      const payload = {
        report: report,
        user: user,
      };

      const responseData = await callApi('/MiscWorklist/viewReport', payload, 'POST', 'arraybuffer');

      if (responseData) {
        setViewData(responseData); // ArrayBuffer
        setOpenViewDialog(true);
      } else {
        console.error('No response found');
      }
    } catch (error) {
      console.error('Error fetching Circle Maker MiscellaneousWorklist:', error.message);
    }
  };

  const handlePDFDownload = async () => {
    console.log('Inside handleDownload PDF');
    try {
      const payload = {
        qDate: qed,
        circleCode: circleCode,
        report: selectedReport,
      };

      const responseData = await callApi('/MiscWorklist/downloadPdfReport', payload, 'POST', 'arraybuffer');

      if (responseData) {
        let fileName = '';
        if (selectedReport.reportMasterId == '5001') {
          fileName = 'DICGC_' + qed + '_' + circleCode + '_Report.pdf';
          downloadFile(responseData, 'pdf', fileName);
        } else if (selectedReport.reportMasterId == '5002') {
          fileName = 'ANNEXURE2C_' + qed + '_' + circleCode + '_Report.pdf';
          downloadFile(responseData, 'pdf', fileName);
        }
      } else {
        console.error('No response found');
      }
    } catch (error) {
      console.error('Error while downloading PDF:', error.message);
    }
  };

  const handleXLDownload = async () => {
    console.log('Inside handleDownload Excel');
    try {
      const payload = {
        qDate: qed,
        circleCode: circleCode,
        report: selectedReport,
      };

      const responseData = await callApi('/MiscWorklist/downloadXLReport', payload, 'POST', 'arraybuffer');

      if (responseData) {
        let fileName = '';
        if (selectedReport.reportMasterId == '5001') {
          fileName = 'DICGC_' + qed + '_' + circleCode + '_Report.xlsx';
          downloadFile(responseData, 'excel', fileName);
        } else if (selectedReport.reportMasterId == '5002') {
          fileName = 'ANNEXURE2C_' + qed + '_' + circleCode + '_Report.xlsx';
          downloadFile(responseData, 'excel', fileName);
        }
      } else {
        console.error('No response found');
      }
    } catch (error) {
      console.error('Error while downloading Excel :', error.message);
    }
  };

  const handleEdit = (report) => {
    console.log('Inside View Report');
    console.log(report);
    console.log(report.reportMasterId);
    CIRCLE_MAKER_REPORT_ROUTES[report.reportMasterId]
      ? navigate('./' + CIRCLE_MAKER_REPORT_ROUTES[report.reportMasterId], { state: { report } })
      : console.log('Invalid ReportMasterID');
  };

  return (
    <Paper sx={{ width: '100%' }}>
      <CustomizedDataGrid rows={reports} columns={columns} getRowId={(row) => row.reportMasterId} />
      {viewData && (
        <ReportViewer
          open={openViewDialog}
          onClose={() => setOpenViewDialog(false)}
          title={selectedReport.name}
          bufferData={viewData}
          type="html"
          onDownloadPDF={handlePDFDownload}
          onDownloadExcel={handleXLDownload}
        />
      )}
    </Paper>
  );
};

export default MiscellaneousWorklist;
