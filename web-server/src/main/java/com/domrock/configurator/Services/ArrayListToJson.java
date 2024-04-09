package com.domrock.configurator.Services;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class ArrayListToJson {

    public String toJson(ArrayList<String> arrayList) {
        Gson gson = new Gson();
        return gson.toJson(arrayList);
    }

    public String newExcelToJson(String filePath) {
        CsvConverter csvConverter = new CsvConverter();
        ArrayList<String> headers = csvConverter.newExcelConverter(filePath);
        return toJson(headers);
    }

    public String oldExcelToJson(String filePath) {
        CsvConverter csvConverter = new CsvConverter();
        ArrayList<String> headers = csvConverter.oldExcelConverter(filePath);
        return toJson(headers);
    }

    public String csvToJson(String filePath) {
        CsvConverter csvConverter = new CsvConverter();
        ArrayList<String> headers = csvConverter.csvConverter(filePath);
        return toJson(headers);
    }
}
