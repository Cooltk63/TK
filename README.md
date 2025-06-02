    public int updatePrevYrCol(String qed) {
        int update = 0;
        try {
            int previousYear = INTEGER_VALUE(qed.substring(6, 10)) - 1;
            log.info("prevYear: " + previousYear);
            String previousQed = "31/03/" + previousYear;
            String preScheme = String.valueOf(previousYear - 1);

            String getT3Data = "SELECT T03_ID,T03_AMT_CAP_CY,T03_AMT_SHCAP_CY,T03_NOSHARE_CY from ${prevyear}.FR_T03 " +
                    "where T03_QED = to_date(?,'dd/mm/yyyy') ORDER BY T03_ID";

            Map<Object, Object> valuesMap = new HashMap<>();
            valuesMap.put("prevyear", "Q4" + preScheme);
            StrSubstitutor sub = new StrSubstitutor(valuesMap);
            getT3Data = sub.replace(getT3Data);

            JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);

            List<Map<String, Object>> prevYrValues = jdbcTemplateObject.queryForList(getT3Data, previousQed);
