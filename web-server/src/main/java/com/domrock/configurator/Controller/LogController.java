package com.domrock.configurator.Controller;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.LogViewDTO;
import com.domrock.configurator.Services.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class LogController {
    private final LogService logService;

    @RequestMapping("/getall")
    @GetMapping
    public List<LogViewDTO> getAllLogs() {
        return logService.getAllLogsWithUserNameandCompanyName();
    }
}


