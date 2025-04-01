D:\jdk1.8.0_351\bin\java.exe "-javaagent:E:\Tushar Khade\Softwares\2025-IDE\IntelliSD\lib\idea_rt.jar=64482" -Dfile.encoding=UTF-8 -classpath "D:\jdk1.8.0_351\jre\lib\charsets.jar;D:\jdk1.8.0_351\jre\lib\deploy.jar;D:\jdk1.8.0_351\jre\lib\ext\access-bridge-64.jar;D:\jdk1.8.0_351\jre\lib\ext\cldrdata.jar;D:\jdk1.8.0_351\jre\lib\ext\dnsns.jar;D:\jdk1.8.0_351\jre\lib\ext\jaccess.jar;D:\jdk1.8.0_351\jre\lib\ext\jfxrt.jar;D:\jdk1.8.0_351\jre\lib\ext\localedata.jar;D:\jdk1.8.0_351\jre\lib\ext\nashorn.jar;D:\jdk1.8.0_351\jre\lib\ext\sunec.jar;D:\jdk1.8.0_351\jre\lib\ext\sunjce_provider.jar;D:\jdk1.8.0_351\jre\lib\ext\sunmscapi.jar;D:\jdk1.8.0_351\jre\lib\ext\sunpkcs11.jar;D:\jdk1.8.0_351\jre\lib\ext\zipfs.jar;D:\jdk1.8.0_351\jre\lib\javaws.jar;D:\jdk1.8.0_351\jre\lib\jce.jar;D:\jdk1.8.0_351\jre\lib\jfr.jar;D:\jdk1.8.0_351\jre\lib\jfxswt.jar;D:\jdk1.8.0_351\jre\lib\jsse.jar;D:\jdk1.8.0_351\jre\lib\management-agent.jar;D:\jdk1.8.0_351\jre\lib\plugin.jar;D:\jdk1.8.0_351\jre\lib\resources.jar;D:\jdk1.8.0_351\jre\lib\rt.jar;F:\Projects\CRS Projects\CRS_Revamp\Backend\decryption_IFAMS\out\production\decryption_IFAMS" AESDecryption
🔹 Encrypted Data Length: 7024
🔹 IV (Hex): 6E 96 71 21 0C 43 80 BE 73 87 16 18 F9 06 FB DC
🔹 CipherText Length: 7008
🔹 Key (Base64 Encoded): tfc54T2/ZsUj1uIubosxdqglCrMjTMQVuPYs4c/2WQYxY4zBEOeFt8wGMMt5JlXVHdnRqR2GaCVo5Yjbag6rEMR7cFvDdk5oXvs41/B6KE96rHhgKul1CNYGtpptl8qHAeXM1kcn6qEO9C9vpukmu54BmgO7vo2tJOFJLHi9l/CxEUSVhgaFG2e+LhxlC+qOcvnZIJk6uBbNYV1tdBPO1FyTNfFRN2YNwCiuWM0Sr4KKssm3ugP+GJ08p5ZdiW2RmdEy4htGaHyTv/GLfmEWh0RP5IkVM2dMXnx/6GKOdB5oZWGOw0yAGBtyJfZvNwFqd0srWAIaDJYHn9b7i0TKWQ==
🔹 Decoded Key (Hex): B5 F7 39 E1 3D BF 66 C5 23 D6 E2 2E 6E 8B 31 76 A8 25 0A B3 23 4C C4 15 B8 F6 2C E1 CF F6 59 06 31 63 8C C1 10 E7 85 B7 CC 06 30 CB 79 26 55 D5 1D D9 D1 A9 1D 86 68 25 68 E5 88 DB 6A 0E AB 10 C4 7B 70 5B C3 76 4E 68 5E FB 38 D7 F0 7A 28 4F 7A AC 78 60 2A E9 75 08 D6 06 B6 9A 6D 97 CA 87 01 E5 CC D6 47 27 EA A1 0E F4 2F 6F A6 E9 26 BB 9E 01 9A 03 BB BE 8D AD 24 E1 49 2C 78 BD 97 F0 B1 11 44 95 86 06 85 1B 67 BE 2E 1C 65 0B EA 8E 72 F9 D9 20 99 3A B8 16 CD 61 5D 6D 74 13 CE D4 5C 93 35 F1 51 37 66 0D C0 28 AE 58 CD 12 AF 82 8A B2 C9 B7 BA 03 FE 18 9D 3C A7 96 5D 89 6D 91 99 D1 32 E2 1B 46 68 7C 93 BF F1 8B 7E 61 16 87 44 4F E4 89 15 33 67 4C 5E 7C 7F E8 62 8E 74 1E 68 65 61 8E C3 4C 80 18 1B 72 25 F6 6F 37 01 6A 77 4B 2B 58 02 1A 0C 96 07 9F D6 FB 8B 44 CA 59
🔹 Decoded Key Length: 256
🔹 Final Key Length: 16
❌ Decryption Error: Given final block not properly padded. Such issues can arise if a bad key is used during decryption.
javax.crypto.BadPaddingException: Given final block not properly padded. Such issues can arise if a bad key is used during decryption.
	at com.sun.crypto.provider.CipherCore.unpad(CipherCore.java:975)
	at com.sun.crypto.provider.CipherCore.fillOutputBuffer(CipherCore.java:1056)
	at com.sun.crypto.provider.CipherCore.doFinal(CipherCore.java:853)
	at com.sun.crypto.provider.AESCipher.engineDoFinal(AESCipher.java:446)
	at javax.crypto.Cipher.doFinal(Cipher.java:2172)
	at AESDecryption.decryptAES(AESDecryption.java:69)
	at AESDecryption.main(AESDecryption.java:50)

Process finished with exit code 0
