Installing JAR: activation.jar
Failed to load native library:jansi.dll. osinfo: Windows/x86_64
java.lang.UnsatisfiedLinkError: C:\Users\v1014064\.m2\wrapper\wrapper\dists\apache-maven-3.9.7-bin\3k9n615lchs6mp84v355m633uo\apache-maven-3.9.7\lib\jansi-native\Windows\x86_64\jansi.dll: Access is denied
Failed to load native library:jansi-2.4.1-14b06701e786f59b-jansi.dll. osinfo: Windows/x86_64
java.lang.UnsatisfiedLinkError: C:\Users\v1014064\AppData\Local\Temp\jansi-2.4.1-14b06701e786f59b-jansi.dll: Access is denied
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------< org.apache.maven:standalone-pom >-------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] --------------------------------[ pom ]---------------------------------
[INFO]
[INFO] --- install:3.1.1:install-file (default-cli) @ standalone-pom ---
[ERROR] The specified file 'D:\activation.jar' does not exist
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.766 s
[INFO] Finished at: 2024-12-02T17:31:36+05:30
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-install-plugin:3.1.1:install-file (default-cli) on project standalone-pom: The specified file 'D:\activation.jar' does not exist -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException

D:\>(
set "file=avalon-framework-4.1.3.jar"
 set "artifactId=avalon-framework-4.1.3"
 echo Installing JAR: !file!
 mvn install:install-file         -Dfile="!file!"         -DgroupId=custom.group         -DartifactId=!artifactId!         -Dversion=1.0.0         -Dpackaging=jar
)
