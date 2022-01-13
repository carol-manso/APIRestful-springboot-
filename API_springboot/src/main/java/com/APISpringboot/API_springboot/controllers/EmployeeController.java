package com.APISpringboot.API_springboot.controllers;

import com.APISpringboot.API_springboot.dtos.EmployeeDto;
import com.APISpringboot.API_springboot.entities.Employee;
import com.APISpringboot.API_springboot.response.Response;
import com.APISpringboot.API_springboot.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Controller
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(){}

    @PutMapping("/{id}")
    public ResponseEntity<Response<EmployeeDto>> update(@PathVariable("id") Long id, @Valid @RequestBody EmployeeDto employeeDto, BindingResult result) throws NoSuchAlgorithmException {

        Response<EmployeeDto> response = new Response<EmployeeDto>();

        Optional<Employee> employee = this.employeeService.findOne(id);
        if(!employee.isPresent()){
            result.addError(new ObjectError("employee", "employee  not found"));
        }
        this.updateDataEmployee(employee.get(), employeeDto, result);
        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        this.employeeService.persist(employee.get());
        response.setData(this.convertEmployeeToDto(employee.get()));
        return ResponseEntity.ok(response);
    }

    private EmployeeDto convertEmployeeToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setId(employee.getId());
        if(employee.getLunchHours() != null){
            employeeDto.setLunchHours(Optional.ofNullable(String.valueOf(employee.getLunchHours())));
        }
        if(employee.getWorkHoursPerDay() != null){
            employeeDto.setWorkHours(Optional.ofNullable(String.valueOf(employee.getWorkHoursPerDay())));
        }
        if(employee.getHourValue() != null){
            employeeDto.setHourValue(Optional.ofNullable(String.valueOf(employee.getHourValue())));
        }
        return employeeDto;

    }

    private void updateDataEmployee(Employee employee, EmployeeDto employeeDto, BindingResult result) {
        employee.setName(employeeDto.getName());
        if(!employee.getEmail().equals(employeeDto.getEmail())){
            this.employeeService.searchByEmail(employeeDto.getEmail())
                    .ifPresent(func -> result.addError(new ObjectError("employee", "email already exists")));
            employee.setEmail(employeeDto.getEmail());
        }
        employee.setLunchHours(null);
        employeeDto.getLunchHours()
                .ifPresent(lunchHours -> employee.setLunchHours(Float.valueOf(lunchHours)));

        employee.setHourValue(null);
        employeeDto.getHourValue().ifPresent(hourValue -> employee.setHourValue(new BigDecimal(hourValue)));

        employee.setWorkHoursPerDay(null);
        employeeDto.getWorkHours().ifPresent(workHours -> employee.setWorkHoursPerDay(Float.valueOf(workHours)));

        employee.setPassword(null);

        if(employeeDto.getSenha().isPresent()){
            employee.setPassword(employeeDto.getSenha().get());
        }

    }

}
