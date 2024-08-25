package com.springBoot.identity_service.service;

import com.springBoot.identity_service.dto.request.RoleRequest;
import com.springBoot.identity_service.dto.response.RoleResponse;
import com.springBoot.identity_service.entity.Role;
import com.springBoot.identity_service.mapper.RoleMapper;
import com.springBoot.identity_service.repository.PermissionRepository;
import com.springBoot.identity_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    private static final Logger log = LoggerFactory.getLogger(RoleService.class);
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse createRole(RoleRequest request) {
        log.info("RoleName: {}", request.getName());
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }
    public List<RoleResponse> getAllRoles() {
        return roleRepository
                .findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void deleteRole(String roleId) {
        roleRepository.deleteById(roleId);
    }
}
