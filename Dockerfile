FROM openjdk:26-ea-21-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
