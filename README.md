spring-sample
=============

This is is a spring small application which is used to create some users and store them to a postgres database.
It is a maven multi-module project made of a simple dao and web.
It has a basic configuration for spring security.
What you need to run it:
- java 8, and JAVA_HOME var set to point to to the install directory
- tomcat 8

Just create the war by running
mvn clean install 
Then start tomcat and drop the war in the webapps directory.
The application can be accessed at http://localhost:8080/sample
