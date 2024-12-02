PS D:\safe duplicates\Dashboard_local> & "c:\Users\V1014064\.m2\wrapper\wrapper\dists\apache-maven-3.9.7-bin\3k9n615lchs6mp84v355m633uo\apache-maven-3.9.7\bin\mvn.cmd" package -f "d:\safe duplicates\Dashboard_local\pom.xml"
Failed to load native library:jansi.dll. osinfo: Windows/x86_64
java.lang.UnsatisfiedLinkError: C:\Users\v1014064\.m2\wrapper\wrapper\dists\apache-maven-3.9.7-bin\3k9n615lchs6mp84v355m633uo\apache-maven-3.9.7\lib\jansi-native\Windows\x86_64\jansi.dll: Access is denied
Failed to load native library:jansi-2.4.1-6d34460dfa65e640-jansi.dll. osinfo: Windows/x86_64
java.lang.UnsatisfiedLinkError: C:\Users\v1014064\AppData\Local\Temp\jansi-2.4.1-6d34460dfa65e640-jansi.dll: Access is denied
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------------------< com:DASHBOARD >----------------------------
[INFO] Building DASHBOARD 0.0.2
[INFO]   from pom.xml
[INFO] --------------------------------[ war ]---------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ DASHBOARD ---
[INFO] Copying 351 resources from WebContent to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ DASHBOARD ---
[INFO] Recompiling the module because of added or removed source files.
[INFO] Compiling 71 source files with javac [debug parameters release 8] to target\classes
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR :
[INFO] -------------------------------------------------------------
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/URLUTF8Encoder.java:[13,14] unmappable character for encoding UTF-8
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/URLUTF8Encoder.java:[16,42] unmappable character for encoding UTF-8
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedPrincipal.java:[11,32] package org.apache.log4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[8,26] package javax.servlet.http does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[9,26] package javax.servlet.http does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[11,56] cannot find symbol
  symbol: class HttpServletRequestWrapper
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[15,44] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.auth10.federation.FederatedHttpServletRequest
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[4,24] package org.apache.log4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[5,21] package org.joda.time does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[6,28] package org.joda.time.format does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[7,28] package org.joda.time.format does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[9,26] package javax.servlet.http does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[10,26] package javax.servlet.http does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[19,16] cannot find symbol
  symbol:   class Logger
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[20,30] cannot find symbol
  symbol:   class DateTimeFormatter
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[23,23] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[26,57] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[30,57] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[35,41] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[42,25] cannot find symbol
  symbol:   class HttpServletResponse
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[29,29] package org.apache.commons.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[30,21] package org.joda.time does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[31,21] package org.joda.time does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[32,20] package org.opensaml does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[33,20] package org.opensaml does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[34,27] package org.opensaml.common does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[35,31] package org.opensaml.saml2.core does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[36,31] package org.opensaml.saml2.core does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[37,37] package org.opensaml.saml2.encryption does not exist      
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[38,24] package org.opensaml.xml does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[39,24] package org.opensaml.xml does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[40,35] package org.opensaml.xml.encryption does not exist        
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[41,35] package org.opensaml.xml.encryption does not exist        
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[42,27] package org.opensaml.xml.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[43,27] package org.opensaml.xml.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[44,27] package org.opensaml.xml.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[45,27] package org.opensaml.xml.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[46,27] package org.opensaml.xml.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[47,27] package org.opensaml.xml.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[48,33] package org.opensaml.xml.security does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[49,33] package org.opensaml.xml.security does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[50,33] package org.opensaml.xml.security does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[51,44] package org.opensaml.xml.security.credential does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[52,44] package org.opensaml.xml.security.credential does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[53,42] package org.opensaml.xml.security.criteria does not exist 
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[54,41] package org.opensaml.xml.security.keyinfo does not exist  
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[55,41] package org.opensaml.xml.security.keyinfo does not exist  
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[56,41] package org.opensaml.xml.security.keyinfo does not exist  
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[57,38] package org.opensaml.xml.security.x509 does not exist     
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[58,34] package org.opensaml.xml.signature does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[59,34] package org.opensaml.xml.signature does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[60,39] package org.opensaml.xml.signature.impl does not exist    
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[61,35] package org.opensaml.xml.validation does not exist        
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[62,17] package org.slf4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[63,17] package org.slf4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[81,32] package org.apache.log4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[83,22] cannot find symbol
  symbol:   class Logger
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[86,44] cannot find symbol
  symbol:   class ConfigurationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[92,56] cannot find symbol
  symbol:   class ConfigurationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[129,46] cannot find symbol
  symbol:   class ConfigurationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[130,58] cannot find symbol
  symbol:   class ValidationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[131,25] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[231,24] cannot find symbol
  symbol:   class SignableSAMLObject
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[233,52] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[246,25] cannot find symbol
  symbol:   class EncryptedAssertion
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[245,24] cannot find symbol
  symbol:   class Assertion
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[294,24] cannot find symbol
  symbol:   class SignableSAMLObject
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[296,25] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[344,24] cannot find symbol
  symbol:   class EncryptedAssertion
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[361,41] cannot find symbol
  symbol:   class Assertion
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[401,25] cannot find symbol
  symbol:   class Assertion
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[409,48] package org.opensaml.saml1.core does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[417,44] cannot find symbol
  symbol:   class Instant
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[417,63] cannot find symbol
  symbol:   class Instant
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[433,46] cannot find symbol
  symbol:   class SignableSAMLObject
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[434,51] cannot find symbol
  symbol:   class ValidationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[435,25] cannot find symbol
  symbol:   class ConfigurationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[435,49] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[465,25] cannot find symbol
  symbol:   class SignableSAMLObject
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[466,32] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[466,56] cannot find symbol
  symbol:   class ValidationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[478,25] cannot find symbol
  symbol:   class SignableSAMLObject
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[479,32] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[479,56] cannot find symbol
  symbol:   class ValidationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[517,25] cannot find symbol
  symbol:   class Assertion
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[518,51] cannot find symbol
  symbol:   class ValidationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[519,25] cannot find symbol
  symbol:   class ConfigurationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[519,49] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[543,48] package org.opensaml.saml1.core does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[544,51] cannot find symbol
  symbol:   class ValidationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[545,25] cannot find symbol
  symbol:   class ConfigurationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[545,49] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[568,49] cannot find symbol
  symbol:   class XMLObject
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[3,17] package org.slf4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[4,17] package org.slf4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[6,1] package javax.servlet does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[7,26] package javax.servlet.http does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[8,26] package javax.servlet.http does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[12,44] cannot find symbol
  symbol: class Filter
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[21,15] cannot find symbol
  symbol:   class Logger
  location: class com.auth10.federation.WSFederationFilter
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[22,32] package org.apache.log4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[29,26] cannot find symbol
  symbol:   class FilterConfig
  location: class com.auth10.federation.WSFederationFilter
[INFO] 102 errors
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.381 s
[INFO] Finished at: 2024-12-02T13:15:27+05:30
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.13.0:compile (default-compile) on project DASHBOARD: Compilation failure: Compilation failure:
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/URLUTF8Encoder.java:[13,14] unmappable character for encoding UTF-8
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/URLUTF8Encoder.java:[16,42] unmappable character for encoding UTF-8
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedPrincipal.java:[11,32] package org.apache.log4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[8,26] package javax.servlet.http does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[9,26] package javax.servlet.http does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[11,56] cannot find symbol
[ERROR]   symbol: class HttpServletRequestWrapper
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[15,44] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.auth10.federation.FederatedHttpServletRequest
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[4,24] package org.apache.log4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[5,21] package org.joda.time does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[6,28] package org.joda.time.format does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[7,28] package org.joda.time.format does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[9,26] package javax.servlet.http does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[10,26] package javax.servlet.http does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[19,16] cannot find symbol
[ERROR]   symbol:   class Logger
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[20,30] cannot find symbol
[ERROR]   symbol:   class DateTimeFormatter
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[23,23] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[26,57] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[30,57] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[35,41] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[42,25] cannot find symbol
[ERROR]   symbol:   class HttpServletResponse
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[29,29] package org.apache.commons.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[30,21] package org.joda.time does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[31,21] package org.joda.time does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[32,20] package org.opensaml does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[33,20] package org.opensaml does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[34,27] package org.opensaml.common does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[35,31] package org.opensaml.saml2.core does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[36,31] package org.opensaml.saml2.core does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[37,37] package org.opensaml.saml2.encryption does not exist      
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[38,24] package org.opensaml.xml does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[39,24] package org.opensaml.xml does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[40,35] package org.opensaml.xml.encryption does not exist        
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[41,35] package org.opensaml.xml.encryption does not exist        
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[42,27] package org.opensaml.xml.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[43,27] package org.opensaml.xml.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[44,27] package org.opensaml.xml.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[45,27] package org.opensaml.xml.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[46,27] package org.opensaml.xml.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[47,27] package org.opensaml.xml.io does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[48,33] package org.opensaml.xml.security does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[49,33] package org.opensaml.xml.security does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[50,33] package org.opensaml.xml.security does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[51,44] package org.opensaml.xml.security.credential does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[52,44] package org.opensaml.xml.security.credential does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[53,42] package org.opensaml.xml.security.criteria does not exist 
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[54,41] package org.opensaml.xml.security.keyinfo does not exist  
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[55,41] package org.opensaml.xml.security.keyinfo does not exist  
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[56,41] package org.opensaml.xml.security.keyinfo does not exist  
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[57,38] package org.opensaml.xml.security.x509 does not exist     
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[58,34] package org.opensaml.xml.signature does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[59,34] package org.opensaml.xml.signature does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[60,39] package org.opensaml.xml.signature.impl does not exist    
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[61,35] package org.opensaml.xml.validation does not exist        
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[62,17] package org.slf4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[63,17] package org.slf4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[81,32] package org.apache.log4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[83,22] cannot find symbol
[ERROR]   symbol:   class Logger
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[86,44] cannot find symbol
[ERROR]   symbol:   class ConfigurationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[92,56] cannot find symbol
[ERROR]   symbol:   class ConfigurationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[129,46] cannot find symbol
[ERROR]   symbol:   class ConfigurationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[130,58] cannot find symbol
[ERROR]   symbol:   class ValidationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[131,25] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[231,24] cannot find symbol
[ERROR]   symbol:   class SignableSAMLObject
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[233,52] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[246,25] cannot find symbol
[ERROR]   symbol:   class EncryptedAssertion
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[245,24] cannot find symbol
[ERROR]   symbol:   class Assertion
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[294,24] cannot find symbol
[ERROR]   symbol:   class SignableSAMLObject
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[296,25] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[344,24] cannot find symbol
[ERROR]   symbol:   class EncryptedAssertion
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[361,41] cannot find symbol
[ERROR]   symbol:   class Assertion
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[401,25] cannot find symbol
[ERROR]   symbol:   class Assertion
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[409,48] package org.opensaml.saml1.core does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[417,44] cannot find symbol
[ERROR]   symbol:   class Instant
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[417,63] cannot find symbol
[ERROR]   symbol:   class Instant
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[433,46] cannot find symbol
[ERROR]   symbol:   class SignableSAMLObject
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[434,51] cannot find symbol
[ERROR]   symbol:   class ValidationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[435,25] cannot find symbol
[ERROR]   symbol:   class ConfigurationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[435,49] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[465,25] cannot find symbol
[ERROR]   symbol:   class SignableSAMLObject
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[466,32] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[466,56] cannot find symbol
[ERROR]   symbol:   class ValidationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[478,25] cannot find symbol
[ERROR]   symbol:   class SignableSAMLObject
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[479,32] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[479,56] cannot find symbol
[ERROR]   symbol:   class ValidationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[517,25] cannot find symbol
[ERROR]   symbol:   class Assertion
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[518,51] cannot find symbol
[ERROR]   symbol:   class ValidationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[519,25] cannot find symbol
[ERROR]   symbol:   class ConfigurationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[519,49] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[543,48] package org.opensaml.saml1.core does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[544,51] cannot find symbol
[ERROR]   symbol:   class ValidationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[545,25] cannot find symbol
[ERROR]   symbol:   class ConfigurationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[545,49] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[568,49] cannot find symbol
[ERROR]   symbol:   class XMLObject
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[3,17] package org.slf4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[4,17] package org.slf4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[6,1] package javax.servlet does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[7,26] package javax.servlet.http does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[8,26] package javax.servlet.http does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[12,44] cannot find symbol
[ERROR]   symbol: class Filter
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[21,15] cannot find symbol
[ERROR]   symbol:   class Logger
[ERROR]   location: class com.auth10.federation.WSFederationFilter
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[22,32] package org.apache.log4j does not exist
[ERROR] /d:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[29,26] cannot find symbol
[ERROR]   symbol:   class FilterConfig
[ERROR]   location: class com.auth10.federation.WSFederationFilter
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
PS D:\safe duplicates\Dashboard_local>
PS D:\safe duplicates\Dashboard_local> 
PS D:\safe duplicates\Dashboard_local> 
PS D:\safe duplicates\Dashboard_local> mvn clean package
Failed to load native library:jansi.dll. osinfo: Windows/x86_64
java.lang.UnsatisfiedLinkError: C:\Users\v1014064\.m2\wrapper\wrapper\dists\apache-maven-3.9.7-bin\3k9n615lchs6mp84v355m633uo\apache-maven-3.9.7\lib\jansi-native\Windows\x86_64\jansi.dll: Access is denied
Failed to load native library:jansi-2.4.1-d074009029932ccc-jansi.dll. osinfo: Windows/x86_64
java.lang.UnsatisfiedLinkError: C:\Users\v1014064\AppData\Local\Temp\jansi-2.4.1-d074009029932ccc-jansi.dll: Access is denied
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------------------< com:DASHBOARD >----------------------------
[INFO] Building DASHBOARD 0.0.2
[INFO]   from pom.xml
[INFO] --------------------------------[ war ]---------------------------------
[INFO] 
[INFO] --- clean:3.3.2:clean (default-clean) @ DASHBOARD ---
[INFO] Deleting D:\safe duplicates\Dashboard_local\target
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ DASHBOARD ---
[INFO] Copying 351 resources from WebContent to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ DASHBOARD ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 71 source files with javac [debug parameters release 8] to target\classes
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR :
[INFO] -------------------------------------------------------------
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/URLUTF8Encoder.java:[13,14] unmappable character for encoding UTF-8
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/URLUTF8Encoder.java:[16,42] unmappable character for encoding UTF-8
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedPrincipal.java:[11,32] package org.apache.log4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[8,26] package javax.servlet.http does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[9,26] package javax.servlet.http does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[11,56] cannot find symbol
  symbol: class HttpServletRequestWrapper
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[15,44] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.auth10.federation.FederatedHttpServletRequest
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[4,24] package org.apache.log4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[5,21] package org.joda.time does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[6,28] package org.joda.time.format does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[7,28] package org.joda.time.format does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[9,26] package javax.servlet.http does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[10,26] package javax.servlet.http does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[19,16] cannot find symbol
  symbol:   class Logger
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[20,30] cannot find symbol
  symbol:   class DateTimeFormatter
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[23,23] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[26,57] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[30,57] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[35,41] cannot find symbol
  symbol:   class HttpServletRequest
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[42,25] cannot find symbol
  symbol:   class HttpServletResponse
  location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[29,29] package org.apache.commons.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[30,21] package org.joda.time does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[31,21] package org.joda.time does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[32,20] package org.opensaml does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[33,20] package org.opensaml does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[34,27] package org.opensaml.common does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[35,31] package org.opensaml.saml2.core does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[36,31] package org.opensaml.saml2.core does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[37,37] package org.opensaml.saml2.encryption does not exist      
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[38,24] package org.opensaml.xml does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[39,24] package org.opensaml.xml does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[40,35] package org.opensaml.xml.encryption does not exist        
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[41,35] package org.opensaml.xml.encryption does not exist        
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[42,27] package org.opensaml.xml.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[43,27] package org.opensaml.xml.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[44,27] package org.opensaml.xml.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[45,27] package org.opensaml.xml.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[46,27] package org.opensaml.xml.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[47,27] package org.opensaml.xml.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[48,33] package org.opensaml.xml.security does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[49,33] package org.opensaml.xml.security does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[50,33] package org.opensaml.xml.security does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[51,44] package org.opensaml.xml.security.credential does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[52,44] package org.opensaml.xml.security.credential does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[53,42] package org.opensaml.xml.security.criteria does not exist 
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[54,41] package org.opensaml.xml.security.keyinfo does not exist  
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[55,41] package org.opensaml.xml.security.keyinfo does not exist  
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[56,41] package org.opensaml.xml.security.keyinfo does not exist  
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[57,38] package org.opensaml.xml.security.x509 does not exist     
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[58,34] package org.opensaml.xml.signature does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[59,34] package org.opensaml.xml.signature does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[60,39] package org.opensaml.xml.signature.impl does not exist    
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[61,35] package org.opensaml.xml.validation does not exist        
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[62,17] package org.slf4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[63,17] package org.slf4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[81,32] package org.apache.log4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[83,22] cannot find symbol
  symbol:   class Logger
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[86,44] cannot find symbol
  symbol:   class ConfigurationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[92,56] cannot find symbol
  symbol:   class ConfigurationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[129,46] cannot find symbol
  symbol:   class ConfigurationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[130,58] cannot find symbol
  symbol:   class ValidationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[131,25] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[231,24] cannot find symbol
  symbol:   class SignableSAMLObject
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[233,52] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[246,25] cannot find symbol
  symbol:   class EncryptedAssertion
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[245,24] cannot find symbol
  symbol:   class Assertion
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[294,24] cannot find symbol
  symbol:   class SignableSAMLObject
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[296,25] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[344,24] cannot find symbol
  symbol:   class EncryptedAssertion
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[361,41] cannot find symbol
  symbol:   class Assertion
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[401,25] cannot find symbol
  symbol:   class Assertion
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[409,48] package org.opensaml.saml1.core does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[417,44] cannot find symbol
  symbol:   class Instant
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[417,63] cannot find symbol
  symbol:   class Instant
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[433,46] cannot find symbol
  symbol:   class SignableSAMLObject
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[434,51] cannot find symbol
  symbol:   class ValidationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[435,25] cannot find symbol
  symbol:   class ConfigurationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[435,49] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[465,25] cannot find symbol
  symbol:   class SignableSAMLObject
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[466,32] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[466,56] cannot find symbol
  symbol:   class ValidationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[478,25] cannot find symbol
  symbol:   class SignableSAMLObject
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[479,32] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[479,56] cannot find symbol
  symbol:   class ValidationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[517,25] cannot find symbol
  symbol:   class Assertion
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[518,51] cannot find symbol
  symbol:   class ValidationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[519,25] cannot find symbol
  symbol:   class ConfigurationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[519,49] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[543,48] package org.opensaml.saml1.core does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[544,51] cannot find symbol
  symbol:   class ValidationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[545,25] cannot find symbol
  symbol:   class ConfigurationException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[545,49] cannot find symbol
  symbol:   class UnmarshallingException
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[568,49] cannot find symbol
  symbol:   class XMLObject
  location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[3,17] package org.slf4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[4,17] package org.slf4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[6,1] package javax.servlet does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[7,26] package javax.servlet.http does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[8,26] package javax.servlet.http does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[12,44] cannot find symbol
  symbol: class Filter
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[21,15] cannot find symbol
  symbol:   class Logger
  location: class com.auth10.federation.WSFederationFilter
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[22,32] package org.apache.log4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[29,26] cannot find symbol
  symbol:   class FilterConfig
  location: class com.auth10.federation.WSFederationFilter
[INFO] 102 errors
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  26.971 s
[INFO] Finished at: 2024-12-02T13:16:39+05:30
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.13.0:compile (default-compile) on project DASHBOARD: Compilation failure: Compilation failure:
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/URLUTF8Encoder.java:[13,14] unmappable character for encoding UTF-8
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/URLUTF8Encoder.java:[16,42] unmappable character for encoding UTF-8
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedPrincipal.java:[11,32] package org.apache.log4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[8,26] package javax.servlet.http does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[9,26] package javax.servlet.http does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[11,56] cannot find symbol
[ERROR]   symbol: class HttpServletRequestWrapper
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedHttpServletRequest.java:[15,44] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.auth10.federation.FederatedHttpServletRequest
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[4,24] package org.apache.log4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[5,21] package org.joda.time does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[6,28] package org.joda.time.format does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[7,28] package org.joda.time.format does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[9,26] package javax.servlet.http does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[10,26] package javax.servlet.http does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[19,16] cannot find symbol
[ERROR]   symbol:   class Logger
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[20,30] cannot find symbol
[ERROR]   symbol:   class DateTimeFormatter
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[23,23] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[26,57] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[30,57] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[35,41] cannot find symbol
[ERROR]   symbol:   class HttpServletRequest
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/FederatedLoginManager.java:[42,25] cannot find symbol
[ERROR]   symbol:   class HttpServletResponse
[ERROR]   location: class com.auth10.federation.FederatedLoginManager
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[29,29] package org.apache.commons.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[30,21] package org.joda.time does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[31,21] package org.joda.time does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[32,20] package org.opensaml does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[33,20] package org.opensaml does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[34,27] package org.opensaml.common does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[35,31] package org.opensaml.saml2.core does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[36,31] package org.opensaml.saml2.core does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[37,37] package org.opensaml.saml2.encryption does not exist      
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[38,24] package org.opensaml.xml does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[39,24] package org.opensaml.xml does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[40,35] package org.opensaml.xml.encryption does not exist        
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[41,35] package org.opensaml.xml.encryption does not exist        
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[42,27] package org.opensaml.xml.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[43,27] package org.opensaml.xml.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[44,27] package org.opensaml.xml.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[45,27] package org.opensaml.xml.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[46,27] package org.opensaml.xml.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[47,27] package org.opensaml.xml.io does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[48,33] package org.opensaml.xml.security does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[49,33] package org.opensaml.xml.security does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[50,33] package org.opensaml.xml.security does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[51,44] package org.opensaml.xml.security.credential does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[52,44] package org.opensaml.xml.security.credential does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[53,42] package org.opensaml.xml.security.criteria does not exist 
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[54,41] package org.opensaml.xml.security.keyinfo does not exist  
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[55,41] package org.opensaml.xml.security.keyinfo does not exist  
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[56,41] package org.opensaml.xml.security.keyinfo does not exist  
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[57,38] package org.opensaml.xml.security.x509 does not exist     
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[58,34] package org.opensaml.xml.signature does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[59,34] package org.opensaml.xml.signature does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[60,39] package org.opensaml.xml.signature.impl does not exist    
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[61,35] package org.opensaml.xml.validation does not exist        
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[62,17] package org.slf4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[63,17] package org.slf4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[81,32] package org.apache.log4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[83,22] cannot find symbol
[ERROR]   symbol:   class Logger
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[86,44] cannot find symbol
[ERROR]   symbol:   class ConfigurationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[92,56] cannot find symbol
[ERROR]   symbol:   class ConfigurationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[129,46] cannot find symbol
[ERROR]   symbol:   class ConfigurationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[130,58] cannot find symbol
[ERROR]   symbol:   class ValidationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[131,25] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[231,24] cannot find symbol
[ERROR]   symbol:   class SignableSAMLObject
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[233,52] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[246,25] cannot find symbol
[ERROR]   symbol:   class EncryptedAssertion
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[245,24] cannot find symbol
[ERROR]   symbol:   class Assertion
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[294,24] cannot find symbol
[ERROR]   symbol:   class SignableSAMLObject
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[296,25] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[344,24] cannot find symbol
[ERROR]   symbol:   class EncryptedAssertion
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[361,41] cannot find symbol
[ERROR]   symbol:   class Assertion
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[401,25] cannot find symbol
[ERROR]   symbol:   class Assertion
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[409,48] package org.opensaml.saml1.core does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[417,44] cannot find symbol
[ERROR]   symbol:   class Instant
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[417,63] cannot find symbol
[ERROR]   symbol:   class Instant
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[433,46] cannot find symbol
[ERROR]   symbol:   class SignableSAMLObject
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[434,51] cannot find symbol
[ERROR]   symbol:   class ValidationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[435,25] cannot find symbol
[ERROR]   symbol:   class ConfigurationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[435,49] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[465,25] cannot find symbol
[ERROR]   symbol:   class SignableSAMLObject
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[466,32] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[466,56] cannot find symbol
[ERROR]   symbol:   class ValidationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[478,25] cannot find symbol
[ERROR]   symbol:   class SignableSAMLObject
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[479,32] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[479,56] cannot find symbol
[ERROR]   symbol:   class ValidationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[517,25] cannot find symbol
[ERROR]   symbol:   class Assertion
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[518,51] cannot find symbol
[ERROR]   symbol:   class ValidationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[519,25] cannot find symbol
[ERROR]   symbol:   class ConfigurationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[519,49] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[543,48] package org.opensaml.saml1.core does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[544,51] cannot find symbol
[ERROR]   symbol:   class ValidationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[545,25] cannot find symbol
[ERROR]   symbol:   class ConfigurationException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[545,49] cannot find symbol
[ERROR]   symbol:   class UnmarshallingException
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/SamlTokenValidator.java:[568,49] cannot find symbol
[ERROR]   symbol:   class XMLObject
[ERROR]   location: class com.auth10.federation.SamlTokenValidator
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[3,17] package org.slf4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[4,17] package org.slf4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[6,1] package javax.servlet does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[7,26] package javax.servlet.http does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[8,26] package javax.servlet.http does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[12,44] cannot find symbol
[ERROR]   symbol: class Filter
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[21,15] cannot find symbol
[ERROR]   symbol:   class Logger
[ERROR]   location: class com.auth10.federation.WSFederationFilter
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[22,32] package org.apache.log4j does not exist
[ERROR] /D:/safe duplicates/Dashboard_local/src/com/auth10/federation/WSFederationFilter.java:[29,26] cannot find symbol
[ERROR]   symbol:   class FilterConfig
[ERROR]   location: class com.auth10.federation.WSFederationFilter
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
PS D:\safe duplicates\Dashboard_local> 
