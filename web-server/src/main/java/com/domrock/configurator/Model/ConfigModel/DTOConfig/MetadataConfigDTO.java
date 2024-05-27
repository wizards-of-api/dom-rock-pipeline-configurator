package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import com.domrock.configurator.Model.ConfigModel.ColumnConfig;
import com.domrock.configurator.Model.ConfigModel.Company;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;

import java.util.List;

public record MetadataConfigDTO(
        Integer fileId,
        String name,
        Boolean hasHeader,
        String fileName,
        String fileExtension,
        String fileOrigin,
        Integer frequencyNumber,
        String frequencyType,
        Company company,
        List<ColumnConfig> columns
) {
    public MetadataConfigDTO(LZMetadataConfig lzMetadataConfig, List<ColumnConfig> columns) {
        this(
                lzMetadataConfig.getFileId(),
                lzMetadataConfig.getName(),
                lzMetadataConfig.getHasHeader() == 1,
                lzMetadataConfig.getFileName(),
                lzMetadataConfig.getFileExtension(),
                lzMetadataConfig.getFileOrigin(),
                lzMetadataConfig.getFrequencyNumber(),
                lzMetadataConfig.getFrequencyType(),
                lzMetadataConfig.getCompany(),
                columns
        );
    }
}
