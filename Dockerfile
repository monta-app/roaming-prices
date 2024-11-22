# Use Amazon Corretto as the base image for Java
ARG JAVA_VERSION=21
FROM amazoncorretto:${JAVA_VERSION}-alpine AS build

# Copy the project files to the Docker image
COPY . /home/src

# Set the working directory
WORKDIR /home/src

# Ensure logback.xml is handled correctly
RUN mv src/main/resources/logback.xml src/main/resources/logback-local.xml || echo "logback.xml not found"
RUN mv src/main/resources/logback-docker.xml src/main/resources/logback.xml || echo "logback-docker.xml not found"

# Build the application
RUN --mount=type=cache,target=/root/.gradle ./gradlew --no-daemon clean build

# Runtime stage
FROM amazoncorretto:${JAVA_VERSION}-alpine

# Set the working directory for the runtime container
WORKDIR /home/app

# Copy the built application to the runtime image
COPY --from=build /home/src/build/libs/*.jar /home/app/application.jar

# Expose the application port
EXPOSE 8080

# Define the entry point for the application
ENTRYPOINT ["java", "-server", "-XX:+Use", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseContainerSupport", "-XX:", "-jar", "/home/app/application.jar"]