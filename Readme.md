# Spring Boot Template

This is a template for a Spring Boot application. 

## Getting Started
You need Java 17 to run this application.

## Running the application
To run the application, execute the following commands:
docker-compose up -d //Do this in a separate terminal
./create-db.sh //This is a one time command to create the database
./gradlew bootRun //This runs the application on http://localhost:8080 on your local system

## Exploring the API
The API is documented using Swagger. You can access the Swagger UI at http://localhost:8080/swagger-ui.html

## Running the tests
To run the tests, execute the following command:
./gradlew test

## Creating a new api directory
E.g. to create a users api directory, execute the following command:
`create-resource.sh users`

## Creating a new entity
E.g. to create a User entity, execute the following command:
`create-entity.sh -c User -t users -r users`
Arguments are: -c for class name, -t for table name, -r for resource name
