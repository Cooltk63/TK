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
		<session-timeout>90</session-timeout>
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
	

</web-app>
