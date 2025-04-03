D:\jdk1.8.0_351\bin\java.exe "-javaagent:E:\Tushar Khade\Softwares\2025-IDE\IntelliSD\lib\idea_rt.jar=65034" -Dfile.encoding=UTF-8 -classpath "D:\jdk1.8.0_351\jre\lib\charsets.jar;D:\jdk1.8.0_351\jre\lib\deploy.jar;D:\jdk1.8.0_351\jre\lib\ext\access-bridge-64.jar;D:\jdk1.8.0_351\jre\lib\ext\cldrdata.jar;D:\jdk1.8.0_351\jre\lib\ext\dnsns.jar;D:\jdk1.8.0_351\jre\lib\ext\jaccess.jar;D:\jdk1.8.0_351\jre\lib\ext\jfxrt.jar;D:\jdk1.8.0_351\jre\lib\ext\localedata.jar;D:\jdk1.8.0_351\jre\lib\ext\nashorn.jar;D:\jdk1.8.0_351\jre\lib\ext\sunec.jar;D:\jdk1.8.0_351\jre\lib\ext\sunjce_provider.jar;D:\jdk1.8.0_351\jre\lib\ext\sunmscapi.jar;D:\jdk1.8.0_351\jre\lib\ext\sunpkcs11.jar;D:\jdk1.8.0_351\jre\lib\ext\zipfs.jar;D:\jdk1.8.0_351\jre\lib\javaws.jar;D:\jdk1.8.0_351\jre\lib\jce.jar;D:\jdk1.8.0_351\jre\lib\jfr.jar;D:\jdk1.8.0_351\jre\lib\jfxswt.jar;D:\jdk1.8.0_351\jre\lib\jsse.jar;D:\jdk1.8.0_351\jre\lib\management-agent.jar;D:\jdk1.8.0_351\jre\lib\plugin.jar;D:\jdk1.8.0_351\jre\lib\resources.jar;D:\jdk1.8.0_351\jre\lib\rt.jar;F:\Projects\CRS Projects\CRS_Revamp\Backend\decryption_IFAMS\out\production\decryption_IFAMS" decryptPayload
Decryption Error: Illegal base64 character 3f
java.lang.IllegalArgumentException: Illegal base64 character 3f
	at java.util.Base64$Decoder.decode0(Base64.java:714)
	at java.util.Base64$Decoder.decode(Base64.java:526)
	at java.util.Base64$Decoder.decode(Base64.java:549)
	at decryptPayload.decryptPayload(decryptPayload.java:46)
	at decryptPayload.main(decryptPayload.java:30)

Process finished with exit code 0
