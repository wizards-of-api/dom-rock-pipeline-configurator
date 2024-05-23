package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import com.domrock.configurator.Model.ConfigModel.ColumnConfig;

public record SilverConfigDTO(
    Integer silverId,
    String from, 
    String to
) {}