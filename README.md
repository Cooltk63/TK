@Override
    public int getPYData(Map<String, Object> map) {

        String qed = (String) map.get("qed");
        String reportId = (String) map.get("reportId");

        String year = qed.substring(6, 10);
        int prevYr = parseInt(year) - 1;
        String prevQed = "31/03/" + prevYr;
        AtomicInteger result = new AtomicInteger();
        String getData = "select ID, DESCE ,CY from ${tableName} where QED= TO_DATE(?,'dd/mm/yyyy') order by ID";

        Map<Object, Object> valuesMap = new HashMap<>();
        valuesMap.put("tableName", map.get("tableName"));
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        getData = sub.replace(getData);

        try {
            ArrayList<String> prevYData = jdbcTemplate.query(getData, new Object[]{prevQed}, rs -> {
                ArrayList<String> t_Data = new ArrayList<>();
                while (rs.next()) {
                    t_Data.add(rs.getString(1));
                    t_Data.add(rs.getString(2));
                    t_Data.add(rs.getString(3));

                    String updatePYData = "insert into ${tableName} (ID,DESCE,VAL_PY,QED,RPTID_FK)values(?,?,?,TO_DATE(?,'dd/mm/yyyy'),?)";
                    updatePYData = sub.replace(updatePYData);

                    int update = jdbcTemplate.update(updatePYData, rs.getString(1), rs.getString(2), rs.getString(3),
                            qed, reportId);
                    result.set(update);
                }
                return t_Data;
            });
        } catch (DataAccessException e) {
            log.error("DataAccessException Occurred :" + e.getMessage());

        }
        return result.get();
    }
