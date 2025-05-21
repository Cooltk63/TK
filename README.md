

Vulnerability: Denial of Service: StringBuilder
Likely Impact:The call to append() in AdminDaoImpl.java on line 124 appends untrusted data to a StringBuilder or StringBuffer instance initialized with the default backing array size (16). This can cause the JVM to overconsume heap memory space.
Recommendation: initialize the StringBuilder or StringBuffer with an initial capacity of the expected appended data size to reduce the number of times the backing array is resized. Check the size of the data before appending it to a StringBuilder or StringBuffer instance.

Kindly provide the solution for above issue impacted code as per below provide me the slution for it using the java 8

        StringBuffer buffer = new StringBuffer();
        Map<String, String> listEmptyBranch = new HashMap<String, String>();
        for (int i = 0; i < listOfBranches.size(); i++) {
            String branchCode = (String) listOfBranches.get(i);
            listEmptyBranch.put(branchCode, "N");
            if (i == listOfBranches.size() - 1) {
                buffer.append("'" + branchCode + "'");
            } else {
                buffer.append("'" + branchCode + "',");
            }
        }
