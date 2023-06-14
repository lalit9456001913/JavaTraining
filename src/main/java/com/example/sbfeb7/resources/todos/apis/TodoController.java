package com.example.sbfeb7.resources.todos.apis;

import com.example.sbfeb7.lib.errors.ValidationError;
import com.example.sbfeb7.lib.errors.ValidationException;
import com.example.sbfeb7.resources.employees.mappers.EmployeeMapper;
import com.example.sbfeb7.resources.employees.request.EmployeeRequest;
import com.example.sbfeb7.resources.employees.response.EmployeeResponse;
import com.example.sbfeb7.resources.employees.services.EmployeeService;
import com.example.sbfeb7.resources.todos.entities.Todo;
import com.example.sbfeb7.resources.todos.mappers.TodoMapper;
import com.example.sbfeb7.resources.todos.repos.TodosRepository;
import com.example.sbfeb7.resources.todos.request.TodoRequest;
import com.example.sbfeb7.resources.todos.response.TodoResponse;
import com.example.sbfeb7.resources.todos.services.TodoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/todos")
public class TodoController {

    private TodosRepository todosRepository;
    private TodoService todoService;

    private TodoMapper todoMapper = TodoMapper.INSTANCE;

    public TodoController(TodosRepository todosRepository, TodoService todoService) {
        this.todosRepository = todosRepository;
        this.todoService = todoService;
    }

    @GetMapping()
    public ResponseEntity<List<Todo>> getTodos() {
        List<Todo> todos = todosRepository.findAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getSingleTodo(@RequestParam(name = "id") UUID id) {
//        Todo byId = todosRepository.findById(id).orElseThrow();
        Optional<Todo> optionalTodo = todosRepository.findById(id);
        Todo byId = optionalTodo
                .orElseThrow(() ->
                        new ValidationException(
                                new ValidationError("id", "Todo by id %s does not exist".formatted(id))));
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @GetMapping("/descriptions/{description}")
    public ResponseEntity<Todo> getTodoByDescription(@RequestParam(name="description") String description, @RequestParam UUID id) {
        var todo = todosRepository.findByDescriptionAndId(description, id).orElseThrow();
        return new ResponseEntity<>(todo, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoRequest todoRequest) {
        Todo todo = todoMapper.todoRequestToTodo(todoRequest);
        Todo savedTodo = todoService.createTodo(todo);
        TodoResponse todoResponse = todoMapper.todoToTodoResponse(savedTodo);
        System.out.println("todoresponse===="+todoResponse);
        return new ResponseEntity<>(todoResponse, HttpStatus.CREATED);
    }
}
