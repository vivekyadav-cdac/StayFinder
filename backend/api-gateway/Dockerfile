FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/apigateway-0.0.1-SNAPSHOT.jar /app/apigateway.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/app/apigateway.jar"]
