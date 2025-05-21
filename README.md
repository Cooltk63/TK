

                Vulnerability Description in Detail: Attackers might control data written to a spreadsheet when calling write() at AdminController.java line 1816, which could enable them to target users who open the file on certain spreadsheet processors.  
                Likely Impact: Attackers might control data written to a spreadsheet when calling write() at AdminController.java line 1816, which could enable them to target users who open the file on certain spreadsheet processors.  
                Recommendation : The best way to prevent injection attacks is with a level of indirection: create a list of legitimate values from which the user must select. With this approach, the user-provided input is never used directly to specify the resource name. In some situations, this approach is impractical because the set of legitimate resource names is too large or too hard to maintain. Programmers often resort to implementing a deny list in these situations. A deny list is used to selectively reject or escape potentially dangerous characters before using the input. However, any such list of unsafe characters is likely to be incomplete and will almost certainly become out of date. A better approach is to create a list of characters that are permitted to appear in the resource name and accept input composed exclusively of characters in the approved set. In the case of formula injection, it would be ideal to use an allow list to verify only alphanumeric characters are included. If this approach is not feasible, at least check a deny list to prevent the following characters: =, +, - and @.

                line of code impacted ::
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
