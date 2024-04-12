package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.ColumnConfig;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnConfigInterface extends JpaRepository<ColumnConfig, Integer> {
    
}
