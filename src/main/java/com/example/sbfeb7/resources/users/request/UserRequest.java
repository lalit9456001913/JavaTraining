package com.example.sbfeb7.resources.users.request;

import com.example.sbfeb7.resources.users.entities.UserRoleType;
import com.example.sbfeb7.resources.users.validations.ValidName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserRequest {
    @Pattern(regexp = "^[1-9]\\d{9}$", message = "Not a valid mobile number")
    private String mobileNumber;
    @Min(value = 1)
    private int countryCode;
    private LocalDateTime mobileVerifiedAt;
    @ValidName
    private String name;
    @Email
    private String emailId;

    private UserRoleType role;
}
