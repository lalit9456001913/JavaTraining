package com.example.sbfeb7.resources.employees.services;

import com.example.sbfeb7.factory.DataFactory;
import com.example.sbfeb7.resources.companies.entities.Company;
import com.example.sbfeb7.resources.companies.services.CompanyService;
import com.example.sbfeb7.resources.employees.entities.Employee;
import com.example.sbfeb7.resources.employees.repos.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private CompanyService companyService;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void createEmployee() {
        // Arrange
        var companyId = UUID.randomUUID();
        var employeeId = UUID.randomUUID();

        var company = new Company().setCompanyName("Acme corp");
        company.setId(companyId);

//        Employee employee = new Employee().setId(employeeId).setName("employeename").setCompany(company);
        var employee = DataFactory.employee().build();
        var returnEmployee = DataFactory.employee().company(company).build();

        when(companyService.getCompanyById(companyId)).thenReturn(company);
        when(employeeRepository.save(employee)).thenReturn(returnEmployee);
        var result = employeeService.createEmployee(employee, companyId);

        MockedStatic<UUID> mocked = mockStatic(UUID.class);
        Mockito.when(UUID.randomUUID()).thenReturn(employeeId);


        // Assert
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository, times(1)).save(employeeArgumentCaptor.capture());
        assertEquals(employee.getName(), employeeArgumentCaptor.getValue().getName());
        assertEquals(employee.getId(), employeeArgumentCaptor.getValue().getId());
        assertEquals(employee.getId(), employeeArgumentCaptor.getValue().getId());
        assertEquals(company, employeeArgumentCaptor.getValue().getCompany());
        assertEquals(company, result.getCompany());
    }
}
