package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import java.util.List;

public record DataConfigDTO(
    MetadataConfigDTO metadata,
    List<ColumnConfigDTO> columns
) {}    

