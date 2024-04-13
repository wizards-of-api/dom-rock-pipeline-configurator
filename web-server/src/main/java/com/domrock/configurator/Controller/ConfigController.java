package com.domrock.configurator.controller;

import com.domrock.configurator.Interface.LZMetadataConfigInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.domrock.configurator.Model.ConfigModel.ColumnConfig;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.ColumnConfigDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.DataConfigDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.MetadataConfigDTO;
import com.domrock.configurator.Services.ColumnConfigServices;
import com.domrock.configurator.Services.LZMetadataConfigServices;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    LZMetadataConfigServices lzMetadataServices;

    @Autowired
    ColumnConfigServices lzColumnConfigServices;

    @Autowired
    LZMetadataConfigInterface lzRepository;

    @PostMapping
    public ResponseEntity<LZMetadataConfig> postConfig(@RequestBody DataConfigDTO data){
        MetadataConfigDTO metadataConfigDTO = data.metadata();
        try {
            LZMetadataConfig lzMetadataConfigBase = new LZMetadataConfig(metadataConfigDTO);
            LZMetadataConfig lzMetadataConfig = lzMetadataServices.saveLzMetadataConfig(lzMetadataConfigBase); 
            for (ColumnConfigDTO columnConfigDTO : data.columns()) {
                System.out.println(columnConfigDTO);
                ColumnConfig columnConfig = new ColumnConfig(lzMetadataConfig, columnConfigDTO);
                lzColumnConfigServices.saveConfigModel(columnConfig);
            }
            return new ResponseEntity<>(lzMetadataConfig, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping("/list-view")
    @GetMapping
    public ResponseEntity<Page<LZMetadataConfig>> listDoctor(@PageableDefault(size = 16, sort={"name"}) Pageable paginator){
        Page<LZMetadataConfig> page = lzRepository.findAll(paginator);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Transactional
    public LZMetadataConfig details(@PathVariable Integer id){
        var configNum = lzRepository.findById(id);
        if(configNum.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return configNum.get();
    }
}