public int updatePrevYrValue(Map<String,Object >map){
      int update = 0;
      String qed = map.get("qed").toString();
      int prevYear = Integer.parseInt(qed.substring(6,10))-1;
      String preScheme = String.valueOf(prevYear - 1);

      String prevQed = "31/03/"+prevYear;
      String tableName = map.get(TABLE).toString();
      String columnName = tableName.split("_")[1];

      JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);

      String prevYearValue = "SELECT ${COLUMN_NAME}_PARAMID_FK,${COLUMN_NAME}_VAL_CY FROM ${prevyear}.${TABLE_NAME} " +
                "WHERE ${COLUMN_NAME}_QED = to_date(?,'dd/mm/yyyy') ORDER BY ${COLUMN_NAME}_PARAMID_FK";

      String updatePrevYearValue = "UPDATE ${TABLE_NAME} SET ${COLUMN_NAME}_VAL_PY = ? " +
                "WHERE ${COLUMN_NAME}_QED = to_date(?,'dd/mm/yyyy') AND ${COLUMN_NAME}_PARAMID_FK = ?";

      Map<Object, Object> valuesMap = new HashMap<>();
      valuesMap.put(COLUMN_NAME, columnName);
      valuesMap.put(TABLE_NAME, tableName);
      valuesMap.put("prevyear", "Q4" + preScheme);
      StrSubstitutor sub = new StrSubstitutor(valuesMap);

      prevYearValue = sub.replace(prevYearValue);
      updatePrevYearValue = sub.replace(updatePrevYearValue);

        List<Map<String,Object>> prevYearValues = null;
        try {
            prevYearValues = jdbcTemplateObject.queryForList(prevYearValue,prevQed);
        } catch (DataAccessException e) {
            log.error("DataException Occurred: "+e.getMessage());
        }

        try {
          for (Map<String, Object> mapRow : prevYearValues) {
              update+= jdbcTemplate.update(updatePrevYearValue,mapRow.get(columnName+"_VAL_CY"),qed,
                        mapRow.get(columnName+"_PARAMID_FK"));
          }
      } catch (DataAccessException e) {
          log.error("DataException Occurred: "+e.getMessage());
      }
        return update;
    }
