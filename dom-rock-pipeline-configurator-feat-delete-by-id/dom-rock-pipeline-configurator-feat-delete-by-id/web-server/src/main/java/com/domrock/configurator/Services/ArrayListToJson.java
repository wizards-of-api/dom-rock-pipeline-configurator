package com.domrock.configurator.Services;

import com.google.gson.Gson;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArrayListToJson {
    public String toJson(List<String> arrayList) {
        Gson gson = new Gson();
        return gson.toJson(arrayList);
    }

    public String newExcelToJson(String filePath) {
        CsvConverter csvConverter = new CsvConverter();
        List<String> headers = csvConverter.newExcelConverter(filePath);
        return toJson(headers);
    }

    public String oldExcelToJson(String filePath) {
        CsvConverter csvConverter = new CsvConverter();
        List<String> headers = csvConverter.oldExcelConverter(filePath);
        return toJson(headers);
    }
}
