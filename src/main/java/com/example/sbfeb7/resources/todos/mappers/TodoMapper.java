package com.example.sbfeb7.resources.todos.mappers;

import com.example.sbfeb7.resources.todos.entities.Todo;
import com.example.sbfeb7.resources.todos.request.TodoRequest;
import com.example.sbfeb7.resources.todos.response.TodoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);


    TodoResponse todoToTodoResponse(Todo todo);
    Todo todoRequestToTodo(TodoRequest todoRequest);

}
