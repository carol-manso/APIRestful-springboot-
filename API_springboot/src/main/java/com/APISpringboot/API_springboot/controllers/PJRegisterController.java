package com.APISpringboot.API_springboot.controllers;


import com.APISpringboot.API_springboot.dtos.JPRegisterDto;
import com.APISpringboot.API_springboot.entities.Company;
import com.APISpringboot.API_springboot.entities.Employee;
import com.APISpringboot.API_springboot.enums.ProfileEnum;
import com.APISpringboot.API_springboot.repositories.EmployeeRepository;
import com.APISpringboot.API_springboot.response.Response;
import com.APISpringboot.API_springboot.services.CompanyService;
import com.APISpringboot.API_springboot.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/api/register-jp")
@CrossOrigin(origins = "*")
public class PJRegisterController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompanyService companyService;

    public PJRegisterController() {
    }

    @PostMapping
    public ResponseEntity<Response<JPRegisterDto>> register(@Valid @RequestBody JPRegisterDto jpRegisterDto, BindingResult result) throws NoSuchAlgorithmException {
        Response<JPRegisterDto> response = new Response<>();
        validateExistingData(jpRegisterDto, result);
        Company company = this.convertDtoToCompany(jpRegisterDto);
        Employee employee = this.convertDtoToEmployee(jpRegisterDto);
        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        this.companyService.persist(company);
        employee.setCompany(company);
        this.employeeService.persist(employee);
        response.setData(this.convertRegisterJPDto(employee));

        return ResponseEntity.ok(response);
    }

    private JPRegisterDto convertRegisterJPDto(Employee employee) {
        JPRegisterDto jpRegisterDto = new JPRegisterDto();
        jpRegisterDto.setCnpj(employee.getCompany().getCnpj());
        jpRegisterDto.setEmail(employee.getEmail());
        jpRegisterDto.setCpf(employee.getCpf());
        jpRegisterDto.setSocialReason(employee.getCompany().getSocialReason());
        jpRegisterDto.setId(employee.getId());
        jpRegisterDto.setName(employee.getName());

        return jpRegisterDto;
    }

    private Employee convertDtoToEmployee(JPRegisterDto jpRegisterDto) {
        Employee employee = new Employee();
        employee.setCpf(jpRegisterDto.getCpf());
        employee.setName(jpRegisterDto.getName());
        employee.setEmail(jpRegisterDto.getEmail());
        employee.setProfile(ProfileEnum.ROLE_ADMIN); // se é pj, automaticamente é admin
        employee.setPassword(jpRegisterDto.getPassword());
        return employee;
    }

    private Company convertDtoToCompany(JPRegisterDto jpRegisterDto) {
        Company company = new Company();
        company.setCnpj(jpRegisterDto.getCnpj());
        company.setSocialReason(jpRegisterDto.getSocialReason());
        return company;
    }

    //verify if the employee or the company already exists in the DB.
    private void validateExistingData(JPRegisterDto jpRegisterDto, BindingResult result) {
        this.companyService.serachByCnpj(jpRegisterDto.getCnpj())
                .ifPresent(comp -> result.addError(new ObjectError("company", "company already exists")));

        this.employeeService.searchByCpf(jpRegisterDto.getCpf())
                .ifPresent(emp -> result.addError(new ObjectError("employee", "employee already exists")));

        this.employeeService.searchByEmail(jpRegisterDto.getEmail())
                .ifPresent(emp -> result.addError(new ObjectError("employee", "employee already exists")));

    }

}
