import React, { useState } from 'react';
import {
  Container, Typography, Box, Alert, IconButton, Table, TableHead,
  TableRow, TableCell, TableBody, Checkbox, Button, Select, MenuItem,
  FormControl, InputLabel, OutlinedInput, Stack
} from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import VisibilityIcon from '@mui/icons-material/Visibility';
import PictureAsPdfIcon from '@mui/icons-material/PictureAsPdf';
import FileDownloadIcon from '@mui/icons-material/FileDownload';
import TextSnippetIcon from '@mui/icons-material/TextSnippet';

const ReportsPage = ({ csrfToken, heading, listOfRows, branchList, listOfCircle }) => {
  const [checkcircle, setCheckcircle] = useState(false);
  const [checkcircleMessage, setCheckcircleMessage] = useState('');
  const [showAltMessage, setShowAltMessage] = useState(false);
  const [message, setMessage] = useState('Reports List');

  const handleChangeRowField = (index, field, value) => {
    listOfRows[index][field] = value;
  };

  return (
    <Box sx={{ pt: 4 }}>
      {checkcircle && (
        <Alert
          severity="warning"
          action={
            <IconButton onClick={() => setCheckcircle(false)} size="small">
              <CloseIcon fontSize="inherit" />
            </IconButton>
          }
          sx={{ mb: 2 }}
        >
          <strong>{checkcircleMessage}</strong>
        </Alert>
      )}

      <Container>
        <Typography variant="h4" gutterBottom>{message}</Typography>

        {showAltMessage && (
          <Typography color="error" variant="body1" sx={{ mb: 2 }}>
            Please select the checkbox for zero-suppressed report.
          </Typography>
        )}

        <input type="hidden" value={csrfToken} />

        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Report Name</TableCell>
              <TableCell>Suppressed</TableCell>
              <TableCell>View</TableCell>
              <TableCell>PDF</TableCell>
              <TableCell>Excel</TableCell>
              <TableCell>CSV</TableCell>
              {heading === '2' && <TableCell>Branch</TableCell>}
              <TableCell>PRE/POST</TableCell>
              {heading === '2' && <TableCell>CompCode</TableCell>}
              {heading === '1' && <TableCell>Circles</TableCell>}
            </TableRow>
          </TableHead>
          <TableBody>
            {listOfRows.map((row, index) => (
              <TableRow key={index}>
                <TableCell>{row.dash_name}</TableCell>

                <TableCell>
                  {row.dash_suppresed === 'Z' && (
                    <Checkbox
                      checked={row.isSuppresed || false}
                      onChange={(e) => handleChangeRowField(index, 'isSuppresed', e.target.checked)}
                    />
                  )}
                </TableCell>

                <TableCell>
                  {row.dash_dwnload.includes('H') && (
                    <IconButton color="primary" onClick={() => { /* View logic */ }}>
                      <VisibilityIcon />
                    </IconButton>
                  )}
                </TableCell>

                <TableCell>
                  {row.dash_dwnload.includes('P') && (
                    <IconButton color="error" onClick={() => { /* Download PDF */ }}>
                      <PictureAsPdfIcon />
                    </IconButton>
                  )}
                </TableCell>

                <TableCell>
                  {row.dash_dwnload.includes('E') && (
                    <IconButton color="success" onClick={() => { /* Download Excel */ }}>
                      <FileDownloadIcon />
                    </IconButton>
                  )}
                </TableCell>

                <TableCell>
                  {(row.dash_dwnload.includes('T') || row.dash_dwnload.includes('C')) && (
                    <IconButton color="info" onClick={() => { /* Download CSV */ }}>
                      <TextSnippetIcon />
                    </IconButton>
                  )}
                </TableCell>

                {heading === '2' && (
                  <TableCell>
                    {row.dash_param.includes('BRANCH_CODE') && (
                      <FormControl fullWidth size="small">
                        <InputLabel>Branch</InputLabel>
                        <Select
                          value={row.branchCode || ''}
                          onChange={(e) => handleChangeRowField(index, 'branchCode', e.target.value)}
                          label="Branch"
                        >
                          {branchList.map((branch) => (
                            <MenuItem key={branch.branchCode} value={branch.branchCode}>
                              {branch.branchCode}
                            </MenuItem>
                          ))}
                        </Select>
                      </FormControl>
                    )}
                  </TableCell>
                )}

                <TableCell>
                  {row.dash_param.includes('TYPE') && (
                    <FormControl fullWidth size="small">
                      <InputLabel>Type</InputLabel>
                      <Select
                        value={row.type || 'PRE'}
                        onChange={(e) => handleChangeRowField(index, 'type', e.target.value)}
                        label="Type"
                      >
                        <MenuItem value="PRE">PRE</MenuItem>
                        <MenuItem value="POST">POST</MenuItem>
                        {row.dash_param.includes('TYPESE') && (
                          <MenuItem value="INTERSE">INTERSE</MenuItem>
                        )}
                      </Select>
                    </FormControl>
                  )}
                </TableCell>

                {heading === '2' && (
                  <TableCell>
                    {row.dash_param.includes('COMP') && (
                      <FormControl fullWidth size="small">
                        <InputLabel>Circle</InputLabel>
                        <Select
                          multiple
                          value={row.circle || []}
                          onChange={(e) => handleChangeRowField(index, 'circle', e.target.value)}
                          input={<OutlinedInput label="Circle" />}
                        >
                          {listOfCircle.map((circle) => (
                            <MenuItem key={circle.circleCode} value={circle.circleCode}>
                              {circle.circleName}
                            </MenuItem>
                          ))}
                        </Select>
                      </FormControl>
                    )}
                  </TableCell>
                )}

                {heading === '1' && (
                  <TableCell>
                    {row.dash_param.includes('COMP') && (
                      <FormControl fullWidth size="small">
                        <InputLabel>Circle</InputLabel>
                        <Select
                          multiple
                          value={row.circle || []}
                          onChange={(e) => handleChangeRowField(index, 'circle', e.target.value)}
                          input={<OutlinedInput label="Circle" />}
                        >
                          {listOfCircle.map((circle) => (
                            <MenuItem key={circle.circleCode} value={circle.circleCode}>
                              {circle.circleName}
                            </MenuItem>
                          ))}
                        </Select>
                      </FormControl>
                    )}
                  </TableCell>
                )}
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Container>
    </Box>
  );
};

export default ReportsPage;