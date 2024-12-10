This is teh error we getting from my code output in vs code 
2024-12-11T00:06:11.602+05:30  INFO 32464 --- [roWorklistService] [nio-8083-exec-2] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-12-11T00:06:11.603+05:30  INFO 32464 --- [roWorklistService] [nio-8083-exec-2] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-12-11T00:06:11.607+05:30  INFO 32464 --- [roWorklistService] [nio-8083-exec-2] o.s.web.servlet.DispatcherServlet        : Completed initialization in 4 ms
2024-12-11T00:06:11.634+05:30 DEBUG 32464 --- [roWorklistService] [nio-8083-exec-2] org.hibernate.SQL                        : 
    update
        REPORT_SUBMISSION
    set
        CURRENT_STATUS=?
    where
        SUBMISSION_ID=?
Hibernate:
    update
        REPORT_SUBMISSION
    set
        CURRENT_STATUS=?
    where
        SUBMISSION_ID=?
2024-12-11T00:06:11.659+05:30  INFO 32464 --- [roWorklistService] [nio-8083-exec-2] c.c.r.services.roWorklistServiceImpl     : Status of rejectReport data update in Report Submission :1
2024-12-11T00:06:11.666+05:30 DEBUG 32464 --- [roWorklistService] [nio-8083-exec-2] org.hibernate.SQL                        : 
    select
        w1_0.workflow_id,
        w1_0.action_by,
        w1_0.last_action,
        w1_0.last_action_dt,
        w1_0.pending_with,
        w1_0.remarks,
        w1_0.status,
        w1_0.submission_id_fk
    from
        workflow w1_0
    where
        w1_0.submission_id_fk=?
Hibernate:
    select
        w1_0.workflow_id,
        w1_0.action_by,
        w1_0.last_action,
        w1_0.last_action_dt,
        w1_0.pending_with,
        w1_0.remarks,
        w1_0.status,
        w1_0.submission_id_fk
    from
        workflow w1_0
    where
        w1_0.submission_id_fk=?
2024-12-11T00:06:11.679+05:30  INFO 32464 --- [roWorklistService] [nio-8083-exec-2] c.c.r.services.roWorklistServiceImpl     : exception Occurred while rejecting report
2024-12-11T00:06:11.681+05:30  INFO 32464 --- [roWorklistService] [nio-8083-exec-2] c.c.r.services.roWorklistServiceImpl     : Exception OccurredQuery did not return a unique result: 2 results were returned
2024-12-11T00:06:11.682+05:30  INFO 32464 --- [roWorklistService] [nio-8083-exec-2] c.c.r.services.roWorklistServiceImpl     : Exception Occurred 111org.hibernate.NonUniqueResultException: Query did not return a unique result: 2 results were returned

below is the source code for above  error 


// Rejecting Report & Change RMl Status
    @Transactional
    @Override
    public ResponseEntity rejectReport(Map<String, Object> map) {

        Map<String, String> loginUserData = (Map<String, String>) map.get("user");
        Map<String, Object> data = (Map<String, Object>) map.get("data");

        ResponseVO<Integer> responseVO = new ResponseVO();
        int submissionId=Integer.parseInt(data.get("submissionId").toString());
        int getAcceptStatus=0;
        try {

            String status = "24";

            getAcceptStatus = reportSubRepo.acceptAndRejectReport(
                    status,
                    submissionId);

            log.info("Status of rejectReport data update in Report Submission :"+getAcceptStatus);

            // Setting Entity Data for Query
            Workflow entity=setEntity("Rejected",data,loginUserData);


            // If SubmissionId Existed in Table
            if(getAcceptStatus>0){
                // Insert/Update WorkFlow Table

                Workflow entity2=workflowRepository.findBySubmissionIdFK(submissionId);

                log.info("entity2 values SubmissionIdFK :"+entity2.getSubmissionIdFK());
                log.info("entity2 values Status:"+entity2.getStatus());
                if(entity2 ==null)
                {
                    log.info("No Existing data found Inserting new record into workflow REJECT");
                    // Inserting New Row for workFlow
                    workflowRepository.save(entity);
                    log.info("Row Inserted Successfully");
                }
                else{
                    log.info(" Existing data found updating existing record into workflow REJECT");
                    // Else part for Updating Existing Entity Value
                    entity2.setStatus("Rejected");
                    workflowRepository.save(entity2);
                    log.info("Row Updated Successfully");
                }



            }

            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("data updated successfully");
            responseVO.setResult(getAcceptStatus);

        } catch (Exception e) {
            log.info("exception Occurred while rejecting report");
            responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseVO.setMessage("Exception Occurred" + e.getMessage());
            log.info("Exception Occurred" + e.getMessage());
            log.info("Exception Occurred 111" + e.getCause());
            responseVO.setResult(getAcceptStatus);
        }
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }



below is the repository query i am running 

 // Query need to be changed
    @Modifying
    @Query(nativeQuery = true,value = "update REPORT_SUBMISSION set CURRENT_STATUS=:CURRENT_STATUS where SUBMISSION_ID=:SUBMISSION_ID")
   int acceptAndRejectReport(
                        @Param("CURRENT_STATUS") String CURRENT_STATUS,
                        @Param("SUBMISSION_ID") int SUBMISSION_ID);
