package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class SilverColumnResponseDTO {
    private List<SilverResponseDTO> columnsSilver;
}