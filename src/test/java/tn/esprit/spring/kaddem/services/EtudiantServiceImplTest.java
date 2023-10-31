package tn.esprit.spring.kaddem.services;


import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import java.util.List;


@SpringBootTest
@Slf4j
public class EtudiantServiceImplTest {

    @Autowired
    EtudiantServiceImpl etudiantService;
    @Autowired
    DepartementServiceImpl departementService;
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    ContratRepository contratRepository;
    @Autowired
    EtudiantRepository etudiantRepository;

    @Test
    void retrieveAllEtudiant() {
        final List<Etudiant> allEtudiants = this.etudiantService.retrieveAllEtudiants();
        // Vérifie que la liste n'est pas nulle
        Assert.assertNotNull(allEtudiants);

        // Vérifie que la liste contient au moins un étudiant
        Assert.assertTrue(allEtudiants.size() > 0);
    }
    @Test
    void testAddEtudiant() {
        Etudiant newEtudiant = new Etudiant();
        newEtudiant.setNomE("jrad");
        newEtudiant.setPrenomE("houssem");
        newEtudiant.setOp(Option.GAMIX);
        Etudiant savedEtudiant = etudiantService.addEtudiant(newEtudiant);

        // Vérifiez que l'étudiant a été correctement enregistré et possède un identifiant
        Assert.assertNotNull(savedEtudiant.getIdEtudiant());
    }
    @Test
    void testUpdateEtudiant() {

        Etudiant existingEtudiant = etudiantService.retrieveEtudiant(2);


        existingEtudiant.setOp(Option.SIM);
        Etudiant updatedEtudiant = etudiantService.updateEtudiant(existingEtudiant);


        Assert.assertEquals(Option.SIM, updatedEtudiant.getOp());
    }
    @Test
    void testAssignEtudiantToDepartement() {

        Etudiant etudiant = etudiantRepository.findById(6).orElse(null);
        Departement departement = departementRepository.findById(1).orElse(null);

        etudiantService.assignEtudiantToDepartement(etudiant.getIdEtudiant(), departement.getIdDepart());

        Etudiant assignedEtudiant = etudiantService.retrieveEtudiant(6);

        Assert.assertEquals(departement.getIdDepart(), assignedEtudiant.getDepartement().getIdDepart());
    }
    @Test
    void testAddAndAssignEtudiantToEquipeAndContract() {

        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("jrad");
        etudiant.setPrenomE("houssem");
        etudiant.setOp(Option.GAMIX);
        Contrat contrat = contratRepository.findById(1).orElse(null);
        Equipe equipe = equipeRepository.findById(1).orElse(null);
        log.info("contart"+contrat);
        log.info("equipe"+equipe);

        contratRepository.save(contrat);
        equipeRepository.save(equipe);


        Etudiant etudiantAssigne = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, contrat.getIdContrat(), equipe.getIdEquipe());
        Assert.assertTrue(isEtudiantAssignedToContractOrEquipe(etudiantAssigne.getIdEtudiant(), contrat.getIdContrat(), equipe.getIdEquipe()));
    }
    // Méthode de vérification personnalisée pour tester l'affectation d'un étudiant
    private boolean isEtudiantAssignedToContractOrEquipe(Integer etudiantId, Integer contratId, Integer equipeId) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
        if (etudiant != null) {
            if (contratId != null) {
                // Vérifier l'affectation au contrat
                for (Contrat contrat : etudiant.getContrats()) {
                    if (contrat.getIdContrat().equals(contratId)) {
                        return true;
                    }
                }
            }
            if (equipeId != null) {
                // Vérifier l'affectation à l'équipe
                for (Equipe equipe : etudiant.getEquipes()) {
                    if (equipe.getIdEquipe().equals(equipeId)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Test
    void testretrieveEtudiant(){
        Etudiant e = etudiantService.retrieveEtudiant(1);
        Assert.assertNotNull(e);
    }
    @Test
    public void testremoveEtudiant(){

        Etudiant etudiantToRemove = new Etudiant();
        etudiantToRemove.setIdEtudiant(4);


        etudiantService.removeEtudiant(etudiantToRemove.getIdEtudiant());

        List<Etudiant> lista = etudiantService.retrieveAllEtudiants();
        for(Etudiant e:lista){
            Assert.assertNotEquals(e.getIdEtudiant(),etudiantToRemove.getIdEtudiant());
        }


    }
    @Test
    public void testgetEtudiantsByDepartement(){
        List<Etudiant> e = etudiantService.getEtudiantsByDepartement(1);
        Assert.assertNotNull(e);
    }

    }









