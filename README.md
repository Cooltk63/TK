This is my ANgular controller code ::

dicgcReport.downloadGranularFile = function () {
        console.log("Inside the download gran controller function")
        dicgcReportFactory.downloadGranularFile().then(function (response) {
            let file = new Blob([data], {
                type: 'text/csv'
            });
            saveAs(file, "Granular" + ".csv");
        }, function (errResponse) {
            alert("Failed to Download Excel " + errResponse);
        });
    };

    This is my angular ajax call for Backend ::
     function downloadGranularFile() {
        let deferred = $q.defer();
        $http.post(REST_SERVICE_URI_25, {
            responseType: 'text/csv'
        }).then(function (response) {
            deferred.resolve(response.data);
        }, function (errResponse) {
            console.error('Error while downloading csv');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    }

    This is backened java controller code
      @RequestMapping("/downloadGranular") 
    public void downloadMocFile(HttpServletResponse response) throws IOException {
        log.info("inside download granular");
        Path path = Paths.get(("/resources/document/moc.csv")).toAbsolutePath();
        log.info("Path is" + path );
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"moc.csv\"");
        StreamUtils.copy(resource.getInputStream(), response.getOutputStream());
    }

    for mapping I have already added dicgcreport to controller help to resolve issue what is actual issue with my thois code as of now I cannot download the file from path is there any simple way other than this as previolsy I am using thsi angular code for download any files

    Provided below code for using download pdf files just for reference 

     try {
            File file2 = new File(CleanPath.cleanString(outFilePath));
            byte[] pdfContent = FileUtils.readFileToByteArray(file2);
            String contentType = "application/pdf";
            String extention = ".pdf";
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment;filename=" + filename + extention);
            OutputStream out1 = null;
            out1 = response.getOutputStream();
            out1.write(pdfContent);
            out1.close();
            response.flushBuffer();

        } catch (FileNotFoundException e) {
            log.error("Filenot found exception occured " + e);
        } catch (Exception e) {
            log.error("Exception occured " + e);
        }
