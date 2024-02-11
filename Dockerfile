# Use the official OpenJDK base image
FROM openjdk:8-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/spring-api-0.0.1-SNAPSHOT.jar /app/application.jar

# Expose the port that your application will run on
EXPOSE 80

# Run the application
CMD ["java", "-jar", "your-application.jar"]