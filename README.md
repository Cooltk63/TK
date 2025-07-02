Console Output::
2025-07-02 :: 10:40:54.098 || ERROR:: EmailServiceImpl.java: | 189 | ::  Failed to log email status to DB
org.springframework.dao.DataIntegrityViolationException: could not execute statement [ORA-00001: unique constraint (FNSONLI.IAM_EMAIL_LOGS_PK) violated
] [insert into iam_email_logs (frn_email_data,email_date,email_remark,email_status,frn_email,frn_no,user_id,rml_id) values (?,?,?,?,?,?,?,?)]; SQL [insert into iam_email_logs (frn_email_data,email_date,email_remark,email_status,frn_email,frn_no,user_id,rml_id) values (?,?,?,?,?,?,?,?)]; constraint [FNSONLI.IAM_EMAIL_LOGS_PK]
	
Caused by: org.hibernate.exception.ConstraintViolationException: could not execute statement [ORA-00001: unique constraint (FNSONLI.IAM_EMAIL_LOGS_PK) violated
] [insert into iam_email_logs (frn_email_data,email_date,email_remark,email_status,frn_email,frn_no,user_id,rml_id) values (?,?,?,?,?,?,?,?)]
	
	... 22 common frames omitted
Caused by: java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique constraint (FNSONLI.IAM_EMAIL_LOGS_PK) violated

Caused by: oracle.jdbc.OracleDatabaseException: ORA-00001: unique constraint (FNSONLI.IAM_EMAIL_LOGS_PK) violated


