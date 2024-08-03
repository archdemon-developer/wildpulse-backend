<p align="center">
  <img width="300" height="300" src="logo.svg">
</p>

> **Note**
> This Readme is a WIP and will be continuously updated as project grows.

# Wildpulse

This is a project which has been on my mind for sometime. It is a nature/biodiversity focussed social media platform which i am building to learn some concepts related to product engineering in general. This is the backend. The front-end code is available here: <https://github.com/archdemon-developer/wildpulse-ui>

## Technologies Used

Technologies used in this project are:

- Java
- Spring Boot
- JUnit
- PostgreSQL
- Mockito
- Hibernate
- Swagger
- Jacoco
- Spotless
  (Technologies used will be updated here as and when added).

## Installation

First, clone this project using git, and navigate into the project folder:

```bash
  git clone https://github.com/archdemon-developer/wildpulse-backend.git
  cd wildpulse-backend
```

Then, install the required dependencies

```bash
  ./mvnw clean install
```

That's it! Start the server using

```bash
  ./mvnw spring-boot:run
```

Build the project using

```bash
  ./mvnw clean build
```

## Running Tests

This project uses Vitest for unit testing. To run the tests, run the following command:

```bash
  ./mvnw clean test
```

If you want to see your test coverage(this project uses Jacoco), then run:

```bash
  ./mvnw clean verify
```

## Formatting

For formatting the code (the project uses spotless), run:

```bash
  ./mvnw spotless:apply
```

## Roadmap

**Things to keep in mind:**

- Modular code - Adhere to OOPS and SOLID Principles whenever possible.
- Clean coding practices
- Unit testing

**Essential Features:**

- Login/Signup User

**Optional Features**

## Acknowledgements

- [Readme.so](https://readme.so/) - To help create this readme.