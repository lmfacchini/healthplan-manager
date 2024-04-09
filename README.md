# Health Plan Beneficiaries Registration Using Spring Boot 3

This is a project developed with Spring Boot to provide a REST API for managing the registration of health plan beneficiaries.

## Technologies Used

- Spring Boot 3.2.4
- Spring Data JPA
- OpenAPI (Swagger) for REST documentation
- Lombok
- H2 Database (in-memory)
- Maven 3.9.3

## Prerequisites

- Java 17
- Maven 3.9.3

## Instructions for Running

1. Clone this repository to your local machine:


2. Navigate to the project directory:


3. Open the `application.yml` file and configure your OpenAI API Key:
    ```yaml
    spring:
      ai:
        openai:
          apiKey: "your-openai-api-key-here"
    
It can be created at the URL: https://platform.openai.com/api-keys




4. Execute the following command to compile the project:

    ```shell
     mvn clean package

5. Execute the following command to start the application:

    ```shell
     mvn spring-boot:run

6. After successfully starting, the application will be available at `http://localhost:8080`.

## API Documentation

The API documentation is automatically generated using OpenAPI (Swagger) and can be accessed at `http://localhost:8080/swagger-ui.html`.

## Contact

For more information, please contact us via email: lmfacchini@gmail.com

