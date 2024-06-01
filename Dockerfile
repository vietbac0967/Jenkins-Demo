# Stage 1: Build the application
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

# Stage 2: Run the application
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/Jenkins-Demo-0.0.1-SNAPSHOT.jar app.jar 
ENTRYPOINT [ "java", "-jar" , "app.jar" ]