package com.domrock.configurator.Controller;

import java.util.List;
import java.util.Optional;

import com.domrock.configurator.Interface.SilverConfigInterface;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.SilverConfigDTO;
import com.domrock.configurator.Model.ConfigModel.SilverConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.domrock.configurator.Views;
import com.domrock.configurator.Interface.ColumnConfigInterface;
import com.domrock.configurator.Interface.LZMetadataConfigInterface;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;
import com.domrock.configurator.Services.ArrayListToJson;
import com.domrock.configurator.Services.ColumnConfigServices;
import com.domrock.configurator.Services.LZMetadataConfigServices;
import com.domrock.configurator.Services.SilverConfigServices;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/silver-config")
@CrossOrigin(origins = "http://localhost:5173")
public class SilverConfigController {
    
    @Autowired
    LZMetadataConfigInterface lzMetadataConfigInterface;

    @Autowired
    LZMetadataConfigServices lzMetadataServices;

    @Autowired
    ColumnConfigServices lzColumnConfigServices;

    @Autowired
    ColumnConfigInterface lzColumnConfigInterface;

    @Autowired
    private SilverConfigInterface silverConfigInterface;

    @Autowired
    private SilverConfigServices silverConfigServices;

    @Autowired
    private ArrayListToJson arrayListToJson;


    @GetMapping("/new-excel-to-json")
    public String newExcelToJson(@RequestParam String filePath) {
        return arrayListToJson.newExcelToJson(filePath);
    }

    @GetMapping("/old-excel-to-json")
    public String oldExcelToJson(@RequestParam String filePath) {
        return arrayListToJson.oldExcelToJson(filePath);
    }

    @GetMapping("/list-view")
    public ResponseEntity<Page<SilverConfig>> getConfigList(@PageableDefault(size = 16) Pageable paginator){
        Page<SilverConfig> page = silverConfigInterface.findAll(paginator);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("fromTo/{id}")
    @Transactional
    @JsonView(Views.Silver.class)
    public ResponseEntity<SilverConfig> getSilverFromToConfig(@PathVariable Integer id){
        try {
            Optional<SilverConfig> silverConfig = silverConfigServices.getConfigById(id); 
            if (silverConfig.isPresent()) {
                return new ResponseEntity<>(silverConfig.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Transactional
    @JsonView(Views.Silver.class)
    public ResponseEntity<LZMetadataConfig> getLZConfig(@PathVariable Integer id) {
        try {
            Optional<LZMetadataConfig> lzConfig = lzMetadataServices.getConfigById(id);
            if (lzConfig.isPresent()) {
                return new ResponseEntity<>(lzConfig.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PostMapping("/save/{id}")
    public ResponseEntity<SilverConfig> postConfig(@PathVariable Integer id, @RequestBody SilverConfig data) {
       SilverConfig newSilverData = silverConfigServices.saveSilverConfig(id, data);
       if (newSilverData != null) {
        return new ResponseEntity<>(newSilverData, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<SilverConfig> deleteById (@PathVariable Integer id) {
        Optional<SilverConfig> delete = silverConfigInterface.findById(id);
        if (delete.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        silverConfigInterface.delete(delete.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-by-fileid/{fileId}")
    public ResponseEntity<List<SilverConfigDTO>> getAllSilverByFileId(@PathVariable Integer fileId) {
        try {
            List<SilverConfigDTO> silverConfigList = silverConfigServices.getAllSilverConfigByFileId(fileId);
            if (silverConfigList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(silverConfigList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}