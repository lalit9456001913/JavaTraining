package com.example.sbfeb7.resources.companies.repos;

import com.example.sbfeb7.factory.DataFactory;
import com.example.sbfeb7.resources.companies.entities.Company;
import com.example.sbfeb7.resources.employees.entities.Employee;
import com.example.sbfeb7.resources.employees.repos.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    Employee employeeOne;
    Employee employeeTwo;

    @BeforeEach
    void setUp() {
        employeeOne = DataFactory.employee().build();
        employeeTwo = DataFactory.employee().build();
        employeeRepository.saveAll(List.of(employeeOne, employeeTwo));
    }

    @Test
    @DisplayName("Test if saving works")
    public void save() {
        Company company = DataFactory.company().build();
        company.getEmployees().addAll(List.of(employeeOne, employeeTwo));
        Company savedCompany = companyRepository.save(company);
        Employee employeeOne = savedCompany.getEmployees().stream().findFirst().get();
//        Employee employeeOne = savedCompany.getEmployees().in.get();
        assertEquals(2, savedCompany.getEmployees().size());

    }
}