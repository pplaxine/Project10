# Project10 - Library SOAP information system (improved)

This is a SAOP architecture made in order to provide an information system to a city library. For more information please see documentation provided. 
 

## Getting Started

  #### DataBase
    1. Start PGAdmin 4 and select the database you have created for the purpose. 
    2. Via the query tool, browse the following database creation script : Project7\LibraryGit\Documentation\MPD\03_LIBRARY_BDD_SETUP - V3.sql . 
    3. Execute the script. 
    
    Your database is now ready to go. 

  #### WebService
    1. Into "libraryWS-webservice.war\WEB-INF\classes\properties\DataBaseConf.properties" set url, username, and password of your database. Configure, also, the email service in the same file.
    2. Start your first tomcat serveur. 
    3. To access Tomcat Manager App, go to your server address ( ex:http://localhost:8080/ ) and select Manager App. 
    4. In section WAR file to deploy, browse the libraryWS-webservice.war you have downloaded, and select deploy.
    
    IMPORTANT: 
    Make sure your Webservice app is ran always first by, if possible, configuring the starting order or deploy on a separated server. 
    If a Webservice Client is ran first, this will end up in a waiting catch 22 situation. Client will be waiting for the Webservice to be operational (as it consumes the Webservice), and WebService will be waiting to run after Client. 

    Your webservice is now ready to go.

    You can access the WSDL of the Webservice via : YourServerAddress/libraryWS-webservice/libraryService 
    (ex : http://localhost:8080/libraryWS-webservice/libraryService)


  #### WebApp
    1. Into "libraryWebapp-webapp.war\WEB-INF\classes\webserviceConfig.properties" your can configure the wsld address of the different services used by the app. 
    2. Start your second tomcat serveur. 
    3. To access Tomcat Manager App, go to your server address ( ex:http://localhost:8081/ ) and select Manager App. 
    4. In section WAR file to deploy, browse the libraryWebapp-webapp.war you have downloaded, and select deploy.

    Your webapp is now ready to go.
    
    You can now run the webapp via the following url : YourServerAddress/libraryWebapp-webapp/

    (ex : http://localhost:8081/libraryWebapp-webapp/)

  #### libraryBatch
    1. To run the batch, open a command prompt.
    2. Go to your ".jar" file directory.
    3. Type the following command : java -jar libraryBatch.jar .

    Your batch is now running 



## Prerequisites

Install Java JRE version 8 or higher.

Install PGAdmin4 and create, for the webapp, a corresponding database (for more information : https://www.pgadmin.org/). 

Install the latest version of Tomcat and set configuration to unlock access to the Manager app(for more information : https://tomcat.apache.org/tomcat-9.0-doc/manager-howto.html). 

## Built With

* [Eclipse](https://www.eclipse.org/documentation/)

## Authors

* **Philippe Plaxine** - *Initial work* - [PPlaxine](https://github.com/pplaxine)
