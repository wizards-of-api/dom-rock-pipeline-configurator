package com.domrock.configurator.Services;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.ColumnResponseDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileConverter {
    List<String> headers = new ArrayList<>();
    public List<ColumnResponseDTO> csvConverter(String filePath, String separator) {
        try {
            FileReader reader = new FileReader(filePath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            for (CSVRecord record : csvParser) {
                for (int i = 0; i < (csvParser.getRecords().size()); i++) {
                    for (String values : record) {
                        String[] headersArrayList = values.split(separator, 0);
                        for (String header : headersArrayList) {
                            headers.add(header);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertColumnToJson(headers);
    }

    public List<ColumnResponseDTO> excelConverter(String filePath, String separator) {
            try {
                FileInputStream file = new FileInputStream(filePath);
                XSSFWorkbook wkbk = new XSSFWorkbook(file);
                XSSFSheet sheet = wkbk.getSheetAt(0);
                Row row = sheet.getRow(0);
                String[] headersToVector = new String[row.getLastCellNum()];
    
                for (int i = 0; i < (row.getLastCellNum()); i++) {
                    Cell cell = row.getCell(i);
                    headersToVector[i] = cell.toString();
                }
                for (String header : headersToVector) {
                    header.split(separator, 0);
                    headers.add(header);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return convertColumnToJson(headers);
        }
        
    public List<ColumnResponseDTO> typeSpreadsheet(String fileExtension, String filePath, String separator) throws Exception {
        if (fileExtension == "csv") {
            csvConverter(filePath, separator);
        } else if (fileExtension == "Excel") {
            excelConverter(filePath, separator);
        } else {
            throw new Exception("Arquivo inválido!");
        } 
        return convertColumnToJson(headers);
    }

    public List<ColumnResponseDTO> createFileCsv(MultipartFile file, String fileName, String separator){
        List<ColumnResponseDTO>  columns = new ArrayList<>();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                columns  = csvConverter(serverFile.getAbsolutePath(), separator);
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
        return columns;
    }

    public List<ColumnResponseDTO> convertColumnToJson(List<String> fileCsv){
        List<ColumnResponseDTO> columns = new ArrayList<>();
        for (int i = 0; i<fileCsv.size(); i++){
             columns.add(new ColumnResponseDTO(fileCsv.get(i),i));
        }
        return columns;
    }
}
