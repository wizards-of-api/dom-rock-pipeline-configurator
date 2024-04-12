package com.domrock.configurator.Controller;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domrock.configurator.Model.ConfigModel.ColumnConfig;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;
import com.domrock.configurator.Services.ColumnConfigServices;
import com.domrock.configurator.Services.LZMetadataConfigServices;

@RestController
@RequestMapping("/lz-config")
public class ConfigController {
    @Autowired
    LZMetadataConfigServices lzMetadataServices;

    @Autowired
    ColumnConfigServices lzColumnConfigServices;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/save")
    public ResponseEntity<LZMetadataConfig> postConfig(@RequestBody DataConfigDTO data){
        MetadataConfigDTO metadataConfigDTO = data.metadata();
        try {
            LZMetadataConfig lzMetadataConfigBase = new LZMetadataConfig(metadataConfigDTO);
            LZMetadataConfig lzMetadataConfig = lzMetadataServices.saveLzMetadataConfig(lzMetadataConfigBase); 
            for (ColumnConfigDTO columnConfigDTO : data.columns()) {
                ColumnConfig columnConfig = new ColumnConfig(lzMetadataConfig, columnConfigDTO);
                lzColumnConfigServices.saveConfigModel(columnConfig);
            }
            return new ResponseEntity<>(lzMetadataConfig, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}