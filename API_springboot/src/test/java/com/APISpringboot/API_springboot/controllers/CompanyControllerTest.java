package com.APISpringboot.API_springboot.controllers;

import com.APISpringboot.API_springboot.entities.Company;
import com.APISpringboot.API_springboot.services.CompanyService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc //contexto WEB
public class CompanyControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CompanyService companyService;

    private static final String SEARCH_COMPANY_CNPJ_URL = "/api/company/cnpj/";
    private static final Long ID = Long.valueOf(1);
    private static final String CNPJ = "36468558036102";
    private static final String SOCIAL_REAZON = "Company Z";

    @Test
    public void testSearchForCompanyInvalidCnpj() throws Exception{
        BDDMockito.given(this.companyService.serachByCnpj(Mockito.anyString())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.get(SEARCH_COMPANY_CNPJ_URL + CNPJ).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value("Company not found for CNPJ =  " + CNPJ));
    }

    @Test
    public void testSearchCompanyValidCnpj() throws Exception{
        BDDMockito.given(this.companyService.serachByCnpj(Mockito.anyString()))
                .willReturn(Optional.of(this.getCompanyData()));
        mvc.perform(MockMvcRequestBuilders.get(SEARCH_COMPANY_CNPJ_URL + CNPJ).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$data.id").value(ID))
                .andExpect(jsonPath("$data.cnpj", equalTo(CNPJ)))
                .andExpect(jsonPath("$data.errors").isEmpty());


    }

    private Company getCompanyData() {
        Company company = new Company();
        company.setId(ID);
        company.setSocialReason(SOCIAL_REAZON);
        company.setCnpj(CNPJ);
        return company;
    }
}
