I need the on button click call the backend API/Admin/viewReportJrxmlCircle  which sent BLOB as response and display the BLOB inside some model below table container alredy written using the MUI version 5.0 or above react JS from backend recieved the blob as byte[] so make changes accordingly

Alredy written code as per below please make the modification which wont affect the existing written functionality but for enchancement & performance make changes without getting business logic change also 

I also need the on PDF Icon button lick call the API /Admin/viewReportJrxmlCircle with param as I have alredy mentioned inside actionHandler methods payload variable 
also on same for exceldownload button click 'DescriptionIcon' call the backend with /Admin/viewReportJrxmlCircle with parameter mentioned already on button just make sure no other changes required in code & everthing with the proper explained comments


import  React, { useState,useEffect } from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Button} from '@mui/material';
import useApi from "../../../common/hooks/useApi"
import {
  Close as CloseIcon,
  Visibility as VisibilityIcon,
  PictureAsPdf as PictureAsPdfIcon,
  Description as DescriptionIcon, // Generic for Excel/Text or use more specific ones
  WarningAmber as WarningIcon,
  CenterFocusStrong,
} from '@mui/icons-material';

const loginUser = JSON.parse(localStorage.getItem('user'));


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
  // hide last border
  '&:last-child td, &:last-child th': {
    border: 0,
  },
}));

function createData(name, calories, fat, carbs, protein) {
  return { name, calories, fat, carbs, protein };
}










const IFRSDownloadReport = () => {

  const loadData = async () => {
    try{
      
      const responseData = await callApi('/Admin/getListOfReports', loginUser, 'POST');
      return responseData;
    } catch (error) {
      console.error('error :: ',error);
    }
  }

  const {data,error,isLoading,callApi} = useApi()
  const [rows , setRows] = useState([]);
  const [recivedData , setRecivedData] = useState({});
  useEffect(() => {
    loadData().then((data)=>{
      console.log(data)
      setRecivedData(data)
      setRows(data.list1)
    })},[]);

  const actionHandler = (type, data) => {
    let payload = {
      dash_suppresed: null,
      isSuppresed: false,
      report: data,
      user: loginUser
    }
    // Need CODE for Button Clicked Here


  }


  return (
    <TableContainer component={Paper}>
      <Table aria-label="customized table">
        <TableHead>
          <TableRow >
            <StyledTableCell >Report Name</StyledTableCell>
            <StyledTableCell colSpan={3} sx={{textAlign:'center'}} >Actions</StyledTableCell>
            
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <StyledTableRow key={row.dash_name}>
              <StyledTableCell component="th" scope="row">
                {row.dash_name ? row.dash_name : "HELLO"}
              </StyledTableCell>
              <StyledTableCell>
                {(
                  <Button size="small" onClick={() => actionHandler( 'view', row)}>
                    <VisibilityIcon />
                  </Button>
                )}
              </StyledTableCell>
              <StyledTableCell >
                { (
                  <Button size="small" color="error" onClick={() => actionHandler('downloadPDF', row)}>
                    <PictureAsPdfIcon />
                  </Button>
                )}
              </StyledTableCell>
              <StyledTableCell >
                {(
                  <Button size="small" color="success" onClick={() => actionHandler( 'downloadExcel', row)}>
                    <DescriptionIcon />
                  </Button>
                )}
              </StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default IFRSDownloadReport;
