package com.example.sbfeb7.resources.users.repos;

import com.example.sbfeb7.factory.DataFactory;
import com.example.sbfeb7.resources.companies.entities.Company;
import com.example.sbfeb7.resources.companies.repos.CompanyRepository;
import com.example.sbfeb7.resources.users.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    String userMobileNumber;

    @BeforeEach
    void setUp() {
        userMobileNumber = DataFactory.user().build().getMobileNumber();
    }

    @Test()
    void save() {
        UUID companyId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Company company = new Company();
        company.setId(companyId);
        Company savedCompany = companyRepository.save(company);
        assertEquals(companyId, savedCompany.getId());
        User user = DataFactory.user().id(userId).build();
        user.addCompany(savedCompany);
        userRepository.save(user);
        userRepository.findById(userId).ifPresentOrElse(
                savedUser ->
                        savedUser.getCompanies().stream().findFirst().ifPresentOrElse(
                            company1 -> assertEquals(companyId, company1.getId()),
                        () -> fail("Company not found")),
                () -> fail("User not found")
        );
    }
}