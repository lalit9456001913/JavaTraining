package com.example.sbfeb7.resources.employees.services;

import com.example.sbfeb7.resources.companies.repos.CompanyRepository;
import com.example.sbfeb7.resources.companies.services.CompanyService;
import com.example.sbfeb7.resources.employees.entities.Employee;
import com.example.sbfeb7.resources.employees.repos.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;

    public EmployeeService(EmployeeRepository employeeRepository, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }

    public Employee createEmployee(Employee employee, UUID companyId){
        employee.setCompany(companyService.getCompanyById(companyId));
        employee.setId(UUID.randomUUID());
        return employeeRepository.save(employee);
    }
}
