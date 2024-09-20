FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/car_rental_controller-2.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

