package com.domrock.configurator.Interface;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;

public interface LZMetadataConfigInterface extends JpaRepository<LZMetadataConfig, Integer>{

    @Query(value = "SELECT DISTINCT l.file_id, l.file_config_name " +
            "FROM lz_config l JOIN file_column c " +
            "ON l.file_id = c.file_id " +
            "WHERE c.column_valid != 0 AND c.column_active != 0", nativeQuery = true)
    public List<Object[]> findBronzeValidated();
    
    @Query(value = "SELECT * FROM lz_config WHERE file_config_name LIKE %:searchedName%", nativeQuery = true)
    public List<LZMetadataConfig> findByName(@Param("searchedName") String searchedName);

    @Query(value = "SELECT DATE_FORMAT(file_timestamp, '%m-%Y') as Month_Year, COUNT(file_id) as QTD FROM lz_config GROUP BY DATE_FORMAT(file_timestamp, '%m-%Y') ORDER BY DATE_FORMAT(file_timestamp, '%m-%Y')", nativeQuery = true)
    public List<Object[]> countFilesByDay();

    @Query(value = "SELECT DATE_FORMAT(file_timestamp, '%m-%Y') AS Month_Year, COUNT(file_id) AS QTD FROM lz_config WHERE YEAR(file_timestamp) BETWEEN :startYear AND :endtYear GROUP BY DATE_FORMAT(file_timestamp, '%m-%Y') ORDER BY DATE_FORMAT(file_timestamp, '%m-%Y')", nativeQuery = true)
    public List<Object[]> countFilesBetweenYears(@Param("startYear") String startYear, @Param("endYear") String endYear);

}

