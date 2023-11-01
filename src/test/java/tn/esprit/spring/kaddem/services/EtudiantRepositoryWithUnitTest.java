package tn.esprit.spring.kaddem.services;



import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.List;

import static org.junit.Assert.assertNotNull;
//mvn test -Dtest=PisteRepositoryTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
public class EtudiantRepositoryWithUnitTest {

    @Autowired
    private EtudiantRepository etudiantRepository;
    Etudiant etudiant = Etudiant.builder().nomE("sahnoun").prenomE("yosr").op(Option.SIM).build();




    @Test
    @Order(0)
    public void ajouterEtudiantTest() {
        etudiant = etudiantRepository.save(etudiant);
        log.info(etudiant.toString());
        Assertions.assertNotNull(etudiant.getIdEtudiant());
    }

    @Test
    @Order(1)
    public void modifierEtudiantTest() {
        etudiant.setNomE("Belahsen");
        etudiant = etudiantRepository.save(etudiant);
        log.info(etudiant.toString());
        Assertions.assertNotEquals(etudiant.getNomE(), "yosr");
    }

    @Test
    @Order(2)
    public void listerEtudiants() {
        List<Etudiant> list =(List<Etudiant>)etudiantRepository.findAll();
        log.info(list.size()+"");
        Assertions.assertTrue(list.size() > 0);
    }



    @Test
    @Order(3)
    public void supprimerPiste() {
        etudiantRepository.delete(etudiant);
    }

    @Test
    @Order(4)
    public void compter() {

        long taille = etudiantRepository.count();
        List<Etudiant> lista=(List<Etudiant>)  etudiantRepository.findAll();
        Assertions.assertEquals(taille,lista.size());
    }
}

