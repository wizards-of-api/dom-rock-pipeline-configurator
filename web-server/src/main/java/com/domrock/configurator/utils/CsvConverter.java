package com.domrock.configurator.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CsvConverter {
    public ArrayList<String> csvConverter() {
        int index = 0;
        ArrayList<String> headers = new ArrayList<String>();

        try {
            FileInputStream file = new FileInputStream("web-server/people.xlsx");
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
}