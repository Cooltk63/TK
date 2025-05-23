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
