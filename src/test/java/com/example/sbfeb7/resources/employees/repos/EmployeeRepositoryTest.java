package com.example.sbfeb7.resources.employees.repos;

import com.example.sbfeb7.factory.DataFactory;
import com.example.sbfeb7.resources.companies.entities.Company;
import com.example.sbfeb7.resources.companies.repos.CompanyRepository;
import com.example.sbfeb7.resources.employees.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    Company company;

    @BeforeEach
    public void beforeEach() {
        company = DataFactory.company().build();
        companyRepository.save(company);
    }

    @Test()
    void save() {
        Employee employee = DataFactory.employee().build();

        employee.setCompany(company);
        employeeRepository.save(employee);

        employeeRepository.findById(employee.getId()).ifPresentOrElse(employee1 -> {
                    assertEquals(company.getId(), employee1.getCompany().getId());
                },
                () -> fail("User not found"));
    }


}