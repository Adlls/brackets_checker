FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]