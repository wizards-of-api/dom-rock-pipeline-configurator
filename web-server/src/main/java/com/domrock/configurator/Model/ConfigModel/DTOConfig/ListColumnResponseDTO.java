package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListColumnResponseDTO {
    private List<ColumnResponseDTO> columns;
}
