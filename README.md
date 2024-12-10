// Rejecting Report & Change RMl Status
@Transactional
@Override
public ResponseEntity rejectReport(Map<String, Object> map) {

    Map<String, String> loginUserData = (Map<String, String>) map.get("user");
    Map<String, Object> data = (Map<String, Object>) map.get("data");

    ResponseVO<Integer> responseVO = new ResponseVO<>();
    int submissionId = Integer.parseInt(data.get("submissionId").toString());
    int getAcceptStatus = 0;

    try {
        String status = "24"; // Setting the rejection status

        // Update the Report Submission table
        getAcceptStatus = reportSubRepo.acceptAndRejectReport(status, submissionId);
        log.info("Status of rejectReport data update in Report Submission: " + getAcceptStatus);

        // Setting entity for Workflow using setEntity method
        Workflow entity = setEntity("Rejected", data, loginUserData);

        if (getAcceptStatus > 0) {
            // Retrieve all Workflow records with the given submissionId
            List<Workflow> workflows = workflowRepository.findAllBySubmissionIdFK(submissionId);

            if (workflows == null || workflows.isEmpty()) {
                // No existing records, insert a new Workflow record
                log.info("No existing data found. Inserting a new record into workflow REJECT.");
                workflowRepository.save(entity); // Insert new workflow record
                log.info("Row inserted successfully.");
            } else {
                // Update all records with the given submissionId
                log.info("Existing data found. Updating all relevant records in workflow REJECT.");
                for (Workflow workflow : workflows) {
                    workflow.setStatus("Rejected"); // Update status to 'Rejected'
                    workflowRepository.save(workflow); // Save updated record
                    log.info("Updated workflow ID: " + workflow.getWorkflowId());
                }
            }
        }

        // Prepare response for successful operation
        responseVO.setStatusCode(HttpStatus.OK.value());
        responseVO.setMessage("Data updated successfully");
        responseVO.setResult(getAcceptStatus);

    } catch (Exception e) {
        // Handle exceptions and prepare error response
        log.info("Exception occurred while rejecting report");
        responseVO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseVO.setMessage("Exception Occurred: " + e.getMessage());
        log.info("Exception details: " + e.getMessage(), e);
        responseVO.setResult(getAcceptStatus);
    }

    return new ResponseEntity<>(responseVO, HttpStatus.OK);
}


////
@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, Long> {

    // Method to fetch all rows by submissionIdFK
    @Query("SELECT w FROM Workflow w WHERE w.submissionIdFK = :submissionId")
    List<Workflow> findAllBySubmissionIdFK(@Param("submissionId") int submissionId);
}