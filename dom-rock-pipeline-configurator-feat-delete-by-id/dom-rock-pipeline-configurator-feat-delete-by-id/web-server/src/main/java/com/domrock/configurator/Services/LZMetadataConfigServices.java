package com.domrock.configurator.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;
import com.domrock.configurator.Interface.LZMetadataConfigInterface;

@Service
public class LZMetadataConfigServices {
    @Autowired
    private LZMetadataConfigInterface lzConfigInterface;

    @Autowired
    private LZMetadataConfigInterface lzConfig;

    public LZMetadataConfig saveLzMetadataConfig(LZMetadataConfig lzConfig) {
        return lzConfigInterface.save(lzConfig);
    }

    
}
