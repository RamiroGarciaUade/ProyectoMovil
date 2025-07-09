# Stage 1: Build con Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos pom.xml y la carpeta src
COPY pom.xml .
COPY src ./src

# Compilamos y generamos el JAR (sin tests para agilizar)
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiamos el JAR compilado desde el stage “build”
COPY --from=build /app/target/*.jar app.jar

# Exponé el puerto (ajusta si tu app usa otro)
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]