# Use a base image with Java installed
FROM adoptopenjdk/openjdk11:alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/ordersystem-0.0.1-SNAPSHOT /app/ordersystem-0.0.1-SNAPSHOT

# Specify the command to run your application
CMD ["java", "-jar", "ordersystem-0.0.1-SNAPSHOT"]