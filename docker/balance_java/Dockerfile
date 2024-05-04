FROM openjdk:21-ea-30-slim

WORKDIR /app

ARG JAR_FILE=./poc_ddd-0.0.1.jar
COPY ${JAR_FILE} /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]