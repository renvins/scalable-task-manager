FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/user-app.jar user-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-app.jar"]