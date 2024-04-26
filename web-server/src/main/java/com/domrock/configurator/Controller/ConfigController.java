package com.domrock.configurator.Controller;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.*;
import com.domrock.configurator.Services.ArrayListToJson;
import com.domrock.configurator.Services.FileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.domrock.configurator.Model.ConfigModel.ColumnConfig;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;
import com.domrock.configurator.Services.ColumnConfigServices;
import com.domrock.configurator.Services.LZMetadataConfigServices;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/lz-config")
@CrossOrigin(origins = "http://localhost:5173")
public class ConfigController {
    @Autowired
    LZMetadataConfigServices lzMetadataServices;

    @Autowired
    ColumnConfigServices lzColumnConfigServices;

    @Autowired
    private ArrayListToJson arrayListToJson;

    @Autowired
    private FileConverter fileConverter;

    // @GetMapping("/new-excel-to-json")
    // public String newExcelToJson(@RequestParam String filePath) {
    //     return arrayListToJson.newExcelToJson(filePath);
    // }

    // @GetMapping("/old-excel-to-json")
    // public String oldExcelToJson(@RequestParam String filePath) {
    //     return arrayListToJson.oldExcelToJson(filePath);
    // }

    @PostMapping("/upload-csv")
    public ResponseEntity<ListColumnResponseDTO> csvToJson(
            @RequestParam("file") MultipartFile file,
            @RequestParam("hasHeader") boolean hasHeader,
            @RequestParam("fileExtension") String fileExtension,
            @RequestParam("separator") String separator,
            @RequestParam("separator") String fileName
            ) throws Exception {
        List<ColumnResponseDTO> columns = fileConverter.typeSpreadsheet(fileExtension, fileName, fileName);
        ListColumnResponseDTO responseJson = new ListColumnResponseDTO(columns);
        return ResponseEntity.ok(responseJson);
    }

    @PostMapping("/save")
    public ResponseEntity<LZMetadataConfig> postConfig(@RequestBody DataConfigDTO data){
        MetadataConfigDTO metadataConfigDTO = data.metadata();

        LZMetadataConfig lzMetadataConfigBase = new LZMetadataConfig(metadataConfigDTO);
        LZMetadataConfig lzMetadataConfig = lzMetadataServices.saveLzMetadataConfig(lzMetadataConfigBase); 
        for (ColumnConfigDTO columnConfigDTO : data.columns()) {
            ColumnConfig columnConfig = new ColumnConfig(lzMetadataConfig, columnConfigDTO);
            lzColumnConfigServices.saveConfigModel(columnConfig);
        }
        return new ResponseEntity<>(lzMetadataConfig, HttpStatus.OK);
        
    }
}