package com.domrock.configurator.Services;

import org.springframework.beans.factory.annotation.Autowired;

import com.domrock.configurator.Interface.SilverConfigInterface;
import com.domrock.configurator.Model.ConfigModel.SilverConfig;

public class SilverConfigServices {

    @Autowired
    SilverConfigInterface silverConfigInterface;

    public SilverConfig saveSilverConfig(SilverConfig lzConfig) {
        return silverConfigInterface.save(lzConfig);
    }
}
