FROM openjdk:11
EXPOSE 8087
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]