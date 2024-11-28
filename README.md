PS D:\Dashboard_local> mvn clean package
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------------------< com:DASHBOARD >----------------------------
[INFO] Building DASHBOARD 0.0.2
[INFO]   from pom.xml
[INFO] --------------------------------[ war ]---------------------------------
[INFO] 
[INFO] --- clean:3.3.2:clean (default-clean) @ DASHBOARD ---
[INFO] Deleting D:\Dashboard_local\target
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ DASHBOARD ---
[INFO] skip non existing resourceDirectory D:\Dashboard_local\src\main\resources
[INFO] skip non existing resourceDirectory D:\Dashboard_local\src\main\resources
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ DASHBOARD ---
[INFO] No sources to compile
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ DASHBOARD ---
[INFO] skip non existing resourceDirectory D:\Dashboard_local\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ DASHBOARD ---
[INFO] No sources to compile
[INFO]
[INFO] --- surefire:3.2.5:test (default-test) @ DASHBOARD ---
[INFO] No tests to run.
[INFO]
[INFO] --- war:3.4.0:war (default-war) @ DASHBOARD ---
[INFO] Packaging webapp
[INFO] Assembling webapp [DASHBOARD] in [D:\Dashboard_local\target\DASHBOARD-0.0.2]
[INFO] Processing war project
[INFO] Building war: D:\Dashboard_local\target\DASHBOARD-0.0.2.war
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.007 s
[INFO] Finished at: 2024-11-28T19:36:06+05:30
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-war-plugin:3.4.0:war (default-war) on project DASHBOARD: Error assembling WAR: webxml attribute is required (or pre-existing WEB-INF/web.xml if executing in update 
mode) -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException
