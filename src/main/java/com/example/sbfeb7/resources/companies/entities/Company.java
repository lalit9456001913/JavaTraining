package com.example.sbfeb7.resources.companies.entities;

import com.example.sbfeb7.resources.employees.entities.Employee;
import com.example.sbfeb7.resources.users.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "companies")
public class Company {
    @Id
    private UUID id;

    @Column
    private String companyName;

    @Column
    private String brandName;

    @Column
    @CreatedDate
    private Instant createdAt;

    @Column
    @LastModifiedDate
    private Instant updatedAt;

//    @JsonIgnore
    @ManyToMany(mappedBy = "companies")
    @Builder.Default
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "company")
    @Builder.Default
    private Set<Employee> employees = new HashSet<>();

}
