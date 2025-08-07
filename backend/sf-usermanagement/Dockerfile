FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/sf-usermanagement-0.0.1-SNAPSHOT.jar /app/sf-usermanagement.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/sf-usermanagement.jar"]
