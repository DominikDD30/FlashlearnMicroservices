# FlashLearn Microservices

This repository contains the microservices version of the **FlashLearn Application**. The application has been refactored into several independent services, each focusing on specific features of the application. The microservices architecture allows for better scalability, modularity, and ease of maintenance.

## Services Overview

1. **DeepLService**
   - **Purpose**: Integrates with the DeepL API to provide language translation services.
   - **Technology**: Spring Boot
   - **Description**: This service handles translation requests, allowing other services to easily translate text into different languages.

2. **FlashcardService**
   - **Purpose**: Manages flashcards for the FlashLearn application.
   - **Technology**: Spring Boot
   - **Description**: Responsible for CRUD operations (Create, Read, Update, Delete) on flashcards. This service allows users to create, manage, and retrieve flashcards for studying purposes.

3. **GenerationService**
   - **Purpose**: Handles the content generation aspects of FlashLearn.
   - **Technology**: FastAPI (Python)
   - **Description**: This service utilizes the Gemmini Flash 1.5 model developed by Google. This model enhances the ability to intelligently generate sets for quizzes and flashcards from PDF files and plain text.

4. **PexelService**
   - **Purpose**: Connects to the Pexels API to fetch relevant images.
   - **Technology**: Spring Boot
   - **Description**: Retrieves images related to flashcards enriching the learning experience with visual content.

5. **QuizService**
   - **Purpose**: Manages quizzes within the FlashLearn application.
   - **Technology**: Spring Boot
   - **Description**: Handles CRUD operations on quizzes, allowing users to create, update, and retrieve quizzes.

6. **SecurityService**
   - **Purpose**: Manages authentication and authorization.
   - **Technology**: Spring Boot
   - **Description**: Ensures secure access to the FlashLearn services. This service handles user authentication, JWT token management to secure sensitive operations and data.

## Additional Files

- **api-gateway.yaml**: Configuration file for the API gateway, which routes external requests to the appropriate microservices. This file centralizes access to all services, allowing for easier load balancing and routing.
- **deploy-commands.txt**: Contains deployment commands and instructions, useful for setting up and deploying the services on a Kubernetes or Docker environment.

## Technology Stack

- **Java 17 & Spring Boot**: Most of the backend services are implemented in Java using Spring Boot, which provides a robust framework for building microservices.
- **Python & FastAPI**: The `GenerationService` leverages FastAPI due to its asynchronous capabilities and ease of use for machine learning or AI-focused tasks.
- **Docker**: Each service is containerized with Docker for easy deployment and scalability.
- **Kubernetes**: The application can be orchestrated on Kubernetes for production deployments, using the configuration files provided.

## Running the Application

1. **Build and Run Services**:
2. 1. Use the commands from deploy-commands.txt file to install service images.
   2. deploy services change path to deploy manifests in terminal then type kubectl apply -f <file>.yaml
   3. deploy ingress controller 
