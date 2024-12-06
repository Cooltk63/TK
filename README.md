PS F:\Projects\CRS Projects\CRS ReWork\backend\SSOLoginService> & "c:\Users\v1012297\.m2\wrapper\wrapper\dists\apache-maven-3.9.7-bin\3k9n615lchs6mp84v355m633uo\apache-maven-3.9.7\bin\mvn.cmd" compile -f "f:\Projects\CRS Projects\CRS ReWork\backend\SSOLoginService\pom.xml"
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------< com.crs:SsoLoginService >-----------------------
[INFO] Building SsoLoginService 0.0.2
[INFO]   from pom.xml
[INFO] --------------------------------[ war ]---------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ SsoLoginService ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 216 resources from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ SsoLoginService ---
[INFO] Recompiling the module because of added or removed source files.
[INFO] Compiling 18 source files with javac [debug parameters release 17] to target\classes
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR :
[INFO] -------------------------------------------------------------
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[3,23] package jakarta.servlet does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[4,23] package jakarta.servlet does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[5,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[6,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[7,38] package org.springframework.stereotype does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[8,38] package org.springframework.web.filter does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[13,36] cannot find symbol
  symbol: class OncePerRequestFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[12,2] cannot find symbol
  symbol: class Component
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[16,37] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.Filter.ApiCallFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[17,37] cannot find symbol
  symbol:   class HttpServletResponse
  location: class com.crs.SsoLoginService.Filter.ApiCallFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[18,37] cannot find symbol
  symbol:   class FilterChain
  location: class com.crs.SsoLoginService.Filter.ApiCallFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[18,69] cannot find symbol
  symbol:   class ServletException
  location: class com.crs.SsoLoginService.Filter.ApiCallFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/FilterConfig.java:[4,46] package org.springframework.context.annotation does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/FilterConfig.java:[5,46] package org.springframework.context.annotation does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/FilterConfig.java:[7,2] cannot find symbol
  symbol: class Configuration
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[3,17] package org.slf4j does not exist    
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[4,17] package org.slf4j does not exist    
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[5,38] package org.springframework.stereotype does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[8,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[9,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[16,44] cannot find symbol
  symbol: class Filter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[15,2] cannot find symbol
  symbol: class Component
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[27,16] cannot find symbol
  symbol:   class Logger
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[35,26] cannot find symbol
  symbol:   class FilterConfig
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[35,54] cannot find symbol
  symbol:   class ServletException
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[49,30] cannot find symbol
  symbol:   class ServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[49,54] cannot find symbol
  symbol:   class ServletResponse
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[50,25] cannot find symbol
  symbol:   class FilterChain
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[50,64] cannot find symbol
  symbol:   class ServletException
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[126,44] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[127,25] cannot find symbol
  symbol:   class HttpServletResponse
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[135,51] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[136,25] cannot find symbol
  symbol:   class HttpServletResponse
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[143,46] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[144,25] cannot find symbol
  symbol:   class HttpServletResponse
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[153,44] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[170,46] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[176,25] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[176,53] cannot find symbol
  symbol:   class HttpServletResponse
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[182,42] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[189,25] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[189,53] cannot find symbol
  symbol:   class HttpServletResponse
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[221,32] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[222,25] cannot find symbol
  symbol:   class HttpServletResponse
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[235,54] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[7,1] package jakarta.servlet does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[4,46] package org.springframework.context.annotation does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[5,46] package org.springframework.context.annotation does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[6,67] package org.springframework.security.config.annotation.web.builders does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[7,40] package org.springframework.security.web does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[8,55] package org.springframework.security.web.authentication does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[9,53] package org.springframework.security.web.util.matcher does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[11,2] cannot find symbol
  symbol: class Configuration
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[46,52] cannot find symbol
  symbol:   class HttpSecurity
  location: class com.crs.SsoLoginService.Security.SecurityConfig
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[46,12] cannot find symbol
  symbol:   class SecurityFilterChain
  location: class com.crs.SsoLoginService.Security.SecurityConfig
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/ServletInitializer.java:[3,40] package org.springframework.boot.builder does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/ServletInitializer.java:[4,52] package org.springframework.boot.web.servlet.support does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/ServletInitializer.java:[6,41] cannot find symbol
  symbol: class SpringBootServletInitializer
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/ServletInitializer.java:[9,50] cannot find symbol
  symbol:   class SpringApplicationBuilder
  location: class com.crs.SsoLoginService.ServletInitializer
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/ServletInitializer.java:[9,15] cannot find symbol
  symbol:   class SpringApplicationBuilder
  location: class com.crs.SsoLoginService.ServletInitializer
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/SsoService.java:[3,32] package org.springframework.boot does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/SsoService.java:[4,46] package org.springframework.boot.autoconfigure does not exist 
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/SsoService.java:[6,6] cannot find symbol
  symbol: class SpringBootApplication
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedHttpServletRequest.java:[8,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedHttpServletRequest.java:[9,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedHttpServletRequest.java:[11,56] cannot find symbol        
  symbol: class HttpServletRequestWrapper
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedHttpServletRequest.java:[15,44] cannot find symbol        
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.FederatedHttpServletRequest
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[4,24] package org.apache.log4j does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[5,21] package org.joda.time does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[6,28] package org.joda.time.format does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[7,28] package org.joda.time.format does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[9,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[10,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[19,16] cannot find symbol
  symbol:   class Logger
  location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[20,30] cannot find symbol
  symbol:   class DateTimeFormatter
  location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[23,23] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[26,57] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[30,57] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[35,41] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[42,25] cannot find symbol
  symbol:   class HttpServletResponse
  location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[29,29] package org.apache.commons.io does 
not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[30,21] package org.joda.time does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[31,21] package org.joda.time does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[32,20] package org.opensaml does not exist[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[33,20] package org.opensaml does not exist[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[34,27] package org.opensaml.common does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[35,31] package org.opensaml.saml2.core does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[36,31] package org.opensaml.saml2.core does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[37,37] package org.opensaml.saml2.encryption does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[38,24] package org.opensaml.xml does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[39,24] package org.opensaml.xml does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[40,35] package org.opensaml.xml.encryption does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[41,35] package org.opensaml.xml.encryption does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[42,27] package org.opensaml.xml.io does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[43,27] package org.opensaml.xml.io does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[44,27] package org.opensaml.xml.io does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[45,27] package org.opensaml.xml.io does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[46,27] package org.opensaml.xml.io does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[47,27] package org.opensaml.xml.io does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[48,33] package org.opensaml.xml.security does not exist
[INFO] 100 errors
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.675 s
[INFO] Finished at: 2024-12-06T13:45:40+05:30
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.13.0:compile (default-compile) on project SsoLoginService: Compilation failure: Compilation failure:
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[3,23] package jakarta.servlet does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[4,23] package jakarta.servlet does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[5,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[6,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[7,38] package org.springframework.stereotype does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[8,38] package org.springframework.web.filter does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[13,36] cannot find symbol
[ERROR]   symbol: class OncePerRequestFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[12,2] cannot find symbol
[ERROR]   symbol: class Component
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[16,37] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.Filter.ApiCallFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[17,37] cannot find symbol
[ERROR]   symbol:   class HttpServletResponse
[ERROR]   location: class com.crs.SsoLoginService.Filter.ApiCallFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[18,37] cannot find symbol
[ERROR]   symbol:   class FilterChain
[ERROR]   location: class com.crs.SsoLoginService.Filter.ApiCallFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Filter/ApiCallFilter.java:[18,69] cannot find symbol
[ERROR]   symbol:   class ServletException
[ERROR]   location: class com.crs.SsoLoginService.Filter.ApiCallFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/FilterConfig.java:[4,46] package org.springframework.context.annotation does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/FilterConfig.java:[5,46] package org.springframework.context.annotation does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/FilterConfig.java:[7,2] cannot find symbol
[ERROR]   symbol: class Configuration
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[3,17] package org.slf4j does not exist    
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[4,17] package org.slf4j does not exist    
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[5,38] package org.springframework.stereotype does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[8,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[9,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[16,44] cannot find symbol
[ERROR]   symbol: class Filter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[15,2] cannot find symbol
[ERROR]   symbol: class Component
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[27,16] cannot find symbol
[ERROR]   symbol:   class Logger
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[35,26] cannot find symbol
[ERROR]   symbol:   class FilterConfig
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[35,54] cannot find symbol
[ERROR]   symbol:   class ServletException
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[49,30] cannot find symbol
[ERROR]   symbol:   class ServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[49,54] cannot find symbol
[ERROR]   symbol:   class ServletResponse
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[50,25] cannot find symbol
[ERROR]   symbol:   class FilterChain
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[50,64] cannot find symbol
[ERROR]   symbol:   class ServletException
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[126,44] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[127,25] cannot find symbol
[ERROR]   symbol:   class HttpServletResponse
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[135,51] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[136,25] cannot find symbol
[ERROR]   symbol:   class HttpServletResponse
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[143,46] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[144,25] cannot find symbol
[ERROR]   symbol:   class HttpServletResponse
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[153,44] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[170,46] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[176,25] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[176,53] cannot find symbol
[ERROR]   symbol:   class HttpServletResponse
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[182,42] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[189,25] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[189,53] cannot find symbol
[ERROR]   symbol:   class HttpServletResponse
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[221,32] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[222,25] cannot find symbol
[ERROR]   symbol:   class HttpServletResponse
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[235,54] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.WSFederationFilter
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/WSFederationFilter.java:[7,1] package jakarta.servlet does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[4,46] package org.springframework.context.annotation does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[5,46] package org.springframework.context.annotation does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[6,67] package org.springframework.security.config.annotation.web.builders does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[7,40] package org.springframework.security.web does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[8,55] package org.springframework.security.web.authentication does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[9,53] package org.springframework.security.web.util.matcher does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[11,2] cannot find symbol
[ERROR]   symbol: class Configuration
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[46,52] cannot find symbol
[ERROR]   symbol:   class HttpSecurity
[ERROR]   location: class com.crs.SsoLoginService.Security.SecurityConfig
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/Security/SecurityConfig.java:[46,12] cannot find symbol
[ERROR]   symbol:   class SecurityFilterChain
[ERROR]   location: class com.crs.SsoLoginService.Security.SecurityConfig
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/ServletInitializer.java:[3,40] package org.springframework.boot.builder does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/ServletInitializer.java:[4,52] package org.springframework.boot.web.servlet.support does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/ServletInitializer.java:[6,41] cannot find symbol
[ERROR]   symbol: class SpringBootServletInitializer
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/ServletInitializer.java:[9,50] cannot find symbol
[ERROR]   symbol:   class SpringApplicationBuilder
[ERROR]   location: class com.crs.SsoLoginService.ServletInitializer
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/ServletInitializer.java:[9,15] cannot find symbol
[ERROR]   symbol:   class SpringApplicationBuilder
[ERROR]   location: class com.crs.SsoLoginService.ServletInitializer
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/SsoService.java:[3,32] package org.springframework.boot does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/SsoService.java:[4,46] package org.springframework.boot.autoconfigure does not exist 
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/SsoService.java:[6,6] cannot find symbol
[ERROR]   symbol: class SpringBootApplication
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedHttpServletRequest.java:[8,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedHttpServletRequest.java:[9,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedHttpServletRequest.java:[11,56] cannot find symbol        
[ERROR]   symbol: class HttpServletRequestWrapper
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedHttpServletRequest.java:[15,44] cannot find symbol        
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.FederatedHttpServletRequest
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[4,24] package org.apache.log4j does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[5,21] package org.joda.time does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[6,28] package org.joda.time.format does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[7,28] package org.joda.time.format does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[9,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[10,28] package jakarta.servlet.http does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[19,16] cannot find symbol
[ERROR]   symbol:   class Logger
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[20,30] cannot find symbol
[ERROR]   symbol:   class DateTimeFormatter
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[23,23] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[26,57] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[30,57] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[35,41] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/FederatedLoginManager.java:[42,25] cannot find symbol
[ERROR]   symbol:   class HttpServletResponse
[ERROR]   location: class com.crs.SsoLoginService.auth10.federation.FederatedLoginManager
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[29,29] package org.apache.commons.io does 
not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[30,21] package org.joda.time does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[31,21] package org.joda.time does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[32,20] package org.opensaml does not exist[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[33,20] package org.opensaml does not exist[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[34,27] package org.opensaml.common does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[35,31] package org.opensaml.saml2.core does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[36,31] package org.opensaml.saml2.core does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[37,37] package org.opensaml.saml2.encryption does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[38,24] package org.opensaml.xml does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[39,24] package org.opensaml.xml does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[40,35] package org.opensaml.xml.encryption does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[41,35] package org.opensaml.xml.encryption does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[42,27] package org.opensaml.xml.io does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[43,27] package org.opensaml.xml.io does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[44,27] package org.opensaml.xml.io does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[45,27] package org.opensaml.xml.io does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[46,27] package org.opensaml.xml.io does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[47,27] package org.opensaml.xml.io does not exist
[ERROR] /f:/Projects/CRS Projects/CRS ReWork/backend/SSOLoginService/src/main/java/com/crs/SsoLoginService/auth10/federation/SamlTokenValidator.java:[48,33] package org.opensaml.xml.security does not exist
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


Getting this issue 



