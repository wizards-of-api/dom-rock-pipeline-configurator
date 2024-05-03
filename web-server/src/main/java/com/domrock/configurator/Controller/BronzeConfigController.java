package com.domrock.configurator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.domrock.configurator.Views;
import com.domrock.configurator.Services.ArrayListToJson;
import com.domrock.configurator.Interface.LZMetadataConfigInterface;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;
import com.domrock.configurator.Services.ColumnConfigServices;
import com.domrock.configurator.Services.LZMetadataConfigServices;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.Optional;

@RestController
@RequestMapping("/bronze-config")
@CrossOrigin(origins = "http://localhost:5173")
public class BronzeConfigController {
    @Autowired
    LZMetadataConfigInterface lzMetadataConfigInterface;

    @Autowired
    LZMetadataConfigServices lzMetadataServices;

    @Autowired
    ColumnConfigServices lzColumnConfigServices;

    @Autowired
    private ArrayListToJson arrayListToJson;

    // @GetMapping("/new-excel-to-json")
    // public String newExcelToJson(@RequestParam String filePath) {
    //     return arrayListToJson.newExcelToJson(filePath);
    // }

    // @GetMapping("/old-excel-to-json")
    // public String oldExcelToJson(@RequestParam String filePath) {
    //     return arrayListToJson.oldExcelToJson(filePath);
    // }

    @GetMapping("/list-view")
    public ResponseEntity<Page<LZMetadataConfig>> getConfigList(@PageableDefault(size = 16, sort={"name"}) Pageable paginator){
        Page<LZMetadataConfig> page = lzMetadataConfigInterface.findAll(paginator);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }     

    @GetMapping("/{id}")
    @Transactional
    @JsonView(Views.Bronze.class)
    public ResponseEntity<LZMetadataConfig> getBronzeConfig(@PathVariable Integer id){
        try {
            Optional<LZMetadataConfig> bronzeConfig = lzMetadataServices.getConfigById(id); 
            if (bronzeConfig.isPresent()) {
                return new ResponseEntity<>(bronzeConfig.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }  
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<LZMetadataConfig> updateConfigById(@PathVariable Integer id, @RequestBody LZMetadataConfig newConfigData) {
        try {
            LZMetadataConfig updatedConfig = lzMetadataServices.updateConfigById(id, newConfigData);
            if (updatedConfig != null) {
                return new ResponseEntity<>(updatedConfig, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}