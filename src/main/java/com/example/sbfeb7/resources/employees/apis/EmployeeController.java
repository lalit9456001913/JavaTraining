package com.example.sbfeb7.resources.employees.apis;

import com.example.sbfeb7.resources.employees.mappers.EmployeeMapper;
import com.example.sbfeb7.resources.employees.request.EmployeeRequest;
import com.example.sbfeb7.resources.employees.response.EmployeeResponse;
import com.example.sbfeb7.resources.employees.services.EmployeeService;
import com.example.sbfeb7.resources.users.mappers.UserMapper;
import com.example.sbfeb7.resources.users.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Transactional
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest employeeRequest) {
        var employee = employeeMapper.employeeRequestToEmployee(employeeRequest);
        var saved = employeeService.createEmployee(employee, employeeRequest.getCompanyId());
        return new ResponseEntity<>(employeeMapper.employeeToEmployeeResponse(saved), HttpStatus.CREATED);
    }
}
