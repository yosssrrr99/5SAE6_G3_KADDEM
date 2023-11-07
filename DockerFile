FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/kaddem-0.0.1-SNAPSHOT.jar kaddem-project.jar
ENTRYPOINT ["java","-jar","kaddem-project.jar"]