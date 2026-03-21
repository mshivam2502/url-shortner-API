FROM ubuntu:latest
LABEL authors="HP"

ENTRYPOINT ["top", "-b"]

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]