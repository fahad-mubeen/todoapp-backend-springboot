FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Make gradlew executable
RUN chmod +x ./gradlew

# Download dependencies (cached if build.gradle doesn't change)
RUN ./gradlew dependencies --no-daemon

# Copy source code
COPY src ./src

# Build the application
RUN ./gradlew clean build -x test --no-daemon

# Production stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]