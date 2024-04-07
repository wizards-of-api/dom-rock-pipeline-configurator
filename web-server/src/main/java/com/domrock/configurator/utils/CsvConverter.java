package com.domrock.configurator.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet; 
import org.apache.poi.ss.usermodel.Cell;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CsvConverter {
    public ArrayList<String> newExcelConverter(String filePath) {
        int index = 0;
        ArrayList<String> headers = new ArrayList<String>();

        try {
            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook wkbk = new XSSFWorkbook(file);
            XSSFSheet sheet = wkbk.getSheetAt(0);
            Row row = sheet.getRow(0);

            for (int i = 0; i < (row.getLastCellNum()); i++) {
                Cell cell = row.getCell(i);
                headers.add(index + "");
                headers.add(cell.toString());
                index += 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return headers;
    }

    public ArrayList<String> oldExcelConverter(String filePath) {
        int index = 0;
        ArrayList<String> headers = new ArrayList<String>();
    
        try {
            FileInputStream file = new FileInputStream(filePath);
            HSSFWorkbook hkbk = new HSSFWorkbook(file);
            HSSFSheet sheet = hkbk.getSheetAt(0);
            Row row = sheet.getRow(0);
    
            for (int i = 0; i < (row.getLastCellNum()); i++) {
                Cell cell = row.getCell(i);
                headers.add(index + "");
                headers.add(cell.toString());
                index += 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return headers;
    }

    public ArrayList<String> csvConverter(String filePath) {
        int index = 0;
        ArrayList<String> headers = new ArrayList<String>();
        try {
            FileReader reader = new FileReader(filePath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            for (CSVRecord record : csvParser) {
                for (int i=0; i<(csvParser.getRecords().size()); i++) {
                    for (String values : record) {
                        String[] headersArrayList = values.split(";", 0);
                        for (String header : headersArrayList) {
                            headers.add(index+"");
                            index += 1;
                            headers.add(header);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return headers;
    }
}