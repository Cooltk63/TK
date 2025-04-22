  @Override
    public SFTPStatus getListData(String QED) {
            log.info("inside getListData DaoImpl ::");
            String query = "select CIRCLE_NAME, REPORT_STATUS, FILES_RECEIVED_STATUS, FILES_TIMESTAMP,REVISED_COUNT,ERROR_MESSAGE FROM BS_SFTPFILES_STATUS where QUARTER_DATE= to_date(?,'dd/mm/yyyy')";
            return jdbcTemplate.query(query, new Object[]{QED},
                    rs -> {
                        SFTPStatus sftpStatus= null;
                        while (rs.next()) {
                            sftpStatus = new SFTPStatus();
                            sftpStatus.setCircleName(rs.getString("CIRCLE_NAME"));
                            sftpStatus.setReportStatus(rs.getString("REPORT_STATUS"));
                            sftpStatus.setFileReceivedStatus(rs.getString("FILES_RECEIVED_STATUS"));
                            sftpStatus.setTimeStamp(rs.getString("FILES_TIMESTAMP"));
                            sftpStatus.setRevisedFileStatus(rs.getString("REVISED_COUNT"));
                            sftpStatus.setMessage(rs.getString("ERROR_MESSAGE"));
                        }
                        log.info("Returning Data ::"+sftpStatus.getCircleName() +"Report status :: "+sftpStatus.getReportStatus());

                        sftpStatus.setRevisedFileStatus(sftpStatus.getRevisedFileStatus().equalsIgnoreCase(String.valueOf(1))?"NO":"YES");
                        log.info("Final Files Revised COunt IS:::"+sftpStatus.getRevisedFileStatus());
                        return sftpStatus;
                    });

    }

    
