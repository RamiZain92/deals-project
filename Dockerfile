FROM openjdk:11.0.4-jre-slim-buster
#FROM adoptopenjdk/openjdk11:alpine-jre
RUN apt-get update && apt-get install telnet vim  curl unzip -y
## Refer to Maven build -> finalName
ARG JAR_FILE=target/fazeal-logistics-microservice-0.0.1-SNAPSHOT.jar
## cd /opt/app
WORKDIR /opt/app
RUN curl -O https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip && unzip newrelic-java.zip
#COPY newrelic.yml /opt/app/newrelic
## cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar
## java -jar /opt/app/app.jar
#ENTRYPOINT ["java","-javaagent:newrelic/newrelic.jar","-jar","app.jar"]
ENTRYPOINT ["java","-jar","app.jar"]