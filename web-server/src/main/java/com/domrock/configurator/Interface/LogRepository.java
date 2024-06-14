package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.LogViewDTO;
import com.domrock.configurator.Model.ConfigModel.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer> {

    @Query(value = "SELECT l.log_id, l.log_datetime, u.name, l.log_action FROM log l JOIN user u ON l.log_user_email = u.email JOIN company c ON l.log_company_cnpj = c.cnpj", nativeQuery = true)
    List<LogViewDTO> findAllLogsWithUserNameAndCompanyName();
}
