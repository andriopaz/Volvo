# volvo-test
## Spring Boot + JSF Primefaces + Restful API + ActiveMQ

### Purpose
The purpose of this tutorial is to show how to combine Spring Boot with JSF and Primefaces in order to create a simple micro-service with an attractive front-end.

### Key Technologies
It covers:
* Java 8
* Spring Boot Autoconfiguration 2.0.0.RELEASE
* Spring Data JPA
* Spring Rest
* Spring Boot Test coverage
* Embedded Tomcat
* Spring Actuator
* JSF and Primefaces (CRUD)
* Jackson Repository Populators
* ActiveMQ
* Relationship between entities (User, Department, Permission)


Main Application:
It starts when you run Application.java as a Java Application.


GUI (Primefaces):
http://localhost:9090/


Restful API (GET):
http://localhost:9090/service/user/1
http://localhost:9090/service/department/1
http://localhost:9090/service/permission/1

http://localhost:9090/service/users
http://localhost:9090/service/departments
http://localhost:9090/service/permissions

http://localhost:9090/service/user/delete/1
http://localhost:9090/service/department/delete/1
http://localhost:9090/service/permission/delete/1

Restful API (POST):

You may save or update all entities calling save method.


Automated TESTS:
Validation of rest calls and queue structure.