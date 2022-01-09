package com.APISpringboot.API_springboot.services;
import com.APISpringboot.API_springboot.entities.Cast;
import com.APISpringboot.API_springboot.repositories.CastRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class CastServiceTest {
    @MockBean
    private CastRepository castRepository;
    @Autowired
    private CastService castService;
    @BeforeEach
    public void setUp(){
        BDDMockito.given(this.castRepository.findByEmployeeId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
                .willReturn(new PageImpl<Cast>(new ArrayList<Cast>()));

        BDDMockito.given(this.castRepository.findById(Mockito.anyLong())).willReturn(java.util.Optional.of(new Cast()));
        BDDMockito.given(this.castRepository.save(Mockito.any(Cast.class))).willReturn(new Cast());
    }
    @Test
    public void testSearchCastByEmployeeId(){
        Page<Cast> cast = this.castService.findByEmployeeId(1L, PageRequest.of(0, 10));
        assertNotNull(cast);
    }

    @Test
    public void testSearchCatById(){

        Optional<Cast> cast = this.castService.findById(1L);
        assertTrue(cast.isPresent());
    }
    @Test
    public void testPersistCast(){
        Cast cast = this.castService.persist(new Cast());
        assertNotNull(cast);
    }



}
