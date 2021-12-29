package com.APISpringboot.API_springboot.services.implementation;

import com.APISpringboot.API_springboot.entities.Company;
import com.APISpringboot.API_springboot.repositories.CompanyRepository;
import com.APISpringboot.API_springboot.services.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class CompanyServiceImplementation implements CompanyService {
    public static final Logger log = LoggerFactory.getLogger(CompanyServiceImplementation.class);
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Optional<Company> serachByCnpj(String cnpj) {
        log.info("Searching for a company for the CNPJ {}", cnpj );
        return Optional.ofNullable(companyRepository.findByCnpj(cnpj));
    }

    @Override
    public Company persist(Company company) {
        log.info("Persisting the company: ", company );

        return this.companyRepository.save(company);
    }
}
