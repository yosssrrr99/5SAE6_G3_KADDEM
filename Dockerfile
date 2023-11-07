FROM openjdk:11
EXPOSE 8089
ADD 5SAE6_G3_KADDEM/target/kaddem-1.0.0.jar kaddem
ENTRYPOINT ["java", "-jar","kaddem"]