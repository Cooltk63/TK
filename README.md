java.security.InvalidAlgorithmParameterException: Wrong IV length: must be 16 bytes long
	at java.base/com.sun.crypto.provider.CipherCore.init(CipherCore.java:442)
	at java.base/com.sun.crypto.provider.AESCipher.engineInit(AESCipher.java:344)
	at java.base/javax.crypto.Cipher.implInit(Cipher.java:876)
	at java.base/javax.crypto.Cipher.chooseProvider(Cipher.java:934)
	at java.base/javax.crypto.Cipher.init(Cipher.java:1466)
	at java.base/javax.crypto.Cipher.init(Cipher.java:1393)
	at AES.AESFileTool.encrypt(AESFileTool.java:23)
	at decryptionLG.YourApp.main(YourApp.java:15)
