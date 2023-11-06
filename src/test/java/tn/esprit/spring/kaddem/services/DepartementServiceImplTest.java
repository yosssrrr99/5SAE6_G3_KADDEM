package tn.esprit.spring.kaddem.services;

import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Departement;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)

public class DepartementServiceImplTest {
   @InjectMocks
    @Autowired
    DepartementServiceImpl departementService;
    @Mock
    private DepartementRepository departementRepository;
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


    @Test
    public void testRetrieveDepartement() {
        // Arrange
        Integer idDepart = 1;
        Departement expectedDepartement = new Departement();
        expectedDepartement.setIdDepart(idDepart);

        // Mocking the behavior of the repository method
        when(departementRepository.findById(idDepart)).thenReturn(Optional.of(expectedDepartement));

        // Act
        Departement retrievedDepartement = departementService.retrieveDepartement(idDepart);

        // Assert
        Assert.assertNotNull(retrievedDepartement);
        Assert.assertEquals(idDepart, retrievedDepartement.getIdDepart());
    }

    @Test
    public void deleteDepartement() {
        // Arrange
        Integer idDepartement = 6;

        // Mocking the behavior of the retrieveDepartement method to return an empty Optional
        when(departementRepository.findById(idDepartement)).thenReturn(Optional.empty());

        // Act
        departementService.deleteDepartement(idDepartement);

        // Assert
        // Verify that the delete method of the repository was never called
        verify(departementRepository, never()).delete(any(Departement.class));
    }
}


