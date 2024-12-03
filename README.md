SELECT
    CRS_YAPL_TYPE type,
    CRS_YAPL_CODE || '-' || CRS_YAPL_HEADING code,
    CRS_YAPL_CGL || '-' || COALESCE(CRS_COMP.DESCRIPTION, '') CGL,
    CASE
        WHEN crs_yapl_addition IS NULL THEN crs_yapl_deduction
        WHEN crs_yapl_deduction IS NULL THEN crs_yapl_addition
        ELSE ''
    END AS transaction,
    CASE
        WHEN CRS_YAPL_TYPE IN ('ASSETS', 'EXPENDITURE') AND crs_yapl_addition IS NULL THEN 'Additions (Debit)'
        WHEN CRS_YAPL_TYPE IN ('ASSETS', 'EXPENDITURE') AND crs_yapl_deduction IS NULL THEN 'Deductions (Credit)'
        WHEN CRS_YAPL_TYPE IN ('LIABILITIES', 'INCOME') AND crs_yapl_addition IS NULL THEN 'Deductions (Debit)'
        WHEN CRS_YAPL_TYPE IN ('LIABILITIES', 'INCOME') AND crs_yapl_deduction IS NULL THEN 'Addition (Credit)'
        ELSE ''
    END AS transactionType,
    CRS_YAPL_REMARKS,
    CRS_YAPL_ID,
    'false'
FROM
    crs_moc_yapl
LEFT JOIN
    crs_comp ON CRS_COMP.cgl = crs_yapl_cgl AND CRS_COMP.COMPFLAG = '1' AND CRS_COMP.REPORT_MASTER_LIST_ID_FK = '697'
WHERE
    crs_moc_yapl.REPORT_MASTER_LIST_ID_FK = '697'
    AND CRS_YAPL_BRANCH = '07390'
    AND CRS_YAPL_DATE = TO_DATE('30/09/2024', 'dd/MM/yyyy');