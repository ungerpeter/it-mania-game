FROM maven:3.8.1-jdk-11 AS build

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src
COPY res ./res
RUN mvn package -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
# RUN echo 'debconf debconf/frontend select Noninteractive' | debconf-set-selections
RUN apt-get update -y && \
    apt-get install -y libxext6 libxrender1 libxtst6 libxi6 apt-utils libfreetype6 fontconfig fonts-dejavu && \
    rm -rf /var/lib/apt/lists/*
COPY --from=build /app/target/*.jar /app/app.jar
COPY ./res /app/res
ENTRYPOINT ["java", "-jar", "/app/app.jar"]