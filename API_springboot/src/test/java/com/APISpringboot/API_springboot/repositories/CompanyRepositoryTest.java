package com.APISpringboot.API_springboot.repositories;

import com.APISpringboot.API_springboot.entities.Company;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class CompanyRepositoryTest {
    @Autowired //injeção de dependencia.
    private CompanyRepository companyRepository;
    private static final String cnpj = "33268564000169";
    @BeforeEach //antes da execução do teste - cadastro uma empresa no meu bd
    public final void setUp() throws Exception{
        Company company = new Company();
        company.setSocialReason("Example company");
        company.setCnpj(cnpj);
        this.companyRepository.save(company);
    }

    @After("") //depois do meu teste, a deleto
    public final void tearDown(){
        this.companyRepository.deleteAll();
    }
    @Test //durante o meu teste.
    public void testSearchByCnpj(){
        Company company = this.companyRepository.findByCnpj(cnpj);
        assertEquals(cnpj, company.getCnpj());
    }
    

}
