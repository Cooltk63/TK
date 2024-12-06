<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="src" output="target/classes" path="src/main/java">
		<attributes>
			<attribute name="optional" value="true"/>
			<attribute name="maven.pomderived" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry excluding="**" kind="src" output="target/classes" path="src/main/resources">
		<attributes>
			<attribute name="maven.pomderived" value="true"/>
			<attribute name="optional" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="src" output="target/test-classes" path="src/test/java">
		<attributes>
			<attribute name="optional" value="true"/>
			<attribute name="maven.pomderived" value="true"/>
			<attribute name="test" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-17">
		<attributes>
			<attribute name="maven.pomderived" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="con" path="org.eclipse.m2e.MAVEN2_CLASSPATH_CONTAINER">
		<attributes>
			<attribute name="maven.pomderived" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry excluding="**" kind="src" output="target/test-classes" path="src/test/resources">
		<attributes>
			<attribute name="maven.pomderived" value="true"/>
			<attribute name="test" value="true"/>
			<attribute name="optional" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="src" path="target/generated-sources/annotations">
		<attributes>
			<attribute name="optional" value="true"/>
			<attribute name="maven.pomderived" value="true"/>
			<attribute name="ignore_optional_problems" value="true"/>
			<attribute name="m2e-apt" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="lib" path="src/main/resources/lib"/>
	<classpathentry kind="lib" path="src/main/resources/sso_lib"/>
	<classpathentry kind="src" output="target/test-classes" path="target/generated-test-sources/test-annotations">
		<attributes>
			<attribute name="optional" value="true"/>
			<attribute name="maven.pomderived" value="true"/>
			<attribute name="ignore_optional_problems" value="true"/>
			<attribute name="m2e-apt" value="true"/>
			<attribute name="test" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="output" path="target/classes"/>
</classpath>


This is my .classpath file 

this the rrro i am getting

PS F:\Projects\CRS Projects\CRS ReWork\backend\SSOLoginService>  & 'D:\Java22\bin\java.exe' '@C:\Users\v1012297\AppData\Local\Temp\cp_56jd319gtytxxjms604ppthdw.argfile' 'com.crs.SsoLoginService.SsoService'
Error: Could not find or load main class com.crs.SsoLoginService.SsoService    
Caused by: java.lang.ClassNotFoundException: com.crs.SsoLoginService.SsoService****
