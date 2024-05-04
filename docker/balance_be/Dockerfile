FROM openjdk:21-ea-30-slim

WORKDIR /app

#ARG JAR_FILE="../../target/app_balance--0.0.1.jar"
#COPY ${JAR_FILE} /app/app.jar
COPY app_balance-0.0.1.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]