-- Selecting empaneled firm details with optional circle-based filtering
SELECT
    iam_firm_empanelment.frn,                          -- Firm Registration Number
    iam_firm_empanelment.firm_name,                    -- Name of the Firm
    iam_firm_empanelment.empanelment_type,             -- Type of Empanelment (e.g., Infra, Housing)
    iam_assignment_type_master.assignment_subtype AS "EMPANELMENT_SUB_TYPE",  -- Human-readable subtype
    iam_firm_empanelment.poc_email,                    -- Point of Contact Email
    iam_firm_empanelment.contact_person,               -- Contact Person Name
    iam_firm_empanelment.mob_no                        -- Contact Mobile Number
FROM
    iam_firm_empanelment
    -- Join with assignment type master to fetch subtype name and circle applicability
JOIN
    iam_assignment_type_master
    ON iam_firm_empanelment.empanelment_sub_type = iam_assignment_type_master.assignment_type_id

    -- Optional join with firm master details (not used in SELECT but possibly useful for future filters)
LEFT JOIN
    iam_firm_master_details
    ON iam_firm_empanelment.frn = iam_firm_master_details.frn_no

WHERE
    -- Filter by financial year passed as a parameter
    iam_firm_empanelment.financial_year = :finyear

    -- Only include firms that are marked as empaneled
    AND iam_firm_empanelment.empaneled_status = 'YES'

    -- Filter by empanelment type (e.g., Infra, Housing) passed as a parameter
    AND iam_firm_empanelment.empanelment_type = :emptype

    -- Filter by empanelment sub-type (ID) passed as a parameter
    AND iam_firm_empanelment.empanelment_sub_type = :empSubtype

    -- Conditional circle-based filtering:
    -- 1. If the assignment type has assignment_type_circle != 'T', then include all firms regardless of circle.
    -- 2. If assignment_type_circle = 'T', then only include firms that match the given circle.
    AND (
        iam_assignment_type_master.assignment_type_circle != 'T'
        OR iam_firm_empanelment.circle = :circle
    )