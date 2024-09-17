FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/task-app.jar task-app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "task-app.jar"]