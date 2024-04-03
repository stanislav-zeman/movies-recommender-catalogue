FROM maven:3.9.6-eclipse-temurin-21 as build
MAINTAINER cere.dev
ARG SERVICE
WORKDIR /usr/build

COPY . .
RUN mvn install -Dmaven.test.skip=true -pl ${SERVICE} -am


FROM eclipse-temurin:21
ARG SERVICE
WORKDIR /usr/${SERVICE}

COPY --from=build /usr/build/${SERVICE}/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]