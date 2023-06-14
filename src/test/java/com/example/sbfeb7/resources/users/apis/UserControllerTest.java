package com.example.sbfeb7.resources.users.apis;

import com.example.sbfeb7.config.JwtAuthenticationFilter;
import com.example.sbfeb7.factory.DataFactory;
import com.example.sbfeb7.lib.errors.ErrorType;
import com.example.sbfeb7.lib.errors.ValidationError;
import com.example.sbfeb7.lib.errors.ValidationErrorsResponse;
import com.example.sbfeb7.resources.employees.entities.Employee;
import com.example.sbfeb7.resources.users.apis.UserController;
import com.example.sbfeb7.resources.users.entities.User;
import com.example.sbfeb7.resources.users.mappers.UserMapper;
import com.example.sbfeb7.resources.users.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = UserController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class))
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private UserMapper userMapper = UserMapper.INSTANCE;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("should return status created")
    void whenValidUrlAndMethodAndContentType_thenReturns200() throws Exception {

        var companyId = UUID.randomUUID();
        var user = DataFactory.userRequest().build();

        String content = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/companies/%s/users".formatted(companyId))
                        .content(content)
                        .contentType("application/json"))
                .andExpect(status().isCreated());

    }
    @Test
    @DisplayName("should check valid input")
    void validInput () throws Exception {

        var companyId = UUID.randomUUID();
        var user = DataFactory.userRequest().name(null).build();

        String content = objectMapper.writeValueAsString(user);

        MvcResult result = mockMvc.perform(post("/companies/%s/users".formatted(companyId))
                        .content(content)
                        .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        ValidationErrorsResponse errorsResponse = objectMapper.readValue(body, ValidationErrorsResponse.class);
        var expectedError = new ValidationErrorsResponse(false,
                ErrorType.validation,
                new ValidationError("name", "Name must not be empty or null"));
        assertEquals(expectedError, errorsResponse);
    }
    @Test
    @DisplayName("should validate bussiness logic")
    void validateBusinessLogic() throws Exception {

        var companyId = UUID.randomUUID();
        var userRequest = DataFactory.userRequest().build();

        String content = objectMapper.writeValueAsString(userRequest);

        User returnUser = userMapper.userRequestToUser(userRequest);
        when(userService
                .createUser(argThat(user -> user.getName().equals(userRequest.getName())), eq(companyId)))
                .thenReturn(returnUser);


        mockMvc.perform(post("/companies/%s/users".formatted(companyId))
                        .content(content)
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(userRequest.getName()))
                .andExpect(jsonPath("$.emailId").value(userRequest.getEmailId()))
                .andExpect(jsonPath("$.mobileNumber").value(userRequest.getMobileNumber()));

    }

    @Test
    @DisplayName("should return user by id")
    void returnUser() throws Exception {

        var userId = UUID.randomUUID();

        mockMvc.perform(get("/users/%s".formatted(userId))
                        .contentType("application/json"))
                .andExpect(status().isOk());

        ArgumentCaptor<UUID> userIdCaptor = ArgumentCaptor.forClass(UUID.class);

        verify(userService, times(1)).userById(userIdCaptor.capture());
        UUID capturedId = userIdCaptor.getValue();
        assertEquals(userId, capturedId);

    }

}

