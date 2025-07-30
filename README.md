[ERROR] Failed to execute goal org.springframework.boot:spring-boot-maven-plugin:3.5.4:repackage (repackage) on project Fincore: Execution repackage of goal org.springframework.boot:spring-boot-maven-plugin:3.5.4:repackage failed: Plugin org.springframework.boot:spring-boot-maven-plugin:3.5.4 or one of its dependencies could not be resolved:
[ERROR] 	Failed to read artifact descriptor for commons-codec:commons-codec:jar:1.17.1
[ERROR] 	Failed to read artifact descriptor for org.apache.commons:commons-lang3:jar:3.16.0
[ERROR] 	Failed to read artifact descriptor for io.micrometer:micrometer-observation:jar:1.14.9
[ERROR] 	Failed to read artifact descriptor for org.apache.maven.plugins:maven-shade-plugin:jar:3.6.0
[ERROR] 
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/PluginResolutionException

Getting this error after running the command 

mvn clean package -DskipTests
