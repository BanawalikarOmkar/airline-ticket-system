# airline-ticket-system
Project details

This project contains 3 parts 

1.	Ui code in folder ‘Fly with us’.
2.	Java code in folder ‘spring-rest-security-javaconfig’. 
3.	My Sql DB server.

1.	Front end
The Front end is developed using Angular 7. To install all the dependencies, install nodejs v12.0.0 after that use following cmd in the directory ‘Fly with us’.
npm install  
to start the app run the following command, this will run the angular server at http://localhost:4200 
ng serve
The front-end is expecting the backend to be running at http://localhost:8080/my-spring/home/

2.	Java code
The backend is developed using Java version "1.8.0_191", and Spring boot v2.1.1,
To install the app run following command in  ‘spring-rest-security-javaconfig’ folder
mvn clean install
this will generate artifact “spring-app.war” in target folder. Deploy this war file to tomcat 9 installation webapps folder. This will start the backend at url http://localhost:8080/my-spring

3.	Database
The database used in my app is MySQL v8.0.17. Run the file data.sql for initializing the DB. The backend is expecting the DB is running at url localhost:3306 

