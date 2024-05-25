package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.SilverConfigDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.domrock.configurator.Model.ConfigModel.SilverConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface SilverConfigInterface extends JpaRepository<SilverConfig, Integer>{
    @Query(value =
            "SELECT s.id_silver, s.column_id, s.column_from, s.column_to " +
            "FROM silver s " +
            "LEFT JOIN file_column f " +
            "ON s.column_id = f.column_id " +
            "WHERE f.file_id = :fileId",
            nativeQuery = true)
    public List<Object[]> findAllSilverByFileId(@Param("fileId") Integer fileId);
}
