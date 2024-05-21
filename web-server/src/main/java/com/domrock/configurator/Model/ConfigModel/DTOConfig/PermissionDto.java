package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.domrock.configurator.Model.ConfigModel.Permission}
 */
@Value
public class PermissionDto implements Serializable {
    Integer id_permission;
    String type;
    Set<String> userEmails;
}