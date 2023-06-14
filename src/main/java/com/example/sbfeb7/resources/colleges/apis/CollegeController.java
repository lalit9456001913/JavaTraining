package com.example.sbfeb7.resources.colleges.apis;

import com.example.sbfeb7.resources.colleges.entities.College;
import com.example.sbfeb7.resources.colleges.repos.CollegeRepository;
import com.example.sbfeb7.resources.employees.mappers.EmployeeMapper;
import com.example.sbfeb7.resources.employees.request.EmployeeRequest;
import com.example.sbfeb7.resources.employees.response.EmployeeResponse;
import com.example.sbfeb7.resources.employees.services.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Validated
@Transactional
public class CollegeController {
    private  CollegeRepository collegeRepository;

    public CollegeController(CollegeRepository collegeRepository){
        this.collegeRepository=collegeRepository;
    }
     @PostMapping("/college")
     public ResponseEntity<College> createCollege(@RequestBody College college){
        college.setId(UUID.randomUUID());
        College saved=collegeRepository.save(college);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }
}
