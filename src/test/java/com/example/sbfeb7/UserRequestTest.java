package com.example.sbfeb7;

import com.example.sbfeb7.factory.DataFactory;
import com.example.sbfeb7.resources.users.request.UserRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidation() {
        // Given
        UserRequest userRequest = DataFactory.userRequest().build();

        // When
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailMobileNumberValidation() {
        // Given
        UserRequest userRequest = DataFactory.userRequest()
                .mobileNumber("1234")
                .build();

        // When
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        // Then
        assertEquals(1, violations.size());
        ConstraintViolation<UserRequest> violation = violations.iterator().next();
        assertEquals("Not a valid mobile number", violation.getMessage());
        assertEquals("mobileNumber", violation.getPropertyPath().toString());
    }

    @Test
    void shouldFailEmailValidation() {
        // Given
        UserRequest userRequest = DataFactory.userRequest()
                .emailId("johndoe")
                .build();

        // When
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        // Then
        assertEquals(1, violations.size());
        ConstraintViolation<UserRequest> violation = violations.iterator().next();
        assertEquals("must be a well-formed email address", violation.getMessage());
        assertEquals("emailId", violation.getPropertyPath().toString());
    }

}