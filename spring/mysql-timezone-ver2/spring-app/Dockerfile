FROM openjdk:11-jre-slim

VOLUME /tmp

COPY ./mvnw ./
COPY ./mvnw.cmd ./
COPY ./pom.xml ./
COPY ./src ./src

RUN chmod +x mvnw

RUN ./mvnw package -DskipTests

ENTRYPOINT ["./mvnw", "spring-boot:run"]