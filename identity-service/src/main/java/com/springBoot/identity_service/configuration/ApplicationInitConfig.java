package com.springBoot.identity_service.configuration;

import com.springBoot.identity_service.entity.Role;
import com.springBoot.identity_service.entity.User;
import com.springBoot.identity_service.enums.Roles;
import com.springBoot.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner runner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()){
//                var roles = new HashSet<Role>();
//                roles.add(Roles.ADMIN.name());
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                       //.roles(roles)
                        .build();

                userRepository.save(user);
                log.warn("Admin user has been created with default password: admin, please change password");
            }
        };
    }
}
