package com.domrock.configurator.Controller;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.JwtAuhenticationResponseDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.LoginRequestDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.SignupRequestDTO;
import com.domrock.configurator.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public JwtAuhenticationResponseDTO signup(@RequestBody SignupRequestDTO request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/login")
    public JwtAuhenticationResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authenticationService.login(request);
    }
}
