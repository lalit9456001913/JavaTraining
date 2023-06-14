package com.example.sbfeb7.resources.todos.services;

import com.example.sbfeb7.resources.employees.entities.Employee;
import com.example.sbfeb7.resources.todos.entities.Todo;
import com.example.sbfeb7.resources.todos.repos.TodosRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TodoService {
    private final TodosRepository todosRepository;

    public TodoService(TodosRepository todosRepository) {
        this.todosRepository = todosRepository;
    }

    public Todo createTodo(Todo todo){
        todo.setId(UUID.randomUUID());
        return todosRepository.save(todo);
    }
}
