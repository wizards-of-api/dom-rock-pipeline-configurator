package com.domrock.configurator.Interface;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domrock.configurator.Model.ConfigModel.ColumnConfig;

public interface ColumnConfigInterface extends JpaRepository<ColumnConfig, Integer> {
    
}
