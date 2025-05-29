Vulnerability
SQL Injection

Vulnerability Description in Detail
On line 44 of AdminLogDaoImpl.java, the method getResult() invokes a SQL query built using input potentially coming from an untrusted source. This call could allow an attacker to modify the statement's meaning or to execute arbitrary SQL commands.

Likely Impact
On line 44 of AdminLogDaoImpl.java, the method getResult() invokes a SQL query built using input potentially coming from an untrusted source. This call could allow an attacker to modify the statement's meaning or to execute arbitrary SQL commands.

Recommendation
The root cause of a SQL injection vulnerability is the ability of an attacker to change context in the SQL query, causing a value that the programmer intended to be interpreted as data to be interpreted as a command instead. When a SQL query is constructed, the programmer knows what should be interpreted as part of the command and what should be interpreted as data. Parameterized SQL statements can enforce this behavior by disallowing data-directed context changes and preventing nearly all SQL injection attacks. Parameterized SQL statements are constructed using strings of regular SQL, but when user-supplied data needs to be included, they create bind parameters, which are placeholders for data that is subsequently inserted. Bind parameters allow the program to explicitly specify to the database what should be treated as a command and what should be treated as data. When the program is ready to execute a statement, it specifies to the database the runtime values to use for the value of each of the bind parameters, without the risk of the data being interpreted as commands.

Code Impacted ::
	@Override
	public List<List<String>> getResult(String queryLogger) throws SQLException {

		//log.info("Admin Log");
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
/*Change Here inside Query parameter using CleanPath*/
		ResultSet rs = stmt.executeQuery(CleanPath.cleanString(queryLogger));
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		String[] columns = new String[columnCount];
		List<List<String>> loggerData = new ArrayList();
		List<String> sbColumns = new ArrayList();
		for (int i = 1; i <= columnCount; i++) {
			String columnName = rsMetaData.getColumnName(i);
			columns[i - 1] = columnName;
			// sbColumns.append(columnName+"~");
			sbColumns.add(columnName);
		}
		loggerData.add(sbColumns);

		rs.beforeFirst();
		while (rs.next()) {
			List<String> ls = new ArrayList<String>();
			for (String column : columns) {
				ls.add(rs.getString(column));
			}
			loggerData.add(ls);
		}

		/*
		 * for(List ls : loggerData){ //log.info(ls.toString());
		 * 
		 * }
		 */
		rs.close();
		stmt.close();
		conn.close();

		return loggerData;
	}
