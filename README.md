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
            log.info("Setted the headers content types");
            response.setHeader("Content-Disposition", "attachment;filename=Granular.csv");

            Files.copy(file.toPath(), response.getOutputStream());
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
    
