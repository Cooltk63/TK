Weblogic.xml

<?xml version="1.0" encoding="UTF-8"?>
<wls:weblogic-web-app xmlns:wls="http://xmlns.oracle.com/weblogic/weblogic-web-app" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd http://xmlns.oracle.com/weblogic/weblogic-web-app http://xmlns.oracle.com/weblogic/weblogic-web-app/1.9/weblogic-web-app.xsd">
      <wls:context-root>BS</wls:context-root>
      <wls:session-descriptor>
            <wls:cookie-name>BSCookie</wls:cookie-name>
            <wls:cookie-path>/BS</wls:cookie-path>
            <wls:cookie-secure>true</wls:cookie-secure>
            <wls:cookie-http-only>true</wls:cookie-http-only>
            <wls:persistent-store-type>replicated_if_clustered</wls:persistent-store-type>
            <wls:persistent-store-cookie-name>BSCookies</wls:persistent-store-cookie-name>
            <wls:url-rewriting-enabled>false</wls:url-rewriting-enabled>
            <wls:http-proxy-caching-of-cookies>true</wls:http-proxy-caching-of-cookies>
            <wls:sharing-enabled>true</wls:sharing-enabled>
      </wls:session-descriptor>
      <wls:container-descriptor>
            <wls:prefer-web-inf-classes>true</wls:prefer-web-inf-classes>
      </wls:container-descriptor>
</wls:weblogic-web-app>

Console error output as per below :

weblogic.application.ModuleException: VALIDATION PROBLEMS WERE FOUND
  <6:4> problem: cvc-complex-type.2.3: Element 'web-app@http://xmlns.jcp.org/xml/ns/javaee' with element-only content type cannot have text content.
	at weblogic.servlet.internal.WebAppModule.createModuleException(WebAppModule.java:2220)
	at weblogic.servlet.internal.WebAppModule.loadDescriptor(WebAppModule.java:1700)
	at weblogic.servlet.internal.WebAppModule.init(WebAppModule.java:281)
	at weblogic.servlet.internal.WebAppModule.init(WebAppModule.java:732)
	at weblogic.application.internal.flow.ScopedModuleDriver.init(ScopedModuleDriver.java:162)
	Truncated. see log file for complete stacktrace
Caused By: weblogic.descriptor.DescriptorException: VALIDATION PROBLEMS WERE FOUND
  <6:4> problem: cvc-complex-type.2.3: Element 'web-app@http://xmlns.jcp.org/xml/ns/javaee' with element-only content type cannot have text content.
	at weblogic.descriptor.internal.MarshallerFactory$1.evaluateResults(MarshallerFactory.java:249)
	at weblogic.descriptor.internal.MarshallerFactory$1.evaluateResults(MarshallerFactory.java:235)
	at weblogic.descriptor.internal.MarshallerFactory$1.createDescriptor(MarshallerFactory.java:159)
	at weblogic.descriptor.BasicDescriptorManager.createDescriptor(BasicDescriptorManager.java:345)
	at weblogic.descriptor.BasicDescriptorManager.createDescriptor(BasicDescriptorManager.java:307)
	Truncated. see log file for complete stacktrace
> 
[2025-05-23 04:50:01,590] Artifact BS_5.4:war exploded: Error during artifact deployment. See server log for details.
