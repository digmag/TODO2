FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean install -Dmaven.test.skip

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/todolist-0.0.1-SNAPSHOT.jar /app/todolist-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/todolist-0.0.1-SNAPSHOT.jar"]