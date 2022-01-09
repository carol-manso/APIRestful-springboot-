package com.APISpringboot.API_springboot.services.implementation;

import com.APISpringboot.API_springboot.entities.Company;
import com.APISpringboot.API_springboot.repositories.CompanyRepository;
import com.APISpringboot.API_springboot.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class CompanyServiceImplementation implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Optional<Company> serachByCnpj(String cnpj) {
        System.out.println("Searching for a company for the CNPJ {}" + cnpj );
        return Optional.ofNullable(companyRepository.findByCnpj(cnpj));
    }

    @Override
    public Company persist(Company company) {
        System.out.println("Persisting the company: " + company );

        return this.companyRepository.save(company);
    }
}
