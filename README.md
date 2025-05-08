 blob:http://localhost:7000/ba746c48-649e-44cd-8906-3e4e46f24510

above blob I am getting on 'console.log('URL created :::', url);' this line inside the handleView function

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

      const responseData = await callApi('/Admin/viewReportJrxml', payload, 'POST', 'arraybuffer');
      console.log('Getting view Data ', responseData);
      if (responseData) {
        const blob = new Blob([responseData], { type: 'application/pdf' });
        const url = URL.createObjectURL(blob); // Create a URL for the Blob
        console.log('URl created :::', url);
        setPdfUrl(url); // ArrayBuffer
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
        let fileName = circleName + '_' + qed + '_' + report.dash_name + '.excel';
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


Below response received after click on view button which fetch the data from backened as per below
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title></title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <style type="text/css">
    a {text-decoration: none}
  </style>
</head>
<body text="#000000" link="#000000" alink="#000000" vlink="#000000">
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr><td width="50%">&nbsp;</td><td align="center">

<a name="JR_PAGE_ANCHOR_0_1"></a>
<table class="jrPage" data-jr-height="345" cellpadding="0" cellspacing="0" border="0" style="empty-cells: show; width: 595px; border-collapse: collapse; background-color: white;">
<tr valign="top" style="height:0">
<td style="width:25px"></td>
<td style="width:30px"></td>
<td style="width:100px"></td>
<td style="width:60px"></td>
<td style="width:130px"></td>
<td style="width:48px"></td>
<td style="width:62px"></td>
<td style="width:10px"></td>
<td style="width:28px"></td>
<td style="width:77px"></td>
<td style="width:25px"></td>
</tr>
<tr valign="top" style="height:30px">
<td colspan="11">
</td>
</tr>
<tr valign="top" style="height:20px">
<td>
</td>
<td colspan="9" style="text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 12px; line-height: 1.1748047; font-weight: bold;">State Bank of India</span></td>
<td>
</td>
</tr>
<tr valign="top" style="height:20px">
<td>
</td>
<td colspan="9" style="text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 12px; line-height: 1.1748047; font-weight: bold;">Balance Application User Details</span></td>
<td>
</td>
</tr>
<tr valign="top" style="height:21px">
<td colspan="7">
</td>
<td colspan="3" style="text-indent: 0px;  vertical-align: middle;text-align: right;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 10px; line-height: 1.1748047; font-weight: bold;">5/8/25 11:16 AM</span></td>
<td>
</td>
</tr>
<tr valign="top" style="height:20px">
<td>
</td>
<td colspan="9" style="text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 12px; line-height: 1.1748047; font-weight: bold;">Circle:- WHOLE BANK LEVEL - FRT</span></td>
<td>
</td>
</tr>
<tr valign="top" style="height:20px">
<td>
</td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 10px; line-height: 1.1748047; font-weight: bold;">Sl </span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 10px; line-height: 1.1748047; font-weight: bold;">Circle</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 10px; line-height: 1.1748047; font-weight: bold;">PFID</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 10px; line-height: 1.1748047; font-weight: bold;">Name</span></td>
<td colspan="3" style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 10px; line-height: 1.1748047; font-weight: bold;">Role</span></td>
<td colspan="2" style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 10px; line-height: 1.1748047; font-weight: bold;">Status</span></td>
<td>
</td>
</tr>
<tr valign="top" style="height:20px">
<td>
</td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">1</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Whole Bank Level - Frt</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">1010101</span></td>
<td style="border: 1px solid #000000; padding-left: 5px; text-indent: 0px;  vertical-align: middle;text-align: left;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Uat Testing</span></td>
<td colspan="3" style="border: 1px solid #000000; padding-left: 5px; text-indent: 0px;  vertical-align: middle;text-align: left;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Ifrs User</span></td>
<td colspan="2" style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Approved</span></td>
<td>
</td>
</tr>
<tr valign="top" style="height:20px">
<td>
</td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">2</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Whole Bank Level - Frt</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">0123456</span></td>
<td style="border: 1px solid #000000; padding-left: 5px; text-indent: 0px;  vertical-align: middle;text-align: left;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Ifrs_user_</span></td>
<td colspan="3" style="border: 1px solid #000000; padding-left: 5px; text-indent: 0px;  vertical-align: middle;text-align: left;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Ifrs User</span></td>
<td colspan="2" style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Approved</span></td>
<td>
</td>
</tr>
<tr valign="top" style="height:20px">
<td>
</td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">3</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Whole Bank Level - Frt</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">1234567</span></td>
<td style="border: 1px solid #000000; padding-left: 5px; text-indent: 0px;  vertical-align: middle;text-align: left;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Ifrs User</span></td>
<td colspan="3" style="border: 1px solid #000000; padding-left: 5px; text-indent: 0px;  vertical-align: middle;text-align: left;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Ifrs User</span></td>
<td colspan="2" style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Approved</span></td>
<td>
</td>
</tr>
<tr valign="top" style="height:20px">
<td>
</td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">4</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Whole Bank Level - Frt</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">0066666</span></td>
<td style="border: 1px solid #000000; padding-left: 5px; text-indent: 0px;  vertical-align: middle;text-align: left;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Ifrs_user_0066</span></td>
<td colspan="3" style="border: 1px solid #000000; padding-left: 5px; text-indent: 0px;  vertical-align: middle;text-align: left;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Ifrs User</span></td>
<td colspan="2" style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Approved</span></td>
<td>
</td>
</tr>
<tr valign="top" style="height:20px">
<td>
</td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">5</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Whole Bank Level - Frt</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">0000001</span></td>
<td style="border: 1px solid #000000; padding-left: 5px; text-indent: 0px;  vertical-align: middle;text-align: left;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Ifrs_user_001</span></td>
<td colspan="3" style="border: 1px solid #000000; padding-left: 5px; text-indent: 0px;  vertical-align: middle;text-align: left;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Ifrs User</span></td>
<td colspan="2" style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Approved</span></td>
<td>
</td>
</tr>
<tr valign="top" style="height:20px">
<td>
</td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">6</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Whole Bank Level - Frt</span></td>
<td style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">0999999</span></td>
<td style="border: 1px solid #000000; padding-left: 5px; text-indent: 0px;  vertical-align: middle;text-align: left;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Ifrs_user_001</span></td>
<td colspan="3" style="border: 1px solid #000000; padding-left: 5px; text-indent: 0px;  vertical-align: middle;text-align: left;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Ifrs User</span></td>
<td colspan="2" style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1640625;">Approved</span></td>
<td>
</td>
</tr>
<tr valign="top" style="height:20px">
<td>
</td>
<td colspan="9" style="border: 1px solid #000000; text-indent: 0px;  vertical-align: middle;text-align: center;">
<span style="font-family: 'DejaVu Serif', 'Times New Roman', Times, serif; color: #000000; font-size: 9px; line-height: 1.1748047; font-weight: bold;">Total Users : 6 &nbsp;Approved Users : 6 &nbsp;Pending Users : 0</span></td>
<td>
</td>
</tr>
<tr valign="top" style="height:29px">
<td colspan="11">
</td>
</tr>
<tr valign="top" style="height:20px">
<td colspan="6">
</td>
<td colspan="3" style="text-indent: 0px;  vertical-align: middle;text-align: right;">
<span style="font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 10px; line-height: 1.1640625;">Page 1</span></td>
<td style="text-indent: 0px;  vertical-align: middle;text-align: left;">
<span style="font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 10px; line-height: 1.1640625;">&nbsp;of 1</span></td>
<td>
</td>
</tr>
<tr valign="top" style="height:25px">
<td colspan="11">
</td>
</tr>
</table>

</td><td width="50%">&nbsp;</td></tr>
</table>
</body>
</html>

