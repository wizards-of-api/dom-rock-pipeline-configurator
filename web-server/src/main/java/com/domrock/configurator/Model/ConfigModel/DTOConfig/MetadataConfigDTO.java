package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;

public record MetadataConfigDTO(
        Integer fileId,
        String name,
        Boolean hasHeader,
        String fileName,
        String fileExtension,
        String fileOrigin,
        Integer frequencyNumber,
        String frequencyType
) {
    public MetadataConfigDTO(LZMetadataConfig lzMetadataConfig) {
        this(
                lzMetadataConfig.getFileId(),
                lzMetadataConfig.getName(),
                lzMetadataConfig.getHasHeader() == 1,
                lzMetadataConfig.getFileName(),
                lzMetadataConfig.getFileType(),
                lzMetadataConfig.getFileOrigin(),
                lzMetadataConfig.getFrequency(),
                lzMetadataConfig.getFilePeriod()
        );
    }
}
