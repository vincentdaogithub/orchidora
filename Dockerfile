FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=build /app/target/orchidora-0.0.1.jar ./orchidora.jar
EXPOSE 8080
CMD ["java", "-jar", "orchidora.jar"]