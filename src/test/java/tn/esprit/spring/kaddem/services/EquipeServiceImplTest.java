package tn.esprit.spring.kaddem.services;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import javax.transaction.Transactional;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest


public class EquipeServiceImplTest {


    @InjectMocks
    @Autowired
    private EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepository equipeRepository;
    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;


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




}
