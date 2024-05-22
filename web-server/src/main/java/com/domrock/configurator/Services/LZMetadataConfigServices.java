package com.domrock.configurator.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.BronzeValidatedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domrock.configurator.Model.ConfigModel.ColumnConfig;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;
import com.domrock.configurator.Interface.LZMetadataConfigInterface;

@Service
public class LZMetadataConfigServices {
    @Autowired
    private LZMetadataConfigInterface lzConfigInterface;

    public LZMetadataConfig saveLzMetadataConfig(LZMetadataConfig lzConfig) {
        return lzConfigInterface.save(lzConfig);
    }

    public LZMetadataConfig updateConfigById(int id, LZMetadataConfig newConfigData) {
        Optional<LZMetadataConfig> configData = lzConfigInterface.findById(id);
        if (configData.isPresent()) {
            LZMetadataConfig updatedConfigData = configData.get();
            updatedConfigData.setFileName(newConfigData.getFileName());
            updatedConfigData.setName(newConfigData.getName());
            updatedConfigData.setFileType(newConfigData.getFileType());
            updatedConfigData.setFileOrigin(newConfigData.getFileOrigin());
            updatedConfigData.setFrequency(newConfigData.getFrequency());
            updatedConfigData.setFilePeriod(newConfigData.getFilePeriod());
            updatedConfigData.setHasHeader(newConfigData.getHasHeader());
            if (newConfigData.getColumns() != null) {
                List<ColumnConfig> updatedColumns = new ArrayList<>();
                for (ColumnConfig newColumn : newConfigData.getColumns()) {
                    newColumn.setFileId(updatedConfigData);
                    updatedColumns.add(newColumn);
                }
                updatedConfigData.setColumns(updatedColumns);
            } 
            return lzConfigInterface.save(updatedConfigData);
        } else {
            return null;
        }
    }

    public Optional<LZMetadataConfig> getConfigById(Integer id) {
        return lzConfigInterface.findById(id);
    }

    public List<BronzeValidatedDTO> getAllBronzeValidated() {
        List<Object[]> queryResult = lzConfigInterface.findBronzeValidated();
        if (queryResult.isEmpty()) {
            throw new NoSuchElementException("No Bronze validated data found");
        } else {
            return queryResult.stream()
                    .map(objects -> new BronzeValidatedDTO((Integer) objects[0], (String) objects[1]))
                    .collect(Collectors.toList());
        }
    }
}
