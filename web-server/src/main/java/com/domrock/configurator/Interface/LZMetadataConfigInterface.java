package com.domrock.configurator.Interface;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;

public interface LZMetadataConfigInterface extends JpaRepository<LZMetadataConfig, Integer>{
    @Query(value ="SELECT * FROM lz_config WHERE file_config_name LIKE %:searchedName%", nativeQuery = true)
    List<LZMetadataConfig> findByName(@Param("searchedName") String searchedName);
}