SELECT  
                    IAM_FIRM_EMPANELMENT.FRN,  
                    IAM_FIRM_EMPANELMENT.FIRM_NAME, 
                    IAM_FIRM_EMPANELMENT.EMPANELMENT_TYPE, 
                     IAM_FIRM_EMPANELMENT.EMPANELMENT_SUB_TYPE,  
                    IAM_FIRM_EMPANELMENT.POC_EMAIL,  
                    IAM_FIRM_EMPANELMENT.CONTACT_PERSON,  
                    IAM_FIRM_EMPANELMENT.REF_NO,  
                    IAM_FIRM_MASTER_DETAILS.GSTN_NO,  
                    IAM_FIRM_MASTER_DETAILS.ADDRESS,  
                    IAM_FIRM_MASTER_DETAILS.CITY  
                     from  
                    IAM_FIRM_EMPANELMENT   
                    left join  
                    IAM_FIRM_MASTER_DETAILS    
                    on  
                    IAM_FIRM_EMPANELMENT.FRN=IAM_FIRM_MASTER_DETAILS.FRN_NO 
                    where FINANCIAL_YEAR=:finyear  
                    AND  
                    EMPANELED_STATUS='YES' 
                    AND 
                    EMPANELMENT_TYPE=:emptype 
                    AND 
                    EMPANELMENT_SUB_TYPE=:empSubtype 

                    i wanted to add one more column in result from IAM_EMAIL_LOGS Table  fetch column EMAIL_STATUS
                    which has the condtion based on FRN= IAM_FIRM_EMPANELMENT.FRN OR IAM_FIRM_MASTER_DETAILS.FRN_NO TABLE FRN AND  fetch the result where RMLID is max(Number) which is sequence or most recent.

                    
