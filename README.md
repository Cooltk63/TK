// inside ReportViewer component
<Dialog open={open} onClose={onClose} maxWidth="lg" fullWidth>
  <DialogTitle>{title}</DialogTitle>
  <DialogContent dividers>
    <iframe
      src={bufferData}
      title="Report View"
      width="100%"
      height="600px"
      style={{ border: 'none' }}
    />
  </DialogContent>
  <DialogActions>
    <Button onClick={() => onDownloadPDF(selectedRow)} color="primary">Download PDF</Button>
    <Button onClick={onClose} color="secondary">Close</Button>
  </DialogActions>
</Dialog>