CREATE TABLE file_column(
column_id integer auto_increment primary key,
file_id integer NOT NULL,
column_name varchar(50) not null,
column_type varchar(50) not null,
column_empty int not null,
column_description varchar(225) not null,
column_active int not null,
CONSTRAINT fk_file_id FOREIGN KEY (file_id) REFERENCES lz_config(file_id)
);