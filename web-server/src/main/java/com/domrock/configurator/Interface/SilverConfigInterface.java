package com.domrock.configurator.Interface;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domrock.configurator.Model.ConfigModel.SilverConfig;

public interface SilverConfigInterface extends JpaRepository<SilverConfig, Integer>{
    
}
