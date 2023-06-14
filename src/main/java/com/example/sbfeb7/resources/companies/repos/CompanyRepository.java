package com.example.sbfeb7.resources.companies.repos;

import com.example.sbfeb7.resources.companies.entities.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "companies")
public interface CompanyRepository extends CrudRepository<Company, UUID>, PagingAndSortingRepository<Company, UUID> {
}