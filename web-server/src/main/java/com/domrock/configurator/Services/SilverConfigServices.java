package com.domrock.configurator.Services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domrock.configurator.Interface.SilverConfigInterface;
import com.domrock.configurator.Model.ConfigModel.SilverConfig;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.SilverConfigDTO;

@Service
public class SilverConfigServices {

    @Autowired
    private SilverConfigInterface silverConfigInterface;

    @Autowired
    private ModelMapper modelMapper;

    public SilverConfigDTO saveSilverConfig(SilverConfigDTO silverConfigDTO) {
        SilverConfig silverConfig = modelMapper.map(silverConfigDTO, SilverConfig.class);
        silverConfigInterface.save(silverConfig);
        return silverConfigDTO;
    }

    public SilverConfigDTO getConfigById(Integer id) {
        Optional<SilverConfig> silverConfig = silverConfigInterface.findById(id);
        SilverConfigDTO silverConfigDTO = modelMapper.map(silverConfig.get(), SilverConfigDTO.class);
        return silverConfigDTO;
    }

}
