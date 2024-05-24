package com.domrock.configurator.Controller;

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

import com.domrock.configurator.Interface.ColumnConfigInterface;
import com.domrock.configurator.Interface.LZMetadataConfigInterface;
import com.domrock.configurator.Model.ConfigModel.ColumnConfig;
import com.domrock.configurator.Services.ColumnConfigServices;
import com.domrock.configurator.Services.LZMetadataConfigServices;
import com.domrock.configurator.Services.SilverConfigServices;

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

    @GetMapping("/list-view")
    public ResponseEntity<Page<SilverConfig>> getConfigList(@PageableDefault(size = 16) Pageable paginator){
        Page<SilverConfig> page = silverConfigInterface.findAll(paginator);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/addSilverFromTo/{id}")
    public ResponseEntity<SilverConfig> addSilverFromTo(@PathVariable int id, @RequestBody SilverConfigDTO silverConfigDTO) {
        ColumnConfig columnConfig = lzColumnConfigInterface.findById(id).get();
        SilverConfig silverConfig = new SilverConfig(columnConfig, silverConfigDTO);
        silverConfigServices.saveSilverConfig(silverConfig);
        return new ResponseEntity<>(HttpStatus.OK);
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
}