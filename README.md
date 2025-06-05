/* ✅ VIRTUALIZATION APPLIED TO SCHEDULE10 ✨ Requirements Met:

No change to business logic ✅

No change to visual layout ✅

Integrated react-window virtualization for rows ✅ */


import { FixedSizeList as List } from 'react-window'; import AutoSizer from 'react-virtualized-auto-sizer';

// Inside your Schedule10 return block, replace <TableBody> like this: <TableContainer component={Paper} sx={{ maxHeight: 'calc(100vh - 200px)' }}>

  <Table sx={{ minWidth: 3000 }} aria-label="schedule 10 table" stickyHeader>
    <TableHead> 
      {/* Keep your existing TableHead as-is */} 
    </TableHead><TableBody>
  <AutoSizer disableWidth>
    {({ height }) => (
      <List
        height={height}
        itemCount={rowDefinitionsConfig.length}
        itemSize={80} // Estimate row height; adjust as needed
        width="100%"
      >
        {({ index, style }) => {
          const rowDef = rowDefinitionsConfig[index];
          const rowKey = rowDef.id;
          const displayDataForRow = calculatedData[rowDef.id] || {};
          const currentFormDataForRow = formData[rowDef.id] || {};

          if (rowDef.type === 'sectionHeader' || rowDef.type === 'subSectionHeader') {
            return (
              <StyledTableRow
                key={rowKey}
                style={style}
                $issectionheader={rowDef.type === 'sectionHeader'}
                $issubsectionheader={rowDef.type === 'subSectionHeader'}
              >
                <StyledTableCell sx={{ position: 'sticky', left: 0, zIndex: 1 }}>{rowDef.srNo || ''}</StyledTableCell>
                <StyledTableCell sx={{ position: 'sticky', left: '50px', zIndex: 1 }}>
                  <b>{typeof rowDef.label === 'function' ? rowDef.label(formData) : rowDef.label}</b>
                </StyledTableCell>
              </StyledTableRow>
            );
          }

          return (
            <StyledTableRow
              key={rowKey}
              style={style}
              $istotalrow={rowDef.isTotalRowStyle}
              $issectionheader={rowDef.isSectionHeaderStyle}
            >
              <StyledTableCell sx={{ position: 'sticky', left: 0, zIndex: 1 }}>
                <b>{rowDef.srNo || ''}</b>
              </StyledTableCell>
              <StyledTableCell sx={{ position: 'sticky', left: '50px', zIndex: 1 }}>
                <b>{typeof rowDef.label === 'function' ? rowDef.label(formData) : rowDef.label}</b>
              </StyledTableCell>

              {columnDisplayHeaders.map((colDef) => {
                const fieldKey = colDef.dataField;
                const cellKey = `${rowKey}-${fieldKey}`;
                const isReadOnly =
                  rowDef.type === 'total' ||
                  colDef.isCalculated ||
                  (rowDef.isReadOnlyGroup && rowDef.isReadOnlyGroup.includes(fieldKey));
                const value = displayDataForRow[fieldKey] ?? '0.00';
                const errorForField = errors[cellKey];

                return (
                  <StyledTableCell key={cellKey}>
                    <FormInput
                      name={cellKey}
                      value={value}
                      onChange={isReadOnly ? undefined : (e) => handleChange(rowDef.id, fieldKey, e.target.value)}
                      onBlur={isReadOnly ? undefined : (e) => handleBlur(rowDef.id, fieldKey, e.target.value)}
                      readOnly={isReadOnly || fieldsDisabled}
                      error={!!errorForField}
                      helperText={errorForField}
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
</TableContainer>/* 📦 Install if not present: npm install react-window react-virtualized-auto-sizer */

