package com.springBoot.identity_service.dto.request;

import com.springBoot.identity_service.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {
     String name;
     String description;
     Set<String> permissions;
}
