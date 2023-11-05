package tn.esprit.spring.kaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;

@Slf4j
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UniversiteServiceImplTest {


    @Mock
    UniversiteRepository universiteRepo;
    //ou MagasinRepository magasinRepo = Mockito.mock(MagasinRepository.class);


    @InjectMocks
    UniversiteServiceImpl universiteService;


    Universite u = Universite.builder()
            .nomUniv("Esprit")
            .build();

    List<Universite> list = new ArrayList<Universite>() {{
        add(Universite.builder().nomUniv("Esprit").build());
        add(Universite.builder().nomUniv("Tekup").build());
    }};

    @Test
    public void addMagasinTest() {
        Mockito.when(universiteRepo.save(Mockito.any(Universite.class))).then(invocation -> {
            Universite model = invocation.getArgument(0, Universite.class);
            model.setIdUniv(1);
            return model;
        });
        log.info("Avant ==> " + u.toString());
        Universite universite = universiteService.addUniversite(u);
        assertSame(universite, u);
        log.info("Après ==> " + u.toString());
    }

    @Test
    public void retreiveUniversiteTest() {

        Mockito.when(universiteRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(u));

        Universite universite = universiteService.retrieveUniversite((Integer) 1566);
        assertNotNull(u);
        log.info("get ==> " + u.toString());

        verify(universiteRepo).findById(Mockito.anyInt());

    }

    @Test
    public void retreiveAllUniversiteTest() {
        Mockito.when(universiteRepo.findAll()).thenReturn(list);
        List<Universite> universites = universiteService.retrieveAllUniversites();
        assertNotNull(universites);
    }
}



