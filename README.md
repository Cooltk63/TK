vulnaribility:Cookie Security: Persistent Session Cookie  
issuwee:Persistent session cookies can lead to account compromise.
Impact: Persistent session cookies can lead to account compromise.

Code which impact inside web.xml file: 	
<session-config>
		<session-timeout>90</session-timeout>
		<cookie-config>
			<secure>true</secure>
		</cookie-config>
	</session-config>

 solution to be implmented : Do not use persistent session cookies. You can do this by setting server.servlet.session.cookie.persistent=false in your Spring Boot configuration file.
