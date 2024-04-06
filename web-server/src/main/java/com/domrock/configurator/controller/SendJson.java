package com.domrock.configurator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domrock.configurator.utils.ArrayListToJson;

@RestController
@RequestMapping("/api")
public class SendJson {

    @GetMapping("/convert")
    public String convertArrayListToJson() {
        ArrayListToJson arrayListToJson = new ArrayListToJson();
        return arrayListToJson.jsonConversion();
    }
}
