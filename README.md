// Enhanced security utility class for header sanitization
public class HeaderSecurityUtils {
    
    /**
     * Sanitizes filename for HTTP headers to prevent header injection attacks
     * @param filename The original filename
     * @return Sanitized filename safe for HTTP headers
     */
    public static String sanitizeFilenameForHeader(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            return "report";
        }
        
        // Remove or replace dangerous characters that could cause header injection
        String sanitized = filename
            .replaceAll("[\r\n\t]", "") // Remove line breaks and tabs
            .replaceAll("[\"\\\\]", "_") // Replace quotes and backslashes
            .replaceAll("[<>:|?*]", "_") // Replace other problematic chars
            .replaceAll("\\s+", "_") // Replace whitespace with underscore
            .replaceAll("_{2,}", "_") // Replace multiple underscores with single
            .trim();
        
        // Ensure filename doesn't start or end with underscore/dot
        sanitized = sanitized.replaceAll("^[._]+|[._]+$", "");
        
        // Limit length to prevent buffer overflow
        if (sanitized.length() > 100) {
            sanitized = sanitized.substring(0, 100);
        }
        
        // Ensure we have a valid filename
        if (sanitized.isEmpty()) {
            sanitized = "report";
        }
        
        return sanitized;
    }
    
    /**
     * Sets Content-Disposition header safely
     * @param response HttpServletResponse object
     * @param filename Filename to set
     * @param inline Whether to display inline (true) or as attachment (false)
     */
    public static void setContentDispositionHeader(HttpServletResponse response, String filename, boolean inline) {
        String sanitizedFilename = sanitizeFilenameForHeader(filename);
        String disposition = inline ? "inline" : "attachment";
        
        // Use RFC 6266 compliant header format
        String headerValue = String.format("%s; filename=\"%s\"", disposition, sanitizedFilename);
        
        response.setHeader("Content-Disposition", headerValue);
    }
    
    /**
     * Sets all security headers to prevent various attacks
     * @param response HttpServletResponse object
     */
    public static void setSecurityHeaders(HttpServletResponse response) {
        // Prevent clickjacking
        response.setHeader("X-Frame-Options", "DENY");
        
        // Prevent MIME type sniffing
        response.setHeader("X-Content-Type-Options", "nosniff");
        
        // Enable XSS protection
        response.setHeader("X-XSS-Protection", "1; mode=block");
        
        // Prevent caching of sensitive content
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
    }
}

// Your updated method with security fixes
@RequestMapping(value = "/viewReportJrxmlCircle", method = RequestMethod.POST)
public void viewReportCircle(@RequestBody Map<String, Object> map, HttpServletResponse response)
        throws JRException, ConfigurationException, SQLException, IOException {

    byte[] pdfContent = null;
    LinkedHashMap list = (LinkedHashMap) map.get("user");
    String circleCode = (String) list.get("circleCode");
    String userId = (String) list.get("userId");
    String quarterEndDate = (String) list.get("quarterEndDate");

    LinkedHashMap reportList = (LinkedHashMap) map.get("report");
    String parameters = (String) reportList.get("dash_param");
    String prePost = (String) map.get("prePost");
    String dash_dwnload = (String) map.get("type");
    String downloadType = (String) map.get("dash_dwnload");
    String branchCode = (String) map.get("branchCode");
    List<String> circleList = (List) map.get("compcircle");

    // Input validation
    if (circleCode == null || userId == null || quarterEndDate == null) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters");
        return;
    }

    String jrxmlName = "";
    // for setting jrxmlName
    if (null != branchCode && branchCode.equalsIgnoreCase("All Branches")) {
        jrxmlName = (String) reportList.get("dash_jrxml");
        branchCode = circleCode;
    } else {
        String jrxmlNamecheck = (String) reportList.get("dash_jrxml");
        if (jrxmlNamecheck.equalsIgnoreCase("BS_PNL")) {
            jrxmlName = "BS_PNL_B";
        } else if (jrxmlNamecheck.equalsIgnoreCase("BS_YSA")) {
            jrxmlName = "BS_YSA_B";
        } else {
            jrxmlName = jrxmlNamecheck;
        }
    }

    String para[] = null;
    if (parameters != null && !parameters.equals("null")) {
        para = parameters.split(",");
    }

    Map param = new HashMap();
    if (para != null) {
        for (String listOfParameters : para) {
            if (listOfParameters.equalsIgnoreCase("DATE")) {
                param.put(listOfParameters, quarterEndDate);
            }
            if (listOfParameters.equalsIgnoreCase("CIRCLE_CODE")) {
                param.put(listOfParameters, circleCode);
            }
            if (listOfParameters.contains("TYPE")) {
                param.put(listOfParameters, prePost);
            }
            if (listOfParameters.equalsIgnoreCase("COMP")) {
                param.put("COMP", circleList);
            }
            if (listOfParameters.equalsIgnoreCase("BRANCH_CODE")) {
                param.put("BRANCH_CODE", branchCode);
            }
        }
    }
    
    param.put("ZERO", map.get("isSuppresed"));

    Configuration config = new PropertiesConfiguration("common.properties");
    param.put("SUBREPORT_DIR", config.getProperty("REPORT_HOME_DIR") + "jasper" + File.separator);

    Connection con = dataSource.getConnection();

    String checkPath = config.getProperty("REPORT_HOME_DIR") + "created" + File.separator + circleCode
            + File.separator + quarterEndDate.replaceAll("/", "");
    if (null == branchCode) {
        branchCode = "";
    } else {
        branchCode = branchCode + "_";
    }

    try {
        // Set security headers for all responses
        HeaderSecurityUtils.setSecurityHeaders(response);
        
        if (dash_dwnload.equalsIgnoreCase("view")) {

            String outFilePath = config.getProperty("REPORT_HOME_DIR") + "created" + File.separator + circleCode
                    + File.separator + quarterEndDate.replaceAll("/", "") + File.separator + branchCode
                    + quarterEndDate.replaceAll("/", "") + "_" + jrxmlName + ".html";

            File check = new File(checkPath);
            if (!check.exists())
                check.mkdirs();
            JasperPrint jasperPrint;
            String JasperFilePath = config.getProperty("REPORT_HOME_DIR") + "jasper" + File.separator + jrxmlName
                    + ".jasper";

            param.put("IS_IGNORE_PAGINATION", true);
            jasperPrint = JasperFillManager.fillReport(JasperFilePath, param, con);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, outFilePath);

            File file2 = new File(outFilePath);
            pdfContent = FileUtils.readFileToByteArray(file2);
            
            OutputStream os = response.getOutputStream();
            
            // SECURE HEADER IMPLEMENTATION
            response.setContentType("text/html");
            response.setHeader("Content-Length", String.valueOf(file2.length()));
            HeaderSecurityUtils.setContentDispositionHeader(response, jrxmlName + ".html", true);
            
            os.write(pdfContent);
            os.close();
            file2.delete();
            
        } else if (dash_dwnload.equalsIgnoreCase("downloadPDF")) {
            
            String outFilePath = config.getProperty("REPORT_HOME_DIR") + "created" + File.separator + circleCode
                    + File.separator + quarterEndDate.replaceAll("/", "") + File.separator + branchCode
                    + quarterEndDate.replaceAll("/", "") + "_" + jrxmlName + ".pdf";

            File check = new File(checkPath);
            if (!check.exists())
                check.mkdirs();
            JasperPrint jasperPrint;
            String JasperFilePath = config.getProperty("REPORT_HOME_DIR") + "jasper" + File.separator + jrxmlName
                    + ".jasper";

            jasperPrint = JasperFillManager.fillReport(JasperFilePath, param, con);
            JasperExportManager.exportReportToPdfFile(jasperPrint, outFilePath);

            File file2 = new File(outFilePath);
            pdfContent = FileUtils.readFileToByteArray(file2);
            
            OutputStream os = response.getOutputStream();
            
            // SECURE HEADER IMPLEMENTATION FOR PDF
            response.setContentType("application/pdf");
            HeaderSecurityUtils.setContentDispositionHeader(response, jrxmlName + ".pdf", false);
            
            os.write(pdfContent);
            os.close();
            file2.delete();
            
        } else if (dash_dwnload.contains("downloadCSV")) {
            
            String outFilePath = config.getProperty("REPORT_HOME_DIR") + "created" + File.separator + circleCode
                    + "_" + quarterEndDate.replaceAll("/", "") + "_" + jrxmlName + ".csv";

            JasperPrint jasperPrint;

            File check = new File(checkPath);
            if (!check.exists())
                check.mkdirs();
            String JasperFilePath = config.getProperty("REPORT_HOME_DIR") + "jasper" + File.separator + jrxmlName
                    + ".jasper";

            jasperPrint = JasperFillManager.fillReport(JasperFilePath, param, con);

            OutputStream out2 = new FileOutputStream(new File(outFilePath));
            JRCsvExporter CSV = new JRCsvExporter();
            CSV.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            CSV.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFilePath);
            CSV.exportReport();

            // CSV Formula Injection Protection (your existing code is good)
            List<String> safeLines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(outFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] cells = line.split(",", -1);
                    for (int i = 0; i < cells.length; i++) {
                        String cell = cells[i].trim();
                        if (!cell.isEmpty() && (cell.startsWith("=") || cell.startsWith("+") || 
                                              cell.startsWith("-") || cell.startsWith("@"))) {
                            cells[i] = "'" + cell;
                        }
                    }
                    safeLines.add(String.join(",", cells));
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFilePath))) {
                for (String safeLine : safeLines) {
                    writer.write(safeLine);
                    writer.newLine();
                }
            }
            
            // SECURE HEADER FOR CSV DOWNLOAD
            File csvFile = new File(outFilePath);
            pdfContent = FileUtils.readFileToByteArray(csvFile);
            
            response.setContentType("text/csv");
            HeaderSecurityUtils.setContentDispositionHeader(response, jrxmlName + ".csv", false);
            
            OutputStream os = response.getOutputStream();
            os.write(pdfContent);
            os.close();
            csvFile.delete();
            
            adminService.insertCSVInfo(circleCode, userId, quarterEndDate);

        } else {
            // Excel export
            param.put("isExcel", true);
            param.put("IS_DETECT_CELL_TYPE", true);
            String outFilePath = config.getProperty("REPORT_HOME_DIR") + "created" + File.separator + branchCode
                    + quarterEndDate.replaceAll("/", "") + "_" + jrxmlName + ".xlsx";
           
            JasperPrint jasperPrint;

            File check = new File(checkPath);
            if (!check.exists())
                check.mkdirs();
            String JasperFilePath = config.getProperty("REPORT_HOME_DIR") + "jasper" + File.separator + jrxmlName
                    + ".jasper";

            param.put("IS_IGNORE_PAGINATION", true);
            jasperPrint = JasperFillManager.fillReport(JasperFilePath, param, con);
           
            OutputStream out2 = new FileOutputStream(new File(outFilePath));

            JRXlsxExporter excelExporter = new JRXlsxExporter();
            excelExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            excelExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out2));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setDetectCellType(true);
            configuration.setWhitePageBackground(false);
            configuration.setRemoveEmptySpaceBetweenRows(true);
            configuration.setIgnoreCellBorder(true);
            excelExporter.setConfiguration(configuration);

            excelExporter.exportReport();
            out2.close();

            // SECURE HEADER FOR EXCEL DOWNLOAD
            File file2 = new File(outFilePath);
            pdfContent = FileUtils.readFileToByteArray(file2);
            
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            HeaderSecurityUtils.setContentDispositionHeader(response, jrxmlName + ".xlsx", false);
            
            OutputStream os = response.getOutputStream();
            os.write(pdfContent);
            os.close();
            file2.delete();
        }

    } catch (RuntimeException e) {
        log.error("Exception Occurred " + e.getMessage(), e);
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Report generation failed");
    } finally {
        if (null != con) {
            con.close();
        }
    }
}