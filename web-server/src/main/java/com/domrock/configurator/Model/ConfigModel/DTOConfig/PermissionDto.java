package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.domrock.configurator.Model.ConfigModel.Permission}
 */
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto implements Serializable {
    Integer id;
    String type;
}