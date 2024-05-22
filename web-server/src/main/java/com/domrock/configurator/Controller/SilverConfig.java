package com.domrock.configurator.Controller;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.BronzeValidatedDTO;
import com.domrock.configurator.Services.LZMetadataConfigServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/silver")
@CrossOrigin(origins = "http://localhost:5173")
public class SilverConfig {
    @Autowired
    private LZMetadataConfigServices lzMetadataConfigServices;

    @GetMapping("/get-bronze-validated")
    public ResponseEntity<List<BronzeValidatedDTO>> getBronzeValidated() {
        List<BronzeValidatedDTO> bronzeValidatedDTOS = lzMetadataConfigServices.getAllBronzeValidated();
        try {
            return ResponseEntity.ok(bronzeValidatedDTOS);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
