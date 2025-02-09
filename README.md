# Video Status API

A Quarkus-based system for processing video status updates via AWS SQS, syncing with a database, and exposing REST APIs. Built with Hexagonal Architecture for modularity and testability.

## Features

- SQS Integration: Asynchronous processing of video status updates via AWS SQS queues
- Status Workflow: Track video states (SOLICITADO, CARREGADO, PROCESSANDO, CONCLUIDO, FALHA)
- REST API: CRUD operations for videos and manual triggers for SQS processing
- Scheduled Polling: Automatic SQS message consumption every 30 seconds
- Database Sync: PostgreSQL integration for video state persistence
- Error Handling: Custom exceptions with HTTP status mapping

## Project Structure

### Source Code

```plaintext
src/main/java/org/jfm/
├── bootloader/           # CDI configuration (SQS client, services)
├── controller/           # REST endpoints + exception mapping
├── domain/               # Core business logic
│   ├── entities/         # Video model, Status enum
│   ├── exceptions/       # Custom exceptions
│   ├── ports/            # Repository interfaces
│   ├── usecases/         # Usecases implementations   
│   └── services/         # Business services
└── infrastructure/       # External implementations
    └── repository/       # SQL database adapter
```

## Dependencies

- Framework: Quarkus
- AWS: SDK for SQS
- Database: Hibernate ORM + PostgreSQL
- Testing: JUnit 5, Mockito
- Utilities: MapStruct (DTO mapping)

## Configuration

Edit `src/main/resources/application.properties`:

```properties
# AWS SQS  
SQS.AWS.RECEBER=https://sqs.us-east-1.amazonaws.com/your-queue/receber  
SQS.AWS.ENVIAR=https://sqs.us-east-1.amazonaws.com/your-queue/enviar  
aws.region=us-east-1  

# Database  
quarkus.datasource.db-kind=postgresql  
quarkus.datasource.username=postgres  
quarkus.datasource.password=secret  
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/videos  
quarkus.hibernate-orm.database.generation=update  
```

## Build & Run

### Development Mode

```sh
./mvnw quarkus:dev  
```

### Package as JAR

```sh
./mvnw package  
# Run: java -jar target/quarkus-app/quarkus-run.jar  
```

### Native Build

```sh
./mvnw package -Dnative  
# Run: ./target/video-shield-1.0.0-SNAPSHOT-runner  
```

## Testing
- Unity and BDD tests.

### Run Unit Tests

```sh
./mvnw test  
```

## API Endpoints

| Endpoint          | Method | Description                       |
|-------------------|--------|-----------------------------------|
| /videos           | GET    | List all videos                   |
| /videos/{id}      | GET    | Get video by ID                   |
| /videos/email/{email} | GET | Get videos by email               |

## Flow

### Scheduler

Runs every 30s (SqsScheduler)

### SQS Consumption

- Receives messages from `SQS.AWS.RECEBER` queue
- Parses message format: `UUID.STATUS[.optional_data]`

### State Management

- Creates/updates Video entities in database
- On failure status: sends notifications via `SQS.AWS.ENVIAR`
- Message Cleanup: Deletes processed SQS messages

## Contributing

<<<<<<< HEAD
1. Fork the repository
2. Create a branch: `git checkout -b feat/new-feature`
3. Commit changes with tests: `git commit -m 'feat: Add video validation'`
4. Push: `git push origin feat/new-feature`
5. Open a pull request

## License

MIT License - See LICENSE

## FIAP - Software Architecture Postgraduate Project

Developed as part of the Software Engineering Architecture specialization at FIAP.

This structure emphasizes the hexagonal architecture while showcasing the video processing workflow. Adjust queue URLs and database credentials as needed for your environment.
=======
[Related guide section...](https://quarkus.io/guides/smallrye-health)

.
>>>>>>> d8e81fefa13f814349e888a58f8d260d518d5419
