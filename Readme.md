# VUTTR Tools API

## Overview

The VUTTR Tools API is a RESTful API built with Spring Boot that provides a simple way to manage tools. The API allows users to create, read, update, and delete tools, as well as filter tools by tag.

## Getting Started

### Building the Application

To build the application, run the following command:

mvn clean package

```java
Running the Application

```

To run the application, use the following command:

```java
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

Alternatively, you can use Docker to run the application. To do so, build the Docker image by running:

```docker
docker build -t vuttr-tools-api .
```

Then, run the Docker container using:

```docker
docker run -p 8080:8080 vuttr-tools-api
```

## Accessing the API

The API is accessible at <http://localhost:8080>. You can use a tool like Postman or cURL to interact with the API.

### Swagger Documentation

To access the Swagger documentation, visit <http://localhost:8080/swagger-ui/index.html#/>. This will provide you with a user-friendly interface to explore the API endpoints and test them out.
License

This project is licensed under the Apache License 2.0.

## Contributing

Contributions are welcome! If you'd like to contribute to the project, please fork the repository and submit a pull request.

I hope this helps! Let me know if you need any further assistance.
