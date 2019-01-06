# Voting system for deciding where to have lunch

Design and implement a JSON API using Hibernate/Spring/SpringMVC **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.




As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

P.S.: you can use a project seed you find where all technologies are already preconfigured.

P.P.S.: Make sure everything works with latest version that is on github :)

P.P.P.S.: Asume that your API will used by a frontend developer to build frontend on top of that.

#### Used technologies
- Java 11
- Maven
- Spring Security
- Spring 5
  * Spring Core (Beans, Context)
  * Spring Data JPA
  * Spring MVC
- Hibernate
- DBs: HSQLDB
- Spring Security Test / JUnit 5
- SLF4J & Logback
- Tomcat

#### Credentials

User:
 * login: user@yandex.ru
 * password: password
Admin:
 * login: admin@gmail.com
 * password: admin

P.S. all unauthorized access prohibited. Users have not access to admin commands

# Documentation

## <a href="https://documenter.getpostman.com/view/6266767/RznEJdFX"> Postman documantation</a>

# CURL Commands

---
## User:

- #### Get all restaurant's with today's menu's
  `curl -X GET http://localhost:8080/graduation/restaurants/dishes -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`
- #### Vote as user for restaurant with id 100003. After 11:00 you cannot vote
  `curl -X POST http://localhost:8080/graduation/votes/100003 -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

## Admin:

### dishes:

     curl -X GET http://localhost:8080/graduation/dishes/100005 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     curl -X PUT http://localhost:8080/graduation/dishes/100005 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H 'Content-Type: application/json' -d '{"name":"Updated Humburger","price":"120.12","restaurantId":100002}'
     curl -X POST http://localhost:8080/graduation/dishes/ -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H 'Content-Type: application/json' -d '{"name":"Double Hamburger","price":"105.00","restaurantId":100002}''
     curl -X DELETE http://localhost:8080/graduation/dishes/100005 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     curl -X GET http://localhost:8080/graduation/dishes/restaurants/100002 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     
### restaurants:
      curl -X GET http://localhost:8080/graduation/restaurants/100003 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
      curl -X POST http://localhost:8080/graduation/restaurants -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H 'Content-Type: application/json' -d '{"name":"Fridays","address":"Moscow, sha la la la street"}'
      curl -X GET http://localhost:8080/graduation/restaurants -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H 'Postman-Token: 8ac37ed2-2a10-49d8-9889-f721c380232c' -H 'cache-control: no-cache'
      curl -X GET http://localhost:8080/graduation/restaurants/100002/dishes -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
      curl -X GET http://localhost:8080/graduation/restaurants/dishes -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
      curl -X PUT http://localhost:8080/graduation/restaurants/100002 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H 'Content-Type: application/json' -d '{"id":100002,"name":"McDonalds New","address":"New Address"}'
      curl -X DELETE http://localhost:8080/graduation/restaurants/100002 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
      
### votes:
 
- #### Vote as admin for restaurant with id 100003. After 11:00 you cannot vote
  `curl -X POST http://localhost:8080/graduation/votes/100003 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
