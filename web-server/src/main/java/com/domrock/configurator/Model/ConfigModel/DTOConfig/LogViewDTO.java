package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogViewDTO {
    private Integer id;
    private LocalDateTime logDateTime;
    private String userName;
    private String companyName;
    private String action;
}
