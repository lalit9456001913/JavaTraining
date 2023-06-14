package com.example.sbfeb7.resources.todos.repos;

import com.example.sbfeb7.resources.todos.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface TodosRepository extends JpaRepository<Todo, UUID> {
    public Optional<Todo> findByDescriptionAndId(String description, UUID id);
}
