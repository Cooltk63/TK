 @RequestMapping(value = "/viewReportJrxml", method = RequestMethod.POST)
    public void viewReport(@RequestBody Map<String, Object> map, HttpServletResponse response)
            throws JRException, ConfigurationException, SQLException, IOException {
        byte[] pdfContent = null;
        LinkedHashMap list = (LinkedHashMap) map.get("user");
        String circleCode = (String) list.get("circleCode");
        String userId = (String) list.get("userId");
        String quarterEndDate = (String) list.get("quarterEndDate");
        LinkedHashMap reportList = (LinkedHashMap) map.get("report");
        String jrxmlName = (String) reportList.get("dash_jrxml");
        String parameters = (String) reportList.get("dash_param");
        String prePost = (String) map.get("prePost");
        String dash_dwnload = (String) map.get("type");
        //log.info("**************  parameters  " + parameters);
        String circle = "";
        ArrayList circleList = null;


        if (parameters.contains("CIRCLE_LIST")) {
            circleList = (ArrayList) map.get("compcircle");


        }
        if (parameters.contains("CIRCLE_CODE")) {
            circle = (String) map.get("compcircle");

        }

        //log.info("  " + circleList + "   ************  " + circle);

        String para[] = null;
        if (parameters != "null") {
            para = parameters.split(",");
        }

        Map param = new HashMap();
        for (String listOfParameters : para) {
            //log.info("parameters" + listOfParameters);

            if (listOfParameters.equalsIgnoreCase("DATE")) {
                param.put(listOfParameters, quarterEndDate);
            }
            if (listOfParameters.equalsIgnoreCase("CIRCLE_CODE")) {

                param.put(listOfParameters, circle);
            }
            if (listOfParameters.equalsIgnoreCase("CIRCLE_LIST")) {

                param.put(listOfParameters, circleList);
            }

            if (listOfParameters.contains("TYPE")) {
                param.put(listOfParameters, prePost);
            }

            if (listOfParameters.equalsIgnoreCase("COMP")) {
                param.put("COMP", circleList);
            }

        }
        param.put("ZERO", map.get("isSuppresed"));
        //log.info("pre post ------- " + prePost);

        //log.info("jrxmlName " + jrxmlName);
        //log.info("circleCode " + circleCode);
        //log.info("quarterEndDate " + quarterEndDate);
        //log.info("userId " + userId);
        //log.info("dash_dwnload " + dash_dwnload);

        /*
         * Map param = new HashMap(); param.put("DATE", quarterEndDate);
         * param.put("CIRCLE_CODE", circleCode); param.put("TYPE", "PRE");
         */

        Configuration config = new PropertiesConfiguration("common.properties");
        param.put("SUBREPORT_DIR", config.getProperty("REPORT_HOME_DIR") + "jasper" + File.separator);

        /*String sourceFilePath = config.getProperty("REPORT_HOME_DIR") + "jrxmls" + File.separator + jrxmlName
                + ".jrxml";
*/
        Connection con = dataSource.getConnection();

        String checkPath = config.getProperty("REPORT_HOME_DIR") + "created" + File.separator + circleCode
                + File.separator + quarterEndDate.replaceAll("/", "");
        String outFilePath = null;
        String contentType = null;
        String extention = null;
        File file2 = null;
        try {
            if (dash_dwnload.equalsIgnoreCase("view") || dash_dwnload.equalsIgnoreCase("downloadPDF")) {

                outFilePath = config.getProperty("REPORT_HOME_DIR") + "created" + File.separator + circleCode
                        + File.separator + quarterEndDate.replaceAll("/", "") + File.separator + circleCode + "_"
                        + quarterEndDate.replaceAll("/", "") + "_" + jrxmlName + ".pdf";

                File check = new File(CleanPath.cleanString(checkPath));
                if (!check.exists())
                    check.mkdirs();
                JasperPrint jasperPrint;

                String JasperFilePath = config.getProperty("REPORT_HOME_DIR") + "jasper" + File.separator + jrxmlName
                        + ".jasper";
                //CommonFunction cf = new CommonFunction();
                //boolean compiledFlag = cf.complieJrxml(jrxmlName);
                //log.info("File Compiled");
                //log.info("outFilePath" + outFilePath);
                ////log.info("sourceFilePath" + sourceFilePath);

                //log.info("compiled done");

                jasperPrint = JasperFillManager.fillReport(JasperFilePath, param, con);
                //log.info("filled  done");
                JasperExportManager.exportReportToPdfFile(jasperPrint, outFilePath);
                //log.info("export done");

                file2 = new File(outFilePath);
                pdfContent = FileUtils.readFileToByteArray(file2);
                // FileInputStream stream=new FileInputStream(file2);
                OutputStream os = (OutputStream) response.getOutputStream();
                // IOUtils.copy(stream, response.getOutputStream());
                contentType = "application/pdf";
                extention = ".pdf";
                response.setContentType(contentType);

                response.setHeader("Content-Disposition", "attachment: filename=" + jrxmlName + extention);
                os.write(pdfContent);
                os.flush();
                // os.close();
                // byte[] pdfContent = FileUtils.readFileToByteArray(file2);
                // response.flushBuffer();
                // stream.close();
                // file2.delete();
            } else {

                //adding these parameters to make amounts as numeric in Excel format
                param.put("isExcel", true);
                param.put("IS_DETECT_CELL_TYPE", true);
                outFilePath = config.getProperty("REPORT_HOME_DIR") + "created" + File.separator + circleCode
                        + "_" + quarterEndDate.replaceAll("/", "") + "_" + jrxmlName + ".xls";
                //log.info("output file path : " + outFilePath);
                ////log.info("Source file path : " + sourceFilePath);

                JasperReport jasperReport;
                JasperPrint jasperPrint;

                File check = new File(checkPath);
                if (!check.exists())
                    check.mkdirs();
                String JasperFilePath = config.getProperty("REPORT_HOME_DIR") + "jasper" + File.separator + jrxmlName
                        + ".jasper";

                //CommonFunction cf = new CommonFunction();
                //boolean compiledFlag = cf.complieJrxml(jrxmlName);
                ////log.info("File Compiled");
                ////log.info("compiled done");
                param.put("IS_IGNORE_PAGINATION", true);

                jasperPrint = JasperFillManager.fillReport(JasperFilePath, param, con);
                //log.info("printing done");

                OutputStream out2 = new FileOutputStream(new File(outFilePath));

                JRXlsxExporter excelExporter = new JRXlsxExporter();
                /*
                 * excelExporter.setParameter(JRXlsExporterParameter.
                 * JASPER_PRINT, jasperPrint);
                 * excelExporter.setParameter(JRXlsExporterParameter.
                 * OUTPUT_STREAM, out2);
                 * excelExporter.setParameter(JRXlsExporterParameter.
                 * IS_DETECT_CELL_TYPE, Boolean.TRUE);
                 * excelExporter.setParameter(JRXlsExporterParameter.
                 * IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                 *
                 * excelExporter.setParameter(JRXlsExporterParameter.
                 * IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                 * excelExporter.setParameter(JRXlsExporterParameter.
                 * IS_IGNORE_CELL_BORDER, Boolean.TRUE);
                 * excelExporter.exportReport();
                 */
                //log.info("************new ************* method called");
                excelExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                excelExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out2));
                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                // configuration.setOnePagePerSheet(true);
                configuration.setDetectCellType(true);
                configuration.setWhitePageBackground(false);
                configuration.setRemoveEmptySpaceBetweenRows(true);
                configuration.setIgnoreCellBorder(true);
                excelExporter.setConfiguration(configuration);

                excelExporter.exportReport();

                file2 = new File(outFilePath);
                pdfContent = FileUtils.readFileToByteArray(file2);
                contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                extention = ".xlsx";
                response.setContentType(contentType);
                response.setHeader("Content-Disposition", "attachment;filename=" + jrxmlName + extention);
                // response.setHeader("Content-disposition","inline;filename="+fileName);
                OutputStream out1 = (OutputStream) response.getOutputStream();
                out1.write(pdfContent);
                out1.flush();
                // out1.close();
                // file2.delete();
            }

        } catch (Exception e) {

            e.printStackTrace();
            //log.info("file download try again");
            if (null != file2 && file2.exists()) {
                try {
                    //log.info("file download trying");
                    file2 = new File(outFilePath);
                    pdfContent = FileUtils.readFileToByteArray(file2);
                    OutputStream os = (OutputStream) response.getOutputStream();
                    response.setContentType(contentType);
                    response.setHeader("Content-Disposition", "attachment: filename=" + jrxmlName + extention);
                    os.write(pdfContent);
                    os.flush();
                    //os.close();
                    // byte[] pdfContent = FileUtils.readFileToByteArray(file2);
                    // response.flushBuffer();
                    // stream.close();
                    file2.delete();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    //log.info("file download failing");
                }
            }
        } finally {
            if (file2.exists()) {
                file2.delete();
            }
            if (null != con) {
                con.close();
            }
        }
    }
