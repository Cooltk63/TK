This is angularjs controller code
  dicgcReport.downloadGranularFiles = function () {
        console.log("Inside the download gran controller function");
        dicgcReportFactory.downloadGranularFile().then(function (response) {
            let file = new Blob([response], {
                type: 'text/csv'
            });
            saveAs(file, "Granular"+".csv");
        }, function (errResponse) {
            alert("Failed to Download CSV " ,errResponse);
        });
    };

    This is angularjs services.js


function downloadGranularFile() {
        let deferred = $q.defer();
        $http.post(REST_SERVICE_URI_25, {
            responseType: 'arraybuffer'
        }).then(function (response) {
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while downloading csv');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }

    This is java code
    @RequestMapping("/downloadGranular")
    public void downloadGranularCsv(HttpServletResponse response) {

        try {
            String PathFile=servletContext.getRealPath("/../WebContent/resources/document/granular.csv");
            // 🔽 This should point to your WebContent/resources/document/granular.csv
            File file = new File(PathFile);
            log.info("FILE Absoulute Path ::"+file.getAbsolutePath());

            if (!file.exists()) {
                log.info("File does not exist");
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
                return;
            }

            byte[] csvContent = FileUtils.readFileToByteArray(file);

            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment;filename=Granular.csv");

            OutputStream out = response.getOutputStream();
            out.write(csvContent);
            out.close();
            response.flushBuffer();

            log.info("Granular CSV downloaded successfully");

        } catch (FileNotFoundException e) {
            log.error("File not found exception occurred", e);
        } catch (IOException e) {
            log.error("IOException occurred", e);
        } catch (Exception e) {
            log.error("General exception occurred", e);
        }
    }

Console error I am getting
<4 Jun, 2025 12:57:56,424 AM IST> <Error> <HTTP> <BEA-101005> <[ServletContext@66701581[app:BS_5_4_war_exploded module:BS_5_4_war_exploded path:null spec-version:4.0]] getRealPath() called with unsafe path: "/../WebContent/resources/document/granular.csv".
weblogic.utils.io.FilenameEncoder$UnsafeFilenameException: F:\Projects\BSA Projects\BS_5.4\out\artifacts\WebContent\resources\document\granular.csv not under: F:\Projects\BSA Projects\BS_5.4\out\artifacts\BS_5_4_war_exploded
	at weblogic.utils.io.FilenameEncoder.getSafeFile0(FilenameEncoder.java:246)
	at weblogic.utils.io.FilenameEncoder.getSafeFile(FilenameEncoder.java:165)
	at weblogic.servlet.internal.WebAppServletContext.getRealPath(WebAppServletContext.java:799)
	at com.tcs.utils.DownloadFiles.downloadGranularCsv(DownloadFiles.java:41)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	Truncated. see log file for complete stacktrace
