CREATE TABLE silver 
(
    id_silver INT auto_increment PRIMARY KEY,
    column_id int NOT NULL,
    column_from varchar(255) NOT NULL,
    column_to varchar(255) NOT NULL, 
    CONSTRAINT fk_column_id FOREIGN KEY (column_id) REFERENCES file_column (column_id) 
);