package com.domrock.configurator.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domrock.configurator.Interface.SilverConfigInterface;
import com.domrock.configurator.Model.ConfigModel.SilverConfig;

@Service
public class SilverConfigServices {

    @Autowired
    SilverConfigInterface silverConfigInterface;

    public SilverConfig saveSilverConfig(SilverConfig lzConfig) {
        return silverConfigInterface.save(lzConfig);
    }

    public Optional<SilverConfig> getConfigById(Integer id) {
        return silverConfigInterface.findById(id);
    }
}
