  String outFilePath = config.getProperty("REPORT_HOME_DIR") + "created" + File.separator + circleCode
                        + "_" + quarterEndDate.replaceAll("/", "") + "_" + jrxmlName + ".csv";
                // log.info("output file path : " + outFilePath);
                ////log.info("Source file path : " + sourceFilePath);

                JasperReport jasperReport;
                JasperPrint jasperPrint;

                File check = new File(checkPath);
                if (!check.exists())
                    check.mkdirs();
                String JasperFilePath = config.getProperty("REPORT_HOME_DIR") + "jasper" + File.separator + jrxmlName
                        + ".jasper";

                // CommonFunction cf = new CommonFunction();
                // boolean compiledFlag = cf.complieJrxml(jrxmlName);
                ////log.info("File Compiled");
                ////log.info("compiled done");

                jasperPrint = JasperFillManager.fillReport(JasperFilePath, param, con);

                OutputStream out2 = new FileOutputStream(new File(outFilePath));
                JRCsvExporter CSV = new JRCsvExporter();
                // CSV.setParameter(JRTextExporterParameter.PAGE_WIDTH, 150);
                // CSV.setParameter(JRTextExporterParameter.PAGE_HEIGHT, 40);
                CSV.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                CSV.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFilePath);
                CSV.exportReport();

                File file2 = new File(outFilePath);
                pdfContent = FileUtils.readFileToByteArray(file2);
                response.setContentType("application/text/csv;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + jrxmlName + ".csv");
                // response.setHeader("Content-disposition","inline;filename="+fileName);
                OutputStream out1 = (OutputStream) response.getOutputStream();
                out1.write(pdfContent);
                out1.flush();
                out1.close();
                file2.delete();
