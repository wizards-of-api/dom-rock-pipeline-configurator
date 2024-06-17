package com.domrock.configurator.Controller;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.BronzeValidatedDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.ListBronzeValidatedDTO;
import com.domrock.configurator.Services.LZMetadataConfigServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/silver")
@CrossOrigin(origins = "http://localhost:5173")
public class SilverConfig {
    @Autowired
    private LZMetadataConfigServices lzMetadataConfigServices;

    @GetMapping("/get-bronze-validated/{cnpj}")
    public ResponseEntity<ListBronzeValidatedDTO> getBronzeValidated(@PathVariable String cnpj) {
        List<BronzeValidatedDTO> bronzeValidatedDTOS = lzMetadataConfigServices.getAllBronzeValidated(cnpj);
        try {
            ListBronzeValidatedDTO fileList = new ListBronzeValidatedDTO(bronzeValidatedDTOS);
            return ResponseEntity.ok(fileList);
        } catch (Exception e) {
            return ResponseEntity.ok().build();
        }
    }

}
