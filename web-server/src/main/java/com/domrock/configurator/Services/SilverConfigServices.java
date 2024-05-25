package com.domrock.configurator.Services;

import java.util.*;
import java.util.stream.Collectors;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.SilverConfigDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domrock.configurator.Interface.ColumnConfigInterface;
import com.domrock.configurator.Interface.SilverConfigInterface;
import com.domrock.configurator.Model.ConfigModel.ColumnConfig;
import com.domrock.configurator.Model.ConfigModel.SilverConfig;

@Service
public class SilverConfigServices {

    @Autowired
    SilverConfigInterface silverConfigInterface;

    @Autowired
    ColumnConfigInterface columnConfigInterface;
    @Autowired
    private ModelMapper modelMapper;

    public SilverConfig saveSilverConfig(Integer id, SilverConfig silverData) {
        Optional<ColumnConfig> columnData = columnConfigInterface.findById(id);
        if (columnData.isPresent()) {
           ColumnConfig newColumnData = columnData.get();
           silverData.setColumnId(newColumnData);
           return silverConfigInterface.save(silverData);
        }
        else {
            return null;
        }
    }

    public Optional<SilverConfig> getConfigById(Integer id) {
        return silverConfigInterface.findById(id);
    }

    /**
     * Retrieves a list of all SilverConfigs as DTOs by the specified file ID.
     * @param fileId the ID of the file the SilverConfigDTO objects are queried from.
     * @return a list of SilverConfigDTO objects corresponding to the queried columns
     * associated with the specified file ID
     */
    public List<Object> getAllSilverConfigByFileId(Integer fileId) {
        List<Object[]> queryObjects = silverConfigInterface.findAllSilverByFileId(fileId);
        if (queryObjects.isEmpty()) {
            throw new NoSuchElementException("Silver config not found");
        } else {
            return queryObjects.stream()
                    .map(objects -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("silverId", objects[0]);
                        map.put("columnId", objects[1]);
                        map.put("columnName", objects[2]);
                        map.put("from", objects[3]);
                        map.put("to", objects[4]);
                        return map;
                    })
                    .collect(Collectors.toList());
        }
    }

}
