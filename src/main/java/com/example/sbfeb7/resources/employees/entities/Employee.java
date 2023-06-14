package com.example.sbfeb7.resources.employees.entities;

import com.example.sbfeb7.resources.companies.entities.Company;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "employees")
public class Employee {
    @Id
    @Column
    private UUID id;

    @Column
    private String name;

    @Column()
    @CreatedDate
    private Instant createdAt;

    @Column()
    @LastModifiedDate
    private Instant updatedAt;

    @ManyToOne
    Company company;
}

