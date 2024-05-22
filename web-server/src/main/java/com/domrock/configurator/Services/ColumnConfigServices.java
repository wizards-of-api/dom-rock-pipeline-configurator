package com.domrock.configurator.Services;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domrock.configurator.Interface.ColumnConfigInterface;
import com.domrock.configurator.Model.ConfigModel.ColumnConfig;

@Service
public class ColumnConfigServices {
    @Autowired
    private ColumnConfigInterface columnConfigInterface;
    
    public ColumnConfig saveConfigModel(ColumnConfig columnConfig) {
        return columnConfigInterface.save(columnConfig);
    }
}