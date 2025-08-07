FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/sf-paymentmanagement-0.0.1-SNAPSHOT.jar /app/sf-paymentmanagement.jar
EXPOSE 8095
ENTRYPOINT ["java", "-jar", "/app/sf-paymentmanagement.jar"]
