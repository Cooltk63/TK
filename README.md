public ResponseEntity getPdfFileContent(String reportMasterId, Map<String, String> loginUserData, String absPath, String outputAbsPath, String fileName, HttpServletRequest request, String branchCode, String qed) {
        log.info("111.1");
        ResponseVO<Map<String, Object>> responseVO = new ResponseVO<>();
        Map<String, Object> map = null;
        try {
            map = new HashMap<>();
            InputStream is;
            log.info("112");
            if (!reportMasterId.equalsIgnoreCase("3071")) {

                if (!(reportMasterId.equalsIgnoreCase("3067") || reportMasterId.equalsIgnoreCase("3069"))) {
                    byte[] buffer = new byte[1024];
                    GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(absPath));
                    FileOutputStream out1 = new FileOutputStream(outputAbsPath);
                    int len1;
                    while ((len1 = gzis.read(buffer)) > 0) {
                        out1.write(buffer, 0, len1);
                    }

                    gzis.close();
                    out1.close();
                }
                log.info("113");
                is = new FileInputStream(outputAbsPath);

                //String tempoutFilePath = AutoClean.cleanedPath(AutoClean.CREATED, "", "", request);
                String tempoutFilePath = AutoClean.cleanedPath(AutoClean.CREATED, branchCode, qed, "", "");


                log.info("114");
                File file1 = new File(tempoutFilePath);
                if (!file1.exists()) {
                    log.info("115");
                    file1.mkdirs();
                }
                log.info("116");
                String serverFilePath = CleanPath.cleanString(tempoutFilePath + File.separator + fileName);
                log.info("117");
                File serverFile = new File(serverFilePath);
                if (serverFile.exists())
                    serverFile.delete();
                log.info("118");
                OutputStream out = new FileOutputStream(serverFilePath);
                byte[] buf = new byte[1024];
                int len;
                while ((len = is.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                log.info("111111111111");
                out.close();
                is.close();
                Document pdfDoc = new Document();
                log.info("2222222222");
                String pdfFileName = CleanPath.cleanString(tempoutFilePath + File.separator + fileName + ".pdf");
                log.info("333333");


                OutputStream outPDFWRITER = new FileOutputStream(pdfFileName);
                log.info("444444445555555555555");

                //PdfWriter writer = PdfWriter.getInstance(pdfDoc, new FileOutputStream(pdfFileName));
                PdfWriter writer = PdfWriter.getInstance(pdfDoc, outPDFWRITER);







                log.info("44444");
                pdfDoc.open();
                log.info("120");
                Font myFont = new Font(Font.FontFamily.COURIER, 11);
                Font bold_font = new Font();
                myFont.setSize(6);
                pdfDoc.add(new Paragraph("\n"));
                FileInputStream fileInput = new FileInputStream(serverFile);
                DataInputStream dis = new DataInputStream(fileInput);
                InputStreamReader isr = new InputStreamReader(dis);
                BufferedReader br = new BufferedReader(isr);
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    Paragraph para = new Paragraph(strLine + "\n", myFont);
                    para.setAlignment(Element.ALIGN_JUSTIFIED);
                    pdfDoc.add(para);
                }
                pdfDoc.close();

                writer.close();
                outPDFWRITER.flush();
                outPDFWRITER.close();




                //String checkOutFilePath = AutoClean.cleanedPath(AutoClean.REPORTMASTERID, fileName, reportMasterId, request);
                String checkOutFilePath = AutoClean.cleanedPath(AutoClean.REPORTMASTERID, branchCode, qed, fileName, reportMasterId);

                log.info("122.1 >>>>" + checkOutFilePath);
                log.info("pdfFileName : "+pdfFileName);
                PdfReader reader = new PdfReader(pdfFileName);
                PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(checkOutFilePath));
                log.info("122.2");
                Map<String, String> info = reader.getInfo();
                info.put("uniqueBr", loginUserData.get("branch_code") + "_" + reportMasterId + "_" + loginUserData.get("qed"));
                stamper.setMoreInfo(info);
                stamper.close();
                reader.close();
                log.info("122.3");
                File file2 = new File(checkOutFilePath);
                log.info("122.4");
                byte[] pdfContent = FileUtils.readFileToByteArray(file2);
                fileInput.close();
                dis.close();
                isr.close();
                br.close();
                log.info("122.5");
                map.put("pdfContent", pdfContent);
                map.put("displayFlag", true);
                map.put("disMessage", null);
                log.info("123");
            } else {
                log.info("124");
                //String checkOutFilePath = AutoClean.cleanedPath(AutoClean.REPORTMASTERID, fileName, reportMasterId, request);
                String checkOutFilePath = AutoClean.cleanedPath(AutoClean.REPORTMASTERID, branchCode, qed, fileName, reportMasterId);

                log.info("125");
                //String checkPath = AutoClean.cleanedPath(AutoClean.CREATED, "", "", request);
                String checkPath = AutoClean.cleanedPath(AutoClean.CREATED, branchCode, qed, "", "");

                log.info("126");
                File checkFile = new File(checkPath);
                if (!checkFile.exists()) {
                    log.info("127");
                    checkFile.mkdirs();
                }
                PdfReader reader;

                reader = new PdfReader(absPath);
                PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(checkOutFilePath));
                Map<String, String> info = reader.getInfo();
                info.put("uniqueBr", loginUserData.get("branch_code") + "_" + reportMasterId + "_" + loginUserData.get("qed"));
                stamper.setMoreInfo(info);
                stamper.close();
                reader.close();
                log.info("128");
                //////////////////////////
                File file2 = new File(checkOutFilePath);
                byte[] pdfContent;
                log.info("129");
                pdfContent = FileUtils.readFileToByteArray(file2);

                map.put("pdfContent", pdfContent);
                map.put("disMessage", null);
                map.put("displayFlag", true);
                log.info("130");
                //////////////////////
                String fileNamePdf = "check.pdf";
                //String checkPreRequisite = AutoClean.cleanedPath(AutoClean.DOCUMENT, fileNamePdf, "", request);
                String checkPreRequisite = AutoClean.cleanedPath(AutoClean.DOCUMENT, branchCode, qed, fileNamePdf, "");

                File preCheck = new File(checkPreRequisite);
                byte[] check;
                log.info("131");
                check = FileUtils.readFileToByteArray(preCheck);

                map.put("check", check);
                log.info("132");
            }
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("PdfFileContent fetched successfully");
            responseVO.setResult(map);
            log.info("133");
        } catch (IOException e) {
            log.info("134");
            map.put("displayFlag", false);
            log.info("IOException Occurred: " + e.getCause());
            log.info("IOException Occurred: " + e.getMessage());
            e.printStackTrace();
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage("Getting Error Message " + e.getMessage());
            responseVO.setResult(map);

        } catch (DocumentException e) {
            log.info("135");
            map.put("displayFlag", false);
            log.info("DocumentException Occurred: " + e.getCause());
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage("Getting Error Message " + e.getMessage());
            responseVO.setResult(map);

        } catch (Exception e) {
            map.put("displayFlag", false);
            log.info("Exception Occurred: " + e.getCause());
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage("Getting Error Message " + e.getMessage());
            responseVO.setResult(map);
            //log.error(" IOException :  " + e.getMessage());
        }
        log.info("Final Result");
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

