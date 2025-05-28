Vulnerability
Often Misused: File Upload

Vulnerability Description in Detail
A parameter of type org.springframework.web.multipart.MultipartFile  in BranchGstinController.java on line 69 is used by the Spring MVC framework to set uploaded files. Permitting users to upload files can allow attackers to inject dangerous content or malicious code to run on the server.


Likely Impact
A parameter of type org.springframework.web.multipart.MultipartFile  in AdminController.java on line 1078 is used by the Spring MVC framework to set uploaded files. Permitting users to upload files can allow attackers to inject dangerous content or malicious code to run on the server.

Recommendation
Do not accept attachments if they can be avoided. If a program must accept attachments, then restrict the ability of an attacker to supply malicious content by only accepting the specific types of content the program expects. Most attacks that rely on uploaded content require that attackers be able to supply content of their choosing. Placing restrictions on the content the program will accept will greatly limit the range of possible attacks. Check file names, extensions, and file content to make sure they are all expected and acceptable for use by the application. Make it difficult for the attacker to determine the name and location of uploaded files. Such solutions are often program-specific and vary from storing uploaded files in a directory with a name generated from a strong random value when the program is initialized to assigning each uploaded file a random name and tracking them with entries in a database.

code impacted :
 @RequestMapping(value = "/uploadGSTIN", method = RequestMethod.POST, headers = "Content-Type=multipart/form-data", produces = MediaType.TEXT_PLAIN)
    public @ResponseBody String uploadGSTIN(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "circleCode") String circleCode, @RequestParam(value = "quarterEndDate") String quarterEndDate, HttpServletRequest request) throws IOException, ConfigurationException {
        List<GSTIN> list = new ArrayList<>();
        //log.info("inside uploadFile......"+file.getContentType());


        //log.info("OLD file name " + file.getOriginalFilename());
        Configuration config = new PropertiesConfiguration("common.properties");
        String pathOfGSTINError = config.getProperty("REPORT_HOME_DIR") + "GSTIN" + File.separator + quarterEndDate.replaceAll("/", "");

        String GSTINFileName = "GSTIN_" + CommonFunction.getStringDate() + ".csv";
        String errorFileName = "ErrorGSTIN_" + CommonFunction.getStringDate() + ".txt";

        String displayMessage = CommonConstant.DEFAULT_ERROR_MESSAGE;
        List<GSTIN> validGSTINList = new ArrayList<>();
        List<String> errorList = new ArrayList<>();
        boolean gstInfo = true;
        /*(FileReader reader = new FileReader(file)*/
        try {
            InputStream is = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line = null;

            GSTIN gbean = null;
            List<GSTIN> gstinList = branchGstinDao.gstinListMethod();

            // SCR-2024-25 Vulnerability : Denial of Service
            // Line length limit (e.g., 5000 characters)
            int maxLineLength = 5000;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > maxLineLength) {
                    log.error("File is too large to read : Possible DoS attack");
                    //throw new IOException("File is too large to read : Possible DoS attack");
                    return null;
                }
                if (validatePattern(line, errorList, gstinList)) {


                    String[] items = line.split(",");
                    for (GSTIN gstin : gstinList) {
                        if (items[1].equalsIgnoreCase(gstin.getGSTIN())) {
                            items[1] = gstin.getGstinId();
                            break;
                        }
                    }
                    gbean = setGSTINBean(items);
                    validGSTINList.add(gbean);
                }
            }

            //Collections.sort(validGSTINList);
            //log.info("valid datalist--- " + validGSTINList.size() + "  ...  " + validGSTINList.toString());
            //log.info("error list--- " + errorList.size() + "  ...  " + errorList.toString());

        } catch (IOException e) {
            log.error("Exception Occurred " + e.getMessage());
        }


        int count = 0;
        int errorCount = 0;

        int updatedCount = 0;


        count = validGSTINList.size();

        errorCount = errorList.size();


        if (errorList.isEmpty() || validGSTINList.size() > 0) {


            updatedCount = branchGstinDao.saveGSTINBranchWise(validGSTINList);
            //log.info("insertedCount*************  " + count);
            gstInfo = false;

        } else {
            ////log.info("error line 1");
            int tempErrorCount = errorList.size();
            for (GSTIN gbean : validGSTINList) {
                addErrorLineToErrorList(errorList, gbean.toString(), "No error");
            }
            int tempValidCount = validGSTINList.size();
            validGSTINList.clear();
        }

        count = validGSTINList.size();
        //validCount = validList.size();
        errorCount = errorList.size();

        if (gstInfo) {
            ////log.info("error line 2");
            String errorGSTINpath = pathOfGSTINError + File.separator + errorFileName;

        }


        if (updatedCount == 0 && validGSTINList.isEmpty()) {
            ////log.info("error line 3");
            for (GSTIN gbean : validGSTINList) {
                addErrorLineToErrorList(errorList, gbean.toString(), "Some thing went wrong: Please check GSTIN file not uploaded and try again ");
            }
        }

        if (updatedCount != count) {
            //log.info("all valid entries are not updated ");
            for (GSTIN gstin : validGSTINList) {
                addErrorLineToErrorList(errorList, gstin.toString(), "Branch is not found ");
            }

        }

        String errorString = "";
        // Write Error file.

        if (!errorList.isEmpty()) {
            ////log.info("error line 4");
            writeGSTINErrorFile(false, pathOfGSTINError, errorList, GSTINFileName, errorFileName);
            //log.info(pathOfGSTINError+"Error File Written " +errorFileName);
            errorString = pathOfGSTINError + File.separator + errorFileName;
        }

        if (errorCount == 0 && (updatedCount == validGSTINList.size())) {
            displayMessage = "All GSTI Numbers  are updated for branches successfully";
        } else if (errorCount > 0 && count == 0) {
            ////log.info("error line 5");
            displayMessage = "Entries are not updated due to errors. Kindly check the Error File, after making corrections, upload the GSTIN again" + "~" + errorString;
        }
        if (errorCount > 0 && updatedCount > 0) {
            ////log.info("error line 6");
            displayMessage = "Entries are partially updated due to errors. Kindly check the Error File, after making corrections, upload again." + "~" + errorString;
        }
        return CleanPath.cleanString(displayMessage);
    }
