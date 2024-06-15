package com.domrock.configurator.Services;

import com.domrock.configurator.Interface.CompanyRepository;
import com.domrock.configurator.Interface.PermissionRepository;
import com.domrock.configurator.Interface.UserRepository;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.JwtAuhenticationResponseDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.LoginRequestDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.SignupRequestDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Model.ConfigModel.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PermissionRepository permissionRepository;
    private final ModelMapper modelMapper;
    private final LogService logService;
    private final CompanyRepository companyRepository;

    public JwtAuhenticationResponseDTO signup(SignupRequestDTO request) {
        var user = User
                .builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .permission(permissionRepository.findById(request.getPermission()))
                .companyCnpj(companyRepository.findById(request.getCompanyCnpj()).orElseThrow())
                .build();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userService.createUser(userDTO);

        var jwt = jwtService.generateToken(user);
        return JwtAuhenticationResponseDTO.builder().token(jwt).build();
    }

    public JwtAuhenticationResponseDTO login(LoginRequestDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Log successful login attempt
            logService.saveLog(request.getEmail(), null, "/auth/login", 200);

            var jwt = jwtService.generateToken(user);
            return JwtAuhenticationResponseDTO.builder().token(jwt).build();
        } catch (AuthenticationException e) {
            // Log unsuccessful login attempt
            logService.saveLog(request.getEmail(), null, "/auth/login", 401);
            throw new IllegalArgumentException("Invalid credentials", e);
        }
    }

}
