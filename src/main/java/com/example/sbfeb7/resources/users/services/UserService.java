package com.example.sbfeb7.resources.users.services;

import com.example.sbfeb7.lib.errors.ValidationError;
import com.example.sbfeb7.lib.errors.ValidationException;
import com.example.sbfeb7.resources.companies.services.CompanyService;
import com.example.sbfeb7.resources.users.apis.UserController;
import com.example.sbfeb7.resources.users.entities.User;
import com.example.sbfeb7.resources.users.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final CompanyService companyService;
    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserController.class);


    public UserService(UserRepository userRepository, CompanyService companyService) {
        this.userRepository = userRepository;
        this.companyService = companyService;
    }
    public User userById( UUID id){
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ValidationException(
                                new ValidationError("id", "User by id %s does not exist".formatted(id))));
    }

    public User createUser(User user, UUID companyId) {
        checkEmailShouldNotExist(user.getEmailId());
        checkMobileShouldNotExist(user.getMobileNumber());
        user.setId(UUID.randomUUID());
        user.getCompanies().add(companyService.getCompanyById(companyId));
        return userRepository.save(user);
    }

    public void checkEmailShouldNotExist(String emailId) {
        userRepository.findByEmailId(emailId).ifPresent(u -> {
            throw new ValidationException(new ValidationError("emailId", "User by %s email id already exists".formatted(emailId)));
        });
    }
    public void checkMobileShouldNotExist(String mobileNumber) {
        userRepository.findByMobileNumber(mobileNumber).ifPresent(u -> {
            throw new ValidationException(new ValidationError("mobileNumber", "User by %s mobile number already exists".formatted(mobileNumber)));
        });
    }

}
