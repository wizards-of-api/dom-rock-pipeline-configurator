package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SilverConfigDTO {
    private Integer silverId;
    private Integer columnId;
    private String from;
    private String to;
}