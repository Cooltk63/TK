import React, { useState, useEffect, useMemo, useCallback } from 'react'; import { Table, TableBody, TableContainer, TableHead, TableRow, Paper, Button, Alert, Box, Snackbar, Stack, CircularProgress, Typography, Dialog, DialogTitle, DialogContent, DialogActions } from '@mui/material'; import TableCell, { tableCellClasses } from '@mui/material/TableCell'; import { styled } from '@mui/material/styles'; import lodashDebounce from 'lodash/debounce'; import FormInput from '../../../../common/components/ui/FormInput'; import useApi from '../../../../common/hooks/useApi'; import { FixedSizeList as List } from 'react-window'; import AutoSizer from 'react-virtualized-auto-sizer';

// ... Assume all your original rowDefinitionsConfig, columnDisplayHeaders, generateInitialSchedule10Data, etc., are unchanged and placed above

const StyledTableCell = styled(TableCell, { shouldForwardProp: (prop) => prop !== 'isFixedColumn' && prop !== 'isHeaderSticky' && prop !== 'headerBgColor', })(({ theme, isFixedColumn, isHeaderSticky, headerBgColor }) => ({ fontSize: '0.875rem', padding: '8px', border: '1px solid rgba(224, 224, 224, 0.13)', whiteSpace: 'nowrap', backgroundColor: theme.palette.background.paper, [&.${tableCellClasses.head}]: { backgroundColor: headerBgColor || theme.palette.grey[200], fontWeight: 'bold', textAlign: 'center', position: 'sticky', top: 0, zIndex: isFixedColumn ? 4 : 3, }, [&.${tableCellClasses.body}]: { textAlign: 'left', ...(isFixedColumn && { position: 'sticky', zIndex: 1, backgroundColor: theme.palette.background.paper, }), }, }));

const StyledTableRow = styled(TableRow)(({ theme, $istotalrow, $issectionheader, $issubsectionheader }) => ({ '&:nth-of-type(odd)': {}, ...($issectionheader && { backgroundColor: theme.palette.grey[100], '& > td, & > th': { fontWeight: 'bold', textAlign: 'left' }, }), ...($issubsectionheader && { backgroundColor: theme.palette.grey[50], '& > td, & > th': { fontWeight: 'bold', fontStyle: 'italic', textAlign: 'left' }, }), ...($istotalrow && { backgroundColor: theme.palette.grey[200], '& > td, & > th': { fontWeight: 'bold' }, }), }));

const Schedule10 = () => { const [formData, setFormData] = useState(generateInitialSchedule10Data); const [errors, setErrors] = useState({}); const [isLoading, setIsLoading] = useState(true); const [isCalculating, setIsCalculating] = useState(false); const user = useMemo(() => JSON.parse(localStorage.getItem('user')), []); const [snackbar, setSnackbar] = useState({ open: false, message: '', severity: 'success' }); const { callApi } = useApi(); const [fieldsDisabled, setFieldsDisabled] = useState(false); const [dialogOpen, setDialogOpen] = useState(false); const [dialogContent, setDialogContent] = useState({ title: '', message: '', onConfirm: () => {} }); const [openSubmitDialog, setOpenSubmitDialog] = useState(false); const [isSubmitting, setIsSubmitting] = useState(false);

useEffect(() => { setIsLoading(true); const timerId = setTimeout(() => { setFormData((prev) => ({ ...prev })); setIsLoading(false); }, 50); return () => clearTimeout(timerId); }, []);

const getNum = (value) => parseFloat(value) || 0;

const calculatedData = useMemo(() => { const newCalculatedData = JSON.parse(JSON.stringify(formData)); const p = (r, f) => getNum(newCalculatedData[r]?.[f]);

const calculateInternalRowTotals = (rowObj) => {
  if (!rowObj) return;
  const p = (fieldKey) => getNum(rowObj[fieldKey]);
  rowObj.totalA = (p('stcNstaff') + p('offResidenceA') + p('otherPremisesA') + p('electricFitting')).toFixed(2);
  rowObj.compSoftwareTotal = (p('compSoftwareInt') + p('compSoftwareNonint')).toFixed(2);
  const otherMachineryPlantVal = p('offResidenceB') + p('stcLho') + p('otherPremisesB');
  rowObj.otherMachineryPlant = otherMachineryPlantVal.toFixed(2);
  rowObj.totalB = (p('computers') + getNum(rowObj.compSoftwareTotal) + p('motor') + otherMachineryPlantVal).toFixed(2);
  rowObj.totalFurnFix = (getNum(rowObj.totalA) + getNum(rowObj.totalB)).toFixed(2);
  const premisTotalVal = p('landNotRev') + p('landRev') + p('offBuildNotRev') + p('offBuildRev') + p('residQuartNotRev') + p('residQuartRev');
  rowObj.premisTotal = premisTotalVal.toFixed(2);
  const revTotalVal = p('landRevEnh') + p('offBuildRevEnh') + p('residQuartRevEnh');
  rowObj.revtotal = revTotalVal.toFixed(2);
  rowObj.totalC = (premisTotalVal + revTotalVal).toFixed(2);
  rowObj.grandTotal = (getNum(rowObj.totalA) + getNum(rowObj.totalB) + getNum(rowObj.totalC) + p('premisesUnderCons')).toFixed(2);
};

rowDefinitionsConfig.forEach((rowDef) => {
  if (!newCalculatedData[rowDef.id]) {
    newCalculatedData[rowDef.id] = {};
    schedule10DataFields.forEach((key) => {
      newCalculatedData[rowDef.id][key] = '0.00';
    });
  }
  if (rowDef.type === 'entry' && formData[rowDef.id]) {
    Object.keys(formData[rowDef.id]).forEach((key) => {
      if (schedule10DataFields.includes(key) && !intraRowCalculatedFields.includes(key)) {
        newCalculatedData[rowDef.id][key] = formData[rowDef.id][key];
      }
    });
  }
});

rowDefinitionsConfig.forEach((rowDef) => {
  if (rowDef.type === 'entry') {
    calculateInternalRowTotals(newCalculatedData[rowDef.id]);
  }
});

rowDefinitionsConfig.forEach((rowDef) => {
  if (rowDef.type === 'total') {
    const target = newCalculatedData[rowDef.id];
    schedule10DataFields.forEach((fieldKey) => {
      let value = 0;
      if (rowDef.operation === 'sum') {
        rowDef.subItemIds.forEach((subId) => {
          value += getNum(newCalculatedData[subId]?.[fieldKey]);
        });
      } else if (rowDef.operation === 'subtract') {
        value = getNum(newCalculatedData[rowDef.subItemIds[0]]?.[fieldKey]) - getNum(newCalculatedData[rowDef.subItemIds[1]]?.[fieldKey]);
      } else if (rowDef.operation === 'subtract_special_IIii_Eii') {
        value = getNum(newCalculatedData['row9']?.[fieldKey]) - getNum(newCalculatedData['row24']?.[fieldKey]);
      } else if (rowDef.operation === 'custom_H_minus_IplusJ') {
        value = getNum(newCalculatedData['row30']?.[fieldKey]) - (getNum(newCalculatedData['row31']?.[fieldKey]) + getNum(newCalculatedData['row35']?.[fieldKey]));
      }
      target[fieldKey] = value.toFixed(2);
    });
    calculateInternalRowTotals(target);
  }
});
return newCalculatedData;

}, [formData]);

const debouncedSetFormData = useCallback(lodashDebounce((rowId, fieldKey, val) => { setIsCalculating(true); setFormData((prev) => { const newRow = { ...(prev[rowId] || {}), [fieldKey]: val }; return { ...prev, [rowId]: newRow }; }); }, 300), []);

const handleChange = (rowId, fieldKey, value) => { setFormData((prev) => ({ ...prev, [rowId]: { ...(prev[rowId] || {}), [fieldKey]: value } })); debouncedSetFormData(rowId, fieldKey, value); };

const handleBlur = (rowId, fieldKey, value) => { const fieldKeyOnly = fieldKey; const numericRegex = /^-?\d*.?\d{0,2}$/; let error = ''; if (value !== '' && value !== '-' && !numericRegex.test(value)) error = 'Invalid number'; setErrors((prev) => ({ ...prev, [${rowId}-${fieldKeyOnly}]: error })); };

if (isLoading) { return <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '80vh' }}><CircularProgress /><Typography sx={{ ml: 2 }}>Loading Schedule 10...</Typography></Box>; }

return ( <Box sx={{ p: 1 }}> <TableContainer component={Paper} sx={{ maxHeight: 'calc(100vh - 200px)' }}> <Table stickyHeader> <TableHead> {/* Your table head remains unchanged */} </TableHead> <TableBody> <AutoSizer disableWidth> {({ height }) => ( <List height={height} itemCount={rowDefinitionsConfig.length} itemSize={80} width="100%"> {({ index, style }) => { const rowDef = rowDefinitionsConfig[index]; const rowKey = rowDef.id; const displayDataForRow = calculatedData[rowDef.id] || {}; const currentFormDataForRow = formData[rowDef.id] || {};

if (rowDef.type === 'sectionHeader' || rowDef.type === 'subSectionHeader') {
                  return (
                    <StyledTableRow key={rowKey} style={style} $issectionheader $issubsectionheader>
                      <StyledTableCell>{rowDef.srNo || ''}</StyledTableCell>
                      <StyledTableCell>{typeof rowDef.label === 'function' ? rowDef.label(formData) : rowDef.label}</StyledTableCell>
                    </StyledTableRow>
                  );
                }

                return (
                  <StyledTableRow key={rowKey} style={style} $istotalrow={rowDef.isTotalRowStyle}>
                    <StyledTableCell><b>{rowDef.srNo || ''}</b></StyledTableCell>
                    <StyledTableCell><b>{typeof rowDef.label === 'function' ? rowDef.label(formData) : rowDef.label}</b></StyledTableCell>
                    {columnDisplayHeaders.map((colDef) => {
                      const fieldKey = colDef.dataField;
                      const cellKey = `${rowKey}-${fieldKey}`;
                      const isReadOnly = rowDef.type === 'total' || colDef.isCalculated || (rowDef.isReadOnlyGroup && rowDef.isReadOnlyGroup.includes(fieldKey));
                      const value = displayDataForRow[fieldKey] || '0.00';
                      const error = errors[cellKey];
                      return (
                        <StyledTableCell key={cellKey}>
                          <FormInput
                            name={cellKey}
                            value={value}
                            onChange={isReadOnly ? undefined : (e) => handleChange(rowKey, fieldKey, e.target.value)}
                            onBlur={isReadOnly ? undefined : (e) => handleBlur(rowKey, fieldKey, e.target.value)}
                            readOnly={isReadOnly || fieldsDisabled}
                            error={!!error}
                            helperText={error}
                          />
                        </StyledTableCell>
                      );
                    })}
                  </StyledTableRow>
                );
              }}
            </List>
          )}
        </AutoSizer>
      </TableBody>
    </Table>
  </TableContainer>
</Box>

); };

export default Schedule10;

