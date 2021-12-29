package com.APISpringboot.API_springboot.repositories;

import com.APISpringboot.API_springboot.entities.Company;
import com.APISpringboot.API_springboot.entities.Employee;
import com.APISpringboot.API_springboot.enums.ProfileEnum;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    private static final String EMAIL = "email@example.com";
    private static final String CPF = "65689724569";

    @BeforeEach
    public void setUp() throws Exception{
        this.companyRepository.deleteAll();
        this.employeeRepository.deleteAll();
        Company company = this.companyRepository.save(getCompanyData());
        //System.out.println("COMPANY DATAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + getCompanyData().toString());
        this.employeeRepository.save(getEmployeeData(company));
    }
    @After("")
    public  final void tearDown(){
        this.companyRepository.deleteAll();
        this.employeeRepository.deleteAll();
    }
    @Test
    public void testSearchByEmail(){
        Employee employee = this.employeeRepository.findByEmail(EMAIL);
        assertEquals(EMAIL, employee.getEmail());
    }

    @Test
    public void testSearchByCpf(){
        Employee employee = this.employeeRepository.findByCpf(CPF);
        assertEquals(CPF, employee.getCpf());
    }

    @Test
    public void testSearchByEmailAndCpf(){
        Employee employee = this.employeeRepository.findByCpfOrEmail(CPF, EMAIL);
        assertNotNull(employee);
    }
    @Test
    public void testSearchByEmailOrCpfInvalidEmail(){
        Employee employee = this.employeeRepository.findByCpfOrEmail(CPF, "oi@gmail.com");
        assertNotNull(employee);
    }
    @Test
    public void testSearchByEmailAndCpfInvalidCpf(){
        Employee employee = this.employeeRepository.findByCpfOrEmail("12535896478", EMAIL);
        assertNotNull(employee);
    }
    private Employee getEmployeeData(Company company){
        Employee employee = new Employee();
        employee.setName("Fulano");
        employee.setHourValue(BigDecimal.valueOf(120));
        employee.setProfile(ProfileEnum.ROLE_ADMIN);
        employee.setPassword("123586");
        employee.setLunchHours(2F);
        employee.setCompany(company);
        employee.setWorkHoursPerDay(8F);
        employee.setEmail(EMAIL);
        employee.setCpf(CPF);
        return employee;
    }
    private Company getCompanyData() {
        Company company = new Company();
        company.setSocialReason("company test");
        company.setCnpj("65231485968");
        return company;
    }
}
