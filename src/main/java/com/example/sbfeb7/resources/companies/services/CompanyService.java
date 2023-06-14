package com.example.sbfeb7.resources.companies.services;

import com.example.sbfeb7.lib.errors.ValidationError;
import com.example.sbfeb7.lib.errors.ValidationException;
import com.example.sbfeb7.resources.companies.entities.Company;
import com.example.sbfeb7.resources.companies.repos.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company getCompanyById(UUID id) {
        return companyRepository.findById(id).orElseThrow(() -> new ValidationException(new ValidationError("companyId", "Company by id %s does not exist".formatted(id))));
    }
}
