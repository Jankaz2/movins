# Movins

## Table of contents

* [General info](#general-info)
* [Technologies](#technologies)
* [Installation](#installation)
* [Pictures](#pictures)
***

## General info
The aim of this application is to help the user find his favourite cinema (if it exists in our database),
check what seances this cinema currently shows and book the ticket. All of these steps 
are really easy and fast to do with `Movins` web.  
If you are `admin` you are able to manage all of the `cinemas`, `users`, and  `seances`. 
You can create, delete or update new cinemas, add movies etc.

I decided to create this application to better understand the concept of commercial programs 
and get better at `Spring` framework.
***

***
## Technologies
Project is created with: 
- Java 16
- Spring Boot 2.4.5
  - Spring Boot Security
  - Spring Boot Data
  - Spring Boot Web
  - Spring Boot Thymeleaf
- MySQL
- Maven
- Docker 20.10.8
- Lombok


***
## Installation
If you want to use the `Movins` application locally, you need to create `web/docker-compose.yml`
and `web/src/main/resources/properties.yml` files to set the database and mail properties.  
Then you need to follow these few steps:
```aidl
//from main directory
>> mvn clean install
>> cd .\web\
>> docker-compose up -d --build
//logs if you want to be sure that the application is working correctly
>> docker-compose logs -f
```

***
## Pictures
Graphical representation is placed in `front-end repository` to this `project`.  
[movins-layout github](https://github.com/Jankaz2/movins-layout)