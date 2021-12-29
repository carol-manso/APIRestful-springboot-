package com.APISpringboot.API_springboot.services;


import com.APISpringboot.API_springboot.entities.Employee;

import java.util.Optional;



public interface EmployeeService {

    /**
     * Persists an employee in the database
     *
     * @param employee
     * @return Employee
     */
    Optional<Employee> persist(Employee employee);
    Optional<Employee> searchByCpf(String cpf);
    Optional<Employee> searchByEmail(String email);
    Optional<Employee> findOne(Long id);

}
