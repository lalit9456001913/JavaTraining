package com.example.sbfeb7.resources.colleges.repos;

import com.example.sbfeb7.resources.colleges.entities.College;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CollegeRepository extends JpaRepository<College, UUID> {
}