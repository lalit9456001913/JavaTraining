package com.example.sbfeb7.resources.users.services;

import com.example.sbfeb7.factory.DataFactory;
import com.example.sbfeb7.lib.errors.ValidationException;
import com.example.sbfeb7.resources.companies.entities.Company;
import com.example.sbfeb7.resources.companies.services.CompanyService;
import com.example.sbfeb7.resources.users.entities.User;
import com.example.sbfeb7.resources.users.repos.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private CompanyService companyService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser() {
        // Arrange
        var companyId = UUID.randomUUID();
        var userId=UUID.randomUUID();
        var company = new Company().setCompanyName("Acme corp");
        company.setId(companyId);
        User user =new User().setId(userId).setName("name");


        when(userRepository.findByMobileNumber(user.getMobileNumber())).thenReturn(Optional.empty());
        when(userRepository.findByEmailId(user.getEmailId())).thenReturn(Optional.empty());
        when(companyService.getCompanyById(companyId)).thenReturn(company);

        // Act
        var result = userService.createUser(user, companyId);

        // Assert
        verify(userRepository, times(1)).save(any());
    }
    @Test
    void testGetUserById() {

        var userId=UUID.randomUUID();
        User user =new User().setId(userId).setName("name");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        var result = userService.userById(userId);
        verify(userRepository, times(1)).findById(any());
    }
    @Test
    void testGetUserByIdThrowValidation() {

        var userId=UUID.randomUUID();
        User user =new User().setId(userId).setName("name");

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        ValidationException exception = assertThrows(ValidationException.class, () -> userService.userById(userId));
        assertEquals(1, exception.getApiErrors().size());
        assertEquals("id", exception.getApiErrors().get(0).getField());
        assertEquals("User by id %s does not exist".formatted(userId), exception.getApiErrors().get(0).getMessage());


    }

    @Test
    void testUserWithSameNameEmailDoestNotExist() {



        var existingUser = DataFactory.user().emailId("email@gmail.com").build();
        when(userRepository.findByEmailId(existingUser.getEmailId())).thenReturn(Optional.of(existingUser));

        // Act + Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> userService.checkEmailShouldNotExist(existingUser.getEmailId()));
        assertEquals(1, exception.getApiErrors().size());
        assertEquals("emailId", exception.getApiErrors().get(0).getField());
        assertEquals("User by %s email id already exists".formatted(existingUser.getEmailId()), exception.getApiErrors().get(0).getMessage());
    }
    @Test
    void testUserWithSameNameMobileDoestNotExist() {


         var mobileNumber = "1234567890";

        var existingUser = DataFactory.user().mobileNumber(mobileNumber).build();

        when(userRepository.findByMobileNumber(existingUser.getMobileNumber())).thenReturn(Optional.of(existingUser));

        // Act + Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> userService.checkMobileShouldNotExist(existingUser.getMobileNumber()));
        assertEquals(1, exception.getApiErrors().size());
        assertEquals("mobileNumber", exception.getApiErrors().get(0).getField());
        assertEquals("User by %s mobile number already exists".formatted(existingUser.getMobileNumber()), exception.getApiErrors().get(0).getMessage());
    }


}

