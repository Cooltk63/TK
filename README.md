DECLARE
    v_blob       BLOB;
    v_clob       CLOB;
    v_raw        RAW(2000);  -- Limit to 2000 bytes per chunk
    v_base64     CLOB;
    v_encoded    VARCHAR2(4000);
    v_length     NUMBER;
    v_offset     NUMBER := 1;
    v_chunk_size NUMBER := 2000;  -- 2000 bytes per chunk
BEGIN
    -- Retrieve BLOB data from table
    SELECT reports_data INTO v_blob
    FROM crs_reports
    WHERE ROWNUM = 1;

    -- Get total BLOB length
    v_length := DBMS_LOB.GETLENGTH(v_blob);

    -- Create temporary CLOB for Base64 output
    DBMS_LOB.CREATETEMPORARY(v_base64, TRUE);

    -- Process BLOB in smaller chunks
    WHILE v_offset <= v_length LOOP
        -- Extract RAW chunk from BLOB
        v_raw := DBMS_LOB.SUBSTR(v_blob, v_chunk_size, v_offset);

        -- Encode RAW chunk to Base64
        v_encoded := UTL_RAW.CAST_TO_VARCHAR2(UTL_ENCODE.BASE64_ENCODE(v_raw));

        -- Append Base64 data to CLOB
        DBMS_LOB.WRITEAPPEND(v_base64, LENGTH(v_encoded), v_encoded);

        -- Move to next chunk
        v_offset := v_offset + v_chunk_size;
    END LOOP;

    -- Output first 32767 characters only (DBMS_OUTPUT limit)
    DBMS_OUTPUT.PUT_LINE('Base64 Encoded Data (first 32767 chars):');
    DBMS_OUTPUT.PUT_LINE(DBMS_LOB.SUBSTR(v_base64, 32767, 1));

    -- Free temporary CLOB
    DBMS_LOB.FREETEMPORARY(v_base64);
END;
/