package com.domrock.configurator.Model.ConfigModel.DTOConfig;

public record ColumnConfigDTO(
    String name,
    Integer index,
    String type,
    boolean canBeNull,
    String description,
    Integer status,
    boolean hash,
    boolean valid
) {}
