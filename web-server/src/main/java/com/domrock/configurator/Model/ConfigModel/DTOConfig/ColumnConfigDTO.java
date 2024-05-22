package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import com.domrock.configurator.Model.ConfigModel.SilverConfig;

import java.util.List;

public record ColumnConfigDTO(
    String name,
    Integer index,
    String type,
    boolean CanBeNull,
    String description,
    Integer status,
    boolean hash,
    boolean valid,
    List<SilverConfig> columnsFromTo
) {}
