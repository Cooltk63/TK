List<Map<String, Object>> getColumnData = columnDataRepository.getTabColumnData((String) data.get("reportId"), (String) tabData.get(i).get("TAB_VALUE_FK"));



                List<Map<String, Object>> dynamicColumnData = new ArrayList<>(getColumnData);

                //Changing 2nd Position Column Data
                Map<String,Object>secondDynamicColumn=new HashMap<>(dynamicColumnData.get(2));
                String updatedName2=dynamicColumnData.get(2).get("COLUMN_NAME").toString()+ " " +"01.04."+loginuserData.get("quarterEndDate").toString().substring(6,10);
                // Set to Map
                secondDynamicColumn.replace("COLUMN_NAME",updatedName2);
                //Set to List Data
                dynamicColumnData.set(2,secondDynamicColumn);



                //Changing 6th Position Column Data
                Map<String,Object>sixDynamicColumn=new HashMap<>(dynamicColumnData.get(6));
                String updatedName6="PROVISIONABLE AMT AS ON"+ " "+loginuserData.get("quarterEndDate").toString() +" (7)=3-4+5-6";
                // Set to Map
                sixDynamicColumn.replace("COLUMN_NAME",updatedName6);
                //Set to List Data
                dynamicColumnData.set(6,sixDynamicColumn);

                //Changing 8th Position Column Data
                Map<String,Object>EightDynamicColumn=new HashMap<>(dynamicColumnData.get(8));
                String updatedName8="PROVISION REQUIREMENT AS ON" +loginuserData.get("quarterEndDate").toString()+ " (9)=7*8";
                // Set to Map
                EightDynamicColumn.replace("COLUMN_NAME",updatedName8);
                //Set to List Data
                dynamicColumnData.set(8,EightDynamicColumn);

                // Adding COLUMN-DATA
                updatedTabData.put("TAB_COLUMN_DATA", dynamicColumnData);


java.lang.IndexOutOfBoundsException: Index 8 out of bounds for length 8
	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100) ~[na:na]
	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106) ~[na:na]
	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302) ~[na:na]
	at java.base/java.util.Objects.checkIndex(Objects.java:365) ~[na:na]
	at java.base/java.util.ArrayList.get(ArrayList.java:428) ~[na:na]
	at com.crs.commonReportsService.services.RW04ServiceImpl.getReportDetails(RW04ServiceImpl.java:159) ~[classes/:na]
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:354) ~[spring-aop-6.1.8.jar:6.1.8]
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:716) ~[spring-aop-6.1.8.jar:6.1.8]
	at com.crs.commonReportsService.services.RW04ServiceImpl$$SpringCGLIB$$0.getReportDetails(<generated>) ~[classes/:na]
	at com.crs.commonReportsService.controllers.RW04Controller.getReportDetails(RW04Controller.java:24) ~[classes/:na]
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


 I am getting this error only when i am trying to set the 8th position data
