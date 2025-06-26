<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <style>
        body {
            font-family: Times New Roman, serif;
            font-size: 14px;
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

<p><b>Date:</b> {{DATE}}</p>
<p><b>Ref. No.:</b> {{REF_NO}}</p>

<p><b>Name of the Firm:</b> {{FIRM_NAME}}</p>
<p><b>FRN No.:</b> {{FRN_NO}}</p>
<p><b>GSTIN No.:</b> {{GSTIN}}</p>
<p><b>Address of the Firm:</b> {{FIRM_ADDR}}</p>

<p>Madam/ Dear Sir,</p>

<p><b><u>EMPANELMENT OF THE FIRM {{FIRM_NAME}} - INTIMATION</u></b></p>

<p>We are glad to inform you that your firm has been empanelled as <b>{{ASSIGNMENT_TYPE}}</b> in our Bank.</p>

<p>This empanelment as <b>{{ASSIGNMENT_TYPE}}</b> does not mean assignment of mandate in respect of any specific work. Assignment of specific mandate will be done and documented by the branch(es) by way of issuing a separate letter of allotment of work.</p>

<p>You are advised to mention the reference no. of this letter in future correspondence with the branch/bank. Please also mention this reference no. while presenting any bill to the branch/bank in respect of the assignment entrusted to your firm.</p>

<p>Yours faithfully,</p>
<p><b>(Authorised Signatory)</b></p>

</body>
</html>