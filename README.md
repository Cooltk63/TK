Vulnerability :J2EE Misconfiguration: Incomplete Error Handling

Vulnerability Description in Detail :A web application must define a default error page for java.lang.Throwable to handle any uncaught exceptions in order to prevent attackers from mining information from the application container's built-in error response.
Likely Impact
A web application must define a default error page for java.lang.Throwable to handle any uncaught exceptions in order to prevent attackers from mining information from the application container's built-in error response.

Recommendation
A web application must be configured with a default error page. Your web.xml should include at least the following entries: <error-page> <exception-type>java.lang.Throwable</exception-type> <location>/error.jsp</location> </error-page> <error-page> <error-code>404</error-code> <location>/error.jsp</location> </error-page> <error-page> <error-code>500</error-code> <location>/error.jsp</location> </error-page>

This is my web.xml file

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	id="WebApp_ID" version="3.0">
	<display-name>BS</display-name>
	<servlet>
		<servlet-name>spring-dispatcher</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/config/spring-dispatcher-servlet.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
		<async-supported>true</async-supported>
	</servlet>
	<servlet-mapping>
		<servlet-name>spring-dispatcher</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
	<welcome-file-list>
		<welcome-file>index.jsp</welcome-file>
	</welcome-file-list>
	<error-page>
		<error-code>500</error-code>
		<location>/500.jsp</location>
	</error-page>
	<error-page>
		<error-code>404</error-code>
		<location>/404.jsp</location>
	</error-page>
	<session-config>
		<session-timeout>30</session-timeout>
		<cookie-config>
			<secure>true</secure>
		</cookie-config>
	</session-config>
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value><!--/WEB-INF/config/spring-dispatcher-servlet.xml,-->
	/WEB-INF/config/appContext-auth.xml
	</param-value>

	</context-param>



	<!--<filter>
		<filter-name>XSS</filter-name>
		<filter-class>com.tcs.utils.CrossScriptingFilter</filter-class>
	</filter>-->
	<!--<filter-mapping>
		<filter-name>XSS</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>-->



	<context-param>
		<param-name>defaultHtmlEscape</param-name>
		<param-value>true</param-value>
	</context-param>

	<error-page>
		<exception-type>org.springframework.web.HttpSessionRequiredException</exception-type>
		<location>/Security/logout</location>
	</error-page>



	<filter>
		<filter-name>FederationFilter</filter-name>
		<filter-class>com.auth10.federation.WSFederationFilter</filter-class>
		<async-supported>true</async-supported>
		<init-param> <param-name>login-page-url</param-name>
			<param-value>signapplet.jar</param-value>
		</init-param>
		<init-param> <param-name>exclude-urls-regex</param-name> <param-value>/resources/|/assets/</param-value>
		</init-param>
	</filter>


	<filter-mapping> <filter-name>FederationFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>


<!--	<filter>-->
<!--		<filter-name>CORSFilter</filter-name>-->
<!--		<filter-class>com.tcs.security.CORSFilter</filter-class>-->
<!--	</filter>-->
<!--	<filter-mapping>-->
<!--		<filter-name>CORSFilter</filter-name>-->
<!--		<url-pattern>/*</url-pattern>-->
<!--	</filter-mapping>-->

 <!-- <filter>
        <filter-name>loadSalt</filter-name>
        <filter-class>com.tcs.csrf.LoadSalt</filter-class>
    </filter>
    <filter-mapping>
    <filter-name>loadSalt</filter-name>
		<url-pattern>/index.jsp</url-pattern>
        <url-pattern>/Security/login</url-pattern>
        <url-pattern>/Security/submitUserDetails</url-pattern>
        <url-pattern>/Security/logoutCircleUsers</url-pattern>
        <url-pattern>/Security/downloadDocs</url-pattern>
        <url-pattern>/Security/logout</url-pattern>

        <url-pattern>/AuditMapping/uploadAuditGroup</url-pattern>
    <url-pattern>/AuditMapping/updateData</url-pattern>
    <url-pattern>/AuditMapping/downloadErrorFile</url-pattern>

    <url-pattern>/AuditMaster/updateData</url-pattern>
    <url-pattern>/AuditMaster/insertAudit</url-pattern>

    <url-pattern>/AuditReports/getAuditdata</url-pattern>
    <url-pattern>/AuditReports/viewReportJrxml</url-pattern>

    <url-pattern>/BranchGstin/uploadGSTIN</url-pattern>
    <url-pattern>/BranchGstin/updateData</url-pattern>
    <url-pattern>/BranchGstin/downloadErrorFile</url-pattern>
    <url-pattern>/BranchGstin/viewReportMapping</url-pattern>


    <url-pattern>/BranchManagement/checkBranch</url-pattern>
    <url-pattern>/BranchManagement/insertBranch</url-pattern>
    <url-pattern>/BranchManagement/update</url-pattern>
    <url-pattern>/BranchManagement/deleteBranch</url-pattern>

    <url-pattern>/BsMapTable/searchRecord</url-pattern>
    <url-pattern>/BsMapTable/recordUpdate</url-pattern>

    <url-pattern>/ConfigureSign/search</url-pattern>
    <url-pattern>/ConfigureSign/save</url-pattern>
    <url-pattern>/ConfigureSign/delete</url-pattern>

    <url-pattern>/CreatedReports/downloadReport</url-pattern>

    <url-pattern>/dashboard/getDashboard</url-pattern>
    <url-pattern>/dashboard/downloadReports</url-pattern>


    <url-pattern>/ReportGstin/viewReportJrxml</url-pattern>

    <url-pattern>/Shc01/getCurrentData</url-pattern>
    <url-pattern>/Shc01/getSavedData</url-pattern>
    <url-pattern>/Shc01/submit</url-pattern>

    <url-pattern>/UserManage/updateUserDetails</url-pattern>
    <url-pattern>/UserManage/deleteUser</url-pattern>
    <url-pattern>/UserManage/createUser</url-pattern>
    <url-pattern>/UserManage/DeleteUser</url-pattern>
    <url-pattern>/UserManage/confirmCreateUser</url-pattern>
    <url-pattern>/UserManage/checkUserDetails</url-pattern>
    <url-pattern>/UserManage/getUserInfo</url-pattern>
    <url-pattern>/UserManage/reopenCircleReports</url-pattern>
    <url-pattern>/UserManage/checkAdjSubmit</url-pattern>


    <url-pattern>/Auditor/rejectReport</url-pattern>
    <url-pattern>/Auditor/getSignedReport</url-pattern>
    <url-pattern>/Auditor/downloadSignedReport</url-pattern>

    &lt;!&ndash;<url-pattern>/MntAscii/uploadFile</url-pattern>
    <url-pattern>/MntAscii/deleteAscii</url-pattern>
    <url-pattern>/MntAscii/getQendDates</url-pattern>

    <url-pattern>/PnlVar/downloadPnlReport</url-pattern>
    <url-pattern>/PnlVar/generateReport</url-pattern>&ndash;&gt;


    <url-pattern>/GSTINMaster/updateData</url-pattern>
    <url-pattern>/GSTINMaster/checkGSTIN</url-pattern>
    <url-pattern>/GSTINMaster/insertGSTIN</url-pattern>


    <url-pattern>/Maker/submitNineA</url-pattern>
    <url-pattern>/Maker/submitNineB</url-pattern>
    <url-pattern>/Maker/submitNineC</url-pattern>
    <url-pattern>/Maker/submitNineMig</url-pattern>
    <url-pattern>/Maker/submitTen</url-pattern>
    <url-pattern>/Maker/saveMocEntries</url-pattern>
    <url-pattern>/Maker/accRejMoc</url-pattern>
    <url-pattern>/Maker/submitEditMOC</url-pattern>
    <url-pattern>/Maker/deleteMOC</url-pattern>
    <url-pattern>/Maker/deleteAllMocs</url-pattern>
    <url-pattern>/Maker/reverseMocs</url-pattern>
    <url-pattern>/Maker/viewReport</url-pattern>
    <url-pattern>/Maker/downloadReport</url-pattern>
    <url-pattern>/Maker/submitScrTwo</url-pattern>
    <url-pattern>/Maker/submitPLSUPReport</url-pattern>
    <url-pattern>/Maker/SubmitSC09Report</url-pattern>
    <url-pattern>/Maker/uploadFile</url-pattern>
    <url-pattern>/Maker/submitNineSupl</url-pattern>

    <url-pattern>/Admin/saveBranch</url-pattern>
    <url-pattern>/Admin/checkFreezedOrNot</url-pattern>
    <url-pattern>/Admin/downloadSignedReport</url-pattern>
    <url-pattern>/Admin/viewReport</url-pattern>
    <url-pattern>/Admin/UploadFile2</url-pattern>
    <url-pattern>/Admin/viewReportPreReports</url-pattern>
    <url-pattern>/Admin/downloadReport</url-pattern>
    <url-pattern>/Admin/acceptReport</url-pattern>
    <url-pattern>/Admin/rejectReport</url-pattern>
    <url-pattern>/Admin/uploadFile</url-pattern>
    <url-pattern>/Admin/deleteAsciData</url-pattern>
    <url-pattern>/Admin/approveBranchList</url-pattern>
    <url-pattern>/Admin/checkBrList</url-pattern>
    <url-pattern>/Admin/downBranchList</url-pattern>
    <url-pattern>/Admin/viewReportJrxml</url-pattern>
    <url-pattern>/Admin/viewReportJrxmlCircle</url-pattern>
    <url-pattern>/Admin/fileDownload</url-pattern>
    <url-pattern>/Admin/bulkFileDownload</url-pattern>
    <url-pattern>/Admin/downloadAscii</url-pattern>
    <url-pattern>/Admin/updatePropertiesFileData</url-pattern>
    <url-pattern>/Admin/submitBsFooter</url-pattern>
    <url-pattern>/Admin/deleteBsFooter</url-pattern>


    <url-pattern>/IbgUserController/getFileCheck</url-pattern>
    <url-pattern>/IbgUserController/valiadateAsciiFile</url-pattern>
    <url-pattern>/IbgUserController/loadAsciiFile</url-pattern>
    <url-pattern>/IbgUserController/getFilesData</url-pattern>




    <url-pattern>/FRTMaker/submitApropriatn</url-pattern>
    <url-pattern>/FRTMaker/submitAdjustAmounts</url-pattern>
    <url-pattern>/FRTMaker/saveAdjustAmounts</url-pattern>
    <url-pattern>/FRTMaker/submitSchedule4Report</url-pattern>
    <url-pattern>/FRTMaker/acceptReject</url-pattern>
    <url-pattern>/FRTMaker/submitPostInterse</url-pattern>
    <url-pattern>/FRTMaker/submitProvision</url-pattern>
    <url-pattern>/FRTMaker/deleteProvision</url-pattern>


    <url-pattern>/FRTAdmin/approveAdjustAmounts</url-pattern>
    <url-pattern>/FRTAdmin/areAllCircleFreezed</url-pattern>
    <url-pattern>/FRTAdmin/getWBSDataAdjust</url-pattern>

    <url-pattern>/DWH/getLastAction</url-pattern>
    <url-pattern>/DWH/generateFile</url-pattern>
    <url-pattern>/DWH/getUserAuth</url-pattern>
    <url-pattern>/DWH/getActionCount</url-pattern>

</filter-mapping>-->


	<!--<filter>
		<filter-name>JsonFilter</filter-name>
		<filter-class>com.tcs.security.JsonFilter</filter-class>
		<async-supported>true</async-supported>
	</filter>

	<filter-mapping>
		<filter-name>JsonFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>-->



	<filter>
		<filter-name>jwtTokenAuthFilter</filter-name>
		<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
		<async-supported>true</async-supported>
	</filter>

	<filter-mapping>
		<filter-name>jwtTokenAuthFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>


	<filter>
		<filter-name>CORS</filter-name>
		<filter-class>com.tcs.security.CORSFilter</filter-class>
	</filter>

	<filter-mapping>
		<filter-name>CORS</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>


	<!--<filter>
		<filter-name>rateLimitFilter</filter-name>
		<filter-class>com.tcs.utils.rateLimit.RateLimitFilter</filter-class>
	</filter>-->

	<!--<filter-mapping>
		<filter-name>rateLimitFilter</filter-name>
		<url-pattern>/BS/*</url-pattern>
	</filter-mapping>-->

</web-app>
