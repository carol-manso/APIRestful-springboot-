package com.APISpringboot.API_springboot.services;

import com.APISpringboot.API_springboot.entities.Company;

import java.util.Optional;

public interface CompanyService {
    Optional<Company> serachByCnpj(String cnpj);
    Company persist(Company company);
}
