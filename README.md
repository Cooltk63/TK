Getting this error

PS F:\Projects\CRS Projects\CRS ReWork\backend\SSOLoginService>  & 'D:\Java22\bin\java.exe' '@C:\Users\v1012297\AppData\Local\Temp\cp_56jd319gtytxxjms604ppthdw.argfile' 'com.crs.SsoLoginService.SsoService'
Error: Could not find or load main class com.crs.SsoLoginService.SsoService    
Caused by: java.lang.ClassNotFoundException: com.crs.SsoLoginService.SsoService
PS F:\Projects\CRS Projects\CRS ReWork\backend\SSOLoginService> 

 below is the file content for @C:\Users\v1012297\AppData\Local\Temp\cp_56jd319gtytxxjms604ppthdw.argfile this file
 -XX:+ShowCodeDetailsInExceptionMessages -cp "F:\\Projects\\CRS Projects\\CRS ReWork\\backend\\SSOLoginService\\target\\classes;F:\\Projects\\CRS Projects\\CRS ReWork\\backend\\SSOLoginService\\src\\main\\resources\\lib;F:\\Projects\\CRS Projects\\CRS ReWork\\backend\\SSOLoginService\\src\\main\\resources\\sso_lib"
