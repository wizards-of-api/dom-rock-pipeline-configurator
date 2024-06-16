package com.domrock.configurator.Services;

import com.domrock.configurator.Interface.LogRepository;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.LogViewDTO;
import com.domrock.configurator.Model.ConfigModel.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    public void saveLog(String userEmail, String companyCnpj, String action) {
        Log log = Log.builder()
                .userEmail(userEmail)
                .companyCnpj(companyCnpj)
                .logDateTime(LocalDateTime.now())
                .action(action)
                .build();
        logRepository.save(log);
    }

    public List<LogViewDTO> getAllLogsWithUserNameandCompanyName() {
        return logRepository.findAllLogsWithUserNameAndCompanyName();
    }
}
