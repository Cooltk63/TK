public ResponseEntity downloadZip(Map<String,Object> payload)  {
        Map<String, Object> userData = (Map<String, Object>) payload.get("user");
        ResponseVO<String> responseVO = new ResponseVO<>();
        String FinYear = userData.get("financial_year").toString();
        Path filePath = Paths.get("/media", "IAM", FinYear, "IAM_Combined.zip");
        File file = filePath.toFile();

        log.info("Looking for file at: " + file.getAbsolutePath());

        byte[] fileContent = null;
        try {
            log.info("File does not exist: " + file.exists());
            if (!file.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            fileContent = Files.readAllBytes(filePath);
            if(fileContent.length==0 || fileContent == null){
                log.info("Corrupted File"+fileContent.length);
                responseVO.setStatusCode(HttpStatus.OK.value());
                responseVO.setMessage("File Corrupted");
                responseVO.setResult(null);
            }
        } catch (IOException e) {
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage(" Failed to downloaded Zip file");
            responseVO.setResult(Arrays.toString(fileContent));
        }

        responseVO.setStatusCode(HttpStatus.OK.value());
        responseVO.setMessage("Zip File downloaded successfully.");
        responseVO.setResult(fileContent.toString());

        return new ResponseEntity(responseVO, HttpStatus.OK);

    }


    Console Output :

    2025-06-25 :: 13:21:00.679 || INFO :: PdfTemplateServiceImpl.java: | 301 | ::  Looking for file at: F:\media\IAM\2024-25\IAM_Combined.zip
2025-06-25 :: 13:21:00.679 || INFO :: PdfTemplateServiceImpl.java: | 305 | ::  File does not exist: false


Right now I am testing on windows

Still getting this issue tell me how to resolve this issue use java 17 feature for efficiency or for lengthy code avoiding
