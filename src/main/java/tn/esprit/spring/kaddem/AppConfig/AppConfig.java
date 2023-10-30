package tn.esprit.spring.kaddem.AppConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.spring.kaddem.controllers.EtudiantRestController;

@Configuration
public class AppConfig {
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(EtudiantRestController.class);
    }
}
