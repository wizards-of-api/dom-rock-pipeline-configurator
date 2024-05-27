package com.domrock.configurator.Services;

import java.util.*;
import java.util.stream.Collectors;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.SilverConfigDTO;
import jakarta.transaction.Transactional;
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

    @Transactional
    public SilverConfigDTO saveSilver(SilverConfigDTO silverConfigDTO) {
        if (silverConfigDTO == null) {
            throw new IllegalArgumentException("SilverConfigDTO cannot be null");
        } else {
            SilverConfig silverConfig = modelMapper.map(silverConfigDTO, SilverConfig.class);
            Optional<ColumnConfig> column = columnConfigInterface.findById(silverConfigDTO.getColumnId());
            silverConfig.setColumnId(column.get());
            silverConfigInterface.save(silverConfig);
            return silverConfigDTO;
        }
    }

    @Transactional
    public Optional<SilverConfig> getConfigById(Integer id) {
        return silverConfigInterface.findById(id);
    }

    /**
     * Retrieves a list of all SilverConfigs as DTOs by the specified file ID.
     * @param fileId the ID of the file the SilverConfigDTO objects are queried from.
     * @return a list of SilverConfigDTO objects corresponding to the queried columns
     * associated with the specified file ID
     */
    @Transactional
    public List<Object> getAllSilverConfigByFileId(Integer fileId) {
        List<Object[]> queryObjects = silverConfigInterface.findAllSilverByFileId(fileId);
        if (queryObjects.isEmpty()) {
            return Collections.emptyList();
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
