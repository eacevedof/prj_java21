FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/poc_ddd-0.0.1.jar
COPY ${JAR_FILE} poc_ddd.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "poc_ddd.jar"]