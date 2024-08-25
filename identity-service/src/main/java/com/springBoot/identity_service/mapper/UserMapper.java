package com.springBoot.identity_service.mapper;

import com.springBoot.identity_service.dto.request.UserCreationRequest;
import com.springBoot.identity_service.dto.request.UserUpdateRequest;
import com.springBoot.identity_service.dto.response.UserResponse;
import com.springBoot.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    UserResponse toUserResponse(User user);
}
