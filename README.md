 public RW10 getMakerReportScreenDetails(final RW10 report, String report_id) {
        RW10 reportUpdated = null;
        
        String query1 = "select rm.branch_code , rm.quarter,rm.financial_year,ax.CRS_INDU_DVLP_PROCFEE,ax.CRS_INDU_DVLP_OTHER,ax.CRS_INDU_DVLP_TOTAL,ax.CRS_INDU_DVLP_TOTAL_ADVANCES " +
                " from reports_master_list rm , CRS_INDU_DVLP_INC ax " +
                " where rm.report_id=ax.REPORT_MASTER_LIST_ID_FK and rm.report_id=? ";
        String query2 = "select rm.branch_code , rm.quarter,rm.financial_year,ax.CRS_INFRA_DVLP_PROCFEE,ax.CRS_INFRA_DVLP_OTHER,ax.CRS_INFRA_DVLP_TOTAL,ax.CRS_INFRA_DVLP_TOTAL_ADVANCES " +
                " from reports_master_list rm , CRS_INFRA_DVLP_INC ax " +
                " where rm.report_id=ax.REPORT_MASTER_LIST_ID_FK and rm.report_id=?";
        String query3 = "select rm.branch_code , rm.quarter,rm.financial_year,ax.CRS_AGRI_DVLP_PROCFEE,ax.CRS_AGRI_DVLP_OTHER,ax.CRS_AGRI_DVLP_TOTAL,ax.CRS_AGRI_DVLP_TOTAL_ADVANCES " +
                " from reports_master_list rm , CRS_AGRI_DVLP_INC ax " +
                " where rm.report_id=ax.REPORT_MASTER_LIST_ID_FK and rm.report_id=? ";
        String query4 = "select rm.branch_code , rm.quarter,rm.financial_year,ax.CRS_HOUS_DVLP_PROCFEE,ax.CRS_HOUS_DVLP_OTHER,ax.CRS_HOUS_DVLP_TOTAL,ax.CRS_HOUS_DVLP_TOTAL_ADVANCES " +
                "  from reports_master_list rm , CRS_HOUS_DVLP_INC ax " +
                " where rm.report_id=ax.REPORT_MASTER_LIST_ID_FK and rm.report_id=? ";
        try {
            reportUpdated = jdbcTemplateObject.query(query1, new Object[]{report_id}, new ResultSetExtractor<RW10>() {
                        @Override
                        public RW10 extractData(ResultSet rs1) throws SQLException, DataAccessException {
                            // TODO Auto-generated method stub
                            // List<Report> list=new ArrayList<Report>();
                            while (rs1.next()) {
                                report.setQuarter(rs1.getString(CommonConstants.QUARTER));
                                report.setYear(rs1.getString("financial_year"));
                                report.setBranchCode(rs1.getString("branch_code"));
                                report.setIndustrialDevelopmentProcessingFees(rs1.getString("CRS_INDU_DVLP_PROCFEE"));
                                report.setIndustrialDevelopmentOtherCharges(rs1.getString("CRS_INDU_DVLP_OTHER"));
                                report.setIndustrialDevelopmentTotal(rs1.getString("CRS_INDU_DVLP_TOTAL"));
                                report.setIndustrialDevelopmentTotalAdvance(rs1.getString("CRS_INDU_DVLP_TOTAL_ADVANCES"));
                            }
                            return report;
                        }
                    });
            reportUpdated = jdbcTemplateObject.query(query2, new Object[]{report_id}, new ResultSetExtractor<RW10>() {
                        public RW10 extractData(ResultSet rs2) throws SQLException, DataAccessException {
                            while (rs2.next()) {
                                report.setInfrastructureDevelopmentProcessingFees(rs2.getString("CRS_INFRA_DVLP_PROCFEE"));
                                report.setInfrastructureDevelopmentOtherCharges(rs2.getString("CRS_INFRA_DVLP_OTHER"));
                                report.setInfrastructureDevelopmentTotal(rs2.getString("CRS_INFRA_DVLP_TOTAL"));
                                report.setInfrastructureDevelopmentTotalAdvance(rs2.getString("CRS_INFRA_DVLP_TOTAL_ADVANCES"));
                            }
                            return report;
                        }
                    });
            reportUpdated = jdbcTemplateObject.query(query3, new Object[]{report_id}, new ResultSetExtractor<RW10>() {
                        public RW10 extractData(ResultSet rs3) throws SQLException, DataAccessException {
                            while (rs3.next()) {
                                report.setAgricultureDevelopmentProcessingFees(rs3.getString("CRS_AGRI_DVLP_PROCFEE"));
                                report.setAgricultureDevelopmentOtherCharges(rs3.getString("CRS_AGRI_DVLP_OTHER"));
                                report.setAgricultureDevelopmentTotal(rs3.getString("CRS_AGRI_DVLP_TOTAL"));
                                report.setAgricultureDevelopmentTotalAdvance(rs3.getString("CRS_AGRI_DVLP_TOTAL_ADVANCES"));
                            }
                            return report;
                        }
                    });
            reportUpdated = jdbcTemplateObject.query(query4, new Object[]{report_id}, new ResultSetExtractor<RW10>() {
                        public RW10 extractData(ResultSet rs4) throws SQLException, DataAccessException {
                            while (rs4.next()) {
                                report.setHousingDevelopmentProcessingFees(rs4.getString("CRS_HOUS_DVLP_PROCFEE"));
                                report.setHousingDevelopmentOtherCharges(rs4.getString("CRS_HOUS_DVLP_OTHER"));
                                report.setHousingDevelopmentTotal(rs4.getString("CRS_HOUS_DVLP_TOTAL"));
                                report.setHousingDevelopmentTotalAdvance(rs4.getString("CRS_HOUS_DVLP_TOTAL_ADVANCES"));
                            }
                            return report;
                        }
                    });
        } catch (Exception sqle) {

            log.error("Exception  " + sqle.getMessage());

        }
        return reportUpdated;
    }


    I need this code in using spring JPA as i am using the spring boot app give me result in  List<List<String>> form. for above provided code and create the model for each table or any logic to get the all the tables data into single object or anything suggestion
