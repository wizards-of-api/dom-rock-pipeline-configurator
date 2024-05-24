package com.domrock.configurator.Model.ConfigModel.DTOConfig;

public record SilverConfigDTO(
    Integer silverId,
    Integer columnId,
    String from, 
    String to
) {}