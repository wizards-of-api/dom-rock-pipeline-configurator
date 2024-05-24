package com.domrock.configurator.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domrock.configurator.Interface.ColumnConfigInterface;
import com.domrock.configurator.Interface.SilverConfigInterface;
import com.domrock.configurator.Model.ConfigModel.ColumnConfig;
import com.domrock.configurator.Model.ConfigModel.SilverConfig;

@Service
public class SilverConfigServices {

    @Autowired
    SilverConfigInterface silverConfigInterface;

    @Autowired
    ColumnConfigInterface columnConfigInterface;

    public SilverConfig saveSilverConfig(Integer id, SilverConfig silverData) {
        Optional<ColumnConfig> columnData = columnConfigInterface.findById(id);
        if (columnData.isPresent()) {
           ColumnConfig newColumnData = columnData.get();
           silverData.setColumnId(newColumnData);
           return silverConfigInterface.save(silverData);
        }
        else {
            return null;
        }
    }

    public Optional<SilverConfig> getConfigById(Integer id) {
        return silverConfigInterface.findById(id);
    }

}
