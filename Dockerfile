FROM openjdk:11
EXPOSE 8089
ADD /target/kaddem-1.0.0.jar kaddem
ENTRYPOINT ["java", "-jar","kaddem"]