FROM maven:3.9.6-eclipse-temurin-21 as build
ARG SERVICE
WORKDIR /usr/build

COPY . .
RUN mvn install -Dmaven.test.skip=true -pl ${SERVICE} -am


FROM eclipse-temurin:21
ARG SERVICE
WORKDIR /usr/${SERVICE}

COPY --from=build /usr/build/${SERVICE}/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]