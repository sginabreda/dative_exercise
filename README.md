## Dative Exercise

Implementation for the first point in the exercise.

Index
1) [Project structure](#project-structure)
2) [How to run](#how-to-run)

# Project structure

The project follows a DDD (Domain Driven Design) structure, divided into the following three layers:
- **Application**: contains objects related to the web framework, such as Beans, Exception Handler, Rest Controller and DTOs.
- **Domain**: contains our domain classes and the business logic (use cases). Framework agnostic.
- **Infrastructure**: contains classes and logic related to the persistence of our domain entities.

The project is written in Java and uses Spring Boot as the underlying web framework, with H2 as the database.
It also uses Flyway to manage database migrations.

# How to run

You'll first need to install Java 17.

### Running the service with an IDE (i.e. IntelliJ IDEA)

1) Clone the service to your computer using the command below

``git clone git@github.com:sginabreda/dative_exercise.git``

2) Import the project into the IDE.

3) Execute the main class `com.exercise.dative.DativeApplication.java` to start the service.

### Running the service without an IDE

1) Clone the service to your computer using the command below

``git clone git@github.com:sginabreda/dative_exercise.git``

2) Go inside the folder containing the project

``cd dative_exercise``

3) Run the following command to start the application

``./gradlew bootRun`` in Linux

``gradlew.bat bootRun`` in Windows