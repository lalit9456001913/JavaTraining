package com.example.sbfeb7.resources.auth.apis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String mobileNumber;
    private String otp;
    private String countryCode;
}
