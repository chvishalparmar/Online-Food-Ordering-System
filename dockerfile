# Use a base image with Java installed
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/ordersystem-0.0.1-SNAPSHOT.jar /app/ordersystem-0.0.1-SNAPSHOT.jar

# Specify the command to run your application
ENTRYPOINT ["java", "-jar", "/app/ordersystem-0.0.1-SNAPSHOT.jar"]