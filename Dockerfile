FROM amazoncorretto:11-alpine-jdk
MAINTAINER Carlos Prado
COPY target/bank.api-0.0.1-SNAPSHOT.jar bank-api.jar
ENTRYPOINT ["java","-jar","/bank-api.jar"]