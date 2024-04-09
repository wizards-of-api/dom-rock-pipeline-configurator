package com.domrock.configurator.Services;

import java.util.ArrayList;

import com.google.gson.Gson;

public class ArrayListToJson {
  public String jsonConversion() {
    Gson gson = new Gson();
    CsvConverter csv = new CsvConverter();
    ArrayList<String> sheet = csv.csvConverter();
    return (gson.toJson(sheet));
  }
}