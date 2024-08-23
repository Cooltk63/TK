select  
        replace(replace(substr(ch.HEDGEINV_INVDATE, 0, 10), '-', '/'), '/19', '/2019') datedo,  
        ch.HEDGEINV_PAN,  
        ch.HEDGEINV_CUSTOMER,  
        ch.HEDGEINV_AMOUNT , 
        ch.HEDGEINV_ID AS RWOSEQ, 
        ch.HEDGEINV_ID, 
        'false' 
        from CRS_HEDGEINV ch  
        where ch.REPORT_MASTER_LIST_ID_FK='6864028'
        order by ch.HEDGEINV_ID
