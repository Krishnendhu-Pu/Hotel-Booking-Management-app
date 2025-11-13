# ===========================
# 1. Build Spring Boot backend
# ===========================
FROM maven:3.9.4-eclipse-temurin-21 AS backend-build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ===========================
# 2. Build React frontend
# ===========================
FROM node:18 AS frontend-build
WORKDIR /frontend
# Copy package.json first for caching
COPY hotel-booking-frontend/package*.json ./
RUN npm install
# Copy the full frontend source
COPY hotel-booking-frontend ./hotel-booking-frontend
WORKDIR /frontend/hotel-booking-frontend
RUN npm run build

# ===========================
# 3. Final image (JDK 21)
# ===========================
FROM eclipse-temurin:21-jdk
WORKDIR /app
# Copy Spring Boot jar
COPY --from=backend-build /app/target/*.jar app.jar
# Copy React build folder
COPY --from=frontend-build /frontend/hotel-booking-frontend/build /app/static
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
