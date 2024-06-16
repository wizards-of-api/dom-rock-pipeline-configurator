package com.domrock.configurator.Config;


import com.domrock.configurator.Interface.CompanyRepository;
import com.domrock.configurator.Interface.PermissionRepository;
import com.domrock.configurator.Interface.UserRepository;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PermissionRepository permissionRepository;
    private final CompanyRepository companyRepository;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            UserDTO admin = UserDTO
                    .builder()
                    .name("admin")
                    .email("superuser@wiz.com")
                    .password(passwordEncoder.encode("admin"))
                    .permission(permissionRepository.findById(1))
                    .build();

            userService.createUser(admin);
            log.debug("Created admin user - {}", admin);
        }
    }
}
