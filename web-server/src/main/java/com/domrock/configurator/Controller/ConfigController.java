package com.domrock.configurator.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.DataConfigDTO;

@RestController
@RequestMapping("/config")
public class ConfigController {
    @PostMapping
    public void postConfig(@RequestBody DataConfigDTO data){

    }
}
