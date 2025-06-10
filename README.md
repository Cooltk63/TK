 public String getBrcodeforsftp(String circleCode,String rpname){


        String  result="";
        try {
            String query = "SELECT branchCode from BS_CCDP_FILES where CIRCLECODE=? and REPORTNAME=?";

            result = jdbcTemplate.queryForObject(query, new Object[]{ circleCode, rpname}, String.class);
            log.info("Result is :" + result);
        } catch (DataAccessException e) {
            log.error("Data Access Exception :" + e.getMessage());
        }

        return result;
    }
    public int checkCCDPTimeStamp(String circleCode,String qed, String fileType){
       String qry="select count(*) from BS_CCDPFILES_TIME where circlecode=? and QUARTER_DATE=to_date(?,'dd/mm/yyyy') AND FILE_TYPE=?";
        int count = jdbcTemplate.queryForObject(qry, new Object[]{circleCode,  qed,fileType }, Integer.class);
        log.info("count:-" + count);
        return count;
    }

    public String getCCDPTimeStamp(String circleCode,String qed, String fileType){
        String qry="select nvl(CCDP_timestamp,0) as CCDP_timestamp from BS_CCDPFILES_TIME where circlecode=? and QUARTER_DATE=to_date(?,'dd/mm/yyyy') AND FILE_TYPE=?";
        String timeSt = jdbcTemplate.queryForObject(qry, new Object[]{circleCode,  qed,fileType }, String.class);
        log.info("timeSt:-" + timeSt);
        return timeSt;
    }

    public int updateCCDPFiletime( String timeStamp,String circleCode,String qed,String  type ){
        String qry="";
        int countForCCDPTime= checkCCDPTimeStamp(circleCode,qed,type);
        if(countForCCDPTime >=1){
            qry="update BS_CCDPFILES_TIME set CCDP_timestamp=? where circlecode=? and quarter_date=to_date(?,'dd/mm/yyyy') and file_Type=? ";

        }else{
            qry="insert into BS_CCDPFILES_TIME (CCDP_timestamp, circlecode, quarter_date, file_type) " +
                    "VALUES (?, ?, to_date(?,'dd/mm/yyyy'), ?)";
        }
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);

        int count = jdbcTemplateObject.update(qry,
                timeStamp,circleCode,qed,type);
        log.info("count:-"+count);
        return count;
    }

    public int getCountdata(String circleCode,String qed,String  type ){
        String qry="";
        String rpName="";
        int countForCCDPTime= checkCCDPTimeStamp(circleCode,qed,type);
        if(type.equalsIgnoreCase("SC9")){
            qry="select count(*) from BS_SC9 where SC9_CIRCLE =? and  SC9_DATE =to_date(?,'dd/mm/yyyy')";

        }else{
            qry="select count(*) from BS_ANX2C where ANNX2C_CIRCLE=? and  ANNX2C_QED=to_date(?,'dd/mm/yyyy')";
        }
        JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);

        int count = jdbcTemplateObject.queryForObject(qry, new Object[]{circleCode, qed}, Integer.class);

        log.info("count:-"+count);
        return count;
    }
