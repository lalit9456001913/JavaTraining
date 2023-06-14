package com.example.sbfeb7.resources.employees.mappers;

import com.example.sbfeb7.resources.employees.entities.Employee;
import com.example.sbfeb7.resources.employees.request.EmployeeRequest;
import com.example.sbfeb7.resources.employees.response.EmployeeResponse;
import com.example.sbfeb7.resources.users.mappers.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);


    EmployeeResponse employeeToEmployeeResponse(Employee employee);
    Employee employeeRequestToEmployee(EmployeeRequest employeeRequest);

}
