package com.example.sbfeb7.lib.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiErrorsResponse {
    private List<ValidationError> errors;
}

