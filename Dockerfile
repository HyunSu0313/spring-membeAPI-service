FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY build/libs/member-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]