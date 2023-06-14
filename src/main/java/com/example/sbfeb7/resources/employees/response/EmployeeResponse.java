package com.example.sbfeb7.resources.employees.response;

import com.example.sbfeb7.resources.companies.entities.CompanyDto;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class EmployeeResponse {

    private UUID id;

    private String name;

    private Instant createdAt;

    private Instant updatedAt;
    CompanyDto company;
}
