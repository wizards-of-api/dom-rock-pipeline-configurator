package com.domrock.configurator.Services;

import java.util.stream.Collectors;
import java.util.*;

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
            updatedConfigData.setFileExtension(newConfigData.getFileExtension());
            updatedConfigData.setFileOrigin(newConfigData.getFileOrigin());
            updatedConfigData.setFrequencyNumber(newConfigData.getFrequencyNumber());
            updatedConfigData.setFrequencyType(newConfigData.getFrequencyType());
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

    public LZMetadataConfig updateLzData(int id, LZMetadataConfig newConfigData) {
        Optional<LZMetadataConfig> configDataOptional = lzConfigInterface.findById(id);
        LZMetadataConfig configData = null;
        if (configDataOptional.isPresent() && newConfigData != null) {
            configData = configDataOptional.get();
            updateMetadata(configData, newConfigData);
            List<ColumnConfig> newColumns = newConfigData.getColumns();
            if (newColumns != null) {
                List<ColumnConfig> oldColumns = configData.getColumns();
                updateMetadataColumn(oldColumns,newColumns);
            }
        }
       return configData;
    }

    public void updateMetadata(LZMetadataConfig oldData, LZMetadataConfig newData){
        oldData.setFileName(newData.getFileName());
        oldData.setName(newData.getName());
        oldData.setFileExtension(newData.getFileExtension());
        oldData.setFileOrigin(newData.getFileOrigin());
        oldData.setFrequencyNumber(newData.getFrequencyNumber());
        oldData.setFrequencyType(newData.getFrequencyType());
        oldData.setHasHeader(newData.getHasHeader());
    }

    public void updateMetadataColumn(List<ColumnConfig> oldColumns, List<ColumnConfig> newColumns){
        Map<Integer, ColumnConfig> oldColumnMap = new HashMap<>();
        for (ColumnConfig oldColumn : oldColumns) {
            oldColumnMap.put(oldColumn.getColumnNumber(), oldColumn);
        }
        for (ColumnConfig newColumn : newColumns) {
            ColumnConfig oldColumn = oldColumnMap.get(newColumn.getColumnNumber());
            if (oldColumn != null) {
                oldColumn.setColumnName(newColumn.getColumnName());
                oldColumn.setType(newColumn.getType());
                oldColumn.setCanBeNull(newColumn.getCanBeNull());
                oldColumn.setDescription(newColumn.getDescription());
                oldColumn.setStatus(newColumn.getStatus());
            }
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
    public List<Object[]> getCountFilesByDay() {
        return lzConfigInterface.countFilesByDay();
    }
    
    
    public List<Object[]> getCountFilesBetweenYears(String startYear, String endYear) {
        return lzConfigInterface.countFilesBetweenYears(startYear,endYear);
    }
}
