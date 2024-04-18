package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnResponseDTO {
    private String column;
    private Integer index;

    public ColumnResponseDTO(String column, Integer index){
        this.column = column;
        this.index = index;
    }
}
