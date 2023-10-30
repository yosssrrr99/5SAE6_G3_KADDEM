package tn.esprit.spring.kaddem.services;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Equipe;

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


}
