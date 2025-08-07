FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/Auth-Service-0.0.1-SNAPSHOT.jar /app/sf-auth.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "/app/sf-auth.jar"]
