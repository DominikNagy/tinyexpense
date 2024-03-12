FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/tinyexpense.jar /app/tinyexpense.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "tinyexpense.jar"]