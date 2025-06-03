 @Override
    public Integer createIfrsReport(JSONObject jsonObjData) {
        log.info("inside insert dao function");

        try {
            log.info("inside try block in insert dao function");
            log.info("rmId::;"+ jsonObjData.get("rmId"));
            log.info("rmId::;"+  (String) jsonObjData.get("rmId"));


            String insertQuery = "insert into IFRS_REPORTS(IFRS_REPORT_MASTER_ID_FK, CIRCLE, QED, MAKERID,STATUS) " +
                    "VALUES (?, ?, to_date(?, 'dd/mm/yyyy'), ?,?)";

            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(conn -> {
                String[] generatedColumns = {"ID"};
                PreparedStatement prepareStatement = conn.prepareStatement(insertQuery, generatedColumns);
                prepareStatement.setString(1, (String) jsonObjData.get("rmId"));
                prepareStatement.setString(2, (String) jsonObjData.get("circleCode"));
                prepareStatement.setString(3, (String) jsonObjData.get("qed"));
                prepareStatement.setString(4, (String) jsonObjData.get("userId"));
                prepareStatement.setString(5, "10");
                return prepareStatement;
            }, generatedKeyHolder);

            log.info("key generated" +generatedKeyHolder.getKey());
            Integer key = generatedKeyHolder.getKey().intValue();
            if(key>1){

            }

            return Objects.requireNonNull(generatedKeyHolder.getKey()).intValue();

        } catch (DataAccessException e) {
            log.info("inside catch block in insert dao function");
            log.error("DataAccessException Occurred" + e.getMessage());
            return 0;
        }
    }

    How do I resolve this issue of SQL injection here on

    PreparedStatement prepareStatement = conn.prepareStatement(insertQuery, generatedColumns); 

    this line. help me to resolve the security issue without changing the business logican dworking functionality

    
