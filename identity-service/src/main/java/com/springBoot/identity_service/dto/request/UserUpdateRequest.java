package com.springBoot.identity_service.dto.request;

import com.springBoot.identity_service.validation.DobConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
     String password;
     String firstName;
     String lastName;

     @DobConstraint(min = 18, message = "INVALID_DOB")
     LocalDate birthDate;

     List<String> roles;
}
