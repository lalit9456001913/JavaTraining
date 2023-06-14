package com.example.sbfeb7.resources.users.response;

import com.example.sbfeb7.resources.companies.entities.CompanyDto;
import com.example.sbfeb7.resources.users.entities.UserRoleType;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String mobileNumber;
    private int countryCode;
    private LocalDateTime mobileVerifiedAt;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
    private String emailId;
    private String password;
    private UUID recruiterManagerId;
    private Set<CompanyDto> companies;
    private UserRoleType role;
}
