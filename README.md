/**
 * Triggers download of a file using Blob or ArrayBuffer data.
 *
 * @param {Blob|ArrayBuffer} fileData - The binary data from the backend
 * @param {string} fileName - Name of the file to be downloaded (e.g. "report.pdf")
 * @param {string} mimeType - MIME type of the file (e.g. "application/pdf")
 */
export const triggerDownload = (fileData, fileName, mimeType) => {
  const blob =
    fileData instanceof Blob
      ? fileData
      : new Blob([fileData], { type: mimeType || 'application/octet-stream' });

  const url = window.URL.createObjectURL(blob);

  const link = document.createElement('a');
  link.href = url;
  link.setAttribute('download', fileName || `file_${Date.now()}`);
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);

  window.URL.revokeObjectURL(url);
};