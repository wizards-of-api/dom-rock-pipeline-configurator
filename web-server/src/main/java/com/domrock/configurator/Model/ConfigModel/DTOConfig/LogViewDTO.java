package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogViewDTO {
    private Integer id;
    private String logDateTime;
    private String userName;
    private String companyName;
    private String action;
}
