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

# CURL Commands

---
## User:

- #### Get all restaurant's with today's menu's
  `curl -X GET http://localhost:8080/graduation/restaurants/dishes -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`
- #### Vote as user for restaurant with id 100003. After 11:00 you cannot vote
  `curl -X POST http://localhost:8080/graduation/votes/100003 -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

## Admin:

### dishes:
CURL:

     curl 'http://localhost:8080/api/users' -i -H'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     curl 'http://localhost:8080/api/users/0' -i -H'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     curl 'http://localhost:8080/api/users/search/by-email?email=admin@gmail.com' -i -H'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     curl 'http://localhost:8080/api/users' -i -d'{"name" : "NewUser", "email" : "new@mail.ru","password" : "123456","roles" : ["ROLE_USER"]}' -H'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H'Content-Type: application/json'
