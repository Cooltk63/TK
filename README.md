
            Vulnerability	:Header Manipulation
            Vulnerability Description in Detail :The method downloadXLReport() in MiscWorklistMakerController.java includes unvalidated data in an HTTP response header on line 379. This enables attacks such as cache-poisoning, cross-site scripting, cross-user defacement, page hijacking, cookie manipulation or open redirect.
            Likely Impact	:The method redirectToLoginPage() in WSFederationFilter.java includes unvalidated data in an HTTP response header on line 127. This enables attacks such as cache-poisoning, cross-site scripting, cross-user defacement, page hijacking, cookie manipulation or open redirect.
            Recommendation :The solution to prevent Header Manipulation is to ensure that input validation occurs in the required places and checks for the correct properties. Since Header Manipulation vulnerabilities occur when an application includes malicious data in its output, one logical approach is to validate data immediately before it leaves the application. However, because web applications often have complex and intricate code for generating responses dynamically, this method is prone to errors of omission (missing validation). An effective way to mitigate this risk is to also perform input validation for Header Manipulation. Web applications must validate all input to prevent other vulnerabilities, such as SQL injection, so augmenting an application's existing input validation mechanism to include checks for Header Manipulation is generally relatively easy. Despite its value, input validation for Header Manipulation does not take the place of rigorous output validation. An application might accept input through a shared data store or other trusted source, and that data store might accept input from a source that does not perform adequate input validation. Therefore, the application cannot implicitly rely on the safety of this or any other data. This means that the best way to prevent Header Manipulation vulnerabilities is to validate everything that enters the application or leaves the application destined for the user. The most secure approach to validation for Header Manipulation is to create an allow list of safe characters that can appear in HTTP response headers and accept input composed exclusively of characters in the approved set. For example, a valid name might only include alphanumeric characters or an account number might only include digits 0-9. A more flexible, but less secure approach is to implement a deny list, which selectively rejects or escapes potentially dangerous characters before using the input. To form such a list, you first need to understand the set of characters that hold special meaning in HTTP response headers. Although the CR and LF characters are at the heart of an HTTP response splitting attack, other characters, such as ':' (colon) and '=' (equal), have special meaning in response headers as well. After you identify the correct points in an application to perform validation for Header Manipulation attacks and what special characters the validation should consider, the next challenge is to identify how your validation handles special characters. The application should reject any input destined to be included in HTTP response headers that contains special characters, particularly CR and LF, as invalid. Many application servers attempt to limit an application's exposure to HTTP response splitting vulnerabilities by providing implementations for the functions responsible for setting HTTP headers and cookies that perform validation for the characters essential to an HTTP response splitting attack. Do not rely on the server running your application to make it secure. For any developed application, there are no guarantees about which application servers it will run on during its lifetime. As standards and known exploits evolve, there are no guarantees that application servers will continue to stay in sync.

            Impacted code :

            
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
            log.info("export done");

            File file2 = new File(outFilePath);
            byte[] pdfContent = FileUtils.readFileToByteArray(file2);
//            log.info("@@@@  Excel pdfContent " + pdfContent);
            String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            String extention = ".xlsx";
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment;filename=" + filename + extention);
            OutputStream out1 = null;
            out1 = (OutputStream) response.getOutputStream();
            out1.write(pdfContent);
            out1.close();
            response.flushBuffer();
            String sts[] = filename.split("_");


help me to resolve the code.
