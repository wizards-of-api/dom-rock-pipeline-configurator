package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import com.domrock.configurator.Model.ConfigModel.SilverConfig;

import java.util.List;

public record ColumnConfigDTO(
    Integer columnId,
    String columnName,
    Integer columnNumber,
    String type,
    boolean canBeNull,
    String description,
    Integer status,
    boolean hash,
    boolean valid,
    List<SilverConfig> columnsFromTo
) {}
