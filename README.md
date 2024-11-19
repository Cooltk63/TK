SELECT CASE
           WHEN MONTHS_BETWEEN(SYSDATE, TO_DATE('01-APR', 'DD-MON')) < 0
           THEN ADD_MONTHS(TO_DATE('01-APR', 'DD-MON'), -1)
           ELSE TO_DATE('01-APR', 'DD-MON')
       END AS financial_year_start
FROM DUAL;