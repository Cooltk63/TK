D:\jdk1.8.0_351\bin\java.exe "-javaagent:E:\Tushar Khade\Softwares\2025-IDE\IntelliSD\lib\idea_rt.jar=54464" -Dfile.encoding=UTF-8 -classpath "D:\jdk1.8.0_351\jre\lib\charsets.jar;D:\jdk1.8.0_351\jre\lib\deploy.jar;D:\jdk1.8.0_351\jre\lib\ext\access-bridge-64.jar;D:\jdk1.8.0_351\jre\lib\ext\cldrdata.jar;D:\jdk1.8.0_351\jre\lib\ext\dnsns.jar;D:\jdk1.8.0_351\jre\lib\ext\jaccess.jar;D:\jdk1.8.0_351\jre\lib\ext\jfxrt.jar;D:\jdk1.8.0_351\jre\lib\ext\localedata.jar;D:\jdk1.8.0_351\jre\lib\ext\nashorn.jar;D:\jdk1.8.0_351\jre\lib\ext\sunec.jar;D:\jdk1.8.0_351\jre\lib\ext\sunjce_provider.jar;D:\jdk1.8.0_351\jre\lib\ext\sunmscapi.jar;D:\jdk1.8.0_351\jre\lib\ext\sunpkcs11.jar;D:\jdk1.8.0_351\jre\lib\ext\zipfs.jar;D:\jdk1.8.0_351\jre\lib\javaws.jar;D:\jdk1.8.0_351\jre\lib\jce.jar;D:\jdk1.8.0_351\jre\lib\jfr.jar;D:\jdk1.8.0_351\jre\lib\jfxswt.jar;D:\jdk1.8.0_351\jre\lib\jsse.jar;D:\jdk1.8.0_351\jre\lib\management-agent.jar;D:\jdk1.8.0_351\jre\lib\plugin.jar;D:\jdk1.8.0_351\jre\lib\resources.jar;D:\jdk1.8.0_351\jre\lib\rt.jar;F:\Projects\CRS Projects\CRS_Revamp\Backend\decryption_IFAMS\out\production\decryption_IFAMS" AESDecryption
Encrypted data length: 7024
IV Length: 16
CipherText Length: 7008
Raw Key File (Base64): tfc54T2/ZsUj1uIubosxdqglCrMjTMQVuPYs4c/2WQYxY4zBEOeFt8wGMMt5JlXVHdnRqR2GaCVo5Yjbag6rEMR7cFvDdk5oXvs41/B6KE96rHhgKul1CNYGtpptl8qHAeXM1kcn6qEO9C9vpukmu54BmgO7vo2tJOFJLHi9l/CxEUSVhgaFG2e+LhxlC+qOcvnZIJk6uBbNYV1tdBPO1FyTNfFRN2YNwCiuWM0Sr4KKssm3ugP+GJ08p5ZdiW2RmdEy4htGaHyTv/GLfmEWh0RP5IkVM2dMXnx/6GKOdB5oZWGOw0yAGBtyJfZvNwFqd0srWAIaDJYHn9b7i0TKWQ==
Decoded Key Length: 256
finalKey Length: 16
Decryption Error: Given final block not properly padded. Such issues can arise if a bad key is used during decryption.
javax.crypto.BadPaddingException: Given final block not properly padded. Such issues can arise if a bad key is used during decryption.
	at com.sun.crypto.provider.CipherCore.unpad(CipherCore.java:975)
	at com.sun.crypto.provider.CipherCore.fillOutputBuffer(CipherCore.java:1056)
	at com.sun.crypto.provider.CipherCore.doFinal(CipherCore.java:853)
	at com.sun.crypto.provider.AESCipher.engineDoFinal(AESCipher.java:446)
	at javax.crypto.Cipher.doFinal(Cipher.java:2172)
	at AESDecryption.main(AESDecryption.java:54)

Process finished with exit code 0


Still getiing this error try different way to get the IV from encrypted
