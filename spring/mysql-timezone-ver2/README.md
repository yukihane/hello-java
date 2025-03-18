# Spring Boot and MySQL Docker Project

This project demonstrates how to set up a Spring Boot application with a MySQL database using Docker Compose. It includes the necessary configurations and files to run both services in isolated containers.

## Project Structure

```
spring-mysql-docker
├── docker-compose.yml        # Defines the services for MySQL and Spring Boot
├── mysql                     # Directory containing MySQL setup files
│   ├── Dockerfile            # Dockerfile for MySQL container
│   ├── init.sql              # SQL script to initialize the database
│   └── my.cnf                # MySQL configuration file
├── spring-app                # Directory containing the Spring Boot application
│   ├── Dockerfile            # Dockerfile for Spring Boot container
│   ├── mvnw                  # Maven wrapper script (Unix)
│   ├── mvnw.cmd              # Maven wrapper script (Windows)
│   ├── pom.xml               # Maven configuration file
│   └── src                   # Source code for the Spring Boot application
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── demo
│       │   │               ├── DemoApplication.java
│       │   │               ├── controller
│       │   │               │   └── AppController.java
│       │   │               ├── model
│       │   │               │   └── User.java
│       │   │               └── repository
│       │   │                   └── UserRepository.java
│       │   └── resources
│       │       └── application.properties
│       └── test
│           └── java
│               └── com
│                   └── example
│                       └── demo
│                           └── DemoApplicationTests.java
└── README.md                 # Project documentation
```

## Getting Started

### Prerequisites

- Docker
- Docker Compose

### Setup

1. Clone the repository:
   ```
   git clone <repository-url>
   cd spring-mysql-docker
   ```

2. Build and run the containers:
   ```
   docker-compose up --build
   ```

3. Access the Spring Boot application at `http://localhost:8080`.

### MySQL Initialization

The MySQL container is initialized with a user, database, and tables defined in the `mysql/init.sql` file. You can modify this file to change the initial setup.

### Stopping the Application

To stop the application, run:
```
docker-compose down
```

## License

This project is licensed under the MIT License.