# springboot-3-course

This is my own course work while following this course: https://github.com/darbyluv2code/spring-boot-3-spring-6-hibernate-for-beginners

## Prerequisites
- Install [SDKMAN](https://sdkman.io) https://github.com/ockertbotha/dev-env-mac/blob/main/README.md#14-install-sdkman

## Preparing the project area
From a terminal open in the project root:

```
sdk install java 17.0.12-zulu
sdk install maven 3.9.9    
```
```
sdk env init
```

## Now from a file editor:
#### Edit the new .sdkmanrc file:

```
# Enable auto-env through the sdkman_auto_env config
# Add key=value pairs of SDKs to use below
java=17.0.12-zulu
maven=3.9.9
```
#### Edit the .gitignore file and add:
```
# SDKMAN - Java Environment Manager
.sdkmanrc
```

## Back to the terminal
#### Switch to the Java environment
```
sdk env
java -version
mvn -v
```
Both Java and Maven versions should match the selected versions and Maven should be using the selected Java version. (This did not detect directly after Maven install but corrected itself while being investigated). Check all paths using: 
```
env
```

## Addressing Java Version Issue
Fix found: Curretnly Spring Initializr defaults to Java 22, updated the property to Java 17 in the `pom.xml`
```
	<properties>
		<java.version>17</java.version>
	</properties>
```

This did not fix the incompatibility issue but shows how to set Java Home and Runtimes specifically in VSCode, following lines should be added to ```.vscode/settings.json```
```
  "java.jdt.ls.java.home": "/Users/ockert/.sdkman/candidates/java/17.0.12-zulu",
  "java.compile.nullAnalysis.mode": "automatic",
  "java.configuration.runtimes": [
    {
      "name": "JavaSE-17",
      "path": "/Users/ockert/.sdkman/candidates/java/17.0.12-zulu",
      "default": true
    },
  ],
  ```
  