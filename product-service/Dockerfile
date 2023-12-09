FROM openjdk:17-jdk
COPY ./build/libs/product-service.jar /app.jar
WORKDIR /
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app.jar"]