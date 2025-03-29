package com.crs.automaticReportsService.services;

/*import com.crs.cs.Model.CrsSts;
import com.crs.cs.Model.ReportMasterList;*/

import com.crs.automaticReportsService.ccdpintegration.CCDPCommonConstants;
import com.crs.automaticReportsService.models.CrsCcdpError;
import com.crs.automaticReportsService.models.CrsProperty;
import com.crs.automaticReportsService.repositories.CrsCcdpErrorRepository;
import com.crs.automaticReportsService.repositories.CrsNilCheckRepository;
import com.crs.automaticReportsService.repositories.CrsPropertyRepository;
import com.crs.automaticReportsService.utils.*;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageXYZDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

@Slf4j
@Service("AutomaticService")
public class AutomaticServiceImpl implements AutomaticService {

    @Autowired
    CrsCcdpErrorRepository crsCcdpErrorRepository;

    @Autowired
    CrsNilCheckRepository crsNilCheckRepository;

    @Autowired
    CrsPropertyRepository crsPropertyRepository;

    //static Logger log = Logger.getLogger(AutomaticServiceImpl.class.getName());

    String disMessage = CommonConstants.DISPLAYMESSAGE;

    @Override
    public ResponseEntity getPdfContent(Map<String, Object> map, HttpServletRequest request) {
        log.info("inside pdfcontent serviceImpl 111" + map);

        ResponseVO<Map<String, Object>> responseVO = new ResponseVO<>();
        Map<String, Object> mapData = new HashMap<>();

        try {
            // For getting additional Details other than User Details
            Map<String, Object> data = (Map<String, Object>) map.get("data");

            // For Logged User Data
            Map<String, String> loginUserData = (Map<String, String>) map.get("user");

            String reportMasterId = (String) data.get("reportMasterId");
            String branchCode = loginUserData.get("branch_code");
            String qed = loginUserData.get("quarterEndDate");
            String quarter = loginUserData.get("quarter");

            log.info("getQuaterFromQuaterEndDate >>> " + CommonFunction.getQuaterFromQuaterEndDate(qed));
            log.info("getPdfContent >>> " + CommonFunction.getFinancialYear());

            // String quater = CommonFunction.getQuater();
            // String financialYear = CommonFunction.getFinancialYear();
            // JSONObject dateccdpDate = getCcdpDate("Q4", "2024-25"); // check quarter
            JSONObject dateccdpDate = getCcdpDate(qed, quarter); // check quarter
            log.info("333 >>" + dateccdpDate);
            // 333 >> {"date":"20240630","ccdpDate":"30062024"}
            // 333 >>{"date":"250331","ccdpDate":"310325"}
            // 333 >>{"date":"20240930","ccdpDate":"30092024"}
            // 333 >>{"date":"20240930","ccdpDate":"30092024"}

            String rptNilFlag = getNilFlag(reportMasterId, loginUserData.get("branch_code"),
                    loginUserData.get("quarterEndDate"));

            log.info("444");
            JSONObject sftpFilePathDetails = sftpFilePathDetails(reportMasterId, loginUserData, data, dateccdpDate,
                    request);
            log.info("555");
            String displayMessage = sftpFilePathDetails.getString("displayMessage");
            String fileName = sftpFilePathDetails.getString("fileName");
            String fileNameForSftp = sftpFilePathDetails.getString("fileNameForSftp");
            String absPath = sftpFilePathDetails.getString("absPath");
            String outputAbsPath = sftpFilePathDetails.getString("outputAbsPath");
            System.out.println("@@@RA-05-09 : " + outputAbsPath);
            System.out.println("@@@RA-05-09 : " + absPath);
            boolean error = false;
            log.info("666");
            if (reportMasterId.equalsIgnoreCase("3041")) {
                int ccdpFlag = getDecryptRa05Files(reportMasterId, absPath, fileNameForSftp,
                        loginUserData.get("branch_code"));
                log.info(" CCDP Flag >>> " + ccdpFlag);
                log.info(" CommonConstants.CCDP_SUCCESS >>> " + CommonConstants.CCDP_SUCCESS);
                log.info("777" + java.util.Objects.equals(ccdpFlag, CommonConstants.SUCCESS));
                if (ccdpFlag != CCDPCommonConstants.SUCCESS) {
                    log.info("7755555555555557");
                    error = true;
                    // insert into database
                    writeCcdpSftpErrorLogs(loginUserData.get("branch_code"), loginUserData.get("quarterEndDate"),
                            ccdpFlag);
                    log.info("888");
                    if (ccdpFlag <= 4) {
                        log.info("999");
                        disMessage = CCDPCommonConstants.ERROR_WAIT_30_MIN;
                    } else {
                        log.info("101");
                        disMessage = CCDPCommonConstants.ERROR_CONTACT_CCDP;
                    }
                }
            }

            // boolean isFtpSuccess = false;
            if (!error) {

                String passKeyIp = CommonFunction.getServerIp(CommonConstants.POST_SERVER_IP_0);
                CrsProperty crsR = crsPropertyRepository.getCrsPropertiesByCrsServerIP(passKeyIp);
                log.info(" getServerPass crsR: " + crsR);
                log.info(" getServerPass crsR: " + crsR.getCrsCurrPass());

                boolean isFtpSuccess = Utilities.getFileFromSftp(fileNameForSftp, CommonConstants.POST_SERVER_IP_0,
                        absPath.substring(0, absPath.length() - fileNameForSftp.length()),
                        absPath.substring(0, absPath.length() - fileNameForSftp.length()), crsR.getCrsCurrPass());

                log.info("108");

                if (!isFtpSuccess) {
                    log.info("109");

                    mapData.put("rptNilFlag", rptNilFlag);
                    mapData.put("displayFlag", false); // doubt..........
                    mapData.put("disMessage", disMessage);
                    responseVO.setStatusCode(HttpStatus.OK.value());
                    responseVO.setMessage(disMessage);
                    responseVO.setResult(mapData);
                    return new ResponseEntity<>(responseVO, HttpStatus.OK);
                }
            }
            log.info(" CCDP Flag >>> " + absPath);
            File file = new File(absPath);
            log.info(" CCDP Flag >>> " + file);

            if (!file.exists() && !error) {
                error = true;
                log.info("102 : " + disMessage);
                disMessage = CommonConstants.DISPLAYMESSAGE;
            }
            log.info("102.1 : ");

            if (file.exists() && !error && reportMasterId.equalsIgnoreCase("3041")) {
                log.info("103");
                if (DecryptCCDP.compareHash(
                        absPath.substring(0, absPath.length() - fileNameForSftp.length()).replace("\\", "/")
                                + fileNameForSftp)) {
                    log.info("104");
                } else {
                    log.info("105");
                    error = true;
                    disMessage = CommonConstants.HASH_NOT_MATCH;
                }
            }
            log.info("106.0 : ");
            if (error) {
                // log.info("106 "+CommonConstants.DISPLAYMESSAGE);
                mapData.put("rptNilFlag", rptNilFlag);
                mapData.put("displayFlag", false); // doubt..........
                mapData.put("disMessage", disMessage);
                responseVO.setStatusCode(HttpStatus.OK.value());
                responseVO.setMessage(disMessage);
                responseVO.setResult(mapData);
                return new ResponseEntity<>(responseVO, HttpStatus.OK);
            }
            log.info("107");

            return getPdfFileContent(
                    reportMasterId,
                    loginUserData,
                    absPath,
                    outputAbsPath,
                    fileName,
                    request,
                    branchCode,
                    qed);
        } catch (IOException e) {
            log.info("110");
            mapData.put("displayFlag", false);
            log.info("IOException Occurred: " + e.getCause());
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage(e.getMessage());
            responseVO.setResult(mapData);
            return new ResponseEntity<>(responseVO, HttpStatus.OK);
        }

    }

    public JSONObject getCcdpDate(String qed, String quarter) {

        // String quarter = CommonFunction.getPreviousQuater();
        //String quarter = CommonFunction.getQuaterFromQuaterEndDate(qed);
        String financialYear = CommonFunction.getFinancialYear();
        String quaterEndDate = CommonFunction.getPreviousQuaterEndDate();
        log.info("financialYear :: " + financialYear);
        String year = financialYear.split("-")[0];
        log.info(" quarter :: "+ quarter);
        if (quarter.equalsIgnoreCase(CommonConstants.QUARTER_FOUR)) {
            year = financialYear.split("-")[1];
        }

        log.info("year :: " + year);

        JSONObject obj = new JSONObject();
        log.info(qed);
        log.info(quaterEndDate);
        log.info(quaterEndDate.split("-")[1] + year);
        obj.put("date", year + quaterEndDate.split("-")[0]);
        obj.put("ccdpDate", quaterEndDate.split("-")[1] + year);
        //obj.put("ccdpDate", qed.replace("/", ""));

        return obj;
    }

    public JSONObject getCcdpDate_OLD(String quarter, String financial_year) {

        // String quarterd = CommonFunction.getQuater();
        // String financialYear = CommonFunction.getFinancialYear();
        log.info("getQuaterEndDate >>> " + CommonFunction.getQuaterEndDate());

        // TODO : Calculate finacial year in java
        String year = "";
        JSONObject obj = new JSONObject();

        switch (quarter) {
            case CommonConstants.QUARTER_ONE -> {
                year = financial_year.split("-")[0];
                obj.put("date", year + "0630");
                obj.put("ccdpDate", "3006" + year);
            }
            case CommonConstants.QUARTER_TWO -> {
                year = financial_year.split("-")[0];
                obj.put("date", year + "0930");
                obj.put("ccdpDate", "3009" + year);
            }
            case CommonConstants.QUARTER_THREE -> {
                year = financial_year.split("-")[0];
                obj.put("date", year + "1231");
                obj.put("ccdpDate", "3112" + year);
            }
            case CommonConstants.QUARTER_FOUR -> {
                year = financial_year.split("-")[1];
                obj.put("date", year + "0331");
                obj.put("ccdpDate", "3103" + year);
            }
        }

        return obj;
    }

    public String getNilFlag(String reportMasterId, String branchCode, String quarter_end_date) {

        if (reportMasterId.equalsIgnoreCase("3041") ||
                reportMasterId.equalsIgnoreCase("3069") ||
                reportMasterId.equalsIgnoreCase("3071")) {
            int flag = getNilTableFlag(branchCode, quarter_end_date, reportMasterId);
            if (flag > 0) {
                return "N";
            } else {
                return "Y";
            }
        }
        return "N";
    }

    public int getNilTableFlag(String branchCode, String quarter_end_date, String reportMasterId) {
        return crsNilCheckRepository.getNilTableFlag(branchCode, quarter_end_date, reportMasterId);
    }

    public JSONObject sftpFilePathDetails(String reportMasterId, Map<String, String> loginUserData,
                                          Map<String, Object> data, JSONObject obj, HttpServletRequest request) {
        String fileName = "";
        String absPath = "";
        String fileNameForSftp = "";
        String outputAbsPath = "";
        disMessage = CommonConstants.DISPLAYMESSAGE;

        String branchCode = (String) loginUserData.get("branch_code");
        String qed = (String) loginUserData.get("quarterEndDate");
        String reportName = (String) data.get("reportName");

        JSONObject objSftpData = new JSONObject();

        switch (reportMasterId) {
            case "3071" -> {
                disMessage = CommonConstants.MESSAGE_IFAMS;
                fileName = loginUserData.get("branch_code") + "_" + obj.get("date") + "_IFAMS_SCH10.PDF";
                // absPath = AutoClean.cleanedPath("ifams", fileName, "", request);
                absPath = AutoClean.cleanedPath(AutoClean.IFAMS, branchCode, qed, fileName, "");
                fileNameForSftp = fileName;
            }
            case "2001" -> {
                disMessage = CommonConstants.MESSAGE_YSA;
                fileName = loginUserData.get("branch_code") + "_newya.txt";
                fileNameForSftp = fileName + ".gz";

                // absPath = AutoClean.cleanedPath(AutoClean.F1, fileNameForSftp, "", request);
                absPath = AutoClean.cleanedPath(AutoClean.F1, branchCode, qed, fileNameForSftp, "");

                // outputAbsPath = AutoClean.cleanedPath(AutoClean.F1, fileName, "", request);
                outputAbsPath = AutoClean.cleanedPath(AutoClean.F1, branchCode, qed, fileName, "");
            }
            case "2003" -> {
                disMessage = CommonConstants.MESSAGE_PNL;
                fileName = loginUserData.get("branch_code") + "_newpl.txt";
                fileNameForSftp = fileName + ".gz";

                // absPath = AutoClean.cleanedPath(AutoClean.F1, fileNameForSftp, "", request);
                absPath = AutoClean.cleanedPath(AutoClean.F1, branchCode, qed, fileNameForSftp, "");

                // outputAbsPath = AutoClean.cleanedPath(AutoClean.F1, fileName, "", request);
                outputAbsPath = AutoClean.cleanedPath(AutoClean.F1, branchCode, qed, fileName, "");
            }
            case "3067" -> {
                disMessage = CommonConstants.MESSAGE_CCUB;
                fileName = loginUserData.get("branch_code") + "_" + obj.get("date") + "_car2.txt";

                // absPath = AutoClean.cleanedPath(AutoClean.CCUBE, fileName, "", request);
                absPath = AutoClean.cleanedPath(AutoClean.CCUBE, branchCode, qed, fileName, "");

                // outputAbsPath = AutoClean.cleanedPath(AutoClean.CCUBE, fileName, "",
                // request);
                outputAbsPath = AutoClean.cleanedPath(AutoClean.CCUBE, branchCode, qed, fileName, "");

                fileNameForSftp = fileName;
            }
            case "3069" -> {
                disMessage = CommonConstants.MESSAGE_CCUB;
                fileName = loginUserData.get("branch_code") + "_" + obj.get("date") + "_car3.txt";

                // absPath = AutoClean.cleanedPath(AutoClean.CCUBE, fileName, "", request);
                absPath = AutoClean.cleanedPath(AutoClean.CCUBE, branchCode, qed, fileName, "");

                // outputAbsPath = AutoClean.cleanedPath(AutoClean.CCUBE, fileName, "",
                // request);
                outputAbsPath = AutoClean.cleanedPath(AutoClean.CCUBE, branchCode, qed, fileName, "");

                fileNameForSftp = fileName;
            }
            case "3041" -> {
                log.info("obj.get(ccdpDate)    >>>>>>>>>>> " + obj.get("ccdpDate"));
                disMessage = CommonConstants.MESSAGE_CCDP;
                fileName = loginUserData.get("branch_code") + "_CCDP_" + obj.get("ccdpDate") + "_AUDIT_SUMMARY.txt";
                // fileName = loginUserData.get("branch_code") + qed + reportName + ".txt";
                fileNameForSftp = fileName + ".gz";

                // absPath = AutoClean.cleanedPath(AutoClean.CCDP, fileNameForSftp, "",
                // request);
                absPath = AutoClean.cleanedPath(AutoClean.CCDP, branchCode, qed, fileNameForSftp, "");

                // String createdFileName = loginUserData.get("branch_code") + reportName +
                // ".txt";
                // outputAbsPath = AutoClean.cleanedPath(AutoClean.CCDP, fileName, "", request);
                outputAbsPath = AutoClean.cleanedPath(AutoClean.CCDP, branchCode, qed, fileName, "");

                // fileName = loginUserData.get("branch_code") + "_" + "20240630" + "_" +
                // reportName + ".txt";
                System.out.println("###RA-05-09 : " + outputAbsPath); // /media/CRS/AUTOMATIC/CCDP/20240630\00437_CCDP_30062024_AUDIT_SUMMARY.txt
                System.out.println("###RA-05-09 : " + absPath); // /media/CRS/AUTOMATIC/CCDP/20240630\00437_CCDP_30062024_AUDIT_SUMMARY.txt.gz
            }
        }

        objSftpData.put("displayMessage", disMessage);
        objSftpData.put("fileName", fileName);
        objSftpData.put("fileNameForSftp", fileNameForSftp);
        objSftpData.put("absPath", absPath);
        objSftpData.put("outputAbsPath", outputAbsPath);

        return objSftpData;
    }

    private int getDecryptRa05Files(@RequestParam("reportMasterId") String reportMasterId, String absPath,
                                    String fileNameForSftp, String branchCode) throws IOException {
        System.out.println("777777 : " + absPath);
        System.out.println("777777 branchCode : " + branchCode);

        String passKeyIp = CommonFunction.getServerIp(CommonConstants.POST_SERVER_IP_0);
        CrsProperty crsR = crsPropertyRepository.getCrsPropertiesByCrsServerIP(passKeyIp);
        log.info(" getServerPass crsR: " + crsR);
        log.info(" getServerPass crsR: " + crsR.getCrsCurrPass());

        return DecryptCCDP.getDecryptedCCDPFile(
                fileNameForSftp,
                absPath.substring(0, absPath.length() - fileNameForSftp.length()).replace("\\", "/"),
                CommonConstants.POST_SERVER_IP_0,
                branchCode,
                crsR.getCrsCurrPass());
    }

    public int writeCcdpSftpErrorLogs(String branchCode, String quaterDate, int errorMsg) {
        int result = 0;
        System.out.println("inside writeCcdpSftpErrorLogs >>>");
        try {
            // Parse the quarterEndDate from the user data
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date parsedDate = dateFormat.parse(quaterDate);

            // crsCcdpErrorRepository.insertData(branchCode, quaterDate, errorMsg);
            CrsCcdpError crsCcdpError = new CrsCcdpError();
            crsCcdpError.setErrBranch(branchCode);
            crsCcdpError.setErrQed(parsedDate);
            crsCcdpError.setErrCode(String.valueOf(errorMsg));
            crsCcdpErrorRepository.save(crsCcdpError);
        } catch (Exception e) {
            System.out.println("Exception occurred : " + e.getMessage() + " : " + e.getCause());
        }
        System.out.println("@@@result >>>> " + result);
        return result;
    }

    public ResponseEntity getPdfFileContent(String reportMasterId, Map<String, String> loginUserData, String absPath,
                                            String outputAbsPath, String fileName, HttpServletRequest request, String branchCode, String qed) {
        log.info("reportMasterId >>>> " + reportMasterId);
        log.info("loginUserData >>>> " + loginUserData);
        log.info("absPath >>>> " + absPath);
        log.info("fileName >>>> " + fileName);
        log.info("branchCode >>>> " + branchCode);
        log.info("qed >>>> " + qed);
        log.info("reportMasterId >>>> " + reportMasterId);
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

                // String tempoutFilePath = AutoClean.cleanedPath(AutoClean.CREATED, "", "",
                // request);
                String tempoutFilePath = AutoClean.cleanedPath(AutoClean.CREATED, branchCode, qed, "", "");

                log.info("tempoutFilePath >>>> " + tempoutFilePath);
                log.info("114");
                File file1 = new File(tempoutFilePath);
                if (!file1.exists()) {
                    log.info("115");
                    file1.mkdirs();
                }
                log.info("116");
                String serverFilePath = CleanPath.cleanString(tempoutFilePath + File.separator + fileName);
                log.info("serverFilePath >>> " + serverFilePath);
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
                //Document pdfDoc = new Document();
                //PdfDocument pdfDoc = new PdfDocument();
                log.info("2222222222  " + fileName);
                log.info("2222222222  " + fileName.split("\\.")[0]);

                String pdfFileName = CleanPath
                        .cleanString(tempoutFilePath + File.separator + fileName.split("\\.")[0] + ".pdf");
                log.info("333333 >> " + pdfFileName);
                log.info("333333 >> " + System.currentTimeMillis());

                // The requested operation cannot be performed on a file with a user-mapped
                // section open
                // String tempFileName = CleanPath.cleanString(tempoutFilePath + File.separator
                // + System.currentTimeMillis() + ".txt");
                // OutputStream outPdfWriter = new FileOutputStream(tempFileName);
                // outPdfWriter.flush();
                // outPdfWriter.close();

                /*
                 * File f = new File(pdfFileName);
                 * if(f.exists() && !f.isDirectory()) {
                 * log.info("===============");
                 * f.deleteOnExit();
                 * //f.delete();
                 * log.info("===============");
                 * }
                 */

                log.info("44444444555555555555566666666666666");

                // OutputStream outPdfWriter = new FileOutputStream(pdfFileName);
                // outPdfWriter = new FileOutputStream(pdfFileName);
                log.info("444444445555555555555");

                //PdfWriter writer = PdfWriter.getInstance(pdfDoc, new FileOutputStream(pdfFileName));
                // PdfWriter writer = PdfWriter.getInstance(pdfDoc, outPdfWriter);

                //PdfWriter writer = new PdfWriter(pdfFileName);
                //PdfDocument pdfDocument = new PdfDocument(writer);
                //Document document = new Document(pdfDocument);


                //////////////////////////////////////////////////////////////////////////////////////

                log.info("44444");
               /* pdfDoc.open();
                log.info("120");
                Font myFont = new Font(Font.FontFamily.COURIER, 11);
                Font bold_font = new Font();
                myFont.setSize(6);
                pdfDoc.add(new Paragraph("\n"));*/


               /* PdfFont pdfFont = PdfFontFactory.createFont(StandardFonts.COURIER);
                PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
                document.add(new Paragraph("\n").setFont(pdfFont));
                log.info("55555555");*/

              /*  FileInputStream fileInput = new FileInputStream(serverFile);
                DataInputStream dis = new DataInputStream(fileInput);
                InputStreamReader isr = new InputStreamReader(dis);
                BufferedReader br = new BufferedReader(isr);*/
                log.info("555555555666666666666");

                List<String> lines = new ArrayList<>();
                try (
                        FileInputStream fileInput = new FileInputStream(serverFile);
                        DataInputStream dis = new DataInputStream(fileInput);
                        InputStreamReader isr = new InputStreamReader(dis);
                        BufferedReader br = new BufferedReader(isr);
                ) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        log.info("line >>>>>>>>>>>>>>>> " + line);
                        lines.add(line);
                    }
                }
                log.info("555555555666666666666777777777777777 " + pdfFileName);
                try (
                        //PdfReader reader = new PdfReader(absPath);
                        PdfWriter writer = new PdfWriter(pdfFileName);
                        PdfDocument pdfDocument = new PdfDocument(writer);
                        Document document = new Document(pdfDocument);
                ) {
                    log.info("ssssssssssss55555555");
                    PdfFont pdfFont = PdfFontFactory.createFont(StandardFonts.COURIER);
                    PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
                    document.add(new Paragraph("\n").setFont(pdfFont));
                    log.info("55555555");

                    String strLine;
                    //while ((strLine = br.readLine()) != null) {
                    for (String line : lines) {
                        Paragraph para = new Paragraph(line + "\n").setFont(pdfFont).setTextAlignment(TextAlignment.JUSTIFIED);
                        //para.setAlignment(Element.ALIGN_JUSTIFIED);
                        document.add(para);
                    }
                    //document.close();
                } catch (Exception e) {
                    log.info("dfsdffafadsfdasfadsf");
                    //throw new RuntimeException(e);
                }


                //writer.close();
                log.info("666666666666");
                // outPdfWriter.flush();
                // outPdfWriter.close();

                // String checkOutFilePath = AutoClean.cleanedPath(AutoClean.REPORTMASTERID,
                // fileName, reportMasterId, request);
                // String checkOutFilePath = AutoClean.cleanedPath(AutoClean.REPORTMASTERID,
                // branchCode, qed, fileName, reportMasterId);
                String checkOutFilePath = AutoClean.cleanedPath(AutoClean.REPORTMASTERID, branchCode, qed, fileName,
                        reportMasterId);

                log.info("122.1 >>>>" + checkOutFilePath);
                log.info("pdfFileName : " + pdfFileName);
                PdfReader reader = new PdfReader(pdfFileName);




                /*PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(checkOutFilePath));
                log.info("122.2");
                Map<String, String> info = reader.getInfo();
                info.put("uniqueBr",
                        loginUserData.get("branch_code") + "_" + reportMasterId + "_" + loginUserData.get("qed"));
                stamper.setMoreInfo(info);
                stamper.close();
                reader.close();*/


               /* PdfWriter pdfWriter = new PdfWriter(checkOutFilePath);
                PdfDocument pdfDocument1 = new PdfDocument(pdfWriter);
                PdfDocumentInfo pdfDocumentInfo = pdfDocument1.getDocumentInfo();
                pdfDocumentInfo.setMoreInfo("uniqueBr", loginUserData.get("branch_code") + "_" + reportMasterId + "_" + loginUserData.get("qed"));
                pdfDocument1.close();*/


                ///////////////////////////////////////////////////
                try (
                        //PdfReader reader = new PdfReader(absPath);
                        PdfWriter writer = new PdfWriter(checkOutFilePath);
                        PdfDocument pdfDocument = new PdfDocument(writer);
                        Document document = new Document(pdfDocument);
                ) {
                    log.info("ssssssssssss55555555");
                    PdfFont pdfFont = PdfFontFactory.createFont(StandardFonts.COURIER);
                    PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
                    document.add(new Paragraph("\n").setFont(pdfFont));
                    log.info("55555555");

                    String strLine;
                    //while ((strLine = br.readLine()) != null) {
                    for (String line : lines) {
                        Paragraph para = new Paragraph(line + "\n").setFont(pdfFont).setTextAlignment(TextAlignment.JUSTIFIED);
                        //para.setAlignment(Element.ALIGN_JUSTIFIED);
                        document.add(para);
                    }
                    //document.close();
                } catch (Exception e) {
                    log.info("dfsdffafadsfdasfadsf");
                    //throw new RuntimeException(e);
                }
                ////////////////////////////////////////////////////////////////////////


                log.info("122.3");
                File file2 = new File(checkOutFilePath);
                log.info("122.4");
                byte[] pdfContent = FileUtils.readFileToByteArray(file2);
               /* fileInput.close();
                dis.close();
                isr.close();
                br.close();*/
                log.info("122.5");

                // adding bookmarks starts
                if (!checkForBookmarks(pdfContent)) {
                    log.info("Bookmarks Does Not Exist");
                    pdfContent = addBookmarks(pdfContent);
                } else {
                    log.info("Bookmarks Exist");
                }
                // ends
                map.put("pdfContent", pdfContent);
                map.put("displayFlag", true);
                map.put("disMessage", null);
                log.info("123");
            } else {
                log.info("124");
                // String checkOutFilePath = AutoClean.cleanedPath(AutoClean.REPORTMASTERID,
                // fileName, reportMasterId, request);
                String checkOutFilePath = AutoClean.cleanedPath(AutoClean.REPORTMASTERID, branchCode, qed, fileName,
                        reportMasterId);

                log.info("125");
                // String checkPath = AutoClean.cleanedPath(AutoClean.CREATED, "", "", request);
                String checkPath = AutoClean.cleanedPath(AutoClean.CREATED, branchCode, qed, "", "");

                log.info("126");
                File checkFile = new File(checkPath);
                if (!checkFile.exists()) {
                    log.info("127");
                    checkFile.mkdirs();
                }


                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                try (
                        PdfReader reader = new PdfReader(absPath);
                        PdfWriter writer = new PdfWriter(checkOutFilePath);
                        //FileOutputStream fos = new FileOutputStream(checkOutFilePath);
                        PdfDocument pdfDoc = new PdfDocument(reader, writer)
                ) {
                    PdfDocumentInfo info = pdfDoc.getDocumentInfo();
                    info.setMoreInfo("uniqueBr", loginUserData.get("branch_code") + "_" + reportMasterId + "_" + loginUserData.get("qed"));
                    //stamper.setMoreInfo(info);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                /////////////////////////////////////////////

                ///////////////////////////////////////////////////////////////////////////////////////////////////////

                //////////////////////////
                File file2 = new File(checkOutFilePath);
                byte[] pdfContent;
                log.info("129");
                pdfContent = FileUtils.readFileToByteArray(file2);


                // adding bookmarks starts
                if (!checkForBookmarks(pdfContent)) {
                    log.info("Bookmarks Does Not Exist");
                    pdfContent = addBookmarks(pdfContent);
                } else {
                    log.info("Bookmarks Exist");
                }
                // ends

                map.put("pdfContent", pdfContent);
                map.put("disMessage", null);
                map.put("displayFlag", true);
                log.info("130");
                //////////////////////
               /* String fileNamePdf = "check.pdf";
                // String checkPreRequisite = AutoClean.cleanedPath(AutoClean.DOCUMENT,
                // fileNamePdf, "", request);
                String checkPreRequisite = AutoClean.cleanedPath(AutoClean.DOCUMENT, branchCode, qed, fileNamePdf, "");
                log.info("checkPreRequisite >>> " + checkPreRequisite);
                File preCheck = new File(checkPreRequisite);
                byte[] check;
                log.info("131");
                check = FileUtils.readFileToByteArray(preCheck);

                map.put("check", check);
                log.info("132");*/
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

        }/* catch (DocumentException e) {
            log.info("135");
            map.put("displayFlag", false);
            log.info("DocumentException Occurred: " + e.getCause());
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage("Getting Error Message " + e.getMessage());
            responseVO.setResult(map);

        }*/ catch (Exception e) {
            map.put("displayFlag", false);
            e.printStackTrace();

            log.info("Exception Occurred: " + e.getCause());

            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage("Getting Error Message " + e.getMessage());
            responseVO.setResult(map);
            // log.error(" IOException : " + e.getMessage());
        }
        log.info("Final Result");
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    public static byte[] addBookmarks(byte[] pdfBytes) {
        PDDocument doc = null;
        try {
            doc = PDDocument.load(pdfBytes);
            PDPage lastPage = doc.getPage(doc.getNumberOfPages() - 1);
            PDDocumentOutline outline = doc.getDocumentCatalog().getDocumentOutline();
            if (outline == null) {


                outline = new PDDocumentOutline();
                doc.getDocumentCatalog().setDocumentOutline(outline);
            }
            PDPageDestination destination1 = new PDPageXYZDestination();
            PDOutlineItem wrapperOutlineItem = new PDOutlineItem();
            destination1.setPage(lastPage);
            float lastPageHeight = lastPage.getMediaBox().getHeight();
            float lastPageWidth = lastPage.getMediaBox().getWidth();

            float lastPageBoxLowerLeftX = lastPage.getMediaBox().getLowerLeftX();
            float lastPageBoxLowerLeftY = lastPage.getMediaBox().getLowerLeftY();

            ((PDPageXYZDestination) destination1).setLeft((int) (lastPageBoxLowerLeftX + 20));
            ((PDPageXYZDestination) destination1).setTop((int) (lastPageBoxLowerLeftY + 50));

            PDPageDestination destination2 = new PDPageXYZDestination();
            destination2.setPage(lastPage);
            ((PDPageXYZDestination) destination2).setLeft((int) (lastPageWidth - 130 - 20));
            ((PDPageXYZDestination) destination2).setTop((int) (lastPageBoxLowerLeftY + 50));


            PDOutlineItem bookMark1 = new PDOutlineItem();
            bookMark1.setDestination(destination1);
            bookMark1.setTitle("CheckerSign");
            wrapperOutlineItem.addLast(bookMark1);

            PDOutlineItem bookMark2 = new PDOutlineItem();
            bookMark2.setDestination(destination2);
            bookMark2.setTitle("AuditorSign");
            wrapperOutlineItem.addLast(bookMark2);

            outline.addLast(wrapperOutlineItem);


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            doc.save(baos);
            doc.close();
            return baos.toByteArray();
        } catch (IOException e) {
            log.error("Exception :: " + e.getMessage());
            return null;
        } finally {
            try {
                if (doc != null) {
                    doc.close();
                }
            } catch (IOException e) {
                log.error("IO Exception :: " + e.getMessage());
            }
        }

    }


    public static boolean checkForBookmarks(byte[] pdfBytes) {
        int bookMarkCount = 0;
        ByteArrayInputStream bais = new ByteArrayInputStream(pdfBytes);
        PDDocument document = null;
        try {
            document = PDDocument.load(bais);
            PDDocumentOutline outline = document.getDocumentCatalog().getDocumentOutline();

            if (outline != null) {
                PDOutlineItem currentItem = outline.getFirstChild().getFirstChild();

                while (currentItem != null) {


                    PDDestination destination = currentItem.getDestination();

                    if (destination instanceof PDPageDestination) {
                        bookMarkCount++;
                    }
                    currentItem = currentItem.getNextSibling();
                }
            }
            document.close();
        } catch (IOException e) {
            log.error("IO Exception :: " + e.getMessage());
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    log.error("IO Exception :: " + e.getMessage());
                }
            }
            return bookMarkCount > 0;
        }
    }


}
