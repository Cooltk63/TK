Getting this error while ruuning the docker file I dont know Evething is perfect if not just give me simple java Project with controller & following pom.xml dependency
Error: A JNI error has occurred, please check your installation and try again
2025-08-01T13:41:56.246356800Z Exception in thread "main" java.lang.SecurityException: Invalid signature file digest for Manifest main attributes
2025-08-01T13:41:56.247437800Z 	at java.base/sun.security.util.SignatureFileVerifier.processImpl(SignatureFileVerifier.java:339)
2025-08-01T13:41:56.247462600Z 	at java.base/sun.security.util.SignatureFileVerifier.process(SignatureFileVerifier.java:281)
2025-08-01T13:41:56.247464900Z 	at java.base/java.util.jar.JarVerifier.processEntry(JarVerifier.java:323)
2025-08-01T13:41:56.247466700Z 	at java.base/java.util.jar.JarVerifier.update(JarVerifier.java:235)
2025-08-01T13:41:56.247468400Z 	at java.base/java.util.jar.JarFile.initializeVerifier(JarFile.java:739)
2025-08-01T13:41:56.247469900Z 	at java.base/java.util.jar.JarFile.ensureInitialization(JarFile.java:1049)
2025-08-01T13:41:56.247471500Z 	at java.base/java.util.jar.JavaUtilJarAccessImpl.ensureInitialization(JavaUtilJarAccessImpl.java:42)
2025-08-01T13:41:56.247473000Z 	at java.base/jdk.internal.loader.URLClassPath$JarLoader$1.getManifest(URLClassPath.java:720)
2025-08-01T13:41:56.247474600Z 	at java.base/jdk.internal.loader.BuiltinClassLoader.defineClass(BuiltinClassLoader.java:762)
2025-08-01T13:41:56.247476300Z 	at java.base/jdk.internal.loader.BuiltinClassLoader.findClassOnClassPathOrNull(BuiltinClassLoader.java:691)
2025-08-01T13:41:56.247477800Z 	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:620)
2025-08-01T13:41:56.247479600Z 	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:578)
2025-08-01T13:41:56.247481100Z 	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:490)
2025-08-01T13:41:56.247482500Z 	at java.base/java.lang.Class.forName0(Native Method)
2025-08-01T13:41:56.247484000Z 	at java.base/java.lang.Class.forName(Class.java:543)
2025-08-01T13:41:56.247485500Z 	at java.base/sun.launcher.LauncherHelper.loadMainClass(LauncherHelper.java:863)
2025-08-01T13:41:56.248140800Z 	at java.base/sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:748)

Docker File ::

# Use Red Hat UBI with OpenJDK 17 (if accessible internally)
FROM cimg/openjdk:24.0.2-node

# Set working directory inside container
WORKDIR /app

# Set environment variable (optional)
#ENV SPRING_PROFILES_ACTIVE=prod

# Copy JAR file into the container
COPY out/artifacts/Fincore_jar/*.jar app.jar

# Run your Spring Boot app
CMD ["java", "-jar", "app.jar"]

is there any issue in configuration or naything


