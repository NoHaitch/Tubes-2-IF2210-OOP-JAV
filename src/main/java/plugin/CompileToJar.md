# How to compile plugin.java to .jar

### 1. Compile the project
> For ease of generating the .jar file we will compile the project using maven.
> This will automatically download the dependencies and compile the class for each file.
```shell
mvn clean package
mvn compile
```

### 2. Create the jar file using the compiled class
> Note: Make sure you are in the root directory of the project.
```shell
jar cvf JSONDataLoader.jar .\target\classes\plugin\JSONDataLoader.class
```