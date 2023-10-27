# Use the official OpenJDK base image
FROM openjdk:8-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/your-application.jar /app/your-application.jar

# Expose the port that your application will run on
EXPOSE 80

# Run the application
CMD ["java", "-jar", "your-application.jar"]