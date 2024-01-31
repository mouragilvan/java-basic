FROM maven:3.8.7-openjdk-18-slim 

COPY ../src /app/src
COPY ../pom.xml /app

RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip 

FROM openjdk:18-jdk-alpine3.15

EXPOSE 8080

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT [ "java","-jar","/app.jar" ]