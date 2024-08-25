package com.springBoot.identity_service.controller;

import com.springBoot.identity_service.dto.request.ApiResponse;
import com.springBoot.identity_service.dto.request.UserCreationRequest;
import com.springBoot.identity_service.dto.request.UserUpdateRequest;
import com.springBoot.identity_service.dto.response.UserResponse;
import com.springBoot.identity_service.entity.User;
import com.springBoot.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController
{
    UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
       ApiResponse<User> apiResponse = new ApiResponse<>();

       apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getAllUsers() {
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        log.info("Username: {}", authentication.getName());
//        authentication.getAuthorities().forEach(grantedAuthority -> log.info("GrantedAuthority: {}", grantedAuthority));
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUser())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.findUserById(userId))
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable("userId") String userId , @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);
        return ApiResponse.<String>builder()
                .result("User deleted")
                .build();
    }
}
