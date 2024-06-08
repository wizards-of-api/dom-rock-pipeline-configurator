package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import java.util.List;

public record ListConfigsByCompanyDTO(
        List<ConfigsCompanyDTO> configs
) {}