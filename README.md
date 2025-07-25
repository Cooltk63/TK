-- Fetching empaneled firm details conditionally filtered by circle
SELECT
    iam_firm_empanelment.frn,                          -- Firm Registration Number
    iam_firm_empanelment.firm_name,                    -- Firm Name
    iam_firm_empanelment.empanelment_type,             -- Empanelment Type
    iam_assignment_type_master.assignment_subtype AS "EMPANELMENT_SUB_TYPE",  -- Subtype Name
    iam_firm_empanelment.poc_email,                    -- Point of Contact Email
    iam_firm_empanelment.contact_person,               -- Contact Person
    iam_firm_empanelment.mob_no                        -- Mobile Number
FROM
    iam_firm_empanelment
JOIN
    iam_assignment_type_master
    ON iam_firm_empanelment.empanelment_sub_type = iam_assignment_type_master.assignment_type_id
LEFT JOIN
    iam_firm_master_details
    ON iam_firm_empanelment.frn = iam_firm_master_details.frn_no
WHERE
    -- Filter by financial year
    iam_firm_empanelment.financial_year = :finyear

    -- Only include empaneled firms
    AND iam_firm_empanelment.empaneled_status = 'YES'

    -- Filter by empanelment type and subtype
    AND iam_firm_empanelment.empanelment_type = :emptype
    AND iam_firm_empanelment.empanelment_sub_type = :empSubtype

    -- Conditionally apply circle filter only if assignment_type_circle is 'T'
    AND (
        NVL(iam_assignment_type_master.assignment_type_circle, 'N') != 'T'
        OR iam_firm_empanelment.circle = :circle
    )