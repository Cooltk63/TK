SELECT 
    ROWNUM AS rownum, 
    *
FROM (
    SELECT 
        a.SRNO, 
        a.TITLE, 
        a.CATEGORY, 
        a.SUB_CATEGORY, 
        b.ID, 
        b.NEW_CATEGORY, 
        b.NEW_TITLE, 
        b.ACTION, 
        b.PFID, 
        b.FK_SRNO
    FROM 
        IFRS_INTRA_GROUP_LIABILITY a
    LEFT JOIN 
        IFRS_GROUP_CHANGE_REQUEST b
    ON 
        b.FK_SRNO = a.SRNO
    WHERE 
        a.SUB_CATEGORY = 'Associates' 
        AND a.STATUS = 'A'
    
    UNION ALL
    
    SELECT 
        NULL AS SRNO, 
        NULL AS TITLE, 
        NULL AS CATEGORY, 
        NULL AS SUB_CATEGORY, 
        b.ID, 
        b.NEW_CATEGORY, 
        b.NEW_TITLE, 
        b.ACTION, 
        b.PFID, 
        b.FK_SRNO
    FROM 
        IFRS_GROUP_CHANGE_REQUEST b
    WHERE 
        b.FK_SRNO NOT IN (SELECT a.SRNO FROM IFRS_INTRA_GROUP_LIABILITY a)
)
ORDER BY rownum;