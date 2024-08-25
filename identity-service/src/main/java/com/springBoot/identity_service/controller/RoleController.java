package com.springBoot.identity_service.controller;

import com.springBoot.identity_service.dto.request.ApiResponse;
import com.springBoot.identity_service.dto.request.RoleRequest;
import com.springBoot.identity_service.dto.response.RoleResponse;
import com.springBoot.identity_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request) {
        log.info(request.getName());
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAllRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAllRoles())
                .build();
    }

    @DeleteMapping("{roleId}")
    ApiResponse<String> deleteRole(@PathVariable String roleId){
        roleService.deleteRole(roleId);
        return ApiResponse.<String>builder()
                .result("Deleted role")
                .build();
    }
}
