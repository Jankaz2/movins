# Movins

### Backend server is turned off now because I am poor student (AWS is so expensive :smiley:) so the only thing you will see by clicking the link below is frontend
Link to website: http://movins.com.s3-website.eu-central-1.amazonaws.com

## Table of contents 📓

* [General info](#general-info-information_source)
* [Usecases](#usecases-briefcase)
* [Technologies](#technologies-computer)
* [Installation](#installation-hammer)
* [Pictures](#pictures-framed_picture)
***

## General info :information_source:
The aim of this application is to help the user find his favourite cinema (if it exists in our database),
check what seances this cinema currently shows and book the ticket. All of these steps 
are really easy and fast to do with `Movins` web.  
Movins is application that helped me to better understand the concept of commercial programs and get better at `Spring` framework.
When I was writing this website I tried to use all of the best programming rules like `DDD` or `SOLID`.

Frontend for this application is built in `React` library - more details here: [movins-layout](https://github.com/Jankaz2/movins-layout/blob/master/README.md)

***

## Usecases :briefcase:
As an **user** you are able to:
 - reviewing cinemas list, their cinema rooms and seances,
 - order ticket,
 - access to history of tickets that you bought.

As an **admin** you are able to:
 - do whatever the normal user can
 - adding new cinemas,
 - reviewing users list,
 - cinema modification,
 - creating new admin account,
 - adding cinema room to existing cinema.
 - delete cinema or user

***
## Technologies :computer:
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
- AWS EC2


***
## Installation :hammer:
If you want to use the `Movins` application locally, you need to create `web/docker-compose.yml`
and `web/src/main/resources/properties.yml` files to set the database and mail properties.  
Then you need to follow these few steps:
```aidl
//from main directory
$ mvn clean install
$ cd .\web\
$ docker-compose up -d --build
//logs if you want to be sure that the application is working correctly
$ docker-compose logs -f
```

***
## Pictures :framed_picture:
Graphical representation for this project is placed in `movins-layout repository`.  
[movins-layout](https://github.com/Jankaz2/movins-layout)
