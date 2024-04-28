package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import com.domrock.configurator.Model.ConfigModel.ColumnConfig;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;

import java.util.List;
import java.util.stream.Collectors;

public record MetadataConfigDTO(
        Integer fileId,
        String name,
        Boolean hasHeader,
        String fileName,
        String fileExtension,
        String fileOrigin,
        Integer frequencyNumber,
        String frequencyType,
        List<ColumnConfig> columns
) {
    public MetadataConfigDTO(LZMetadataConfig lzMetadataConfig, List<ColumnConfig> columns) {
        this(
                lzMetadataConfig.getFileId(),
                lzMetadataConfig.getName(),
                lzMetadataConfig.getHasHeader() == 1,
                lzMetadataConfig.getFileName(),
                lzMetadataConfig.getFileType(),
                lzMetadataConfig.getFileOrigin(),
                lzMetadataConfig.getFrequency(),
                lzMetadataConfig.getFilePeriod(),
                columns
        );
    }
}
