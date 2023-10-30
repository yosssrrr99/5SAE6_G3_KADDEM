package tn.esprit.spring.kaddem.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.List;

@SpringBootTest
public class EtudiantServiceImplTest {

    @Autowired
    EtudiantServiceImpl etudiantService;

    @Test
    void retrieveAllEtudiant() {
        final List<Etudiant> allEtudiants = this.etudiantService.retrieveAllEtudiants();
        // Écrivez vos assertions pour tester les résultats
    }
}

