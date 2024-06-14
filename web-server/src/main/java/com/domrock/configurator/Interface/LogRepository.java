package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.LogViewDTO;
import com.domrock.configurator.Model.ConfigModel.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer> {

    @Query(value = "SELECT l.log_id as id," +
            "date_format(l.log_datetime,'%d-%m-%Y %H:%i:%s') as logDateTime," +
            "u.name as userName," +
            "c.fantasy_name as companyName," +
            "l.log_action as action " +
            "FROM log l left join user u on l.log_user_email = u.email " +
            "left join company c on l.log_company_cnpj = c.cnpj"
            , nativeQuery = true)
    List<Object[]> findAllLogsWithUserNameAndCompanyName();
}
