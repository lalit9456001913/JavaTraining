package com.example.sbfeb7.resources.employees.repos;

import com.example.sbfeb7.resources.employees.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}