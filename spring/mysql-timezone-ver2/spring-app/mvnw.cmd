@echo off
setlocal

set MAVEN_HOME=%~dp0\.mvn\wrapper\maven-wrapper.jar
set MAVEN_OPTS=-Xmx1024m

if not exist "%MAVEN_HOME%" (
    echo "Maven wrapper jar not found. Please run 'mvn -N io.takari:maven:wrapper' to generate it."
    exit /b 1
)

java -jar "%MAVEN_HOME%" %*