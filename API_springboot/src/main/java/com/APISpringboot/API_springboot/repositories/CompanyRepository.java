package com.APISpringboot.API_springboot.repositories;

import com.APISpringboot.API_springboot.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Transient;
import javax.transaction.Transactional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Transactional()
    Company findByCnpj(String cnpj);

}
