@Scheduled(cron = "0 25 16 * * *")
public void scheduleSFTP() throws ConfigurationException, SQLException {
    int pageSize = 1000;
    int pageNumber = 0;
    Page<CrsReports> page;

    do {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        page = crsReportsRepository.getData(pageable);

        List<CrsReports> reportsList = page.getContent();
        log.info("Fetched batch " + pageNumber + ", size: " + reportsList.size());

        if (!reportsList.isEmpty()) {
            schedularService.filesUpload(reportsList); // process current batch
        }

        pageNumber++;

    } while (page.hasNext());
}