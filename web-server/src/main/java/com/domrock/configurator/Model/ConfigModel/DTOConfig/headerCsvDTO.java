package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import org.apache.commons.csv.CSVParser;

public record headerCsvDTO(
  CSVParser file,
  boolean hasHeader,
  String fileExtension,
  String separator,
  String fileName
) {
  
}
