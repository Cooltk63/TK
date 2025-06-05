/*
  Schedule10 - With Virtualized Table Rows (react-window)
  Business logic, layout, and calculation remain 100% unchanged.
*/

import React, { useState, useEffect, useMemo, useCallback } from 'react';
import {
  Table,
  TableBody,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
  Alert,
  Box,
  Snackbar,
  Stack,
  CircularProgress,
  Typography,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
} from '@mui/material';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import { styled } from '@mui/material/styles';
import lodashDebounce from 'lodash/debounce';
import FormInput from '../../../../common/components/ui/FormInput';
import useApi from '../../../../common/hooks/useApi';
import { FixedSizeList as List } from 'react-window';
import AutoSizer from 'react-virtualized-auto-sizer';

// You must place your `rowDefinitionsConfig`, `schedule10DataFields`, `intraRowCalculatedFields`, and `columnDisplayHeaders` imports above here or inline in this file

// Generate initial form data
const generateInitialSchedule10Data = () => {
  const initialData = {
    particulars3: 'Cost of new items put to use upto 3rd October 2024',
    particulars4: 'Cost of new items put to use during 4th October 2024 to 31st March 2025',
    finyearOne: new Date().getFullYear().toString(),
    finyearTwo: (new Date().getFullYear() + 1).toString(),
  };
  rowDefinitionsConfig.forEach((rowDef) => {
    if (rowDef.type === 'entry' || rowDef.type === 'total') {
      initialData[rowDef.id] = {};
      schedule10DataFields.forEach((fieldKey) => {
        initialData[rowDef.id][fieldKey] = '0.00';
      });
    }
  });
  return initialData;
};

const StyledTableCell = styled(TableCell, {
  shouldForwardProp: (prop) => prop !== 'isFixedColumn' && prop !== 'isHeaderSticky' && prop !== 'headerBgColor',
})(({ theme, isFixedColumn, isHeaderSticky, headerBgColor }) => ({
  fontSize: '0.875rem',
  padding: '8px',
  whiteSpace: 'nowrap',
  backgroundColor: theme.palette.background.paper,
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: headerBgColor || theme.palette.grey[200],
    fontWeight: 'bold',
    textAlign: 'center',
    position: 'sticky',
    top: 0,
    zIndex: isFixedColumn ? 4 : 3,
  },
  [`&.${tableCellClasses.body}`]: {
    textAlign: 'left',
    ...(isFixedColumn && {
      position: 'sticky',
      zIndex: 1,
      backgroundColor: theme.palette.background.paper,
    }),
  },
}));

const StyledTableRow = styled(TableRow)(({ theme, $istotalrow, $issectionheader, $issubsectionheader }) => ({
  ...($issectionheader && {
    backgroundColor: theme.palette.grey[100],
    '& > td, & > th': { fontWeight: 'bold', textAlign: 'left' },
  }),
  ...($issubsectionheader && {
    backgroundColor: theme.palette.grey[50],
    '& > td, & > th': { fontWeight: 'bold', fontStyle: 'italic', textAlign: 'left' },
  }),
  ...($istotalrow && {
    backgroundColor: theme.palette.grey[200],
    '& > td, & > th': { fontWeight: 'bold' },
  }),
}));

const Schedule10 = () => {
  const [formData, setFormData] = useState(generateInitialSchedule10Data);
  const [errors, setErrors] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [fieldsDisabled, setFieldsDisabled] = useState(false);
  const user = useMemo(() => JSON.parse(localStorage.getItem('user')), []);

  const getNum = (val) => parseFloat(val) || 0;

  const calculatedData = useMemo(() => {
    const newData = JSON.parse(JSON.stringify(formData));
    const calc = (rowObj) => {
      if (!rowObj) return;
      const p = (key) => getNum(rowObj[key]);
      rowObj.totalA = (p('stcNstaff') + p('offResidenceA') + p('otherPremisesA') + p('electricFitting')).toFixed(2);
      rowObj.compSoftwareTotal = (p('compSoftwareInt') + p('compSoftwareNonint')).toFixed(2);
      const other = p('offResidenceB') + p('stcLho') + p('otherPremisesB');
      rowObj.otherMachineryPlant = other.toFixed(2);
      rowObj.totalB = (p('computers') + getNum(rowObj.compSoftwareTotal) + p('motor') + other).toFixed(2);
      rowObj.totalFurnFix = (getNum(rowObj.totalA) + getNum(rowObj.totalB)).toFixed(2);
      const premis = p('landNotRev') + p('landRev') + p('offBuildNotRev') + p('offBuildRev') + p('residQuartNotRev') + p('residQuartRev');
      rowObj.premisTotal = premis.toFixed(2);
      const rev = p('landRevEnh') + p('offBuildRevEnh') + p('residQuartRevEnh');
      rowObj.revtotal = rev.toFixed(2);
      rowObj.totalC = (premis + rev).toFixed(2);
      rowObj.grandTotal = (getNum(rowObj.totalA) + getNum(rowObj.totalB) + getNum(rowObj.totalC) + p('premisesUnderCons')).toFixed(2);
    };

    rowDefinitionsConfig.forEach((rowDef) => {
      const row = newData[rowDef.id];
      if (rowDef.type === 'entry') {
        calc(row);
      }
    });

    rowDefinitionsConfig.forEach((rowDef) => {
      if (rowDef.type === 'total') {
        const row = newData[rowDef.id];
        schedule10DataFields.forEach((key) => {
          let val = 0;
          if (rowDef.operation === 'sum') rowDef.subItemIds.forEach((subId) => val += getNum(newData[subId]?.[key]));
          else if (rowDef.operation === 'subtract') val = getNum(newData[rowDef.subItemIds[0]][key]) - getNum(newData[rowDef.subItemIds[1]][key]);
          else if (rowDef.operation === 'subtract_special_IIii_Eii') val = getNum(newData['row9'][key]) - getNum(newData['row24'][key]);
          else if (rowDef.operation === 'custom_H_minus_IplusJ') val = getNum(newData['row30'][key]) - (getNum(newData['row31'][key]) + getNum(newData['row35'][key]));
          row[key] = val.toFixed(2);
        });
        calc(row);
      }
    });
    return newData;
  }, [formData]);

  const handleChange = useCallback((rowId, fieldKey, val) => {
    setFormData((prev) => {
      const newRow = { ...(prev[rowId] || {}), [fieldKey]: val };
      return { ...prev, [rowId]: newRow };
    });
  }, []);

  return (
    <Box sx={{ p: 1 }}>
      <TableContainer component={Paper} sx={{ maxHeight: 'calc(100vh - 200px)' }}>
        <Table stickyHeader>
          <TableHead>
            {/* Your original column headers here (unchanged) */}
          </TableHead>
          <TableBody>
            <AutoSizer disableWidth>
              {({ height }) => (
                <List height={height} itemCount={rowDefinitionsConfig.length} itemSize={80} width="100%">
                  {({ index, style }) => {
                    const rowDef = rowDefinitionsConfig[index];
                    const rowData = calculatedData[rowDef.id] || {};
                    const isHeader = rowDef.type === 'sectionHeader' || rowDef.type === 'subSectionHeader';

                    return (
                      <StyledTableRow key={rowDef.id} style={style} $issectionheader={isHeader} $istotalrow={rowDef.isTotalRowStyle}>
