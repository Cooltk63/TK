Vulnerability
SQL Injection

Vulnerability Description in Detail
On line 126 of AssetQualityDaoImpl.java, the method save() invokes a SQL query built using input potentially coming from an untrusted source. This call could allow an attacker to modify the statement's meaning or to execute arbitrary SQL commands.

Likely Impact
On line 44 of AdminLogDaoImpl.java, the method getResult() invokes a SQL query built using input potentially coming from an untrusted source. This call could allow an attacker to modify the statement's meaning or to execute arbitrary SQL commands.

Recommendation
The root cause of a SQL injection vulnerability is the ability of an attacker to change context in the SQL query, causing a value that the programmer intended to be interpreted as data to be interpreted as a command instead. When a SQL query is constructed, the programmer knows what should be interpreted as part of the command and what should be interpreted as data. Parameterized SQL statements can enforce this behavior by disallowing data-directed context changes and preventing nearly all SQL injection attacks. Parameterized SQL statements are constructed using strings of regular SQL, but when user-supplied data needs to be included, they create bind parameters, which are placeholders for data that is subsequently inserted. Bind parameters allow the program to explicitly specify to the database what should be treated as a command and what should be treated as data. When the program is ready to execute a statement, it specifies to the database the runtime values to use for the value of each of the bind parameters, without the risk of the data being interpreted as commands.

Code Impacted ::

 public int save(Map<String, Object> map) {
        log.info("in save function in AssetQualityDaoImpl");

        String query = null;
        try {
            query = "UPDATE FR_T40 SET ${COLUMN_NAME} = ? where T40_ID = ? and T40_RPTID_FK = ? " +
                    "and T40_QED= to_date(?,'dd/mm/yyyy')";
        } catch (RuntimeException e) {
            log.error("RuntimeException occurred: "+e.getMessage());
        }

        Map<Object, Object> valuesMap = new HashMap<>();
        valuesMap.put("COLUMN_NAME", map.get("columnName"));
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        query = sub.replace(query);

        return jdbcTemplate.update(
                query,
                map.get(FIELD_VALUE),
                map.get(ROW_ID),
                map.get(REPORT_ID),
                map.get(QUARTER_END_DATE)
        );
    }
