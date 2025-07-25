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
                JOIN iam_assignment_type_master ON iam_firm_empanelment.empanelment_sub_type = iam_assignment_type_master.assignment_type_id
                LEFT JOIN iam_firm_master_details ON iam_firm_empanelment.frn = iam_firm_master_details.frn_no
            WHERE
                    financial_year = :finyear
                AND empaneled_status = 'YES'
                AND empanelment_type = :emptype
                AND empanelment_sub_type = :empSubtype
