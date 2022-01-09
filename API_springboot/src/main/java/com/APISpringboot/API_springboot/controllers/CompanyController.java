package com.APISpringboot.API_springboot.controllers;


import com.APISpringboot.API_springboot.dtos.CompanyDto;
import com.APISpringboot.API_springboot.entities.Company;
import com.APISpringboot.API_springboot.response.Response;
import com.APISpringboot.API_springboot.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "*") //acessivel a multiplos dominios
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    public CompanyController(){

    }
    @GetMapping(value = "/cnpj/{cnpj}")
    public ResponseEntity<Response<CompanyDto>> searchByCnpj(@PathVariable("cnpj") String cnpj){
        System.out.println("Searching by CNPJ: {}" + cnpj);
        Response<CompanyDto> response = new Response<CompanyDto>();
        Optional<Company> company = companyService.serachByCnpj(cnpj);
        if(!company.isPresent()){
            System.out.println("Company not found for CNPJ = " + cnpj);
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(this.convertCompanyDto(company.get()));
        return  ResponseEntity.ok(response);
    }

    private CompanyDto convertCompanyDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setCnpj(company.getCnpj());
        companyDto.setId(company.getId());
        companyDto.setSocialReason(company.getSocialReason());
        return companyDto;
    }
}
