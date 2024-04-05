package com.domrock.configurator.Model.ConfigModel.DTOConfig;

public record ColumnConfigDTO(
    String name,
    Integer index,
    String type,
    boolean CanBeNull,
    String description,
    Integer status
) {}
