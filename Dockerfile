FROM maven:3.8.5-openjdk-17 AS build

CMD ["mkdir", "app"]
WORKDIR app/

EXPOSE 8080

ARG RESOURCES_FOLDER=src/main/resources
ARG JAR_FILE=target/plant-0.2-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ADD ${RESOURCES_FOLDER}/import.sql import.sql
ADD ${RESOURCES_FOLDER}/application.properties application.properties
ADD ${RESOURCES_FOLDER}/application-prod.properties application-prod.properties
ADD ${RESOURCES_FOLDER}/application-test.properties application-test.properties

ENTRYPOINT ["java", "-jar", "app.jar"]