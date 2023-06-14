package com.example.sbfeb7.resources.employees.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class EmployeeRequest {
    private String name;
    private UUID companyId;
}
