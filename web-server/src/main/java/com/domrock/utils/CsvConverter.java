package com.domrock.utils;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import java.util.Arrays;

public class CsvConverter {
    
    public static void main (String [] args) {
        SparkSession spark = SparkSession.builder()
        .appName("Read CSV with Spark")
        .master("local[*]")
        .getOrCreate();

        Dataset<Row> df = spark.read()
        .format("csv")
        .option("header", "true")
        .option("sep", ";")
        .option("inferSchema", "true")
        .load("web-server/people.csv");

        String[] headers = new String[df.columns().length]; 
        Integer[] index = new Integer[df.columns().length]; 

        df.show();

        headers = df.columns();

        for (int i=0; i<df.columns().length; i++) {
            df.withColumn("index", df.col(df.DATASET_ID_KEY()))
            .show(false);
        }
        
        System.out.println(df);
    }
}