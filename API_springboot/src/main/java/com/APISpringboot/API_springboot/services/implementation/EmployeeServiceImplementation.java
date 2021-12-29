package com.APISpringboot.API_springboot.services.implementation;

import com.APISpringboot.API_springboot.entities.Employee;
import com.APISpringboot.API_springboot.repositories.EmployeeRepository;
import com.APISpringboot.API_springboot.services.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    private static final Logger log =  LoggerFactory.getLogger(EmployeeServiceImplementation.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> persist(Employee employee) {
        log.info("Persisting employee {}", employee);
        return Optional.ofNullable(employeeRepository.save(employee));
    }

    @Override
    public Optional<Employee> searchByCpf(String cpf) {
        log.info("Searching employee by cpf");
        return  Optional.ofNullable(employeeRepository.findByCpf(cpf));
    }

    @Override
    public Optional<Employee> searchByEmail(String email) {
        log.info("Searching employee by email");
        return Optional.ofNullable(employeeRepository.findByEmail(email));

    }

    @Override
    public Optional<Employee> findOne(Long id) {
        log.info("searching by id");
        return Optional.ofNullable(employeeRepository.findOne(id));
    }
}
