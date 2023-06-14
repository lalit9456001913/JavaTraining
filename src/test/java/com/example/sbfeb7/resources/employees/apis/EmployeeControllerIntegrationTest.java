package com.example.sbfeb7.resources.employees.apis;

import com.example.sbfeb7.factory.DataFactory;
import com.example.sbfeb7.resources.companies.entities.Company;
import com.example.sbfeb7.resources.companies.repos.CompanyRepository;
import com.example.sbfeb7.resources.employees.entities.Employee;
import com.example.sbfeb7.resources.employees.request.EmployeeRequest;
import com.example.sbfeb7.resources.employees.services.EmployeeService;
import com.example.sbfeb7.resources.users.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CompanyRepository companyRepository;

    private EmployeeRequest employeeRequest;
    private Company company;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        var company = DataFactory.company().build();
        company = companyRepository.save(company);
        this.employeeRequest = DataFactory.employeeRequest(company.getId()).build();
    }

    @Test
    @DisplayName("should return status created")
    void whenValidUrlAndMethodAndContentType_thenReturns200() throws Exception {
        String content = objectMapper.writeValueAsString(this.employeeRequest);
        mockMvc.perform(post("/employees")
                        .content(content)
                        .contentType("application/json"))
                .andExpect(status().isCreated());

    }
}
