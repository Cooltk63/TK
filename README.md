cd F:\Projects\CRS Projects\CRS ReWork\backend\commonReportsService; JAVA_HOME=D:\\Java22 cmd /c "\"C:\\Users\\v1012297\\.vscode\\extensions\\oracle.oracle-java-23.0.0\\nbcode\\java\\maven\\bin\\mvn.cmd\" -Dexec.vmArgs=-Dfile.encoding=UTF-8 \"-Dexec.args=${exec.vmArgs} -classpath %classpath ${exec.mainClass} ${exec.appArgs}\" -Dexec.executable=java -Dexec.mainClass=com.crs.commonReportsService.commonReportsService -Dexec.classpathScope=runtime -DskipTests=true -Dexec.appArgs= -Dmaven.ext.class.path=C:\\Users\\v1012297\\.vscode\\extensions\\oracle.oracle-java-23.0.0\\nbcode\\java\\maven-nblib\\netbeans-eventspy.jar --no-transfer-progress process-classes org.codehaus.mojo:exec-maven-plugin:3.1.0:exec"
Scanning for projects...
------------------------------------------------------------------------
BUILD FAILURE
------------------------------------------------------------------------
Total time:  1.349 s

Finished at: 2024-11-22T12:29:45+05:30
------------------------------------------------------------------------
Plugin org.codehaus.mojo:exec-maven-plugin:3.1.0 or one of its dependencies could not be resolved:
	The following artifacts could not be resolved: org.codehaus.mojo:exec-maven-plugin:pom:3.1.0 (absent): Could not transfer artifact org.codehaus.mojo:exec-maven-plugin:pom:3.1.0 from/to central (https://repo.maven.apache.org/maven2): PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
-> [Help 1]

To see the full stack trace of the errors, re-run Maven with the -e switch.
Re-run Maven using the -X switch to enable full debug logging.


i am getting this error while run main class of my spring boot project
