package tn.esprit.spring.kaddem.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)

public class DepartementServiceImplTest {
    @Autowired
    DepartementServiceImpl departementService;
    @Test
    @Transactional
    public void retrieveAllDepartement(){
         List<Departement> allDepartements= this.departementService.retrieveAllDepartements();
        Assert.assertNotNull(allDepartements);

        Assert.assertFalse(allDepartements.isEmpty());
    }
    @Test
    public void testAddDepartement(){
        Departement departement = new Departement();
        departement.setNomDepart("testdepartement");
        Departement departementAjoutee = departementService.addDepartement(departement);
        Assert.assertNotNull(departementAjoutee);
        Assert.assertNotNull(departementAjoutee.getIdDepart());
    }
    @Transactional
    @Test
    public void testUpdateDepartement(){
        // Récupérez un étudiant existant
        Departement existingDepartement= departementService.retrieveDepartement(2);

        // Modifiez les données de l'étudiant
        existingDepartement.setNomDepart("testDepartementU");
        Departement updatedDepartement= departementService.updateDepartement(existingDepartement);

        // Vérifiez que l'étudiant a été correctement mis à jour
        Assert.assertEquals("testDepartementU", updatedDepartement.getNomDepart());
    }
}
