Vulnerability
Trust Boundary Violation



Vulnerability Description in Detail
The method viewReport() in AdminController.java commingles trusted and untrusted data in the same data structure, which encourages programmers to mistakenly trust unvalidated data.


Source Code ::

         public @ResponseBody byte[] viewReport(@RequestBody Map<String, Object> map, HttpServletResponse response,
                                           HttpServletRequest request) throws JRException, ConfigurationException, SQLException, IOException {
        log.info(map.toString());

        Map<String, Object> list = (Map<String, Object>) map.get("user");
        log.info(map.get("user"));
        log.info(list.toString());
        String circleCode = (String) list.get("circleCode");
        String userId = (String) list.get("userId");
        String quarterEndDate = (String) list.get("quarterEndDate");
        LinkedHashMap reportList = (LinkedHashMap) map.get("report");
        String jrxmlName = (String) reportList.get("jrxmlName");
        String reportMasterId = (String) reportList.get("reportMasterId");
        Map param = new HashMap();
        param.put("DATE", quarterEndDate);
        param.put("CIRCLE_CODE", circleCode);
        param.put("TYPE", "POST");
        Configuration config = new PropertiesConfiguration("common.properties");
        param.put("SUBREPORT_DIR", config.getProperty("REPORT_HOME_DIR") + "jasper" + File.separator);
      
        String outFilePath = config.getProperty("REPORT_HOME_DIR") + "created" + File.separator + circleCode
                + File.separator + quarterEndDate.replaceAll("/", "") + File.separator + circleCode + "_"
                + quarterEndDate.replaceAll("/", "") + "_" + jrxmlName + "_POST.pdf";
        String temporaryFilePath = config.getProperty("REPORT_HOME_DIR") + "created" + File.separator + circleCode
                + File.separator + quarterEndDate.replaceAll("/", "") + File.separator + circleCode + "_"
                + quarterEndDate.replaceAll("/", "") + "_" + jrxmlName + "_temp_POST" + ".pdf";


        log.info("temporaryFilePath>>>>>>>>>>"+temporaryFilePath);
        String checkPath = config.getProperty("REPORT_HOME_DIR") + "created" + File.separator + circleCode
                + File.separator + quarterEndDate.replaceAll("/", "");
        Connection con = null;
        try {
            File check = new File(checkPath);
            if (!check.exists())
                check.mkdirs();
            JasperPrint jasperPrint;
            String JasperFilePath = config.getProperty("REPORT_HOME_DIR") + "jasper" + File.separator + jrxmlName
                    + ".jasper";

            con = dataSource.getConnection();

            jasperPrint = JasperFillManager.fillReport(JasperFilePath, param, con);
            // log.info("filled done");
            JasperExportManager.exportReportToPdfFile(jasperPrint, outFilePath);
            // log.info("export done");
            PdfReader reader = new PdfReader(outFilePath);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(temporaryFilePath));
            Map<String, String> info = reader.getInfo();
            info.put("uniqueBr", circleCode + "_" + reportMasterId + "_" + quarterEndDate.replaceAll("/", ""));
            stamper.setMoreInfo(info);
            stamper.close();
            reader.close();
            File file2 = new File(temporaryFilePath);
            byte[] pdfContent = FileUtils.readFileToByteArray(file2);
            HttpSession session = request.getSession();
            session.setAttribute("pdfContent", pdfContent);
            session.setAttribute("message","Operationcompletedsuccessfully");
            return pdfContent;
            //return encrypt;
        } catch (Exception e) {
            log.error("Exception Occurred " +e.getMessage());
        } finally {
            if (null != con)
                con.close();
        }
        // return pdfContent;
        return null;
    }


    Main Method where inside it isue or vulnaribility occurred ::
                  On this lines 
     HttpSession session = request.getSession();
            session.setAttribute("pdfContent", pdfContent);
            session.setAttribute("message","Operationcompletedsuccessfully");
            return pdfContent;


            How to resolve this issue without affecting business logic.
            
