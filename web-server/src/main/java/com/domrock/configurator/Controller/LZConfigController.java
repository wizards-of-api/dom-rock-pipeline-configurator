package com.domrock.configurator.Controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;

import com.domrock.configurator.Views;
import com.domrock.configurator.Interface.LZMetadataConfigInterface;
import com.domrock.configurator.Model.ConfigModel.ColumnConfig;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.ColumnConfigDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.ColumnResponseDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.DataConfigDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.ListColumnResponseDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.MetadataConfigDTO;
import com.domrock.configurator.Services.ArrayListToJson;
import com.domrock.configurator.Services.ColumnConfigServices;
import com.domrock.configurator.Services.CsvConverter;
import com.domrock.configurator.Services.LZMetadataConfigServices;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/lz-config")
@CrossOrigin(origins = "http://localhost:5173")
public class LZConfigController {
    @Autowired
    LZMetadataConfigInterface lzMetadataConfigInterface;

    @Autowired
    LZMetadataConfigServices lzMetadataServices;

    @Autowired
    ColumnConfigServices lzColumnConfigServices;

    @Autowired
    private CsvConverter csvConverter;

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
    public ResponseEntity<Page<LZMetadataConfig>> getConfigList(@PageableDefault(size = 16, sort={"name"}) Pageable paginator){
        Page<LZMetadataConfig> page = lzMetadataConfigInterface.findAll(paginator);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/list-view/company/{cnpj}")
    public ResponseEntity<List<LZMetadataConfig>> getAllLZByCnpj(@PathVariable String cnpj, @PageableDefault(size = 16, sort={"name"}) Pageable paginator){
        try {
            List<LZMetadataConfig> allListByCnpj = lzMetadataServices.getAllLZbyCnpj(cnpj);
            return ResponseEntity.ok(allListByCnpj);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping("/list-view/{configName}")
    public ResponseEntity<List<LZMetadataConfig>> getConfigListByName(@PathVariable String configName){
        try{
            List<LZMetadataConfig> listConfig = lzMetadataConfigInterface.findByName(configName);
        return ResponseEntity.ok(listConfig);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    @Transactional
    @JsonView(Views.LZ.class)
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

    @PostMapping("/upload-csv")
    public ResponseEntity<ListColumnResponseDTO> csvToJson(
        @RequestParam("file") MultipartFile file,
        @RequestParam("hasHeader") boolean hasHeader,
        @RequestParam("fileExtension") String fileExtension,
        @RequestParam("separator") String separator,
        @RequestParam("separator") String fileName
    ) {
        List<ColumnResponseDTO> columns = csvConverter.createFileCsv(file, fileName, fileName);
        ListColumnResponseDTO responseJson = new ListColumnResponseDTO(columns);
        return ResponseEntity.ok(responseJson);
    }

    @PostMapping("/save/{cnpj}")
    @Transactional
    @JsonView(Views.LZ.class)
    public ResponseEntity<LZMetadataConfig> postConfig(@RequestBody DataConfigDTO data, @PathVariable String cnpj){
        MetadataConfigDTO metadataConfigDTO = data.metadata();

        LZMetadataConfig lzMetadataConfigBase = new LZMetadataConfig(metadataConfigDTO);
        LZMetadataConfig lzMetadataConfig = lzMetadataServices.saveLzMetadataConfig(lzMetadataConfigBase, cnpj);
        for (ColumnConfigDTO columnConfigDTO : data.columns()) {
            ColumnConfig columnConfig = new ColumnConfig(lzMetadataConfig, columnConfigDTO);
            lzColumnConfigServices.saveConfigModel(columnConfig);
        }
        return new ResponseEntity<>(lzMetadataConfig, HttpStatus.OK);  
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<LZMetadataConfig> updateConfigById(@PathVariable Integer id, @RequestBody LZMetadataConfig newConfigData) {
        try {
            LZMetadataConfig updatedConfig = lzMetadataServices.updateLzData(id, newConfigData);
            if (updatedConfig != null) {
                return new ResponseEntity<>(updatedConfig, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<LZMetadataConfig> deleteById (@PathVariable Integer id) {
        Optional<LZMetadataConfig> delete = lzMetadataConfigInterface.findById(id);
        if (delete.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        lzMetadataConfigInterface.delete(delete.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count-lzfiles")
    public Map<String, Long> getAllCountFilesByDay() {
        System.out.println("passou na consulta padrão");
        List<Object[]> configsCompany = lzMetadataServices.getCountFilesByDay();
        Map<String, Long> configsDate = new LinkedHashMap<>();

        for (Object[] itens : configsCompany) {
            String companyName = (String) itens[0];
            Long configsCont = (Long) itens[1];
            configsDate.put(companyName, configsCont);
            System.out.println(("configuração = " + configsDate.toString()));
        }
        return configsDate;
    }

    @GetMapping("/count-lzfiles/filter-{Year}")
    public Map<String, Long> getAllCountFilesBetweenYears(@PathVariable String Year) {
        System.out.println("passou na consulta do between");
        List<Object[]> configsCompany = lzMetadataServices.getCountFilesBetweenYears(Year);
        Map<String, Long> configsDate = new LinkedHashMap<>();

        for (Object[] itens : configsCompany) {
            String companyName = (String) itens[0];
            Long configsCont = (Long) itens[1];
            configsDate.put(companyName, configsCont);
            System.out.println(("configuração = " + configsDate.toString()));
        }
        return configsDate;
    }
}