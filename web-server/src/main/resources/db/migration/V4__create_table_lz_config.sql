CREATE TABLE lz_config(
file_id INT auto_increment PRIMARY KEY,
file_config_name VARCHAR(50) UNIQUE NOT NULL,
file_name VARCHAR(50) NOT NULL,
file_type VARCHAR(50) NOT NULL,
file_origin VARCHAR(50) NOT NULL,
file_period VARCHAR(50) NOT NULL,
file_frequency INT NOT NULL,
file_has_header INT NOT NULL,
cnpj VARCHAR(14) NOT NULL,
CONSTRAINT fk_cnpj FOREIGN KEY (cnpj) REFERENCES company(cnpj)
);