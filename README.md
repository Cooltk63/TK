SELECT
    iam_firm_empanelment.frn,
    iam_firm_empanelment.firm_name,
    iam_firm_empanelment.empanelment_type,
    iam_assignment_type_master.assignment_subtype AS "EMPANELMENT_SUB_TYPE",
    iam_firm_empanelment.poc_email,
    iam_firm_empanelment.contact_person,
    iam_firm_empanelment.mob_no
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

    -- Only apply circle filter if assignment_type_circle = 'T'
    AND (
        iam_assignment_type_master.assignment_type_circle IS NULL
        OR iam_assignment_type_master.assignment_type_circle != 'T'
        OR iam_firm_empanelment.circle = :circle
    )