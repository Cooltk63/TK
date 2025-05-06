import React, { useState, useEffect, useCallback, useMemo } from 'react';
import {
  Box,
  Container,
  Typography,
  Alert,
  IconButton,
  Checkbox,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Button,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  CircularProgress,
  Paper
} from '@mui/material';
import {
  Close as CloseIcon,
  Visibility as VisibilityIcon,
  PictureAsPdf as PictureAsPdfIcon,
  Description as DescriptionIcon, // Generic for Excel/Text or use more specific ones
  WarningAmber as WarningIcon,
} from '@mui/icons-material';
import { saveAs } from 'file-saver'; // For file downloads
import _get from 'lodash/get'; // Example of specific Lodash import

// Helper for API calls - replace with your actual API logic
// Example:
// const api = {
//   getBranchList: async (sessionUser) => Promise.resolve([]),
//   getListOfReports: async (sessionUser) => Promise.resolve({ list1: [], list: [], freezedCircles: [] }),
//   viewReportCircle: async (params) => Promise.resolve(new Blob()),
//   viewReport: async (params) => Promise.resolve(new Blob()),
// };

// Simulated sessionUser - replace with your actual session user data/context
const mockSessionUser = {
  capacity: '61', // Example, change to test different headings
  circleCode: 'ABC',
  circleName: 'ExampleCircle',
  quarterEndDate: '2024-Q1', // Example date
  // ... other user properties
};

const ReportsComponent = () => {
  const [sessionUser] = useState(mockSessionUser); // Replace with actual user context/prop
  const [heading, setHeading] = useState('');
  const [branchList, setBranchList] = useState([]);
  const [listOfRows, setListOfRows] = useState([]);
  const [listOfCircle, setListOfCircle] = useState([]);
  const [freezedCircles, setFreezedCircles] = useState([]);
  const [selectedBranchCode, setSelectedBranchCode] = useState('All Branches'); // Default or from user
  const [showDiv, setShowDiv] = useState(false);
  const [showAltMessage, setShowAltMessage] = useState(false);
  const [componentMessage, setComponentMessage] = useState('');
  const [checkCircle, setCheckCircle] = useState(false);
  const [checkCircleMessage, setCheckCircleMessage] = useState('');
  const [pdfContentUrl, setPdfContentUrl] = useState('');
  const [loading, setLoading] = useState(false);

  const [rowStates, setRowStates] = useState({}); // To manage individual row inputs like checkbox, selects

  const [csvModalOpen, setCsvModalOpen] = useState(false);
  const [csvModalParams, setCsvModalParams] = useState(null);

  // Initialize row states when listOfRows changes
  useEffect(() => {
    const initialRowStates = {};
    listOfRows.forEach((row, index) => {
      initialRowStates[index] = {
        isSuppressed: false,
        branchCode: selectedBranchCode, // Or a specific default from row if available
        type: 'PRE', // Default as in Angular
        circle: [], // For multi-select
        // ... any other per-row dynamic fields
      };
    });
    setRowStates(initialRowStates);
  }, [listOfRows, selectedBranchCode]);

  const handleRowStateChange = (index, field, value) => {
    setRowStates(prev => ({
      ...prev,
      [index]: {
        ...prev[index],
        [field]: value,
      },
    }));
  };


  // Determine heading based on sessionUser.capacity
  useEffect(() => {
    if (!sessionUser || !sessionUser.capacity) return;
    const capacity = sessionUser.capacity;
    if (capacity === '61') { //
      setHeading('4'); //
    } else {
      // Original logic: '3' for others, '1' for '61'/'62', '2' for '51'/'52'/'53'
      // This simplified to '4' for '61' and '3' for others based on the provided snippet.
      // Adjust if more capacity codes need different headings.
      // Example from JSP comments:
      // if (capacity === '61' || capacity === '62') setHeading('1');
      // else if (capacity === '52' || capacity === '51' || capacity === '53') setHeading('2');
      setHeading('3'); //
    }
  }, [sessionUser]);

  // Fetch initial data
  useEffect(() => {
    const fetchData = async () => {
      if (!sessionUser) return;
      setLoading(true);
      try {
        // Replace with your actual API calls
        // const branches = await api.getBranchList(sessionUser);
        // setBranchList(branches);

        // const reportsData = await api.getListOfReports(sessionUser);
        // Simulating API response structure from AngularJS controller
        const reportsData = { // Mock data, replace with API call
            list1: [ // Simulating listOfRows
                { dash_name: 'Report Alpha', dash_suppresed: 'Z', dash_dwnload: 'HPEZC', type: 'PRE', circle: '', branchCode: '', dash_param: 'TYPE;BRANCH_CODE;COMP', id: 'r1' },
                { dash_name: 'Report Beta', dash_suppresed: 'N', dash_dwnload: 'HPE', type: 'POST', circle: '', branchCode: '', dash_param: 'TYPE;CIRCLE_LIST', id: 'r2' },
                { dash_name: 'Report Gamma (No Suppress)', dash_suppresed: 'N', dash_dwnload: 'HPET', type: 'PRE', circle: '', branchCode: '', dash_param: 'TYPE', id: 'r3' },
                { dash_name: 'Report Delta (CSV only)', dash_suppresed: 'Z', dash_dwnload: 'C', type: 'INTERSE', circle: '', branchCode: '', dash_param: 'TYPESE;BRANCH_CODE', id: 'r4' },

            ],
            list: [ // Simulating listOfCircle
                { circleCode: 'C001', circleName: 'Circle 1' }, { circleCode: 'C002', circleName: 'Circle 2' }
            ],
            freezedCircles: [ // Simulating freezedCircles
                { circleCode: 'FC001', circleName: 'Frozen Circle 1' }
            ],
        };


        if (reportsData.list1 && reportsData.list1.length > 0) { //
          setShowDiv(true); //
          setListOfRows(reportsData.list1);
          setListOfCircle(reportsData.list || []);
          setFreezedCircles(reportsData.freezedCircles || []); //

          const listWithZ = reportsData.list1.filter(row => row.dash_suppresed === 'Z'); //
          if (listWithZ.length > 0) {
            setShowAltMessage(true); //
          } else {
            setShowAltMessage(false);
          }
          setComponentMessage('');
        } else {
          setComponentMessage('No reports are found to download.'); //
          setShowDiv(false);
        }
      } catch (error) {
        console.error('Error fetching initial data:', error);
        setComponentMessage('Failed to load reports data.');
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [sessionUser]);


  const clearCheckCircleAlert = () => { //
    setCheckCircle(false); //
    setCheckCircleMessage(''); //
  };

  const handleViewReportJrxmlCircle = async (index, action, report) => {
    if (!rowStates[index]) return;
    const currentRowState = rowStates[index];
    const { type: prePost, circle: compcircle, branchCode } = currentRowState;
    const { dash_suppresed, dash_param } = report;
    const isSuppressed = currentRowState.isSuppressed; // This should be the actual checkbox value

    // console.log(`Circle Level Action: ${action}, Report: ${report.dash_name}, Index: ${index}`); //
    // console.log(`Params: prePost=${prePost}, compcircle=${compcircle}, branchCode=${branchCode}, dash_suppressed=${dash_suppresed}, isSuppressed=${isSuppressed}`); //

    let prePostValue = ""; //
    if (dash_param && dash_param.includes('TYPE')) { //
      prePostValue = "_" + prePost; //
    }

    let saveAsName = "";
    if (!branchCode || branchCode === "All Branches") { //
      saveAsName = sessionUser.circleName ? sessionUser.circleName.substring(0, 3) : "CIR"; //
    } else if (branchCode !== "All Branches") { //
      saveAsName = branchCode; //
    }
    saveAsName += `_${sessionUser.quarterEndDate || 'QEND'}_${report.dash_name}${prePostValue}`;


    const apiParams = { //
      report,
      user: sessionUser,
      type: action, // 'view', 'downloadPDF', 'download', 'downloadCSV...'
      prePost,
      compcircle,
      branchCode, //
      dash_suppresed,
      isSuppresed: isSuppressed,
    };

    setLoading(true);
    setPdfContentUrl('');
    try {
      // const data = await api.viewReportCircle(apiParams); // Replace with actual API call
      const data = new Blob(["Mock file content for " + saveAsName], { type: 'application/octet-stream' }); // Mock

      if (action === "view") { //
        const fileType = report.dash_name.toLowerCase().includes('html') ? 'text/html' : 'application/pdf'; // Example logic
        const file = new Blob([data], { type: fileType }); //
        const url = URL.createObjectURL(file); //
        setPdfContentUrl(url); //
        // Forcing download for html as in angular code for msSaveOrOpenBlob
        if (window.navigator && window.navigator.msSaveOrOpenBlob && fileType === 'text/html') {
            window.navigator.msSaveOrOpenBlob(file, saveAsName + ".html"); //
        }
      } else if (action === "downloadPDF") { //
        const file1 = new Blob([data], { type: 'application/pdf' }); //
        saveAs(file1, saveAsName + ".pdf"); //
      } else if (action === "download") { // Excel
        const file2 = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
        saveAs(file2, saveAsName + ".xlsx"); //
      } else if (action && action.startsWith("downloadCSV")) { //
        const extensionType = action.substring(11, 12); //
        let filetype = 'application/text/plain;charset=utf-8'; //
        let extension = ".txt"; //
        if (extensionType === "C") { //
            // filetype remains text/plain based on original
        }
        const file3 = new Blob([data], { type: filetype });
        saveAs(file3, saveAsName + extension); //
      }
    } catch (error) {
      console.error('Error in viewReportJrxmlCircle:', error);
      setCheckCircle(true);
      setCheckCircleMessage('Error processing report request.');
    } finally {
      setLoading(false);
    }
  };

  const handleViewReportJrxml = async (index, action, report) => {
    if (!rowStates[index]) return;
    const currentRowState = rowStates[index];
    const { type: prePost, circle: compcircle } = currentRowState;
    const { dash_suppresed, dash_param } = report;
    const isSuppressed = currentRowState.isSuppressed;

    // console.log(`FRT Level Action: ${action}, Report: ${report.dash_name}, Index: ${index}`); //
    setCheckCircle(false); //
    setPdfContentUrl('');

    let prePostValue = ""; //
    if (dash_param && dash_param.includes('TYPE')) { //
        prePostValue = "_" + prePost; //
    }

    const saveAsName = `${sessionUser.circleName ? sessionUser.circleName.substring(0, 3) : "FRT"}_${sessionUser.quarterEndDate || 'QEND'}_${report.dash_name}${prePostValue}`;


    if (compcircle === undefined && dash_param && dash_param.includes('COMP')) { //
      setCheckCircleMessage("Please select circle"); //
      setCheckCircle(true); //
      return;
    }
    if (compcircle === undefined && dash_param && dash_param.includes('CIRCLE_LIST') && freezedCircles.length > 0) { //
        setCheckCircleMessage("Please select circle"); //
        setCheckCircle(true); //
        return;
    } else if (freezedCircles.length === 0 && dash_param && dash_param.includes('CIRCLE_LIST')) { //
        setCheckCircleMessage("No circle has been freezed to generate Consolidation Report"); //
        setCheckCircle(true); //
        return;
    }


    const apiParams = { //
      report,
      user: sessionUser,
      type: action,
      prePost,
      compcircle: Array.isArray(compcircle) ? compcircle.join(',') : compcircle, // Assuming API expects comma-separated if multiple
      dash_suppresed, //
      isSuppresed: isSuppressed, //
    };

    setLoading(true);
    try {
      // const data = await api.viewReport(apiParams); // Replace with actual API call
      const data = new Blob(["Mock file content for " + saveAsName], { type: 'application/octet-stream' }); // Mock

      if (action === "view") { //
        const file = new Blob([data], { type: 'application/pdf' }); //
        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
            window.navigator.msSaveOrOpenBlob(file, saveAsName + ".pdf"); //
        } else {
            const url = URL.createObjectURL(file);
            setPdfContentUrl(url); //
        }
      } else if (action === "downloadPDF") { //
        const file1 = new Blob([data], { type: 'application/pdf' }); //
        saveAs(file1, saveAsName + ".pdf"); //
      } else if (action === "download") { // Excel
        const file2 = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
        saveAs(file2, saveAsName + ".xlsx"); //
      }
    } catch (error) {
      console.error('Error in viewReportJrxml:', error);
      setCheckCircle(true);
      setCheckCircleMessage('Error processing report request.');
    } finally {
      setLoading(false);
    }
  };

  const handleCheckCsv = (index, action, report) => { //
    if (!rowStates[index]) return;
    const currentRowState = rowStates[index];
    const { type: prePost, circle: compcircle, branchCode } = currentRowState;
    const { dash_suppresed, dash_param } = report;
    const isSuppressed = currentRowState.isSuppressed;

    setCsvModalParams({ index, action, prePost, compcircle, branchCode, dash_suppresed, isSuppressed, dash_param, report }); //
    setCsvModalOpen(true); //
  };

  const handleCsvModalContinue = () => {
    if (csvModalParams) {
      const { index, action, report } = csvModalParams;
      // The original Angular code calls viewReportJrxmlCircle from the modal's continue button.
      handleViewReportJrxmlCircle(index, action, report); //
    }
    setCsvModalOpen(false);
  };


  const renderTableHeaders = () => {
    if (heading === '3') { //
      return (
        <TableRow>
          <TableCell>Report Name</TableCell> {/* */}
          <TableCell>View</TableCell> {/* */}
          <TableCell></TableCell> {/* */}
          <TableCell></TableCell> {/* */}
          <TableCell></TableCell> {/* */}
          <TableCell></TableCell> {/* */}
          <TableCell>PRE/POST</TableCell> {/* */}
        </TableRow>
      );
    }
    if (heading === '5') { //
      return (
        <TableRow>
          <TableCell>Report Name</TableCell> {/* */}
          <TableCell>View</TableCell> {/* */}
          <TableCell></TableCell> {/* */}
          <TableCell></TableCell> {/* */}
          <TableCell></TableCell> {/* */}
          <TableCell></TableCell> {/* */}
          <TableCell></TableCell> {/* */}
        </TableRow>
      );
    }
    // Default for heading '1', '2', '4' (as per original structure, '2' and '4' are main ones with options)
    // This combines logic for heading '2' and '1'/'4' which have similarities
    const isHeading2 = heading === '2';
    const isHeading1Or4 = heading === '1' || heading === '4';

    return (
      <TableRow>
        <TableCell>Report Name</TableCell> {/* */}
        <TableCell>View</TableCell> {/* */}
        <TableCell></TableCell> {/* */}
        <TableCell></TableCell> {/* */}
        <TableCell></TableCell> {/* */}
        {isHeading2 && <TableCell></TableCell>} {/* For CSV button space */}
        {isHeading2 && <TableCell>Circle/Branch</TableCell>} {/* */}
        <TableCell>PRE/POST</TableCell> {/* */}
        {isHeading2 && <TableCell>CompCode</TableCell>} {/* */}
        {isHeading1Or4 && heading === '1' && <TableCell>Circles</TableCell>} {/* */}
      </TableRow>
    );
  };

  const renderTableRows = () => { //
    return listOfRows.map((row, index) => {
      if (!rowStates[index]) return null; // Should be initialized
      const currentRowState = rowStates[index];
      const dashDwnload = row.dash_dwnload || "";
      const dashParam = row.dash_param || "";

      const showViewButton = dashDwnload.includes('H'); //
      const showPdfButton = dashDwnload.includes('P'); //
      const showExcelButton = dashDwnload.includes('E'); //
      const showCsvButton = dashDwnload.includes('T') || dashDwnload.includes('C'); //

      const actionHandler = (heading === '2' || heading === '4') ? handleViewReportJrxmlCircle : handleViewReportJrxml;

      return (
        <TableRow key={row.id || index}> {/* */}
          <TableCell>{row.dash_name}</TableCell>
          <TableCell sx={{width: '4%'}}>
            {row.dash_suppresed === 'Z' && ( //
              <Checkbox
                checked={!!currentRowState.isSuppressed} //
                onChange={(e) => handleRowStateChange(index, 'isSuppressed', e.target.checked)} //
              />
            )}
          </TableCell>

          {/* View, PDF, Excel Buttons common logic pattern*/}
          {/* For heading '2' or '4' */}
          {(heading === '2' || heading === '4') && (
            <>
              <TableCell sx={{width: '4%'}}>
                {showViewButton && <Button onClick={() => actionHandler(index, 'view', row)}><VisibilityIcon /></Button>} {/* */}
              </TableCell>
              <TableCell sx={{width: '4%'}}>
                {showPdfButton && <Button color="error" onClick={() => actionHandler(index, 'downloadPDF', row)}><PictureAsPdfIcon /></Button>} {/* */}
              </TableCell>
              <TableCell sx={{width: '4%'}}>
                {showExcelButton && <Button color="success" onClick={() => actionHandler(index, 'download', row)}><DescriptionIcon /></Button>} {/* */}
              </TableCell>
            </>
          )}
          {/* For CSV Button (heading '2' only) */}
          {heading === '2' && (
            <TableCell sx={{width: '4%'}}>
              {showCsvButton && <Button color="success" onClick={() => handleCheckCsv(index, `downloadCSV${dashDwnload.includes('C') ? 'C' : 'T'}`, row)}><DescriptionIcon /></Button>} {/* */}
            </TableCell>
          )}

          {/* For heading '1' or '5' (FRT Level) */}
          {(heading === '1' || heading === '5') && (
             <>
              <TableCell sx={{width: '4%'}}>
                {showViewButton && <Button onClick={() => actionHandler(index, 'view', row)}><VisibilityIcon /></Button>} {/* */}
              </TableCell>
              <TableCell sx={{width: '4%'}}>
                {showPdfButton && <Button color="error" onClick={() => actionHandler(index, 'downloadPDF', row)}><PictureAsPdfIcon /></Button>} {/* */}
              </TableCell>
              <TableCell sx={{width: '4%'}}>
                {showExcelButton && <Button color="success" onClick={() => actionHandler(index, 'download', row)}><DescriptionIcon /></Button>} {/* */}
              </TableCell>
               {/* Placeholder for structure if heading 5 had more/different buttons */}
               {heading === '5' && (<>
                <TableCell></TableCell> {/* Corresponds to an empty th in JSP */}
                <TableCell></TableCell> {/* Corresponds to an empty th in JSP */}
               </>)}
            </>
          )}


          {/* Conditional inputs based on heading */}
          {heading === '2' && ( // Circle/Branch, PRE/POST, CompCode for heading '2'
            <>
              <TableCell> {/* Circle/Branch Dropdown */} {/* */}
                {dashParam.includes('BRANCH_CODE') && ( //
                  <FormControl fullWidth size="small">
                    <InputLabel>Branch</InputLabel>
                    <Select
                      value={currentRowState.branchCode || ''} //
                      onChange={(e) => handleRowStateChange(index, 'branchCode', e.target.value)} //
                      required //
                    >
                      {/* <MenuItem value="All Branches">All Branches</MenuItem> */}
                      {branchList.map(branch => ( //
                        <MenuItem key={branch.branchCode} value={branch.branchCode}>{branch.branchCode}</MenuItem>
                      ))}
                    </Select