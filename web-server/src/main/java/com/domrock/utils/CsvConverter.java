package com.domrock.utils;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import java.util.ArrayList;

public class CsvConverter {
    
    public ArrayList<String> csvConverter() {
        int index = 0;
        ArrayList<String> headers = new ArrayList<String>();

        SparkSession spark = SparkSession.builder()
        .appName("Read Excel with Spark")
        .master("local[*]")
        .getOrCreate();

        Dataset<Row> df = spark.read()
        .format("csv")
        .option("header", "true")
        .option("sep", ";")
        .option("inferSchema", "true")
        .load("web-server/people.csv");

        for (String column : df.columns()) {
            headers.add(index + ""); 
            headers.add(column);
            index += 1;
        }

        return headers;
    }
}