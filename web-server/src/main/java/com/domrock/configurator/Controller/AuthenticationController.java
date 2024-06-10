package com.domrock.configurator.Controller;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.JwtAuhenticationResponseDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.LoginRequestDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.SignupRequestDTO;
import com.domrock.configurator.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
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
