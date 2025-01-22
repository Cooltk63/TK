SELECT to_char(AX.MODE_PAYMENT_DT,'yyyy-mm-dd') as MODE_PAYMENT_DT,  
           MODE_NATURE, MODE_PAYEE,  
           NVL(MODE_PAN,' ')MODE_PAN,  
           CASE MODE_TYPEBGL 
           WHEN '1' THEN 'Debited/ Credited to P&L BGL'  
           WHEN '2' THEN 'Debited/ Credited to Balance Sheet BGL'  
           END AS MODE_TYPEBGL, 
           NVl(MODE_AMT,0)MODE_AMT,  
           NVl(MODE_REASON,' ')MODE_REASON,  
           NVL(FILE_NAME, ' ')FILE_NAME,  
           MODE_ID,
           MODE_ID AS MROW_ID,'false'  
           FROM TAR_REPORTS_MASTER_LIST RM ,TAR_MODE AX  
           left outer join TAR_FILE fx on fx.TAR_RML_FK=ax.TAR_RML_FK and fx.FILE_SEQ_ID=ax.MODE_ID   
           WHERE RM.REPORT_ID=AX.TAR_RML_FK AND RM.REPORT_ID='1777772' ORDER BY AX.MODE_ID;

           This query returnng the 2 output ros even if i only had a single entry inside db aginast the subkissionID
           
