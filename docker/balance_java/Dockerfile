FROM openjdk:17-jdk-slim

WORKDIR /app

ARG JAR_FILE=/Users/ioedu/projects/prj_java21/target/poc_ddd-0.0.1.jar
COPY ${JAR_FILE} /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar]