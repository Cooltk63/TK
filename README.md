INSERT INTO YOUR_TABLE_NAME (
    REQUEST_ID, UCN_NO, REQUEST_CREATED_TIMESTAMP, REQUEST_RESOLVED_TIMESTAMP,
    CONTACT_PERSON, ADDRESS, MOB_NO, FIRM_TYPE, POC_DESIGNATION, PIN_CODE,
    CITY, GSTN_NO, PAN_NO, FIRM_NAME, REQUEST_EMPANELMENT_SUB_TYPE,
    FRN_NO, REQUEST_EMPANELMENT_TYPE, REQUEST_TYPE, REQUEST_BATCH_ID,
    REQUEST_CREATED_BY, REQUEST_RESOLVED_BY, REQUEST_STATUS, STATE, DISTRICT, POC_EMAIL
) VALUES
-- Row 1
(100001, 123456789012345678, SYSTIMESTAMP, SYSTIMESTAMP + 2,
 'Amit Shah', '12 MG Road, Pune', '9876543210', 'LLP', 'Partner', '411001',
 'Pune', '27AAECS1234F1Z3', 'ABCDE1234F', 'Tech Solutions LLP', 'Engineering',
 'FRN1001', 'Fresh', 'Online', 'BATCH001', 'admin', 'approver1', 'Approved', 'Maharashtra', 'Pune', 'amit.shah@example.com'),

-- Row 2
(100002, 223456789012345679, SYSTIMESTAMP - 3, SYSTIMESTAMP - 1,
 'Sneha Patil', '5th Floor, Infotech Park, Nagpur', '9123456789', 'Private Ltd', 'Manager', '440001',
 'Nagpur', '27BBECS4321D2Z4', 'BCDPE4321F', 'InnovaTech Pvt Ltd', 'IT Services',
 'FRN1002', 'Renewal', 'Offline', 'BATCH002', 'user1', 'approver2', 'Pending', 'Maharashtra', 'Nagpur', 'sneha.patil@example.com'),

-- Row 3
(100003, 323456789012345680, SYSTIMESTAMP - 5, NULL,
 'Rahul Verma', 'Sector 21, Noida', '9988776655', 'Proprietorship', 'Owner', '201301',
 'Noida', '09AABCU3456M1Z2', 'AZTVE9876L', 'Verma Traders', 'Retail',
 'FRN1003', 'Fresh', 'Online', 'BATCH003', 'admin', NULL, 'In Progress', 'Uttar Pradesh', 'Gautam Buddha Nagar', 'rahul.verma@example.com'),

-- Row 4
(100004, 423456789012345681, SYSTIMESTAMP - 1, SYSTIMESTAMP,
 'Priya Nair', 'MG Road, Ernakulam', '9753124680', 'LLP', 'CEO', '682016',
 'Ernakulam', '32AACCN1234F2Z1', 'CQTVF2345P', 'GreenTech LLP', 'Consultancy',
 'FRN1004', 'Renewal', 'Offline', 'BATCH004', 'user2', 'approver3', 'Rejected', 'Kerala', 'Ernakulam', 'priya.nair@example.com'),

-- Row 5
(100005, 523456789012345682, SYSTIMESTAMP - 10, SYSTIMESTAMP - 7,
 'Alok Kumar', 'Near Rajendra Nagar, Patna', '9812345678', 'Partnership', 'Partner', '800016',
 'Patna', '10AABCA3214H3Z9', 'BNZPK6754R', 'Bihar Services', 'Auditing',
 'FRN1005', 'Fresh', 'Online', 'BATCH005', 'admin', 'approver1', 'Approved', 'Bihar', 'Patna', 'alok.kumar@example.com'),

-- Row 6
(100006, 623456789012345683, SYSTIMESTAMP - 6, NULL,
 'Deepika Singh', 'Sector 62, Gurgaon', '9001234567', 'Private Ltd', 'CFO', '122001',
 'Gurgaon', '06AACCD1234F4Z6', 'AZDFE8765M', 'Singh Finance Pvt Ltd', 'Finance',
 'FRN1006', 'Renewal', 'Online', 'BATCH006', 'user3', NULL, 'In Progress', 'Haryana', 'Gurgaon', 'deepika.singh@example.com'),

-- Row 7
(100007, 723456789012345684, SYSTIMESTAMP - 8, SYSTIMESTAMP - 4,
 'Manish Mehta', 'Lal Baug, Mumbai', '9823456780', 'Proprietorship', 'Owner', '400012',
 'Mumbai', '27AACCM5678K1Z0', 'MNZPK7654R', 'Mehta Electricals', 'Maintenance',
 'FRN1007', 'Fresh', 'Offline', 'BATCH007', 'admin', 'approver2', 'Approved', 'Maharashtra', 'Mumbai', 'manish.mehta@example.com'),

-- Row 8
(100008, 823456789012345685, SYSTIMESTAMP - 2, NULL,
 'Neha Joshi', 'BTM Layout, Bangalore', '9745123480', 'LLP', 'Co-founder', '560076',
 'Bangalore', '29AAACN7890L2Z5', 'BTYPK9876N', 'NTech LLP', 'Technology',
 'FRN1008', 'Renewal', 'Online', 'BATCH008', 'user4', NULL, 'In Progress', 'Karnataka', 'Bangalore Urban', 'neha.joshi@example.com'),

-- Row 9
(100009, 923456789012345686, SYSTIMESTAMP - 1, SYSTIMESTAMP,
 'Kiran Deshmukh', 'Civil Lines, Nagpur', '9845001234', 'Private Ltd', 'Director', '440001',
 'Nagpur', '27AAABD5432E1Z6', 'KLMPF8765T', 'Deshmukh Tech Pvt Ltd', 'Software',
 'FRN1009', 'Fresh', 'Offline', 'BATCH009', 'admin', 'approver1', 'Approved', 'Maharashtra', 'Nagpur', 'kiran.deshmukh@example.com'),

-- Row 10
(100010, 1023456789012345687, SYSTIMESTAMP - 12, SYSTIMESTAMP - 5,
 'Anita Reddy', 'Banjara Hills, Hyderabad', '9876012345', 'LLP', 'Managing Partner', '500034',
 'Hyderabad', '36AAECR1234F5Z9', 'RWTPE5432G', 'Reddy Legal LLP', 'Legal',
 'FRN1010', 'Renewal', 'Online', 'BATCH010', 'user5', 'approver2', 'Pending', 'Telangana', 'Hyderabad', 'anita.reddy@example.com');