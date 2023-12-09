FROM openjdk:17-jdk
COPY authentication-service/build/libs/authentication-service.jar /app.jar
WORKDIR /
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]