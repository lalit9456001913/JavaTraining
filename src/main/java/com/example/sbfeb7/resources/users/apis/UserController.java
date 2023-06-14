package com.example.sbfeb7.resources.users.apis;

import com.example.sbfeb7.resources.users.entities.User;
import com.example.sbfeb7.resources.users.mappers.UserMapper;
import com.example.sbfeb7.resources.users.request.UserRequest;
import com.example.sbfeb7.resources.users.response.UserResponse;
import com.example.sbfeb7.resources.users.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
public class UserController {

    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
//    @PreAuthorize("")
    public UserResponse getUser(@PathVariable(value = "id") UUID id) {
        User user = userService.userById(id);
        return UserMapper.INSTANCE.userToUserResponse(user);
    }

    @PostMapping("/companies/{id}/users")
    public ResponseEntity<?> createUser(@PathVariable(value = "id") UUID id, @Valid @RequestBody UserRequest userRequest) {

        logger.info("Creating user for company id: {}", id);
        User user = userMapper.userRequestToUser(userRequest);
        user = userService.createUser(user, id);
        return new ResponseEntity<>(userMapper.userToUserResponse(user), HttpStatus.CREATED);
    }


}
