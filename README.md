
Vulnerability Description in Detail :Attackers can control the file system path argument to File() at PnlVarController.java line 308, which allows them to access or modify otherwise protected files.


Likely Impact :Attackers can control the file system path argument to File() at BCPGPDecryptor.java line 74, which allows them to access or modify otherwise protected files.
Recommendation :The best way to prevent path manipulation is with a level of indirection: create a list of legitimate values from which the user must select. With this approach, the user-provided input is never used directly to specify the resource name. In some situations this approach is impractical because the set of legitimate resource names is too large or too hard to maintain. Programmers often resort to implementing a deny list in these situations. A deny list is used to selectively reject or escape potentially dangerous characters before using the input. However, any such list of unsafe characters is likely to be incomplete and will almost certainly become out of date. A better approach is to create a list of characters that are permitted to appear in the resource name and accept input composed exclusively of characters in the approved set.

Code impacted ::

     
    try {
                //log.info("check");
                con = dataSource.getConnection();
                //log.info("datachecckkk");
                config = new PropertiesConfiguration("common.properties");
                param.put("req_id", String.valueOf(id));
                param.put("isExcel",true);
                param.put("IS_DETECT_CELL_TYPE",true);
                String checkPath = config.getProperty("REPORT_HOME_DIR") + "reports";

                outFilePath = config.getProperty("REPORT_HOME_DIR") + "reports" + File.separator + filename + ".xlsx";
                JasperPrint jasperPrint;
                File check = new File(checkPath);
                if (!check.exists()) {
                    check.mkdirs();
                    //log.info("foldercreated");
                }


                String JasperFilePath="/media/BS/Oracle3/Oracle_Home/user_projects/domains/base_domain/servers/node_15/tmp/_WL_user/BS/9g1ohn/war/WEB-INF/BS"+File.separator+"BsJasper"+File.separator+ "PNL_VARIANCE_REPORT" + ".jasper";
                //log.info("jasperfileeepath"+JasperFilePath);
                /*try {

                    ServletContext context = request.getServletContext();
                    String realContextPath = context.getRealPath(request.getContextPath("/WEB-INF/BS"));
                    //log.info("testtttt" + realContextPath);
                }finally {
                    //log.info("nullllllll");
                }*/


                jasperPrint = JasperFillManager.fillReport(JasperFilePath, param, con);
                //log.info("jasperrrrprint"+jasperPrint);



                OutputStream out2= new FileOutputStream(new File(outFilePath));
                //log.info("outputstreammmmm");
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
                //log.info("exportdoneeee");
                String serverPostKeyString = CommonFunction.POST_SERVER_IP_0;
                String SFTPHOST = CommonFunction.getServerIp(serverPostKeyString);
                String SFTPUSER = CommonFunction.getServerUserName(serverPostKeyString);
                String SFTPPASS = CommonFunction.getServerPass(serverPostKeyString);
                int SFTPPORT = CommonFunction.getServerPort(serverPostKeyString);
