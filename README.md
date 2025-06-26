    public ResponseEntity<Map<String, Object>> generateTemplate(Map<String, Object> payload) {
        // Step 1: Extract template string from userData map
        Map<String, Object> userData=(Map<String, Object>) payload.get("user");
        Map<String, Object> dataMap=(Map<String, Object>) payload.get("data");

        log.info("UserData ::"+userData);
        log.info("DataMap ::"+dataMap);
        ResponseVO<String> responseVO = new ResponseVO<>();

//        String template = (String) userData.get("template"); // key must be "template"
//        String template = userData.get("template").toString();
        String template = sampleTemplate();

        if (template == null || template.trim().isEmpty()) {
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Template string is missing in userData map");
            responseVO.setResult(null);
            return new ResponseEntity(responseVO, HttpStatus.OK);
        }

        // Step 2: Convert dataMap <String, Object> to <String, String> safely
        Map<String, String> stringDataMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            if (entry.getValue() != null) {
                stringDataMap.put(entry.getKey(), entry.getValue().toString());
            }
        }
        // Step 3: Replace placeholders like {{KEY}} with actual values
        String filledText = replacePlaceholders(template, stringDataMap);

        // Step 5: Generate PDF using iText + XMLWorker
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            InputStream htmlStream = new ByteArrayInputStream(filledText.getBytes(StandardCharsets.UTF_8));
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, htmlStream, StandardCharsets.UTF_8);
            document.close();

            String result= Base64.getEncoder().encodeToString(outputStream.toByteArray());

            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Template Data Generated Successfully");
            responseVO.setResult(result);

        } catch (DocumentException | IOException e) {
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Failed to generate Template Data");
            responseVO.setResult(null);
        }


        // Step 6: Encode PDF bytes to Base64 and return
        return new ResponseEntity(responseVO, HttpStatus.OK) ;
    }

    while i am passing the HTMl template to this function getting the below error
    :
    Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: com.itextpdf.tool.xml.exceptions.RuntimeWorkerException: Invalid nested tag p found, expected closing tag br.] with root cause
com.itextpdf.tool.xml.exceptions.RuntimeWorkerException: Invalid nested tag p found, expected closing tag br.
	at com.itextpdf.tool.xml.XMLWorker.endElement(XMLWorker.java:134)
	at com.itextpdf.tool.xml.parser.XMLParser.endElement(XMLParser.java:403)
	at com.itextpdf.tool.xml.parser.state.ClosingTagState.process(ClosingTagState.java:70)



My Html Template as perr below:
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Empanelment Intimation</title>
    <style>
        body {
            font-family: Times New Roman, serif;
            font-size: 1.5em;
            line-height: 1.5;
            padding: 20px;
        }

        h2 {
            text-align: center;
        }

        p {
            margin: 10px 0;
        }
    </style>
</head>
<body>

<p>
    <b>Date:</b></p>
<p>
    <b>Ref. No.:</b></p>

<br/>
<br/>


<p><b>Name of the Firm:</b> {{FRN_NO}}</p>
<p><b>FRN No.:</b> {{FRN_NO}}</p>
<p><b>GSTIN No.:</b></p>
<p><b>Address of the Firm:</b></p>

<br/>
<br/>

<p> Madam/ Dear Sir,</p>

<p><b><u>EMPANELMENT OF THE FIRM{{FIRM_NAME}} INTIMATION</u></b></p>

<p>We are glad to inform you that your firm has been empanelled as (type of assignment) in our Bank.<br></p>

<p>2. This empanelment as (type of assignment) does not mean assignment of <br/>
    mandate in respect of any specific work. Assignment of specific mandate will be <br/>
    done and documented by the branch (es) by way of issuing separate letter of<br/>
	allotment of work.</p>

<p>3. You are advised to mention the reference no. of this letter in future
    <br/>correspondence with the branch/ bank. Please also mention this reference no.<br>
    while presenting any bill to the branch/ bank in respect of the assignment <br/>
    entrusted to your firm.</p>
<br/>
<br/>
<p>Yours faithfully,</p>

<p> (Authorised Signatory) {{REF_NO}}</p>

</body>
</html>

Help me to resolve this 
