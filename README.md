2024-12-04T14:55:33.057+05:30  INFO 3120 --- [commonReportsService] [nio-8081-exec-2] c.c.c.services.RW49ServiceImpl           : currentStatus :X
2024-12-04T14:55:33.057+05:30  INFO 3120 --- [commonReportsService] [nio-8081-exec-2] c.c.c.services.RW49ServiceImpl           : Getting Previous Quarter Data from ADJDATA: 30/09/2024 branch_code: 00003
2024-12-04T14:55:33.104+05:30 DEBUG 3120 --- [commonReportsService] [nio-8081-exec-2] org.hibernate.SQL                        : 
    SELECT
        AD_BALANCE
    FROM
        CRS_ADJDATA
    where
        AD_DATE=to_date(?, 'dd/mm/yyyy')
        AND AD_BRANCH=?
Hibernate: 
    SELECT
        AD_BALANCE
    FROM
        CRS_ADJDATA
    where
        AD_DATE=to_date(?, 'dd/mm/yyyy')
        AND AD_BRANCH=?
2024-12-04T14:55:33.156+05:30  INFO 3120 --- [commonReportsService] [nio-8081-exec-2] c.c.c.services.RW49ServiceImpl           : ADJData List Size: 5
2024-12-04T14:55:33.157+05:30  INFO 3120 --- [commonReportsService] [nio-8081-exec-2] c.c.c.services.RW49ServiceImpl           : ADJ DATA WE HAVE: [1111, 2222, 33333, 444444, 555555]
2024-12-04T14:55:33.158+05:30  INFO 3120 --- [commonReportsService] [nio-8081-exec-2] c.c.c.services.RW49ServiceImpl           : Processing Particular: CHRG-OFFICE RENT, Component: 10151
2024-12-04T14:55:33.199+05:30 DEBUG 3120 --- [commonReportsService] [nio-8081-exec-2] org.hibernate.SQL                        : 
    select
        ca1_0.adjmoc_id,
        ca1_0.adjmoc_actual_expense,
        ca1_0.adjmoc_amount,
        ca1_0.adjmoc_branch,
        ca1_0.adjmoc_cgl,
        ca1_0.adjmoc_date,
        ca1_0.adjmoc_compdesc,
        ca1_0.adjmoc_head,
        ca1_0.adjmoc_comp,
        ca1_0.adjmoc_subhead,
        ca1_0.adjmoc_est_expense,
        ca1_0.adjmoc_likely_expense,
        ca1_0.adjmoc_remarks,
        ca1_0.report_master_list_id_fk
    from
        crs_adjmoc ca1_0
    where
        ca1_0.adjmoc_compdesc=?
        and ca1_0.adjmoc_comp=?
Hibernate: 
    select
        ca1_0.adjmoc_id,
        ca1_0.adjmoc_actual_expense,
        ca1_0.adjmoc_amount,
        ca1_0.adjmoc_branch,
        ca1_0.adjmoc_cgl,
        ca1_0.adjmoc_date,
        ca1_0.adjmoc_compdesc,
        ca1_0.adjmoc_head,
        ca1_0.adjmoc_comp,
        ca1_0.adjmoc_subhead,
        ca1_0.adjmoc_est_expense,
        ca1_0.adjmoc_likely_expense,
        ca1_0.adjmoc_remarks,
        ca1_0.report_master_list_id_fk
    from
        crs_adjmoc ca1_0
    where
        ca1_0.adjmoc_compdesc=?
        and ca1_0.adjmoc_comp=?
2024-12-04T14:55:33.256+05:30 ERROR 3120 --- [commonReportsService] [nio-8081-exec-2] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.springframework.dao.IncorrectResultSizeDataAccessException: Query did not return a unique result: 32 results were returned] with root cause

org.hibernate.NonUniqueResultException: Query did not return a unique result: 32 results were returned
        at org.hibernate.query.spi.AbstractSelectionQuery.uniqueElement(AbstractSelectionQuery.java:578) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.hibernate.query.spi.AbstractSelectionQuery.getSingleResult(AbstractSelectionQuery.java:561) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.springframework.data.jpa.repository.query.JpaQueryExecution$SingleEntityExecution.doExecute(JpaQueryExecution.java:223) ~[spring-data-jpa-3.3.0.jar:3.3.0]
        at org.springframework.data.jpa.repository.query.JpaQueryExecution.execute(JpaQueryExecution.java:92) ~[spring-data-jpa-3.3.0.jar:3.3.0]
        at org.springframework.data.jpa.repository.query.AbstractJpaQuery.doExecute(AbstractJpaQuery.java:152) ~[spring-data-jpa-3.3.0.jar:3.3.0]
        at org.springframework.data.jpa.repository.query.AbstractJpaQuery.execute(AbstractJpaQuery.java:140) ~[spring-data-jpa-3.3.0.jar:3.3.0]
        at org.springframework.data.repository.core.support.RepositoryMethodInvoker.doInvoke(RepositoryMethodInvoker.java:170) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.repository.core.support.RepositoryMethodInvoker.invoke(RepositoryMethodInvoker.java:158) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.doInvoke(QueryExecutorMethodInterceptor.java:164) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.invoke(QueryExecutorMethodInterceptor.java:143) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.8.jar:6.1.8]
        at org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor.invoke(DefaultMethodInvokingMethodInterceptor.java:70) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.8.jar:6.1.8]
        at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123) ~[spring-tx-6.1.8.jar:6.1.8]
        at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:392) ~[spring-tx-6.1.8.jar:6.1.8]
        at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119) ~[spring-tx-6.1.8.jar:6.1.8]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.8.jar:6.1.8]
        at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137) ~[spring-tx-6.1.8.jar:6.1.8]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.8.jar:6.1.8]
        at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:136) ~[spring-data-jpa-3.3.0.jar:3.3.0]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.8.jar:6.1.8]
        at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97) ~[spring-aop-6.1.8.jar:6.1.8]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.8.jar:6.1.8]
        at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:223) ~[spring-aop-6.1.8.jar:6.1.8]
        at jdk.proxy4/jdk.proxy4.$Proxy178.findByAdjmocdescriptionsAndAdjmocpnlcompcode(Unknown Source) ~[na:na]
        at com.crs.commonReportsService.services.RW49ServiceImpl.getScreenData(RW49ServiceImpl.java:104) ~[classes/:na]
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
        at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:354) ~[spring-aop-6.1.8.jar:6.1.8]
        at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:716) ~[spring-aop-6.1.8.jar:6.1.8]
        at com.crs.commonReportsService.services.RW49ServiceImpl$$SpringCGLIB$$0.getScreenData(<generated>) ~[classes/:na]
        at com.crs.commonReportsService.controllers.RW49Controller.getScreenData(RW49Controller.java:27) ~[classes/:na]
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
        at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:903) ~[spring-webmvc-6.1.8.jar:6.1.8]
        at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564) ~[tomcat-embed-core-10.1.24.jar:6.0]
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

