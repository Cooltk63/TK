2024-11-12T12:49:42.907+05:30  INFO 23260 --- [CommonService] [nio-8086-exec-2] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-11-12T12:49:42.907+05:30  INFO 23260 --- [CommonService] [nio-8086-exec-2] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-11-12T12:49:42.909+05:30  INFO 23260 --- [CommonService] [nio-8086-exec-2] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms
2024-11-12T12:49:42.984+05:30  INFO 23260 --- [CommonService] [nio-8086-exec-2] c.crs.commonService.CommonServiceImpl    : configPath: /E:/Sample%20Testing%20CommenService/commonService/target/classes/common.properties
2024-11-12T12:49:42.986+05:30 ERROR 23260 --- [CommonService] [nio-8086-exec-2] c.crs.commonService.CommonServiceImpl    : Exception Occurred 

java.io.FileNotFoundException: E:\Sample%20Testing%20CommenService\commonService\target\classes\common.properties (The system cannot find the path specified)
	at java.base/java.io.FileInputStream.open0(Native Method) ~[na:na]
	at java.base/java.io.FileInputStream.open(FileInputStream.java:213) ~[na:na]
	at java.base/java.io.FileInputStream.<init>(FileInputStream.java:152) ~[na:na]
	at java.base/java.io.FileInputStream.<init>(FileInputStream.java:106) ~[na:na]
	at com.crs.commonService.CommonServiceImpl.previewReport(CommonServiceImpl.java:115) ~[classes/:na]
	at com.crs.commonService.CommonServiceController.previewReport(CommonServiceController.java:51) ~[classes/:na]
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:255) ~[spring-web-6.1.8.jar:6.1.8]
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:188) ~[spring-web-6.1.8.jar:6.1.8]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118) ~[spring-webmvc-6.1.8.jar:6.1.8]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:926) ~[spring-webmvc-6.1.8.jar:6.1.8]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:831) ~[spring-webmvc-6.1.8.jar:6.1.8]
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-6.1.8.jar:6.1.8]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089) ~[spring-webmvc-6.1.8.jar:6.1.8]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979) ~[spring-webmvc-6.1.8.jar:6.1.8]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014) ~[spring-webmvc-6.1.8.jar:6.1.8]
	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:914) ~[spring-webmvc-6.1.8.jar:6.1.8]
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590) ~[tomcat-embed-core-10.1.24.jar:6.0]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885) ~[spring-webmvc-6.1.8.jar:6.1.8]
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658) ~[tomcat-embed-core-10.1.24.jar:6.0]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51) ~[tomcat-embed-websocket-10.1.24.jar:10.1.24]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-6.1.8.jar:6.1.8]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.1.8.jar:6.1.8]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-6.1.8.jar:6.1.8]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.1.8.jar:6.1.8]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.springframework.web.filter.ServerHttpObservationFilter.doFilterInternal(ServerHttpObservationFilter.java:109) ~[spring-web-6.1.8.jar:6.1.8]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.1.8.jar:6.1.8]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-6.1.8.jar:6.1.8]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.1.8.jar:6.1.8]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:482) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:896) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1741) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1190) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63) ~[tomcat-embed-core-10.1.24.jar:10.1.24]
	at java.base/java.lang.Thread.run(Thread.java:1570) ~[na:na]




I am getting this how did i resolve this issue.

below is my commoin.properties file

############ Common Home Directory ##########
REPORT_HOME_DIR=/media/CRS/
TAR_REPORT_HOME_DIR=/media/CRS/TAR/
LFAR_REPORT_HOME_DIR=/media/CRS/LFAR/
INDAS_REPORT_HOME_DIR=/media/CRS/INDAS/
#need to update count if server added to this env
serversCount=3
########### Local ENV ##########
localhost=LR
10.0.26.230=LR
10.0.26.172=LR
10.0.26.221=LR
10.0.26.165=LR
10.0.26.158=LR
10.0.26.152=LR
10.0.26.102=LR
10.0.19.101=LR
10.0.19.203=LR
10.189.42.248=LR
LR=L
LRFileServerIP0=10.189.49.121
LRFileServerIP3=10.0.26.152
LRFileServerIP1=10.189.42.248
LRFileServerIP2=10.189.49.121
LRFileServerIP4=10.189.42.246
LRFileServerIP5=10.189.49.121

LFileUserName0=BSAPP
#LFileUserPassword0=password
LFileServerPort0=2022

LFileUserName1=BSAPP
#LFileUserPassword1=password
LFileServerPort1=2022

LFileUserName2=BSAPP
#LFileUserPassword2=password
LFileServerPort2=2022

LFileUserName3=BSAPP
#LFileUserPassword3=password
LFileServerPort3=2022

LFileUserName4=BSAPP
#LFileUserPassword2=password
LFileServerPort4=2022

LFileUserName5=BSAPP
#LFileUserPassword2=password
LFileServerPort5=2022
#------------------------------#
########### UAT ENV ##########
10.176.6.7=UAT
10.191.158.38=DV
10.191.158.37=DV
UAT=U
DV=D
UATFileServerIP9=10.176.6.6
UATFileServerIP0=10.176.6.6
UATFileServerIP2=10.176.6.7
DVFileServerIP0=10.191.167.23
DVFileServerIP1=10.191.158.37
DVFileServerIP2=10.191.158.38

UFileUserName0=crsapp
UFileUserName9=crsapp
#UFileUserPassword0=crs@123
UFileServerPort0=2022
UFileServerPort9=2022

UFileUserName1=crsapp
#UFileUserPassword1=Apr@1902
UFileServerPort1=2022

UFileUserName2=crsapp
#UFileUserPassword2=Dec@2018
UFileServerPort2=2022
#------------------------------#
