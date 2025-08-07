Vulnerability
SQL Injection



Vulnerability Description in Detail
On line 480 of PnlVarDaoImpl.java, the method getMonthlyData() invokes a SQL query built with input that comes from an untrusted source. This call could allow an attacker to modify the statement's meaning or to execute arbitrary SQL commands.

Likely Impact
On line 139 of AdminDaoImpl.java, the method getCheckBranches() invokes a SQL query built with input that comes from an untrusted source. This call could allow an attacker to modify the statement's meaning or to execute arbitrary SQL commands.

Recommendation
The root cause of a SQL injection vulnerability is the ability of an attacker to change context in the SQL query, causing a value that the programmer intended to be interpreted as data to be interpreted as a command instead. When a SQL query is constructed, the programmer knows what should be interpreted as part of the command and what should be interpreted as data. Parameterized SQL statements can enforce this behavior by disallowing data-directed context changes and preventing nearly all SQL injection attacks. Parameterized SQL statements are constructed using strings of regular SQL, but where user-supplied data needs to be included, they include bind parameters, which are placeholders for data that is subsequently inserted. In other words, bind parameters allow the programmer to explicitly specify to the database what should be treated as a command and what should be treated as data. When the program is ready to execute a statement, it specifies to the database the runtime values to use for each of the bind parameters without the risk that the data will be interpreted as a modification to the command.




Source Code ::

         public List<PnlVar> getMonthlyData(String colName, String year) {


        String query1="select count("+colName+") from bs_monthly where MONTHLY_YEAR=?";
        List<PnlVar> monthlyData = new ArrayList<>();
        int count=jdbcTemplate.queryForObject(query1,new Object[]{year},Integer.class);

        if(count>0) {
            String query = "select MONTHLY_COMPCODE,MONTHLY_CGL," + colName + " from bs_monthly where MONTHLY_YEAR=?";

            monthlyData = jdbcTemplate.query(query, new Object[]{year}, new ResultSetExtractor<List<PnlVar>>() {
                @Override
                public List<PnlVar> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    List<PnlVar> pnl = new ArrayList<>();
                    PnlVar list = null;
                    while (resultSet.next()) {
                        list = new PnlVar();
                        list.setMthlyCol(resultSet.getBigDecimal(3));
                        list.setMthlyCgl(resultSet.getString("MONTHLY_CGL"));
                        list.setMthlyComp(resultSet.getString("MONTHLY_COMPCODE"));
                        pnl.add(list);
                    }
                    return pnl;
                }
            });


        }
        return monthlyData;
    }


    as I have recently used the solution for this problem as per below but it still saying the vulnerable

// SCR 2024-25: SQL Injection (Added the columns only allowed to execute)
    private static final Set<String> ALLOWED_COLUMNS = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList(
                   "VARI_REQ_ID",
                   "VARI_COMP_CODE",
                   "VARI_CGL",
                   "VARI_PERIOD_1",
                   "VARI_PERIOD_2",
                   "VARI_DIFF_2_1",
                   "VARI_PC_2_1",
                   "VARI_PERIOD_3",
                   "VARI_DIFF_3_2",
                   "VARI_PC_3_2",
                   "VARI_PERIOD_4",
                   "VARI_DIFF_4_3",
                   "VARI_PC_4_3",
                   "VARI_PERIOD_5",
                   "VARI_DIFF_5_4",
                   "VARI_PC_5_4",
                   "VARI_PERIOD_6",
                   "VARI_DIFF_6_5",
                   "VARI_PC_6_5",
                   "VARI_PERIOD_7",
                   "VARI_DIFF_7_6",
                   "VARI_PC_7_6",
                   "VARI_PERIOD_8",
                   "VARI_DIFF_8_7",
                   "VARI_PC_8_7",
                   "VARI_PERIOD_9",
                   "VARI_DIFF_9_8",
                   "VARI_PC_9_8",
                   "VARI_PERIOD_10",
                   "VARI_DIFF_10_9",
                   "VARI_PC_10_9",
                   "VARI_PERIOD_11",
                   "VARI_DIFF_11_10",
                   "VARI_PC_11_10",
                   "VARI_PERIOD_12",
                   "VARI_DIFF_12_11",
                   "VARI_PC_12_11"
            ))
    );

    public int bsPnlVarinsertion(List<PnlVar> finalData, int reqId, int varCount, String varCol) {
        List<Object[]> inputList1 = new ArrayList<Object[]>();

try {
    if (varCount == 1) {

        // SCR 2024-25: SQL Injection ::Validate the column name
        if (!ALLOWED_COLUMNS.contains(varCol)) {
            log.error("Attempt to use invalid column name: {}"+ varCol);
            return 0;
        }
        String insertQuery = "insert into bs_pnl_variation (VARI_REQ_ID,VARI_COMP_CODE,VARI_CGL," + varCol + ")values(?,?,?,?)";
        String amount="";
        for (PnlVar p : finalData) {
            if(p.getMthlyCol()==null) {
            p.setMthlyCol(new BigDecimal(0));
            }
            Object[] temp1 = {reqId, p.getMthlyComp(), p.getMthlyCgl(), p.getMthlyCol()};
            inputList1.add(temp1);
        }
        int i[]=jdbcTemplate.batchUpdate(insertQuery, inputList1);

    } else {
        List<PnlVar> updatedList = new ArrayList<>();
        String updatequery = "update bs_pnl_variation set  " + varCol + "=? where VARI_REQ_ID=? and VARI_COMP_CODE=? and VARI_CGL=?";
        for (PnlVar p : finalData) {
            PnlVar entry = null;
            String checkQuery = "select count(1) from  bs_pnl_variation  where VARI_CGL=? and VARI_REQ_ID=? ";
            int count = jdbcTemplate.queryForObject(checkQuery, new Object[]{p.getMthlyCgl(), reqId}, Integer.class);
            if (count == 0) {
                log.info("new cgl found for pnl_variation");
                entry = new PnlVar();
                entry.setMthlyCol(p.getMthlyCol());
                entry.setMthlyComp(p.getMthlyComp());
                entry.setMthlyCgl(p.getMthlyCgl());
                updatedList.add(entry);
            } else {

                if(p.getMthlyCol()==null) {
                    p.setMthlyCol(new BigDecimal(0));
                }
                Object[] temp1 = {p.getMthlyCol(), reqId, p.getMthlyComp(), p.getMthlyCgl()};
                inputList1.add(temp1);
            }
        }
      jdbcTemplate.batchUpdate(updatequery, inputList1);

        if (updatedList.size() > 0) {
            updatePnlVariation(updatedList, reqId, varCol);
        }
    }
}catch(DataAccessException se){
    log.info("Sql exception occured..");
   return 0;
}
return 1;
    }



    Tell what else I can do to resolve this issue.
