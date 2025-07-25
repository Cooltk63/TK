-- Select required fields from the IAM_FIRM_EMPANELMENT and IAM_FIRM_MASTER_DETAILS tables,
-- along with the latest EMAIL_STATUS from IAM_EMAIL_LOGS table if available.
SELECT  
    IAM_FIRM_EMPANELMENT.FRN,                 -- Unique firm reference number
    IAM_FIRM_EMPANELMENT.FIRM_NAME,           -- Name of the firm
    IAM_FIRM_EMPANELMENT.EMPANELMENT_TYPE,    -- Type of empanelment
    IAM_FIRM_EMPANELMENT.EMPANELMENT_SUB_TYPE,-- Sub-type of empanelment
    IAM_FIRM_EMPANELMENT.POC_EMAIL,           -- Point of Contact email
    IAM_FIRM_EMPANELMENT.CONTACT_PERSON,      -- Name of contact person
    IAM_FIRM_EMPANELMENT.REF_NO,              -- Reference number for empanelment

    IAM_FIRM_MASTER_DETAILS.GSTN_NO,          -- GST number from master details
    IAM_FIRM_MASTER_DETAILS.ADDRESS,          -- Address of the firm
    IAM_FIRM_MASTER_DETAILS.CITY,             -- City of the firm

    -- Check if EMAIL_STATUS is present in the joined EMAIL_STATUS_VIEW subquery
    -- If yes, return the status; otherwise return 'FAILED'
    CASE 
        WHEN EMAIL_STATUS_VIEW.EMAIL_STATUS IS NOT NULL 
        THEN EMAIL_STATUS_VIEW.EMAIL_STATUS 
        ELSE 'FAILED' 
    END AS EMAIL_STATUS

FROM IAM_FIRM_EMPANELMENT

-- Left join to bring in extra firm details from master details table
LEFT JOIN IAM_FIRM_MASTER_DETAILS
    ON IAM_FIRM_EMPANELMENT.FRN = IAM_FIRM_MASTER_DETAILS.FRN_NO

-- Left join to fetch the most recent EMAIL_STATUS for the firm from IAM_EMAIL_LOGS
LEFT JOIN (
    SELECT FRN_NO, EMAIL_STATUS
    FROM IAM_EMAIL_LOGS
    -- Get only the latest log entry (maximum RMLID) per FRN_NO
    WHERE (FRN_NO, RMLID) IN (
        SELECT FRN_NO, MAX(RMLID)
        FROM IAM_EMAIL_LOGS
        GROUP BY FRN_NO
    )
) EMAIL_STATUS_VIEW
    -- Match the FRN_NO from email logs with either empanelment or master details FRN
    ON EMAIL_STATUS_VIEW.FRN_NO = IAM_FIRM_EMPANELMENT.FRN
    OR EMAIL_STATUS_VIEW.FRN_NO = IAM_FIRM_MASTER_DETAILS.FRN_NO

-- Filter based on year, status and empanelment types
WHERE IAM_FIRM_EMPANELMENT.FINANCIAL_YEAR = :finyear  
    AND IAM_FIRM_EMPANELMENT.EMPANELED_STATUS = 'YES' 
    AND IAM_FIRM_EMPANELMENT.EMPANELMENT_TYPE = :emptype 
    AND IAM_FIRM_EMPANELMENT.EMPANELMENT_SUB_TYPE = :empSubtype;