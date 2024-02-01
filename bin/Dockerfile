
FROM eclipse-temurin:11-jdk
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app 
COPY --from=stage1 /usr/src/app/target/*.jar app.jar
EXPOSE 8080
CMD ["-java","-jar","app.jar"]