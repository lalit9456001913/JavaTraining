package com.example.sbfeb7.resources.todos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {

    @NotEmpty
    @Size(min = 5)
//    @Pattern(regexp = "^[1-9]\\d{9}$", message = "Not a valid mobile number")
    private String description;
}
