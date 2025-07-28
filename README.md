SELECT
    iam_firm_empanelment.frn,
    iam_firm_empanelment.firm_name,
    iam_firm_empanelment.empanelment_type,
    iam_assignment_type_master.assignment_subtype AS "EMPANELMENT_SUB_TYPE",
    iam_firm_empanelment.poc_email,
    iam_firm_empanelment.contact_person,
    iam_firm_empanelment.mob_no,
    iam_assignment_type_master.assignment_type_id,
    iam_firm_empanelment.CIRCLE
FROM
    iam_firm_empanelment
JOIN
    iam_assignment_type_master
    ON iam_firm_empanelment.empanelment_sub_type = iam_assignment_type_master.assignment_type_id
LEFT JOIN
    iam_firm_master_details
    ON iam_firm_empanelment.frn = iam_firm_master_details.frn_no
WHERE
    iam_firm_empanelment.financial_year = :finyear
    AND iam_firm_empanelment.empaneled_status = 'YES'
    AND iam_firm_empanelment.empanelment_type = :emptype
    AND iam_firm_empanelment.empanelment_sub_type = :empSubtype
    AND (
        NVL(iam_assignment_type_master.ASSIGNMENT_TYPE_CIRCLE_TOGGLE, 'N') != 'T'
        OR iam_firm_empanelment.circle = :circle
    )


   This query returns the null or empty results  even if IAM_ASSIGNMENT_TYPE_MASTER has the rows data which columns EMPANELMENT_TYPE=SA , which circle =021 AND Also Against the FRN no data aslo present in IAM_FIRM_MASTER-DETAILS and in IAM_ASSIGNMENT_TYPE_MASTER aslo had the ASSIGNMENT_TYPE_ID=2 (Whichc is Passed empSubtype) and its column ASSIGNMENT_TYPE_CIRCLE_TOGGLE values is 'T'
   
   why this getting issue  or some logic mismatch 
