 List<Map<String, Object>> firmList = (List<Map<String, Object>>) payload.get("firms");

 Received values inside here as List of Map's as per below

  "firms": [
        {
            "FIRM_NAME": "TRINITY ENT",
            "FRN_NO": "45687",
            "GSTN": "27AAHCK850D1ZK",
            "FIRM_ADDR": "NAVI MUMBAI, BELPAUR",
            "DATE": "31/03/2025",
            "REF_NO": "1001",
            "ASSIGNMENT_TYPE": "Statutory Audit",
            "EMAIL": "tushar.khade.cbstcs@sbi.co.in.com"
        }
        ,
        {
            "FIRM_NAME": "OMEGA & CO",
            "FRN_NO": "89456",
            "GSTN": "29AABCU9603R1ZR",
            "FIRM_ADDR": "BANGALORE, KARNATAKA",
            "DATE": "31/03/2025",
            "REF_NO": "1002",
            "ASSIGNMENT_TYPE": "Internal Audit",
            "EMAIL": "omega@example.com"
        },
        {
            "FIRM_NAME": "ALPHA CONSULTING",
            "FRN_NO": "10324",
            "GSTN": "07ABCDE1234F2Z5",
            "FIRM_ADDR": "DELHI NCR",
            "DATE": "31/03/2025",
            "REF_NO": "1003",
            "ASSIGNMENT_TYPE": "Branch Audit",
            "EMAIL": "alpha@example.com"
        }
    ]

    I alredy had the model entity class for this above data tell me how will i iterate this List of map data and save insdie db using the spring data jpa and java 17
