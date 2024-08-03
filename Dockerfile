# Use a multi-stage build to minimize the final image size

# Stage 1: Build the application
FROM maven:3.9.8-eclipse-temurin-21-alpine as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build file and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Stage 2: Create the minimal runtime image
FROM eclipse-temurin:21-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]
