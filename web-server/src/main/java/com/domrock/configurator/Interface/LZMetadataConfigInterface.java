package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.BronzeValidatedDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LZMetadataConfigInterface extends JpaRepository<LZMetadataConfig, Integer>{

    @Query(value = "SELECT DISTINCT l.file_id, l.file_config_name " +
            "FROM lz_config l JOIN file_column c " +
            "ON l.file_id = c.file_id " +
            "WHERE c.column_valid != 0 AND c.column_active != 0", nativeQuery = true)
    public List<Object[]> findBronzeValidated();
    
}
