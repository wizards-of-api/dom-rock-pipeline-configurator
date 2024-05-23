package com.domrock.configurator.Controller;

import java.util.Optional;

import com.domrock.configurator.Interface.SilverConfigInterface;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.SilverConfigDTO;
import com.domrock.configurator.Model.ConfigModel.SilverConfig;
import org.modelmapper.ModelMapper;
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
import com.domrock.configurator.Model.ConfigModel.ColumnConfig;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;
import com.domrock.configurator.Services.ArrayListToJson;
import com.domrock.configurator.Services.ColumnConfigServices;
import com.domrock.configurator.Services.CsvConverter;
import com.domrock.configurator.Services.LZMetadataConfigServices;
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
    private LZMetadataConfigInterface lZMetadataConfigInterface;

    @Autowired
    private CsvConverter csvConverter;

    @Autowired
    private SilverConfigInterface silverConfigInterface;

    @Autowired
    private ArrayListToJson arrayListToJson;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/new-excel-to-json")
    public String newExcelToJson(@RequestParam String filePath) {
        return arrayListToJson.newExcelToJson(filePath);
    }

    @GetMapping("/old-excel-to-json")
    public String oldExcelToJson(@RequestParam String filePath) {
        return arrayListToJson.oldExcelToJson(filePath);
    }

    @GetMapping("/list-view")
    public ResponseEntity<Page<LZMetadataConfig>> getConfigList(@PageableDefault(size = 16, sort={"name"}) Pageable paginator){
        Page<LZMetadataConfig> page = lzMetadataConfigInterface.findAll(paginator);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }     

    @GetMapping("/{id}")
    @Transactional
    @JsonView(Views.Silver.class)
    public ResponseEntity<LZMetadataConfig> getSilverConfig(@PathVariable Integer id){
        try {
            Optional<LZMetadataConfig> silverConfig = lzMetadataServices.getConfigById(id); 
            if (silverConfig.isPresent()) {
                return new ResponseEntity<>(silverConfig.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }  
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional
    @PutMapping("/addSilverFromTo/{id}")
    public void addSilverFromTo(@PathVariable int id, @RequestBody SilverConfigDTO silverConfigDTO) {
        ColumnConfig columnConfig = lzColumnConfigInterface.findById(id).get();
        SilverConfig silverConfig = new SilverConfig(columnConfig,silverConfigDTO.silverId(),silverConfigDTO.from(),silverConfigDTO.to());
        silverConfigInterface.save(silverConfig);

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<ColumnConfig> deleteById (@PathVariable Integer id) {
        Optional<ColumnConfig> delete = lzColumnConfigInterface.findById(id);
        if (delete.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        lzColumnConfigInterface.delete(delete.get());
        return ResponseEntity.noContent().build();
    }
}
