# Spring Boot - Back-end application

This is the back-end component of the Budget Application. 


## Requirements

* Java Development Kit - JDK17
* Apache Maven


## Launching

### Option 1: (Execution through Maven)
1. Open a Terminal window on the location of this directory
2. Execute the following command:
```bash
mvn spring-boot:run
```
3. The application should now launch on localhost at port 8090

### Option 2: (JAR executable)
1. Open a Terminal window on the location of this directory
2. Execute the following command:
```bash
mvn clean compile package install
```
3. Open a new Terminal window on at /target
4. Execute the following command:
```bash
java -jar BudgetApplication.jar
```
5. The application should now launch on localhost at port 8090