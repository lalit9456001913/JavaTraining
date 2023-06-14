package com.example.sbfeb7.resources.auth.request;

import lombok.Data;

@Data
public class AuthMobileUserRequest {
    private String mobileNumber;
    private String otp;
    private String countryCode;
}
