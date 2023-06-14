package com.example.sbfeb7.resources.todos.entities;

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
@Table(name = "todos")
public class Todo {
    @Id
    @Column
    private UUID id;

    @Column
    private String description;

    @Column()
    @CreatedDate
    private Instant createdAt;

    @Column()
    @LastModifiedDate
    private Instant updatedAt;
}

