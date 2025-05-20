<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns:beans="http://www.springframework.org/schema/beans"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:util="http://www.springframework.org/schema/util"
             xmlns="http://www.springframework.org/schema/security"
             xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/security http://www.springframework.org/schema/security/spring-security.xsd 
http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">


      <http auto-config="true" pattern="/**">

            <custom-filter ref="csrfTokenFilter" after="CSRF_FILTER"/>
            <csrf/>

            <headers>
                  <frame-options policy="DENY"/>
                  <content-security-policy
                          policy-directives="script-src 'self' https://bsuat.info.sbi; object-src https://bsuat.info.sbi;"/>
                  <referrer-policy policy="same-origin"/>
            </headers>
      </http>


      <!-- The tag below has no use but Spring Security needs it to autowire the parent property of
              org.springframework.security.authentication.ProviderManager. Otherwise we get an error
              A probable bug. This is still under investigation-->
      <authentication-manager/>

</beans:beans> 

this is my spring-dispatcher-servlet.xml file I am having the security issue need to resolved under vulnarilibilty Spring Security uses an incorrect request matcher to protect a path.

Recmommanded Solution ::
To protect Spring MVC endpoints, use the MVC request matcher instead of the Ant matcher.
