package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceImplTest {


    @Autowired
    private IContratService contratService;

    //private ContratRepository contratRepository;
    private static final Logger logger = LogManager.getLogger(ContratServiceImpl.class);



    @Test
    public void testRetrieveAllContrats() {
        List<Contrat> listContrat=this.contratService.retrieveAllContrats();
        assertNotNull(listContrat);
        logger.info(listContrat);
    }

    @Test
    public void testAjoutContrat() {
        Contrat c1=new Contrat( new Date(), new Date(), Specialite.IA, false, 1000);
        Contrat c=this.contratService.addContrat(c1);
        assertNotNull(c);
        logger.info("contrat ajouter avec succee !");
    }

    @Test
    public void testUpdateContrat(){
        int idC=5;
        Contrat c=this.contratService.retrieveContrat(idC);
        assertNotNull(c);

        c.setMontantContrat(2000);
        c.setSpecialite(Specialite.CLOUD);

        Contrat contratToUpdate=this.contratService.updateContrat(c);
        assertEquals(2000, contratToUpdate.getMontantContrat());
        logger.info("le contrat avec l'id " + c.getIdContrat() +" est modifié avec succee !");
    }

    @Test
    public void testDeleteContrat() {
        int idC=5;
        Contrat c=this.contratService.retrieveContrat(idC);
        assertNotNull(c);

        this.contratService.removeContrat(idC);
        logger.info("contrat supprimer avec succee !");

    }
}
