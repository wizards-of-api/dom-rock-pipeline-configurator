package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.LogViewDTO;
import com.domrock.configurator.Model.ConfigModel.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer> {

    @Query("SELECT new com.domrock.configurator.Model.ConfigModel.DTOConfig.LogViewDTO(l.id, l.logDateTime, u.name, c.fantasyName, l.action) " +
            "FROM Log l " +
            "JOIN User u ON l.userEmail = u.email " +
            "JOIN Company c ON u.company.cnpj = c.cnpj")
    List<LogViewDTO> findAllLogsWithUserNameAndCompanyName();
}
