CREATE TABLE file_column(
column_id integer primary key,
file_id integer,
column_name varchar(50) not null,
column_type varchar(50) not null,
column_empty int not null,
column_description varchar(225) not null,
column_active int not null,
FOREIGN key (file_id) references lz_config(id_file)
);