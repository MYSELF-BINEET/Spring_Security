# Spring Security Microservices Project

## Overview
This project demonstrates the integration of **Spring Security** within a microservices architecture. It leverages Spring Security as a component, used as a bean to manage authentication and authorization efficiently across multiple services.

## Features
- **User Authentication:** Secure login and registration with JWT tokens.
- **Role-Based Authorization:** Grant specific permissions based on user roles.
- **Microservices Security:** Protect endpoints and ensure secure communication.
- **Custom Security Configurations:** Fine-tuned security settings for different services.
- **OAuth2 Support:** Integration with third-party authentication providers.
- **Actuator Monitoring:** Secure endpoints to monitor the health of services.

## Technologies Used
- **Spring Boot** (Microservices Architecture)
- **Spring Security** (Authentication & Authorization)
- **Spring Cloud** (Microservices Orchestration)
- **Spring Data JPA** (Persistence Layer)
- **PostgreSQL/MySQL** (Database)
- **JWT (JSON Web Token)** (Token-based authentication)
- **Docker** (Containerization)

## Project Structure
```
Spring_Security_Project/
│-- config-server/         # Centralized configuration service
│-- api-gateway/           # Gateway for routing & security
│-- user-service/          # Manages user authentication and profiles
│-- common-security/       # Shared security configurations
│-- README.md              # Project documentation
```

## Setup Instructions

### Prerequisites
Ensure you have the following installed:
- JDK 17+
- Maven
- Docker
- PostgreSQL/MySQL

### Installation
1. Clone the repository:
   ```bash
   https://github.com/MYSELF-BINEET/Spring_Security.git
   cd Spring_Security_Project
   ```
2. Configure the database settings in `application.properties` or `application.yml`.
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the microservices using Docker Compose:
   ```bash
   docker-compose up --build
   ```

## Running the Application
You can start each microservice individually:
```bash
cd discovery-server && mvn spring-boot:run
cd config-server && mvn spring-boot:run
cd api-gateway && mvn spring-boot:run
cd user-service && mvn spring-boot:run
```

## API Endpoints
### Authentication
- `POST /api/auth/register` - Register new users
- `POST /api/auth/login` - Authenticate and receive a JWT

### Secured Endpoints (Require Authorization)
- `GET /api/v1/user` - Get user profile
- `GET /api/v1/admin` - Get admin profile

## Security Configuration
Spring Security is configured via:
- **JWT Authentication:** Token-based authentication mechanism
- **Method-Level Security:** `@PreAuthorize` for securing specific methods
- **CORS and CSRF Protection:** Configured to allow specific origins

## Testing
Run unit and integration tests using:
```bash
mvn test
```

## Deployment
To deploy the project using Docker:
```bash
docker build -t spring-security-microservices .
docker run -p 8080:8080 spring-security-microservices
```

## Contribution Guidelines
1. Fork the repository.
2. Create a feature branch.
3. Commit your changes with proper messages.
4. Push and create a pull request.

## License
This project is licensed under the MIT License.

## Contact
For inquiries, please contact [bineetpradhan03@gmail.com](mailto:bineetpradhan03@gmail.com)

