# Booking Management Backend Api Project


#Description  

You are requested to use a message broker (specifically RabbitMQ) to manage the events produced and
consumed by and from your application.
Moreover, it is requested that your application exposes a REST layer to manage booking operations
(CRUD), specifically:
* Get list of all bookings
* Get booking by id
* Add booking
* Update bookings
* Delete booking 
  
### Tech

Application uses a number of open source projects to work properly:

* Java 8- Primary Language
* Spring boot - An open source Java-based framework used to create a micro Service.
* Maven - Dependency Management
* H2 -Embedded Database
* Swagger - Documentation of API
* RabbitMQ - Open-source message-broker software 

### Pre Request  

Rabbit MQ 

 Download the image using the following docker command: 

 ```sh
  $ docker pull rabbitmq
 ``` 
 
 ```sh
 $ docker run -d --hostname ecabs-rabbit --name rabbit-assignment -e RABBITMQ_DEFAULT_VHOST=/
 -p 15672:15672 -p 5672:5672 rabbitmq:3-management
 
```

### Cloning Repository
```sh
$ git clone https://github.com/mehmetfatihustdag/booking-management.git
```

### Installation
After cloning or downloading the repository. Follow to steps to run the project.

You will need to be go to  folder where pom.xml located.
  ```sh
     $ cd booking-management
  ```
  
Steps to Run :
```sh
$ mvn spring-boot:run
```

Project will run under the http://localhost:8080 
Note : 8080 is configurable port it can be changed via application.yml file

### Swagger UI : 
  Application Endpoints documented via Swagger . You can find the swagger ui below link, and test endpoints via browser as well
  http://localhost:8080/swagger-ui.html

### Suggestion For Improvements :  
 - Write Unit and Integration Tests
 - I used H2 for demonstration purpose. Real ones can be used  
 - Development, test , and production profiles can be used. 
 - Writing Java Docs 
 
### Author 
   Fatih Ustdag
   fatihustdag@gmail.com
 



