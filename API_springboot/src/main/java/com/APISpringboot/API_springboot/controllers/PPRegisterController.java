package com.APISpringboot.API_springboot.controllers;


import com.APISpringboot.API_springboot.dtos.PPRegisterDto;
import com.APISpringboot.API_springboot.entities.Company;
import com.APISpringboot.API_springboot.entities.Employee;
import com.APISpringboot.API_springboot.enums.ProfileEnum;
import com.APISpringboot.API_springboot.response.Response;
import com.APISpringboot.API_springboot.services.CompanyService;
import com.APISpringboot.API_springboot.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Controller
@RequestMapping("/api/register-pp")
//@CrossOrigin(origins = "*")
public class PPRegisterController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompanyService companyService;

    public PPRegisterController() {
    }

    @PostMapping
    public ResponseEntity<Response<PPRegisterDto>> register(@Valid @RequestBody PPRegisterDto ppRegisterDto, BindingResult result) throws NoSuchAlgorithmException {
        Response<PPRegisterDto> response = new Response<>();
        validateExistingData(ppRegisterDto, result);
        Employee employee = this.convertDtoToEmployee(ppRegisterDto);
        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        this.employeeService.persist(employee);
        response.setData(this.convertEmployeeToDto(employee));
        return ResponseEntity.ok(response);
    }

    private PPRegisterDto convertEmployeeToDto(Employee employee) {
        PPRegisterDto ppRegisterDto = new PPRegisterDto();
        ppRegisterDto.setCnpj(employee.getCompany().getCnpj());
        ppRegisterDto.setEmail(employee.getEmail());
        ppRegisterDto.setCpf(employee.getCpf());
        ppRegisterDto.setId(employee.getId());
        ppRegisterDto.setName(employee.getName());
        ppRegisterDto.setHoursPerDay( Optional.ofNullable(employee.getWorkHoursPerDay()));
        ppRegisterDto.setLunchHours( Optional.ofNullable(employee.getLunchHours()));
        ppRegisterDto.setHourValue( Optional.ofNullable(employee.getHourValue()));

        return ppRegisterDto;
    }

    private Employee convertDtoToEmployee(PPRegisterDto ppRegisterDto) {
        Employee employee = new Employee();
        employee.setCpf(ppRegisterDto.getCpf());
        employee.setName(ppRegisterDto.getName());
        employee.setEmail(ppRegisterDto.getEmail());
        employee.setProfile(ProfileEnum.ROLE_USER); // se é pj, automaticamente é admin
        employee.setPassword(ppRegisterDto.getPassword());
        ppRegisterDto.getHoursPerDay().ifPresent(hours ->  employee.setWorkHoursPerDay(ppRegisterDto.getHoursPerDay().get()));
        ppRegisterDto.getHourValue().ifPresent(value -> employee.setHourValue(ppRegisterDto.getHourValue().get()));
        ppRegisterDto.getLunchHours().ifPresent(value -> employee.setLunchHours(ppRegisterDto.getLunchHours().get()) );
        employee.setCompany(this.companyService.serachByCnpj(ppRegisterDto.getCnpj()).get());
        return employee;
    }


    //verify if the employee or the company already exists in the DB.
    private void validateExistingData(PPRegisterDto ppRegisterDto, BindingResult result) {

        Optional<Company> company = this.companyService.serachByCnpj(ppRegisterDto.getCnpj());
        if(!company.isPresent()){
            result.addError(new ObjectError("company", "Company doesn't exist"));
        }
        this.employeeService.searchByCpf(ppRegisterDto.getCpf())
                .ifPresent(emp -> result.addError(new ObjectError("employee", "employee already exists")));

        this.employeeService.searchByEmail(ppRegisterDto.getEmail())
                .ifPresent(emp -> result.addError(new ObjectError("employee", "employee already exists")));

    }

}
