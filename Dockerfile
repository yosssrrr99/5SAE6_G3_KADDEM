FROM openjdk:8
EXPOSE 8089
ADD target/kaddem-0.0.1-SNAPSHOT.jar houssem-docker.jar
ENTRYPOINT ["java","-jar","houssem-docker.jar"]    
