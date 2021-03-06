package com.APISpringboot.API_springboot.repositories;

import com.APISpringboot.API_springboot.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional()
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByCpf(String cpf);
    Employee findByEmail(String email);
    Optional<Employee> findById(Long id);
    Employee findByCpfOrEmail(String cpf,String email);
}

