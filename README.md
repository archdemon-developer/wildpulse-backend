<p align="center">
  <img width="300" height="300" src="./logo.svg">
</p>

> **Note**
> This Readme is a WIP and will be continuously updated as project grows.

# Wildpulse

This is a project which has been on my mind for sometime. It is a nature/biodiversity focussed social media platform which i am building to learn some concepts related to product engineering in general. This is the back-end. The front-end code can be found at: https://github.com/archdemon-developer/wildpulse-ui.

## Technologies Used

Technologies used in this project are:

- Kotlin
- Spring Boot
- Hibernate
- PostgreSQL
- JUnit
- Mockito
- Jacoco
  (Technologies used will be updated here as and when added).

## Installation

First, clone this project using git, and navigate into the project folder:

```bash
  git clone https://github.com/archdemon-developer/wildpulse-backend.git
  cd wildpulse-backend
```

Then, install the required dependencies

```bash
  ./gradlew clean build
```

That's it! Start the embedded tomcat server using

```bash
  ./gradlew bootRun
```

## Running Tests

This project uses Vitest for unit testing. To run the tests, run the following command:

```bash
  ./gradlew test
```

If you want to see your test coverage, then run:

```bash
  ./gradlew clean build jacocoTestReport
```

To format your code using the spotless plugin, run:

```bash
  ./gradlew spotlessApply
```

## Roadmap

**Things to keep in mind:**

- API Development with Spring boot and Rest API Standards.
- Modular code - Adhere to SOLID Principles, Design Patterns and OOP wherever possible.
- API Documentation with Swagger
- Clean coding practices
- Unit testing

**Essential Features:**

More will be added to this list as and when developed.

**Optional Features**

Will update this as i think of more usecases.

## Acknowledgements

- [Readme.so](https://readme.so/) - To help create this readme.
