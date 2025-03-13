SELECT  
            trm.REPORT_ID REPORT_ID, 
            trml.report_id SUBMISSION_ID, 
            trm.REPORT_NAME,
            trml.BRANCH_CODE as Branch,
            trm.REPORT_DESC, 
            trm.REPORT_JRXML_NAME, trm.RPT_NAME, 
            nvl(trml.NIL_REPORT_FLAG,'N') AS NIL_FLAG,
            trml.status current_status  
            from 
            tar_report_master trm
            join tar_reports_master_list trml 
            on (trml.report_master_id = trm.report_id
            and trml.quarter_date =to_date(:REPORT_DATE,'dd/MM/yyyy'))
            join tar_branch bm 
            on bm.branchno = trml.branch_code 
            and 
            region_code = :REGION_CODE 
            and 
            bm.CIRCLE_CODE = :circleCode
            where trm.report_order is not null and trm.report_level in ('B') and trml.status > 39 and trml.status <50;
