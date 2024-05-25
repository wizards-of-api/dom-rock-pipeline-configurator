package com.domrock.configurator.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * @param fileId the ID of the file the SilverConfigDTO objects are queried.
     * @return a list of SilverConfigDTO objects corresponding to the SilverConfig entities
     * associated with the specified file ID
     */
    public List<SilverConfig> getAllSilverConfigByFileId(Integer fileId) {
        List<SilverConfig> silverConfigList = silverConfigInterface.findAllSilverByFileId(fileId);
//        List<SilverConfigDTO> silverConfigDTOList = new ArrayList<SilverConfigDTO>();
//        for (SilverConfig silverConfig : silverConfigList) {
//            SilverConfigDTO silverConfigDTO = modelMapper.map(silverConfig, SilverConfigDTO.class);
//            silverConfigDTOList.add(silverConfigDTO);
//        }
        return silverConfigList;
    }

}
