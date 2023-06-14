package com.example.sbfeb7.resources.users.validations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameValidatorTest {

    @Test
    void isValid() {
        NameValidator nameValidator = new NameValidator();
        assertTrue(nameValidator.isValid("john_cool", null));
        assertFalse(nameValidator.isValid("john", null));
        assertFalse(nameValidator.isValid(null, null));
    }
}