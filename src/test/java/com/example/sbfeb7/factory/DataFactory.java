package com.example.sbfeb7.factory;

import com.example.sbfeb7.resources.companies.entities.Company;
import com.example.sbfeb7.resources.employees.entities.Employee;
import com.example.sbfeb7.resources.employees.repos.EmployeeRepository;
import com.example.sbfeb7.resources.employees.request.EmployeeRequest;
import com.example.sbfeb7.resources.users.entities.User;
import com.example.sbfeb7.resources.users.entities.UserRoleType;
import com.example.sbfeb7.resources.users.request.UserRequest;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class DataFactory {
    private static final Faker faker = new Faker(Locale.ENGLISH);

    public static UserRequest.UserRequestBuilder userRequest() {
        return UserRequest.builder()
                //10 digit mobile number
                .mobileNumber(faker.regexify("[1-9]\\d{9}"))
                .countryCode(faker.number().numberBetween(1, 100))
                .mobileVerifiedAt(LocalDateTime.now())
                .name(faker.name().fullName())
                .emailId(faker.internet().emailAddress())
                .role(UserRoleType.ADMIN);
    }

    public static User.UserBuilder user() {
        return User.builder()
                .id(UUID.randomUUID())
                //10 digit mobile number
                .mobileNumber(faker.regexify("[1-9]\\d{9}"))
                .countryCode(faker.number().numberBetween(1, 100))
                .mobileVerifiedAt(LocalDateTime.now())
                .name(faker.name().fullName() + "_cool")
                .emailId(faker.internet().emailAddress())
                .role(UserRoleType.ADMIN);
    }

    public static Employee.EmployeeBuilder employee() {
        return Employee.builder()
                .id(UUID.randomUUID())
                .name(faker.name().name());
    }

    public static EmployeeRequest.EmployeeRequestBuilder employeeRequest(UUID companyId) {
        return EmployeeRequest.builder()
                .name(faker.name().name())
                .companyId(companyId);
    }

    public static Company.CompanyBuilder company() {
        return Company.builder()
                .id(UUID.randomUUID())
                .companyName(faker.name().name());
    }

}
