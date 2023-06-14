package com.example.sbfeb7.resources.companies.entities;

import com.example.sbfeb7.resources.employees.entities.Employee;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class CompanyDto {

    private UUID id;

    private String companyName;

    private String brandName;

    private Instant createdAt;

    private Instant updatedAt;

    private List<Employee> employees;
}
