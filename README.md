2025-08-05T13:21:22.617372100Z 2025-08-05 :: 13:21:22.616 || ERROR:: DirectJDKLog.java: | 170 | ::  Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.springframework.web.client.ResourceAccessException: I/O error on POST request for "http://Product-Service/Product/getProductOneString": Connection refused] with root cause
2025-08-05T13:21:22.617449000Z java.net.ConnectException: Connection refused
2025-08-05T13:21:22.617456800Z 	at java.base/sun.nio.ch.Net.connect0(Native Method)
2025-08-05T13:21:22.617459800Z 	at java.base/sun.nio.ch.Net.connect(Net.java:535)
2025-08-05T13:21:22.617465400Z 	at java.base/sun.nio.ch.Net.connect(Net.java:524)
2025-08-05T13:21:22.617468200Z 	at java.base/sun.nio.ch.NioSocketImpl.connect(NioSocketImpl.java:574)
2025-08-05T13:21:22.617471000Z 	at java.base/java.net.Socket.connect(Socket.java:665)
2025-08-05T13:21:22.617473900Z 	at java.base/java.net.Socket.connect(Socket.java:603)
2025-08-05T13:21:22.617476400Z 	at java.base/sun.net.NetworkClient.doConnect(NetworkClient.java:166)
2025-08-05T13:21:22.617478800Z 	at java.base/sun.net.www.http.HttpClient.openServer(HttpClient.java:516)
2025-08-05T13:21:22.617481200Z 	at java.base/sun.net.www.http.HttpClient.openServer(HttpClient.java:604)
2025-08-05T13:21:22.617483700Z 	at java.base/sun.net.www.http.HttpClient.<init>(HttpClient.java:276)
2025-08-05T13:21:22.617486500Z 	at java.base/sun.net.www.http.HttpClient.New(HttpClient.java:380)
2025-08-05T13:21:22.617488800Z 	at java.base/sun.net.www.http.HttpClient.New(HttpClient.java:393)
2025-08-05T13:21:22.617491200Z 	at java.base/sun.net.www.protocol.http.HttpURLConnection.getNewHttpClient(HttpURLConnection.java:1030)
2025-08-05T13:21:22.617493700Z 	at java.base/sun.net.www.protocol.http.HttpURLConnection.plainConnect0(HttpURLConnection.java:963)
2025-08-05T13:21:22.617504000Z 	at java.base/sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:899)
2025-08-05T13:21:22.617506200Z 	at java.base/sun.net.www.protocol.http.HttpURLConnection.connect(HttpURLConnection.java:872)
2025-08-05T13:21:22.617508000Z 	at org.springframework.http.client.SimpleClientHttpRequest.executeInternal(SimpleClientHttpRequest.java:79)
2025-08-05T13:21:22.617510100Z 	at org.springframework.http.client.AbstractStreamingClientHttpRequest.executeInternal(AbstractStreamingClientHttpRequest.java:71)
2025-08-05T13:21:22.617512200Z 	at org.springframework.http.client.AbstractClientHttpRequest.execute(AbstractClientHttpRequest.java:81)
2025-08-05T13:21:22.617514000Z 	at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:900)
2025-08-05T13:21:22.617517400Z 	at org.springframework.web.client.RestTemplate.execute(RestTemplate.java:801)
2025-08-05T13:21:22.617519400Z 	at org.springframework.web.client.RestTemplate.postForObject(RestTemplate.java:518)
2025-08-05T13:21:22.617521400Z 	at com.example.Fincore.testController.FincoreServiceController.callProductOne(FincoreServiceController.java:31)
2025-08-05T13:21:22.617523200Z 	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
2025-08-05T13:21:22.617525100Z 	at java.base/java.lang.reflect.Method.invoke(Method.java:565)
2025-08-05T13:21:22.617527200Z 	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:258)
2025-08-05T13:21:22.617529000Z 	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:191)
2025-08-05T13:21:22.617531000Z 	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118)
2025-08-05T13:21:22.617533000Z 	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:991)
2025-08-05T13:21:22.617534900Z 	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:896)
2025-08-05T13:21:22.617536800Z 	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
2025-08-05T13:21:22.617538500Z 	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089)
2025-08-05T13:21:22.617540200Z 	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)
2025-08-05T13:21:22.617542000Z 	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)
2025-08-05T13:21:22.617543900Z 	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:914)
2025-08-05T13:21:22.617545600Z 	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
2025-08-05T13:21:22.617547200Z 	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)
2025-08-05T13:21:22.617552200Z 	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
2025-08-05T13:21:22.617554200Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195)
2025-08-05T13:21:22.617794000Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
2025-08-05T13:21:22.617796800Z 	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)
2025-08-05T13:21:22.617800000Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
2025-08-05T13:21:22.617801900Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
2025-08-05T13:21:22.617803700Z 	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
2025-08-05T13:21:22.617805400Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2025-08-05T13:21:22.617807200Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
2025-08-05T13:21:22.617808800Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
2025-08-05T13:21:22.617810500Z 	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
2025-08-05T13:21:22.617812100Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2025-08-05T13:21:22.617813800Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
2025-08-05T13:21:22.617815300Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
2025-08-05T13:21:22.617817000Z 	at org.springframework.web.filter.ServerHttpObservationFilter.doFilterInternal(ServerHttpObservationFilter.java:110)
2025-08-05T13:21:22.617819000Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2025-08-05T13:21:22.617820800Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
2025-08-05T13:21:22.617822500Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
2025-08-05T13:21:22.617824100Z 	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
2025-08-05T13:21:22.617825900Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2025-08-05T13:21:22.617827700Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
2025-08-05T13:21:22.617829500Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
2025-08-05T13:21:22.617831100Z 	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
2025-08-05T13:21:22.617832900Z 	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
2025-08-05T13:21:22.617835700Z 	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:483)
2025-08-05T13:21:22.617859500Z 	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:116)
2025-08-05T13:21:22.617863500Z 	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
2025-08-05T13:21:22.617865300Z 	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
2025-08-05T13:21:22.617867000Z 	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344)
2025-08-05T13:21:22.617868500Z 	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:398)
2025-08-05T13:21:22.617870100Z 	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
2025-08-05T13:21:22.617871800Z 	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:903)
2025-08-05T13:21:22.617873600Z 	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1769)
2025-08-05T13:21:22.617875200Z 	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
2025-08-05T13:21:22.617876600Z 	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1189)
2025-08-05T13:21:22.617878300Z 	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:658)
2025-08-05T13:21:22.617880000Z 	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)
2025-08-05T13:21:22.617881700Z 	at java.base/java.lang.Thread.run(Thread.java:1447)


I ma again getting this error

/    Sending String Received String
    @PostMapping("/FinTestOne")
    public String callProductOne() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>("Hello from Fin-core", headers);

        return restTemplate.postForObject(productServiceUrl + "/Product/getProductOneString", request, String.class);
    }



If i do the URL hardcoded in above controller  then is will call the other service easily & return other service result like http://Product-Service:8089/Product/getProductOneString

but whenever i use the dynamic url create get error above (Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.springframework.web.client.ResourceAccessException: I/O error on POST request for "http://Product-Service/Product/getProductOneString": Connection refused] with root cause)

in Spring cloud kubernates Service resolved by itself using the service name and no need to mentioned the port no ..!!

Or have to mentioned it for local testing or tell me othen option to do so like other software in restricted env where i can test service to service call working fine 
