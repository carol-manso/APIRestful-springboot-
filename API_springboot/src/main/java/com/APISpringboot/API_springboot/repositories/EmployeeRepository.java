package com.APISpringboot.API_springboot.repositories;

import com.APISpringboot.API_springboot.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional()
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByCpf(String cpf);
    Employee findByEmail(String email);
    Employee findOne(Long id);
    Employee findByCpfOrEmail(String cpf,String email);
}
//métodos customizados.
