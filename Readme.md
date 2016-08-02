A Example application using drop wizard and mongo db

Requirements The requirements of this application are:

[1] IntelliJ IDEA

[2] Maven

[3] JUnit 4.11

[5] Mongo


Running the Application

Test

-> maven compile

-> maven test


Application

-> mvn exec:java -Dexec.mainClass="EmployeeService" (for running the app)

(i) For pushing data into the mongo DB

curl -i -X POST -H "Content-Type: application/json" -d '{"title":"something","name": "some name"}' http://localhost:8080/employees


(ii) Fetching it on the api

curl http://localhost:8080

