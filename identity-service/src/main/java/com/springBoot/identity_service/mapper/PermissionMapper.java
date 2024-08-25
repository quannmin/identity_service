package com.springBoot.identity_service.mapper;

import com.springBoot.identity_service.dto.request.PermissionRequest;
import com.springBoot.identity_service.dto.response.PermissionResponse;
import com.springBoot.identity_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);

}
