package com.APISpringboot.API_springboot.services;

import com.APISpringboot.API_springboot.entities.Employee;
import com.APISpringboot.API_springboot.repositories.EmployeeRepository;
import com.APISpringboot.API_springboot.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class EmployeeServiceTest {
    @MockBean
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    private final String EMAIL = "juju@gmail.com";
    private final String CPF = "12369874568";
    private final Long ID = 1L;

    @BeforeEach
    public void setUp() throws Exception{
        BDDMockito.given(this.employeeRepository.findByCpf(Mockito.anyString())).willReturn(new Employee());
        BDDMockito.given(this.employeeRepository.findByEmail(Mockito.anyString())).willReturn(new Employee());
        BDDMockito.given(this.employeeRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Employee()));
        BDDMockito.given(this.employeeRepository.save(Mockito.any(Employee.class))).willReturn(new Employee());

    }

    @Test
    public void testPersistEmployee(){
        Optional<Employee> employee = this.employeeService.persist(new Employee());
        assertNotNull(employee.get());
    }
    @Test
    public void testsearchByEmail(){
        Optional<Employee> employee = employeeService.searchByEmail(EMAIL);
        assertTrue(employee.isPresent());
    }
    @Test
    public void testfindemployeeByCpf(){
        Optional<Employee> employee = employeeService.searchByCpf(CPF);
        assertTrue(employee.isPresent());
    }
    @Test
    public void testfindemployeeById(){
        Optional<Employee> employee = employeeService.findOne(ID);
        assertTrue(employee.isPresent()); //idPresent() -> se existe um employee dentro do meu optional.

    }
}

