DECLARE
    v_blob      BLOB;
    v_clob      CLOB;
    v_raw       RAW(32767);
    v_base64    CLOB;
    v_length    NUMBER;
BEGIN
    -- Retrieve BLOB data from table
    SELECT crs_reports_data INTO v_blob
    FROM reports_submission_id
    WHERE ROWNUM = 1;

    -- Get BLOB length
    v_length := DBMS_LOB.GETLENGTH(v_blob);

    -- Convert BLOB to RAW
    v_raw := DBMS_LOB.SUBSTR(v_blob, v_length, 1);

    -- Encode RAW to Base64
    v_base64 := UTL_RAW.CAST_TO_VARCHAR2(UTL_ENCODE.BASE64_ENCODE(v_raw));

    -- Print Base64 output
    DBMS_OUTPUT.PUT_LINE(v_base64);
END;
/