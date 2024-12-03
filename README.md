SELECT
        CRS_YAPL_TYPE type,
        CRS_YAPL_CODE || '-' || CRS_YAPL_HEADING code ,
        CRS_YAPL_CGL || '-' || (SELECT
            DESCRIPTION
        FROM
            crs_comp,
            crs_moc_yapl
        where
            COMPFLAG = '1'
            and cgl = crs_yapl_cgl
            and REPORT_MASTER_LIST_ID_FK = '697') CGL,
        case
            when crs_yapl_addition is null
                then crs_yapl_deduction
            when crs_yapl_deduction is null
                then crs_yapl_addition
            else ''
    end as transaction,
    case
        when CRS_YAPL_TYPE = 'ASSETS'
        or CRS_YAPL_TYPE = 'EXPENDITURE'
        and crs_yapl_addition is null
            then 'Additions (Debit)'
        when CRS_YAPL_TYPE = 'ASSETS'
        or CRS_YAPL_TYPE = 'EXPENDITURE'
        and crs_yapl_deduction is null
            then 'Deductions (Credit)'
        when CRS_YAPL_TYPE = 'LIABILITIES'
        or CRS_YAPL_TYPE = 'INCOME'
        and crs_yapl_addition is null
            then 'Deductions (Debit)'
        when CRS_YAPL_TYPE = 'LIABILITIES'
        or CRS_YAPL_TYPE = 'INCOME'
        and crs_yapl_deduction is null
            then 'Addition (Credit)'
        else ''
end as transactionType,
CRS_YAPL_REMARKS,
CRS_YAPL_ID,
CRS_YAPL_ID,
'false'
FROM
    crs_moc_yapl
where
    REPORT_MASTER_LIST_ID_FK = '697'
    and CRS_YAPL_BRANCH = '07390'
    and CRS_YAPL_DATE = to_date('30/09/2024', 'dd/MM/yyyy');
