FROM openjdk:17-jdk
COPY ./build/libs/api-gateway.jar /app.jar
WORKDIR /
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]