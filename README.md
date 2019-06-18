# Project10 - Library SOAP information system (improved)

This is a SAOP architecture made in order to provide an information system to a city library. For more information please see documentation provided. 
 

## Getting Started

  #### DataBase
    *WARNING* Before starting, you have to modify the docker-compose.yml in the following directories : 

        . directoryOfYourProject/Project10/dockerdev/libraryWappTestDB/ - Change ports :  - "192.168.99.102:9032:5432" to - "127.0.0.1:9032:5432" or "yourDockerMachineIp:9032:5432".

        . directoryOfYourProject/Project10/dockerdev/libraryWSTestDB/ - Change ports :  - "192.168.99.102:9033:5432" to - "127.0.0.1:9033:5432" or "yourDockerMachineIp:9033:5432".

    1. In your cmd, go to the following directory : "directoryOfYourProject/Project10/docker/dev/libraryWappTestDB/" 
    2. run docker-compose (docker-compose up -d)

    Your database is now ready to go. 

    3. In your cmd, go to the following directory : "directoryOfYourProject/Project10/docker/dev/libraryWSTestDB/
    4. run docker-compose (docker-compose up -d)

    Your integration tests database is now ready to go.



  #### WebService
    1. Into "directoryOfYourProject/Library/libraryWS/libraryWS-exposure/src/main/resources/propertiesDataBaseConf.properties" set url, username, and password of your database. Configure, also, the email service in the same file.   

    2. In your cmd, go to the following directory : "directoryOfYourProject/Project10/Library/libraryWS/ and run a mvn clean install

    3. Deploy the war in a docker container with the following command in your cmd : docker run -it --name libraryws -v (add second "/" if using docker tool box)/directoryOfYourProject/Project10/Library/libraryWS/libraryWS-exposure/target:/usr/local/tomcat/webapps/ -p (yourLocalhostIp or yourDockerMachineIp):8080:8080 tomcat:9-jre8-alpine

    Your webservice is now ready to go.

    You can access the WSDL of the Webservice via : YourServerAddress(orYourDockerMachineIp):8080/libraryWS-webservice/libraryService 
    (ex : http://localhost:8080/libraryWS-webservice/libraryService)


  #### WebApp
    IMPORTANT: 
    Make sure your Webservice app is ran always first by deploying its Docker container first.  
    To mvn install, the Webapp will need the Webservice to be operational (as it consumes the Webservice -> read wsdl while compiling).

    1. In your cmd, go to the following directory : "directoryOfYourProject/Project10/Library/libraryWebapp/ and run a mvn clean install 

    2. Deploy the war in a docker container with the following command in your cmd : docker run -it --name librarywebapp -v (add second "/" if using docker tool box)/directoryOfYourProject/Project10/Library/libraryWebapp/libraryWebapp-webapp/target:/usr/local/tomcat/webapps/ -p (yourLocalhostIp or YourDockerMachineIp):8081:8080 tomcat:9-jre8-alpine

    Your webapp is now ready to go.
    
    You can now run the webapp via the following url : YourServerAddress(orYourDockerMachineIp):8081/libraryWebapp-webapp/

    (ex : http://localhost:8081/libraryWebapp-webapp/)

  #### libraryBatch 
    1. To run the batch, open a command prompt.
    2. Go to your ".jar" file directory.
    3. Type the following command : java -jar libraryBatch.jar .

    Your batch is now running 



## Prerequisites

Install Java JRE version 8 or higher. (https://www.oracle.com/technetwork/java/javase/downloads/index.html).

Install the latest version of Maven (for more information : https://maven.apache.org/).

Install Docker (https://www.docker.com/). 

## Built With 

* [Eclipse](https://www.eclipse.org/documentation/).

## Authors

* **Philippe Plaxine** - *Initial work* - [PPlaxine](https://github.com/pplaxine)
