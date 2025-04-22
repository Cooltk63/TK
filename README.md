@Query(value = "SELECT cr.REPORTS_DATA, rs.FILE_PATH, cr.REPORTS_SUBMISSION_ID, cr.SFTP_STATUS " +
               "FROM CRS_REPORTS cr " +
               "LEFT JOIN REPORT_SUBMISSION rs ON cr.REPORTS_SUBMISSION_ID = rs.SUBMISSION_ID " +
               "WHERE cr.SFTP_STATUS = 0 AND rs.FILE_PATH IS NOT NULL",
       countQuery = "SELECT count(*) FROM CRS_REPORTS cr " +
                    "LEFT JOIN REPORT_SUBMISSION rs ON cr.REPORTS_SUBMISSION_ID = rs.SUBMISSION_ID " +
                    "WHERE cr.SFTP_STATUS = 0 AND rs.FILE_PATH IS NOT NULL",
       nativeQuery = true)
Page<CrsReports> getData(Pageable pageable);