FROM maven:3.8.5-openjdk-21

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mkdir -p /app/src/main/resources/android/apps

RUN mvn clean compile -DskipTests

ENTRYPOINT ["mvn", "test"]