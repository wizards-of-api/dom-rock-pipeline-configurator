package com.domrock.configurator.Services;

import com.domrock.configurator.Interface.LogRepository;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.LogViewDTO;
import com.domrock.configurator.Model.ConfigModel.Log;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;
    private final ModelMapper modelMapper;

    public void saveLog(String userEmail, String companyCnpj, String action, int responseCode) {
        Log log = Log.builder()
                .userEmail(userEmail)
                .companyCnpj(companyCnpj)
                .logDateTime(LocalDateTime.now())
                .action(action)
                .responseCode(responseCode)
                .build();
        logRepository.save(log);
    }

    public List<LogViewDTO> getAllLogsWithUserNameandCompanyName() {
        List<Object[]> list = logRepository.findAllLogsWithUserNameAndCompanyName();
        return list.stream()
                .map(objects -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", objects[0]);
                    map.put("logDateTime", objects[1]);
                    map.put("userName", objects[2]);
                    map.put("companyName", objects[3]);
                    map.put("action", objects[4]);
                    return modelMapper.map(map, LogViewDTO.class);
                }).collect(Collectors.toList());
    }

    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }
}
