package com.example.sbfeb7.lib.errors;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private List<ValidationError> apiErrors;
    public ValidationException(ValidationError... apiErrors) {
        this.apiErrors = List.of(apiErrors);
    }
}
