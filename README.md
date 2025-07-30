Error: A JNI error has occurred, please check your installation and try again
2025-07-30T17:26:39.244783700Z Exception in thread "main" java.lang.SecurityException: Invalid signature file digest for Manifest main attributes
2025-07-30T17:26:39.246105700Z 	at java.base/sun.security.util.SignatureFileVerifier.processImpl(SignatureFileVerifier.java:339)
2025-07-30T17:26:39.246135300Z 	at java.base/sun.security.util.SignatureFileVerifier.process(SignatureFileVerifier.java:281)
2025-07-30T17:26:39.246697000Z 	at java.base/java.util.jar.JarVerifier.processEntry(JarVerifier.java:323)
2025-07-30T17:26:39.248144200Z 	at java.base/java.util.jar.JarVerifier.update(JarVerifier.java:235)
2025-07-30T17:26:39.248216100Z 	at java.base/java.util.jar.JarFile.initializeVerifier(JarFile.java:739)
2025-07-30T17:26:39.248222800Z 	at java.base/java.util.jar.JarFile.ensureInitialization(JarFile.java:1049)
2025-07-30T17:26:39.248224800Z 	at java.base/java.util.jar.JavaUtilJarAccessImpl.ensureInitialization(JavaUtilJarAccessImpl.java:42)
2025-07-30T17:26:39.248321700Z 	at java.base/jdk.internal.loader.URLClassPath$JarLoader$1.getManifest(URLClassPath.java:720)
2025-07-30T17:26:39.248328700Z 	at java.base/jdk.internal.loader.BuiltinClassLoader.defineClass(BuiltinClassLoader.java:762)
2025-07-30T17:26:39.248682800Z 	at java.base/jdk.internal.loader.BuiltinClassLoader.findClassOnClassPathOrNull(BuiltinClassLoader.java:691)
2025-07-30T17:26:39.248696700Z 	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:620)
2025-07-30T17:26:39.248698800Z 	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:578)
2025-07-30T17:26:39.248700300Z 	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:490)
2025-07-30T17:26:39.248702100Z 	at java.base/java.lang.Class.forName0(Native Method)
2025-07-30T17:26:39.248703600Z 	at java.base/java.lang.Class.forName(Class.java:543)
2025-07-30T17:26:39.248705200Z 	at java.base/sun.launcher.LauncherHelper.loadMainClass(LauncherHelper.java:863)
2025-07-30T17:26:39.248706900Z 	at java.base/sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:748)


getting this erorr how to reoslve this error 
