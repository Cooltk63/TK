
@echo off
setlocal enabledelayedexpansion

REM Path to the directory containing the JARs
set jarDir=D:/safe_duplicates/Dashboard_local/WebContent/WEB-INF/lib

REM Group ID, Artifact ID, and Version (Modify these if needed)
set groupId=custom.group
set version=1.0.0

for %%f in ("%jarDir%\*.jar") do (
    set "file=%%~nxf"
    set "artifactId=%%~nf"
    echo Installing JAR: !file!
    mvn install:install-file ^
        -Dfile="!file!" ^
        -DgroupId=%groupId% ^
        -DartifactId=!artifactId! ^
        -Dversion=%version% ^
        -Dpackaging=jar
)
pause