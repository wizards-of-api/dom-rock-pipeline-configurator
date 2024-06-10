package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {
    String name;
    String email;
    String password;
}
