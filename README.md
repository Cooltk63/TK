DECLARE
    v_blob       BLOB;
    v_clob       CLOB;
    v_raw        RAW(32767);
    v_base64     CLOB;
    v_length     NUMBER;
    v_offset     NUMBER := 1;
    v_chunk_size NUMBER := 32767;
BEGIN
    -- Retrieve BLOB data from table
    SELECT crs_reports_data INTO v_blob
    FROM reports_submission_id
    WHERE ROWNUM = 1;

    -- Get total BLOB length
    v_length := DBMS_LOB.GETLENGTH(v_blob);

    -- Create temporary CLOB for Base64 output
    DBMS_LOB.CREATETEMPORARY(v_base64, TRUE);

    -- Process BLOB in chunks
    WHILE v_offset <= v_length LOOP
        -- Extract RAW chunk from BLOB
        v_raw := DBMS_LOB.SUBSTR(v_blob, v_chunk_size, v_offset);

        -- Encode RAW chunk to Base64 and append to CLOB
        DBMS_LOB.WRITEAPPEND(v_base64, 
            UTL_RAW.CAST_TO_VARCHAR2(UTL_ENCODE.BASE64_ENCODE(v_raw)));

        -- Move to next chunk
        v_offset := v_offset + v_chunk_size;
    END LOOP;

    -- Output the Base64 result
    DBMS_OUTPUT.PUT_LINE(v_base64);

    -- Free temporary CLOB
    DBMS_LOB.FREETEMPORARY(v_base64);
END;
/