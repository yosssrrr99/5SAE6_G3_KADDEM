package tn.esprit.spring.kaddem.services;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest


public class EquipeServiceImplTest {

    @Autowired
    private EquipeServiceImpl equipeService;


    @Test
    @Transactional
    public void testRetrieveAllEquipes() {
        List<Equipe> equipes = equipeService.retrieveAllEquipes();
        Assert.assertNotNull(equipes);

        Assert.assertFalse(equipes.isEmpty());
    }

    @Test
    public void testAddEquipe() {
        Equipe equipe = new Equipe();
        equipe.setNomEquipe("testequipe");


        Equipe equipeAjoutee = equipeService.addEquipe(equipe);

       Assert.assertNotNull(equipeAjoutee);

        Assert.assertNotNull(equipeAjoutee.getIdEquipe());



    }
    @Transactional
    @Test
   public void testUpdateEquipe() {
        // Récupérez un étudiant existant
        Equipe existingEquipe= equipeService.retrieveEquipe(2);

        // Modifiez les données de l'étudiant
        existingEquipe.setNiveau(Niveau.SENIOR);
        Equipe updatedEquipe = equipeService.updateEquipe(existingEquipe);

        // Vérifiez que l'étudiant a été correctement mis à jour
        Assert.assertEquals(Niveau.SENIOR, updatedEquipe.getNiveau());
    }



}
