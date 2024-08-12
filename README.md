# PlantPoppa Backend
## Description
This is the backend **Spring Boot** server for the PlantPoppa Application. The user interface can be found [here](https://github.com/Aaron-Heath/plantpoppa-ui). This application enables authentication and authorization as well as CRUD functions on user data.

## Key Features
* **Authentication:** Handles username/password authentication requests. Issues Bearer Tokens upon successful authentication.
* **Authorization:** Parses Bearer Tokens to ensure users can only access their own data.
* **Secure Password Storage:** Employs password encryption for storage on the database..
* **User Data Management:** Provides CRUD (Create, Read, Update, Delete) functionality for managing user data.
* **Plant Data Management:** Provides CRUD functionality for users' plant data. 

**This service has the following base endpoints:**
* `/api/auth` - Authentication and user creation
* `/api/user` - CRUD functions for user data
* `/api/plant` - CRUD functions for plant data
* `/api/user-plant` - CRUD functions for userPlants


## Technology Stack
<div>
    <img src="https://raw.githubusercontent.com/devicons/devicon/55609aa5bd817ff167afce0d965585c92040787a/icons/java/java-original-wordmark.svg" width="50" height="50" alt="Java" title="Java"/>
    <img  src="https://raw.githubusercontent.com/devicons/devicon/55609aa5bd817ff167afce0d965585c92040787a/icons/spring/spring-original-wordmark.svg" height="50" width="50" alt="Spring" title="Spring"/>
    <img src="https://raw.githubusercontent.com/devicons/devicon/6910f0503efdd315c8f9b858234310c06e04d9c0/icons/postgresql/postgresql-original-wordmark.svg" height="50" width="50" alt="PostgreSQL icon" title="PostgreSQL"/>
</div>

This application runs on the Spring Framework using Java. A previous implementation, built with Dropwizard can be found [here](https://github.com/Aaron-Heath/plantpoppa-auth/tree/dropwizard). The front end of the application is under development using ReactJS and can be found [here](https://github.com/Aaron-Heath/plantpoppa-ui)

## Usage
Go to https://plantpoppa.com and register! **Note:** The UI is still under development, but is made available for those interested in seeing the progress.

This repository can be cloned and run locally. `git clone` this repository and run `mvn clean install` to build the jar. You can stand up a server immediately using an in-memory database by running the server with the following environment variable:
*   `SPRING_ACTIVE_PROFILES=test`

To run this with a persistent database, install Postgresql and configure an application-dev.properties file with the following values set based on your local PostgreSQL information:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/<YOUR_DATABASE_NAME>
spring.datasource.username=<YOUR_USERNAME> # default is postgres
spring.datasource.password=<YOUR_PASSWORD>
spring.jpa.show-sql=true # set to true to see generated sql in the console
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update # set to update to generate missing parts of the schema on startup
logging.level.org.springframework.security=DEBUG # choose level of logging for spring security
server.port=8080 # default port / adjust if needed
```