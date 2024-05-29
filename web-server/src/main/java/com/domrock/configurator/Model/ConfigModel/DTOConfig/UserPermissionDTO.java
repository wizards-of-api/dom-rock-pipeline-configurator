package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import com.domrock.configurator.Model.ConfigModel.UserPermission;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link UserPermission}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPermissionDTO implements Serializable {
    String userEmailEmail;
    Integer permissionId;
}