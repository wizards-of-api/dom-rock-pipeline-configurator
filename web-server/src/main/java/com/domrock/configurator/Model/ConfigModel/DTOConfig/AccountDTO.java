package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    String name;
    String email;
    String password;
    String companyCnpj;
    String permission;

}
