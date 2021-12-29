package com.APISpringboot.API_springboot.services;

import com.APISpringboot.API_springboot.entities.Company;
import com.APISpringboot.API_springboot.repositories.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class CompanyServiceTest {
    @MockBean //ao invés de um autowired que pegaria uma isntancia real.
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;

    private static final String CNPJ = "33268564000169";
    @BeforeEach
    public void SetUp() throws Exception{
        //um mock para cada um dos métodos x.
        BDDMockito.given(this.companyRepository.findByCnpj(Mockito.anyString())).willReturn(new Company());
        BDDMockito.given(this.companyRepository.save(Mockito.any(Company.class))).willReturn(new Company());

    }
    @Test
    public void testSearchCompanyByCnpj(){
        Optional<Company> company = this.companyService.serachByCnpj(CNPJ);
        assertTrue(company.isPresent());
    }
    @Test
    public void testPersistCompany(){
        Company company = this.companyService.persist(new Company());
        assertNotNull(company);
    }
}

