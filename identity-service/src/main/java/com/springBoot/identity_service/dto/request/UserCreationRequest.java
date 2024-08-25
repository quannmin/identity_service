package com.springBoot.identity_service.dto.request;

import com.springBoot.identity_service.dto.GroupConstraint.FirstGroup;
import com.springBoot.identity_service.dto.GroupConstraint.SecondGroup;
import com.springBoot.identity_service.validation.DobConstraint;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;

    @DobConstraint(min = 10, message = "INVALID_DOB")
    LocalDate birthDate;
}
