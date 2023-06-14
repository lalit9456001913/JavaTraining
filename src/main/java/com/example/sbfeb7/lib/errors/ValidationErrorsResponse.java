package com.example.sbfeb7.lib.errors;

import lombok.Data;

import java.util.List;

@Data
public class ValidationErrorsResponse {
    private boolean status = false;
    private ErrorType errorType = ErrorType.validation;
    private List<ValidationError> errors;

    public ValidationErrorsResponse(List<ValidationError> errors) {
        this.errors = errors;
    }

    public ValidationErrorsResponse(boolean status, ErrorType errorType, ValidationError... errors) {
        this.status = status;
        this.errorType = errorType;
        this.errors = List.of(errors);
    }
}

