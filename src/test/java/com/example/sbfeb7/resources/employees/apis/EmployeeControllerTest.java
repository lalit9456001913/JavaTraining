package com.example.sbfeb7.resources.employees.apis;

import com.example.sbfeb7.config.JwtAuthenticationFilter;
import com.example.sbfeb7.factory.DataFactory;
import com.example.sbfeb7.resources.employees.entities.Employee;
import com.example.sbfeb7.resources.employees.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class))
@AutoConfigureMockMvc(addFilters = false)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EmployeeService employeeService;


    @Test
    @DisplayName("should return status created")
    void whenValidUrlAndMethodAndContentType_thenReturns200() throws Exception {

        var companyId = UUID.randomUUID();
        var employeeRequest = DataFactory.employeeRequest(companyId).build();

        String content = objectMapper.writeValueAsString(employeeRequest);
        mockMvc.perform(post("/employees")
                        .content(content)
                        .contentType("application/json"))
                .andExpect(status().isCreated());

    }
    @Test
    @DisplayName("should validate business logic")
    void validateBusinessLogic() throws Exception {

        var companyId = UUID.randomUUID();
        var employeeRequest = DataFactory.employeeRequest(companyId).build();

        String content = objectMapper.writeValueAsString(employeeRequest);

        when(employeeService
                .createEmployee(argThat(employee -> employee.getName().equals(employeeRequest.getName())), eq(companyId)))
                .thenReturn(Employee.builder().name("John").build());

        mockMvc.perform(post("/employees")
                        .content(content)
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John"));
    }
}