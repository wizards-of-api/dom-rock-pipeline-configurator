package com.domrock.configurator.Controller;

import java.util.Optional;

import com.domrock.configurator.Interface.SilverConfigInterface;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.SilverConfigDTO;
import com.domrock.configurator.Model.ConfigModel.SilverConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.domrock.configurator.Interface.ColumnConfigInterface;
import com.domrock.configurator.Interface.LZMetadataConfigInterface;
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

    @Transactional
    @PostMapping("/addSilverFromTo")
    public ResponseEntity<SilverConfigDTO> addSilverFromTo(@RequestBody SilverConfigDTO silverConfigDTO) {
        try {
            silverConfigDTO = silverConfigServices.saveSilverConfig(silverConfigDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
}
