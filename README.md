While try to call the Fincore Service to Product Service Getting below issue 

Fincore Service Controller API Request ::( Callingn this request from POSTMAN) 
 //Sending List<String> Data to Another Service & Received List<Map<String, Objects>> Data as Response from Service
    @PostMapping("/GetMapData")
    public String GetMappingtest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<String> ListData = new ArrayList<>();
        ListData.add("1");
        ListData.add("2");
        ListData.add("3");
        HttpEntity<List<String>> request = new HttpEntity<>(ListData, headers);

        String data= new RestTemplate().postForObject(productServiceUrl+"/service/getProductTwo",request, List.class).toString();

        return data;
    }


Product Controller API ::


@PostMapping("/getProductTwo")
    public String getProductTwo(@RequestBody List<String> data){
        return "Received getProductTwo the Data from Product Service ::"+data;
    }

    
Console OutPut :: 
2025-08-05 :: 07:47:40.077 || ERROR:: DirectJDKLog.java: | 170 | ::  Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.springframework.web.client.ResourceAccessException: I/O error on POST request for "http://localhost:8081/service/getProductTwo": Connection refused] with root cause
2025-08-05T07:47:40.083961100Z java.net.ConnectException: Connection refused
2025-08-05T07:47:40.083966100Z 	at java.base/sun.nio.ch.Net.connect0(Native Method)
2025-08-05T07:47:40.083968100Z 	at java.base/sun.nio.ch.Net.connect(Net.java:535)
2025-08-05T07:47:40.083970000Z 	at java.base/sun.nio.ch.Net.connect(Net.java:524)
2025-08-05T07:47:40.083971800Z 	at java.base/sun.nio.ch.NioSocketImpl.connect(NioSocketImpl.java:574)
2025-08-05T07:47:40.083973600Z 	at java.base/java.net.Socket.connect(Socket.java:665)
2025-08-05T07:47:40.083975400Z 	at java.base/java.net.Socket.connect(Socket.java:603)
2025-08-05T07:47:40.083977300Z 	at java.base/sun.net.NetworkClient.doConnect(NetworkClient.java:166)
2025-08-05T07:47:40.083979000Z 	at java.base/sun.net.www.http.HttpClient.openServer(HttpClient.java:516)
2025-08-05T07:47:40.083980800Z 	at java.base/sun.net.www.http.HttpClient.openServer(HttpClient.java:604)
2025-08-05T07:47:40.083982500Z 	at java.base/sun.net.www.http.HttpClient.<init>(HttpClient.java:276)
2025-08-05T07:47:40.083984200Z 	at java.base/sun.net.www.http.HttpClient.New(HttpClient.java:380)
2025-08-05T07:47:40.083985900Z 	at java.base/sun.net.www.http.HttpClient.New(HttpClient.java:393)
2025-08-05T07:47:40.083987600Z 	at java.base/sun.net.www.protocol.http.HttpURLConnection.getNewHttpClient(HttpURLConnection.java:1030)
2025-08-05T07:47:40.083989600Z 	at java.base/sun.net.www.protocol.http.HttpURLConnection.plainConnect0(HttpURLConnection.java:963)
2025-08-05T07:47:40.083991700Z 	at java.base/sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:899)
2025-08-05T07:47:40.083993500Z 	at java.base/sun.net.www.protocol.http.HttpURLConnection.connect(HttpURLConnection.java:872)
2025-08-05T07:47:40.083995000Z 	at org.springframework.http.client.SimpleClientHttpRequest.executeInternal(SimpleClientHttpRequest.java:79)
2025-08-05T07:47:40.083996800Z 	at org.springframework.http.client.AbstractStreamingClientHttpRequest.executeInternal(AbstractStreamingClientHttpRequest.java:71)
2025-08-05T07:47:40.083998500Z 	at org.springframework.http.client.AbstractClientHttpRequest.execute(AbstractClientHttpRequest.java:81)
2025-08-05T07:47:40.084000000Z 	at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:900)
2025-08-05T07:47:40.084002800Z 	at org.springframework.web.client.RestTemplate.execute(RestTemplate.java:801)
2025-08-05T07:47:40.084016900Z 	at org.springframework.web.client.RestTemplate.postForObject(RestTemplate.java:518)
2025-08-05T07:47:40.084018700Z 	at com.example.Fincore.testController.ServiceController.GetMappingtest(ServiceController.java:46)
2025-08-05T07:47:40.084020100Z 	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
2025-08-05T07:47:40.084021500Z 	at java.base/java.lang.reflect.Method.invoke(Method.java:565)
2025-08-05T07:47:40.084022800Z 	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:258)
2025-08-05T07:47:40.084024300Z 	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:191)
2025-08-05T07:47:40.084025600Z 	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118)
2025-08-05T07:47:40.084027500Z 	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:991)
2025-08-05T07:47:40.084029100Z 	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:896)
2025-08-05T07:47:40.084030700Z 	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
2025-08-05T07:47:40.084032100Z 	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089)
2025-08-05T07:47:40.084033700Z 	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)
2025-08-05T07:47:40.084035100Z 	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)
2025-08-05T07:47:40.084036400Z 	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:914)
2025-08-05T07:47:40.084037700Z 	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
2025-08-05T07:47:40.084038900Z 	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)
2025-08-05T07:47:40.084040400Z 	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
2025-08-05T07:47:40.084041800Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195)
2025-08-05T07:47:40.084043400Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
2025-08-05T07:47:40.084044800Z 	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)
2025-08-05T07:47:40.084046700Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
2025-08-05T07:47:40.084048400Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
2025-08-05T07:47:40.084050100Z 	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
2025-08-05T07:47:40.084055100Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2025-08-05T07:47:40.084057100Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
2025-08-05T07:47:40.084058900Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
2025-08-05T07:47:40.084060400Z 	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
2025-08-05T07:47:40.084061900Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2025-08-05T07:47:40.084063400Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
2025-08-05T07:47:40.084064800Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
2025-08-05T07:47:40.084066400Z 	at org.springframework.web.filter.ServerHttpObservationFilter.doFilterInternal(ServerHttpObservationFilter.java:110)
2025-08-05T07:47:40.084068100Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2025-08-05T07:47:40.084069700Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
2025-08-05T07:47:40.084071500Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
2025-08-05T07:47:40.084073000Z 	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
2025-08-05T07:47:40.084074500Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2025-08-05T07:47:40.084076000Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
2025-08-05T07:47:40.084078300Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
2025-08-05T07:47:40.084079600Z 	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
2025-08-05T07:47:40.084081000Z 	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
2025-08-05T07:47:40.084082300Z 	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:483)
2025-08-05T07:47:40.084084300Z 	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:116)
2025-08-05T07:47:40.084085700Z 	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
2025-08-05T07:47:40.084087200Z 	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
2025-08-05T07:47:40.084088800Z 	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344)
2025-08-05T07:47:40.084090300Z 	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:398)
2025-08-05T07:47:40.084091800Z 	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
2025-08-05T07:47:40.084093300Z 	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:903)
2025-08-05T07:47:40.084097400Z 	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1769)
2025-08-05T07:47:40.084099700Z 	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
2025-08-05T07:47:40.084101200Z 	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1189)
2025-08-05T07:47:40.084102800Z 	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:658)
2025-08-05T07:47:40.084104300Z 	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)
2025-08-05T07:47:40.084105900Z 	at java.base/java.lang.Thread.run(Thread.java:1447)
