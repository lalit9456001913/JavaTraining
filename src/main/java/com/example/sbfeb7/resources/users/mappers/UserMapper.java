package com.example.sbfeb7.resources.users.mappers;

import com.example.sbfeb7.resources.users.request.UserRequest;
import com.example.sbfeb7.resources.users.response.UserResponse;
import com.example.sbfeb7.resources.users.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse userToUserResponse(User user);

    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "recruiterManagerId")
    @Mapping(ignore = true, target = "companies")
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "tokens")
    User userRequestToUser(UserRequest userRequest);
}
