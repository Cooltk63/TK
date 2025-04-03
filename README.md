DECLARE
    v_blob      BLOB;
    v_clob      CLOB;
    v_dest_offset NUMBER := 1;
    v_src_offset NUMBER := 1;
    v_amount NUMBER := DBMS_LOB.LOBMAXSIZE;
    v_base64    CLOB;
BEGIN
    -- Retrieve PDF BLOB from table
    SELECT crs_reports_data INTO v_blob
    FROM reports_submission_id 
    WHERE ROWNUM = 1;

    -- Create temporary CLOB to store Base64
    DBMS_LOB.CREATETEMPORARY(v_base64, TRUE);

    -- Convert BLOB to Base64 encoded CLOB
    v_base64 := UTL_RAW.CAST_TO_VARCHAR2(UTL_ENCODE.BASE64_ENCODE(DBMS_LOB.SUBSTR(v_blob, DBMS_LOB.GETLENGTH(v_blob), 1)));

    -- Print the Base64 output
    DBMS_OUTPUT.PUT_LINE(v_base64);

    -- Free temporary CLOB
    DBMS_LOB.FREETEMPORARY(v_base64);
END;
/