FROM eclipse-temurin:11-jdk 
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app 
COPY /target/parking-control-0.0.1-SNAPSHOT.jar  app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]