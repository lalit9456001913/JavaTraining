package com.example.sbfeb7.resources.auth.apis;

import com.example.sbfeb7.lib.errors.ValidationError;
import com.example.sbfeb7.lib.errors.ValidationException;
import com.example.sbfeb7.resources.users.repos.UserRepository;
import com.example.sbfeb7.resources.users.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRequest request
    ) {
        checkEmailShouldNotExist(request);
        checkMobileShouldNotExist(request);
        return ResponseEntity.ok(service.register(request));
    }

    private void checkEmailShouldNotExist(UserRequest userRequest) {
        userRepository.findByEmailId(userRequest.getEmailId()).ifPresent(u -> {
            throw new ValidationException(new ValidationError("emailId", "User by %s email id already exists".formatted(userRequest.getEmailId())));
        });
    }

    private void checkMobileShouldNotExist(UserRequest userRequest) {
        userRepository.findByMobileNumber(userRequest.getMobileNumber()).ifPresent(u -> {
            throw new ValidationException(new ValidationError("mobileNumber", "User by %s mobile number already exists".formatted(userRequest.getMobileNumber())));
        });
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}
