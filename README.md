# Task Manage
## Java code challenge for Solnet

__Author:__ Przemysław Rek

__mail:__ przemyslawrek@gmail.com

## Overview

This application is a production grade API created with spring boot as a code challenge in Solnet's recruitment process. It's using embedded Derby DB to store Tasks data. Endpoints description is provided by swagger-ui (see __Documentation__)

## Pre-requisites
1. Java needs to be installed on the system and environment variable JAVA_HOME should be set correctly to the JDK path.  
   Check by running below command in command prompt  
   `java -version`
2. Maven needs to be installed on the system.  
   Check by running below command in command prompt  
   `mvn -v`

## Run the application
This project uses tomcat as an embedded container to host the web application.  
Go to the base folder of the application and execute the following command to launch the application.  
`mvn spring-boot:run`

The application will be available at [http://localhost:8080](http://localhost:8080)

## Run tests
To run example tests go to the base folder of the application and execute the following command to launch the application.  
`mvn test`

Because of simple nature of the application test has been written more in an integration tests manner testing functionalities as a whole.

Another way of testing the application is running it and  accessing swagger-ui to perform requests. See more in __Documentation__ fragment below.

__DISCLAIMER:__ Keep in mind that application cannot be running during the tests start, as it will try to run it\'s own instance of Derby DB

## Documentation
Application uses swagger-ui as a documentation. It will contain description of all endpoints with parameters. Swagger-ui will as well allow to run requests to the application for testing purpose.

If application is running visit http://server:port/context-path/swagger-ui/index.html

(Default) [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Implementation notes
### Doubts
- It has been not specified if creation_date should represent being submitted to the system and if so do user should be able to overwrite it. (assumed creation date can come from user but cannot be from future)
- Should empty title by valid (assumed it shouldn't)

### Possible improvements
- introducing DTOs
- modify DB structure, ex. turn status varchar(10) to statusId with predefined statuses in another table
- add security (auth, https)
- add service layer if any logic would be applied
- add docker file