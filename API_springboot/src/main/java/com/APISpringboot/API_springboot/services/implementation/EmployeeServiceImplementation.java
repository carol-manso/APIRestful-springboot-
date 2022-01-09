package com.APISpringboot.API_springboot.services.implementation;

import com.APISpringboot.API_springboot.entities.Employee;
import com.APISpringboot.API_springboot.repositories.EmployeeRepository;
import com.APISpringboot.API_springboot.services.EmployeeService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> persist(Employee employee) {
        System.out.println("Persisting employee {}" + employee);
        return Optional.ofNullable(employeeRepository.save(employee));
    }

    @Override
    public Optional<Employee> searchByCpf(String cpf) {
        System.out.println("Searching employee by cpf");
        return  Optional.ofNullable(employeeRepository.findByCpf(cpf));
    }

    @Override
    public Optional<Employee> searchByEmail(String email) {
        System.out.println("Searching employee by email");
        return Optional.ofNullable(employeeRepository.findByEmail(email));

    }

    @Override
    public Optional<Employee> findOne(Long id) {
        System.out.println("searching by id");
        return employeeRepository.findById(id);
    }
}
