FROM openjdk:21-jdk-slim-bullseye

VOLUME /tmp

COPY ./mvnw ./
COPY ./.mvn ./.mvn
COPY ./pom.xml ./
COPY ./src ./src

RUN chmod +x mvnw

RUN ./mvnw package -DskipTests

ENTRYPOINT ["./mvnw", "spring-boot:run"]
