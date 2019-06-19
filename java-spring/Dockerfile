FROM openjdk:12-jdk as builder
COPY . .
RUN ./gradlew --no-daemon bootJar

FROM openjdk:12
COPY --from=builder build/libs/*.jar /app.jar
RUN chmod +x /app.jar
CMD java -jar /app.jar
