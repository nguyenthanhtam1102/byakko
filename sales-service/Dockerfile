FROM openjdk:17-jdk
COPY build/libs/sales-service.jar /app.jar
WORKDIR /
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app.jar"]