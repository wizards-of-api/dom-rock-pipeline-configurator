package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import com.domrock.configurator.Model.ConfigModel.Company;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.domrock.configurator.Model.ConfigModel.User}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    String email;
    String name;
    String password;
    Byte isSuper;
    private Set<String> companyCnpjs;

}