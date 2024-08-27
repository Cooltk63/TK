  public RW49 getActualExpAmt(final RW49 report, String previousQed, String branchCode) {
        RW49 newReport = null;
        
        String query1 = "SELECT AD_BALANCE, AD_CGL FROM CRS_ADJDATA where AD_DATE=to_date(?,'dd/mm/yyyy') AND AD_BRANCH=?";
        try {
            newReport = jdbcTemplateObject.query(query1, new Object[] {previousQed, branchCode}, new ResultSetExtractor<RW49>() {
                @Override
                public RW49 extractData(ResultSet rs) throws SQLException, DataAccessException {
                    // Report report=null;
                    // TODO Auto-generated method stub
                    // List<Report> list=new ArrayList<Report>();
                    while (rs.next()) {
                        String cglNo = rs.getString("AD_CGL");
                        if(cglNo.equalsIgnoreCase("8455505001")){
                            report.setOfficeRentActualExp(rs.getString("AD_BALANCE"));
                        }
                        else if(cglNo.equalsIgnoreCase("8455505002")){
                            report.setHouseRentActualExp(rs.getString("AD_BALANCE"));
                        }
                        else if(cglNo.equalsIgnoreCase("8456505002")){
                            report.setTelephoneActualExp(rs.getString("AD_BALANCE"));
                        }
                        else if(cglNo.equalsIgnoreCase("8457505001")){
                            report.setElectricityActualExp(rs.getString("AD_BALANCE"));
                        }
                        else if(cglNo.equalsIgnoreCase("8462505001")){
                            report.setRepairsBankPropActualExp(rs.getString("AD_BALANCE"));
                        }
                    }
                    return report;
                }
            });
        } catch (DataAccessException sqle) {
            log.error("Exception  " + sqle.getMessage());
        }
        return newReport;
    }

    how do i write this method using jpa native query and using RW49serviceImpl file with mentioned condition inisde the method.
