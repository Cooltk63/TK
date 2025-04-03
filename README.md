DECLARE
    v_clob CLOB;
    v_varchar VARCHAR2(32767);
BEGIN
    -- Convert BLOB to CLOB
    DBMS_LOB.CREATETEMPORARY(v_clob, TRUE);
    DBMS_LOB.CONVERTTOCLOB(
        dest_lob    => v_clob,
        src_blob    => (SELECT crs_reports_data FROM reports_submission_id WHERE rownum = 1), 
        amount      => DBMS_LOB.LOBMAXSIZE,
        dest_offset => 1,
        src_offset  => 1
    );

    -- Convert CLOB to VARCHAR2 (taking first 32,767 bytes)
    v_varchar := DBMS_LOB.SUBSTR(v_clob, 32767, 1);

    -- Print or use the result
    DBMS_OUTPUT.PUT_LINE(v_varchar);

    -- Free temporary CLOB
    DBMS_LOB.FREETEMPORARY(v_clob);
END;
/