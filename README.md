Error: A JNI error has occurred, please check your installation and try again
2025-07-30T17:50:52.096540700Z Exception in thread "main" java.lang.SecurityException: Invalid signature file digest for Manifest main attributes
2025-07-30T17:50:52.097239900Z 	at java.base/sun.security.util.SignatureFileVerifier.processImpl(SignatureFileVerifier.java:339)
2025-07-30T17:50:52.097255400Z 	at java.base/sun.security.util.SignatureFileVerifier.process(SignatureFileVerifier.java:281)
2025-07-30T17:50:52.097415200Z 	at java.base/java.util.jar.JarVerifier.processEntry(JarVerifier.java:323)
2025-07-30T17:50:52.097610700Z 	at java.base/java.util.jar.JarVerifier.update(JarVerifier.java:235)
2025-07-30T17:50:52.097619700Z 	at java.base/java.util.jar.JarFile.initializeVerifier(JarFile.java:739)
2025-07-30T17:50:52.097622400Z 	at java.base/java.util.jar.JarFile.ensureInitialization(JarFile.java:1049)
2025-07-30T17:50:52.097624500Z 	at java.base/java.util.jar.JavaUtilJarAccessImpl.ensureInitialization(JavaUtilJarAccessImpl.java:42)
2025-07-30T17:50:52.098470900Z 	at java.base/jdk.internal.loader.URLClassPath$JarLoader$1.getManifest(URLClassPath.java:720)
2025-07-30T17:50:52.098491300Z 	at java.base/jdk.internal.loader.BuiltinClassLoader.defineClass(BuiltinClassLoader.java:762)
2025-07-30T17:50:52.098493900Z 	at java.base/jdk.internal.loader.BuiltinClassLoader.findClassOnClassPathOrNull(BuiltinClassLoader.java:691)
2025-07-30T17:50:52.098495900Z 	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:620)
2025-07-30T17:50:52.098498000Z 	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:578)
2025-07-30T17:50:52.098500000Z 	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:490)
2025-07-30T17:50:52.098503100Z 	at java.base/java.lang.Class.forName0(Native Method)
2025-07-30T17:50:52.098505000Z 	at java.base/java.lang.Class.forName(Class.java:543)
2025-07-30T17:50:52.098506800Z 	at java.base/sun.launcher.LauncherHelper.loadMainClass(LauncherHelper.java:863)
2025-07-30T17:50:52.098508800Z 	at java.base/sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:748)

even after following all of your steps getting this error what the hell is this error I am really frusstrated with this isue.
