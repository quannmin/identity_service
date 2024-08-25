package com.springBoot.identity_service.controller;

import com.springBoot.identity_service.dto.request.ApiResponse;
import com.springBoot.identity_service.dto.request.PermissionRequest;
import com.springBoot.identity_service.dto.response.PermissionResponse;
import com.springBoot.identity_service.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController
{
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        log.info("Username: {}", authentication.getName());
//        authentication.getAuthorities().forEach(grantedAuthority -> log.info("GrantedAuthority: {}", grantedAuthority));
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

//    @GetMapping("/{userId}")
//    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
//        return ApiResponse.<UserResponse>builder()
//                .result(userService.findUserById(userId))
//                .build();
//    }
//
//    @GetMapping("/myInfo")
//    ApiResponse<UserResponse> getMyInfo() {
//        return ApiResponse.<UserResponse>builder()
//                .result(userService.getMyInfo())
//                .build();
//    }
//
//    @PutMapping("/{userId}")
//    ApiResponse<UserResponse> updateUser(@PathVariable("userId") String userId , @RequestBody UserUpdateRequest request) {
//        return ApiResponse.<UserResponse>builder()
//                .result(userService.updateUser(userId, request))
//                .build();
//    }

    @DeleteMapping("/{permissionId}")
    ApiResponse<String> deleteUser(@PathVariable("permissionId") String permissionId) {
        permissionService.deletePermissionById(permissionId);
        return ApiResponse.<String>builder()
                .result("Permission deleted")
                .build();
    }
}
