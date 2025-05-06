import React, { useState, useEffect } from 'react';
import { Box, Paper, useTheme } from '@mui/material';
import useApi from '../../common/hooks/useApi';
import CustomizedDataGrid from '../../common/components/ui/CustomizedDataGrid';
import { ViewButton, CreateButton } from '../../common/components/ui/Buttons';
import Chip from '@mui/material/Chip';
import _ from 'lodash';
import { useNavigate } from 'react-router-dom';
import { CIRCLE_MAKER_REPORT_ROUTES } from '../../common/constants/RoutesConstants';

// Main Worklist Component
const Worklist = () => {
  const { callApi } = useApi();
  const navigate = useNavigate();
  const [reports, setReports] = useState([]);
  const theme = useTheme();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const responseData = await callApi(
          '/Maker/getCircleMakerWorklist',
          {
            userId: 'Ot8DorIJK6+qObfKwkxJMQ==',
            token:
              '0b6KGqWJGylD+5vKS9zFjxiOpIn5+VQGJMWKxPAdl8qZUDmEb4jrF23SHc29Mwmz3viAJRmB881nKmhEXM9d2fCw4WlKO8GzzQtA+cwilEMfT2SoAebJgo4sevLJfOZXfl7g0g9/Jabb4cq5DrV1hxppOB/GPhw+s+VdeyVMOL5xd2sjvXwBno+93vdfldfAMB1vWSXKsI9RjbaGmHLshExBgaACzlaBj23vvGdWLBA6qNMIjso7xIg1rZ3FGUduLxvV6EqCjqikV0GuRGmrLtIFjiUjHCF2Nh13h74oVkrCXr9sSBzxTjcTsgSFfb+WYZZP4LrfCpcXiXukpmHIgljZyliMoVY7saERGmfQpQARCuACsrXtottPoKNPpgCuCixtrEc0/sbU5lvQOLyjgVjT8udPXYWh0xMwTsXOfPZ387u9AiMGGbhGvLBbntXuETdEarN9PWp9BG1q7InETl4KMCYeanTtrDoWoUFu917gGPgqJgP5j02Slk/fE1q8SsLqAu9XAh61Dn6DA4O6PADA+RZQAhCh0PuLR55cFt924YjNqJHt8L0ZQpKUcinHwrg4F8T5IDNCFLY3ZlJW2uivM3HhpssXtxbHECXuPmmA2QuEk2nrBy8Mx0114tUFES3YA/aNj81GpKcpU4bBjK6w5XMTkL2pAJxj+WMbGMyc0+1imJdoP96nGH3DZZRDMl9yJaj5P/vf3dcyZTfR8Nxwvh5sPa/3I5SNLwq0CQrCoDikyeclrKuGT3Os7LN/6uJgGbWgRaTtVgxD81w7SFp/omK3nPZ6YOsmFffBoUU0G7k4Fpm9Q1fHVK6/9kXa',
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
          },
          'POST'
        );

        if (responseData && responseData.length > 0) {
          setReports(responseData);
          console.log(responseData);
        } else {
          console.log('No response data');
        }
      } catch (error) {
        console.error('Error fetching Circle Maker Worklist:', error.message);
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
      flex: 1.5,
    },
    {
      field: 'checkerRemarks',
      headerName: 'Checker Remarks',
      headerAlign: 'center', 
      align: 'center' ,
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
      align: 'center' ,
      flex: 1.5,
      hideable: false,
      renderCell: (params) => renderStatus(params.value),
    },
    {
      field: 'actions',
      headerName: 'Action',
      headerAlign: 'center', 
      align: 'center' ,
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
          <ViewButton
            onClickHandler={() => {
              console.log(params.row, 'clicked on view');
            }}
          />
          <CreateButton
            onClickHandler={() => {
              handleCreate(params.row);
            }}
          />
        </Box>
      ),
    },
  ];

  const handleCreate = (report) => {
    CIRCLE_MAKER_REPORT_ROUTES[report.reportMasterId]
      ? navigate('./' + CIRCLE_MAKER_REPORT_ROUTES[report.reportMasterId], { state: { report } })
      : console.log('Invalid ReportMasterID');
  };

  return (
    <Paper sx={{ width: '100%'}}>
      <CustomizedDataGrid rows={reports} columns={columns} getRowId={(row) => row.reportMasterId} />
    </Paper>
  );
};

export default Worklist;
