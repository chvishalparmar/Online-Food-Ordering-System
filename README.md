# Online-Food-Ordering-System
The objective of this Project is to design and develop an Online Food Ordering System using Java and Spring Boot. The application should allow users to view menus, place orders.It uses Docker for containerization and Docker Compose to manage multi-container setups including a MySQL database.

##Prerequisites
  Docker: Install Docker
  Docker Compose: Install Docker Compose
  
##Project Structure
  Dockerfile: Contains instructions to build the Docker image for the Spring Boot application.
  docker-compose.yml: Defines and runs multi-container Docker applications, including the Spring Boot app and a MySQL 
  database.
  
##Build and Run the Application
  Step 1: Clone the Repository
          `git clone <repository-url>`
          `cd <repository-directory>`
  Step 2: Build the Docker Images
          Run the following command to build the Docker images for the application and the MySQL database: `docker-compose build`
  Step 3: Start the Application
          Start the application and database containers using Docker Compose:
          docker-compose up
          This will start two containers:

          ordersystem: The Spring Boot application.
          database: The MySQL database.
          You can check the status of the containers with:

bash
Copy code
docker-compose ps
Step 4: Access the Application
Spring Boot Application: Access the application at http://localhost:9090
Swagger UI: Access the API documentation at http://localhost:9090/swagger-ui.html
Step 5: Stop the Application
To stop the containers, run:

bash
Copy code
docker-compose down
This will stop and remove the containers, but it will not remove the volumes.

Troubleshooting
Container Not Starting: Ensure that the ports specified in docker-compose.yml are not in use by other applications.
Database Connection Issues: Check the logs of the ordersystem container to confirm it is correctly connecting to the database container.
Configuration
Spring Boot Environment Variables: Configuration for the Spring Boot application is defined in the docker-compose.yml file under the app service. Modify the environment variables as needed.

MySQL Configuration: Database credentials and settings are defined in the docker-compose.yml file under the db service. Adjust as necessary.

Additional Information
For more information on Docker and Docker Compose, refer to the official Docker documentation.


