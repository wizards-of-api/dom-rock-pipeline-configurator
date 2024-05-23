package com.domrock.configurator.Interface;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;

import java.util.List;

public interface LZMetadataConfigInterface extends JpaRepository<LZMetadataConfig, Integer> {

    @Query(value = "SELECT DISTINCT l.file_id, l.file_config_name " + "FROM lz_config l JOIN file_column c " + "ON l.file_id = c.file_id " + "WHERE c.column_valid != 0 AND c.column_active != 0", nativeQuery = true)
    List<Object[]> findBronzeValidated();

    @Query(value = "SELECT * FROM lz_config WHERE file_config_name LIKE %:searchedName%", nativeQuery = true)
    List<LZMetadataConfig> findByName(@Param("searchedName") String searchedName);
}
