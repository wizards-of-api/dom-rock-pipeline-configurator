package com.domrock.configurator.Model.ConfigModel.DTOConfig;

public record MetadataConfigDTO(
    String name,
    boolean hasHeader,
    String fileName,
    String fileExtension,
    String fileOrigin,
    Integer frequencyNumber,
    String frequencyType
) {}
